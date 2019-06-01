package com.example.simplemarket.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

//@JsonFilter("UserFilter")
@ApiModel(description = "All details about the user.")
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @Size(min=2, message = "Name should have atleast 2 characters")
    @ApiModelProperty(notes = "name should have 2 char")
    private String name;

    @Past
    @ApiModelProperty(notes = "Birthdate should be in the past")
    private Date date;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;
    private Integer ordersCount;
    private BigDecimal totalPrice;

    public User() {
    }

    public User(Integer id,
                @Size(min = 2, message = "Name should have atleast 2 characters")
                String name,
                @Past
                Date date, List<Order> orders, Integer ordersCount, BigDecimal totalPrice) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.orders = orders;
        this.ordersCount = ordersCount;
        this.totalPrice = totalPrice;
    }

    public User(
            @Size(min = 2, message = "Name should have atleast 2 characters")
                    String name,
            @Past
                    Date date, List<Order> orders) {
        this.name = name;
        this.date = date;
        this.orders = orders;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Integer getOrdersCount() {
        return ordersCount;
    }

    public void setOrdersCount(Integer ordersCount) {
        this.ordersCount = ordersCount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", orders=" + orders +
                ", ordersCount=" + ordersCount +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
