package com.example.simplemarket.resource;

import com.example.simplemarket.model.UserLogin;
import com.example.simplemarket.repository.UserLoginRepository;
import com.example.simplemarket.util.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/user")
public class UserLoginResource {
    public static final Logger logger = LoggerFactory.getLogger(UserLoginResource.class);

    @Autowired
    private UserLoginRepository userLoginRepository;

    @GetMapping(path = "/login")
    public CommonResponse login(@RequestParam("username") String username, @RequestParam("password") String password){
        logger.info("login user with username = "+username);
        UserLogin userLogin = null;
//                userLoginRepository.findByUsernameAndPassword(username, password);
        CommonResponse response = new CommonResponse();
        if(userLogin==null){
            response.setMessage("User not found with username = "+username);
            response.setStatus(false);
            return response;
        }
        response.setStatus(true);
        response.setObject(userLogin);
        return response;
    }
}
