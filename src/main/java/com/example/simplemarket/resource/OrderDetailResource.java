package com.example.simplemarket.resource;

import com.example.simplemarket.model.ObjectDetail;
import com.example.simplemarket.model.OrderDetail;
import com.example.simplemarket.model.UserDetail;
import com.example.simplemarket.repository.ObjectDetailRepository;
import com.example.simplemarket.repository.OrderDetailRepository;
import com.example.simplemarket.repository.UserDetailRepository;
import com.example.simplemarket.util.CommonResponse;
import com.example.simplemarket.util.SaveOrderDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/order/order-detail")
public class OrderDetailResource {
    Logger logger = LoggerFactory.getLogger(ObjectDetailResource.class);
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    ObjectDetailRepository objectDetailRepository;
    @Autowired
    UserDetailRepository userDetailRepository;

    @GetMapping(path = "/getAll")
    public CommonResponse getAllOrder() {
        logger.info("order detail get all");
        List<OrderDetail> orderDetail = orderDetailRepository.findAll();
        CommonResponse response = new CommonResponse();
        response.setStatus(true);
        if (orderDetail.isEmpty()) {
            response.setStatus(false);
            response.setMessage("List Order Detail is null");
            return response;
        }
        response.setObject(orderDetail);
        return response;
    }

    @GetMapping(path = "/get/{id}")
    public CommonResponse getOrder(@PathVariable Integer id) {
        logger.info("order detail get by id");
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(id);
        CommonResponse response = new CommonResponse();
        response.setStatus(true);
        if (!orderDetail.isPresent()) {
            response.setStatus(false);
            response.setMessage("Order Detail Cant be find!");
            return response;
        }
        OrderDetail order = orderDetail.get();
        response.setObject(order);
        return response;
    }

    @PostMapping(path = "/save")
    public CommonResponse saveOrder(@RequestBody SaveOrderDetail request) {
        logger.info("order detail save");
        CommonResponse response = new CommonResponse();
        if (request == null) {
            response.setStatus(false);
            response.setMessage("Order Detail Cant be save!");
            return response;
        }
        if (request.getUserDetail() == null) {
            response.setStatus(false);
            response.setMessage("User Detail is null");
            return response;
        }
        if (request.getOrderDetail() == null) {
            response.setStatus(false);
            response.setMessage("Order Detail is null");
            return response;
        }
        //save order to get id
        OrderDetail orderDetail = orderDetailRepository.save(request.getOrderDetail());
        //save object
        for(ObjectDetail objectDetail:request.getOrderDetail().getObjectDetail()){
            logger.info("save object = "+objectDetail.toString());
            objectDetail.setOrderDetail(orderDetail);
            objectDetail.setId(null);
            objectDetail = objectDetailRepository.save(objectDetail);
            //save order and add object
            orderDetail.setObjectTotal(orderDetail.getObjectTotal() == null ? 1 : orderDetail.getObjectTotal() + 1);
            orderDetail.setObjectTotalPrice(orderDetail.getObjectTotalPrice() == null ? BigDecimal.ZERO
                    : orderDetail.getObjectTotalPrice().add(objectDetail.getPrice()));
        }
        orderDetail.setUserDetail(request.getUserDetail());
        orderDetail = orderDetailRepository.save(orderDetail);
        response.setStatus(true);
        response.setObject(orderDetail);
        return response;
    }

    @DeleteMapping(path = "/delete")
    public CommonResponse deleteOrder(@RequestBody OrderDetail request) {
        logger.info("order detail delete");
        CommonResponse response = new CommonResponse();
        if (request == null) {
            response.setStatus(false);
            response.setMessage("Order Detail Cant be delete!");
            return response;
        }
        OrderDetail orderDetail = orderDetailRepository.save(request);
        response.setStatus(true);
        response.setObject(orderDetail);
        return response;
    }
}
