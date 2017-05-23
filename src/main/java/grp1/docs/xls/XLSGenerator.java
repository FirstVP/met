package grp1.docs.xls;

import grp1.docs.DocumentModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by admin on 23.05.2017.
 */
public class XLSGenerator {
    public ByteArrayOutputStream generateXLS(DocumentModel model) throws IOException {

        Workbook workbook = new HSSFWorkbook();
        ByteArrayOutputStream baosXLS = new ByteArrayOutputStream();


        try {

            model.buildXls(workbook);
            workbook.write(baosXLS);

        }  catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(workbook != null) {
                workbook.close();
            }
        }

        return baosXLS;
    }
}
