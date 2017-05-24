package grp1.docs.pdf;

import com.opensymphony.xwork2.ActionSupport;
import grp1.docs.DocumentModel;
import grp1.docs.DocumentTypes;
import grp1.docs.model.WeatherDocumentModel;
import grp1.docs.DocumentHelper;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

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


    public Boolean getHasProtection() {
        return hasProtection;
    }

    public void setHasProtection(Boolean hasProtection) {
        this.hasProtection = hasProtection;
    }

    private Boolean hasProtection;

    @Override
    public String execute() throws Exception {
        ServletContext context = ServletActionContext.getServletContext();
        DocumentModel model = DocumentHelper.getDocumentModel(type, context);
        ByteArrayOutputStream baosPDF = new PDFGenerator().generatePDF(model, hasProtection);
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