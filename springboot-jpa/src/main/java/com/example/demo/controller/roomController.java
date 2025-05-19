package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.RoomDto;
import com.example.demo.service.RoomService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Method 				URI 				功能 
 * -------------------------------------------------------------------------------
 * GET 				/room/{roomId}			查詢指定會議室(單筆)
 * GET				/rooms					查詢所有會議室(多筆)
 * POST				/room					新增會議室
 * POST				/room/update/{roomId}	完整修改會議室(同時修改 roomName 與 roomSize)
 * GET				/room/delete/{roomId}   刪除會議室
 *
 */


@Controller
@RequestMapping(value= {"/room","/rooms"})
public class roomController {
	@Autowired
	private RoomService roomService;
	
	@GetMapping
	public String getRooms(Model model) {
		RoomDto roomDto = new RoomDto();
		List <RoomDto> roomDtos= roomService.findAllRoom();
		model.addAttribute("roomDto",roomDto);
		model.addAttribute("roomDtos",roomDtos);
		return "room/room";
	}
	
	@GetMapping("/{roomId}")
	public String findByid(@RequestParam Integer roomId,Model model) {
		return new String();
	}
	
	@PostMapping("path")
	public String postMethodName(@RequestBody String entity) {
		//TODO: process POST request
		
		return entity;
	}
	
}
