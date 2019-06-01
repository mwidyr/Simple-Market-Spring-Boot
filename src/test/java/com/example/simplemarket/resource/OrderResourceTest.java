package com.example.simplemarket.resource;

import com.example.simplemarket.model.Order;
import com.example.simplemarket.service.OrderDaoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class OrderResourceTest {
    @InjectMocks
    OrderResource orderResource;
    @Mock
    OrderDaoService orderDaoService;

    @Test
    public void retrieveAllOrder(){
//        List<Order> orders = new ArrayList<>();
//        orders.add(new Order(1,"TestOrder","Book", BigDecimal.valueOf(5000)));
//        when(orderDaoService.findAll()).thenReturn(orders);
//        List<Order> response = orderResource.retrieveAllOrder();
//        assertNotNull(response);
//        assertEquals(1, response.size());
//        assertEquals(orders.get(0),response.get(0));
    }
}
