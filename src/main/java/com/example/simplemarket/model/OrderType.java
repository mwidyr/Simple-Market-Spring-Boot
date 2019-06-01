package com.example.simplemarket.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderType {
    @Id
    @GeneratedValue
    private Integer id;
    private String orderType;

    public OrderType() {
    }

    public OrderType(String orderType) {
        this.orderType = orderType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OrderType{");
        sb.append("id=").append(id);
        sb.append(", orderType='").append(orderType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
