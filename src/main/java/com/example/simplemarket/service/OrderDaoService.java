package com.example.simplemarket.service;

import com.example.simplemarket.model.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

@Component
public class OrderDaoService {
    static List<Order> orders = new ArrayList<>();
    Integer orderCount = 5;
//    static {
//        orders.add(new Order(1,"Spring Boot","Book", BigDecimal.valueOf(50000)));
//        orders.add(new Order(2,"Apache Camel","Online Course", BigDecimal.valueOf(10000)));
//        orders.add(new Order(3,"Spring MVC","Online Course", BigDecimal.valueOf(55000)));
//        orders.add(new Order(4,"Modern Java 8","Book", BigDecimal.valueOf(2500)));
//        orders.add(new Order(5,"Studying Git","Book", BigDecimal.valueOf(1500)));
//    }

    public List<Order> findAll() {
        return orders;
    }

    public Order saveOrder(Order order) {
        if (order.getId() == null) {
            order.setId(++orderCount);
        }
        orders.add(order);
        return order;
    }

    public Order findOne(Integer id){
        Iterator<Order> userList = orders.iterator();
        while(userList.hasNext()){
            Order order = userList.next();
            if(order.getId()==id){
                return order;
            }
        }
        return null;
    }

    public Order deleteById(Integer id){
        orders.forEach((Order order) -> {
            if(order.getId()==id){
                orders.remove(order);
            }
        });
        return null;
    }
}
