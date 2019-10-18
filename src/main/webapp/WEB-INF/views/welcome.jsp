<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Welcome</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<h2 id="article_header" class="text-warning" align="center">Spring
			Mvc</h2>
		<div>&nbsp;</div>

		<div id="add_new_user">
			<c:url var="addUrl" value="/customer/add" />
			<a id="add" href="${addUrl}" class="btn btn-success">Add Customer</a>
		</div>
		<div>&nbsp;</div>

		<table id="users_table" class="table">
			<thead>
				<tr align="center">
					<th>Id</th>
					<th>Name</th>
					<th>Surname</th>
					<th>Phone</th>
					<th colspan="4"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${customers}" var="customer">
					<tr align="center">
						<td><c:out value="${customer.id}" /></td>
						<td><c:out value="${customer.name}" /></td>
						<td><c:out value="${customer.lastName}" /></td>
						<td><c:out value="${customer.phone}" /></td>

						<td><c:url var="editUrl"
								value="/customer/edit?id=${customer.id}" /><a id="update"
							href="${editUrl}" class="btn btn-warning">Update</a></td>
						<td><c:url var="deleteUrl"
								value="/customer/delete?id=${customer.id}" /><a id="delete"
							href="${deleteUrl}" class="btn btn-danger">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>