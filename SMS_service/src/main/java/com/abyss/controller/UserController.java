package com.abyss.controller;

import com.abyss.pojo.EasyUIResult;
import com.abyss.pojo.User;
import com.abyss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Abyss on 2018/5/30.
 * description:
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("users")
    public String toUsers() {
        return "users";
    }

//    @RequestMapping("page/user-add")
//    public String toPage() {
//        return "user-add";
//    }

    @RequestMapping("page/{pageName}")
    public String toPage(@PathVariable("pageName") String pageName) {
        return pageName;
    }

    @RequestMapping("save")
    @ResponseBody
    public Map<String, Integer> saveUser(User user) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("status", userService.addUser(user) ? 200 : 500);
        return map;
    }


    @RequestMapping("list")
    @ResponseBody
    public EasyUIResult queryUserList(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows) {
        return userService.queryUserList(page, rows);
    }

    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Integer> deleteUser(Long[] ids) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("status", userService.deleteByIds(ids) ? 200 : 500);
        return map;
    }

    @RequestMapping("export/excel")
    public String exportExcel(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows,Model model) {
        EasyUIResult easyUIResult = userService.queryUserList(page, rows);
        model.addAttribute("userList", easyUIResult.getRows());
        return "userExcelView";
    }

}
