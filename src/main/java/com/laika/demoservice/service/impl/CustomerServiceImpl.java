package com.laika.demoservice.service.impl;

import com.laika.demoservice.dao.CustomerMapper;
import com.laika.demoservice.model.Customer;
import com.laika.demoservice.service.ICustomerService;
import com.laika.demoservice.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* @author: 会跳舞的机器人
* @date: 2017/12/21 13:58:15
* @description: Customer服务实现
*/
@Service
@Transactional
public class CustomerServiceImpl extends AbstractService<Customer> implements ICustomerService {
    @Resource
    private CustomerMapper tCustomerMapper;

}
