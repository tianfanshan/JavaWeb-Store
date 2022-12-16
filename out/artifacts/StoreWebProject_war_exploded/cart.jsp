<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>购物车</title>
    <link rel="stylesheet" type="text/css" href="css/public.css">
    <style type="text/css">
        table {
            border-collapse: collapse;
        }

        .threeboder {
            border: 1px solid #5B96D0;
        }

        .trow {
            border-right: 1px solid #5B96D0;
            border-bottom: 1px solid #5A96D6;
        }

        .theader {
            background-color: #A5D3FF;
            font-size: 14px;
            border-right: 1px solid #5B96D0;
            border-bottom: 1px solid #5A96D6;
        }
    </style>

    <script>
        function calc(rowid, quantityInput) {

            let quantity = quantityInput.value
            if (isNaN(quantity)) {
                alert("不是有效的数值！");
                quantityInput.value = 0;
                quantity = quantityInput.value
                quantityInput.focus();
                // return;
            }
            // 单价id
            let price_id = 'price_' + rowid;
            // 单价
            let price = parseFloat(document.getElementById(price_id).innerText);

            // 小计id
            let subtotal_id = 'subtotal_' + rowid;
            // 小计(更新之前)
            let subtotal1 = parseFloat(document.getElementById(subtotal_id).innerText);
            //四舍五入并保留两位小数
            subtotal1 = subtotal1.toFixed(2);
            document.getElementById(subtotal_id).innerText = quantity * price;
            // 小计(更新之后)
            let subtotal2 = parseFloat(document.getElementById(subtotal_id).innerText);
            // 合计
            let total = parseFloat(document.getElementById('total').innerText);
            // 计算合计
            total = total - subtotal1 + subtotal2;
            //四舍五入并保留两位小数
            total = total.toFixed(2);
            //更新合计
            document.getElementById('total').innerText = total;

        }
    </script>

</head>

<body>
<jsp:include page="goods_header.jsp" flush="true">
    <jsp:param name="image" value="mycar.jpg"/>
</jsp:include>
<hr width="100%"/>
<div class="text3" align="center">您选好的商品</div>
<br>
<form action="controller" method="post">
    <table width="100%" border="0" align="center" class="threeboder">
        <tr bgcolor="#A5D3FF">
            <td height="50" align="center" class="theader">商品名称</td>
            <td width="8%" align="center" class="theader">数量</td>
            <td width="15%" align="center" class="theader">单价</td>
            <td width="15%" align="center" class="theader">小计</td>
        </tr>
        <c:forEach var="row" items="${cart}">
            <tr>
                <td height="50" align="left" class="trow">&nbsp;&nbsp;${row.goodsName}</td>
                <td align="center" class="trow">
                    <input name="quantity_${row.goodsId}" type="text" value="${row.quantity}"
                           onblur="calc(${row.goodsId}, this)"/>
                </td>
                <td align="center" class="trow">&yen;<span id="price_${row.goodsId}">${row.goodsPrice}</span></td>
                <td align="center" class="trow">&yen;<span
                        id="subtotal_${row.goodsId}">${row.goodsPrice * row.quantity}</span>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td height="50" colspan="5" align="right">合计：&yen;<span id="total">${total}</span>&nbsp;&nbsp;</td>
        </tr>
    </table>
    <br>

    <div align="center">
        <c:if test="${not empty cart}">
            <input type="image" src="images/submit_order.jpg" border="0">
        </c:if>
    </div>
    <input type="hidden" name="action" value="sub_ord">
</form>
<%@include file="footer.jsp" %>

</body>
</html>
