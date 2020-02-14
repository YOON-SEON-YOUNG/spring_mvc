package com.kh.sample01.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kh.sample01.domain.ProductVo;

@RestController
public class SampleController6 {
	
	@RequestMapping(value = "/getProduct", method = RequestMethod.GET)
	public ProductVo getProduct() throws Exception {
		
		ProductVo vo = new ProductVo();
		vo.setName("새제품");
		vo.setPrice(10000);
		
		return vo;
	}
	
	
	@RequestMapping(value = "/getResult", method = RequestMethod.GET)
	public String getResult() throws Exception	{
		
		return "seuccess";	// success.jsp로 forward 되는 것이 아님.
							// success 라는 스트링 데이터를 리턴
	}
	
	@RequestMapping(value = "/getProductList", method = RequestMethod.GET)
	public List<ProductVo> getProductList() throws Exception {
		
		List<ProductVo> list = new ArrayList<>();
		
		for (int i = 1; i <= 10; i++) {
			ProductVo vo = new ProductVo();
			vo.setName("제목" + i);
			vo.setPrice(10000 * i);
			list.add(vo);
		}
		return list;	// 자바스크립트의 배열[ ] 형태로 리턴
	}
	
	@RequestMapping(value = "/getProductMap", method = RequestMethod.GET)
	public Map<Integer, ProductVo> getProductMap() throws Exception	{
		
		Map<Integer, ProductVo> map = new HashMap<>();
		
		for (int i = 1; i <= 10; i++) {
			ProductVo vo = new ProductVo();
			vo.setName("제목" + i);
			vo.setPrice(10000 * i);
			map.put(i, vo);
		}
		return map;	// 자바스크립트의 객체{ } 형태로 리턴
	}
	
	
	
	
	
	
}
