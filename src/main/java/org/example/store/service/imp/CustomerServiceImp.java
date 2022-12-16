package org.example.store.service.imp;

import org.example.store.dao.CustomerDAO;
import org.example.store.dao.imp.CustomerDAOImp;
import org.example.store.domain.Customer;
import org.example.store.service.CustomerService;
import org.example.store.service.ServiceException;

public class CustomerServiceImp implements CustomerService {

    CustomerDAO customerDAO = new CustomerDAOImp();

    @Override
    public boolean login(Customer customer) throws ServiceException{
        Customer dbCustomer = customerDAO.findByPk(customer.getId());
        if (dbCustomer.getId() == null){
            throw new ServiceException("This customer dose not exist");
        } else if (dbCustomer.getPassword().equals(customer.getPassword())) {
            customer.setName(dbCustomer.getName());
            customer.setAddress(dbCustomer.getAddress());
            customer.setPhone(dbCustomer.getPhone());
            customer.setBirthday(dbCustomer.getBirthday());
            return true;
        }
        return false;
    }

    @Override
    public void register(Customer customer) throws ServiceException {
        Customer dbCustomer = customerDAO.findByPk(customer.getId());
        if (dbCustomer.getId() != null){
            throw new ServiceException("This customer with id: " + customer.getId() + " is already exist");
        }
        customerDAO.create(customer);
    }
}
