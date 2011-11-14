<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title></title>
  <link type="text/css" rel="stylesheet" href="css/css.css" />
</head>
<body>
  <div class="search" style="margin-top: 160px;">
    <div class="sh_bar">
      <form id="sform" action="search" method="get">
      <input id="search" class=sinput type="text" name="q" value="${q}"> 
      <img src="img/searchbtn.jpg" onclick="javascript:document.getElementById('sform').submit()">
      </form>
    </div>
  </div>
</body>
</html>
