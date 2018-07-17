package cn.xiaoyu.guava.collections;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Map;
import java.util.Set;

/**
 * 其中两个键可以在组合的方式被指定为单个值
 */

public class TableTest {
    public static void main(String[] args) {
        Table<String, String, String> employeeTable = HashBasedTable.create();

        employeeTable.put("IBM", "101", "Jack");
        employeeTable.put("IBM", "102", "Tony");
        employeeTable.put("IBM", "103", "Michael");

        employeeTable.put("Microsoft", "111", "Sohan");
        employeeTable.put("Microsoft", "112", "Mohan");
        employeeTable.put("Microsoft", "113", "Rohan");

        employeeTable.put("TCS", "121", "Ram");
        employeeTable.put("TCS", "122", "Shyam");
        employeeTable.put("TCS", "123", "Sunil");

        Map<String, String> ibmEmployees = employeeTable.row("IBM");
        System.out.println("List of IBM Employees:");
        for (Map.Entry<String, String> entry : ibmEmployees.entrySet()) {
            System.out.println("Emp id:" + entry.getKey() + ",Name:" + entry.getValue());
        }

        Set<String> employers = employeeTable.rowKeySet();
        System.out.print("Employers: ");
        for (String employer : employers) {
            System.out.print(employer + " ");
        }
        System.out.println();

        Map<String, String> employerMap = employeeTable.column("102");
        for (Map.Entry<String, String> entry : employerMap.entrySet()) {
            System.out.println("Employer: " + entry.getKey() + ", Name: " + entry.getValue());
        }
    }
}
