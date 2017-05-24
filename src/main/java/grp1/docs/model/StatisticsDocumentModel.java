package grp1.docs.model;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import grp1.dao.CityDao;
import grp1.dao.WeatherDao;
import grp1.docs.DocumentHelper;
import grp1.docs.DocumentModel;
import grp1.docs.pdf.PDFGenerator;
import grp1.model.City;
import grp1.model.Weather;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.supercsv.exception.SuperCsvException;
import org.supercsv.io.ICsvBeanWriter;

import javax.print.attribute.standard.Compression;
import javax.servlet.ServletContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;

/**
 * Created by admin on 24.05.2017.
 */
@SuppressWarnings("ALL")
public class StatisticsDocumentModel extends DocumentModel {
    private int code;
    private List<Weather> weathers;
    private City city;
    private Integer maxTemp;
    private Integer minTemp;
    private Double avgTemp;
    private Double avgWind;
    private Double avgPressure;

    public StatisticsDocumentModel(int code)
    {
        this.code = code;
    }
    @Override
    public void initialize(Object value) throws SQLException {
        CityDao cityDao = new CityDao();
        city = cityDao.getCityById(code);
        if (city == null)
            city = new City(0,0,"Undefined",0,0,0);
        List<Weather> list = new WeatherDao().getAllWeathers();
        weathers = new ArrayList<Weather>();
        for (Weather weatherItem: list)
        {
            if ((weatherItem.getCityId() == city.getCityId()))
            {
                weathers.add(weatherItem);
            }
        }
        try
        {
            maxTemp = weathers.stream().mapToInt(o -> o.getTemp()).max().getAsInt();
            minTemp = weathers.stream().mapToInt(o -> o.getTemp()).min().getAsInt();
            avgTemp = DocumentHelper.round(weathers.stream().mapToInt(o -> o.getTemp()).average().getAsDouble(), 1);
            avgWind = DocumentHelper.round(weathers.stream().mapToInt(o -> o.getWind()).average().getAsDouble(), 1);
            avgPressure = DocumentHelper.round(weathers.stream().mapToInt(o -> o.getPressure()).average().getAsDouble(), 1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    @Override
    public void buildPdf(Document doc) throws DocumentException, SQLException, IOException {
        try {
            initialize(null);
            addTitlePage(doc);
            Font red = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.RED);
            Chunk text = new Chunk("Temperature", red);
            Paragraph firstTitle = new Paragraph(text);
            doc.add(firstTitle);
            Image temp = Image.getInstance(getImage(new java.net.URL("http://pogoda.by/mg/366/noaa_T"+city.getCode().toString()+".gif")));
            doc.add(temp);
            Font blue = new Font(Font.FontFamily.HELVETICA, 12, Font.UNDEFINED, BaseColor.BLUE);
            Chunk secondText = new Chunk("Wind", blue);
            Paragraph secondTitle = new Paragraph(secondText);
            doc.add(secondTitle);
            Image hum = Image.getInstance(getImage(new java.net.URL("http://pogoda.by/mg/366/noaa_W"+city.getCode().toString()+".gif")));
            doc.add(hum);
            createTable(doc);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public byte[] getImage(URL url) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream is = url.openStream ();
        byte[] b = new byte[4096];
        int n;
        while ( (n = is.read(b)) > -1 ) {
            baos.write(b, 0, n);
        }
        return baos.toByteArray();
    }

    private void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        creteEmptyLine(preface, 1);
        preface.add(new Paragraph("Weather statistics: " + city.getName(), PDFGenerator.TIME_ROMAN));
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
        PdfPTable table = new PdfPTable(5);

        PdfPCell c1 = new PdfPCell(new Phrase("Maximum temperature, °C"));
        c1.setBackgroundColor(BaseColor.GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Minimum temperature, °C"));
        c1.setBackgroundColor(BaseColor.GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Average temperature, °C"));
        c1.setBackgroundColor(BaseColor.GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Average wind, m/s"));
        c1.setBackgroundColor(BaseColor.GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Average pressure, kPa"));
        c1.setBackgroundColor(BaseColor.GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);


            table.setWidthPercentage(100);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(maxTemp.toString());
            table.addCell(minTemp.toString());
            table.addCell(avgTemp.toString());
            table.addCell(avgWind.toString());
            table.addCell(avgPressure.toString());


        document.add(table);
    }


    @Override
    public void buildXls(Workbook workbook) throws Exception {
        initialize(null);
        Sheet sheet = workbook.createSheet("Statistics");

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
        headerTitle.createCell(0).setCellValue("Statistics");;
        headerTitle.getCell(0).setCellStyle(headerStyle);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));

        Row header = sheet.createRow(1);
        header.createCell(0).setCellValue("Max temperature");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("Min temperature");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("Avg temperature");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("Avg wind");
        header.getCell(3).setCellStyle(style);
        header.createCell(4).setCellValue("Avg pressure");
        header.getCell(4).setCellStyle(style);

        int rowCount = 2;

        CellStyle otherCellStyle = workbook.createCellStyle();
        otherCellStyle.setBorderBottom(BorderStyle.MEDIUM);
        otherCellStyle.setWrapText(true);

            Row commentRow = sheet.createRow(rowCount++);
            commentRow.createCell(0).setCellValue(maxTemp);
            commentRow.getCell(0).setCellStyle(otherCellStyle);
            int columnIndex = commentRow.getCell(0).getColumnIndex();

            sheet.autoSizeColumn(columnIndex);
            commentRow.createCell(1).setCellValue(minTemp);
            commentRow.getCell(1).setCellStyle(otherCellStyle);
            columnIndex = commentRow.getCell(1).getColumnIndex();
            sheet.autoSizeColumn(columnIndex);

            commentRow.createCell(2).setCellValue(avgTemp);
            commentRow.getCell(2).setCellStyle(otherCellStyle);
            columnIndex = commentRow.getCell(2).getColumnIndex();
            sheet.autoSizeColumn(columnIndex);

            commentRow.createCell(3).setCellValue(avgWind);
            commentRow.getCell(3).setCellStyle(otherCellStyle);
            columnIndex = commentRow.getCell(3).getColumnIndex();
            sheet.autoSizeColumn(columnIndex);

            commentRow.createCell(4).setCellValue(avgPressure);
            commentRow.getCell(4).setCellStyle(otherCellStyle);
            columnIndex = commentRow.getCell(4).getColumnIndex();
            sheet.autoSizeColumn(columnIndex);
    }

    @Override
    public void buildCsv(ICsvBeanWriter writer) throws IOException, SQLException {
        initialize(null);
        String[] header = {"MaxTemp", "MinTemp", "AvgTemp", "AvgWind", "AvgPressure"};
        writer.writeHeader(header);
            try {
                writer.write(new StatsBean(maxTemp, minTemp, avgTemp, avgWind, avgPressure), header);
            } catch (SuperCsvException exception) {
                System.out.println(exception.toString());
            }
    }

    public class StatsBean
    {
        private Integer maxTemp;

        public StatsBean() {
        }

        public StatsBean(Integer maxTemp, Integer minTemp, Double avgTemp, Double avgWind, Double avgPressure) {
            this.maxTemp = maxTemp;
            this.minTemp = minTemp;
            this.avgTemp = avgTemp;
            this.avgWind = avgWind;
            this.avgPressure = avgPressure;
        }

        public Integer getMaxTemp() {

            return maxTemp;
        }

        public void setMaxTemp(Integer maxTemp) {
            this.maxTemp = maxTemp;
        }

        public Integer getMinTemp() {
            return minTemp;
        }

        public void setMinTemp(Integer minTemp) {
            this.minTemp = minTemp;
        }

        public Double getAvgTemp() {
            return avgTemp;
        }

        public void setAvgTemp(Double avgTemp) {
            this.avgTemp = avgTemp;
        }

        public Double getAvgWind() {
            return avgWind;
        }

        public void setAvgWind(Double avgWind) {
            this.avgWind = avgWind;
        }

        public Double getAvgPressure() {
            return avgPressure;
        }

        public void setAvgPressure(Double avgPressure) {
            this.avgPressure = avgPressure;
        }

        private Integer minTemp;
        private Double avgTemp;
        private Double avgWind;
        private Double avgPressure;
    }
}
