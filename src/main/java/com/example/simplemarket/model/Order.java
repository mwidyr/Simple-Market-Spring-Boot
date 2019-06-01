package com.example.simplemarket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue
    private Integer id;
    private BigDecimal orderTotalPrice;
    private Integer orderCount;

//    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

//    @OneToOne(fetch = FetchType.LAZY)
//    @MapsId
//    @JsonIgnore
    private UserDetail userDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order() {
    }

    public Order(BigDecimal orderTotalPrice, Integer orderCount, List<OrderDetail> orderDetails, UserDetail userDetail) {
        this.orderTotalPrice = orderTotalPrice;
        this.orderCount = orderCount;
        this.orderDetails = orderDetails;
        this.userDetail = userDetail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(BigDecimal orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", orderTotalPrice=").append(orderTotalPrice);
        sb.append(", orderCount=").append(orderCount);
        sb.append(", orderDetails=").append(orderDetails);
        sb.append(", userDetail=").append(userDetail);
        sb.append('}');
        return sb.toString();
    }
}
