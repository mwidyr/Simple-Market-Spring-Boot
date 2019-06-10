package com.example.simplemarket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class ObjectDetail {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private String type;
    private String urlImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private OrderDetail orderDetail;

    public ObjectDetail() {
    }

    public ObjectDetail(String name, String description, BigDecimal price, String type, String urlImage, OrderDetail orderDetail) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
        this.urlImage = urlImage;
        this.orderDetail = orderDetail;
    }


//    public ObjectDetail(String name, String description, BigDecimal price, String type, String urlImage) {
//        this.name = name;
//        this.description = description;
//        this.price = price;
//        this.type = type;
//        this.urlImage = urlImage;
//    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ObjectDetailRepository{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", price=").append(price);
        sb.append(", type='").append(type).append('\'');
        sb.append(", orderDetail=").append(orderDetail);
        sb.append(", urlImage=").append(urlImage);
        sb.append('}');
        return sb.toString();
    }
}
