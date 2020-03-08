<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/10/1
  Time: 0:20
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>
<div id="Catalog">
    <form action="NewAccount" method="post">

        <h3>User Information</h3>

        <table>
            <tr>
                <td>User ID:</td>
                <td>
                    <input type="text" id="username" name="username" >
                    <div id="usernameMsg"></div>
                    <script type="text/javascript" src="js/register.js"></script>
                </td>
            </tr>
            <tr>
                <td>New password:</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td>Repeat password:</td>
                <td><input type="password" name="repeatedPassword"></td>
            </tr>
            <tr>
                <td>Verification:</td>
                <td><input type="text" name="checkCode"></td>
                <td><img src="pictureCheckCode" id="CreateCheckCode"></td>
            </tr>
        </table>
        <p>
            <font color="red">${requestScope.msg}</font>
        </p>
        <%@ include file="IncludeAccountFields.jsp"%>

        <input type="submit" name="NewAccount" value="Save Account Information">

    </form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
