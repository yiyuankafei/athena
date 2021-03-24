package com.athena.common.test.menghuan;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.stream.Collectors;

public class JFreeChat {
    /**
     * 创建JFreeChart Line Chart（折线图）
     */
    public static void main(String[] args) throws Exception {
       /* // 步骤1：创建CategoryDataset对象（准备数据）
        CategoryDataset dataset = createDataset(new LinkedHashMap());
        // 步骤2：根据Dataset 生成JFreeChart对象，以及做相应的设置
        JFreeChart freeChart = createChart(dataset);
        // 步骤3：将JFreeChart对象输出到文件，Servlet输出流等
        saveAsFile(freeChart, "E:\\line.jpg", 1200, 800);*/
       Random random = new Random();
       for (int i = 0; i<1000;i++) {
           int j = random.nextInt(6);
           System.out.println(j);
       }
    }

    // 保存为文件
    public static void saveAsFile(JFreeChart chart, String outputPath,
                                  int weight, int height) {
        FileOutputStream out = null;
        try {
            File outFile = new File(outputPath);
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            out = new FileOutputStream(outputPath);
            // 保存为PNG
            // ChartUtilities.writeChartAsPNG(out, chart, 600, 400);
            // 保存为JPEG
            ChartUtilities.writeChartAsJPEG(out, chart, 1200, 800);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
    }

    // 根据CategoryDataset创建JFreeChart对象
    public static JFreeChart createChart(CategoryDataset categoryDataset) throws Exception {
        // 创建JFreeChart对象：ChartFactory.createLineChart
        JFreeChart jfreechart = ChartFactory.createLineChart("不同类别按小时计算拆线图", // 标题
                "怪物血量", // categoryAxisLabel （category轴，横轴，X轴标签）
                "收益", // valueAxisLabel（value轴，纵轴，Y轴的标签）
                categoryDataset, // dataset
                PlotOrientation.VERTICAL, true, // legend
                false, // tooltips
                false); // URLs
        // 使用CategoryPlot设置各种参数。以下设置可以省略。
        CategoryPlot plot = (CategoryPlot)jfreechart.getPlot();
        // 背景色 透明度
        plot.setBackgroundAlpha(0.5f);
        // 前景色 透明度
        plot.setForegroundAlpha(0.5f);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setVisible(true);
        plot.setDomainAxis(domainAxis);
        ValueAxis rAxis = plot.getRangeAxis();plot.getDomainAxis();
        domainAxis.setLabelFont(new Font("黑体",Font.BOLD,18)); //设置X轴的标题文字
        rAxis.setLabelFont(new Font("黑体",Font.BOLD,18));  //设置Y轴的标题文字


        // 其他设置 参考 CategoryPlot类
        LineAndShapeRenderer renderer = (LineAndShapeRenderer)plot.getRenderer();
        renderer.setBaseShapesVisible(true); // series 点（即数据点）可见
        renderer.setBaseLinesVisible(true); // series 点（即数据点）间有连线可见
        renderer.setUseSeriesOffset(true); // 设置偏移量
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);//setSeriesStroke
        renderer.setSeriesStroke(0, new BasicStroke(3F));
        //renderer.setSeriesOutlineStroke(0, new BasicStroke(4.5F));
        jfreechart.setTitle(new TextTitle("无催心浪收益统计(5只须弥10只怪)", new Font("隶书", Font.BOLD, 25)));
        return jfreechart;
    }

    /**
     * 创建CategoryDataset对象
     *
     */
    public static CategoryDataset createDataset(LinkedHashMap<Integer, Float> map) {
        String[] rowKeys = {""};
        /*String[] colKeys = {"0:00", "1:00", "2:00", "7:00", "8:00", "9:00",
                "10:00", "11:00", "12:00", "13:00", "16:00", "20:00", "21:00",
                "23:00"};*/
        String[] colKeys = map.keySet().stream().map(item -> String.valueOf(item)).collect(Collectors.toList()).toArray(new String[0]);
        Float[] Floats = map.values().toArray(new Float[0]);
        double[] ds = new double[Floats.length];
        for (int i = 0; i < Floats.length; i++) {
            ds[i] = Floats[i];
        }
        double[][] data = {ds,};
        // 或者使用类似以下代码
        // DefaultCategoryDataset categoryDataset = new
        // DefaultCategoryDataset();
        // categoryDataset.addValue(10, "rowKey", "colKey");
        return DatasetUtilities.createCategoryDataset(rowKeys, colKeys, data);
    }
}
