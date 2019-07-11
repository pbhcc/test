package com.cy;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourceTest {
	@Autowired
	private DataSource datasource;

	@Test
	public void contextLoads() throws Exception{
		Assert.assertNotEquals(null, datasource);
		System.out.println(datasource.getConnection());
	}

}
