package ar.com.semillero.semillatronalfa.services;

import ar.com.semillero.semillatronalfa.models.dtos.SeedDto;
import ar.com.semillero.semillatronalfa.services.interfaces.SeedService;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

@Component("pages/semillas.html")
public class SeedsExcel extends  AbstractXlsxView{

    @Autowired
    private SeedService seedService;

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"listado-semillas.xlsx\"");

        //Create sheet of excel
        Sheet sheet = workbook.createSheet("Semillas");
        //Create row of sheet
        Row row = sheet.createRow(0);
        //Create cell of the row
        Cell cell = row.createCell(0);

        //create row the data name
        Row dataRow = sheet.createRow(2);
        String[] columns = {"APELLIDO Y NOMBRE",
                            "ROL",
                            "COMISIÓN",
                            "MARCA TEMPORAL",
                            "TURNO SELECCIONADO",
                            "FECHA DE NACIMIENTO",
                            "DNI/PASAPORTE",
                            "GENERO",
                            "PROVINCIA-CIUDAD",
                            "PAIS",
                            "TELEFONO",
                            "CORREO ELECTRONICO",
                            "DISCORD",
                            "LINKEDIN",
                            "¿COMO CONOCIO EL SEMILLERO?",
                            "OTROS ESTUDIOS",
                            "MODIFICAR ESTADO",
                            "ASIGNAR A PROYECTO",
                            "FECHA DE INICIO",
                            "FECHA DE FINALIZACIÓN"};

        //sets data in the row
        for (int i = 0; i < columns.length; i++) {
            cell = dataRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        //list of data seed
        List<SeedDto> seeds = (List<SeedDto>) model.get("seeds");

        int rowNumber = 3;
        for (SeedDto seed : seeds) {
            dataRow = sheet.createRow(rowNumber);
            dataRow.createCell(0).setCellValue(seed.getFirstName());
            dataRow.createCell(1).setCellValue(seed.getRol());
            dataRow.createCell(2).setCellValue(seed.getCommission());
            dataRow.createCell(3).setCellValue(seed.getPostulationDate() == null ? "" : seed.getPostulationDate().toString());
            dataRow.createCell(4).setCellValue(seed.getTurn());
            dataRow.createCell(5).setCellValue(seed.getBirthDate() == null ? "" : seed.getBirthDate().toString());
            dataRow.createCell(6).setCellValue(seed.getDni().toString());
            dataRow.createCell(7).setCellValue(seed.getGender());
            dataRow.createCell(8).setCellValue(seed.getCity());
            dataRow.createCell(9).setCellValue(seed.getCountry());
            dataRow.createCell(10).setCellValue(seed.getTelephone());
            dataRow.createCell(11).setCellValue(seed.getEmail());
            dataRow.createCell(12).setCellValue(seed.getDiscordUser());
            dataRow.createCell(13).setCellValue(seed.getLinkedin());
            dataRow.createCell(14).setCellValue(seed.getMeetSemilleroBy());
            dataRow.createCell(15).setCellValue(seed.getStudies());
            dataRow.createCell(16).setCellValue(seed.getPrimary());
            dataRow.createCell(17).setCellValue(seed.getProjectNames() == null ? "" : String.join(", ", seed.getProjectNames()));
            dataRow.createCell(18).setCellValue(seed.getStartDate() == null ? "" : seed.getStartDate().toString());
            dataRow.createCell(19).setCellValue(seed.getEndDate() == null ? "" : seed.getEndDate().toString());
            rowNumber++;
        }
    }

}
