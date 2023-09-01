<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acfn" uri="http://acmecorp.com/acfn" %>
<%--
  ~ Licensed to the Apache Software Foundation (ASF) under one or more
  ~ contributor license agreements.  See the NOTICE file distributed with
  ~ this work for additional information regarding copyright ownership.
  ~ The ASF licenses this file to You under the Apache License, Version 2.0
  ~ (the "License"); you may not use this file except in compliance with
  ~ the License.  You may obtain a copy of the License at
  ~
  ~     http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  --%>

<!DOCTYPE html>
<html lang="en">
  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Review Movie World</title>

    <script src="${pageContext.request.contextPath}/webjars/jquery/1.11.0/dist/jquery.js"></script>

    <link href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7/dist/css/bootstrap.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7/dist/js/bootstrap.js"></script>

    <link href="${pageContext.request.contextPath}/webjars/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker3.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/webjars/bootstrap-datepicker/1.4.0/js/bootstrap-datepicker.js"></script>

  </head>

  <body>
    <jsp:include page="/WEB-INF/includes/header.jsp" />

    <div class="container">

      <h1>Movie Review Form</h1>


      <c:if test="${messages.info != null}">
        <div class="alert alert-success" role="alert">
          ${messages.info}
        </div>
      </c:if>
      <c:if test="${not empty messages.errors}">
        <div class="alert alert-danger" role="alert">
          Please correct the following errors:<br/><br/>
          <ul class="list-unstyled">
            <c:forEach var="error" items="${messages.errors}">
              <li>${error}</li>
            </c:forEach>
          </ul>
        </div>
      </c:if>

      <form action="${pageContext.request.contextPath}/r/home/create" method="POST" class="form-horizontal">


        <p class="text-uppercase text-muted">Personal details</p>
        <div class="form-group">
          <label for="firstname" class="col-sm-4 control-label <c:if test="${acfn:contains(errorProps,'firstname')}">text-danger</c:if>">First name</label>
          <div class="col-sm-4">
            <input type="text" class="form-control" id="firstname" name="firstname" placeholder="First name"
                value="${reviewForm.firstname}" autofocus>
          </div>
        </div>
        <div class="form-group">
          <label for="lastname" class="col-sm-4 control-label <c:if test="${acfn:contains(errorProps,'lastname')}">text-danger</c:if>">Last name</label>
          <div class="col-sm-4">
            <input type="text" class="form-control" id="lastname" name="lastname" placeholder="Last name"
                value="${reviewForm.lastname}" autofocus>
          </div>
        </div>
        <div class="form-group">
          <label for="dob" class="col-sm-4 control-label <c:if test="${acfn:contains(errorProps,'dob')}">text-danger</c:if>">Date of birth</label>
          <div class="col-sm-4">
            <input type="text" id="dob" name="dob" class="form-control" placeholder="dd/mm/yyyy"
                   value="${reviewForm.dob}" autofocus>
          </div>
        </div>
        <div class="form-group">
          <label for="email" class="col-sm-4 control-label <c:if test="${acfn:contains(errorProps,'email')}">text-danger</c:if>">Email</label>
          <div class="col-sm-4">
            <input type="text" class="form-control" id="email" name="email" placeholder="Email"
                   value="${reviewForm.email}" autofocus>
          </div>
        </div>

        <p class="text-uppercase text-muted">Review details</p><br>
        <div class="form-group">
          <label for="moviename" class="col-sm-4 control-label <c:if test="${acfn:contains(errorProps,'moviename')}">text-danger</c:if>">Movie Title</label>
          <div class="col-sm-4">
            <input type="text" class="form-control" id="moviename" name="moviename" placeholder="Movie Name"
                value="${reviewForm.moviename}" autofocus>
          </div>
        </div>
        <div class="form-group">
          <label for="directorname" class="col-sm-4 control-label <c:if test="${acfn:contains(errorProps,'directorname')}">text-danger</c:if>">Director</label>
          <div class="col-sm-4">
            <input type="text" class="form-control" id="directorname" name="directorname" placeholder="Director Name"
                value="${reviewForm.directorname}" autofocus>
          </div>
        </div>
        <div class="form-group">
          <label for="movierating" class="col-sm-4 control-label <c:if test="${acfn:contains(errorProps,'movierating')}">text-danger</c:if>">Movie Rating (1-10)</label>
          <div class="col-sm-4">
            <input type="number" min="1" max="10" class="form-control" id="movierating" name="movierating" placeholder="Movie Rating"
                value="${reviewForm.movierating}" autofocus>
          </div>
        </div>
        <div class="form-group">
          <label for="notes" class="col-sm-4 control-label">Notes</label>
          <div class="col-sm-6">
            <textarea id="notes" name="notes" class="form-control" rows="5" autofocus>${reviewForm.notes}</textarea>
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-offset-4 col-sm-8">
            <button type="submit" class="btn btn-primary">Submit</button>
          </div>
        </div>
      </form>
    </div>
  </body>
</html>
