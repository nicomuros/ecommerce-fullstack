package com.ecommerce.backend.repository;

import com.ecommerce.backend.models.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("ProductJDBC")
public class ProductJDBCDataAccessService implements ProductDao{

    private final JdbcTemplate jdbcTemplate;
    private final ProductRowMapper productRowMapper;

    public ProductJDBCDataAccessService(JdbcTemplate jdbcTemplate, ProductRowMapper productRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.productRowMapper = productRowMapper;
    }

    @Override
    public List<Product> selectAllProducts() {
        String sql = """
                SELECT id, name, description, price, available, img_Data
                FROM product
                """;

        return jdbcTemplate.query(sql, productRowMapper);
    }

    @Override
    public Optional<Product> selectProductById(Long productId) {
        String sql = """
                SELECT id, name, description, price, available, img_Data
                FROM product
                WHERE id = ?
                """;

        return  jdbcTemplate
                .query(sql, productRowMapper, productId)
                .stream()
                .findFirst();
    }

    @Override
    public void insertProduct(Product product) {
        String sql = """
                INSERT INTO product(name, description, price, available, img_data)
                VALUES (?, ?, ?, ?, ?)
                """;
        int result = jdbcTemplate.update(
                sql,
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getAvailable(),
                product.getImgData()
        );
        System.out.println("jdbcTemplate.update: " + result);
    }

    @Override
    public void deleteProductById(Long productId) {
        String sql = """
                DELETE FROM product
                WHERE id = ?
                """;

        jdbcTemplate.update(
                sql,
                productId);
    }

    @Override
    public boolean existProductWithName(String name) {
        String sql = """
                SELECT count(id)
                FROM product
                WHERE name = ?
                """;
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class, name);
        return (result != null && result > 0);
    }

    @Override
    public boolean existProductWithId(Long productId) {
        String sql = """
                SELECT count(id)
                FROM product
                WHERE id = ?
                """;
        Integer count =  jdbcTemplate.queryForObject(sql, Integer.class, productId);
        return (count != null && count > 0);
    }

    @Override
    public void updateProduct(Product product) {
        if (product.getName() != null){
            String sql = "UPDATE product SET name = ? WHERE id = ?";
            jdbcTemplate.update(
                    sql,
                    product.getName(),
                    product.getId()
            );
        }
        if (product.getDescription() != null){
            String sql = "UPDATE product SET description = ? WHERE id = ?";
            jdbcTemplate.update(
                    sql,
                    product.getDescription(),
                    product.getId()
            );
        }
        if (product.getPrice() != null){
            String sql = "UPDATE product SET price = ? WHERE id = ?";
            jdbcTemplate.update(
                    sql,
                    product.getPrice(),
                    product.getId()
            );
        }
        if (product.getAvailable() != null){
            String sql = "UPDATE product SET available = ? WHERE id = ?";
            jdbcTemplate.update(
                    sql,
                    product.getAvailable(),
                    product.getId()
            );
        }
        if (product.getImgData() != null){
            String sql = "UPDATE product SET img_data = ? WHERE id = ?";
            jdbcTemplate.update(
                    sql,
                    product.getImgData(),
                    product.getId()
            );
        }
    }
}
