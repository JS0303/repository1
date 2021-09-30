package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.Map;


import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.product.ProductService;



public class ProductServiceImpl implements ProductService{
	
	private ProductDAO productDAO;
	
	public ProductServiceImpl() {
		productDAO=new ProductDAO();
	}

	public void insertProduct(ProductVO productVO) throws Exception {
		productDAO.insertProduct(productVO);
	}

	public ProductVO getProduct(int prodNo) throws Exception {
		return productDAO.findProduct(prodNo);
	}

	public HashMap<String,Object> getProductList(SearchVO searchVO) throws Exception {
		return productDAO.getProductList(searchVO);
	}

	public void updateProduct(ProductVO productVO) throws Exception {
		return productDAO.updateProduct(productVO);
	}

	public boolean checkDuplication(int prodNo) throws Exception {
		boolean result=true;
		ProductVO productVO=productDAO.findProduct(prodNo);
		if(productVO != null) {
			result=false;
		}
		return result;
	}
		
}