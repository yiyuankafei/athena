package com.athena.common.util;

import com.madgag.gif.fmsware.AnimatedGifEncoder;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * https://blog.csdn.net/qq_41808217/article/details/103307213?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-3.control&dist_request_id=&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-3.control
 */
public class VideoUtil {

    /**
     * 截取视频指定帧生成gif
     *
     * @param videofile  视频文件
     * @param startFrame 开始帧
     * @param frameCount 截取帧数
     * @param frameRate  帧频率（默认：3）
     * @param margin     每截取一次跳过多少帧（默认：3）
     * @throws java.io.IOException 截取的长度超过视频长度
     */
    public static void buildGif(String videofile, int startFrame, int frameCount, Integer frameRate, Integer margin) throws IOException {
        if (margin == null) {
            margin = 3;
        }
        if (frameRate == null) {
            frameRate = 3;
        }
        FileOutputStream targetFile = new FileOutputStream(videofile.substring(0, videofile.lastIndexOf(".")) + ".gif");
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(videofile);
        Java2DFrameConverter converter = new Java2DFrameConverter();
        ff.start();
        System.out.println("总帧数：" + ff.getLengthInFrames());
        try {
            if (startFrame > ff.getLengthInFrames() & (startFrame + frameCount) > ff.getLengthInFrames()) {
                throw new RuntimeException("THE VIDEO IS TOO SHORT!");
            }
            ff.setFrameNumber(startFrame);
            AnimatedGifEncoder en = new AnimatedGifEncoder();
            en.setFrameRate(frameRate);
            en.start(targetFile);
            for (int i = 0; i < frameCount; i++) {
                en.addFrame(converter.convert(ff.grabImage()));
                ff.setFrameNumber(ff.getFrameNumber() + margin);
            }
            en.finish();
        } finally {
            ff.stop();
            ff.close();
        }
    }

    /**
     * 将图片旋转指定度
     *
     * @param bufferedimage 图片
     * @param degree        旋转角度
     * @return
     */
    public static BufferedImage rotateImage(BufferedImage bufferedimage, int degree) {
        // 得到图片宽度。
        int w = bufferedimage.getWidth();
        // 得到图片高度。
        int h = bufferedimage.getHeight();
        // 得到图片透明度。
        int type = bufferedimage.getColorModel().getTransparency();
        // 空的图片。
        BufferedImage img;
        // 空的画笔。
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w, h, type))
                .createGraphics()).setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        // 旋转，degree是整型，度数，比如垂直90度。
        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
        // 从bufferedimagecopy图片至img，0,0是img的坐标。
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        // 返回复制好的图片，原图片依然没有变，没有旋转，下次还可以使用。
        return img;
    }

    /**
     * 截取视频指定帧保存为指定格式的图片（图片保存在视频同文件夹下）
     *
     * @param videofile  视频地址
     * @param imgSuffix  图片格式
     * @param indexFrame 第几帧（默认：5）
     * @throws Exception
     */
    public static void fetchFrame(String videofile, String imgSuffix, Integer indexFrame) throws Exception {
        if (indexFrame == null) {
            indexFrame = 5;
        }
        Integer suffixIndex = videofile.lastIndexOf(".");
        File targetFile = new File((suffixIndex != -1 ? videofile.substring(0, suffixIndex) : videofile) + imgSuffix);

        try (FFmpegFrameGrabber ff = new FFmpegFrameGrabber(videofile);
             OutputStream outputStream = new FileOutputStream(targetFile)) {
            ff.start();
            ff.setFrameNumber(indexFrame);
            Frame f = ff.grabImage();
            Java2DFrameConverter converter = new Java2DFrameConverter();
            BufferedImage fecthedImage = converter.getBufferedImage(f);
            ImageIO.write(fecthedImage, "jpg", outputStream);
        }
    }


    public static void main(String[] args) {
        try {
            //fetchFrame("F:\\架构\\(010)Netty课程入门网络编程中级篇\\20160106001.mp4", ".jpg", 100);
            buildGif("F:\\架构\\(010)Netty课程入门网络编程中级篇\\20160106001.mp4", 300, 100, 15, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
