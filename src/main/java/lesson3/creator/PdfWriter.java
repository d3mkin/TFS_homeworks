package lesson3.creator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import lesson3.domain.Human;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PdfWriter extends AbstractWriter {

    private static Font DEFAULT_FONT;
    static {
        try {
            DEFAULT_FONT = new Font(BaseFont.createFont(
                "/fonts/FiraSansLight-Italic.ttf",
                BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    public void write(List<Human> humans) {
        try {
            Document document = new Document(PageSize.A3.rotate(), 25, 25, 25, 25);
            File file = new File("humans.pdf");
            com.itextpdf.text.pdf.PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            PdfPTable table = new PdfPTable(getHeaders().size());
            float[] columnWidths = new float[]{150f, 150f, 150f, 110f, 50f, 125f, 150f, 125f, 150f, 175f, 150f, 175f, 75f, 50f};
            table.setWidths(columnWidths);
            addTableHeader(table);
            for (int i = 0; i < humans.size(); i++) {
                addRows(table, humans.get(i));
            }

            document.add(table);
            document.close();

            System.out.println("Файл создан. Путь:" + file.getAbsolutePath());
        } catch (FileNotFoundException | DocumentException e) {
            throw new RuntimeException(e);
        }

    }

    private void addTableHeader(PdfPTable table) {
        for (int i = 0; i < getHeaders().size(); i++) {
            PdfPCell header = new PdfPCell();
            header.setPhrase(toPhrase(getHeaders().get(i)));
            table.addCell(header);
        }
    }
    
    private void addRows(PdfPTable table, Human human) {
        table.addCell(toPhrase(human.getName()));
        table.addCell(toPhrase(human.getSurname()));
        table.addCell(toPhrase(human.getPatronymic()));
        table.addCell(toPhrase(String.valueOf(calculateAge(human.getBirthDate()))));
        table.addCell(toPhrase(format(human.getSex())));
        table.addCell(toPhrase(format(human.getBirthDate())));
        table.addCell(toPhrase(human.getInn()));
        table.addCell(toPhrase(String.valueOf(human.getAddress().getZipCode())));
        table.addCell(toPhrase(human.getAddress().getCountry()));
        table.addCell(toPhrase(human.getAddress().getArea()));
        table.addCell(toPhrase(human.getAddress().getCity()));
        table.addCell(toPhrase(human.getAddress().getStreet()));
        table.addCell(toPhrase(String.valueOf(human.getAddress().getHouse())));
        table.addCell(toPhrase(String.valueOf(human.getAddress().getApartment())));
    }

    private static Phrase toPhrase(String string) {
        return new Phrase(string, DEFAULT_FONT);
    }
}
