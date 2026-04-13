<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户列表</title>
    <style>
        table { border-collapse: collapse; width: 80%; margin-top: 20px; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: center; }
        th { background-color: #f2f2f2; }
        .search-box { margin: 20px 0; }
        .search-box input, .search-box button { padding: 5px; margin-right: 10px; }
    </style>
</head>
<body>
    <h1>用户列表</h1>

    <!-- 搜索表单 -->
    <div class="search-box">
        <form action="/user/search" method="get">
            姓名：<input type="text" name="name" value="${name}" placeholder="请输入姓名">
            年龄：<input type="text" name="age" value="${age}" placeholder="请输入年龄">
            最小金额：<input type="text" name="minMoney" value="${minMoney}" placeholder="≥">
            最大金额：<input type="text" name="maxMoney" value="${maxMoney}" placeholder="≤">
            <button type="submit">搜索</button>
            <a href="/user/list">重置</a>
        </form>
    </div>

    <a href="/user/toAdd">添加用户</a>

    <table>
        <tr>
            <th>ID</th>
            <th>姓名</th>
            <th>年龄</th>
            <th>金额</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.age}</td>
                <td>${user.money}</td>
                <td>
                    <a href="/user/detail/${user.id}">详情</a>
                    <a href="/user/toEdit/${user.id}">修改</a>
                    <a href="/user/delete/${user.id}" onclick="return confirm('确定删除吗？')">删除</a>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty users}">
            <tr>
                <td colspan="5" style="text-align:center">暂无数据</td>
            </tr>
        </c:if>
    </table>
</body>
</html>