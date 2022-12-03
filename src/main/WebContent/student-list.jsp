<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Student Management Application</title>

<link rel="stylesheet"
 href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
 integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
 crossorigin="anonymous">
 </head>
</head>
<body style="font-family: serif;background-color:grey;color:white;">
 <header>
  <div style="background-color: #31318d;padding:10px;margin-bottom: 30px;">
   <div>
    <div class="text-center" style="font-size: 3em;color:White;">KIET GROUP OF INSTITUTIONS</div>
   </div>
  </div>
 </header>

 <div class="row" style="margin-right: 0;margin-left: 0;color:white;">
  <div class="container">
   <h3 class="text-center">List of Students</h3>
   <hr>
   <div class="container text-center">

    <a href="<%=request.getContextPath()%>/new"
     class="btn btn-success">Add New Students</a>
   </div>
   <br>
   <table class="table table-bordered" style="text-align: center;color:white">
    <thead style="font-size: 20px;">
     <tr>
      <th>ID</th>
      <th>Firstname</th>
      <th>Lastname</th>
      <th>Email</th>
      <th>Actions</th>
     </tr>
    </thead>
    <tbody>
    <% int count=0; %>
     <c:forEach var="student" items="${liststudent}">
      <tr>
       <% count+=1; %>
       <td><%= count %></td>
       <td><c:out value="${student.firstname}" /></td>
       <td><c:out value="${student.lastname}" /></td>
       <td><c:out value="${student.email}" /></td>

       <td><a href="edit?id=<c:out value='${student.id}' />" style="color:red;">Update</a>
        &nbsp;&nbsp;&nbsp;&nbsp;
         <a href="delete?id=<c:out value='${student.id}' />" style="color:red;">Delete</a></td>
      </tr>
     </c:forEach>
    </tbody>

   </table>
  </div>
 </div>
</body>
</html>