<%@ page import="hello.servlet.web.domain.MemberRepository" %>
<%@ page import="hello.servlet.web.domain.Member" %><%--
  Created by IntelliJ IDEA.
  User: kitri
  Date: 2023-03-20
  Time: 오후 1:32
  To change this template use File | Settings | File Templates.
--%>

<%--jsp도 결국 서블릿이라 불러 올 수 있음--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  MemberRepository memberRepository = MemberRepository.getInstance();
  String username = request.getParameter("username");
  int age = Integer.parseInt(request.getParameter("age"));

  Member member = Member.builder().username(username).age(age).build();

  memberRepository.save(member);
%>

<html>
<head>
    <title>Title</title>
</head>
<body>

  <ul>
    <li>id <%=member.getId()%> </li>
    <li>username <%=member.getUsername()%></li>
    <li>age<%=member.getAge()%></li>
  </ul>

<a href="/index.html" > 메인으로 가기</a>

</body>
</html>
