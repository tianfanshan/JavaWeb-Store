<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>订单完成</title>
<link rel="stylesheet" type="text/css" href="css/public.css">
<style type="text/css">
a:link {
    font-size: 18px;
    color: #DB8400;
    text-decoration: none;
    font-weight: bolder;
}
a:visited {
    font-size: 18px;
    color: #DB8400;
    text-decoration: none;
    font-weight: bolder;
}
a:hover {
    font-size: 18px;
    color: #DB8400;
    text-decoration: underline;
    font-weight: bolder;
}
</style>
</head>

<body>
<div class="header">智捷网上电脑商城</div>
<hr width="100%" />
<div align="center" >
  <p class="text7"> 谢谢您的购物！ </p>
  <p class="text7"> 您的订单号是： ${orderId}</p>
  <p class="text7"> 您可以继续购物！ </p>
   <p class="text7">
       <a href="controller?action=main">返回主页面</a>
  </p>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
