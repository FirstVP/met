package grp1.docs.model;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import grp1.dao.CityDao;
import grp1.docs.DocumentModel;
import grp1.docs.pdf.PDFGenerator;
import grp1.model.City;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.supercsv.exception.SuperCsvException;
import org.supercsv.io.ICsvBeanWriter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by admin on 23.05.2017.
 */
public class CityDocumentModel extends DocumentModel {
    List<City> cities;

    public CityDocumentModel() {

    }


    @Override
    public void initialize(Object value) throws SQLException {
        CityDao cityDao = new CityDao();
        cities = cityDao.getAllCities();
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
        preface.add(new Paragraph("Cities", PDFGenerator.TIME_ROMAN));
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

        PdfPCell c1 = new PdfPCell(new Phrase("Name"));
        c1.setBackgroundColor(BaseColor.GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Rise"));
        c1.setBackgroundColor(BaseColor.GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Square"));
        c1.setBackgroundColor(BaseColor.GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Population"));
        c1.setBackgroundColor(BaseColor.GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        for (City city: cities)
        {
            table.setWidthPercentage(100);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(city.getName());
            table.addCell(city.getRise().toString());
            table.addCell(city.getSquare().toString());
            table.addCell(city.getPopulation().toString());
        }

        document.add(table);
    }

    @Override
    public void buildXls(Workbook workbook) throws Exception {
        initialize(null);
        Sheet sheet = workbook.createSheet("Cities");

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
        headerTitle.createCell(0).setCellValue("Cities");;
        headerTitle.getCell(0).setCellStyle(headerStyle);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));

        Row header = sheet.createRow(1);
        header.createCell(0).setCellValue("Name");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("Rise");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("Square");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("Population");
        header.getCell(3).setCellStyle(style);

        int rowCount = 2;

        CellStyle otherCellStyle = workbook.createCellStyle();
        otherCellStyle.setBorderBottom(BorderStyle.MEDIUM);
        otherCellStyle.setWrapText(true);

        for (City city : cities) {
            Row commentRow = sheet.createRow(rowCount++);
            commentRow.createCell(0).setCellValue(city.getName());
            commentRow.getCell(0).setCellStyle(otherCellStyle);
            int columnIndex = commentRow.getCell(0).getColumnIndex();

            sheet.autoSizeColumn(columnIndex);
            commentRow.createCell(1).setCellValue(city.getRise());
            commentRow.getCell(1).setCellStyle(otherCellStyle);
            columnIndex = commentRow.getCell(1).getColumnIndex();
            sheet.autoSizeColumn(columnIndex);

            commentRow.createCell(2).setCellValue(city.getSquare());
            commentRow.getCell(2).setCellStyle(otherCellStyle);
            columnIndex = commentRow.getCell(2).getColumnIndex();
            sheet.autoSizeColumn(columnIndex);

            commentRow.createCell(3).setCellValue(city.getPopulation());
            commentRow.getCell(3).setCellStyle(otherCellStyle);
            columnIndex = commentRow.getCell(3).getColumnIndex();
            sheet.autoSizeColumn(columnIndex);

        }
    }

    @Override
    public void buildCsv(ICsvBeanWriter writer) throws IOException, SQLException {
        initialize(null);
        String[] header = {"Name", "Rise", "Square", "Population"};
        writer.writeHeader(header);
        for (City city : cities) {
            try {
                writer.write(new CityBean(city.getName(), city.getRise(), city.getSquare(), city.getPopulation()), header);
            } catch (SuperCsvException exception) {
                System.out.println(exception.toString());
            }
        }
    }

    public class CityBean {
        public CityBean() {
        }

        public CityBean(String name, Integer rise, Integer square, Integer population) {
            this.name = name;
            this.rise = rise;
            this.square = square;
            this.population = population;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getRise() {
            return rise;
        }

        public void setRise(Integer rise) {
            this.rise = rise;
        }

        public Integer getSquare() {
            return square;
        }

        public void setSquare(Integer square) {
            this.square = square;
        }

        public Integer getPopulation() {
            return population;
        }

        public void setPopulation(Integer population) {
            this.population = population;
        }

        private String name;
        private Integer rise;
        private Integer square;
        private Integer population;
    }
}
