<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>管理员添加用户</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/user/regist.css'/>">

<!-- 	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script> -->
<!-- 	<script type="text/javascript" src="<c:url value='/jsps/js/user/regist.js'/>"></script> -->
  </head>
  
  <body>
<div id="divMain">
  <div id="divTitle">
    <span id="spanTitle">管理员添加用户</span>
  </div>
  <div id="divBody">
<form action="<c:url value='/admin/AdminUserServlet'/>" method="post" id="registForm">
	<input type="hidden" name="method" value="editUserById"/>  
	<input type="hidden" name="uid" value="${form.uid} }"/>  
	
    <table id="tableForm">
<!--     disabled="disabled" -->

       <tr>
        <td class="tdText">用户编号：</td>
        <td class="tdInput">
          <input class="inputClass" type="text" name="uid" id="uid" value="${form.uid }" disabled="disabled" />
        </td>
        <td class="tdError">
          <label class="errorClass"></label>
        </td>
      </tr>

      <tr>
        <td class="tdText">用户名：</td>
        <td class="tdInput">
          <input class="inputClass"  type="text" name="loginname" id="loginname" value="${form.loginname }" disabled="disabled"/>
        </td>
        <td class="tdError">
          <label class="errorClass" id="loginnameError">${errors.loginname }</label>
        </td>
      </tr>
      <tr>
        <td class="tdText">登录密码：</td>
        <td>
          <input class="inputClass" type="password" name="loginpass" id="loginpass" value="${form.loginpass }"/>
        </td>
        <td>
          <label class="errorClass" id="loginpassError">${errors.loginpass }</label>
        </td>
      </tr>
        <tr>
        <td class="tdText">Email：</td>
        <td>
          <input class="inputClass" type="text" name="email" id="email" value="${form.email }" disabled="disabled" />
        </td>
        <td>
          <label class="errorClass" id="emailError">${errors.email}</label>
        </td>
      </tr>
      
       <tr>
        <td class="tdText">用户开店状态：</td>
        <td class="tdInput">
          <input class="inputClass" type="text" name="loginname" id="loginname" value="${form.isStore }" disabled="disabled"/>
        </td>
        <td class="tdError">
          <label class="errorClass" ></label>
        </td>
      </tr>
      
       <tr>
        <td></td>
        <td>
        <input type="submit" value="修改用户信息">
<!--           <input type="image" src="<c:url value='/images/addUser2.jpg'/>" id="submitBtn"/> -->
        </td>
        <td>
          <label></label>
        </td>
      </tr>
    </table>
</form>    
  </div>
</div>
  </body>
</html>