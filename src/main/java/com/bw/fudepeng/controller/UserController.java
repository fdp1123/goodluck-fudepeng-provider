package com.bw.fudepeng.controller;

import com.bw.fudepeng.entity.User;
import com.bw.fudepeng.repository.UserRep;
import com.bw.fudepeng.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

import static ognl.DynamicSubscript.all;

/**
 * Created by fudepeng on 2017/7/28.
 * CONTROLLER
 */
@Controller
public class UserController {

    @Autowired
    private UserRep userRep;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 跳转到登录页面toLogin
     */
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    /**
     * 登录login
     */
    @RequestMapping("/login")
    public String login(String username, String pwd, HttpSession session) {
        System.out.println("login:======");
        String mpwd = MD5Util.MD5(pwd);
        System.out.println(mpwd);
        User userByUsernameAndPwd = userRep.findUserByUsernameAndPwd(username, mpwd);
        if (userByUsernameAndPwd != null) {
            session.setAttribute("userLogin", userByUsernameAndPwd);
            return "forward:findAll.action";
        }

        return "login";


    }

    /**
     * 注册register
     */
    @RequestMapping("/register")
    public String register(User user) {
        System.out.println("register:======");
        String pwd = user.getPwd();
        //调用MD5进行加密
        String mpwd = MD5Util.MD5(pwd);
        user.setPwd(mpwd);
        System.out.println("加密后的密码为:" + mpwd);//6of4420aoM44o32sF0341s53so5sM43s
        userRep.save(user);
        return "login";
    }

    /**
     * 查询findAll
     */
    @RequestMapping("/findAll")
    public String findAll(Model model) {
        System.out.println("findAll:======");
        User user = null;
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        Boolean exists = redisTemplate.hasKey("user");
        if (exists) {
            user = operations.get("user");
            System.out.println("exists is true" + user.getUsername());
        } else {
            List<User> all = userRep.findAll();
            model.addAttribute("all", all);
            return "showlist";
        }
        return "showlist";
    }

    /**
     * 修改update
     */
    @RequestMapping("/update")
    public String update(User user) {
        System.out.println("update:======");
        userRep.save(user);
        return "forward:findAll.action";
    }

    /**
     * 根据id查询
     */
    @RequestMapping("/findUserById")
    public String findUserById(Model model, Integer id) {
        System.out.println("findUserById:======");
        User userById = userRep.findUserById(id);
        model.addAttribute("userById", userById);
        return "toUpdate";
    }

    /**
     * 删除delete
     */
    @RequestMapping("/delete")
    public String delete(Integer id) {
        System.out.println("delete:======");
        userRep.delete(id);
        return "forward:findAll.action";
    }

}