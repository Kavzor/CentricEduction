<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<spring:url value="/resources/profile.js" var="profileJs" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
</head>
<body>

	<p>Hello ${ user.username }
	<p>You have logged in ${ user.loginCounter } times
	<p>You live at ${ user.residence }
	<br>
	
	<div id="profile">
		<p> <input type="text" v-model="cityFilter" v-bind:placeholder="placeholder">
		<button v-on:click="searchCity">Search</button>
	
		<ul>
			<li v-for="profile in profiles">
				{{profile.residence }} || {{ profile.username }}
			</li>
		</ul>
	
	</div>
	
	<form action="logout" method="POST">
		<button>Logout</button>
	</form>
	
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
	<script type="text/javascript" src="${profileJs}"></script>
</body>
</html>