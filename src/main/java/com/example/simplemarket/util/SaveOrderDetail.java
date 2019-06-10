package com.example.simplemarket.util;

import com.example.simplemarket.model.ObjectDetail;
import com.example.simplemarket.model.OrderDetail;
import com.example.simplemarket.model.UserDetail;

public class SaveOrderDetail {
    private UserDetail userDetail;
    private OrderDetail orderDetail;

    public SaveOrderDetail() {
    }

    public SaveOrderDetail(UserDetail userDetail, OrderDetail orderDetail) {
        this.userDetail = userDetail;
        this.orderDetail = orderDetail;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SaveOrderDetail{");
        sb.append("userDetail=").append(userDetail);
        sb.append(", orderDetail=").append(orderDetail);
        sb.append('}');
        return sb.toString();
    }
}
