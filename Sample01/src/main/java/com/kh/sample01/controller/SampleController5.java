package com.kh.sample01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.sample01.domain.ProductVo;

@Controller
public class SampleController5 {
	
	@RequestMapping("/doJSON")
	public @ResponseBody ProductVo doJSON() {
		
		// {"name" : "Sample Product", "price" : 10000}
		ProductVo vo = new ProductVo();
		vo.setName("Sample Product");
		vo.setPrice(10000);
		
		return vo;
	}
	
}
