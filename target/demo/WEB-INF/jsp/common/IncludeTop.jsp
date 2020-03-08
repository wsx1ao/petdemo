<%--
  Created by IntelliJ IDEA.
  User: wsx1ao
  Date: 2019/10/15
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link rel="StyleSheet" href="css/jpetstore.css" type="text/css"
          media="screen" />
    <meta name="generator"
          content="HTML Tidy for Linux/x86 (vers 1st November 2002), see www.w3.org" />
    <title>JPetStore Demo</title>
    <meta content="text/html; charset=windows-1252"
          http-equiv="Content-Type" />
    <meta http-equiv="Cache-Control" content="max-age=0" />
    <meta http-equiv="Cache-Control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
    <meta http-equiv="Pragma" content="no-cache" />
<%--    <script type="text/javascript" src="js/jquery-3.3.1.js"></script>--%>
</head>

<body>

<div id="Header">

    <div id="Logo">
        <div id="LogoContent">
            <a href="Main"><img src="images/logo-topbar.gif" /></a>
        </div>
    </div>
    <div id="Menu">
        <div id="MenuContent">
            <a href="Cart"><img align="middle" name="img_cart"
                                    src="images/cart.gif" /></a> <img align="middle"
                                                                         src="images/separator.gif" />
            <c:if test="${sessionScope.account == null}">
                <a href="SignOnForm">Sign In</a>
            </c:if>
            <c:if test="${sessionScope.account != null}">
                <a href="SignOff">Sign Out</a>
            </c:if>
            <img align="middle" src="images/separator.gif" />
            <c:if test="${sessionScope.account != null}">
                <a href="EditAccountForm">My Account</a>
            </c:if>
            <img align="middle" src="images/separator.gif" />
            <a href="http://www.w3school.com.cn/index.html">?</a>
        </div>
    </div>

    <div id="Search">
        <div id="SearchContent">
            <form action="search" method="post">
                <input type="text" id="keyword" name="keyword" size="14" />
                <input type="submit"  name="searchProducts" value="Search" />
                <div class="auto hidden" id="auto">
                    <div class="auto_out"></div>
                    <div class="auto_out"></div>
                    <script src="js/searchProduct.js"></script>
                </div>
            </form>
        </div>
    </div>

    <div id="QuickLinks">
        <a href="Category?categoryId=FISH">
            <img src="images/sm_fish.gif" /></a> <img src="images/separator.gif" />
        <a href="Category?categoryId=DOGS">
            <img src="images/sm_dogs.gif" /></a> <img src="images/separator.gif" />
        <a href="Category?categoryId=REPTILES">
            <img src="images/sm_reptiles.gif" /></a>
        <img src="images/separator.gif" /> <a href="Category?categoryId=CATS">
        <img src="images/sm_cats.gif" /></a> <img src="images/separator.gif" />
        <a href="Category?categoryId=BIRDS">
            <img src="images/sm_birds.gif" /></a>
    </div>

</div>

<div id="Content">
