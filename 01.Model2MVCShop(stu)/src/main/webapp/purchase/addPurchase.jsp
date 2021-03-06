<%@ page contentType="text/html; charset=EUC-KR"%>

<%@ page import="java.util.*"  %>
<%@ page import="com.model2.mvc.service.purchase.vo.PurchaseVO"%>
<%@ page import="com.model2.mvc.common.*" %>
<%@ page import="com.model2.mvc.service.purchase.vo.PurchaseVO" %>
<%@ page import="com.model2.mvc.service.user.vo.UserVO" %>
<%@ page import="com.model2.mvc.service.product.vo.ProductVO" %>
<%@ page import="com.model2.mvc.service.purchase.dao.*" %>

<%
	UserVO userVO = (UserVO)request.getAttribute("userVO");
		System.out.println(getClass().getName()+"addPurchase.jsp에서 불러온 userVO : "+userVO);
		
	ProductVO productVO = (ProductVO)request.getAttribute("productVO");
		System.out.println(getClass().getName()+"addPurchase.jsp에서 불러온 productVO : "+productVO);
		
	PurchaseVO purchaseVO=(PurchaseVO)request.getAttribute("purchaseVO");
		System.out.println(getClass().getName()+"addPurchase.jsp에서 불러온 purchaseVO : "+purchaseVO);
		
	String payment = "신용구매";
%>




<html>
<head>
<title>Insert title here</title>
</head>

<body>

<form name="updatePurchase" action="/updatePurchaseView.do?tranNo=<%=purchaseVO.getTranNo() %>" method="post">

다음과 같이 구매가 되었습니다.

<table border=1>
	<tr>
		<td>물품번호</td>
		<td><%=purchaseVO.getPurchaseProd().getProdNo() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자아이디</td>
		<td><%=purchaseVO.getBuyer().getUserId() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매방법</td>
		<td>
		
			<%=purchaseVO.getPaymentOption() %>
		
		</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자이름</td>
		<td><%=purchaseVO.getReceiverName() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자연락처</td>
		<td><%=purchaseVO.getReceiverPhone() %></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자주소</td>
		<td><%=purchaseVO.getDlvyAddr() %></td>
		<td></td>
	</tr>
		<tr>
		<td>구매요청사항</td>
		<td><%=purchaseVO.getDlvyRequest() %></td>
		<td></td>
	</tr>
	<tr>
		<td>배송희망일자</td>
		<td><%=purchaseVO.getDlvyDate() %></td>
		<td></td>
	</tr>
	<tr>
		<td>주문상태코드</td>
		<td><%=purchaseVO.getTranCode() %></td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>