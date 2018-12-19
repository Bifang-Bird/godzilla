package com.godzilla.cn.godzilla.controller;


import com.godzilla.cn.godzilla.bean.User;
import com.godzilla.cn.godzilla.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;
// 通过这里配置使下面的映射都在/users下，可去除

@RestController
@RequestMapping(value="/users")
@ApiIgnore
public class UserController {


    @Autowired
    public UserService userService;


    @ApiOperation(value="获取用户列表", notes="")
    @RequestMapping(value={"/getUserList"}, method=RequestMethod.GET)
    public List<User> getUserList() {
        List<User> r = new ArrayList<>();
        return userService.findUserList();
    }

    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value="/postUser", method=RequestMethod.POST)
    public String postUser(@RequestBody User user) {
        userService.add(user.getCount(),user.getName(),user.getPassword());
        return "success";
    }

    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="/findUserById/{id}", method=RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @ApiOperation(value="获取用户详细信息", notes="根据url的count和password来获取用户详细信息")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value="/getUserByCountAndPassword", method=RequestMethod.POST)
    public User getUserByCountAndPassword(@RequestBody User user) {
        User use = userService.fingUserbyCountAndPassword(user.getCount(),user.getPassword());
        return use;
    }


    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value="/putUser/{id}", method=RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @RequestBody User user) {
        userService.update(user.getCount(),user.getName(),user.getPassword(),id);
        return "success";
    }

    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value="/deleteUser/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "success";
    }

}
