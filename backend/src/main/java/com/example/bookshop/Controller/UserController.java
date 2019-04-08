package com.example.bookshop.Controller;

import com.example.bookshop.DAO.UserRepository;
import com.example.bookshop.Model.User;
import com.example.bookshop.Security.Config.MyUserDetailsService;
import com.example.bookshop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Resource(name = "authenticationManager")
    private AuthenticationManager authManager;

    @PostMapping(value = "user/register")
    @ResponseBody
    public HashMap<String,Object> lg(){
        User user = userService.queryUser("admin").get();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String s = bCryptPasswordEncoder.encode(user.getUserPassword());
        user.setUserPassword(s);
        userRepository.save(user);
        return null;
    }


    @PostMapping(value = "/user/loggin")
    @ResponseBody
    public HashMap<String,Object> logResult(HttpServletRequest req,
                                        @RequestParam("email") String email,
                                        @RequestParam("password") String password){
        UsernamePasswordAuthenticationToken authReq =
                new UsernamePasswordAuthenticationToken(email,password);
        Authentication auth = authManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);

        User user = userService.queryUser(email).get();
        HashMap<String,Object> hashMap=new HashMap<String, Object>();

        hashMap.put("username",user.getUserName());
        hashMap.put("id",user.getUserId());
        hashMap.put("avatar",user.getAvatarUrl());

        HttpSession session = req.getSession();
        session.setAttribute("user", user);

        return hashMap;
    }
}
