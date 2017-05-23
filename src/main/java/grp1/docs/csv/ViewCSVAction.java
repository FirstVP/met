package grp1.docs.csv;

import com.opensymphony.xwork2.ActionSupport;
import grp1.docs.DocumentModel;
import grp1.docs.DocumentTypes;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 * Created by admin on 23.05.2017.
 */
public class ViewCSVAction extends ActionSupport
        implements ServletResponseAware {

    private static final long serialVersionUID = 1L;
    private HttpServletResponse response;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    @Override
    public String execute() throws Exception {
        DocumentModel model = DocumentTypes.getTypes().get(type);
        String filename = type+".csv";

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename="+filename);

        Writer writer = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
        writer.write('\uFEFF');

        ICsvBeanWriter csvWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
        model.buildCsv(csvWriter);
        csvWriter.flush();
        csvWriter.close();

        return NONE;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }
}
