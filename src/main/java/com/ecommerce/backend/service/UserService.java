package com.ecommerce.backend.service;

import com.ecommerce.backend.exception.DuplicateResourceException;
import com.ecommerce.backend.exception.RequestValidationException;
import com.ecommerce.backend.exception.ResourceNotFoundException;
import com.ecommerce.backend.models.Product;
import com.ecommerce.backend.models.User;
import com.ecommerce.backend.payloads.ProductModificationRequest;
import com.ecommerce.backend.payloads.UserModificationRequest;
import com.ecommerce.backend.payloads.UserRegistrationRequest;
import com.ecommerce.backend.repository.UserDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(@Qualifier("UserJpa") UserDao userDao) {
         this.userDao = userDao;
    }

    public List<User> getAllUsers() { return userDao.selectAllUsers(); }

    public User getUser(Integer userId) {
        return userDao.selectUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un usuario con el id: " + userId));
    }

    public void addUser(UserRegistrationRequest request){
        if (userDao.existUserWithEmail(request.email())){
            throw new DuplicateResourceException("Ya existe un usuario con el correo " + request.email());
        }
        userDao.insertUser(
                new User(
                        request.name(),
                        request.lastName(),
                        request.phone(),
                        request.email()
                )
        );
    }
    public void deleteUserById(Integer userId) {
        if (!userDao.existUserWithId(userId)) {
            throw new ResourceNotFoundException("No se encontró un usuario con el id: " + userId);
        }
        userDao.deleteUserById(userId);
    }

    public void updateUserById(UserModificationRequest request, Integer userId) {
        User user = userDao.selectUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un producto con el id: " + userId));

        boolean changed = false;
        if ((request.name() != null) && (!request.name().equals(user.getName()))) {
            user.setName(request.name());
            changed = true;
        }

        if ((request.lastName() != null) && (!request.lastName().equals(user.getLastName()))) {
            user.setLastName(request.lastName());
            changed = true;
        }

        if ((request.email() != null) && (!request.email().equals(user.getEmail()))) {
            if (userDao.existUserWithEmail(request.email())) {
                throw new DuplicateResourceException("Ya existe un usuario con el correo " + request.email());
            }
            user.setName(request.name());
            changed = true;
        }
        if ((request.phone() != null) && (!request.phone().equals(user.getPhone()))) {
            user.setPhone(request.phone());
            changed = true;
        }
        if (!changed) {
            throw new RequestValidationException("El producto no se pudo modificar");
        }
        userDao.updateUser(user);
    }
}
