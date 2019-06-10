package com.example.simplemarket.resource;

import com.example.simplemarket.model.UserDetail;
import com.example.simplemarket.repository.UserDetailRepository;
import com.example.simplemarket.util.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/user/detail")
public class UserDetailResource {
    Logger logger = LoggerFactory.getLogger(UserDetailResource.class);

    @Autowired
    UserDetailRepository userDetailRepository;

    @GetMapping(path = "/get/{id}")
    public CommonResponse getUser(@PathVariable Integer id) {
        Optional<UserDetail> userDetail = userDetailRepository.findById(id);
        CommonResponse response = new CommonResponse();
        if (!userDetail.isPresent()) {
            response.setStatus(false);
            response.setMessage("User Not Found with Id = " + id);
            return response;
        }
        UserDetail user = userDetail.get();
        response.setStatus(true);
        response.setObject(user);
        return response;
    }

    @GetMapping(path = "/getUserByUsername/{username}")
    public CommonResponse getUserByUsername(@PathVariable String username) {
        CommonResponse response = new CommonResponse();
        UserDetail userDetail = userDetailRepository.findByUsername(username);
        if (userDetail == null) {
            response.setStatus(false);
            response.setMessage("User Not Found with username = " + username);
            return response;
        }
        response.setStatus(true);
        response.setObject(userDetail);
        return response;
    }

    @GetMapping(path = "/getAll")
    public CommonResponse getAllUserDetail() {
        logger.info("user detail get all");
        List<UserDetail> userDetail = userDetailRepository.findAll();
        CommonResponse response = new CommonResponse();
        response.setStatus(true);
        if (userDetail.isEmpty()) {
            response.setStatus(false);
            response.setMessage("List User Detail is null");
            return response;
        }
        response.setObject(userDetail);
        return response;
    }

    @PostMapping(path = "/save")
    public CommonResponse saveUser(@RequestBody UserDetail request) {
        logger.info("user detail save");
        CommonResponse response = new CommonResponse();
        if (request == null) {
            response.setStatus(false);
            response.setMessage("User Detail Cant be save!");
            return response;
        }
        if (request.getUsername() == null) {
            response.setStatus(false);
            response.setMessage("Username is null!");
            return response;
        }
        if (request.getPassword() == null) {
            response.setStatus(false);
            response.setMessage("Password is null");
            return response;
        }
        if (request.getEmail() != null && userDetailRepository.findByEmail(request.getEmail()) != null) {
            response.setStatus(false);
            response.setMessage("Email already exist for = " + request.getEmail());
            return response;
        }
        if (request.getMobileNumber() != null &&
                userDetailRepository.findByMobileNumber(request.getMobileNumber()) != null) {
            response.setStatus(false);
            response.setMessage("Mobile number already exist for = " + request.getMobileNumber());
            return response;
        }
        UserDetail userDetail = userDetailRepository.findByUsername(request.getUsername());
        if (userDetail == null) {
            response.setStatus(false);
            response.setMessage("User Detail doesnt exist with username = " + request.getUsername());
            return response;
        }
        BeanUtils.copyProperties(request, userDetail, getNullPropertyNames(request));
        logger.info("user detail for save == " + userDetail);
        userDetail = userDetailRepository.save(userDetail);
        response.setStatus(true);
        response.setObject(userDetail);
        return response;
    }

    /**
     * Returns an array of null properties of an object
     *
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    @DeleteMapping(path = "/delete")
    public CommonResponse deleteUser(@RequestBody UserDetail request) {
        logger.info("user detail delete");
        CommonResponse response = new CommonResponse();
        if (request == null) {
            response.setStatus(false);
            response.setMessage("User Detail Cant be delete!");
            return response;
        }
        UserDetail userDetail = userDetailRepository.save(request);
        response.setStatus(true);
        response.setObject(userDetail);
        return response;
    }
}
