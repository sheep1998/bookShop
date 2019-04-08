package com.example.bookshop.Security.Config;

import com.example.bookshop.DAO.RoleRepository;
import com.example.bookshop.DAO.UserRepository;
import com.example.bookshop.Model.Role;
import com.example.bookshop.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).get();
        if(user==null){
            throw new UsernameNotFoundException("User not found");
        }
        int roleId = user.getRoleId();
        Role role = roleRepository.findByRoleId(roleId).get();
        String authStr = "ROLE_"+role.getRoleName().toUpperCase();
        System.out.println(authStr);
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getUserPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(authStr));

    }
}
