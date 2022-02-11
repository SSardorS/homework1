package uz.pdp.homework1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.homework1.dto.RoomDTO;
import uz.pdp.homework1.entity.Hotel;
import uz.pdp.homework1.entity.Room;
import uz.pdp.homework1.repository.HotelRepository;
import uz.pdp.homework1.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomCotroller {
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    HotelRepository hotelRepository;

    @GetMapping("/get")
    public Page<Room> getAll(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 2);
        Page<Room> all = roomRepository.findAll(pageable);
        return all;
    }

    @GetMapping("/get/hotelId")
    public Page<Room> getById(@PathVariable Long hotelId, @RequestParam int page) {
        PageRequest pageable = PageRequest.of(page, 3);
        Page<Room> byHotelId = roomRepository.findByHotelId(hotelId, pageable);
        return byHotelId;
    }

    @PostMapping("/add")
    public String add(@RequestBody RoomDTO roomDTO) {
        boolean b = roomRepository.existsRoomByNumber(roomDTO.getNumber());
        String response = null;
        if (!b) {
            Optional<Hotel> byId = hotelRepository.findById(roomDTO.getHotelId());
            if (byId.isPresent()) {
                Hotel hotel = byId.get();
                Room room = new Room(null, roomDTO.getNumber(), roomDTO.getFloor(), roomDTO.getSize(), hotel);
                roomRepository.save(room);
                response = "ADDED";
            } else {
                response = "ERROR";
            }
        }
        return response;
    }

    @PutMapping("/edit/{id}")
    public String edit(@RequestBody RoomDTO roomDTO, @PathVariable Long id) {
        Optional<Room> byId = roomRepository.findById(id);
        Optional<Hotel> byIdhotel = hotelRepository.findById(roomDTO.getHotelId());
        String response = null;
        if (byId.isPresent() && byIdhotel.isPresent()) {
            Hotel hotel = byIdhotel.get();
            Room room = byId.get();
            room.setNumber(roomDTO.getNumber());
            room.setFloor(roomDTO.getFloor());
            room.setSize(roomDTO.getSize());
            room.setHotel(hotel);
            roomRepository.save(room);
            response = "EDITED";
        } else {
            response = "ERROR";
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        roomRepository.deleteById(id);
        return "DELETED";
    }
}
