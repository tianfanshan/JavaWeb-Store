package org.example.store.service.imp;

import org.example.store.domain.Customer;
import org.example.store.service.CustomerService;
import org.example.store.service.ServiceException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImpTest {

    CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImp();
    }

    @AfterEach
    void tearDown() {
        customerService = null;
    }

    @Test
    void loginSuccess() throws ServiceException {

        Customer customer = new Customer();
        customer.setId("1");
        customer.setPassword("123456");

        assertTrue(customerService.login(customer));
        assertNotNull(customer);
        assertEquals("1",customer.getId());
        assertEquals("pepe",customer.getName());
        assertEquals("123456",customer.getPassword());
        assertEquals("santander1",customer.getAddress());
        assertEquals("123456",customer.getPhone());
        assertEquals(111111,customer.getBirthday().getTime());
    }

    @Test
    void loginFailed() throws ServiceException {
        Customer customer = new Customer();
        customer.setId("1");
        customer.setPassword("123");
        assertFalse(customerService.login(customer));

        Customer customer1 = new Customer();
        customer1.setId("123");
        customer1.setPassword("123");
        assertThrows(ServiceException.class,()->customerService.login(customer1));
    }

    @Test
    void registerSuccess() throws ServiceException{
        Customer customer = new Customer();
        customer.setId("juanito");
        customer.setName("juan");
        customer.setPassword("123456");
        customer.setAddress("santander2");
        customer.setPhone("222222");
        customer.setBirthday(new Date(1111111L));
        customerService.register(customer);

        Customer customer1 = new Customer();
        customer1.setId("juanito");
        customer1.setPassword("123456");
        customerService.login(customer1);

        assertNotNull(customer1);
        assertEquals("juanito",customer1.getId());
        assertEquals("juan",customer1.getName());
        assertEquals("123456",customer1.getPassword());
        assertEquals("santander2",customer1.getAddress());
        assertEquals("222222",customer1.getPhone());
        assertEquals(1111111L,customer1.getBirthday().getTime());
    }

    @Test
    @DisplayName("Customer id already exist")
    void registerFailed(){
        Customer customer = new Customer();
        customer.setId("juanito");
        customer.setName("juan");
        customer.setPassword("123456");
        customer.setAddress("santander2");
        customer.setPhone("222222");
        customer.setBirthday(new Date(1111111L));
        assertThrows(ServiceException.class,()->customerService.register(customer));
    }
}