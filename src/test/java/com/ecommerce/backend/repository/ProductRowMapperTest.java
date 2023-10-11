package com.ecommerce.backend.repository;

import com.ecommerce.backend.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ProductRowMapperTest {


    @Test
    void mapRow() throws SQLException {
        // Given
        ProductRowMapper productRowMapper = new ProductRowMapper();
        ResultSet rs = mock(ResultSet.class);
        when(rs.getLong("id")).thenReturn(1L);
        when(rs.getString("name")).thenReturn("Nico");
        when(rs.getString("description")).thenReturn("description");
        when(rs.getInt("price")).thenReturn(25);
        when(rs.getBoolean("available")).thenReturn(true);
        when(rs.getString("img_data")).thenReturn("https://img.jpg");

        // When
        Product actual = productRowMapper.mapRow(rs, 1);
        // Then
        Product expected = new Product(
                1L,
                "Nico",
                "description",
                25,
                true,
                "https://img.jpg"
        );
        assertThat(actual).isEqualTo(expected);
    }
}