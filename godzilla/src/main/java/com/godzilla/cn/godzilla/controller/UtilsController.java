package com.godzilla.cn.godzilla.controller;


import com.godzilla.cn.godzilla.bean.Notes;
import com.godzilla.cn.godzilla.service.NotesService;
import com.godzilla.cn.godzilla.utils.SignUtil;
import com.google.common.hash.Hashing;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/utils")     // 通过这里配置使下面的映射都在/users下，可去除
public class UtilsController {


    @Autowired
    public NotesService notesService;


    @ApiOperation(value="生成hash256", notes="")
    @GetMapping(value="/getHash256")
    public String findNotesByUserId(@RequestParam(value = "queryStr") String queryStr) {

        String path = Hashing.sha256().hashBytes(queryStr.getBytes()).toString();

        return path;
    }

    @ApiOperation(value="生成请求签名", notes="生成请求签名")
    @GetMapping(value="/getSign")
    public String getSign(@RequestParam(value = "queryStr") String queryStr,@RequestParam(value = "clientSecret") String clientSecret) {
        String sign = SignUtil.getSign(queryStr,clientSecret);
        return sign;
    }

}
