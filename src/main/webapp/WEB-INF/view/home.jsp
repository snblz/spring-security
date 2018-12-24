<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>Home Page</title>
</head>
<body>
	<hr>
	<h2>Welcome to the Home Page</h2>
	<hr>
		<p>User: <security:authentication property="principal.username"/></p>
		<p>Role(s): <security:authentication property="principal.authorities"/></p>
		
		<security:authorize access="hasRole('MANAGER')">
			<hr>
			<p><a href="${pageContext.request.contextPath}/leaders">Leadership Meeting </a>(only for managers)</p>
		</security:authorize>
		
		<security:authorize access="hasRole('ADMIN')">
			<hr>
			<p><a href="${pageContext.request.contextPath}/systems">System administrator message </a>(only for admins)</p>
		</security:authorize>
	<hr>
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
		<input type="submit" value="Logout" />
	</form:form>
</body>
</html>