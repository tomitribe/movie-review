<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <title>Review Details</title>

    <script src="${pageContext.request.contextPath}/webjars/jquery/1.11.0/dist/jquery.js"></script>

    <link href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7/dist/css/bootstrap.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7/dist/js/bootstrap.js"></script>

    <link href="${pageContext.request.contextPath}/webjars/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker3.css"
          rel="stylesheet">
    <script src="${pageContext.request.contextPath}/webjars/bootstrap-datepicker/1.4.0/js/bootstrap-datepicker.js"></script>


    <script type="text/javascript">
        function edit() {
            window.location = "${pageContext.request.contextPath}/r/review/edit/${reviewId}";
        }

        function remove() {
            if (!confirm("Are you sure you wish to delete this review?")) {
                return;
            }

            window.location = "${pageContext.request.contextPath}/r/review/remove/${reviewId}";
        }

        function done() {
            window.location = "${pageContext.request.contextPath}/r/review/list"
        }

    </script>
</head>

<body>
<jsp:include page="/WEB-INF/includes/header.jsp"/>

<div class="container">

    <h1>Review Details</h1>

    <br>
    <p class="text-uppercase text-muted"><b>Personal details</b></p>
    <div class="row">
        <span class="col-sm-4 text-right">First name</span>
        <span class="col-sm-8">${review.firstname}</span>
    </div>
    <div class="row">
        <span class="col-sm-4 text-right">Last name</span>
        <span class="col-sm-8">${review.lastname}</span>
    </div>
    <div class="row">
        <span class="col-sm-4 text-right">Date of birth</span>
        <span class="col-sm-8">${review.dob}</span>
    </div>
    <div class="row">
        <span class="col-sm-4 text-right">Email</span>
        <span class="col-sm-8">${review.email}</span>
    </div>
    <br>
    <p class="text-uppercase text-muted"><b>Review details</b></p>
    <div class="row">
        <span class="col-sm-4 text-right">Movie Title</span>
        <span class="col-sm-8">${review.moviename}</span>
    </div>
    <div class="row">
        <span class="col-sm-4 text-right">Director</span>
        <span class="col-sm-8">${review.directorname}</span>
    </div>
    <div class="row">
        <span class="col-sm-4 text-right">Movie Rating</span>
        <span class="col-sm-8">${review.movierating}/10</span>
    </div>
    <div class="row">
        <span class="col-sm-4 text-right">Notes</span>
        <span class="col-sm-8">${review.notes}</span>
    </div>




    <div class="row">
        <span class="col-sm-8">&nbsp;</span>

        <div class="col-sm-4 text-right">
            <div>
                <input type="hidden" name="id" value="${reviewId}"/>
                <button type="button" class="btn btn-primary" onclick="remove()">Delete</button>
                <button type="button" class="btn btn-primary" onclick="edit()">Edit</button>
                <button type="button" class="btn btn-primary" onclick="done()">Done</button>
            </div>
        </div>
    </div>

</body>
</html>
