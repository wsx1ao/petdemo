<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/9/29
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
    <a href="Category?categoryId=${sessionScope.product.categoryId}">Return to ${sessionScope.product.categoryId}</a>
</div>

<div id="Catalog">

    <h2>${sessionScope.product.name}</h2>

    <table>
        <tr>
            <th>Item ID</th>
            <th>Product ID</th>
            <th>Description</th>
            <th>List Price</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach var="item" items="${sessionScope.itemList}">
            <tr>
                <td>
                    <a href="Item?itemId=${item.itemId}">${item.itemId}</a>
                </td>
                <td>
                    ${item.product.productId}</td>
                <td>
                    ${item.attribute1} ${item.attribute2} ${item.attribute3}
                    ${item.attribute4} ${item.attribute5} ${sessionScope.product.name}
                </td>
                <td>
                    <fmt:formatNumber value="${item.listPrice}" pattern="$#,##0.00" />
                </td>
                <td>
                    <a class="button" href="AddItemToCart?workingItemId=${item.itemId}">Add to Cart</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td>
            </td>
        </tr>
    </table>

</div>

<%@ include file="../common/IncludeBottom.jsp"%>






