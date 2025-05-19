package com.example.demo.service.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.demo.exception.RoomAlreadyExistsException;
import com.example.demo.exception.RoomException;
import com.example.demo.exception.RoomNotFoundException;
import com.example.demo.mapper.RoomMapper;
import com.example.demo.model.dto.RoomDto;
import com.example.demo.model.entity.Room;
import com.example.demo.repository.RoomRepository;
import com.example.demo.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService{

	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private RoomMapper roomMapper;
	
	@Override
	public List<RoomDto> findAllRoom() {	
		return roomRepository.findAll()
							 .stream()
							 .map(roomMapper::toDto)
							 .toList();
	}

	@Override
	public RoomDto getRoomById(Integer roomId) {
		Room room = roomRepository.findById(roomId)
							 .orElseThrow(()->new RoomNotFoundException("找不到會議室: roomId= "+roomId));
		return roomMapper.toDto(room);
	}

	@Override
	public void addRoom(RoomDto roomDto) {
		Optional<Room> optRoom= roomRepository.findById(roomDto.getRoomId());
		if(optRoom.isPresent()) {
			throw new RoomAlreadyExistsException("新增失敗: 房號 " + roomDto.getRoomId() + " 已經存在");
		}
		// 進入新增程序
		// DTO 轉 Entity
		Room room = roomMapper.toEntity(roomDto);
		// 將 Entity room 存入
		roomRepository.save(room);
	}

	@Override
	public void addRoom(Integer roomId, String roomName, Integer roomSize) {
		RoomDto roomDto = new RoomDto(roomId, roomName, roomSize);
		addRoom(roomDto);
	}

	@Override
	public void updateRoom(Integer roomId, RoomDto roomDto) {
		Optional<Room> optRoom= roomRepository.findById(roomDto.getRoomId());
		if(optRoom.isEmpty()) {
			throw new RoomAlreadyExistsException("修改失敗: 房號 " + roomDto.getRoomId() + " 不存在");
		}
		// 進入新增程序
		// DTO 轉 Entity
		Room room = roomMapper.toEntity(roomDto);
		// 將 Entity room 存入
		roomRepository.save(room);
	}

	@Override
	public void updateRoom(Integer roomId, String roomName, Integer roomSize) {
		
		
		RoomDto roomDto = new RoomDto(roomId, roomName, roomSize);
		updateRoom(roomId,roomDto);
	}

	@Override
	public void deleteRoom(Integer roomId) {
		Optional<Room> optRoom= roomRepository.findById(roomId);
		if(optRoom.isEmpty()) {
			throw new RoomAlreadyExistsException("修改失敗: 房號 " + roomId + " 不存在");
		}
		
		roomRepository.deleteById(roomId);
	}

}
