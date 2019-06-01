package com.example.simplemarket.service;

import com.example.simplemarket.exception.UserNotFoundException;
import com.example.simplemarket.model.User;
import com.example.simplemarket.util.StaticData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class UserDaoService {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoService.class);
    static List<User> users = StaticData.users;

    int userCount = StaticData.userCount;

    public List<User> findAll() {
        return users;
    }

//    public User saveUser(User user) {
//        if (user.getId() == null) {
//            user.setId(++userCount);
//        }
//        if (user.getOrders().size() == 0) {
//            user.setOrdersCount(0);
//            user.setTotalPrice(BigDecimal.ZERO);
//        } else {
//            if (users.stream().anyMatch(x->user.getId().equals(x.getId()))) {
//                throw new UserNotFoundException("Id is already exist");
//            }
//            if (users.stream().anyMatch(x->user.getName().equals(x.getName()))) {
//                throw new UserNotFoundException("Name " + user.getName()+" already exist, please user another name");
//            }
//            if (!user.getOrders().stream().allMatch(order -> StaticData.orderType.contains(order.getType()))) {
//                throw new UserNotFoundException("Order Type is Wrong! Please use this type instead : " + StaticData.orderType.toString());
//            }
//            user.setOrdersCount(user.getOrders().size());
//            user.setTotalPrice(BigDecimal.valueOf(user.getOrders().stream()
//                    .mapToLong(x -> x.getPrice().longValue())
//                    .sum()));
//        }
//        users.add(user);
//        return user;
//    }

    public User findOne(Integer id) {
        User user = users.stream().filter(customer -> id.equals(customer.getId()))
                .findAny()
                .orElse(null);
        return user;
    }

    public User deleteById(Integer id) {
        User user = findOne(id);
        if (user != null) {
            logger.info("USER == " + user.toString());
            users.remove(user);
        }
        return user;
    }

    public User payTotal(Integer userId, BigDecimal totalPay) {
        User user = findOne(userId);
        if (user != null) {
            if(user.getTotalPrice().compareTo(BigDecimal.ZERO) <= 0){
                throw new UserNotFoundException("Billing is already paid!");
            }
            logger.info("USER == " + user.toString());
            user.setTotalPrice(user.getTotalPrice().subtract(totalPay));
            if (user.getTotalPrice().compareTo(BigDecimal.ZERO) <= 0) {
                throw new UserNotFoundException("Payment is more than it should be, change = " + user.getTotalPrice());
            }
        }
        return user;
    }
}
