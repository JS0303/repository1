package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.purchase.vo.PurchaseVO;



public class PurchaseDAO {
	
	///Constructor
	public PurchaseDAO(){
	}
	///Method
	public void addPurchase(PurchaseVO purchaseVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "insert into TRANSACTION values (seq_product_prod_no.nextval,?,?,?,?,?,sysdate)";
				
		PreparedStatement stmt = con.prepareStatement(sql);
		
		con.close();
	}
	
	public PurchaseVO getPurchase(int tranNo) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql =
				
		PreparedStatement stmt = con.prepareStatement(sql);
		
		con.close();
		
		return null;
	}
	
	public PurchaseVO getPurchase2(int ProdNo) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql =
				
		PreparedStatement stmt = con.prepareStatement(sql);
		
		con.close();
		
		return null;
	}
	
	public HashMap<String,Object> getPurchaseList(SearchVO searchVO,String buyerId) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql =
				
		PreparedStatement stmt = con.prepareStatement(sql);
		
		con.close();
		
		return null;
	}
	
	public HashMap<String,Object> getSaleList(SearchVO searchVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql =
				
		PreparedStatement stmt = con.prepareStatement(sql);
		
		con.close();
		
		return null;
	}
	
	public void updatePurcahse(PurchaseVO purchaseVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql =
				
		PreparedStatement stmt = con.prepareStatement(sql);
		
		con.close();
	}
	
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql =
				
		PreparedStatement stmt = con.prepareStatement(sql);
		
		con.close();
	}
}
