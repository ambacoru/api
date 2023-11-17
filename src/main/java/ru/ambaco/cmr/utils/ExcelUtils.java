package ru.ambaco.cmr.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.ambaco.cmr.entities.User;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


@Data
@AllArgsConstructor
public class ExcelUtils {
    private static  String HEADER[] = {
            "id","Nom" ,"Prénom","Date de naissance",
            "Sexe","Filiére","Cycle",
            "Niveau d'étude","Etablissement","Ville"
    };

    public static  String SHEET_NAME = "Listes des Étudiants";
    public  static ByteArrayInputStream dataToExcel(List<User> userList) throws IOException {

        Workbook workbook =  new XSSFWorkbook();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            Sheet sheet = workbook.createSheet(SHEET_NAME);
            Row row = sheet.createRow(0);
            for(int i = 0 ; i< HEADER.length; i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(HEADER[i]);
            }
            int rowIndex = 1;
            for(User user:userList){
                Row row1 = sheet.createRow(rowIndex);
                rowIndex++;
                row1.createCell(0).setCellValue(user.getId());
                row1.createCell(1).setCellValue(user.getName());
                row1.createCell(2).setCellValue(user.getSurname());
                row1.createCell(3).setCellValue(user.getBirthDay());
                row1.createCell(4).setCellValue(user.getGender());
                row1.createCell(5).setCellValue(user.getSector());
                row1.createCell(6).setCellValue(user.getCycle());
                row1.createCell(7).setCellValue(user.getLevelOfStudy());
                row1.createCell(8).setCellValue(user.getEstablishment());
                row1.createCell(9).setCellValue(user.getCity());
            }

            workbook.write(byteArrayOutputStream);
            return  new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        }
        catch (IOException e){
            throw  new RuntimeException();
        }
        finally {
            workbook.close();
            byteArrayOutputStream.close();
        }

    }
}
