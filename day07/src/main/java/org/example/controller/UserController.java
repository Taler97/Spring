package org.example.controller;

import org.example.entity.User;
import org.example.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // ========== 查询操作 ==========

    /**
     * 列表页（查询所有）
     * GET /user/list
     */
    @GetMapping("/list")
    public String list(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user/list";
    }

    /**
     * 多条件搜索
     * GET /user/search?name=张&age=25&minMoney=1000&maxMoney=5000
     */
    @GetMapping("/search")
    public String search(@RequestParam(required = false) String name,
                         @RequestParam(required = false) Integer age,
                         @RequestParam(required = false) Double minMoney,
                         @RequestParam(required = false) Double maxMoney,
                         Model model) {
        List<User> users = userService.searchUsers(name, age, minMoney, maxMoney);
        model.addAttribute("users", users);
        // 回显搜索条件
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        model.addAttribute("minMoney", minMoney);
        model.addAttribute("maxMoney", maxMoney);
        return "user/list";
    }



    // ========== 新增操作 ==========

    /**
     * 跳转到添加页面
     * GET /user/toAdd
     */
    @GetMapping("/toAdd")
    public String toAdd() {
        return "user/add";
    }

    /**
     * 执行添加
     * POST /user/add
     */
    @PostMapping("/add")
    public String add(User user) {
        userService.addUser(user);
        return "redirect:/user/list";
    }

    // ========== 修改操作 ==========



    /**
     * 执行修改
     * POST /user/update
     */
    @PostMapping("/update")
    public String update(User user) {
        userService.updateUser(user);
        return "redirect:/user/list";
    }

    // ========== 删除操作 ==========

    /**
     * 执行删除
     * GET /user/delete/{id}
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "redirect:/user/list";
    }
}