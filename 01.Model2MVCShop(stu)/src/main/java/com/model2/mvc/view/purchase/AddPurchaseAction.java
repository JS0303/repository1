package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class AddPurchaseAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PurchaseVO purchaseVO = new PurchaseVO();
		//purchaseVO.setBuyer(request.getParameter("BUYER_ID"));
		purchaseVO.setDivyAddr(request.getParameter("DEMAILADDR"));
		purchaseVO.setDivyDate(request.getParameter("DLVY_DATE"));
		purchaseVO.setDivyRequest(request.getParameter("DLVY_REQUEST"));
		//purchaseVO.setOrderDate(request.getParameter("ORDER_DATA"));
		purchaseVO.setPaymentOption(request.getParameter("PAYMENT_OPTION"));
		//purchaseVO.setPurchaseProd(request.getParameter("PROD_NO"));
		purchaseVO.setReceiverName(request.getParameter("RECEIVER_NAME"));
		purchaseVO.setReceiverPhone(request.getParameter("RECEIVER_PHONE"));
		purchaseVO.setTranCode(request.getParameter("TRAN_STATUS_CODE"));
		purchaseVO.setTranNo(Integer.parseInt(request.getParameter("TRAN_NO")));
		
		System.out.println(purchaseVO);
		
		PurchaseService service = new PurchaseServiceImpl();
		service.addPurchase(purchaseVO);
		
		request.setAttribute("purchaseVO", purchaseVO);
		
		return "forward:/purchase/addPurchase.jsp";
	}

}
