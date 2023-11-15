
package ar.com.semillero.semillatronalfa.services;

import ar.com.semillero.semillatronalfa.models.dtos.ProjectDto;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

@Component("pages/proyectos.html")
public class ProjectExcel extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"listado-proyectos.xlsx\"");
        
        Sheet sheet = workbook.createSheet("Proyectos");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Lista de Proyectos");
        
        Row dataRow = sheet.createRow(2);
        String[] columns = {"NOMBRE DEL PROYECTO",
                            "COMISIÒN",
                            "TIEMPO ESTIMADO",
                            "FECHA DE INICIO",
                            "FECHA DE FINALIZACIÒN",
                            "ALIADO",
                            "MARCA TEMPORAL",
                            "LUGAR DE ORIGEN",
                            "ODS",
                            "APELLIDO Y NOMBRE RESPONSABLE",
                            "TELEFONO",
                            "EMAIL",
                            "DIRECCION LABORAL",
                            "PRODUCT OWNER",
                            "TELEFONO PO",
                            "EMAIL PO",
                            "DISCORD PO",
                            "PROPOSITO",
                            "TIPO DE PROYECTO",
                            "ESTADO"};
        
        for (int i = 0; i < columns.length; i++) {
            cell = dataRow.createCell(i);
            cell.setCellValue(columns[i]);
        }
        
        List<ProjectDto> projects = (List<ProjectDto>)model.get("projects");
       
        int rowNumber = 3;
        
        for (ProjectDto project : projects) {
            dataRow = sheet.createRow(rowNumber);
            dataRow.createCell(0).setCellValue(project.getProjectName());
            dataRow.createCell(1).setCellValue(project.getCommission());
            dataRow.createCell(2).setCellValue(project.getEstimatedTime());
            dataRow.createCell(3).setCellValue(project.getInitialDate().toString());
            dataRow.createCell(4).setCellValue(project.getEndingDate().toString());
            dataRow.createCell(5).setCellValue(project.getAlly());
            dataRow.createCell(6).setCellValue(project.getTimestamp().toString());
            dataRow.createCell(7).setCellValue(project.getOriginPlace());
            dataRow.createCell(8).setCellValue(project.getOds());
            dataRow.createCell(9).setCellValue(project.getResponsible());
            dataRow.createCell(10).setCellValue(project.getResponsiblePhoneNumber());
            dataRow.createCell(11).setCellValue(project.getResponsibleEmail());
            dataRow.createCell(12).setCellValue(project.getAddress());
            dataRow.createCell(13).setCellValue(project.getProductOwnerFullName());
            dataRow.createCell(14).setCellValue(project.getProductOwnerTelephone());
            dataRow.createCell(15).setCellValue(project.getProductOwnerEmail());
            dataRow.createCell(16).setCellValue(project.getProductOwnerDiscordUser());
            dataRow.createCell(17).setCellValue(project.getPurpose());
            dataRow.createCell(18).setCellValue(project.getProjectType());
            dataRow.createCell(19).setCellValue(project.getProjectStatus() + " / "+project.getProjectStage());
            rowNumber++;
        }
    }
    
}
