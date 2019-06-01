package com.example.simplemarket.resource;



import com.example.simplemarket.exception.UserNotFoundException;
import com.example.simplemarket.model.User;
import com.example.simplemarket.service.UserDaoService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {
    private static final Logger logger = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    UserDaoService userDaoService;

    @GetMapping(path = "/user")
    public List<User> retrieveAllUser(){
        logger.info("GET ALL");
        return userDaoService.findAll();
    }

    @GetMapping(path="/user/{userId}")
    public Resource<User> retrieveUser(@PathVariable Integer userId ){
        logger.info("GET USER");
        User user = userDaoService.findOne(userId);
        if(user==null) throw  new UserNotFoundException("id-"+userId);
        Resource<User> resource = new Resource<>(user);

        ControllerLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).retrieveAllUser());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

//    @GetMapping(path="/user/filtered/{userId}")
//    public Resource<MappingJacksonValue> retrieveUserFiltered(@PathVariable Integer userId ){
//        logger.info("GET USER");
//        User user = userDaoService.findOne(userId);
//        if(user==null) throw  new UserNotFoundException("id-"+userId);
//        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
//                .filterOutAllExcept("id","name");
//        FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter",filter);
//        MappingJacksonValue mapping = new MappingJacksonValue(user);
//        mapping.setFilters(filters);
//
//        Resource<MappingJacksonValue> resource = new Resource<>(mapping);
//        ControllerLinkBuilder linkTo =
//                linkTo(methodOn(this.getClass()).retrieveAllUser());
//        resource.add(linkTo.withRel("all-users"));
//        return resource;
//    }

//    @PostMapping(path="/user")
//    public ResponseEntity<Object> saveUser(
//            @Valid
//            @RequestBody User user ) throws Exception {
//        User userSave = userDaoService.saveUser(user);
//
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(userSave.getId()).toUri();
//        return ResponseEntity.created(location).build();
//    }

    @DeleteMapping(path="/user/delete/{userId}")
    public void deleteUser(@PathVariable Integer userId ){
        logger.info("DELETE USER");
        User user = userDaoService.deleteById(userId);
        if(user==null) throw  new UserNotFoundException("Not Found User By Id-"+userId);
    }

    @GetMapping(path="/user/payment")
    public void payOrder(@RequestParam("userId") Integer userId, @RequestParam("totalPay") BigDecimal totalPay){
        logger.info("totalPay USER");
        User user = userDaoService.payTotal(userId,totalPay);
        if(user==null) throw  new UserNotFoundException("Not Found User By Id -"+userId);
    }
}
