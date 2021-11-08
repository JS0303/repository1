package com.model2.mvc.view.purchase;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class AddPurchaseAction extends Action{
	
	///Constructor
	public AddPurchaseAction() {
		
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		UserVO userVO = new UserVO();
		userVO = (UserVO) session.getAttribute("user");
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		ProductVO productVO = new ProductVO();
		productVO.setProdNo(prodNo);
		productVO.setProdTranCode("001");
		
		
		System.out.println(":: ::AddPurchaseAtion prodNo"+prodNo);
		PurchaseVO purchaseVO = new PurchaseVO();
		System.out.println("::AddPurchaseAtion userVO ::"+userVO);
		System.out.println("::AddPurchaseAtion productVO ::"+productVO);
		
		purchaseVO.setBuyer(userVO);
		purchaseVO.setDlvyAddr(request.getParameter("receiverAddr"));
		purchaseVO.setDlvyDate(request.getParameter("receiverDate"));
		purchaseVO.setDlvyRequest(request.getParameter("receiverRequest"));
		purchaseVO.setPaymentOption(request.getParameter("paymentOption"));
		purchaseVO.setPurchaseProd(productVO);
		purchaseVO.setReceiverName(request.getParameter("receiverName"));
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone"));
		purchaseVO.setTranCode("001");
		//purchaseVO.setTranNo(Integer.parseInt(request.getParameter("tranNo")));
		
		System.out.println(":: AddPurchaseAction¿« purchaseVO ::"+purchaseVO);
		
		//System.out.println(":: AddPurchaseAction¿« tranNo ::"+request.getParameter("tranNo"));
		
		PurchaseService purchaseService = new PurchaseServiceImpl();
		purchaseService.addPurchase(purchaseVO);
		
		request.setAttribute("purchaseVO", purchaseVO);
		
		return "forward:/purchase/addPurchase.jsp";
	}

}
