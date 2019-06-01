package com.example.simplemarket.resource;

import com.example.simplemarket.service.UserDaoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserResourceTest {
    @InjectMocks
    UserResource userResource;
    @Mock
    UserDaoService userDaoService;

    @Test
    public void retrieveAllUser(){

    }
}
