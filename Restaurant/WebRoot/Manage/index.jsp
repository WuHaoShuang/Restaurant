    <%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
            <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

        <!DOCTYPE HTML>
        <html xmlns="http://www.w3.org/1999/xhtml">
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Simpla Admin</title>
        <!-- CSS -->
        <!-- Reset Stylesheet -->
        <link rel="stylesheet" href="resources/css/reset.css" type="text/css" media="screen" />
        <!-- Main Stylesheet -->
        <link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen" />
        <!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
        <link rel="stylesheet" href="resources/css/invalid.css" type="text/css" media="screen" />
        <!-- Javascripts -->
        <!-- jQuery -->
        <script type="text/javascript" src="/Restaurant/js/jquery-1.11.0.min.js"></script>
        <script type="text/javascript" src="/Restaurant/js/jquery.validate.min.js"></script>
        <!-- jQuery Configuration -->
        <script type="text/javascript" src="resources/scripts/simpla.jquery.configuration.js"></script>
        <!-- Facebox jQuery Plugin -->
        <script type="text/javascript" src="resources/scripts/facebox.js"></script>
        <script type="text/javascript" src="/Restaurant/js/datePicker/WdatePicker.js"></script>
        <script type="text/javascript" src="/Restaurant/layer/layer.js"></script>
        <script type="text/javascript" src="/Restaurant/js/main.js"></script>
            <script type="text/javascript" src="/Restaurant/js/jquery.qrcode.min.js"></script>
            <script type="text/javascript" src="/Restaurant/UE/ueditor.config.js"></script>
            <script type="text/javascript" src="/Restaurant/UE/ueditor.all.min.js"></script>
            </head>
        <body>
        <div id="body-wrapper">
        <!-- Wrapper for the radial gradient background -->
        <div id="sidebar">
        <div id="sidebar-wrapper">
        <!-- Sidebar with logo and menu -->
        <h1 id="sidebar-title"><a href="#">Simpla Admin</a></h1>
        <!-- Logo (221px wide) -->
        <a href="#"><img id="logo" src="resources/images/logo.png" alt="Simpla Admin logo" /></a>
        <!-- Sidebar Profile links -->
            <input type="hidden" id="restname" value="<%=session.getAttribute("restname") %>"/>
        <div id="profile-links"> Hello,<%=session.getAttribute("restname") %>
        <br />
        <a href="#" title="View the Site">View the Site</a> | <a href="#" title="Sign Out" id="logout">Sign Out</a>
        </div>
        <ul id="main-nav">
        <!-- Accordion Menu -->
        <li> <a href="#" class="nav-top-item">
        <!-- Add the class "current" to current menu item -->
        菜品 </a>
        <ul>
        <li><a href="#" data="addClass.html">添加分类</a></li>
        <li><a href="#" data="addDish.html">添加菜品</a></li>
        <li><a href="#" data="editDish.html">编辑菜品</a></li>
        </ul>
        </li>
        <li> <a href="#" class="nav-top-item"> 餐桌管理 </a>
        <ul>
        <li><a href="#" data="addtable.html">添加餐桌</a></li>
        <li><a href="#" data="edittable.html">编辑餐桌</a></li>
        <li><a href="#" data="qrcode.html">查看二维码</a></li>
        </ul>
        </li>
        <li> <a href="#" class="nav-top-item"> 个人信息 </a>
        <ul>
        <li><a href="#" data="editPassword.html">修改密码</a></li>
        </ul>
        </li>
        <li> <a href="#" class="nav-top-item"> 销售统计 </a>
        <ul>
            <li><a href="#" data="nowbill.html">查看当前账单</a></li>
        <li><a href="#" data="bill.html">账单查询</a></li>
        <li><a href="#"  data="sale.html">产品销量查询</a></li>

        </ul>
        </li>
        <li> <a href="#" class="nav-top-item"> 产品入库 </a>
        <ul>
        <li><a href="#" data="addProduct.html">添加产品类型</a></li>
        <li><a href="#" data="storage.html">产品入库</a></li>
        <li><a href="#" data="selectStorage.html">入库查询</a></li>
        </ul>
        </li>
        </ul>
        <!-- End #main-nav -->
        </div>
        </div>
        <!-- End #sidebar -->
        <div id="main-content" class="main-content">
        <!-- Main Content Section with everything -->
        <noscript>
        &amp;amp;lt;!-- Show a notification if the user has disabled javascript --&amp;amp;gt;
        &amp;amp;lt;div class="notification error png_bg"&amp;amp;gt;
        &amp;amp;lt;div&amp;amp;gt; Javascript is disabled or is not supported by your browser. Please &amp;amp;lt;a
        href="http://browsehappy.com/" title="Upgrade to a better browser"&amp;amp;gt;upgrade&amp;amp;lt;/a&amp;amp;gt;
        your browser or &amp;amp;lt;a href="http://www.google.com/support/bin/answer.py?answer=23852" title="Enable
        Javascript in your browser"&amp;amp;gt;enable&amp;amp;lt;/a&amp;amp;gt; Javascript to navigate the interface
        properly.
        Download From &amp;amp;lt;a
        href="http://www.exet.tk"&amp;amp;gt;exet.tk&amp;amp;lt;/a&amp;amp;gt;&amp;amp;lt;/div&amp;amp;gt;
        &amp;amp;lt;/div&amp;amp;gt;
        </noscript>
        <!-- Page Head -->
        <h2>Welcome</h2>
        <p id="page-intro">What would you like to do?</p>
        <ul class="shortcut-buttons-set">
        <li><a class="shortcut-button" href="#" data="nowbill.html"><span> <img
        src="resources/images/icons/pencil_48.png" alt="icon"><br>
        查看当前菜单 </span></a></li>
        <li><a class="shortcut-button" href="#" data="addDish.html"><span> <img
        src="resources/images/icons/paper_content_pencil_48.png" alt="icon"><br>
        添加菜品 </span></a></li>
        <li><a class="shortcut-button" href="#" data="editDish.html"><span> <img
        src="resources/images/icons/image_add_48.png" alt="icon"><br>
        编辑菜品 </span></a></li>
        <li><a class="shortcut-button" href="#" data="bill.html"><span> <img src="resources/images/icons/clock_48.png"
        alt="icon"><br>
        查看账单</span></a></li>
        <li><a class="shortcut-button" href="#" data="storage.html" ><span> <img
        src="resources/images/icons/comment_48.png" alt="icon"><br>
        产品入库</span></a></li>
        </ul>
        <!-- End .shortcut-buttons-set -->
        <div class="clear"></div>
        <!-- End .clear -->
        <div class="content-box" id="main-content-box">
        <!-- Start Content Box -->
        <div class="content-box-header">
        <h3 style="cursor: s-resize;">操作页面</h3>
        <ul class="content-box-tabs" style="display: block;">
        <!-- href must be unique and match the id of target div -->

        </ul>
        <div class="clear"></div>
        </div>
        <!-- End .content-box-header -->
        <div class="content-box-content" style="display: block;">
        </div>
        <!-- End .content-box-content -->
        </div>
        <!-- End .content-box -->
        <div class="content-box column-left closed-box">
        <div class="content-box-header">
        <h3 style="cursor: s-resize;">操作方法</h3>
        </div>
        <!-- End .content-box-header -->
        <div class="content-box-content" style="display: none;">
        <div class="tab-content default-tab" style="display: block;">
        <h4>Maecenas dignissim</h4>
        <p> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed in porta lectus. Maecenas dignissim enim quis
        ipsum mattis aliquet. Maecenas id velit et elit gravida bibendum. Duis nec rutrum lorem. Donec egestas metus a
        risus euismod ultricies. Maecenas lacinia orci at neque commodo commodo. </p>
        </div>
        <!-- End #tab3 -->
        </div>
        <!-- End .content-box-content -->
        </div>
        <!-- End .content-box -->
        <div class="content-box column-right closed-box">
        <div class="content-box-header">
        <!-- Add the class "closed" to the Content box header to have it closed by default -->
        <h3 style="cursor: s-resize;">联系方式</h3>
        </div>
        <!-- End .content-box-header -->
        <div class="content-box-content" style="display: none;">
        <div class="tab-content default-tab" style="display: block;">
        <h4>This box is closed by default</h4>
        <p> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed in porta lectus. Maecenas dignissim enim quis
        ipsum mattis aliquet. Maecenas id velit et elit gravida bibendum. Duis nec rutrum lorem. Donec egestas metus a
        risus euismod ultricies. Maecenas lacinia orci at neque commodo commodo. </p>
        </div>
        <!-- End #tab3 -->
        </div>
        <!-- End .content-box-content -->
        </div>
        <!-- End .content-box -->
        <div class="clear"></div>
        <!-- Start Notifications -->


        <!-- End Notifications -->

        <!-- End #footer -->
        </div>
        <!-- End #main-content -->
        </div>
        </body>
        <!-- Download From www.exet.tk-->
        </html>

