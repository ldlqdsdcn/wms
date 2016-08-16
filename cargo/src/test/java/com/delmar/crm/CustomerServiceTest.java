package com.delmar.crm;

import com.delmar.crm.model.Customer;
import com.delmar.crm.service.CustomerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liua
 * Date: 15-9-15
 * Time: 下午1:02
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;
    @Test
    public void testSearchCustomer()
    {
        List<Customer> customerList=customerService.selectByExample(null);
        for(Customer c:customerList)
        {
            System.out.println(c.getName());
        }
        Assert.assertNotNull(customerList);
    }
}
