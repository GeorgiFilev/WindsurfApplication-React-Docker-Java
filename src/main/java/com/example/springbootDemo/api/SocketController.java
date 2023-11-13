package com.example.springbootDemo.api;

import com.example.springbootDemo.model.Message;
import com.example.springbootDemo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class SocketController {

    @Autowired
    private PostService postService;

    @MessageMapping("/messages")
    @SendTo("/store")
    public Message send(@Payload Message message) {
        postService.AddMessageToPost(message.getPostID(),message);
        return message ;
    }
}
