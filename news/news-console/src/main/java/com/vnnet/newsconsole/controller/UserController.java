package com.vnnet.newsconsole.controller;


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

    // Lấy danh sách tất cả người dùng
    @GetMapping("/show")
    public List<SysUser> getAllUsers() {
        return userMapper.selectByExample(new SysUserExample());
    }

    // Tìm người dùng theo ID
    @GetMapping("/{id}")
    public SysUser getUserById(@PathVariable Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    // Thêm người dùng mới
    @PostMapping
    public String createUser(@RequestBody SysUser user) {
        int rows = userMapper.insertSelective(user);
        return rows > 0 ? "Thêm người dùng thành công!" : "Thêm thất bại!";
    }

    // Sửa người dùng
    @PutMapping("/{id}")
    public String updateUser(@PathVariable Integer id, @RequestBody SysUser user) {
        user.setId(id); // Đảm bảo ID được gán đúng
        int rows = userMapper.updateByPrimaryKeySelective(user);
        return rows > 0 ? "Cập nhật thành công!" : "Cập nhật thất bại!";
    }

    // Xóa người dùng
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        int rows = userMapper.deleteByPrimaryKey(id);
        return rows > 0 ? "Xóa thành công!" : "Không tìm thấy người dùng!";
    }
}
