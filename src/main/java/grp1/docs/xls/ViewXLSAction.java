package grp1.docs.xls;

/**
 * Created by admin on 23.05.2017.
 */

import com.opensymphony.xwork2.ActionSupport;
import grp1.docs.DocumentModel;
import grp1.docs.DocumentTypes;
import grp1.docs.pdf.PDFGenerator;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionSupport;
import grp1.docs.DocumentModel;
import grp1.docs.DocumentTypes;
import grp1.docs.model.CityDocumentModel;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.print.Doc;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Map;

public class ViewXLSAction extends ActionSupport
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
        ByteArrayOutputStream baosXLS = new XLSGenerator().generateXLS(model);
        String filename = type+".xls";

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename="+filename);
        response.setContentLength(baosXLS.size());

        OutputStream os = response.getOutputStream();
        os.write(baosXLS.toByteArray());
        os.flush();
        os.close();
        baosXLS.reset();

        return NONE;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }
}