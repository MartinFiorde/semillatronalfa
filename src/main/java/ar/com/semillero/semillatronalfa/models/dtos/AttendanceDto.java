package ar.com.semillero.semillatronalfa.models.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDto {
    private String timestamp;
    private String fullName;
    private Long dni;
}
