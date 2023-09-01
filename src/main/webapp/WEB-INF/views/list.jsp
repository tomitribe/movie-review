<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

    <link href="${pageContext.request.contextPath}/webjars/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker3.css"
          rel="stylesheet">
    <script src="${pageContext.request.contextPath}/webjars/bootstrap-datepicker/1.4.0/js/bootstrap-datepicker.js"></script>

</head>

<body>
<jsp:include page="/WEB-INF/includes/header.jsp"/>

<div class="container">

    <h1>All Reviews</h1>

    <div class="table-responsive">
        <table class="table table-striped table-bordered table-condensed">
            <tr>
                <th>ID</th>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Moviename</th>
                <th>Actions</th>
            </tr>
            <c:forEach items="${results}" var="item">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.firstname}</td>
                    <td>${item.lastname}</td>
                    <td>${item.moviename}</td>
                    <td class="text-center">
                        <a href="${pageContext.request.contextPath}/r/review/view/${item.id}">
                            <small><span class="glyphicon glyphicon-zoom-in" aria-hidden="true"></span></small>
                        </a>
                        <a href="${pageContext.request.contextPath}/r/review/edit/${item.id}">
                            <small><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></small>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

</div>
</body>
</html>
