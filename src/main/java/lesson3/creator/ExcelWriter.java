package lesson3.creator;

import lesson3.domain.Human;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class ExcelWriter extends AbstractWriter {

    public void write(List<Human> humans) {
        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Люди");

            Row header = sheet.createRow(0);

            for (int i = 0; i < getHeaders().size(); i++) {
                Cell headerCell = header.createCell(i);
                headerCell.setCellValue(getHeaders().get(i));
            }

            for (int i = 0; i < humans.size(); i++) {
                Human human = humans.get(i);
                Row row = sheet.createRow(i + 1);

                row.createCell(0).setCellValue(human.getName());
                row.createCell(1).setCellValue(human.getSurname());
                row.createCell(2).setCellValue(human.getPatronymic());
                row.createCell(3).setCellValue(calculateAge(human.getBirthDate()));
                row.createCell(4).setCellValue(format(human.getSex()));
                row.createCell(5).setCellValue(format(human.getBirthDate()));
                row.createCell(6).setCellValue(human.getInn());
                row.createCell(7).setCellValue(human.getAddress().getZipCode());
                row.createCell(8).setCellValue(human.getAddress().getCountry());
                row.createCell(9).setCellValue(human.getAddress().getArea());
                row.createCell(10).setCellValue(human.getAddress().getCity());
                row.createCell(11).setCellValue(human.getAddress().getStreet());
                row.createCell(12).setCellValue(human.getAddress().getHouse());
                row.createCell(13).setCellValue(human.getAddress().getApartment());
            }

            File currDir = new File(".");
            String path = currDir.getAbsolutePath();
            String fileLocation = path.substring(0, path.length() - 1) + "humans.xlsx";

            FileOutputStream outputStream = new FileOutputStream(fileLocation);
            workbook.write(outputStream);
            System.out.println("Файл создан. Путь:" + fileLocation);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
