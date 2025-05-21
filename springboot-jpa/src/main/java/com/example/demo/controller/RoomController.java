package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.RoomDto;
import com.example.demo.service.RoomService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Method 				URI 				功能 
 * -------------------------------------------------------------------------------
 * GET 				/room/{roomId}			查詢指定會議室(單筆)
 * GET				/rooms					查詢所有會議室(多筆)
 * POST				/room					新增會議室
 * PUT				/room/update/{roomId}	完整修改會議室(同時修改 roomName 與 roomSize)
 * DELETE			/room/delete/{roomId}   刪除會議室
 *
 */


@Controller
@RequestMapping(value= {"/room","/rooms"})
public class RoomController {
	@Autowired
	private RoomService roomService;
	
	@GetMapping("/{roomId}")
	public String findByid(@PathVariable Integer roomId,@ModelAttribute RoomDto roomDto,Model model) {
		roomDto = roomService.getRoomById(roomId);
		return "/room/room_update";
	}
	
	
	@GetMapping
	public String getRooms(@ModelAttribute RoomDto roomDto,Model model) {
		
//		RoomDto roomDto = new RoomDto();
//		model.addAttribute("roomDto",roomDto);		
		
		List <RoomDto> roomDtos= roomService.findAllRoom();
		model.addAttribute("roomDtos",roomDtos);
		return "room/room";
	}
	
	
	@PostMapping("")
	public String addRoom(@Valid RoomDto roomDto,BindingResult bindingResult,Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("roomDtos",roomService.findAllRoom());
			return "room/room";
		}
		roomService.addRoom(roomDto);
		return "redirect:/rooms";
	}
	
	
	@PutMapping("/update/{roomId}")
	public String updateRoom(@PathVariable Integer roomId, @Valid RoomDto roomDto, BindingResult bindingResult) {
		// 驗證資料
		if(bindingResult.hasErrors()) { // 若驗證時有錯誤發生
			return "room/room_update";
		}
		
		// 進行修改
		roomService.updateRoom(roomId, roomDto);
		return "redirect:/rooms";
	}
	
	@DeleteMapping("/delete/{roomId}")
	public String updateRoom(@PathVariable Integer roomId) {
		roomService.deleteRoom(roomId);
		return "redirect:/rooms";
	}
	
	@ExceptionHandler({Exception.class})
	public String handleException(Exception e, Model model) {
		e.printStackTrace();
		model.addAttribute("message", e.getMessage());
		return "error";
	}
}
