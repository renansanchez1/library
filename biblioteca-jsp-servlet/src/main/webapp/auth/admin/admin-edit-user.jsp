<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit User</title>

<link
	href="${pageContext.request.contextPath}/resources/bootstrap-5.1.3-dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/jquery-3.6.0-dist/jquery-3.6.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/bootstrap-5.1.3-dist/js/bootstrap.min.js"></script>

</head>
<body>

	<jsp:include page="/public/public-nav.jsp" />

	<div class="container">
		<h2>Edit User</h2>
		<form action="${pageContext.request.contextPath}/auth/admin" method="post">
			<input type="hidden" name="action" value="edit">
			<input type="hidden" name="id" value="${user.id}">

			<div class="mb-3">
				<label for="name" class="form-label">Name:</label>
				<input type="text" class="form-control" id="name" name="name" value="${user.name}" required>
			</div>

			<div class="mb-3">
				<label for="cpf" class="form-label">CPF:</label>
				<input type="text" class="form-control" id="cpf" name="cpf" value="${user.cpf}" required>
			</div>

			<div class="mb-3">
				<label for="date_birth" class="form-label">Date of Birth:</label>
				<input type="date" class="form-control" id="date_birth" name="date_birth" 
					value="<fmt:formatDate value='${user.dateBirth}' pattern='yyyy-MM-dd' />" required>
			</div>

			<div class="mb-3">
				<label for="email" class="form-label">Email:</label>
				<input type="email" class="form-control" id="email" name="email" value="${user.email}" required>
			</div>

			<div class="mb-3">
				<label for="password" class="form-label">Password:</label>
				<input type="password" class="form-control" id="password" name="password" value="${user.password}" required>
			</div>

			<div class="mb-3">
				<label for="login" class="form-label">Login:</label>
				<input type="text" class="form-control" id="login" name="login" value="${user.login}" required>
			</div>

			<div class="mb-3">
				<label class="form-label">Active:</label>
				<select class="form-select" name="active">
					<option value="true" ${user.active ? 'selected' : ''}>Active</option>
					<option value="false" ${!user.active ? 'selected' : ''}>Inactive</option>
				</select>
			</div>

			<button type="submit" class="btn btn-primary">Update</button>
			<a href="${pageContext.request.contextPath}/auth/admin?action=list" class="btn btn-secondary">Cancel</a>
		</form>
	</div>

</body>
</html>
