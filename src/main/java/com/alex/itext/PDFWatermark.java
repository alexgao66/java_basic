package com.alex.itext;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.*;
import org.apache.commons.io.FileUtils;
import sun.font.FontFamily;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by gaojun on 16/3/11.
 */
public class PDFWatermark {

    public static void main(String[] args) throws IOException, DocumentException {
//        overWatermark();

        /*String FILE_DIR = "/Users/gaojun/work/开放平台/";

        PdfReader reader = new PdfReader(FILE_DIR + "美团酒店-直连开放平台_v0.1.pdf");
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(FILE_DIR
                + "/美团酒店-直连开放平台_v0.1_new.pdf"));
        PdfContentByte under = stamper.getUnderContent(1);
        Font f = new Font();
        Phrase p = new Phrase("This watermark is added UNDER the existing content", f);
        ColumnText.showTextAligned(under, Element.ALIGN_CENTER, p, 297, 550, 0);

        stamper.close();
        reader.close();*/
    }

    public static void overWatermark(byte[] fileBytes, String outPath) throws IOException, DocumentException {
        /*String FILE_DIR = "/Users/gaojun/work/开放平台/";

        PdfReader reader = new PdfReader(FILE_DIR + "美团酒店-直连开放平台_v0.1_noWatermark.pdf");
        PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(FILE_DIR
                + "/美团酒店-直连开放平台_v0.1.pdf"));*/

        PdfReader reader = new PdfReader(fileBytes);
        PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(outPath));

        int n = reader.getNumberOfPages();

        PdfGState gs1 = new PdfGState();
        gs1.setFillOpacity(0.1f);

        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI,
                BaseFont.EMBEDDED);
        for(int i = 1; i <= n; i++) {

            //文字水印
            PdfContentByte over = stamp.getOverContent(i);
            over.beginText();
            over.setFontAndSize(bf, 18);
            over.setTextMatrix(30, 30);
            over.setGState(gs1);
            over.showTextAligned(Element.ALIGN_LEFT, "meituan.com", 230, 450, 45);
            over.endText();

        }
        stamp.close();
        reader.close();

    }

}
