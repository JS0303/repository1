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

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


		HttpSession session = request.getSession();
		UserVO userVO = (UserVO)session.getAttribute("user");
		ProductVO productVO=(ProductVO)session.getAttribute("productVO");
		
		PurchaseVO purchaseVO = new PurchaseVO();
		
		purchaseVO.setBuyer(userVO);
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr"));
		purchaseVO.setDivyDate(request.getParameter("receiverDate"));
		purchaseVO.setDivyRequest(request.getParameter("receiverRequest"));
		purchaseVO.setPaymentOption(request.getParameter("paymentOption"));
		purchaseVO.setPurchaseProd(productVO);
		purchaseVO.setReceiverName(request.getParameter("receiverName"));
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone"));
		purchaseVO.setTranCode("001");
		purchaseVO.setTranNo(Integer.parseInt(request.getParameter("prodNo")));
		
		System.out.println(":: AddPurchaseViewAction¿« purchaseVO ::"+purchaseVO);
		
		System.out.println(":: AddPurchaseViewAction¿« tranNo ::"+request.getParameter("tranNo"));
		
		PurchaseService purchaseService = new PurchaseServiceImpl();
		purchaseService.addPurchase(purchaseVO);
		
		request.setAttribute("purchaseVO", purchaseVO);
		
		return "forward:/purchase/addPurchase.jsp";
	}

}
