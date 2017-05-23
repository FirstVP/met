<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html>
<head>
    <meta charset="utf-8" />
	<title><decorator:title default="Struts Starter"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="<s:url value='/styles/main.css'/>" rel="stylesheet" type="text/css" media="all"/>
    <link href="<s:url value='/styles/navbar-fixed-side.css'/>" rel="stylesheet" type="text/css" media="all"/>
    <link href="<s:url value='/struts/niftycorners/niftyCorners.css'/>" rel="stylesheet" type="text/css"/>
    <link href="<s:url value='/struts/niftycorners/niftyPrint.css'/>" rel="stylesheet" type="text/css" media="print"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script language="JavaScript" type="text/javascript" src="<s:url value='/struts/niftycorners/nifty.js'/>"></script>
	<script language="JavaScript" type="text/javascript">
        window.onload = function(){
            if(!NiftyCheck()) {
                return;
            }
            // perform niftycorners rounding
            // eg.
            // Rounded("blockquote","tr bl","#ECF1F9","#CDFFAA","smooth border #88D84F");
        }
    </script>
    <decorator:head/>
</head>
<body id="page-home">

    <div id="page">

        <div id="header" class="clearfix">
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="#">Weather</a>
                    </div>
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">Home</a></li>
                        <li><a href="#">Page 1</a></li>
                        <li><a href="#">Page 2</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <s:if test='#session.user_name != null'>
                            <li><a href="index.action">Hello, <s:property value="#session.user_name" /></a></li>
                            <li><a href="logout.action"><span class="glyphicon glyphicon-log-out"></span> Sign out</a></li>
                        </s:if>
                        <s:else>
                            <s:url action="register" method="input" var="menuAdmin" />

                            <li><s:a href="%{menuAdmin}">Sign up</s:a></li>
                            <li><a href="login.action"><span class="glyphicon glyphicon-log-in"></span>Sign in</a></li>
                        </s:else>
                    </ul>
                </div>
            </nav>
        </div>


        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3 col-lg-2">
                    <nav class="navbar navbar-default navbar-fixed-side">
                        <div class="container">
                            <div class="navbar-header">
                                <button class="navbar-toggle" data-target=".navbar-collapse" data-toggle="collapse">
                                    <span class="sr-only">Toggle navigation</span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
                                <a class="navbar-brand" href="./">Menu</a>
                            </div>
                            <div class="collapse navbar-collapse">
                                <ul class="nav navbar-nav">
                                    <li class="active">
                                        <a href="./">Home</a>
                                    </li>
                                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">News <b class="caret"></b></a><ul class="dropdown-menu">
                                        <li><a href="#">Doc</a></li>
                                    </ul></li>
                                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Cities <b class="caret"></b></a><ul class="dropdown-menu">
                                        <s:url action="citiesIndex" var="citiesUrl"></s:url>
                                        <li><a href="<s:property value="#citiesUrl"/>">Index</a></li>

                                        <s:url var="url" action="viewPDF" escapeAmp="false">
                                            <s:param name="type">Cities</s:param>
                                            <s:param name="hasProtection">false</s:param>
                                        </s:url>
                                        <li><a href="<s:property value="#url"/>">Get cities (PDF)</a></li>

                                        <s:url var="url" action="viewPDF" escapeAmp="false">
                                            <s:param name="type">Cities</s:param>
                                            <s:param name="hasProtection">true</s:param>
                                        </s:url>
                                        <li><a href="<s:property value="#url"/>">Get cities (Protected PDF)</a></li>

                                        <s:url var="url" action="viewXLS">
                                            <s:param name="type">Cities</s:param>
                                        </s:url>
                                        <li><a href="<s:property value="#url"/>">Get cities (XLS)</a></li>

                                        <s:url var="url" action="viewCSV">
                                            <s:param name="type">Cities</s:param>
                                        </s:url>
                                        <li><a href="<s:property value="#url"/>">Get cities (CSV)</a></li>
                                    </ul></li>
                                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Disasters <b class="caret"></b></a><ul class="dropdown-menu">
                                        <s:url action="disastersIndex" var="disastersUrl"></s:url>
                                        <li><a href="<s:property value="#disastersUrl"/>">Index</a></li>
                                        <li><a href="#">Doc</a>
                                    </ul></li>
                                        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Accidents <b class="caret"></b></a><ul class="dropdown-menu">
                                            <s:url action="accidentsIndex" var="accidentsUrl"></s:url>
                                            <li><a href="<s:property value="#accidentsUrl"/>">Index</a></li>
                                            <li><a href="#">Doc</a></li>
                                        </ul></li>





                                </ul>
                                <form class="navbar-form navbar-left">
                                    <div class="form-group">
                                        <input class="form-control" placeholder="Search">
                                    </div>
                                    <button class="btn btn-default">Search</button>
                                </form>
                                <ul class="nav navbar-nav navbar-right">
                                    <li><a href="#">Page 4</a></li>
                                </ul>
                                <button class="btn btn-default navbar-btn">Button</button>
                            </div>
                        </div>
                    </nav>
                </div>
                <div class="col-sm-9 col-lg-10">
                    <div id="content" class="clearfix">
                        <div id="main">
                            <decorator:body/>

                        </div>


                    </div>
                </div>
            </div>
        </div>


        
        <div id="footer" class="clearfix">
            <nav class="navbar navbar-inverse navbar-fixed-bottom">
                <div class="container-fluid">
                    <center style="color:white">© BSUIR 2017</center>
                </div>
            </nav>
            </div>
        </div>
        
    </div>
    
    <div id="extra1">&nbsp;</div>
    <div id="extra2">&nbsp;</div>
</body>
</html>
