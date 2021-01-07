package com.athena.common.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PdfBoxUtil {

    // pdf转png图片  dpi表示图片清晰度 200-300之间
    public static String pdf2Image(String PdfFilePath, String dstImgFolder, int dpi) {

        long l = System.currentTimeMillis();
        File file = new File(PdfFilePath);
        PDDocument pdDocument;
        try {
            String imgPDFPath = file.getParent();
            int dot = file.getName().lastIndexOf('.');
            String imagePDFName = file.getName().substring(0, dot); // 获取图片文件名
            String imgFolderPath = dstImgFolder;

            pdDocument = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(pdDocument);
            /* dpi越大转换后越清晰，相对转换速度越慢 */
            StringBuffer imgFilePath = null;

            String imgFilePathPrefix = imgFolderPath + File.separator + imagePDFName;
            imgFilePath = new StringBuffer();
            imgFilePath.append(imgFilePathPrefix);
            imgFilePath.append(".png");
            File dstFile = new File(imgFilePath.toString());
            BufferedImage image = renderer.renderImageWithDPI(0, dpi);
            int pageWidth = image.getWidth();
            int pageHeight = image.getHeight();
            System.out.println(pageWidth);
            System.out.println(pageHeight);
            BufferedImage imageResult = new BufferedImage(pageWidth, pageHeight * pdDocument.getNumberOfPages(), BufferedImage.TYPE_INT_RGB);
            int[] rgb = image.getRGB(0, 0, pageWidth, pageHeight, null, 0, pageWidth);
            imageResult.setRGB(0, 0, pageWidth, pageHeight, rgb, 0, pageWidth);

            for (int i = 1; i < pdDocument.getNumberOfPages(); i++) {
                System.out.println("循环第" + i + "次");
                image = renderer.renderImageWithDPI(i, dpi);
                System.out.println("渲染第" + i + "次");
                rgb = image.getRGB(0, 0, pageWidth, pageHeight, null, 0, pageWidth);
                imageResult.setRGB(0, pageHeight * i, pageWidth, pageHeight, rgb, 0, pageWidth);
            }

            ImageIO.write(imageResult, "png", dstFile);
            System.out.println("PDF文档转PNG图片成功！");
            long l2 = System.currentTimeMillis();
            System.out.println(l2 - l);
            return imgFilePath.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        pdf2Image("C:\\Users\\Administrator\\Desktop\\test\\单作品.pdf",
                "C:\\Users\\Administrator\\Desktop\\test",120);
    }

}
