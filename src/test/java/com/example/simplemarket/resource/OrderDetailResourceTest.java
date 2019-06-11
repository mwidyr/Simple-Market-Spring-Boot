package com.example.simplemarket.resource;

import com.example.simplemarket.exception.UserNotFoundException;
import com.example.simplemarket.model.ObjectDetail;
import com.example.simplemarket.model.OrderDetail;
import com.example.simplemarket.model.OrderType;
import com.example.simplemarket.model.UserDetail;
import com.example.simplemarket.repository.ObjectDetailRepository;
import com.example.simplemarket.repository.OrderDetailRepository;
import com.example.simplemarket.repository.OrderTypeRepository;
import com.example.simplemarket.repository.UserDetailRepository;
import com.example.simplemarket.util.CommonResponse;
import com.example.simplemarket.util.SaveOrderDetail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderDetailResourceTest {
    @InjectMocks
    OrderDetailResource orderDetailResource;
    @Mock
    OrderDetailRepository orderDetailRepository;
    @Mock
    ObjectDetailRepository objectDetailRepository;
    @Mock
    UserDetailRepository userDetailRepository;
    @Mock
    OrderTypeRepository orderTypeRepository;
    String username = "username";
    String email = "username@gmail.com";
    String password = "password";
    String fullname = "username fullname";
    Long mobileNumber = 6280881922l;
    String description = "description";
    String name = "t-shirt";
    String type = "Shirt";
    String urlImage = "urlImage";
    UserDetail userDetail = new UserDetail();
    ObjectDetail objectDetail = new ObjectDetail();
    List<ObjectDetail> objdetails = new ArrayList<>();
    OrderDetail orderDetail = new OrderDetail();
    List<OrderDetail> orderList = new ArrayList<>();
    List<OrderType> orderTypes = new ArrayList<>();

    @Before
    public void setup() {
        userDetail.setId(1);
        userDetail.setOrderDetails(null);
        userDetail.setEmail(email);
        userDetail.setFullname(fullname);
        userDetail.setPassword(password);
        userDetail.setUsername(username);
        userDetail.setMobileNumber(mobileNumber);


        objectDetail.setId(1);
        objectDetail.setDescription(description);
        objectDetail.setName(name);
        objectDetail.setPrice(BigDecimal.valueOf(50000));
        objectDetail.setType(type);
        objectDetail.setUrlImage(urlImage);

        objdetails.add(objectDetail);

        orderDetail.setUserDetail(userDetail);
        orderDetail.setObjectTotalPrice(BigDecimal.valueOf(50000));
        orderDetail.setObjectTotal(1);
        orderDetail.setObjectDetail(objdetails);
        orderDetail.setId(1);
        orderList.add(orderDetail);

        orderTypes.add(new OrderType("Shirt"));
        orderTypes.add(new OrderType("Hat"));
        orderTypes.add(new OrderType("Shoes"));
        orderTypes.add(new OrderType("Pants"));
    }

    @Test
    public void getAllOrder() {
        when(orderDetailRepository.findAll()).thenReturn(orderList);
        CommonResponse response = orderDetailResource.getAllOrder();
        assertNotNull(response);
        assertTrue(response.getStatus());
        assertEquals(orderList, response.getObject());
        orderList.clear();
        when(orderDetailRepository.findAll()).thenReturn(orderList);
        response = orderDetailResource.getAllOrder();
        assertNotNull(response);
        assertFalse(response.getStatus());
        assertEquals("List Order Detail is null", response.getMessage());
    }

    @Test
    public void getOrder() {
        when(orderDetailRepository.findById(1)).thenReturn(Optional.of(orderDetail));
        CommonResponse response = orderDetailResource.getOrder(1);
        assertNotNull(response);
        assertTrue(response.getStatus());
        assertEquals((java.util.Optional.ofNullable(orderDetail)).get(), response.getObject());
        when(orderDetailRepository.findById(1)).thenReturn(Optional.empty());
        response = orderDetailResource.getOrder(1);
        assertNotNull(response);
        assertFalse(response.getStatus());
        assertEquals("Order Detail Cant be find!", response.getMessage());
    }

    @Test
    public void deleteOrder() {
        CommonResponse response = orderDetailResource.deleteOrder(null);
        assertNotNull(response);
        assertFalse(response.getStatus());
        assertEquals("Order Detail Cant be delete!", response.getMessage());
        when(orderDetailRepository.save(orderDetail)).thenReturn(orderDetail);
        response = orderDetailResource.deleteOrder(orderDetail);
        assertNotNull(response);
        assertTrue(response.getStatus());
        assertEquals(orderDetail, response.getObject());
    }

    @Test(expected = UserNotFoundException.class)
    public void saveOrder() {
        SaveOrderDetail saveOrderDetail = new SaveOrderDetail();
        CommonResponse response = orderDetailResource.saveOrder(null);
        assertNotNull(response);
        assertFalse(response.getStatus());
        assertEquals("Order Detail Cant be save!", response.getMessage());
        response = orderDetailResource.saveOrder(saveOrderDetail);
        assertNotNull(response);
        assertFalse(response.getStatus());
        assertEquals("User Detail is null", response.getMessage());
        saveOrderDetail.setUserDetail(userDetail);
        response = orderDetailResource.saveOrder(saveOrderDetail);
        assertNotNull(response);
        assertFalse(response.getStatus());
        assertEquals("Order Detail is null", response.getMessage());
        saveOrderDetail.setOrderDetail(orderDetail);
        when(orderDetailRepository.save(saveOrderDetail.getOrderDetail())).thenReturn(orderDetail);
        when(objectDetailRepository.save(any(ObjectDetail.class))).thenReturn(objectDetail);
        when(orderTypeRepository.findAll()).thenReturn(orderTypes);
        when(userDetailRepository.findByUsername(saveOrderDetail.getUserDetail().getUsername())).thenReturn(userDetail);
        response = orderDetailResource.saveOrder(saveOrderDetail);
        assertNotNull(response);
        assertTrue(response.getStatus());
        assertEquals(orderDetail, response.getObject());
        objectDetail = new ObjectDetail();
        objectDetail.setId(2);
        objectDetail.setDescription(description);
        objectDetail.setName(name);
        objectDetail.setPrice(BigDecimal.valueOf(50000));
        objectDetail.setType("lala");
        objectDetail.setUrlImage(urlImage);

        objdetails.clear();
        objdetails.add(objectDetail);

        orderDetail = new OrderDetail();
        orderDetail.setUserDetail(userDetail);
        orderDetail.setObjectTotalPrice(BigDecimal.valueOf(50000));
        orderDetail.setObjectTotal(1);
        orderDetail.setObjectDetail(objdetails);
        orderDetail.setId(2);
        saveOrderDetail.setOrderDetail(orderDetail);
        response = orderDetailResource.saveOrder(saveOrderDetail);
    }
}
