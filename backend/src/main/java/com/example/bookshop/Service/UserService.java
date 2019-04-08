package com.example.bookshop.Service;

import com.example.bookshop.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> queryUser(String email);
}
