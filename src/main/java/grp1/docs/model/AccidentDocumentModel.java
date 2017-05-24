package grp1.docs.model;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import grp1.dao.AccidentDao;
import grp1.docs.DocumentHelper;
import grp1.docs.DocumentModel;
import grp1.model.Accident;
import org.apache.poi.ss.usermodel.Workbook;
import org.supercsv.io.ICsvBeanWriter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    }

    @Override
    public void buildPdf(Document doc) throws DocumentException, SQLException, IOException {

    }

    @Override
    public void buildXls(Workbook workbook) throws Exception {

    }

    @Override
    public void buildCsv(ICsvBeanWriter writer) throws IOException, SQLException {

    }
}
