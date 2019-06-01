package com.example.simplemarket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class UserDetail {
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 2, message = "Name should have atleast 2 characters")
    @ApiModelProperty(notes = "name should have 2 char")
    private String fullname;
    private String email;
    private Integer mobileNumber;

//    @OneToOne(mappedBy = "userDetail")
    private Order order;

//    @OneToOne(fetch = FetchType.LAZY)
//    @MapsId
//    @JsonIgnore
    private UserLogin userLogin;

    public UserDetail() {
    }

    public UserDetail(@Size(min = 2, message = "Name should have atleast 2 characters") String fullname, String email, Integer mobileNumber, Order order, UserLogin userLogin) {
        this.fullname = fullname;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.order = order;
        this.userLogin = userLogin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Integer mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserDetail{");
        sb.append("id=").append(id);
        sb.append(", fullname='").append(fullname).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", mobileNumber=").append(mobileNumber);
        sb.append(", order=").append(order);
        sb.append(", userLogin=").append(userLogin);
        sb.append('}');
        return sb.toString();
    }
}
