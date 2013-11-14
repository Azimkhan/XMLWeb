<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>XML Web Application</title>

<link href="${pageContext.request.contextPath }/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath }/css/sticky-footer-navbar.css"
	rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy this line! -->
<!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    	 th:nth-child(n+2){
    		text-align: center
    	}
    	
    	td:nth-child(n+2){
    		text-align: center
    	}
    </style>
</head>

<body>

	<!-- Wrap all page content here -->
	<div id="wrap">
		
		<%@ include file="WEB-INF/fragment/header.jspf" %>

		<!-- Begin page content -->
		<div class="container">
			
			<div class="row">
			<div class="page-header">
	          <h2>Data source and XML Parser</h2>
	        </div>
			<p class="lead">Date source file is located under <code>${ filename }</code>. Parsed by <code>${ parserName }</code> in <code>${parseTime }</code> milliseconds.</p>
			
			<div class="page-header">
	          <h2>Parsed content</h2>
	        </div>
			<c:forEach var="category" items="${ categories }">
				
					<h3 class="text-muted">${category.name}</h3>
				
					<table class="table table-striped table-bordered">
						<thead>
							<tr>
								<th  width="40%">Name</th>
								<th width="10%">Producer</th>
								<th width="13%">Model</th>
								<th width="10%">Date</th>
								<th width="10%">Color</th>
								<th width="10%">Price</th>
								<th width="7%">In stock</th>
							</tr>
						</thead>
						<tbody>
						
					<c:forEach var="subcategory" items="${category.subCategories }">
					<tr><td colspan="7"><strong>${subcategory.name }</strong></td>
							<c:forEach var="good" items="${subcategory.goods }">
								<tr>
									<td>${ good.name }</td>
									<td>${ good.producer }</td>
									<td>${ good.model }</td>
									<td><fmt:formatDate value="${ good.date.time }" /></td>
									<td>${ good.color }</td>
									<c:choose>
										<c:when test="${ good.notInStock }">
											<td>-</td>
											<td><span class="label label-danger">no</span></td>
										</c:when>
										<c:otherwise>
											<td>$${ good.price }</td>
											<td><span class="label label-success">yes</span></td>
										</c:otherwise>
									</c:choose>
								</tr>
							</c:forEach>
						</c:forEach>	
						</tbody>
					</table>

			</c:forEach>
			</div>
		</div>

		</div>
		
	<%@ include file="WEB-INF/fragment/footer.jspf" %>
</body>
</html>