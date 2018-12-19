package com.godzilla.cn.godzilla.controller;


import com.godzilla.cn.godzilla.bean.Notes;
import com.godzilla.cn.godzilla.bean.User;
import com.godzilla.cn.godzilla.service.NotesService;
import com.godzilla.cn.godzilla.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/notes")
@ApiIgnore
public class NoteController {


    @Autowired
    public NotesService notesService;


    @ApiOperation(value="获取用户的便签列表", notes="")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long")
    @RequestMapping(value={"/findNotesByUserId/{uerId}"}, method=RequestMethod.GET)
    public List<Notes> findNotesByUserId(@PathVariable long uerId) {
        List<Notes> r = new ArrayList<>();
        return r = notesService.findNotesByUserId(uerId);
    }

    @ApiOperation(value="创建用户便签", notes="根据User对象创建用户便签")
    @ApiImplicitParam(name = "note", value = "用户详细实体user", required = true, dataType = "Notes")
    @RequestMapping(value="/addNotes", method=RequestMethod.POST)
    public String addNotes(@RequestBody Notes note) {

        notesService.add(note.getUserId(),note.getNote(),note.getContent(),note.getDate());
        return "success";
    }

    @ApiOperation(value="获取详细信息", notes="根据url的id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long")
    @RequestMapping(value="/findNotesById/{id}", method=RequestMethod.GET)
    public Notes getUser(@PathVariable long id) {
        return notesService.findNotesById(id);
    }


    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "note", value = "用户详细实体user", required = true, dataType = "Notes")
    })
    @RequestMapping(value="/updateNote", method=RequestMethod.POST)
    public String updateNote(@RequestBody Notes note) {
        notesService.update(note.getNote(),note.getContent(),note.getId());
        return "success";
    }

    @ApiOperation(value="删除", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long")
    @RequestMapping(value="/deleteNotes/{id}", method=RequestMethod.POST)
    public String deleteNotes(@PathVariable long id) {
        notesService.delete(id);
        return "success";
    }

}
