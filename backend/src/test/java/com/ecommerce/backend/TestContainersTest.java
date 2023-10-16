package com.ecommerce.backend;

import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
public class TestContainersTest extends AbstractTestcontainers {

    @Test
    void canStartMySQLDB() {
        assertThat(mySQLContainer.isRunning()).isTrue();
        assertThat(mySQLContainer.isCreated()).isTrue();
    }

}
