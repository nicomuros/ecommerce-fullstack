package com.ecommerce.backend.repository;

import com.ecommerce.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public boolean existsUserByEmail(String email);
    public boolean existsUserById(Integer id);
}
