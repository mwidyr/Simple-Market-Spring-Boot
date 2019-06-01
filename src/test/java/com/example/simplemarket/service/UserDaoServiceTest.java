package com.example.simplemarket.service;

import com.example.simplemarket.model.Order;
import com.example.simplemarket.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class UserDaoServiceTest {
    @TestConfiguration
    static class UserDaoServiceTestContextConfiguration {
        @Bean
        public UserDaoService getUserDaoService() {
            return new UserDaoService();
        }
    }

    @Autowired
    UserDaoService userDaoService;

    static List<User> users = new ArrayList<>();

    int userCount = 3;

    static {
        users.add(new User(1, "Widy", new Date(), OrderDaoService.orders, 5, BigDecimal.valueOf(119000)));
        users.add(new User(2, "Muh", new Date(), OrderDaoService.orders, 5, BigDecimal.valueOf(119000)));
        users.add(new User(3, "Amad", new Date(), OrderDaoService.orders, 5, BigDecimal.valueOf(119000)));
    }

    @Test
    public void saveUser() {
        Date saveDate = new Date();
        List<Order> orders = new ArrayList<>();
//        Order order1 = new Order(1, "Test Order 1", "Test", BigDecimal.valueOf(5000));
//        Order order2 = new Order(2, "Test Order 2", "Test", BigDecimal.valueOf(5000));
//        orders.add(order1);
//        orders.add(order2);
//        User request = new User("Widy Ramadhani", saveDate, orders);
//        User response = userDaoService.saveUser(request);
//        assertNotNull(response);
//        assertEquals(2, response.getOrdersCount().intValue());
//        assertEquals(BigDecimal.valueOf(10000),response.getTotalPrice());
    }
}
