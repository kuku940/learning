package cn.xiaoyu.learning.csv;

import cn.xiaoyu.learning.common.utils.DateUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 01399268
 * @date 2020/9/27
 */
public class CSVFileTest {
    public static void main(String[] args) throws Exception {
        CSVFileTest csvFile = new CSVFileTest();
        csvFile.testRead();
        csvFile.testWrite();
    }

    private void testWrite() throws Exception {
        String[] headers = {"姓名", "年龄", "分数", "生日", "入学日期", "描述"};

        OutputStream outputStream = new FileOutputStream("D:\\1.csv");
        List<Student> students = new ArrayList() {{
            add(new Student("11", 23, 89.5, new Date(), Calendar.getInstance(), "123\"2"));
            add(new Student("22", 24, 85.5, new Date(), Calendar.getInstance(), "12,34"));
        }};
        List<List<Object>> list = new ArrayList();
        students.forEach(student -> {
            List<Object> l = new ArrayList<>();
            l.add(student.getName());
            l.add(student.getAge());
            l.add(student.getScore());
            l.add(DateUtils.format(student.getBirth(), DateUtils.YYYYMMDD));
            l.add(DateUtils.format(student.getAdmission(), DateUtils.YYYYMMDDHHMMSS));
            l.add(student.getDesc());
            list.add(l);
        });
        CSVFileUtil.writeCSV(outputStream, list, "UTF-8", headers);

    }

    public void testRead() throws Exception {
        String path = this.getClass().getResource("/学生信息.csv").getPath();
        path = URLDecoder.decode(path, "UTF-8");
        InputStream inputStream = new FileInputStream(path);

        Map<String, String> headMap = new HashMap<>();
        headMap.put("姓名", "name");
        headMap.put("年龄", "age");
        headMap.put("分数", "score");
        headMap.put("生日", "birth");
        headMap.put("入学日期", "admission");
        headMap.put("描述", "desc");

        List<Student> students = CSVFileUtil.readCSV(inputStream, headMap, Student.class);
        students.forEach(System.out::print);
    }
}
