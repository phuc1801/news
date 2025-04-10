package com.vnnet.newsconsole.controller;


import com.vnnet.newscommon.bean.HttpResult;
import com.vnnet.newscommon.model.SysUser;
import com.vnnet.newscommon.model.SysUserExample;
import com.vnnet.newscommon.persistence.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private SysUserMapper userMapper;



    @GetMapping(value = "/showhai")
    public String chuoi(){
        return "ok";
    }

    @GetMapping("/show")
    public HttpResult getAllUsers() {
        List<SysUser> users = userMapper.selectByExample(new SysUserExample());
        return HttpResult.ok(users);
    }


    @GetMapping("/findid/{id}")
    public HttpResult getUser(@PathVariable Integer id) {
        SysUser user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return HttpResult.error(404, "Không tìm thấy người dùng");
        }
        return HttpResult.ok("tem thay nguoi dung");
    }


    // Thêm người dùng mới
    @PostMapping("/add")
    public HttpResult createUser(@RequestBody SysUser user) {
        int rows = userMapper.insertSelective(user);
        if (rows > 0) {
            return HttpResult.ok(user);
        } else {
            return HttpResult.error("Thêm người dùng thất bại!");
        }
    }


    @PutMapping("/update/{id}")
    public HttpResult updateUser(@PathVariable Integer id, @RequestBody SysUser user) {
        user.setId(id); // Đảm bảo ID được gán đúng
        int rows = userMapper.updateByPrimaryKeySelective(user);
        if (rows > 0) {
            SysUser updatedUser = userMapper.selectByPrimaryKey(id);
            return HttpResult.ok(updatedUser);
        } else {
            return HttpResult.error("Cập nhật thất bại!");
        }
    }


    @DeleteMapping("/delete/{id}")
    public HttpResult deleteUser(@PathVariable Integer id) {
        int rows = userMapper.deleteByPrimaryKey(id);
        return rows > 0
                ? HttpResult.ok("Xóa thành công!")
                : HttpResult.error(404, "Không tìm thấy người dùng!");
    }




}
