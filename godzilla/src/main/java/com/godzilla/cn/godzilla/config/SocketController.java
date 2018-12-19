package com.godzilla.cn.godzilla.config;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.godzilla.cn.godzilla.bean.Notes;
import com.godzilla.cn.godzilla.bean.User;
import com.godzilla.cn.godzilla.service.NotesService;
import com.godzilla.cn.godzilla.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

@Controller
@CrossOrigin
public class SocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    public NotesService notesService;

    @Autowired
    public UserService userService;

    /**
     * @MessageMapping 接收客户端通过 /server/send 发送的消息
     * messagingTemplate.convertAndSend 给客户端发送消息 客户端通过 /topic/message 接收
     *
     * @param name
     */
    @MessageMapping("/send")
    public void pushMessage(String name) {
        System.out.println(name);
        String contents = "";
        List<Notes> r = notesService.findInfoByUserId(1);
        List<Map> lists =new ArrayList<>();
        for (Notes note: r){
            Map map =new HashMap<>();
            map.put("userId",note.getUserId());
            map.put("date",note.getDate());
            map.put("content",note.getContent());
            lists.add(map);
        }
        contents = JSONArray.fromObject(lists).toString();
        messagingTemplate.convertAndSend("/topic/message", contents);

    }

    @MessageMapping("/addNote")
    public void pushNote(String content) {
        System.out.println(content);
        int i = 0;
        if (!StringUtils.isEmpty(content)){
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
            i = notesService.add(1,content,content,format.format(new Date()));
        }
        messagingTemplate.convertAndSend("/topic/add", i);

    }

    @MessageMapping("/sign")
    public void pushSign(String content) {
        System.out.println(content);
        JSONObject jsStr = JSONObject.fromObject(content);
        String count = jsStr.get("count").toString();
        String pwd = jsStr.get("pwd").toString();
        int i =0 ;
        User user = userService.fingUserbyCountAndPassword(count,pwd);
        if (user!=null){
            i=0;
        }else{
            i=1;
        }
        messagingTemplate.convertAndSend("/topic/sign", i);

    }


}
