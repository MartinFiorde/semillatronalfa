package ar.com.semillero.semillatronalfa.models.dtos;

import ar.com.semillero.semillatronalfa.models.Attendance;
import ar.com.semillero.semillatronalfa.models.event.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDto implements Serializable {
    private String id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    private String title;
    private Image image;
    private String base64;
    private Boolean offeredBySemillero;
    private String status;
    private String organizedBy;
    private String type;
    private String approach;
    private String instructor;
    private String description;
    private String location;
    private String origin;
    private String destination;
    private int duration;
    private String modality;
    private LocalTime startTime;
    private LocalTime endingTime;
    private int totalAttendance;
    private List<Attendance> attendanceList;
}
