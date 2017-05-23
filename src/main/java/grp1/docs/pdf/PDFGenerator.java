package grp1.docs.pdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import grp1.docs.DocumentModel;

public class PDFGenerator {
    private static Font TIME_ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font TIME_ROMAN_SMALL = new Font(
            Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    public ByteArrayOutputStream generatePDF(DocumentModel model) throws DocumentException, IOException {

        Document doc = new Document();
        ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
        PdfWriter pdfWriter = PdfWriter.getInstance(doc, baosPDF);


        try {
            doc.open();
            addMetaData(doc);
            addFormBlank(pdfWriter);
            addTitlePage(doc);

            model.buildPdf(doc);

        } catch(DocumentException de) {
            baosPDF.reset();
            throw de;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(doc != null) {
                doc.close();
            }

            if(pdfWriter != null) {
                pdfWriter.close();
            }
        }

        return baosPDF;
    }

    private void addFormBlank(PdfWriter writer) throws IOException, DocumentException {
        PdfContentByte canvas = writer.getDirectContentUnder();
        Image image = Image.getInstance("http://images.vfl.ru/ii/1495492681/2d99e04d/17319149.png");
        image.scaleAbsolute(PageSize.A4.getWidth(), PageSize.A4.getHeight());
        image.setAbsolutePosition(0, 0);
        canvas.addImage(image);
    }

    private static void addMetaData(Document document) {
        document.addTitle("PDF report");
        document.addSubject("PDF report");
        document.addAuthor("Trukhanovich");
        document.addCreator("Trukhanovich");
    }

    private static void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        creteEmptyLine(preface, 1);
        preface.add(new Paragraph("PDF Report", TIME_ROMAN));
        creteEmptyLine(preface, 1);
        document.add(preface);
    }

    private static void creteEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }


}