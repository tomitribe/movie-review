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

      <h1>Review Form</h1>


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

      <p/>

      <br>
      <form action="${pageContext.request.contextPath}/r/home/select" method="POST" class="form-horizontal">
      <div class="form-group">
        <div class="col-sm-offset-4 col-sm-8">
          <button type="submit" class="btn btn-primary" style="height:100px;width:300px; font-size: 24px;">Create New Review!</button>
        </div>
      </div>
    </form>
  </body>
</html>
