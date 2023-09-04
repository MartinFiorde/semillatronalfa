package ar.com.semillero.semillatronalfa.services;

import ar.com.semillero.semillatronalfa.models.Attendance;
import ar.com.semillero.semillatronalfa.models.dtos.EventDto;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Map;

@Component("pages/evento.html")
public class AttendancesExcel extends AbstractXlsxView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"listado-asistentes.xlsx\"");

        Sheet sheet = workbook.createSheet("Asistentes");
        EventDto event = (EventDto) model.get("event");
        Row row1 = sheet.createRow(0);
        Cell cell = row1.createCell(0);
        cell.setCellValue("Evento: " + event.getTitle());
        Row row2 = sheet.createRow(1);
        cell = row2.createCell(0);
        cell.setCellValue("Lista de asistentes");

        Row dataRow = sheet.createRow(3);
        String[] columns = {"Nombre completo", "DNI", "Marca temporal"};

        for (int i = 0; i < columns.length; i++) {
            cell = dataRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        List<Attendance> attendanceList = (List<Attendance>) model.get("attendance");

        // / 1000.0 / 86400 + 70 * 365.25 + 1
        int rowNumber = 4;
        for (Attendance attendance : attendanceList) {
            dataRow = sheet.createRow(rowNumber);
            dataRow.createCell(0).setCellValue(attendance.getFullName());
            dataRow.createCell(1).setCellValue(attendance.getDni());
            dataRow.createCell(2).setCellValue(DateUtil.getExcelDate(attendance.getTimestamp()));
            rowNumber++;
        }
    }
}
