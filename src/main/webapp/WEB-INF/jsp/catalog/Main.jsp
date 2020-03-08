<%--
  Created by IntelliJ IDEA.
  User: wsx1ao
  Date: 2019/10/15
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../common/IncludeTop.jsp"%>
<div id="Welcome">
    <div id="WelcomeContent">
        <p>
            <font color="#adff2f"> ${sessionScope.account.getUsername()}</font> Welcome to MyPetStore!
        </p>
    </div>
</div>
<div id="Main">
    <div id="Sidebar">
        <div id="SidebarContent">
            <a href="Category?categoryId=FISH"><img src="images/fish_icon.gif" /></a>
            <br/> Saltwater, Freshwater <br/>
            <a href="Category?categoryId=DOGS"><img src="images/dogs_icon.gif" /></a>
            <br /> Various Breeds <br />
            <a href="Category?categoryId=CATS"><img src="images/cats_icon.gif" /></a>
            <br /> Various Breeds, Exotic Varieties <br />
            <a href="Category?categoryId=REPTILES"><img src="images/reptiles_icon.gif" /></a>
            <br /> Lizards, Turtles, Snakes <br />
            <a href="Category?categoryId=BIRDS"><img src="images/birds_icon.gif" /></a>
            <br /> Exotic Varieties
        </div>
    </div>
    <script type="text/javascript"  src="js/mouseEvent.js" ></script>

    <div id="MainImage">
        <div id="MainImageContent">
            <map name="estoremap">
                <area alt="Birds" coords="72,2,280,250" href="Category?categoryId=BIRDS" shape="rect" onmouseover="showInform(alt);" onmouseout="hiddenInform(event)"/>
                <area alt="Fish" coords="2,180,72,250" href="Category?categoryId=FISH" shape="rect" onmouseover="showInform(alt);" onmouseout="hiddenInform(event)"/>
                <area alt="Dogs" coords="60,250,130,320" href="Category?categoryId=DOGS" shape="rect" onmouseover="showInform(alt);" onmouseout="hiddenInform(event)"/>
                <area alt="Reptiles" coords="140,270,210,340" href="Category?categoryId=REPTILES" shape="rect" onmouseover="showInform(alt);" onmouseout="hiddenInform(event)"/>
                <area alt="Cats" coords="225,240,295,310" href="Category?categoryId=CATS" shape="rect"onmouseover="showInform(alt);" onmouseout="hiddenInform(event)"/>
                <area alt="Birds" coords="280,180,350,250" href="Category?categoryId=BIRDS" shape="rect"onmouseover="showInform(alt);" onmouseout="hiddenInform(event)"/>
            </map>
            <img height="355" src="images/splash.gif" align="middle" usemap="#estoremap" width="350" />
        </div>
    </div>
    <div id="inform" style="display: none" onmouseover="showInform()">sssssssssss</div>
    <div id="Separator">&nbsp;</div>
</div>
<%@include file="../common/IncludeBottom.jsp"%>