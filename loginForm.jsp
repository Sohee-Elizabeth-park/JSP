<%--
  Created by IntelliJ IDEA.
  User: soheepark
  Date: 2023/10/17
  Time: 3:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>loginForm</title>
</head>
<body>
<!-- 쿠키값으로 id정보 받아오기 -->
<%
  request.setCharacterEncoding("UTF-8");
  String id = "";
  //쿠키 배열 생성
  Cookie[] cookies = request.getCookies();

  //쿠키가 있다면 쿠키값 가져오기
  if(cookies !=null){
    for(int i=0;i<cookies.length;i++){
      if(cookies[i].getName().equals("id")){ //저장된 쿠키 이름이 "id" >인 경우
        id = cookies[i].getValue(); //쿠키값을 가져옴
        break;
      }
    }
  }
%>
<h1>Login</h1>
<form action="LoginController">
  id: <input name="id" type=text value="<%=id%>" placeholder="your id."><br>
  pwd: <input name="pwd" type=password value="" placeholder="your password."><br>
  <input name="checkbox" type="checkbox" value="true" checked="checked"<%%>/>id 기억하기<br>
  <input type=submit value="로그인">
</form>

</body>
</html>
