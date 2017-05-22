package grp1.docs.model;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import grp1.dao.CityDao;
import grp1.docs.DocumentModel;
import grp1.model.City;
import org.apache.poi.ss.usermodel.Workbook;
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
    public void initialize() throws SQLException {
        CityDao cityDao = new CityDao();
        cities = cityDao.getAllCities();
    }

    @Override
    public void buildPdf(Document doc) throws DocumentException, SQLException {
        initialize();
        createTable(doc);
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

    }

    @Override
    public void buildCsv(ICsvBeanWriter writer) throws IOException {

    }
}
