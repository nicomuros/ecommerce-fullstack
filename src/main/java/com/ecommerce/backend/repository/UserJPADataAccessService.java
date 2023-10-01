package com.ecommerce.backend.repository;

import com.ecommerce.backend.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("UserJpa")
public class UserJPADataAccessService implements UserDao{

    private final UserRepository userRepository;

    public UserJPADataAccessService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> selectAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> selectUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void insertUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public boolean existUserWithEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public boolean existUserWithId(Integer userId) {
        return userRepository.existsUserById(userId);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }
}
