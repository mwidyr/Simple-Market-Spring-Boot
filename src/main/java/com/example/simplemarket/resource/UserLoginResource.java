package com.example.simplemarket.resource;

import com.example.simplemarket.model.UserDetail;
import com.example.simplemarket.model.UserLogin;
import com.example.simplemarket.repository.UserDetailRepository;
import com.example.simplemarket.repository.UserLoginRepository;
import com.example.simplemarket.util.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user")
public class UserLoginResource {
    public static final Logger logger = LoggerFactory.getLogger(UserLoginResource.class);

    @Autowired
    private UserLoginRepository userLoginRepository;
    @Autowired
    private UserDetailRepository userDetailRepository;

    @GetMapping(path = "/login")
    public CommonResponse login(@RequestParam("username") String username, @RequestParam("password") String password){
        logger.info("login user with username = "+username);
        UserLogin userLogin =
                userLoginRepository.findByUsernameAndPassword(username, password);
        CommonResponse response = new CommonResponse();
        if(userLogin!=null){
            response.setMessage("User already login with username = "+username);
            response.setStatus(false);
            return response;
        }
        UserDetail userDetail = userDetailRepository.findByUsernameAndPassword(username, password);
        if(userDetail == null){
            response.setMessage("User not found with username = "+username);
            response.setStatus(false);
            return response;
        }
        userLogin = new UserLogin();
        userLogin.setUsername(username);
        userLogin.setPassword(password);
        userLogin.setSessionId("token1213");
        userLogin.setUserDetail(userDetail);
        UserLogin login = userLoginRepository.save(userLogin);
        //save to user detail new userlogin
        userDetail.setUserLogin(userLogin);
        userDetailRepository.save(userDetail);
        response.setStatus(true);
        response.setObject(login);
        return response;
    }

    @GetMapping(path = "/logout/{username}")
    public CommonResponse logout(@PathVariable String username){
        logger.info("logout user");
        CommonResponse response = new CommonResponse();
        UserLogin userLogin = userLoginRepository.findByUsername(username);
        if(userLogin==null){
            response.setMessage("User not found with username = "+username);
            response.setStatus(false);
            return response;
        }
        userLoginRepository.delete(userLogin);
        //delete userLogin in userDetail
        UserDetail userDetail = userLogin.getUserDetail();
        userDetail.setUserLogin(null);
        userDetailRepository.save(userDetail);
        response.setStatus(true);
        response.setMessage("Success logout!");
        return response;
    }

    @PostMapping(path="/signup")
    public CommonResponse signup(@RequestBody UserDetail request){
        logger.info("login user");
        CommonResponse response = new CommonResponse();
        if(request == null){
            response.setMessage("Request Body is null!");
            response.setStatus(false);
            return response;
        }
        if(request.getUsername() == null){
            response.setMessage("Username is empty!");
            response.setStatus(false);
            return response;
        }
        if(request.getPassword() == null){
            response.setMessage("Password is empty!");
            response.setStatus(false);
            return response;
        }
        UserDetail userDetail = userDetailRepository.findByUsername(request.getUsername());
        if(userDetail!=null){
            response.setMessage("Username already exist!");
            response.setStatus(false);
            return response;
        }
        userDetail = userDetailRepository.save(request);
        response.setObject(userDetail);
        response.setStatus(true);
        return response;
    }
}
