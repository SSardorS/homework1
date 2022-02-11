package uz.pdp.homework1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.homework1.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    boolean existsByName(String name);
}
