package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model2.mvc.common.util.DBUtil;

import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class PurchaseDAO {

	/// Constructor
	public PurchaseDAO() {
	}

	/// Method
	public void insertPurchase(PurchaseVO purchaseVO) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "insert into TRANSACTION values (seq_transaction_tran_no.nextval,?,?,?,?,?,?,?,?,sysdate,?)";

		PreparedStatement stmt = con.prepareStatement(sql);
		System.out.println(":: PurchaseDAO의 준비된 insertPurchase sql ::" + sql);
		stmt.setObject(1, purchaseVO.getPurchaseProd().getProdNo());
		stmt.setObject(2, purchaseVO.getBuyer().getUserId());
		stmt.setString(3, purchaseVO.getPaymentOption());
		stmt.setString(4, purchaseVO.getReceiverName());
		stmt.setString(5, purchaseVO.getReceiverPhone());
		stmt.setString(6, purchaseVO.getDivyAddr());
		stmt.setString(7, purchaseVO.getDivyRequest());
		stmt.setString(8, purchaseVO.getTranCode());
		stmt.setString(9, null);

		System.out.println(purchaseVO.getDivyDate() + " :: PurchaseDAO의 insertPurchase에서 찍은 DivyDate");

		if (!"".equals(purchaseVO.getDivyDate())) {
			stmt.setDate(9, Date.valueOf(purchaseVO.getDivyDate()));
		}

		if (stmt.executeUpdate() == 1) {
			System.out.println("구매등록완료");
		} else {
			System.out.println("구매등록실패");
		}

		stmt.close();

		con.close();
	}

	public PurchaseVO findPurchase(int tranNo) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "select * from PRODUCT where tran_no=?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, tranNo);

		ResultSet rs = stmt.executeQuery();

		PurchaseVO purchaseVO = null;

		while (rs.next()) {
			purchaseVO.setTranNo(rs.getInt("TRAN_NO"));
			purchaseVO.setBuyer((UserVO) rs.getObject("BUYER_ID"));
			purchaseVO.setPaymentOption(rs.getString("PAYMENT_OPTION"));
			purchaseVO.setReceiverName(rs.getString("RECEIVER_NAME"));
			purchaseVO.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
			purchaseVO.setDivyAddr(rs.getString("DEMAILADDR"));
			purchaseVO.setTranCode(rs.getString("TRAN_STATUS_CODE"));
			purchaseVO.setOrderDate(rs.getDate("ORDER_DATA"));
			purchaseVO.setDivyDate(rs.getString("DLVY_DATE"));
		}

		con.close();

		return purchaseVO;
	}

//	public HashMap<String,Object> getPurchaseList(SearchVO searchVO,String buyerId) throws Exception {
//		
//		Connection con = DBUtil.getConnection();
//		
//		String sql = "select * from PURCHASE "+
//					"where "+
//					" order by USER_ID";
//				
//		PreparedStatement stmt = con.prepareStatement(sql);
//		
//		con.close();
//		
//		return null;
//	}
//	
//	public HashMap<String,Object> getSaleList(SearchVO searchVO) throws Exception {
//		
//		Connection con = DBUtil.getConnection();
//		
//		String sql =
//				
//		PreparedStatement stmt = con.prepareStatement(sql);
//		
//		con.close();
//		
//		return null;
//	}
//	
//	public void updatePurcahse(PurchaseVO purchaseVO) throws Exception {
//		
//		Connection con = DBUtil.getConnection();
//		
//		String sql =
//				
//		PreparedStatement stmt = con.prepareStatement(sql);
//		
//		con.close();
//	}
//	
//	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
//		
//		Connection con = DBUtil.getConnection();
//		
//		String sql =
//				
//		PreparedStatement stmt = con.prepareStatement(sql);
//		
//		con.close();
//	}
}
