package grp1.docs.model;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import grp1.dao.NewsDao;
import grp1.docs.DocumentHelper;
import grp1.docs.DocumentModel;
import grp1.docs.pdf.PDFGenerator;
import grp1.model.News;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.supercsv.exception.SuperCsvException;
import org.supercsv.io.ICsvBeanWriter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 24.05.2017.
 */
public class NewsDocumentModel extends DocumentModel {
    List<News> newsList;
    @Override
    public void initialize(Object value) throws SQLException {
        NewsDao newsDao = new NewsDao();
        List<News> items = newsDao.getAllNews();
        newsList = new ArrayList<News>();
        for (News item: items)
        {
            if (DocumentHelper.isDateInCurrentWeek(item.getDate()))
                newsList.add(item);
        }
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
        preface.add(new Paragraph("Latest news", PDFGenerator.TIME_ROMAN));
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
        PdfPTable table = new PdfPTable(3);

        PdfPCell c1 = new PdfPCell(new Phrase("Title"));
        c1.setBackgroundColor(BaseColor.GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Text"));
        c1.setBackgroundColor(BaseColor.GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Date"));
        c1.setBackgroundColor(BaseColor.GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        for (News newsItem: newsList)
        {
            table.setWidthPercentage(100);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(newsItem.getTitle());
            table.addCell(newsItem.getBrief());
            table.addCell(newsItem.getDate().toString());
        }

        document.add(table);
    }

    @Override
    public void buildXls(Workbook workbook) throws Exception {
        initialize(null);
        Sheet sheet = workbook.createSheet("Cities");
        //setDefaultColumnWidth(60);

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
        header.createCell(0).setCellValue("Title");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("Text");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("Date");
        header.getCell(2).setCellStyle(style);

        int rowCount = 2;

        CellStyle otherCellStyle = workbook.createCellStyle();
        otherCellStyle.setBorderBottom(BorderStyle.MEDIUM);
        otherCellStyle.setWrapText(true);

        for (News item : newsList) {
            Row commentRow = sheet.createRow(rowCount++);
            commentRow.createCell(0).setCellValue(item.getTitle());
            commentRow.getCell(0).setCellStyle(otherCellStyle);
            int columnIndex = commentRow.getCell(0).getColumnIndex();

            sheet.autoSizeColumn(columnIndex);
            commentRow.createCell(1).setCellValue(item.getBrief());
            commentRow.getCell(1).setCellStyle(otherCellStyle);
            columnIndex = commentRow.getCell(1).getColumnIndex();
           // sheet.autoSizeColumn(columnIndex);
            sheet.setColumnWidth(1, 10250);
            commentRow.createCell(2).setCellValue(item.getDate().toString());
            commentRow.getCell(2).setCellStyle(otherCellStyle);
            columnIndex = commentRow.getCell(2).getColumnIndex();
            sheet.autoSizeColumn(columnIndex);

        }

    }

    @Override
    public void buildCsv(ICsvBeanWriter writer) throws IOException, SQLException {
        initialize(null);
        String[] header = {"Title", "Text", "Date"};
        writer.writeHeader(header);
        for (News item : newsList) {
            try {
                writer.write(new NewsBean(item.getTitle(), item.getBrief(), item.getDate().toString()), header);
            } catch (SuperCsvException exception) {
                System.out.println(exception.toString());
            }
        }
    }

    public class NewsBean
    {
        private String title;

        public NewsBean() {
        }

        public NewsBean(String title, String text, String date) {
            this.title = title;
            this.text = text;
            this.date = date;
        }

        public String getTitle() {
            return title;

        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        private String text;
        private String date;
    }
}
