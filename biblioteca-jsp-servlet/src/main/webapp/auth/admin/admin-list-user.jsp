<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All users</title>

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
		<div class="row">
			<div class="col">
				<div class="row">
			
			<div class="col">
				<h2>
					Users
				</h2>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Cpf</th>
							<th>Birth</th>
							<th>E-mail</th>
							<th>Active?</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${listUsers}">
							<tr>
								<td><c:out value="${user.id}" /></td>
								<td><c:out value="${user.name}" /></td>
								<td><c:out value="${user.cpf}" /></td>
								<td><fmt:formatDate value='${user.dateBirth}'
										type='date' pattern='dd/MM/yyyy' /></td>
								<td><c:out value="${user.email}" /></td>

								<td>
									
									<span> <c:out value= "${user.active=='true' ? 'active' : 'inactive'}" /> </span>
								</td>								
							
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</div>
	</div>

</body>
</html>