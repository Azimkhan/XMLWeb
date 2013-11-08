<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
		
		<!-- Fixed navbar -->
		<div class="navbar navbar-default navbar-fixed-top" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">XML Web Application</a>
				</div>
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">Goods</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>

		<!-- Begin page content -->
		<div class="container">
			
			<div class="row">
			<div class="page-header">
				<h1>Select parser</h1>
			</div>
				<form action="controller" role="form">
	
					<input name="command" type="hidden" value="data"/>
					<div class="form-group">
					<select class="form-control" name="parser">
						<option value="sax">SAX</option>
						<option value="stax">StAX</option>
						<option value="dom">DOM</option>
					</select>
					</div>
					<div class="form-group">
					<input class="btn btn-lg btn-primary" type="submit" value="Ok"/>
					</div>
				</form>
			</div>
		</div>

		</div>
		<div id="footer">
			<div class="container">
				<p class="text-muted credit">
					<a href="http://epam.com/">EPAM Systems</a> 2013.
				</p>
			</div>
		</div>
</body>
</html>