package com.example.simplemarket.resource;

import com.example.simplemarket.exception.UserNotFoundException;
import com.example.simplemarket.model.ObjectDetail;
import com.example.simplemarket.model.OrderDetail;
import com.example.simplemarket.model.UserDetail;
import com.example.simplemarket.repository.ObjectDetailRepository;
import com.example.simplemarket.repository.OrderDetailRepository;
import com.example.simplemarket.repository.OrderTypeRepository;
import com.example.simplemarket.repository.UserDetailRepository;
import com.example.simplemarket.util.CommonResponse;
import com.example.simplemarket.util.SaveOrderDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    OrderTypeRepository orderTypeRepository;

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
        OrderDetail finalOrderDetail = orderDetail;
        List<String> orderType = orderTypeRepository.findAll()
                .stream()
                .map(result -> result.getOrderType())
                .collect(Collectors.toList());
        request.getOrderDetail().getObjectDetail().stream().forEach(objectDetail -> {
            logger.info("save object = " + objectDetail.toString());
            ObjectDetail finalObjectDetail = objectDetail;
            if (orderType.stream().noneMatch(data -> data.equals(finalObjectDetail.getType()))) {
                throw new UserNotFoundException("Order Type is Wrong! Please use this type instead : " + orderType.toString());
            }
            objectDetail.setOrderDetail(finalOrderDetail);
            objectDetail.setId(null);
            objectDetail = objectDetailRepository.save(objectDetail);
            //save order and add object
            finalOrderDetail.setObjectTotal(finalOrderDetail.getObjectTotal() == null ? 1 : finalOrderDetail.getObjectTotal() + 1);
            logger.info("object Total price == "+finalOrderDetail.getObjectTotalPrice());
            logger.info("object  price == "+objectDetail.getPrice());
            finalOrderDetail.setObjectTotalPrice(finalOrderDetail.getObjectTotalPrice() == null ? BigDecimal.ZERO.add(objectDetail.getPrice())
                    : finalOrderDetail.getObjectTotalPrice().add(objectDetail.getPrice()));
        });
        UserDetail userDetail = userDetailRepository.findByUsername(request.getUserDetail().getUsername());
        BeanUtils.copyProperties(request.getUserDetail(), userDetail, getNullPropertyNames(request.getUserDetail()));
        orderDetail.setUserDetail(userDetail);
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

    /**
     * Returns an array of null properties of an object
     *
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
