package com.example.simplemarket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer objectTotal;
    private BigDecimal objectTotalPrice;
//    @OneToOne(mappedBy = "orderDetail")
    private ObjectDetail objectDetail;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonIgnore
    private Order order;

    public OrderDetail() {
    }

    public OrderDetail(Integer objectTotal, BigDecimal objectTotalPrice, ObjectDetail objectDetail) {
        this.objectTotal = objectTotal;
        this.objectTotalPrice = objectTotalPrice;
        this.objectDetail = objectDetail;
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

    public ObjectDetail getObjectDetail() {
        return objectDetail;
    }

    public void setObjectDetail(ObjectDetail objectDetail) {
        this.objectDetail = objectDetail;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderDetail{");
        sb.append("id=").append(id);
        sb.append(", objectTotal=").append(objectTotal);
        sb.append(", objectTotalPrice=").append(objectTotalPrice);
        sb.append(", objectDetail=").append(objectDetail);
        sb.append('}');
        return sb.toString();
    }
}
