package com.godzilla.cn.godzilla.model;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class JiraInfoModel {
    List<String> jiraCommentsBody;
    DateTime jiraCreateTime;
    String description;
    String reActions;
    String summary;
    String reporter;
    ArrayList<String> assignees;
    String status;
    String issueType;
    ArrayList<String> modules;
    ArrayList<String> qianduans;
    String developers;
    String product;
    String start_develop_time;
    String UE_start_time;
    String UE_end_time;
    String UI_start_time;
    String UI_end_time;
    String app_start_time;
    String app_end_time;
    String qianduan_start_time;
    String qianduan_end_time;
    String develop_start_time;
    String develop_end_time;
    String cause_personnel;
    String repair_details;
    String test_start_time;
    String test_end_time;

    public List<String> getJiraCommentsBody() {
        return jiraCommentsBody;
    }

    public void setJiraCommentsBody(List<String> jiraCommentsBody) {
        this.jiraCommentsBody = jiraCommentsBody;
    }

    public DateTime getJiraCreateTime() {
        return jiraCreateTime;
    }

    public void setJiraCreateTime(DateTime jiraCreateTime) {
        this.jiraCreateTime = jiraCreateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReActions() {
        return reActions;
    }

    public void setReActions(String reActions) {
        this.reActions = reActions;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public ArrayList<String> getAssignees() {
        return assignees;
    }

    public void setAssignees(ArrayList<String> assignees) {
        this.assignees = assignees;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public ArrayList<String> getModules() {
        return modules;
    }

    public void setModules(ArrayList<String> modules) {
        this.modules = modules;
    }

    public ArrayList<String> getQianduans() {
        return qianduans;
    }

    public void setQianduans(ArrayList<String> qianduans) {
        this.qianduans = qianduans;
    }

    public String getDevelopers() {
        return developers;
    }

    public void setDevelopers(String developers) {
        this.developers = developers;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getStart_develop_time() {
        return start_develop_time;
    }

    public void setStart_develop_time(String start_develop_time) {
        this.start_develop_time = start_develop_time;
    }

    public String getUE_start_time() {
        return UE_start_time;
    }

    public void setUE_start_time(String uE_start_time) {
        UE_start_time = uE_start_time;
    }

    public String getUE_end_time() {
        return UE_end_time;
    }

    public void setUE_end_time(String uE_end_time) {
        UE_end_time = uE_end_time;
    }

    public String getUI_start_time() {
        return UI_start_time;
    }

    public void setUI_start_time(String uI_start_time) {
        UI_start_time = uI_start_time;
    }

    public String getUI_end_time() {
        return UI_end_time;
    }

    public void setUI_end_time(String uI_end_time) {
        UI_end_time = uI_end_time;
    }

    public String getApp_start_time() {
        return app_start_time;
    }

    public void setApp_start_time(String app_start_time) {
        this.app_start_time = app_start_time;
    }

    public String getApp_end_time() {
        return app_end_time;
    }

    public void setApp_end_time(String app_end_time) {
        this.app_end_time = app_end_time;
    }

    public String getQianduan_start_time() {
        return qianduan_start_time;
    }

    public void setQianduan_start_time(String qianduan_start_time) {
        this.qianduan_start_time = qianduan_start_time;
    }

    public String getQianduan_end_time() {
        return qianduan_end_time;
    }

    public void setQianduan_end_time(String qianduan_end_time) {
        this.qianduan_end_time = qianduan_end_time;
    }

    public String getDevelop_start_time() {
        return develop_start_time;
    }

    public void setDevelop_start_time(String develop_start_time) {
        this.develop_start_time = develop_start_time;
    }

    public String getDevelop_end_time() {
        return develop_end_time;
    }

    public void setDevelop_end_time(String develop_end_time) {
        this.develop_end_time = develop_end_time;
    }

    public String getCause_personnel() {
        return cause_personnel;
    }

    public void setCause_personnel(String cause_personnel) {
        this.cause_personnel = cause_personnel;
    }

    public String getRepair_details() {
        return repair_details;
    }

    public void setRepair_details(String repair_details) {
        this.repair_details = repair_details;
    }

    public String getTest_start_time() {
        return test_start_time;
    }

    public void setTest_start_time(String test_start_time) {
        this.test_start_time = test_start_time;
    }

    public String getTest_end_time() {
        return test_end_time;
    }

    public void setTest_end_time(String test_end_time) {
        this.test_end_time = test_end_time;
    }
}
