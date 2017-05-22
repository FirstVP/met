package grp1.docs.pdf;

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

public class ViewPDFAction extends ActionSupport
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
        ByteArrayOutputStream baosPDF = new PDFGenerator().generatePDF(model);
        String filename = type+".pdf";

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition",
                "inline; filename=" + filename);
        response.setContentLength(baosPDF.size());

        OutputStream os = response.getOutputStream();
        os.write(baosPDF.toByteArray());
        os.flush();
        os.close();
        baosPDF.reset();

        return NONE;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }
}