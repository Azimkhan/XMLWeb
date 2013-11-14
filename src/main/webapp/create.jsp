<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>XML Web Application</title>

<link href="${pageContext.request.contextPath }/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/css/sticky-footer-navbar.css"
	rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy this line! -->
<!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
<style type="text/css">
th:nth-child(n+2) {
	text-align: center
}

td:nth-child(n+2) {
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
					<h1>Select parser</h1>
				</div>
				<c:if test="${not empty error }">
				<div class="alert alert-danger">
					${error }
				</div>
				</c:if>
				<form action="${ pageContext.request.contextPath }/controller">

					<div class="form-group">
						<label for="category">Category name:</label> <input id="category"
							class="form-control" type="text" name="category" value="${param['category'] }"/>
					</div>

					<div class="form-group">
						<label for="subcategory">Subcategory name:</label> <input id="subcategory"
							class="form-control" type="text" name="subcategory" value="${param['subcategory'] }" />
					</div>
					
					<div class="form-group">
						<label for="name">Name:</label> <input id="name"
							class="form-control" type="text" name="name" value="${param['name'] }"/>
					</div>

					<div class="form-group">
						<label for="producer">Producer:</label> <input id="producer"
							class="form-control" type="text" name="producer" value="${param['producer'] }" />
					</div>

					<div class="form-group">
						<label for="model">Model:</label> <input id="model"
							class="form-control" type="text" name="model" value="${param['model'] }"/>
					</div>

					<div class="form-group">
						<label for="date">Date:</label> <input id="date"
							class="form-control" type="text" name="date" value="${param['date'] }" />
					</div>

					<div class="form-group">
						<label for="color">Color:</label> <input id="color"
							class="form-control" type="text" name="color" value="${param['color'] }"/>
					</div>


					<div class="form-group">
						<label for="price">Price:</label> <input id="price"
							class="form-control" type="text" name="price" value="${param['price'] }" />
					</div>

					<div class="form-group">
						<label for="notInStock">Not in stock?:</label> <input
							id="notInStock" value="true" type="checkbox"
							name="notInStock" />
					</div>

					<input type="submit" name="submit" class="btn btn-primary" /> <input
						type="hidden" name="command" value="create" />
				</form>
			</div>
		</div>

	</div>
	<%@ include file="WEB-INF/fragment/footer.jspf" %>
</body>
</html>