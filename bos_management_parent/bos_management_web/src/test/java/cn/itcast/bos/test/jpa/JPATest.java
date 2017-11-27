package cn.itcast.bos.test.jpa;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.bos.dao.StandardRepository;
import cn.itcast.bos.domain.base.Standard;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JPATest {

	@Resource
	private StandardRepository standardRepository;
	
	/**
	 * 查询所有取派标准
	 */
	@Test
	public void testDemo(){
		List<Standard> list = standardRepository.findAll();
		for(Standard s : list){
			System.out.println(s.getName());
		}
	}
	
	/**
	 * 使用方法名解析查询指定名称的取派标准
	 */
	@Test
	public void testDemo2(){
		List<Standard> list = standardRepository.findByName("10~20公斤");//相当于from Standard where name=?
		for(Standard s : list){
			System.out.println(s.getName());
		}
	}
	
	/**
	 * 使用方法名解析模糊查询指定名称的取派标准
	 */
	@Test
	public void testDemo3(){
		List<Standard> list = standardRepository.findByNameLike("%20%");//相当于from Standard where name=?
		for(Standard s : list){
			System.out.println(s.getName());
		}
	}
	
	/**
	 * 使用query注解模糊查询指定名称的取派标准
	 */
	@Test
	public void testDemo4(){
		List<Standard> list = standardRepository.findStandardByLikeName("%20%");//相当于from Standard where name=?
		for(Standard s : list){
			System.out.println(s.getName());
		}
	}
}
