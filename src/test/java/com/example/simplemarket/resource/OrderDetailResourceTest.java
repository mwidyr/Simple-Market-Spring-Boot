package com.example.simplemarket.resource;

import com.example.simplemarket.model.ObjectDetail;
import com.example.simplemarket.model.OrderDetail;
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

    @Test
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
    }

//    @PostMapping(path = "/save")
//    public CommonResponse saveOrder(@RequestBody SaveOrderDetail request) {
//        logger.info("order detail save");
//        CommonResponse response = new CommonResponse();
//        if (request == null) {
//            response.setStatus(false);
//            response.setMessage("Order Detail Cant be save!");
//            return response;
//        }
//        if (request.getUserDetail() == null) {
//            response.setStatus(false);
//            response.setMessage("User Detail is null");
//            return response;
//        }
//        if (request.getOrderDetail() == null) {
//            response.setStatus(false);
//            response.setMessage("Order Detail is null");
//            return response;
//        }
//        //save order to get id
//        OrderDetail orderDetail = orderDetailRepository.save(request.getOrderDetail());
//        //save object
//        OrderDetail finalOrderDetail = orderDetail;
//        List<String> orderType = orderTypeRepository.findAll()
//                .stream()
//                .map(result -> result.getOrderType())
//                .collect(Collectors.toList());
//        request.getOrderDetail().getObjectDetail().stream().forEach(objectDetail -> {
//            logger.info("save object = " + objectDetail.toString());
//            ObjectDetail finalObjectDetail = objectDetail;
//            if (orderType.stream().noneMatch(data -> data.equals(finalObjectDetail.getType()))) {
//                if (orderType.stream().noneMatch(data -> data.equals(finalObjectDetail.getType()))) {
//                    throw new UserNotFoundException("Order Type is Wrong! Please use this type instead : " + orderType.toString());
//                }
//                throw new UserNotFoundException("Order Type is Wrong! Please use this type instead : " + orderType.toString());
//            }
//            objectDetail.setOrderDetail(finalOrderDetail);
//            objectDetail.setId(null);
//            objectDetail = objectDetailRepository.save(objectDetail);
//            //save order and add object
//            finalOrderDetail.setObjectTotal(finalOrderDetail.getObjectTotal() == null ? 1 : finalOrderDetail.getObjectTotal() + 1);
//            finalOrderDetail.setObjectTotalPrice(finalOrderDetail.getObjectTotalPrice() == null ? BigDecimal.ZERO
//                    : finalOrderDetail.getObjectTotalPrice().add(objectDetail.getPrice()));
//        });
//        orderDetail.setUserDetail(request.getUserDetail());
//        orderDetail = orderDetailRepository.save(orderDetail);
//        response.setStatus(true);
//        response.setObject(orderDetail);
//        return response;
//    }
//

}
