package com.godzilla.cn.godzilla.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.NullProgressMonitor;
import com.atlassian.jira.rest.client.domain.*;
import com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClientFactory;
import com.godzilla.cn.godzilla.bean.JiraTask;
import com.godzilla.cn.godzilla.component.Timegrabbing;
import com.godzilla.cn.godzilla.model.JiraInfoModel;
import com.godzilla.cn.godzilla.service.JiraTaskService;
import com.godzilla.cn.godzilla.service.serviceImp.JiraTaskServiceImp;
import mjson.Json;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JiraUtil {

    private final static String jiraPath = "http://jira.iflytek.com";
    private final static String USERNAME = "junwang33";
    private final static String PSWD = "WJ--lyp82nlf";
    Pattern p = Pattern.compile("\\s*|\t|\r|\n");

    @Autowired
    public JiraTaskService jiraTaskService;

    /**
     * 登录JIRA并返回指定的JiraRestClient对象
     *
     * @param username
     * @param password
     * @return
     * @throws URISyntaxException
     */
    public static JiraRestClient login_jira(String username, String password) throws URISyntaxException {
        try {
            final JerseyJiraRestClientFactory factory = new JerseyJiraRestClientFactory();
            final URI jiraServerUri = new URI(jiraPath);
            final JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, username,
                    password);
            return restClient;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取并返回指定的Issue对象
     *
     * @param issueNum
     * @param username
     * @param password
     * @return
     * @throws URISyntaxException
     */
    public static Issue get_issue(String issueNum, String username, String password) throws URISyntaxException {
        try {
            final JiraRestClient restClient = login_jira(username, password);
            final NullProgressMonitor pm = new NullProgressMonitor();
            final Issue issue = restClient.getIssueClient().getIssue(issueNum, pm);

            return issue;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定JIRA备注部分的内容
     *
     * @param issue
     * @return
     * @throws URISyntaxException
     */
    public static List<String> get_comments_body(Issue issue) throws URISyntaxException {
        try {
            List<String> comments = new ArrayList<String>();
            for (Comment comment : issue.getComments()) {
                comments.add(comment.getBody().toString());
            }
            return comments;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定JIRA的创建时间
     *
     * @return
     * @throws URISyntaxException
     */
    public static DateTime get_create_time(Issue issue) throws URISyntaxException {
        try {
            return issue.getCreationDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定JIRA的描述部分
     *
     * @return
     * @throws URISyntaxException
     */
    public static String get_description(Issue issue) throws URISyntaxException {
        try {
            return issue.getDescription();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定JIRA的标题
     *
     * @return
     * @throws URISyntaxException
     */
    public static String get_summary(Issue issue) throws URISyntaxException {
        try {
            return issue.getSummary();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定JIRA的报告人的名字
     *
     * @return
     * @throws URISyntaxException
     */
    public static String get_reporter(Issue issue) throws URISyntaxException {
        try {
            return issue.getReporter().getDisplayName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取指定JIRA的状态
     *
     * @param issue
     * @return
     * @throws URISyntaxException
     */
    public static String get_status(Issue issue) throws URISyntaxException {
        try {
            return issue.getStatus().getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定JIRA的类型
     *
     * @param issue
     * @return
     * @throws URISyntaxException
     */
    public static String get_issue_type(Issue issue) throws URISyntaxException {
        try {
            return issue.getIssueType().getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定JIRA的模块
     *
     * @param issue
     * @return
     * @throws URISyntaxException
     */
    public static ArrayList<String> get_modules(Issue issue) throws URISyntaxException {
        try {
            ArrayList<String> arrayList = new ArrayList<String>();
            Iterator<BasicComponent> basicComponents = issue.getComponents().iterator();
            while (basicComponents.hasNext()) {
                String moduleName = basicComponents.next().getName();
                arrayList.add(moduleName);
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定JIRA的前端人员的姓名列表
     *
     * @param issue
     * @return
     * @throws URISyntaxException
     */
    public static ArrayList<String> get_qianduans(Issue issue) throws URISyntaxException {
        try {
            ArrayList<String> qianduanList = new ArrayList<String>();
            Json json = Json.read(issue.getFieldByName("前端").getValue().toString());
            Iterator<Json> qianduans = json.asJsonList().iterator();
            while (qianduans.hasNext()) {
                Json qianduan = Json.read(qianduans.next().toString());
                String qianduanName = qianduan.at("displayName").toString();
                qianduanList.add(qianduanName);
            }
            return qianduanList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定JIRA的开发的姓名列表
     *
     * @param issue
     * @return
     * @throws URISyntaxException
     */
    public static String get_developers(Issue issue) throws URISyntaxException {
        String developerName = "", name = "";
        try {
            Json json = Json.read(issue.getFieldByName("责任开发").getValue().toString());
            developerName = json.at("displayName").toString();
            name = json.at("name").toString();

            return developerName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取指定JIRA的重现步骤
     *
     * @param issue
     * @return
     * @throws URISyntaxException
     */
    public static String get_actions(Issue issue) throws URISyntaxException {

        try {
            ArrayList<String> developersList = new ArrayList<String>();
//            Object ogj = issue.getFieldByName("重现步骤");
            String strValue = issue.getField("customfield_10102").getValue().toString();
            return strValue;

//            Iterable<Field> fields = issue.getFields();
//            List<Map<String,String>> lists = new ArrayList<>();
//            for(Field field:fields){
//                String str = field.getName().toString();
//                if(str.contains("重现步骤")){
//                    String strValue = field.getValue()==null?"":field.getValue().toString();
//                    Map map = new HashMap();
//                    map.put(str,strValue);
//                    lists.add(map);
//                    developersList.add(strValue);
//                }
//            }
//            return developersList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取指定JIRA的引起人员
     *
     * @param issue
     * @return
     * @throws URISyntaxException
     */
    public static String cause_personnel(Issue issue) throws URISyntaxException {
        String developerName = "", name = "";
        try {
            Json json = Json.read(issue.getFieldByName("引起人员").getValue().toString());
            developerName = json.at("displayName").toString();
            name = json.at("name").toString();

            return developerName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定JIRA的修复细节
     *
     * @param issue
     * @return
     * @throws URISyntaxException
     */
    public static String get_repair_details(Issue issue) throws URISyntaxException {

        try {
            ArrayList<String> developersList = new ArrayList<String>();
            Object ogj = issue.getFieldByName("修复细节");
            Iterable<Field> fields = issue.getFields();
            List<Map<String, String>> lists = new ArrayList<>();
            for (Field field : fields) {
                String str = field.getName().toString();
                if (str.contains("修复细节")) {
                    String strValue = field.getValue() == null ? "" : field.getValue().toString();
                    Map map = new HashMap();
                    map.put(str, strValue);
                    lists.add(map);
                    developersList.add(strValue);
                }
            }
            return developersList.get(developersList.size() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取指定JIRA的测试结束时间
     *
     * @param issue
     * @return
     * @throws URISyntaxException
     */
    public static String get_test_end_time(Issue issue) throws URISyntaxException {
        try {
            String test_end_time = issue.getFieldByName("测试结束时间")==null?"":issue.getFieldByName("测试结束时间").getValue().toString();
            return test_end_time;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取所有可以收集到的JIRA信息并返回JiraInfoModel类型对象
     *
     * @param issue
     * @return
     * @throws URISyntaxException
     */
    public static JiraInfoModel get_jira_info(Issue issue) throws URISyntaxException {
        List<String> jiraCommentsBody = get_comments_body(issue);
        DateTime jiraCreateTime = get_create_time(issue);
        String description = get_description(issue);
        String summary = get_summary(issue);
        String reporter = get_reporter(issue);
        String status = get_status(issue);
        String issueType = get_issue_type(issue);
        ArrayList<String> modules = get_modules(issue);
        String developers = get_developers(issue);
//        ArrayList<String> qianduans = get_qianduans(issue);
//        ArrayList<String> assignees = get_assignees(issue);
//        String product = get_product(issue);
//        String UE_start_time = Jiraoperation.get_UE_start_time(issue);
//        String UE_end_time = Jiraoperation.get_UE_end_time(issue);
//        String UI_start_time = Jiraoperation.get_UI_start_time(issue);
//        String UI_end_time = Jiraoperation.get_UI_end_time(issue);
//        String app_start_time = Jiraoperation.get_app_start_time(issue);
//        String app_end_time = Jiraoperation.get_app_end_time(issue);
//        String qianduan_start_time = Jiraoperation.get_qianduan_start_time(issue);
//        String qianduan_end_time = Jiraoperation.get_qianduan_end_time(issue);
//        String develop_start_time = Jiraoperation.get_develop_start_time(issue);
//        String develop_end_time = Jiraoperation.get_develop_end_time(issue);
        String cause_personnel = cause_personnel(issue);
        String repair_details = get_repair_details(issue);
        String test_start_time = Jiraoperation.get_test_start_time(issue);
        String test_end_time = get_test_end_time(issue);
        String re_actions = get_actions(issue);
        JiraInfoModel jiraInfoModel = new JiraInfoModel();
        jiraInfoModel.setJiraCommentsBody(jiraCommentsBody);
        jiraInfoModel.setJiraCreateTime(jiraCreateTime);
        jiraInfoModel.setDescription(description);
        jiraInfoModel.setReActions(re_actions);
        jiraInfoModel.setSummary(summary);
        jiraInfoModel.setReporter(reporter);
        jiraInfoModel.setStatus(status);
        jiraInfoModel.setIssueType(issueType);
        jiraInfoModel.setModules(modules);
        jiraInfoModel.setDevelopers(developers);
//        jiraInfoModel.setQianduans(qianduans);
//        jiraInfoModel.setProduct(product);
//        jiraInfoModel.setAssignees(assignees);
//        jiraInfoModel.setUE_start_time(UE_start_time);
//        jiraInfoModel.setUE_end_time(UE_end_time);
//        jiraInfoModel.setUI_start_time(UI_start_time);
//        jiraInfoModel.setUI_end_time(UI_end_time);
//        jiraInfoModel.setApp_start_time(app_start_time);
//        jiraInfoModel.setApp_end_time(app_end_time);
//        jiraInfoModel.setQianduan_start_time(qianduan_start_time);
//        jiraInfoModel.setQianduan_end_time(qianduan_end_time);
//        jiraInfoModel.setDevelop_start_time(develop_start_time);
//        jiraInfoModel.setDevelop_end_time(develop_end_time);
        jiraInfoModel.setCause_personnel(cause_personnel);
        jiraInfoModel.setRepair_details(repair_details);
        jiraInfoModel.setTest_start_time(test_start_time);
        jiraInfoModel.setTest_end_time(test_end_time);
        return jiraInfoModel;
    }

    /**
     * 通过jql语句进行查询并返回一个包含issue的key的数组
     *
     * @param username          登录JIRA的用户名
     * @param password          登录JIRA的用户密码
     * @param current_user_name 要查询的用户名
     * @param jql
     * @return
     * @throws URISyntaxException
     */
    public static ArrayList<String> search_jql(String username, String password, String current_user_name, String jql)
            throws URISyntaxException {
        final JiraRestClient restClient = login_jira(username, password);
        final NullProgressMonitor pm = new NullProgressMonitor();
        SearchResult searchResult = restClient.getSearchClient().searchJql(jql, pm);
        Iterator<BasicIssue> basicIssues = searchResult.getIssues().iterator();
        ArrayList<String> issueKeys = new ArrayList<>();
        while (basicIssues.hasNext()) {
            String issueKey = basicIssues.next().getKey();
            System.out.println(issueKey);
            issueKeys.add(issueKey);
        }
        return issueKeys;
    }

    /**
     * 测试函数
     *
     * @param
     * @throws URISyntaxException
     */

    //定义一个按时间执行的定时任务，在每天1:00执行一次。
    // @Scheduled(cron = "0 0 1* * ?")
    //"0 0 12 * * ?"    每天中午十二点触发
    //"0 15 10 ? * *"    每天早上10：15触发
    //"0 15 10 * * ?"    每天早上10：15触发
    //"0 15 10 * * ? *"    每天早上10：15触发
    //"0 15 10 * * ? 2005"    2005年的每天早上10：15触发
    //"0 * 14 * * ?"    每天从下午2点开始到2点59分每分钟一次触发
    //"0 0/5 14 * * ?"    每天从下午2点开始到2：55分结束每5分钟一次触发
    //"0 0/5 14,18 * * ?"    每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发
    //"0 0-5 14 * * ?"    每天14:00至14:05每分钟一次触发
    //"0 10,44 14 ? 3 WED"    三月的每周三的14：10和14：44触发
    //"0 15 10 ? * MON-FRI"    每个周一、周二、周三、周四、周五的10：15触发
    @Scheduled(fixedRate = 60 * 60 * 1000, initialDelay = 1000*10)
    public void start() throws URISyntaxException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String preMonth = preMonth();
        String curMonth = dateFormat.format(new Date());
        System.out.println("init : " + dateFormat.format(new Date()));
        //查询一段时间内任务
        String taskSql = "project = DIAGNOSIS AND issuetype = 任务 AND resolved >= "+preMonth+" AND resolved <= "+curMonth+" AND assignee in (junwang33)";
        String issueSql = "project = DIAGNOSIS AND issuetype = 缺陷 AND resolved >= "+preMonth+" AND resolved <= "+curMonth+" AND 引起人员 in (junwang33, currentUser())";
        String[] sqlArr = {taskSql, issueSql};
        for (int i = 0; i < sqlArr.length; i++) {
            String Query = sqlArr[i];
            List<String> strings = search_jql(USERNAME, PSWD, null, Query);
            for (String issueKey : strings) {
                if (!Timegrabbing.lists.contains(issueKey.trim())) {
                    final Issue issue = get_issue(issueKey, USERNAME, PSWD);
                    JiraInfoModel jiraInfoModel = get_jira_info(issue);
                    JiraTask jiraTask = new JiraTask();
                    jiraTask.setCreateTime(jiraInfoModel.getJiraCreateTime().toString());
                    jiraTask.setIssuekey(issueKey);

                    Matcher m = p.matcher(jiraInfoModel.getDescription() == null ? "" : jiraInfoModel.getDescription());
                    String des = m.replaceAll("");
                    jiraTask.setDescription(des);
                    jiraTask.setReaction(jiraInfoModel.getReActions());
                    jiraTask.setSummary(jiraInfoModel.getSummary());
                    jiraTask.setReporter(jiraInfoModel.getReporter());
                    jiraTask.setStatus(jiraInfoModel.getStatus());
                    jiraTask.setIssueType(jiraInfoModel.getIssueType());
                    jiraTask.setTitle("");
                    jiraTask.setCreateTime(jiraInfoModel.getJiraCreateTime().toString());
                    jiraTask.setState("0");
                    jiraTaskService.add(jiraTask);
                    System.out.println(jiraInfoModel.getSummary());
                }

            }
        }

    }

    public String preMonth() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        System.out.println("当前时间是：" + dateFormat.format(date));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
        date = calendar.getTime();

        System.out.println("上一个月的时间： " + dateFormat.format(date));
        return  dateFormat.format(date);
    }

}
