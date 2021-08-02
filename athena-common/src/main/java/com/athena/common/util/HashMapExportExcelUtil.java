package com.athena.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class HashMapExportExcelUtil {
    public static <T> void exportExcel(List<T> data, LinkedHashMap<String, String> map, HttpServletResponse response, String fileName) {
        ServletOutputStream out = null;
        try (HSSFWorkbook workbook = new HSSFWorkbook()) {
            response.setContentType("application/vnd.ms-excel");
            fileName = new String((fileName + ".xls").getBytes(), "ISO-8859-1");
            String disposition = "attachment;filename=" + fileName;
            response.setHeader("Content-Disposition", disposition);
            out = response.getOutputStream();

            //title格式
            HSSFCellStyle titleStyle = createTitleStyle(workbook);
            //数据格式
            HSSFCellStyle dataStyle = createDataStyle(workbook);
            HSSFCellStyle amountStyle = createAmountStyle(workbook);
            HSSFSheet sheet = workbook.createSheet("sheet1");
            setColumnWidth(sheet, map.entrySet().size());
            HSSFRow row = sheet.createRow(0);
            HSSFCell cell;
            Set<Map.Entry<String, String>> entrySet = map.entrySet();
            int i = 0;
            for (Map.Entry<String, String> entry : entrySet) {
                cell = row.createCell(i++);
                cell.setCellValue(entry.getKey());
                cell.setCellStyle(titleStyle);
            }
            i = 0;
            if (data.size() > 0) {
                for (int j = 0; j < data.size(); j++) {
                    row = sheet.createRow(j + 1);
                    T vo = data.get(j);
                    for (Map.Entry<String, String> entry : entrySet) {
                        HSSFCell dataCell = row.createCell(i++);
                        dataCell.setCellValue(BeanUtils.getProperty(vo, entry.getValue()));
                        dataCell.setCellStyle(dataStyle);
                    }
                    i = 0;
                }
            }
            workbook.write(out);
        } catch (IOException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            log.error("导出{}发生错误", fileName, e);
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    log.error("关闭 ServletOutputStream 发生错误", e);
                }
            }
        }
    }

    /**
     * 标题单元格样式
     */
    private static HSSFCellStyle createTitleStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFFont titleFont = workbook.createFont();
        titleFont.setFontHeightInPoints((short) 16);
        titleFont.setBold(true);
        titleFont.setFontName("楷体");
        style.setFont(titleFont);
        return style;
    }

    /**
     * 数据单元格样式
     */
    private static HSSFCellStyle createDataStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        HSSFFont dataFont = workbook.createFont();
        dataFont.setFontHeightInPoints((short) 16);
        dataFont.setFontName("宋体");
        style.setFont(dataFont);
        return style;
    }

    /**
     * 数据货币类型单元格样式
     */
    private static HSSFCellStyle createAmountStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = createDataStyle(workbook);
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        style.setDataFormat(dataFormat.getFormat("￥#,##0.00;[Red]￥-#,##0.00"));
        return style;
    }

    /**
     * 设置列宽
     */
    private static void setColumnWidth(HSSFSheet sheet, int size) {
        for (int i = 0; i < size; i++) {
            sheet.setColumnWidth(i, 25 * 256);
        }
    }

}
