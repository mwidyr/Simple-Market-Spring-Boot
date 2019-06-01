package com.example.simplemarket.resource;


import com.example.simplemarket.exception.UserNotFoundException;
import com.example.simplemarket.model.Order;
import com.example.simplemarket.model.User;
import com.example.simplemarket.repository.OrderRepository;
import com.example.simplemarket.repository.OrderTypeRepository;
import com.example.simplemarket.repository.UserRepository;
import com.example.simplemarket.service.UserDaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserJPAResource {
    private static final Logger logger = LoggerFactory.getLogger(UserJPAResource.class);

    @Autowired
    UserDaoService userDaoService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderTypeRepository orderTypeRepository;

    @GetMapping(path = "/jpa/user")
    public List<User> retrieveAllUser() {
        logger.info("GET ALL");
        return userRepository.findAll();
    }

    @GetMapping(path = "/jpa/user/{userId}")
    public Resource<User> retrieveUser(@PathVariable Integer userId) {
        logger.info("GET USER");
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) throw new UserNotFoundException("Not Found id-" + userId);
        Resource<User> resource = new Resource<>(user.get());

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

//    @PostMapping(path = "/jpa/user")
//    public ResponseEntity<Object> saveUser(
//            @Valid
//            @RequestBody User user) throws Exception {
//        logger.info("saving user = "+user.toString());
//        List<User> users = userRepository.findAll();
//
//        if (users.stream().anyMatch(x -> user.getName().equals(x.getName()))) {
//            throw new UserNotFoundException("Name " + user.getName() + " already exist, please user another name");
//        }
//        user.setOrdersCount(user.getOrders().size());
//        user.setTotalPrice(BigDecimal.valueOf(user.getOrders().stream()
//                .mapToLong(x -> x.getPrice().longValue())
//                .sum()));
//
//        User userSave = userRepository.save(user);
//
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(userSave.getId()).toUri();
//        return ResponseEntity.created(location).body(userSave);
//    }
//
//    @DeleteMapping(path = "/jpa/user/delete/{userId}")
//    public void deleteUser(@PathVariable Integer userId) {
//        logger.info("DELETE USER");
//        userRepository.deleteById(userId);
////        if(user==null) throw  new UserNotFoundException("Not Found User By Id-"+userId);
//    }
//
//    @GetMapping(path = "/jpa/user/payment")
//    public void payOrder(@RequestParam("userId") Integer userId, @RequestParam("totalPay") BigDecimal totalPay) {
//        logger.info("totalPay USER");
//        Optional<User> user = userRepository.findById(userId);
//        if (!user.isPresent()) throw new UserNotFoundException("Not Found User By Id -" + userId);
//
//        if (user.get().getTotalPrice().compareTo(BigDecimal.ZERO) <= 0) {
//            throw new UserNotFoundException("Billing is already paid!");
//        }
//        logger.info("USER == " + user.toString());
//        user.get().setTotalPrice(user.get().getTotalPrice().subtract(totalPay));
//        userRepository.save(user.get());
//        if (user.get().getTotalPrice().compareTo(BigDecimal.ZERO) <= 0) {
//            throw new UserNotFoundException("Payment is more than it should be, change = " + user.get().getTotalPrice());
//        }
//    }
//
//    @GetMapping("/jpa/user/{id}/orders")
//    public List<Order> retrieveAllUsers(@PathVariable int id) {
//        Optional<User> userOptional = userRepository.findById(id);
//        if (!userOptional.isPresent()) {
//            throw new UserNotFoundException("not found id - " + id);
//        }
//        return userOptional.get().getOrders();
//    }
//
//    @PostMapping(path = "/jpa/user/{id}/orders")
//    public ResponseEntity<Object> createOrder(@PathVariable int id,
//                                              @Valid
//                                              @RequestBody Order order) throws Exception {
//        Optional<User> userSave = userRepository.findById(id);
//        if (!userSave.isPresent()) throw new UserNotFoundException("not found id - " + id);
//
//        User user = userSave.get();
//        order.setUser(user);
//        List<String> orderType = orderTypeRepository.findAll()
//                .stream()
//                .map(result -> result.getOrderType())
//                .collect(Collectors.toList());
//        logger.info("List type = " + orderType.toString());
//        @Valid Order finalOrder = order;
//        if (orderType.stream().noneMatch(data -> data.equals(finalOrder.getType()))) {
//            throw new UserNotFoundException("Order Type is Wrong! Please use this type instead : " + orderType.toString());
//        }
//        order = orderRepository.save(order);
//        user.setTotalPrice(user.getTotalPrice().add(order.getPrice()));
//        user.setOrdersCount(user.getOrdersCount() + 1);
//        userRepository.save(user);
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(order.getId()).toUri();
//        return ResponseEntity.created(location).build();
//    }
}
