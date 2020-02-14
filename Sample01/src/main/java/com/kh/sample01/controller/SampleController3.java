package com.kh.sample01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.sample01.domain.ProductVo;


@Controller
public class SampleController3 {

	
	@RequestMapping("/doE")
	public String doE(Model model) {
		
		// -> Model : 스프링이 제공하는 바구니
		
		ProductVo product = new ProductVo();
		product.setName("Sample Product");
		product.setPrice(10000);
		
		// request.setAttribute("product", product);
		model.addAttribute(product);
		// -> Key 생략시 : 클래스 명의 첫글자를 소문자로 바꾼 이름 사용.(자동으로~)
		// -> ("productVo", product)
		// view(JSP)에서 사용되는 이름은 productVo
		
		System.out.println("doE 실행됨");
		
		return "productDetail";
	}
	
	
	@RequestMapping("/doF")
	public String doF(Model model) {
		
		ProductVo vo = new ProductVo();
		vo.setName("신제품");
		vo.setPrice(20000);
		
		model.addAttribute("vo", vo);
		// -> views(JSP)에서 사용되는 이름은 vo
		
		
		System.out.println("doF 실행됨");
		
		return "productDetail2";
	}
	
}
