<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/10/1
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
    <form action="EditAccount" method="post">
    <h3>User Information</h3>
    <table>
        <tr>
            <td>User ID:</td>
            <td>${sessionScope.account.getUsername()}</td>
        </tr>
        <tr>
            <td>New password:</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td>Repeat password:</td>
            <td><input type="password" name="repeatedPassword"></td>
        </tr>
    </table>
    <%@ include file="IncludeAccountFields.jsp"%>

    <input type="submit" name="EditAccount" value="Save Account Information">

    </form>
    <a href="ListOrder">My Orders</a>
</div>
<%@ include file="../common/IncludeBottom.jsp"%>

