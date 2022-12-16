<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>商品详细</title>
<link rel="stylesheet" type="text/css" href="css/public.css">
<style type="text/css">
.title {
	font-size: 20px;
	color: #FF6600;
	font-style: italic;
}
</style>
</head>
<body>
<jsp:include page="goods_header.jsp" flush="true" >
  <jsp:param name="image" value="info.jpg"/>
</jsp:include>

<hr width="100%" />
<div class="text3" align="center">${goods.description}</div>
<table width="100%" border="0" align="center">
  <tr>
    <td width="40%" align="right"><div><img src="goods_images/${goods.image}" width="360px" height="360px" /></div>
      <br></td>
    <td><div align="center" class="text4">一 口 价：<span class="title">￥${goods.price}元</span></div>
      <br>
      <table width="80%" height="200" border="0">
        <tbody>
          <tr>
            <td  width="25%" class="text5" >电脑品牌：</td>
            <td width="25%" class="text6" > ${goods.brand} </td>
            <td width="25%" class="text5" >CPU品牌：</td>
            <td width="25%" class="text6" >${goods.cpuBrand}</td>
          </tr>

          <tr>
            <td class="text5" >内存容量：</td>
            <td class="text6" >${goods.memoryCapacity}</td>
            <td class="text5" >CPU型号：</td>
            <td class="text6" >${goods.cardModel}</td>
          </tr>
          <tr>
            <td class="text5" >硬盘容量：</td>
            <td class="text6" > ${goods.hdCapacity}&nbsp;</td>
            <td class="text5" >显卡类型：</td>
            <td class="text6" >&nbsp ${goods.cardModel} </td>
          </tr>
          <tr>
            <td class="text5" >显示器尺寸：</td>
            <td class="text6" > ${goods.displaySize}&nbsp;</td>
            <td class="text5" >&nbsp;</td>
            <td class="text6" >&nbsp;</td>
          </tr>
        </tbody>
      </table>
      <br>
      <br>
      <div><a href="controller?action=add&pageName=detail&productId=${goods.id}&productName=${goods.name}&productPrice=${goods.price}"><img src="images/button.jpg" ></a></div></td>
  </tr>
</table>
<%@include file="footer.jsp" %>
</body>
</html>
