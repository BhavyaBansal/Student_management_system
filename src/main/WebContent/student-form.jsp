<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>
<style type="text/css">
#btn-inline{
	display: inline;
}
</style>
<link rel="stylesheet"
 href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
 integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
 crossorigin="anonymous">
</head>
<body style="font-family: serif;">
 <header>
  <div style="background-color: #262626;padding:10px;margin-bottom: 30px;">
   <div>
    <div class="text-center" style="font-size: 3em;color:White;">KIET GROUP OF INSTITUTIONS</div>
   </div>
  </div>
 </header>
 <br>
 <div class="container col-md-5" style="margin-top: 50px;">
  	<div class="card">
  		 <div class="card-body">
    		<c:if test="${student != null}">
     			<form action="update" method="post">
    		</c:if>
    		<c:if test="${student == null}">
     			<form action="insert" method="post">
    		</c:if>

   		 <caption>
     <h2>
      <c:if test="${student != null}">Update Student</c:if>
      <c:if test="${student == null}">Add New Student</c:if>
     </h2>
    	</caption>

    <c:if test="${student != null}">
     <input type="hidden" name="id" value="<c:out value='${student.id}' />" />
    </c:if>

    <fieldset class="form-group">
     <label>FirstName:</label> <input type="text"
      value="<c:out value='${student.firstname}' />" class="form-control"
      name="firstname" required="required">
    </fieldset>

    <fieldset class="form-group">
     <label>LastName:</label> <input type="text"
      value="<c:out value='${student.lastname}' />" class="form-control"
      name="lastname">
    </fieldset>

    <fieldset class="form-group">
     <label>Email:</label> <input type="text"
      value="<c:out value='${student.email}' />" class="form-control"
      name="email">
    </fieldset>

    <c:if test="${student == null}"><button type="submit" class="btn btn-success" >Save</button></c:if>
    <c:if test="${student != null}"><button type="submit" class="btn btn-success" >Update</button></c:if>
    <a href="<%=request.getContextPath()%>/list"class="nav-link" id="btn-inline"><button class="btn btn-success">Back To List</button></a>
    </form>
   </div>
  </div>
 </div>
</body>
</html>