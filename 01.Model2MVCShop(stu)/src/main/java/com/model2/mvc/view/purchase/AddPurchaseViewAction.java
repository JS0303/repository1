package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;


public class AddPurchaseViewAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PurchaseVO purchaseVO = new PurchaseVO();
		
		//purchaseVO.setBuyer(request.getParameter("buyer"));
		//purchaseVO.setDivyAddr(request.getParameter("divyAddr"));
		purchaseVO.setDivyDate(request.getParameter("divyDate"));
		purchaseVO.setDivyRequest(request.getParameter("divyRequest"));
		//purchaseVO.setOrderDate(request.getParameter("orderDate"));
		purchaseVO.setPaymentOption(request.getParameter("paymentOption"));
		//purchaseVO.setPurchaseProd(request.getParameter("purchaseProd"));
		purchaseVO.setReceiverName(request.getParameter("receiverName"));
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone"));
		purchaseVO.setTranCode(request.getParameter("tranCode"));
		purchaseVO.setTranNo(Integer.parseInt(request.getParameter("tranNo")));
		
		System.out.println(":: AddPurchaseViewAction¿« purchaseVO ::"+purchaseVO);
		
		System.out.println(":: AddPurchaseViewAction¿« tranNo ::"+request.getParameter("tranNo"));
		
		int tranNo=Integer.parseInt(request.getParameter("tranNo"));
				
		PurchaseService service = new PurchaseServiceImpl();
		//PurchaseVO purchaseVO=service.getPurchase2(tranNo);
		
		request.setAttribute("purchaseVO", purchaseVO);
		
		return "forward:/purchase/updatePurchase.jsp";
	}

	

}
