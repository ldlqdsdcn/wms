package com.delmar.crm;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.delmar.crm.model.Customer;
import com.delmar.crm.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MyBatisFieldsTest {
	
	@Autowired
	private CustomerService customerService;
	
	
	@Test
	public void test() {
		Customer obj=customerService.selectFieldsByPrimaryKey("id,name",15);
		
		System.out.println(obj.getName());
		System.out.println(obj.getCreated().getDate());
		fail("Not yet implemented");
	}
	
	public void main(String[] args) {
		
		test();
		
		
	}

}
