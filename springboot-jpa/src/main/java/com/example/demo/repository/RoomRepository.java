package com.example.demo.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Room;

// Spring JPA
@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> { // Room: entity, Integer: @Id 的型別
	//default CRUD
	//自定義 
	//1. find roomSize 自動生成
	List<Room> findByRoomSizeGreaterThan(Integer size);
	
	// 2. 查詢 roomSize 大於指定值的房間(自行撰寫 T-SQL, 注意:欄位名要符合資料表中的設定)
	@Query(value = "select room_id ,room_name ,room_size from room where room_size> :roomSize", nativeQuery= true)
	List<Room> findRooms(Integer size);
	// 3. 查詢 roomSize 大於指定值的房間(自行撰寫 PQL, 注意:欄位名要符合 entity 中的設定, 會自動生成 SQL)
	@Query(value = "select r from room r where r.roomSize> :roomSize", nativeQuery= true)
	List<Room> readRooms(Integer size);
}
