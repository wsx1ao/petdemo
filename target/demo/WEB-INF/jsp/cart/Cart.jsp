<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/9/30
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>
<div id="BackLink">
    <a href="Main">Return to Main Menu</a>
</div>
<div id="Catalog">
    <div id="Cart">
        <h2>Shopping Cart</h2>
            <form action="UpdateCart" method="post">
                <table>
                    <tr>
                        <th><b>Item ID</b></th>
                        <th><b>Product ID</b></th>
                        <th><b>Description</b></th>
                        <th><b>In Stock?</b></th>
                        <th><b>Quantity</b></th>
                        <th><b>List Price</b></th>
                        <th><b>Total Cost</b></th>
                        <th>&nbsp;</th>
                    </tr>
                    <c:if test="${sessionScope.cart.numberOfItems == 0}">
                        <tr>
                            <td colspan="8"><b>Your cart is empty.</b></td>
                        </tr>
                    </c:if>
                    <c:forEach var="cartItem" items="${sessionScope.cart.cartItems}">
                        <tr>
                            <td>
                                <a href="Item?itemId=${cartItem.item.itemId}">${cartItem.item.itemId}</a>
                            </td>
                            <td>${cartItem.item.product.productId}</td>
                            <td>
                                ${cartItem.item.attribute1} ${cartItem.item.attribute2}
                                ${cartItem.item.attribute3} ${cartItem.item.attribute4}
                                ${cartItem.item.attribute5} ${cartItem.item.product.name}
                            </td>
                            <td>${cartItem.inStock}</td>
                            <td>
                                <input type="text"  id="quantity" onblur="updateCart();" name="${cartItem.item.itemId}" value="${cartItem.quantity}">
                                <div id="cartMsg"></div>
                                <script type="text/javascript" src="${pageContext.request.contextPath }/js/updateCart.js"></script>
                            </td>
                            <td>
                                <fmt:formatNumber value="${cartItem.item.listPrice}" pattern="$#,##0.00" />
                            </td>
                            <td>
                                <label id="total">${cartItem.total}</label>
                            </td>
                            <td>
                                <a class="Button" href="RemoveItemFromCart?workingItemId=${cartItem.item.itemId}">Remove</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="7">
                            Sub Total:<label id="subtotal">${sessionScope.cart.subTotal}</label>
                            <input type="submit" value="Update Cart">
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                </table>
            </form>
        <c:if test="${sessionScope.cart.numberOfItems > 0}">
            <a class="Button" href="NewOrderForm">Proceed to Checkout</a>
        </c:if>
    </div>
    <div id="Separator">&nbsp;</div>
</div>
<%@ include file="../common/IncludeBottom.jsp"%>
