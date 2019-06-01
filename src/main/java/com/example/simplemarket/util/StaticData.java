package com.example.simplemarket.util;

import com.example.simplemarket.model.Order;
import com.example.simplemarket.model.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StaticData {
    public static List<Order> orders = new ArrayList<>();
    public static Integer orderCount = 5;
//    static {
//        orders.add(new Order(1,"Spring Boot","Book", BigDecimal.valueOf(50000)));
//        orders.add(new Order(2,"Apache Camel","Online Course", BigDecimal.valueOf(10000)));
//        orders.add(new Order(3,"Spring MVC","Online Course", BigDecimal.valueOf(55000)));
//        orders.add(new Order(4,"Modern Java 8","Book", BigDecimal.valueOf(2500)));
//        orders.add(new Order(5,"Studying Git","Book", BigDecimal.valueOf(1500)));
//    }

    public static List<User> users = new ArrayList<>();

    public static int userCount = 3;

//    static {
//        users.add(new User(1, "Widy", new Date(), orders, 5
//                , BigDecimal.valueOf(orders.stream()
//                .mapToLong(x -> x.getPrice().longValue())
//                .sum())));
//        users.add(new User(2, "Muh", new Date(), orders, 5
//                , BigDecimal.valueOf(orders.stream()
//                .mapToLong(x -> x.getPrice().longValue())
//                .sum())));
//        users.add(new User(3, "Amad", new Date(), orders, 5
//                , BigDecimal.valueOf(orders.stream()
//                .mapToLong(x -> x.getPrice().longValue())
//                .sum())));
//    }

    public static List<String> orderType = new ArrayList<>();
    static{
        orderType.add("Book");
        orderType.add("Online Course");
    }
}
