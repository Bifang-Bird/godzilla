package com.godzilla.cn.godzilla.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker // 開啟使用STOMP協議來傳輸基於代理的消息,Broker就是代理的意思
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

    /**
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //註冊STOMP協議的節點，並指定映射的URL 发送消息前缀
        registry.enableSimpleBroker("/topic");
        //註冊接收消息的節點 接收消息前缀
        registry.setApplicationDestinationPrefixes("/server");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册STOMP协议节点，同时指定使用SockJS协议
        registry.addEndpoint("/websocket-server").setAllowedOrigins("*").withSockJS();
    }
}
