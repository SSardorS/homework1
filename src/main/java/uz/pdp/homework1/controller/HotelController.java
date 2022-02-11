package uz.pdp.homework1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.homework1.entity.Hotel;
import uz.pdp.homework1.repository.HotelRepository;

import java.awt.print.Pageable;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    HotelRepository hotelRepository;

    @GetMapping("/get")
    public List<Hotel> getAll() {
        List<Hotel> all = hotelRepository.findAll();
        return all;
    }

    @GetMapping("/get/{id}")
    public Hotel getById(@PathVariable Long id) {
        Optional<Hotel> byId = hotelRepository.findById(id);
        if (byId.isPresent()) {
            Hotel hotel = byId.get();
            return hotel;
        } else {
            return null;
        }
    }

    @PostMapping("/add")
    public String add(@RequestBody Hotel hotel) {
        boolean b = hotelRepository.existsByName(hotel.getName());
        String response = null;
        if (!b) {
            hotelRepository.save(hotel);
            response = "ADDED";
        } else {
            response = "ERROR";
        }
        return response;
    }

    @PutMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @RequestBody Hotel hotel) {
        Optional<Hotel> byId = hotelRepository.findById(id);
        if (byId.isPresent()) {
            Hotel oldHotel = byId.get();
            oldHotel.setName(hotel.getName());
            hotelRepository.save(oldHotel);
            return "EDITED";
        } else {
            return "ERROR";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        hotelRepository.deleteById(id);
        return "DELETED";
    }


}
