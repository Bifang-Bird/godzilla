package com.godzilla.cn.godzilla.service;

import com.godzilla.cn.godzilla.bean.Article;
import com.godzilla.cn.godzilla.bean.JiraTask;

import java.util.List;

public interface JiraTaskService {
    int add(JiraTask jiraTask);
    int deleteTask(long id);
    String getMusicByName(String name);

    JiraTask findJiraTaskById(long id);
    List<JiraTask> findAllList();

}
