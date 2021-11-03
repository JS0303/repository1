package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class PurchaseDAO {

	/// Constructor
	public PurchaseDAO() {
	}

	/// Method
	public PurchaseVO findPurchase(int no) throws Exception {

		Connection con = DBUtil.getConnection();

		PreparedStatement stmt = null;

		StackTraceElement[] stacks = new Throwable().getStackTrace();

		StackTraceElement beforeStack = stacks[1];
		System.out.println("실행된 메소드 :: " + beforeStack.getMethodName());

		String sql = "select * from TRANSACTION where ";

		if (beforeStack.getMethodName().contains("2")) {
			sql += "prod_no = '" + no + "'";
		} else {
			sql += "tran_no = '" + no + "'";
		}

		System.out.println("findPurchase 메소드의 준비된 sql :: " + sql);
		stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();
		System.out.println("::findPurchase 쿼리전송");

		PurchaseVO purchaseVO = new PurchaseVO();
		ProductVO productVO = new ProductVO();
		UserVO userVO = new UserVO();

		if (rs.next()) {
			purchaseVO.setTranNo(rs.getInt(1));
			productVO.setProdNo(rs.getInt(2));
			userVO.setUserId(rs.getString(3));
			purchaseVO.setPaymentOption(rs.getString(4));
			purchaseVO.setReceiverName(rs.getString(5));
			purchaseVO.setReceiverPhone(rs.getString(6));
			purchaseVO.setDlvyAddr(rs.getString(7));
			purchaseVO.setDlvyRequest(rs.getString(8));
			purchaseVO.setTranCode(rs.getString(9));
			purchaseVO.setOrderDate(rs.getDate(10));

			if (rs.getString(11) != null) {
				purchaseVO.setDlvyDate(rs.getString(11));
			} else {
				purchaseVO.setDlvyDate("");
			}

			purchaseVO.setPurchaseProd(productVO);
			purchaseVO.setBuyer(userVO);
		}

		stmt.close();
		rs.close();
		con.close();

		return purchaseVO;
	}

	public HashMap<String, Object> getPurchaseList(SearchVO searchVO, String buyerId) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "select * from TRANSACTION " + "where BUYER_ID = ?";

		HashMap<String, Object> map = new HashMap<String, Object>();

		PreparedStatement stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);

		stmt.setString(1, buyerId);
		System.out.println("buyerId :: "+buyerId);

		ResultSet rs = stmt.executeQuery();
		rs.last();

		// 총 row 수를 얻는다
		int total = rs.getRow();
		System.out.println("getPurchaseList의 총 row수 :: " + total);

		map.put("count", new Integer(total));
		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit() + 1);
		System.out.println("getPurchseList의 searchVO.getPage()::" + searchVO.getPage());
		System.out.println("getPurchseList의 searchVO.getPageUnit()::" + searchVO.getPageUnit());

		List<PurchaseVO> list = new ArrayList<PurchaseVO>();
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				PurchaseVO purchaseVO = new PurchaseVO();
				ProductVO productVO = new ProductVO();
				UserVO userVO = new UserVO();

				purchaseVO.setTranNo(rs.getInt(1));
				productVO.setProdNo(rs.getInt(2));
				userVO.setUserId(rs.getString(3));
				purchaseVO.setPaymentOption(rs.getString(4));
				purchaseVO.setReceiverName(rs.getString(5));
				purchaseVO.setReceiverPhone(rs.getString(6));
				purchaseVO.setDlvyAddr(rs.getString(7));
				purchaseVO.setDlvyRequest(rs.getString(8));
				purchaseVO.setTranCode(rs.getString(9));
				purchaseVO.setOrderDate(rs.getDate(10));
				purchaseVO.setDlvyDate(rs.getString(11));

				purchaseVO.setPurchaseProd(productVO);
				purchaseVO.setBuyer(userVO);

				list.add(purchaseVO);
				if (!rs.next()) {
					break;
				}
			}
		}

		map.put("list", list);

		rs.close();
		stmt.close();
		con.close();

		return map;
	}

	public HashMap<String, Object> getSaleList(SearchVO searchVO) throws Exception {

		return null;
	}

	public void insertPurchase(PurchaseVO purchaseVO) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "insert into TRANSACTION values (seq_transaction_tran_no.nextval,?,?,?,?,?,?,?,?,sysdate,?)";

		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setInt(1, purchaseVO.getPurchaseProd().getProdNo());
		stmt.setObject(2, purchaseVO.getBuyer().getUserId());
		stmt.setString(3, purchaseVO.getPaymentOption());
		stmt.setString(4, purchaseVO.getReceiverName());
		stmt.setString(5, purchaseVO.getReceiverPhone());
		stmt.setString(6, purchaseVO.getDlvyAddr());
		stmt.setString(7, purchaseVO.getDlvyRequest());
		stmt.setString(8, purchaseVO.getTranCode());
		stmt.setString(9, null);
		System.out.println(":: PurchaseDAO의 준비된 insertPurchase sql ::" + sql);

		System.out.println(purchaseVO.getPurchaseProd() + " :: PurchaseDAO의 insertPurchase에서 찍은 PurchaseProd");

		if (!"".equals(purchaseVO.getDlvyDate())) {
			stmt.setDate(9, Date.valueOf(purchaseVO.getDlvyDate()));
		}

		if (stmt.executeUpdate() == 1) {
			System.out.println("구매등록완료");
		} else {
			System.out.println("구매등록실패");
		}

		stmt.close();

		con.close();
	}

	public void updatePurcahse(PurchaseVO purchaseVO) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = "update TRANSACTION " + "set PAYMENT_OPTION=?, RECEIVER_NAME=?, RECEIVER_PHONE=?, "
				+ "DEMAILADDR=?, DLVY_REQUEST=?, DLVY_DATE=? " + "where TRAN_NO=?";

		PreparedStatement stmt = con.prepareStatement(sql);
		System.out.println("updatePurchase의 sql :: "+sql);
		
		stmt.setString(1, purchaseVO.getPaymentOption());
		stmt.setString(2, purchaseVO.getReceiverName());
		stmt.setString(3, purchaseVO.getReceiverPhone());
		stmt.setString(4, purchaseVO.getDlvyAddr());
		stmt.setString(5, purchaseVO.getDlvyRequest());
		stmt.setString(6, purchaseVO.getDlvyDate());
		stmt.setInt(7, purchaseVO.getTranNo());
		
		
		if(stmt.executeUpdate()==1) {
			System.out.println(":: Purchase Update 성공");
		}else {
			System.out.println(":: Purchase Update 실패");
		}
		
		stmt.close();
		con.close();
	}

	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		System.out.println(purchaseVO.getTranCode()+" "+purchaseVO.getPurchaseProd().getProdNo());
		
		String sql = "update TRANSACTION " + "set TRAN_STATUS_CODE=? " + "where PROD_NO=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, purchaseVO.getTranCode());
		stmt.setInt(2, purchaseVO.getPurchaseProd().getProdNo());
		
		if(stmt.executeUpdate()==1) {
			System.out.println(":: Update TranCode 성공");
		}else {
			System.out.println(":: Update TranCode 실패");
		}
		
		con.close();
	}
}
