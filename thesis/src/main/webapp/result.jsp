<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title></title>
	<link href="css/bootstrap.css" rel="stylesheet">
	<link type="text/css" rel="stylesheet" href="css/css.css" />
</head>
<body>
  <div class="container">
  </div>
	<div class="search">
		<div class="sh_bar">
      <form id="sform" action="search" method="get">
      <input id="search" class=sinput type="text" name="q" value="${q}"> 
      <img src="img/searchbtn.jpg" onclick="javascript:document.getElementById('sform').submit()">
      </form>
		</div>
		<div>
    
		<div class="main">
			<ul>
        <c:forEach items="${pageModel.items}" var="item">
          <li class="item">
	          <h1><a href="${item.url}">${item.title}</a></h1>
	          <p class="content">${item.content}</p>
	          <p class="url">${item.url}</p>
          </li>   
        </c:forEach>
			</ul>
			<%-- 
			<div class="page">
				<span class="page_0">&lt;&lt;</span><span class="page_0">&lt;</span>
				<span class="current">1</span><a href="#">2</a><a href="#">3</a>
			</div>
			--%>
			<%--
			<div class="page">
        <span class="page_0">&lt;&lt;</span><span class="page_0">&lt;</span>
        <c:forEach begin="${pageModel.startPage}" end="${pageModel.endPage}" varStatus="s">
          <c:choose>
            <c:when test="${pageModel.currentPage eq s.index}">
              <span class="current">${s.index}</span>
            </c:when>
            <c:otherwise>
              <c:url var="url" value="search">
                <c:param name="q">${q}</c:param>
                <c:param name="start">${(s.index - 1) * pageModel.itemsPerPage}</c:param>
                <c:param name="rows">${pageModel.itemsPerPage}</c:param>
              </c:url>
              <a href="${url}">${s.index}</a>
            </c:otherwise>
          </c:choose>
        </c:forEach>
      </div>
      --%>
			<div class="pagination">
				<ul>
				  <c:choose>
				    <c:when test="${pageModel.currentPage eq 1}">
				      <li class="prev disabled"><a href="#">&larr; 上一页</a></li>
				    </c:when>
				    <c:otherwise>
				      <li class="prev"><a href="#">&larr; 上一页</a></li>
				    </c:otherwise>
				  </c:choose>
					<c:forEach begin="${pageModel.startPage}" end="${pageModel.endPage}" varStatus="s">
          <c:choose>
            <c:when test="${pageModel.currentPage eq s.index}">
              <li class="active"><a href="#">${s.index}</a></li>
            </c:when>
            <c:otherwise>
              <c:url var="url" value="search">
                <c:param name="q">${q}</c:param>
                <c:param name="start">${(s.index - 1) * pageModel.itemsPerPage}</c:param>
                <c:param name="rows">${pageModel.itemsPerPage}</c:param>
              </c:url>
              <li><a href="${url}">${s.index}</a></li>
            </c:otherwise>
          </c:choose>
        </c:forEach>
        <c:choose>
          <c:when test="${pageModel.currentPage eq pageModel.totalPages}">
            <li class="next"><a href="#">下一页 &rarr;</a></li>
          </c:when>
          <c:otherwise>
				    <li class="next"><a href="#">下一页 &rarr;</a></li>
          </c:otherwise>
        </c:choose>
				</ul>
			</div>

		</div>
	</div>
</body>
</html>
