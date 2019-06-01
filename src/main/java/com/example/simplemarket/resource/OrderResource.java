package com.example.simplemarket.resource;

import com.example.simplemarket.exception.UserNotFoundException;
import com.example.simplemarket.model.Order;
import com.example.simplemarket.service.OrderDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class OrderResource {
    @Autowired
    OrderDaoService orderDaoService;

    @GetMapping(path = "/order")
    public List<Order> retrieveAllOrder(){
        return orderDaoService.findAll();
    }

    @GetMapping(path="/order/{orderId}")
    public Resource<Order> retrieveOrder(@PathVariable Integer orderId ){
        Order Order = orderDaoService.findOne(orderId);
        if(Order==null) throw  new UserNotFoundException("id-"+orderId);
        Resource<Order> resource = new Resource<>(Order);

        ControllerLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).retrieveAllOrder());
        resource.add(linkTo.withRel("all-Orders"));

        return resource;
    }

    @PostMapping(path="/order")
    public ResponseEntity<Object> saveOrder(@Valid @RequestBody Order order ){
        Order OrderSave = orderDaoService.saveOrder(order);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(OrderSave.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path="/order/delete/{orderId}")
    public void deleteOrder(@PathVariable Integer OrderId ){
        Order Order = orderDaoService.deleteById(OrderId);
        if(Order==null) throw  new UserNotFoundException("id-"+OrderId);
    }
}
