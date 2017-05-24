package grp1.docs.model;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import grp1.dao.AccidentDao;
import grp1.dao.CityDao;
import grp1.dao.DisasterDao;
import grp1.docs.DocumentHelper;
import grp1.docs.DocumentModel;
import grp1.docs.pdf.PDFGenerator;
import grp1.model.Accident;
import grp1.model.City;
import grp1.model.Disaster;
import grp1.service.AccidentService;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.supercsv.exception.SuperCsvException;
import org.supercsv.io.ICsvBeanWriter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by admin on 24.05.2017.
 */
public class AccidentDocumentModel extends DocumentModel {
    List<Accident> accidents;
    @Override
    public void initialize(Object value) throws SQLException {
        AccidentDao accidentDao = new AccidentDao();
        List<Accident> items = accidentDao.getAllAccidents();
        accidents = new ArrayList<Accident>();
        for (Accident item: items)
        {
            if (DocumentHelper.isDateInCurrentWeek(item.getDate()))
                accidents.add(item);
        }

        CityDao cityDao = new CityDao();
        List<City> cities = cityDao.getAllCities();
        DisasterDao disasterDao = new DisasterDao();
        List<Disaster> disasters = disasterDao.getAllDisasters();
        AccidentService.setAccidentsChildEntities(accidents, disasters, cities);
    }

    @Override
    public void buildPdf(Document doc) throws DocumentException, SQLException {
        initialize(null);
        addTitlePage(doc);
        createTable(doc);
    }

    private static void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        creteEmptyLine(preface, 1);
        preface.add(new Paragraph("Latest accidents", PDFGenerator.TIME_ROMAN));
        creteEmptyLine(preface, 1);
        document.add(preface);
    }

    private static void creteEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private void createTable(Document document) throws DocumentException {
        Paragraph paragraph = new Paragraph();
        creteEmptyLine(paragraph, 2);
        document.add(paragraph);
        PdfPTable table = new PdfPTable(4);

        PdfPCell c1 = new PdfPCell(new Phrase("Date"));
        c1.setBackgroundColor(BaseColor.GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Disaster"));
        c1.setBackgroundColor(BaseColor.GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("City"));
        c1.setBackgroundColor(BaseColor.GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Level"));
        c1.setBackgroundColor(BaseColor.GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        for (Accident accident: accidents)
        {
            table.setWidthPercentage(100);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(accident.getDate().toString());
            table.addCell(accident.getDisaster().getName());
            table.addCell(accident.getCity().getName());
            table.addCell(accident.getLevel().toString());
        }

        document.add(table);
    }

    @Override
    public void buildXls(Workbook workbook) throws Exception {
        initialize(null);
        Sheet sheet = workbook.createSheet("Accidents");

        CellStyle style = workbook.createCellStyle();
        org.apache.poi.ss.usermodel.Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.MEDIUM);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        style.setWrapText(true);


        CellStyle headerStyle = workbook.createCellStyle();
        org.apache.poi.ss.usermodel.Font fontS = workbook.createFont();
        font.setFontName("Arial");
        font.setBold(true);
        headerStyle.setBorderBottom(BorderStyle.MEDIUM);
        headerStyle.setFont(fontS);
        headerStyle.setWrapText(true);
        Row headerTitle = sheet.createRow(0);
        headerTitle.createCell(0).setCellValue("Accidents");;
        headerTitle.getCell(0).setCellStyle(headerStyle);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));

        Row header = sheet.createRow(1);
        header.createCell(0).setCellValue("Date");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("Disaster");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("City");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("Level");
        header.getCell(3).setCellStyle(style);

        int rowCount = 2;

        CellStyle otherCellStyle = workbook.createCellStyle();
        otherCellStyle.setBorderBottom(BorderStyle.MEDIUM);
        otherCellStyle.setWrapText(true);

        for (Accident accident : accidents) {
            Row commentRow = sheet.createRow(rowCount++);
            commentRow.createCell(0).setCellValue(accident.getDate().toString());
            commentRow.getCell(0).setCellStyle(otherCellStyle);
            int columnIndex = commentRow.getCell(0).getColumnIndex();

            sheet.autoSizeColumn(columnIndex);
            commentRow.createCell(1).setCellValue(accident.getDisaster().getName());
            commentRow.getCell(1).setCellStyle(otherCellStyle);
            columnIndex = commentRow.getCell(1).getColumnIndex();
            sheet.autoSizeColumn(columnIndex);

            commentRow.createCell(2).setCellValue(accident.getCity().getName());
            commentRow.getCell(2).setCellStyle(otherCellStyle);
            columnIndex = commentRow.getCell(2).getColumnIndex();
            sheet.autoSizeColumn(columnIndex);

            commentRow.createCell(3).setCellValue(accident.getLevel());
            commentRow.getCell(3).setCellStyle(otherCellStyle);
            columnIndex = commentRow.getCell(3).getColumnIndex();
            sheet.autoSizeColumn(columnIndex);

        }
    }

    @Override
    public void buildCsv(ICsvBeanWriter writer) throws IOException, SQLException {
        initialize(null);
        String[] header = {"Date", "Disaster", "City", "Level"};
        writer.writeHeader(header);
        for (Accident accident : accidents) {
            try {
                writer.write(new AccidentBean(accident.getDate().toString(), accident.getDisaster().getName(), accident.getCity().getName(), accident.getLevel()), header);
            } catch (SuperCsvException exception) {
                System.out.println(exception.toString());
            }
        }
    }

    public class AccidentBean {
        private String date;

        public AccidentBean() {
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDisaster() {
            return disaster;
        }

        public void setDisaster(String disaster) {
            this.disaster = disaster;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        public AccidentBean(String date, String disaster, String city, Integer level) {
            this.date = date;
            this.disaster = disaster;
            this.city = city;
            this.level = level;
        }

        private String disaster;
        private String city;
        private Integer level;
    }
}
