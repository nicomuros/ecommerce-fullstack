package com.ecommerce.backend.repository;

import com.ecommerce.backend.models.User;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> selectAllUsers();

    Optional<User> selectUserById(Integer userId);

    void insertUser(User user);

    void deleteUserById(Integer userId);

    boolean existUserWithEmail(String email);

    boolean existUserWithId(Integer userId);

    void updateUser(User user);

}
