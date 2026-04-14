package org.example.controller;


import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private Map<String, Object> success(Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "success");
        result.put("data", data);
        return result;
    }

    @GetMapping("/page")
    public String page() {
        return "user/list";  // 对应 /WEB-INF/views/user/list.jsp
    }
    @PostMapping("/add")
    @ResponseBody
    public Map<String, Object> add(User user) {
        return success(userService.add(user));
    }

    @PutMapping("/update")
    @ResponseBody
    public Map<String, Object> update(User user) {
        return success(userService.update(user));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable Integer id) {
        return success(userService.delete(id));
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public Map<String, Object> get(@PathVariable Integer id) {
        return success(userService.get(id));
    }

    @GetMapping("/list")
    @ResponseBody
    public Map<String, Object> list() {
        return success(userService.list());
    }
}