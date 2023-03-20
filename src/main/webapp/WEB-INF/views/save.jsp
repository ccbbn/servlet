<%--jsp도 결국 서블릿이라 불러 올 수 있음--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
  <title>Title</title>
</head>
<body>

<ul>
  <li>id ${member.id} ></li>
  <li>username ${member.username}</li>
  <li>age ${member.age}</li>
</ul>

<a href="/index.html" > 메인으로 가기</a>

</body>
</html>