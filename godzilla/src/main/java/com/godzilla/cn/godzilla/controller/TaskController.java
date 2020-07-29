package com.godzilla.cn.godzilla.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.godzilla.cn.godzilla.bean.JiraTask;
import com.godzilla.cn.godzilla.bean.Notes;
import com.godzilla.cn.godzilla.bean.RequestDto;
import com.godzilla.cn.godzilla.bean.Result;
import com.godzilla.cn.godzilla.service.JiraTaskService;
import com.godzilla.cn.godzilla.service.NotesService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/tasks")
//@ApiIgnore
public class TaskController {


    @Autowired
    public JiraTaskService jiraTaskService;




    @ApiOperation(value="创建任务", notes="")
    @ApiImplicitParam(name = "task", value = "", required = true, dataType = "JiraTask")
    @RequestMapping(value="/addTask", method=RequestMethod.POST)
    public String addTask(@Validated @RequestBody JiraTask task , BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return null;
        }
        jiraTaskService.add(task);
        Result result = new Result();
        result.setCode(0);
        result.setMessage("success");
        return JSON.toJSONString(result);
    }

    @ApiOperation(value="获取task信息", notes="根据id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long")
    @RequestMapping(value="/findJiraTaskById/{id}", method=RequestMethod.GET)
    public JiraTask findJiraTaskById(@PathVariable long id) {
        return jiraTaskService.findJiraTaskById(id);
    }


    @ApiOperation(value="获取所有task", notes="")
    @RequestMapping(value="/findAllTasks", method=RequestMethod.GET)
    public String findAllTasks() {
        List<JiraTask> jiraTaskList = jiraTaskService.findAllList();

        Result result = new Result();
        result.setCode(0);
        result.setNum(jiraTaskList.size());
        result.setMessage("success");
        result.setData(jiraTaskList);
        return JSON.toJSONString(result);
    }

    @ApiOperation(value="删除task", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long")
    @RequestMapping(value="/deleteTask/{id}", method=RequestMethod.POST)
    public String deleteTask(@PathVariable long id) {
        jiraTaskService.deleteTask(id);
        Result result = new Result();
        result.setCode(0);
        result.setMessage("success");
        return JSON.toJSONString(result);
    }

    @ApiOperation(value="获取音乐", notes="查找音乐")
    @ApiImplicitParam(name = "requestDto", value = "查询语句", required = true, dataType = "RequestDto")
    @RequestMapping(value="/getMusicByName", method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
    public String getMusicByName(@RequestBody RequestDto requestDto) {
        String url = "https://api.apiopen.top/searchMusic?name="+requestDto.getContent();
        String rltContent = jiraTaskService.getMusicByName(url);

        JSONObject jsonObject = (JSONObject) JSON.parse(rltContent);

        Result result = new Result();
        result.setCode(0);
        result.setMessage("success");
        result.setData(jsonObject);
        return JSON.toJSONString(result);
    }

}
