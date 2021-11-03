package com.model2.mvc.service.product.vo;

import java.sql.Date;


public class ProductVO {
	
	private String fileName;
	private String manuDate;
	private int price;
	private String prodDetail;
	private String prodName;
	private int prodNo;
	private Date regDate;
	private String prodTranCode;
	
	
	public ProductVO(){
	}
	
	
	
	public String getFileName() {
		return fileName;
	}



	public String getManuDate() {
		return manuDate;
	}



	public int getPrice() {
		return price;
	}



	public String getProdDetail() {
		return prodDetail;
	}



	public String getProdName() {
		return prodName;
	}



	public int getProdNo() {
		return prodNo;
	}



	public Date getRegDate() {
		return regDate;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public void setManuDate(String manuDate) {
		this.manuDate = manuDate;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	public void setProdDetail(String prodDetail) {
		this.prodDetail = prodDetail;
	}



	public void setProdName(String prodName) {
		this.prodName = prodName;
	}



	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}



	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	
	public String getProdTranCode() {
		return prodTranCode;
	}
	
	
	
	public void setProdTranCode(String prodTranCode) {
		this.prodTranCode = prodTranCode;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ProductVO : [fileName] "+fileName+" [manuDate] "+manuDate+" [price] "+price+" [prodDetail] "+ prodDetail
			+" [prodName] "+prodName+" [prodNo] "+prodNo+" [regDate] "+regDate;
	}



}
