package cn.xiaoyu.learning.csv;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
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
        csvFile.testImport();
    }

    public void testImport() throws Exception {
        String path = this.getClass().getResource("/学生信息.csv").getPath();
        path = URLDecoder.decode(path, "UTF-8");
        InputStream inputStream = new FileInputStream(path);

        Map<String, String> headMap = new HashMap<>();
        headMap.put("姓名", "name");
        headMap.put("年龄", "age");
        headMap.put("分数", "score");
        headMap.put("生日", "birth");
        headMap.put("入学日期", "admission");

        List<Student> students = CSVFileUtil.readCSV(inputStream, headMap, Student.class);
        students.forEach(System.out::print);
    }
}
