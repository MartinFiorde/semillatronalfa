package ar.com.semillero.semillatronalfa.services;

import ar.com.semillero.semillatronalfa.models.dtos.EventDto;
import ar.com.semillero.semillatronalfa.services.interfaces.EventService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.*;
import java.time.LocalDateTime;
import java.util.*;


@Component("pages/eventos.html")
public class EventsExcel extends AbstractXlsxView {

    @Autowired
    private EventService eventService;


    @Override
    public void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"listado-eventos.xlsx\"");

        Sheet sheet = workbook.createSheet("Eventos");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Lista de Eventos");

        Row dataRow = sheet.createRow(2);
        String[] columns = {"Nombre de evento", "Fecha", "Ofrecido por Semillero", "Estado", "Organizado por",
        "Tipo", "Enfoque", "Instructor", "Descripción", "Locación", "Origen", "Destinatarios", "Duración",
        "Modalidad", "Hora de inicio", "Hora de finalización", "Total de asistentes"};

        for (int i = 0; i < columns.length; i++) {
            cell = dataRow.createCell(i);
            cell.setCellValue(columns[i]);
        }
        List<EventDto> events = (List<EventDto>) model.get("events");

        int rowNumber = 3;
        for (EventDto event : events) {
            dataRow = sheet.createRow(rowNumber);
            dataRow.createCell(0).setCellValue(event.getTitle());
            dataRow.createCell(1).setCellValue(event.getDate());
            dataRow.createCell(2).setCellValue(event.getOfferedBySemillero() ? "SÍ" : "NO");
            dataRow.createCell(3).setCellValue(event.getStatus());
            dataRow.createCell(4).setCellValue(event.getOrganizedBy());
            dataRow.createCell(5).setCellValue(event.getType());
            dataRow.createCell(6).setCellValue(event.getApproach());
            dataRow.createCell(7).setCellValue(event.getInstructor());
            dataRow.createCell(8).setCellValue(event.getDescription());
            dataRow.createCell(9).setCellValue(event.getLocation());
            dataRow.createCell(10).setCellValue(event.getOrigin());
            dataRow.createCell(11).setCellValue(event.getDestination());
            dataRow.createCell(12).setCellValue(event.getDuration());
            dataRow.createCell(13).setCellValue(event.getModality());
            dataRow.createCell(14).setCellValue(event.getStartTime().toString());
            dataRow.createCell(15).setCellValue(event.getEndingTime().toString());
            dataRow.createCell(16).setCellValue(event.getTotalAttendance());
            rowNumber++;
        }
    }
}
