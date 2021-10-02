package com.model2.mvc.view.product;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

public class GetProductAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		
		int prodNo=Integer.parseInt(request.getParameter("prodNo"));
		
		String history = "";
		
		Cookie[] cookies = request.getCookies();
				
		if(cookies != null) {
			for(int i=0; i<cookies.length; i++) {
				Cookie h = cookies[i];
				//System.out.println("GetProductAction의 값은 : "+cookies[i].getValue());
				System.out.println("GetProductAction의 값은 : "+h.getName());
				if(h.getName().equals("history")){
					System.out.println(":: GetProductAction 쿠키 :: 실행");
					history=h.getValue();
					System.out.println(history);

				}
			}
		}
		history += request.getParameter("prodNo")+",";
		
		
		Cookie cookie = new Cookie("history",history);
		
		response.addCookie(cookie);
		System.out.println("GetProductAction의 cookie.."+cookie);
		
	
		
		ProductService service=new ProductServiceImpl();
		ProductVO productVO=service.getProduct(prodNo);
		
		request.setAttribute("productVO", productVO);
		
		System.out.println(":: GetProductAction.java에서 불러온 menu :: "+request.getParameter("menu"));
		
		if(request.getParameter("menu")==null) {
			return "forward:/product/getProduct.jsp";
			}
		
			if(request.getParameter("menu").equals("search")) {
				return "forward:/product/getProduct.jsp";
			
			}else {
				return "forward:/product/updateProduct.jsp";
			}
		}
	}