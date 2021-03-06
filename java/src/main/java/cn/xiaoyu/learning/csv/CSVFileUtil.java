package cn.xiaoyu.learning.csv;

import cn.xiaoyu.learning.common.utils.DateUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 01399268
 * @date 2020/9/27
 */
public class CSVFileUtil {
    /**
     * 从输入流中读取数据
     */
    public static <T> List<T> readCSV(InputStream inputStream, Map<String, String> headerProp, Class<T> clazz) throws Exception {
        List<T> dataList = new ArrayList<>();
        CSVFormat format = CSVFormat.DEFAULT.withHeader();
        try (CSVParser parser = CSVParser.parse(inputStream, StandardCharsets.UTF_8, format)) {
            Set<String> headers = parser.getHeaderMap().keySet();
            for (CSVRecord record : parser) {
                Object obj = clazz.newInstance();
                for (String header : headers) {
                    if (PropertyUtils.getPropertyType(obj, headerProp.get(header)).isAssignableFrom(Calendar.class)) {
                        Calendar calendar = DateUtils.toCalendar(record.get(header), DateUtils.YYYYMMDDHHMMSS);
                        BeanUtils.setProperty(obj, headerProp.get(header), calendar);
                    } else if (PropertyUtils.getPropertyType(obj, headerProp.get(header)).isAssignableFrom(Date.class)) {
                        Date date = DateUtils.toDate(record.get(header), DateUtils.YYYYMMDD);
                        BeanUtils.setProperty(obj, headerProp.get(header), date);
                    } else {
                        BeanUtils.copyProperty(obj, headerProp.get(header), record.get(header));
                    }
                }
                dataList.add((T) obj);
            }
        }
        return dataList;
    }

    public static void writeCSV(OutputStream out, Iterable<?> iter, String charset, String... header) {
        try {
            // 写入bom, 防止中文乱码
            byte[] bytes = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
            out.write(bytes);

            OutputStreamWriter osw = new OutputStreamWriter(out, charset);
            CSVFormat csvFormat = CSVFormat.EXCEL.withHeader(header);

            CSVPrinter csvPrinter = new CSVPrinter(osw, csvFormat);
            csvPrinter.printRecords(iter);
            csvPrinter.flush();
            csvPrinter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
