package com.example.simplemarket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class UserDetail {
    @Id
    @GeneratedValue
    private Integer id;
    private String email;
    private String username;
    private String password;
    private Long mobileNumber;
    private String fullname;

    @OneToOne(mappedBy = "userDetail")
    private UserLogin userLogin;

    @OneToMany(mappedBy = "userDetail")
    private List<OrderDetail> orderDetails;

    public UserDetail() {
    }

    public UserDetail(String email, String username, String password, Long mobileNumber, String fullname, UserLogin userLogin, List<OrderDetail> orderDetails) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.fullname = fullname;
        this.userLogin = userLogin;
        this.orderDetails = orderDetails;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserDetail{");
        sb.append("id=").append(id);
        sb.append(", email='").append(email).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", mobileNumber=").append(mobileNumber);
        sb.append(", fullname='").append(fullname).append('\'');
        sb.append(", userLogin=").append(userLogin);
        sb.append(", orderDetails=").append(orderDetails);
        sb.append(", password=").append(password);
        sb.append('}');
        return sb.toString();
    }
}
