package uz.pdp.homework1.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.homework1.entity.Room;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Page<Room> findByHotelId(Long hotel_id, Pageable pageable);
    boolean existsRoomByNumber(Integer number);
}
