<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户管理</title>
</head>
<body>
    <h1>用户列表</h1>

    <div>
        <h3>新增用户</h3>
        <input type="text" id="name" placeholder="姓名">
        <input type="number" id="age" placeholder="年龄">
        <button onclick="addUser()">新增</button>
    </div>

    <hr>

    <div>
        <h3>修改用户</h3>
        <input type="text" id="updateId" placeholder="用户ID">
        <input type="text" id="updateName" placeholder="姓名">
        <input type="number" id="updateAge" placeholder="年龄">
        <button onclick="updateUser()">修改</button>
    </div>

    <hr>

    <div>
        <h3>删除用户</h3>
        <input type="text" id="deleteId" placeholder="用户ID">
        <button onclick="deleteUser()">删除</button>
    </div>

    <hr>

    <div>
        <h3>查询单个用户</h3>
        <input type="text" id="getId" placeholder="用户ID">
        <button onclick="getUser()">查询</button>
        <p id="getResult"></p>
    </div>

    <hr>

    <div>
        <h3>所有用户</h3>
        <button onclick="loadList()">刷新列表</button>
        <div id="listResult"></div>
    </div>

    <script>
        // 获取项目路径
        const ctx = "${pageContext.request.contextPath}";

        // 新增
        function addUser() {
            const name = document.getElementById("name").value;
            const age = document.getElementById("age").value;

            fetch(ctx + "/user/add", {
                method: "POST",
                headers: {"Content-Type": "application/x-www-form-urlencoded"},
                body: "name=" + encodeURIComponent(name) + "&age=" + age
            })
            .then(res => res.json())
            .then(data => {
                alert(data.msg);
                if (data.code === 200) loadList();
            });
        }

        // 修改
        function updateUser() {
            const id = document.getElementById("updateId").value;
            const name = document.getElementById("updateName").value;
            const age = document.getElementById("updateAge").value;

            fetch(ctx + "/user/update", {
                method: "PUT",
                headers: {"Content-Type": "application/x-www-form-urlencoded"},
                body: "id=" + id + "&name=" + encodeURIComponent(name) + "&age=" + age
            })
            .then(res => res.json())
            .then(data => {
                alert(data.msg);
                if (data.code === 200) loadList();
            });
        }

        // 删除
        function deleteUser() {
            const id = document.getElementById("deleteId").value;

            fetch(ctx + "/user/delete/" + id, {
                method: "DELETE"
            })
            .then(res => res.json())
            .then(data => {
                alert(data.msg);
                if (data.code === 200) loadList();
            });
        }

        // 查询单个
        function getUser() {
            const id = document.getElementById("getId").value;

            fetch(ctx + "/user/get/" + id)
            .then(res => res.json())
            .then(data => {
                if (data.code === 200) {
                    document.getElementById("getResult").innerHTML =
                        "ID: " + data.data.id + "，姓名: " + data.data.name + "，年龄: " + data.data.age;
                } else {
                    document.getElementById("getResult").innerHTML = data.msg;
                }
            });
        }

        // 查询所有
        function loadList() {
            fetch(ctx + "/user/list")
            .then(res => res.json())
            .then(data => {
                if (data.code === 200) {
                    let html = "<table border='1' cellpadding='5'>";
                    html += "<tr><th>ID</th><th>姓名</th><th>年龄</th></tr>";
                    for (let i = 0; i < data.data.length; i++) {
                        html += "<tr>";
                        html += "<td>" + data.data[i].id + "</td>";
                        html += "<td>" + data.data[i].name + "</td>";
                        html += "<td>" + data.data[i].age + "</td>";
                        html += "</tr>";
                    }
                    html += "</table>";
                    document.getElementById("listResult").innerHTML = html;
                }
            });
        }


        loadList();
    </script>
</body>
</html>