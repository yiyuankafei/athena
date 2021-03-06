package com.athena.common.test.menghuan;

import com.google.common.primitives.Floats;
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
import java.util.Map;
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
        renderer.setBaseShapesVisible(false); // series 点（即数据点）可见
        renderer.setBaseLinesVisible(true); // series 点（即数据点）间有连线可见
        renderer.setUseSeriesOffset(false); // 设置偏移量
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);//setSeriesStroke
        renderer.setSeriesStroke(0, new BasicStroke(3F));
        renderer.setSeriesStroke(1, new BasicStroke(3F));
        renderer.setSeriesStroke(2, new BasicStroke(3F));
        renderer.setSeriesStroke(3, new BasicStroke(3F));
        renderer.setSeriesStroke(4, new BasicStroke(3F));
        renderer.setSeriesStroke(5, new BasicStroke(3F));
        //renderer.setSeriesOutlineStroke(0, new BasicStroke(4.5F));
        jfreechart.setTitle(new TextTitle("无催心浪收益统计(5只须弥10只怪)", new Font("隶书", Font.BOLD, 25)));
        return jfreechart;
    }

    /**
     * 创建CategoryDataset对象
     *
     */
    public static CategoryDataset createDataset(LinkedHashMap<Integer, Float> map) {
        String[] rowKeys = {"111","2222","第三","444","55","666","777"};
        /*String[] colKeys = {"0:00", "1:00", "2:00", "7:00", "8:00", "9:00",
                "10:00", "11:00", "12:00", "13:00", "16:00", "20:00", "21:00",
                "23:00"};*/
        String[] colKeys = map.keySet().stream().map(item -> String.valueOf(item)).collect(Collectors.toList()).toArray(new String[0]);
        Float[] Floats = map.values().toArray(new Float[0]);
        double[] ds1 = new double[Floats.length];
        double[] ds2 = new double[Floats.length];
        double[] ds3 = new double[Floats.length];
        double[] ds4 = new double[Floats.length];
        double[] ds5 = new double[Floats.length];
        double[] ds6 = new double[Floats.length];
        double[] ds7 = new double[Floats.length];
        for (int i = 0; i < Floats.length; i++) {
            ds1[i] = Floats[i];
            ds2[i] = Floats[i] + 0.02;
            ds3[i] = Floats[i] + 0.3;
            ds4[i] = Floats[i] + 0.1;
            ds5[i] = Floats[i] + 0.2;
            ds6[i] = Floats[i] - 0.1;
            ds7[i] = Floats[i] - 0.2;
        }
        double[][] data = {ds1,ds2,ds3,ds4,ds5,ds6,ds7};
        // 或者使用类似以下代码
        // DefaultCategoryDataset categoryDataset = new
        // DefaultCategoryDataset();
        // categoryDataset.addValue(10, "rowKey", "colKey");
        return DatasetUtilities.createCategoryDataset(rowKeys, colKeys, data);
    }
    public static void exportResult(Map<String , LinkedHashMap<Integer, Float>> map) throws Exception {
        String[] rowKeys = {"0层","1层","2层","3层","4层","5层"};
        //LinkedHashMap<Integer, Float> m1 = map.get("无波");
        LinkedHashMap<Integer, Float> m2 = map.get("0层");
        LinkedHashMap<Integer, Float> m3 = map.get("1层");
        LinkedHashMap<Integer, Float> m4 = map.get("2层");
        LinkedHashMap<Integer, Float> m5 = map.get("3层");
        LinkedHashMap<Integer, Float> m6 = map.get("4层");
        LinkedHashMap<Integer, Float> m7 = map.get("5层");
        String[] colKeys = m2.keySet().stream().map(item -> String.valueOf(item)).collect(Collectors.toList()).toArray(new String[0]);
        //Float[] f1 = m1.values().toArray(new Float[0]);
        Float[] f2 = m2.values().toArray(new Float[0]);
        Float[] f3 = m3.values().toArray(new Float[0]);
        Float[] f4 = m4.values().toArray(new Float[0]);
        Float[] f5 = m5.values().toArray(new Float[0]);
        Float[] f6 = m6.values().toArray(new Float[0]);
        Float[] f7 = m7.values().toArray(new Float[0]);
        //double[] ds1 = new double[f1.length];
        double[] ds2 = new double[f2.length];
        double[] ds3 = new double[f3.length];
        double[] ds4 = new double[f4.length];
        double[] ds5 = new double[f5.length];
        double[] ds6 = new double[f6.length];
        double[] ds7 = new double[f7.length];
        for (int i = 0; i < f2.length; i++) {
            //ds1[i] = f1[i] * 100;
            ds2[i] = f2[i];
            ds3[i] = f3[i];
            ds4[i] = f4[i];
            ds5[i] = f5[i];
            ds6[i] = f6[i];
            ds7[i] = f7[i];
        }
        double[][] data = {ds2,ds3,ds4,ds5,ds6,ds7};
        CategoryDataset c = DatasetUtilities.createCategoryDataset(rowKeys, colKeys, data);
        JFreeChart freeChart = JFreeChat.createChart(c);
        // 步骤3：将JFreeChart对象输出到文件，Servlet输出流等
        JFreeChat.saveAsFile(freeChart, "E:\\line.jpg", 1200, 800);
    }
}
