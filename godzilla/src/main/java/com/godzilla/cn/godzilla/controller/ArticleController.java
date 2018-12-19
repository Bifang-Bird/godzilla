package com.godzilla.cn.godzilla.controller;


import com.godzilla.cn.godzilla.bean.Article;
import com.godzilla.cn.godzilla.bean.User;
import com.godzilla.cn.godzilla.bean.UserArticle;
import com.godzilla.cn.godzilla.service.ArticleService;
import com.godzilla.cn.godzilla.service.UserArticleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

// 通过这里配置使下面的映射都在/users下，可去除

@RestController
@RequestMapping(value="/article")
@ApiIgnore
public class ArticleController {

    @Autowired
    public ArticleService articleService;

    @Autowired
    public UserArticleService userArticleService;

    @ApiOperation(value="获取文章章节列表", notes="")
    @RequestMapping(value={"/getArticleTitleList"}, method=RequestMethod.GET)
    public List<Article> getArticleTitleList() {
        List<Article> art = new ArrayList<>();
        art = articleService.findArticleTitleList();
        return art;
    }

    @ApiOperation(value="插入文章", notes="根据对象创建用户")
    @ApiImplicitParam(name = "article", value = "详细实体user", required = true, dataType = "Article")
    @RequestMapping(value="/addArticle", method=RequestMethod.POST)
    public String postUser(@RequestBody Article article) {
        articleService.add(article.getTitle(),article.getDetail(),article.getNodes());

        return "success";
    }

    @ApiOperation(value="获取详细信息", notes="根据url的id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long")
    @RequestMapping(value="/getArticle/{id}", method=RequestMethod.GET)
    public Article getArticle(@PathVariable Long id) {
        return articleService.findArticleById(id);
    }

    @ApiOperation(value="更新详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "article", value = "详细实体", required = true, dataType = "Article")
    })
    @RequestMapping(value="/modifyArticle/{id}", method=RequestMethod.PUT)
    public String putArticle(@PathVariable Long id, @RequestBody Article article) {
        Article u = articleService.findArticleById(id);
        u.setTitle(article.getTitle());
        u.setDetail(article.getDetail());
        u.setNodes(article.getNodes());
        articleService.update(u.getTitle(),u.getDetail(),u.getNodes(),u.getId());

        return "success";
    }

    @ApiOperation(value="删除", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long")
    @RequestMapping(value="/deleteArticle/{id}", method=RequestMethod.GET)
    public String deleteArticle(@PathVariable Long id) {
        articleService.delete(id);
        return "success";
    }

    //****************************个人用户，文章的系列操作************************************************//

    @ApiOperation(value="插入用户文章", notes="根据对象创建用户文章")
    @ApiImplicitParam(name = "userArticle", value = "详细实体user", required = true, dataType = "UserArticle")
    @RequestMapping(value="/addUserArticle", method=RequestMethod.POST)
    public String addUserArticle(@RequestBody UserArticle userArticle) {
        userArticleService.add(userArticle.getUserId(),userArticle.getArticleId(),userArticle.getIsRead(),userArticle.getState());
        return "success";
    }


    @ApiOperation(value="更新详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户文章详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userArticle", value = "详细实体", required = true, dataType = "UserArticle")
    })
    @RequestMapping(value="/modifyUserArticle", method=RequestMethod.POST)
    public String modifyUserArticle(@RequestBody UserArticle userArticle) {
        userArticleService.update(userArticle.getUserId(),userArticle.getArticleId(),userArticle.getIsRead(),userArticle.getState(),userArticle.getId());
        return "success";
    }

    @ApiOperation(value="删除", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long")
    @RequestMapping(value="/deleteUserArticle/{id}", method=RequestMethod.GET)
    public String deleteUserArticle(@PathVariable long id) {
        userArticleService.delete(id);
        return "success";
    }

    @ApiOperation(value="获取详细信息", notes="根据url的用户文章id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long")
    @RequestMapping(value="/findAllById/{id}", method=RequestMethod.GET)
    public UserArticle findAllById(@PathVariable long id) {
        return userArticleService.findAllById(id);
    }

    @ApiOperation(value="获取详细信息", notes="根据url的用户id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "long")
    @RequestMapping(value="/findAllByuserId/{id}", method=RequestMethod.GET)
    public List<UserArticle> findAllByuserId(@PathVariable long id) {
        return userArticleService.findAllByuserId(id);
    }


    @ApiOperation(value="更新文章为已读", notes="根据url的id来指定更新对象，更新文章为已读")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long")
    @RequestMapping(value="/modifyRead/{id}", method=RequestMethod.GET)
    public String modifyRead(@PathVariable long id) {
        userArticleService.modifyRead(id);
        return "success";
    }


}
