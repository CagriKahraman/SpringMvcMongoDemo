<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>User form</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.4.1.min.js"></script>
</head>
<body>
	<div class="container">
		<h3 id="form_header" class="text-warning" align="center">Customer
			Form</h3>

		<c:url var="saveUrl" value="/customer/save" />
		<form:form id="customer_form" modelAttribute="userAttr" method="POST"
			action="${saveUrl}">
			<form:hidden path="id" />
			<label for="customer_name">Customer Name: </label>
			<form:input id="customer_name" cssClass="form-control" path="name"
				required="required" />
			<div>&nbsp;</div>
			<label for="customer_lastName">Customer LastName: </label>
			<form:input id="customer_lastName" cssClass="form-control"
				path="lastName" required="required" />
			<div>&nbsp;</div>
			<label for="customer_phone">Customer Phone: </label>
			<form:input id="customer_phone" cssClass="form-control" path="phone"
				required="required" />
			<div>&nbsp;</div>

			<button id="saveBtn" type="submit" class="btn btn-primary">Save</button>
			<button id="resett" type="reset" class="btn btn-primary">Reset</button>
			<button onclick="cancelAction()" class="btn btn-primary">
				Cancel</button>
		</form:form>

		<script type="text/javascript">
			function cancelAction() {
				window.location.href = "list";
			}
		</script>
	</div>
</body>
</html>