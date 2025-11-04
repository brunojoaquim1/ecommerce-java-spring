package com.awey.dscomerce.services;

import com.awey.dscomerce.entities.User;
import com.awey.dscomerce.services.exception.ForbidenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    public void validateSelOrAdmin(long userId){
        User me = userService.authenticated();
        if (!me.hasRole("ROLE_ADMIN") && !me.getId().equals(userId)) {
            throw new ForbidenException("Acess denied");
        }
    }
}
