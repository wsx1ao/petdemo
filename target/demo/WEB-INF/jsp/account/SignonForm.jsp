<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/10/1
  Time: 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
    <form action="Sign" method="post">
        <p>Please enter your username and password.</p>
        <p>
            Username:<input type="text" name="username" value="j2ee"> <br />
            Password:<input type="password" name="password" value="j2ee"> <br />
            Verification Code:<input type="text" name="checkCode">
            <img src="pictureCheckCode" id="CreateCheckCode" align="middle" > <br>
        </p>
        <p>
            <font color="red">${requestScope.msg}</font>
        </p>
        <input type="submit" name="Sign" value="login">
    </form>Need a user name and password?
    <a href="NewAccountForm">Register Now!</a>
</div>


<%@ include file="../common/IncludeBottom.jsp"%>


