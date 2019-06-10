package com.example.simplemarket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer objectTotal;
    private BigDecimal objectTotalPrice;

        @OneToMany(mappedBy = "orderDetail")
//    @Transient
//    @JsonSerialize
//    @JsonDeserialize
    private List<ObjectDetail> objectDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserDetail userDetail;

    public OrderDetail() {
    }

    public OrderDetail(Integer objectTotal, BigDecimal objectTotalPrice, List<ObjectDetail> objectDetail, UserDetail userDetail) {
        this.objectTotal = objectTotal;
        this.objectTotalPrice = objectTotalPrice;
        this.objectDetail = objectDetail;
        this.userDetail = userDetail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getObjectTotal() {
        return objectTotal;
    }

    public void setObjectTotal(Integer objectTotal) {
        this.objectTotal = objectTotal;
    }

    public BigDecimal getObjectTotalPrice() {
        return objectTotalPrice;
    }

    public void setObjectTotalPrice(BigDecimal objectTotalPrice) {
        this.objectTotalPrice = objectTotalPrice;
    }

    public List<ObjectDetail> getObjectDetail() {
        return objectDetail;
    }

    public void setObjectDetail(List<ObjectDetail> objectDetail) {
        this.objectDetail = objectDetail;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderDetail{");
        sb.append("id=").append(id);
        sb.append(", objectTotal=").append(objectTotal);
        sb.append(", objectTotalPrice=").append(objectTotalPrice);
        sb.append(", objectDetail=").append(objectDetail);
        sb.append(", userDetail=").append(userDetail);
        sb.append('}');
        return sb.toString();
    }
}
