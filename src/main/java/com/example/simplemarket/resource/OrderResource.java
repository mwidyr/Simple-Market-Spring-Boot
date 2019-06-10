package com.example.simplemarket.resource;

import com.example.simplemarket.model.Order;
import com.example.simplemarket.repository.OrderRepository;
import com.example.simplemarket.util.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(path = "/api/order")
public class OrderResource {
    Logger logger = LoggerFactory.getLogger(ObjectDetailResource.class);
    @Autowired
    OrderRepository orderRepository;

    @GetMapping(path = "/getAll")
    public CommonResponse getAllOrder(){
        logger.info("order get all");
        List<Order> order = orderRepository.findAll();
        CommonResponse response = new CommonResponse();
        response.setStatus(true);
        if(order.isEmpty()){
            response.setStatus(false);
            response.setMessage("List Order is null");
            return response;
        }
        response.setObject(order);
        return response;
    }

    @GetMapping(path = "/get/{id}")
    public CommonResponse getOrder(@PathVariable Integer id){
        logger.info("order get by id");
        Optional<Order> orderFind = orderRepository.findById(id);
        CommonResponse response = new CommonResponse();
        response.setStatus(true);
        if(!orderFind.isPresent()){
            response.setStatus(false);
            response.setMessage("Order Cant be find!");
            return response;
        }
        Order order = orderFind.get();
        response.setObject(order);
        return response;
    }

    @PostMapping(path = "/save")
    public CommonResponse saveOrder(@RequestBody Order request){
        logger.info("order save");
        CommonResponse response = new CommonResponse();
        if(request == null){
            response.setStatus(false);
            response.setMessage("Order Cant be save!");
            return response;
        }
        Order order = orderRepository.save(request);
        response.setStatus(true);
        response.setObject(order);
        return response;
    }

    @DeleteMapping(path = "/delete")
    public CommonResponse deleteOrder(@RequestBody Order request){
        logger.info("order delete");
        CommonResponse response = new CommonResponse();
        if(request == null){
            response.setStatus(false);
            response.setMessage("Order Cant be delete!");
            return response;
        }
        Order order = orderRepository.save(request);
        response.setStatus(true);
        response.setObject(order);
        return response;
    }
}
