package com.example.bookshop.Service.ServiceImpl;


import com.example.bookshop.DAO.UserRepository;
import com.example.bookshop.Model.User;
import com.example.bookshop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> queryUser(String email) {
        return userRepository.findByEmail(email);
    }

}
