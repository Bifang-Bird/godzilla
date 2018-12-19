package com.godzilla.cn.godzilla.utils;

import com.atlassian.jira.rest.client.domain.Issue;
import mjson.Json;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;

public class Jiraoperation {

    /**
     * 获取指定JIRA的UE开始时间
     *
     * @param issue
     * @return
     * @throws URISyntaxException
     */
    public static String get_UE_start_time(Issue issue) throws URISyntaxException {
        try {
            String UE_start_time = issue.getFieldByName("UE开始时间").getValue().toString();
            return UE_start_time;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定JIRA的UE结束时间
     *
     * @param issue
     * @return
     * @throws URISyntaxException
     */
    public static String get_UE_end_time(Issue issue) throws URISyntaxException {
        try {
            String UE_end_time = issue.getFieldByName("UE结束时间").getValue().toString();
            return UE_end_time;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定JIRA的UI开始时间
     *
     * @param issue
     * @return
     * @throws URISyntaxException
     */
    public static String get_UI_start_time(Issue issue) throws URISyntaxException {
        try {
            String UI_start_time = issue.getFieldByName("UI开始时间").getValue().toString();
            return UI_start_time;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定JIRA的UI结束时间
     *
     * @param issue
     * @return
     * @throws URISyntaxException
     */
    public static String get_UI_end_time(Issue issue) throws URISyntaxException {
        try {
            String UI_end_time = issue.getFieldByName("UI结束时间").getValue().toString();
            return UI_end_time;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定JIRA的客户端开始时间
     *
     * @param issue
     * @return
     * @throws URISyntaxException
     */
    public static String get_app_start_time(Issue issue) throws URISyntaxException {
        try {
            String app_start_time = issue.getFieldByName("客户端开始时间").getValue().toString();
            return app_start_time;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定JIRA的客户端结束时间
     *
     * @param issue
     * @return
     * @throws URISyntaxException
     */
    public static String get_app_end_time(Issue issue) throws URISyntaxException {
        try {
            String app_end_time = issue.getFieldByName("客户端结束时间").getValue().toString();
            return app_end_time;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定JIRA的前端开始时间
     *
     * @param issue
     * @return
     * @throws URISyntaxException
     */
    public static String get_qianduan_start_time(Issue issue) throws URISyntaxException {
        try {
            String qianduan_start_time = issue.getFieldByName("前端开始时间").getValue().toString();
            return qianduan_start_time;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定JIRA的前端结束时间
     *
     * @param issue
     * @return
     * @throws URISyntaxException
     */
    public static String get_qianduan_end_time(Issue issue) throws URISyntaxException {
        try {
            String qianduan_end_time = issue.getFieldByName("前端结束时间").getValue().toString();
            return qianduan_end_time;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定JIRA的开发开始时间
     *
     * @param issue
     * @return
     * @throws URISyntaxException
     */
    public static String get_develop_start_time(Issue issue) throws URISyntaxException {
        try {
            String develop_start_time = issue.getFieldByName("开发开始时间").getValue().toString();
            return develop_start_time;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定JIRA的开发结束时间
     *
     * @param issue
     * @return
     * @throws URISyntaxException
     */
    public static String get_develop_end_time(Issue issue) throws URISyntaxException {
        try {
            String develop_end_time = issue.getFieldByName("开发结束时间").getValue().toString();
            return develop_end_time;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定JIRA的测试开始时间
     *
     * @param issue
     * @return
     * @throws URISyntaxException
     */
    public static String get_test_start_time(Issue issue) throws URISyntaxException {
        try {
            String test_start_time = issue.getFieldByName("测试开始时间")==null?"":issue.getFieldByName("测试开始时间").getValue().toString();
            return test_start_time;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定JIRA的分派人的姓名列表
     *
     * @param issue
     * @return
     * @throws URISyntaxException
     */
    public static ArrayList<String> get_assignees(Issue issue) throws URISyntaxException {
        try {
            Json json = Json.read(issue.getFieldByName("分派给").getValue().toString());
            Iterator<Json> assignees = json.asJsonList().iterator();
            ArrayList<String> assigneesNames = new ArrayList<String>();
            while (assignees.hasNext()) {
                Json assignee = Json.read(assignees.next().toString());
                String assigneeName = assignee.at("displayName").toString();
                assigneesNames.add(assigneeName);
            }
            return assigneesNames;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定JIRA的产品人员的姓名
     *
     * @param issue
     * @return
     * @throws URISyntaxException
     */
    public static String get_product(Issue issue) throws URISyntaxException {
        try {
            String product_field = issue.getFieldByName("产品人员").getValue().toString();
            return Json.read(product_field).at("displayName").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
