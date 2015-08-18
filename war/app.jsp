<%@page import="com.wildwestbank.common.security.SecurityContext"%>
<%@page import="com.google.gwt.thirdparty.guava.common.base.Strings"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
SecurityContext context = (SecurityContext) session.getAttribute(SecurityContext.class
		.getName());
if (context == null) {
	String url = "login";
	if (!Strings.isNullOrEmpty(request.getQueryString())) {
		url += "?" + request.getQueryString();
	}
	response.sendRedirect(url);
	return;
}
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<link type="text/css" rel="stylesheet" href="app.css">
	    <link type="text/css" rel="stylesheet" href="app/reset.css">
	    
		<title>Wild West Bank</title>
		
		<script type="text/javascript" language="javascript" src="app/app.nocache.js"></script>
	</head>
	<body>
		<!-- RECOMMENDED if your web app will not function without JavaScript enabled -->
	    <noscript>
	      <div style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
	        Your web browser must have JavaScript enabled
	        in order for this application to display correctly.
	      </div>
	    </noscript>
	</body>
</html>