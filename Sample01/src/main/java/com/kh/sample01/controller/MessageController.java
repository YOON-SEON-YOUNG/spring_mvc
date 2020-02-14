package com.kh.sample01.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kh.sample01.domain.MessageVo;
import com.kh.sample01.service.MessageService;

// ajax 방식으로 할거라서
@RestController 
@RequestMapping("/message")
public class MessageController {
		
	@Inject
	private MessageService messageService;
	
	
	@RequestMapping(value = "/send",  method = RequestMethod.POST)
	public String sendMessage(@RequestBody MessageVo messageVo) throws Exception{
		
		messageService.sendMessage(messageVo);
		
		return "success_send";
	}
	
	
	
	@RequestMapping(value = "/read/{message_id}/{userid}", method = RequestMethod.GET)
	public MessageVo readMessage(@PathVariable ("message_id") int message_id,
			@PathVariable ("userid") String userid	) throws Exception{
		
		return messageService.readMessage(message_id, userid);
	}
}
