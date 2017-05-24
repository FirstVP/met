package grp1.docs;

import com.itextpdf.text.DocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.supercsv.io.ICsvBeanWriter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public abstract class DocumentModel {

    public abstract void initialize(Object value) throws SQLException;

    public abstract void buildPdf(com.itextpdf.text.Document doc) throws DocumentException, SQLException;

    public abstract void buildXls(Workbook workbook) throws Exception;

    public abstract void buildCsv(ICsvBeanWriter writer) throws IOException, SQLException;
}