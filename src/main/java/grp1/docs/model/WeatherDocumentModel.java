package grp1.docs.model;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import grp1.dao.CityDao;
import grp1.dao.TypeDao;
import grp1.dao.WeatherDao;
import grp1.docs.DocumentHelper;
import grp1.docs.DocumentModel;
import grp1.docs.pdf.PDFGenerator;
import grp1.model.City;
import grp1.model.Type;
import grp1.model.Weather;
import org.apache.poi.ss.usermodel.Workbook;
import org.supercsv.io.ICsvBeanWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

/**
 * Created by admin on 24.05.2017.
 */
public class WeatherDocumentModel extends DocumentModel {

    private int code;
    private List<Weather> weathers;
    private City city;
    private ServletContext context;

    public WeatherDocumentModel (int code, ServletContext context)
    {
        this.context = context;
        this.code = code;
    }
    @Override
    public void initialize(Object value) throws SQLException {
        CityDao cityDao = new CityDao();
        city = cityDao.getCityById(code);
        if (city == null)
            city = new City(0,0,"Undefined",0,0,0);
        List<Weather> list = new WeatherDao().getAllWeathers();
        List <Type> types = new TypeDao().getAllTypes();
        weathers = new ArrayList<Weather>();
        for (Weather weatherItem: list)
        {
            if ((weatherItem.getCityId() == city.getCityId()) && (DocumentHelper.isDateInCurrentWeek(weatherItem.getDate())))
            {
                weathers.add(weatherItem);

                for (Type typeItem: types)
                {
                    if (weatherItem.getTypeId() == typeItem.getTypeId())
                    {
                        weatherItem.setType(typeItem);
                        break;
                    }
                }

            }

        }
    }

    @Override
    public void buildPdf(Document doc) throws DocumentException, SQLException, IOException {
        initialize(null);
        addTitlePage(doc);
        createTable(doc);
    }

    private void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        creteEmptyLine(preface, 1);
        preface.add(new Paragraph("Weather forecast: "+city.getName(), PDFGenerator.TIME_ROMAN));
        creteEmptyLine(preface, 1);
        document.add(preface);
    }

    private static void creteEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private void createTable(Document document) throws DocumentException, IOException {
        Paragraph paragraph = new Paragraph();
        creteEmptyLine(paragraph, 2);
        document.add(paragraph);
        PdfPTable table = new PdfPTable(5);

        PdfPCell c1 = new PdfPCell(new Phrase("Date"));
        c1.setBackgroundColor(BaseColor.GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Temperature, °C"));
        c1.setBackgroundColor(BaseColor.GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Condition"));
        c1.setBackgroundColor(BaseColor.GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Wind, m/s"));
        c1.setBackgroundColor(BaseColor.GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Pressure, kPa"));
        c1.setBackgroundColor(BaseColor.GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        for (Weather weather: weathers)
        {
            table.setWidthPercentage(100);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

            Calendar calendar= GregorianCalendar.getInstance();
            calendar.setTime(weather.getDate());
            String dayOfWeek = calendar.getDisplayName( Calendar.DAY_OF_WEEK ,Calendar.LONG, Locale.ENGLISH);

            Paragraph paragraphDate = new Paragraph();
            paragraphDate.add(dayOfWeek+"\n");
            paragraphDate.add(weather.getDate().toString());
            table.addCell(paragraphDate);

            table.addCell(weather.getTemp().toString());

            String path = context.getRealPath(weather.getType().getImage());
            Image image = Image.getInstance(path);
            image.setWidthPercentage(20);
            image.setAlignment(Element.ALIGN_CENTER);
            PdfPCell cell = new PdfPCell();
            cell.addElement(image);
            Paragraph p = new Paragraph(weather.getType().getName());
            p.setAlignment( Element.ALIGN_CENTER);
            cell.addElement(p);
            table.addCell(cell);

            table.addCell(weather.getWind().toString());
            table.addCell(weather.getPressure().toString());
        }

        document.add(table);
    }

    @Override
    public void buildXls(Workbook workbook) throws Exception {

    }

    @Override
    public void buildCsv(ICsvBeanWriter writer) throws IOException, SQLException {

    }
}
