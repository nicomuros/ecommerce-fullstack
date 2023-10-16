package com.ecommerce.backend;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import com.github.javafaker.*;

import javax.sql.DataSource;
import java.sql.*;


@Testcontainers //habilitar el uso de Testcontainers en esas pruebas.
public abstract class AbstractTestcontainers {
    @BeforeAll
    static void beforeAll() {
        mySQLContainer.start();


        // Access the MySQL database within the container
        String jdbcUrl = mySQLContainer.getJdbcUrl();
        String username = mySQLContainer.getUsername();
        String password = mySQLContainer.getPassword();

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            // Query the information schema to get a list of databases
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SHOW DATABASES");

            // Print the list of databases
            while (resultSet.next()) {
                String dbName = resultSet.getString("Database");
                System.out.println("Database: " + dbName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        Flyway flyway = Flyway.configure().dataSource(
                mySQLContainer.getJdbcUrl(),
                mySQLContainer.getUsername(),
                mySQLContainer.getPassword()
        ).load();
        flyway.migrate();
        System.out.println();
    }

    @Container //contenedor de Docker que se administrará automáticamente durante la ejecución de la prueba.
    protected static MySQLContainer<?> mySQLContainer =
            new MySQLContainer<>("mysql:8.0.33")
                    .withDatabaseName("ecommerce-dao-unit-test")
                    .withUsername("muros")
                    .withPassword("password");

    @DynamicPropertySource
    private static void registerDataSourceProperties(DynamicPropertyRegistry registry){
        registry.add(
                "spring.datasource.url",
                mySQLContainer::getJdbcUrl
        );
        registry.add(
                "spring.datasource.username",
                mySQLContainer::getUsername
        );
        registry.add(
                "spring.datasource.password",
                mySQLContainer::getPassword
        );
    }

    protected static DataSource getDataSource(){
        return DataSourceBuilder
                .create()
                .driverClassName(mySQLContainer.getDriverClassName())
                .url(mySQLContainer.getJdbcUrl())
                .username(mySQLContainer.getUsername())
                .password(mySQLContainer.getPassword())
                .build();
    }

    protected static JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate(getDataSource());
    }

    protected static final Faker FAKER = new Faker();

}
