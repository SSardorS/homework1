package uz.pdp.homework1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomDTO {
    private Integer number;
    private Integer floor;
    private Double size;
    private Long hotelId;
}
