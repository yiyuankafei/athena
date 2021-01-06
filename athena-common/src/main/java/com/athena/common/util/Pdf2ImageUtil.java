package com.athena.common.util;

import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class Pdf2ImageUtil {

    public static void translate(String pdfPath, String path) throws Exception {
        Document document = new org.icepdf.core.pobjects.Document();
        document.setFile(pdfPath);
        //缩放比例
        float scale = 2.5f;
        //旋转角度
        float rotation = 0f;

        for (int i = 0; i < document.getNumberOfPages(); i++) {
            BufferedImage image = (BufferedImage)
                    document.getPageImage(i, GraphicsRenderingHints.SCREEN, org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
            RenderedImage rendImage = image;
            try {
                String imgName = i + ".png";
                System.out.println(imgName);
                File file = new File(path + imgName);
                ImageIO.write(rendImage, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.flush();
        }
        document.dispose();
    }

    public static void main(String[] args) throws Exception {
        String source = "C:\\Users\\Administrator\\Desktop\\证书更换\\pdf证书.pdf";
        String target = "C:\\Users\\Administrator\\Desktop\\证书更换\\图片证书";
        translate(source, target);
    }

}
