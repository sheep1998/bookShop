package com.example.bookshop.Controller;

import com.example.bookshop.Model.User;
import com.example.bookshop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/user/loggin")
    @ResponseBody
    public Map<String,Object> logResult(HttpSession session,
                                        @RequestParam("email") String email,
                                        @RequestParam("password") String password){
        HashMap<String,Object> hashMap=new HashMap<String, Object>();
        List<User> user = userService.queryUser(email);

        hashMap.put("username",user.get(0).getUserName());
        hashMap.put("id",user.get(0).getUserId());
        hashMap.put("avatar",user.get(0).getAvatarUrl());

        session.setAttribute("user", user.get(0));
        System.out.println(session.getId());


        return hashMap;
    }
}
