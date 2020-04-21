package com.alimama.server.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by PengWX on 2020/4/20.
 */
public class PdfUtils {

    /**
     * 创建PDF文档.
     *
     * @param filename 新PDF文档的路径(全路径 c://hello.pdf)
     * @param filename content 文件内容
     * @throws DocumentException
     * @throws IOException
     */
    public static void createPdf(String filename, String content) throws DocumentException, IOException {
        // 第一步 创建文档实例
        // 自定义页面大小使用
        Rectangle pagesize = new Rectangle(400f, 720f);
        //设置文档相对于页面的长,宽,高
        Document document = new Document(pagesize, 36f, 72f, 108f, 180f);
        // 第二步 获取PdfWriter实例
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph(content));
        // step 5
        document.close();
    }
}
