package com.common.core.utils;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.util.StringUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

public class ExportExcelUtil {

    private static Integer MAXROWS = 1048000;

    public static <Ty> void outfile(HttpServletResponse response, Map<String, String> map, String sheetname, List<Ty> list) throws Exception {
        //创建poi导出数据对象
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook();
        //创建全局样式
        CellStyle cellStyle = sxssfWorkbook.createCellStyle();
        cellStyle.setFillForegroundColor((short) 10);
        cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
        cellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        cellStyle.setBorderTop(BorderStyle.THIN);//上边框
        cellStyle.setBorderRight(BorderStyle.THIN);//右边框
        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 水平居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 垂直居中
        cellStyle.setWrapText(true);//自动换行
        Font font = sxssfWorkbook.createFont();
        font.setFontName("宋体");//设置字体
        short pintSize = 10;
        font.setFontHeightInPoints(pintSize);//设置字体大小
        font.setBold(true);
        cellStyle.setFont(font);
        int integer = list.size();
        if (MAXROWS<integer) {
            throw new RuntimeException("数据行数超过excel最大数据行数!");
        }
        if (integer > 0) {
            //数据量过大分批拉取
            int k = 0;
            //一个sheet最大行数
            final int pageNum = 500000;
            double count = deciMal(integer, pageNum);
            double num = Math.ceil(count);
            int j = 1;
            while (num != 0) {
                String newsheetname = sheetname + j;
                if (num == 1) {
                    List<Ty> subList = list.subList(k, integer);
                    setData(map, newsheetname, sxssfWorkbook, cellStyle, pintSize, subList);
                } else {
                    List<Ty> subList = list.subList(k, k + pageNum);
                    k = k + pageNum;
                    // 创建sheet页
                    setData(map, newsheetname, sxssfWorkbook, cellStyle, pintSize, subList);
                }
                num--;
                j++;
            }
        }

        // 下载导出
        String filename = sheetname + getNowTime();
        // 设置头信息
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel");
        //一定要设置成xlsx格式
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename + ".xlsx", "UTF-8"));
        //创建一个输出流
        ServletOutputStream outputStream = response.getOutputStream();
        //写入数据
        sxssfWorkbook.write(outputStream);
        // 关闭
        outputStream.close();
        sxssfWorkbook.close();
    }

    private static double deciMal(int top, int below) {
        double result = new BigDecimal((float) top / below).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
        return result;
    }

    private static <Ty> void setData(Map<String, String> map, String newsheetname, SXSSFWorkbook sxssfWorkbook, CellStyle cellStyle, short pintSize, List<Ty> subList) {
        // 创建sheet页
        SXSSFSheet sheet = sxssfWorkbook.createSheet(newsheetname);
        int size = map.size() - 1;
        CellRangeAddress region = new CellRangeAddress(0, 0, (short) 0, (short) size);
        //参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列
        sheet.addMergedRegion(region);
        SXSSFRow headTitle = sheet.createRow(0);
        SXSSFCell cellHeadTitle = headTitle.createCell(0);
        cellHeadTitle.setCellValue(newsheetname);
        cellHeadTitle.setCellStyle(cellStyle);
        //创建表头
        SXSSFRow headRow = sheet.createRow(1);
        //设置excel表头
        int i = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String mapKey = entry.getKey();
            String[] split = mapKey.split("-");
            mapKey = split[0];
            //设置表头宽度
            if (split.length > 1) {
                int titlewidth = Integer.parseInt(split[1]);
                titlewidth = titlewidth * pintSize * 30;
                sheet.setColumnWidth(i, titlewidth);
            }
            SXSSFCell cell = headRow.createCell(i);
            cell.setCellValue(mapKey);
            cell.setCellStyle(cellStyle);
            i++;
        }

        // 遍历上面数据库查到的数据
        for (Ty t : subList) {
            SXSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
            Map<String, Object> data = null;
            if (t instanceof Map) {
                data = (Map<String, Object>) t;
            } else {
                data = object2Map(t);
            }
            //设置excel表头
            int j = 0;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String mapValue = entry.getValue();
                SXSSFCell cell = dataRow.createCell(j);
                Object obj = data.get(mapValue);
                String value = "";
                if (!StringUtils.isEmpty(obj)) {
                    value = value + obj;
                }
                cell.setCellValue(value);
                cell.setCellStyle(cellStyle);
                j++;
            }
        }
    }

    private static String getNowTime()  //获取当前系统时间并格式化
    {
        System.setProperty("user.timezone", "GMT+08");
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int date = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        String nowTime = year + "-" + month + "-" + date + "-" + hour + "-" + minute + "-" + second;
        return nowTime;
    }

    /**
     * 实体对象转成Map
     *
     * @param obj 实体对象
     * @return
     */
    private static Map<String, Object> object2Map(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (obj == null) {
            return map;
        }
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * Map转成实体对象
     *
     * @param map   map实体对象包含属性
     * @param clazz 实体对象类型
     * @return
     */
    private static <T> T map2Object(Map<String, Object> map, Class<T> clazz) {
        if (map == null) {
            return null;
        }
        T obj = null;
        try {
            obj = clazz.newInstance();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                String filedTypeName = field.getType().getName();
                if (filedTypeName.equalsIgnoreCase("java.util.date")) {
                    String datetimestamp = String.valueOf(map.get(field.getName()));
                    if (datetimestamp.equalsIgnoreCase("null")) {
                        field.set(obj, null);
                    } else {
                        field.set(obj, new Date(Long.parseLong(datetimestamp)));
                    }
                } else {
                    field.set(obj, map.get(field.getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
