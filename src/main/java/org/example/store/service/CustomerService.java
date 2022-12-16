package org.example.store.service;

import org.example.store.domain.Customer;

public interface CustomerService {

    /**
     * 处理客户登录
     * @param customer
     * @return 登录成功返回true，登陆失败返回false
     */
    boolean login(Customer customer) throws ServiceException;

    /**
     * 处理客户注册
     * @param customer
     * @throws ServiceException 注册失败抛出异常
     */
    void register(Customer customer) throws ServiceException;
}
