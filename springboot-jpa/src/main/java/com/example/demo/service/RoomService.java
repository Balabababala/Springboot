package com.example.demo.service;

import java.util.List;



import com.example.demo.model.dto.RoomDto;



public interface RoomService {
	public List<RoomDto> findAllRoom(); 											//查 all room
	public RoomDto getRoomById(Integer roomId); 									//查單筆room
	public void addRoom(RoomDto roomDto);     										//新增room
	public void addRoom(Integer roomId,String roomName,Integer roomSize);			//新增room
	public void updateRoom (Integer roomId,RoomDto roomDto);						//更新room
	public void updateRoom (Integer roomId,String roomName,Integer roomSize);		//更新room
	public void deleteRoom(Integer roomId);										 	//刪除room
	
}
