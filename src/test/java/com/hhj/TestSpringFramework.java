package com.hhj;

import com.hhj.config.BeanDefination;
import com.hhj.factory.DefaultListableBeanFactory;
import com.hhj.po.Student;
import org.junit.Test;

import java.util.Map;


public class TestSpringFramework {

	@Test
	public void test() throws Exception {

		// 指定xml资源路径
		String location = "classpath:beans.xml";
		// Resource resource = new ClassPathResource(location);
		// 创建工厂
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory(location);
		Map<String, BeanDefination> beanDefinations = beanFactory.getBeanDefinations();
		// 从工厂中获取指定对象
		Student student = (Student) beanFactory.getBean("student");
		// 测试对象是否可用
		System.out.println(student);
	}

}
