package com.example.simplemarket.resource;

import com.example.simplemarket.model.UserDetail;
import com.example.simplemarket.model.UserLogin;
import com.example.simplemarket.repository.UserDetailRepository;
import com.example.simplemarket.repository.UserLoginRepository;
import com.example.simplemarket.util.CommonResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class UserLoginResourceTest {
    @InjectMocks
    UserLoginResource userLoginResource;
    @Mock
    private UserLoginRepository userLoginRepository;
    @Mock
    private UserDetailRepository userDetailRepository;

    @Test
    public void logout() {
        String username = "username";
        String email = "username@gmail.com";
        String password = "password";
        String sessionId = "sakti";
        String fullname = "username fullname";
        Long mobileNumber = 6280881922l;
        UserDetail userDetail = new UserDetail();
        userDetail.setId(1);
        userDetail.setOrderDetails(null);
        userDetail.setEmail(email);
        userDetail.setFullname(fullname);
        userDetail.setPassword(password);
        userDetail.setUsername(username);
        userDetail.setMobileNumber(mobileNumber);
        UserLogin userLogin = new UserLogin();
        userLogin.setId(1);
        userLogin.setSessionId(sessionId);
        userLogin.setUsername(username);
        userLogin.setPassword(password);
        userDetail.setUserLogin(userLogin);
        userLogin.setUserDetail(userDetail);
        when(userLoginRepository.findByUsername(username)).thenReturn(userLogin);
        doNothing().when(userLoginRepository).delete(userLogin);
        when(userDetailRepository.save(userDetail)).thenReturn(userDetail);
        CommonResponse response = userLoginResource.logout(username);
        assertNotNull(response);
        assertTrue(response.getStatus());
        assertEquals("Success logout!", response.getMessage());
        when(userLoginRepository.findByUsername(username)).thenReturn(null);
        response = userLoginResource.logout(username);
        assertNotNull(response);
        assertFalse(response.getStatus());
        assertEquals("User not found with username = " + username, response.getMessage());
    }

    @Test
    public void signup() {
        String username = "username";
        String email = "username@gmail.com";
        String password = "password";
        String fullname = "username fullname";
        Long mobileNumber = 6280881922l;
        UserDetail userDetail = new UserDetail();
        userDetail.setId(1);
        userDetail.setOrderDetails(null);
        userDetail.setEmail(email);
        userDetail.setFullname(fullname);
        userDetail.setPassword(password);
        userDetail.setUsername(username);
        userDetail.setMobileNumber(mobileNumber);
        when(userDetailRepository.findByUsername(username)).thenReturn(null);
        when(userDetailRepository.save(userDetail)).thenReturn(userDetail);
        CommonResponse response = userLoginResource.signup(userDetail);
        assertNotNull(response);
        assertTrue(response.getStatus());
        assertEquals(userDetail, response.getObject());
        response = userLoginResource.signup(null);
        assertFalse(response.getStatus());
        assertEquals("Request Body is null!", response.getMessage());
        userDetail.setPassword(null);
        response = userLoginResource.signup(userDetail);
        assertFalse(response.getStatus());
        assertEquals("Password is empty!", response.getMessage());
        userDetail.setUsername(null);
        response = userLoginResource.signup(userDetail);
        assertFalse(response.getStatus());
        assertEquals("Username is empty!", response.getMessage());
        userDetail.setPassword(password);
        userDetail.setUsername(username);
        when(userDetailRepository.findByUsername(username)).thenReturn(userDetail);
        when(userDetailRepository.save(userDetail)).thenReturn(userDetail);
        response = userLoginResource.signup(userDetail);
        assertFalse(response.getStatus());
        assertEquals("Username already exist!", response.getMessage());
    }

    @Test
    public void login() {
        String username = "username";
        String email = "username@gmail.com";
        String password = "password";
        String sessionId = "sakti";
        String fullname = "username fullname";
        Long mobileNumber = 6280881922l;
        UserDetail userDetail = new UserDetail();
        userDetail.setId(1);
        userDetail.setOrderDetails(null);
        userDetail.setEmail(email);
        userDetail.setFullname(fullname);
        userDetail.setPassword(password);
        userDetail.setUsername(username);
        userDetail.setMobileNumber(mobileNumber);
        UserLogin userLogin = new UserLogin();
        userLogin.setId(1);
        userLogin.setSessionId(sessionId);
        userLogin.setUsername(username);
        userLogin.setPassword(password);
        userDetail.setUserLogin(userLogin);
        userLogin.setUserDetail(userDetail);
        when(userLoginRepository.findByUsernameAndPassword(username, password)).thenReturn(null);
        doNothing().when(userLoginRepository).delete(userLogin);
        when(userDetailRepository.findByUsernameAndPassword(username, password)).thenReturn(userDetail);
        when(userLoginRepository.save(any(UserLogin.class))).thenReturn(userLogin);
        CommonResponse response = userLoginResource.login(username, password);
        assertNotNull(response);
        assertTrue(response.getStatus());
        assertEquals(userLogin, response.getObject());
        when(userDetailRepository.findByUsernameAndPassword(username, password)).thenReturn(null);
        response = userLoginResource.login(username, password);
        assertNotNull(response);
        assertFalse(response.getStatus());
        assertEquals("User not found with username = " + username, response.getMessage());
        when(userLoginRepository.findByUsernameAndPassword(username, password)).thenReturn(userLogin);
        response = userLoginResource.login(username, password);
        assertNotNull(response);
        assertFalse(response.getStatus());
        assertEquals("User already login with username = " + username, response.getMessage());
    }
}
