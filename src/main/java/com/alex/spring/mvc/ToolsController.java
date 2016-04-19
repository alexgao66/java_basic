package com.alex.spring.mvc;

import com.alex.itext.PDFWatermark;
import com.alibaba.fastjson.JSONObject;
import com.lowagie.text.DocumentException;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * Created by gaojun on 16/3/15.
 */

@Controller
@RequestMapping("/tools/")
public class ToolsController {

//    @Setter
//    @Getter
//    private File pdfFile;

    @ResponseBody
    @RequestMapping(value = "/uploadPdf", method= RequestMethod.POST)
    public JSONObject uploadPdf(HttpServletRequest request, @RequestParam("pdfFile") MultipartFile pdfFile) throws IOException, DocumentException {
        String path = request.getSession().getServletContext().getRealPath("/").concat("/watermark.pdf");
        PDFWatermark.overWatermark(pdfFile.getBytes(),path);

        JSONObject obj = new JSONObject();
        obj.put("success", true);
        obj.put("msg", "access in browsser for watermark pdf:[host:port/watermark.pdf]");
        System.out.println("path:" + path);
        return obj;
    }

    @RequestMapping(value = "/downloadPdf")
    public ResponseEntity<byte[]> download(@RequestParam String path) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
//        headers.setContentDispositionFormData("inline", "dict.jpg");//attachment
//        headers.set(Constant2.CONTENT_DISPOSI TION,WebServletUtil.getContentDisposition(true, "dict.jpg"));
        return new ResponseEntity<byte[]>(ToolsController.getBytes4File(path),
                headers, HttpStatus.CREATED);
    }

    public static byte[] getBytes4File(String filePath) throws IOException {

        InputStream in = null;
        BufferedInputStream buffer = null;
        DataInputStream dataIn = null;
        ByteArrayOutputStream bos = null;
        DataOutputStream dos = null;
        byte[] bArray = null;
        try {
            in = new FileInputStream(filePath);
            buffer = new BufferedInputStream(in);
            dataIn = new DataInputStream(buffer);
            bos = new ByteArrayOutputStream();
            dos = new DataOutputStream(bos);
            byte[] buf = new byte[1024];
            while (true) {
                int len = dataIn.read(buf);
                if (len < 0)
                    break;
                dos.write(buf, 0, len);
            }
            bArray = bos.toByteArray();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        } finally {

            if (in != null)
                in.close();
            if (dataIn != null)
                dataIn.close();
            if (buffer != null)
                buffer.close();
            if (bos != null)
                bos.close();
            if (dos != null)
                dos.close();
        }

        return bArray;
    }

}
