package com.example.hbase.stater.component;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author jackie wang
 * @since 2021/2/2 16:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HBaseServiceTest {

    @Autowired
//    private HBaseServiceWith1x hbaseService;
    private HBaseService hbaseService;
    String tableName = "test_hbase";

    public void setUp() throws Exception {

    }

    public void tearDown() throws Exception {
    }

    @Test
    public void testCreatTable() {
        System.err.println("===============testCreatTable()================");
        hbaseService.createTable(tableName, Arrays.asList("f1", "f2", "cf"));
//        hbaseService.createTableBySplitKeys("test_hbase", Arrays.asList("f1","f2"),
//                hbaseService.getSplitKeys(null));

        System.err.println("===============test_hbase创建完成===============");
    }

    @Test
    public void testCreateTableBySplitKeys() {
    }

    @Test
    public void testGetSplitKeys() {
    }

    /**
     * 获取所有表名
     */
    @Test
    public void testGetAllTableNames() {
        List<String> result = hbaseService.getAllTableNames();
        result.forEach(System.out::println);
    }

    @Test
    public void testGetResultScanner() {
        Map<String, Map<String, String>> result = hbaseService.getResultScanner(tableName);
        System.out.println("=============遍历查询=============");
        result.forEach((k, v) -> {
            System.out.println(k + "---->" + v);
        });
    }

    /**
     * 指定startRowKey和stopRowKey的查询
     */
    @Test
    public void testSelectByStartStopRowKey() {
        Map<String, Map<String, String>> result = hbaseService.getResultScanner(
                tableName, "2021_00002", "2021_00005");
        result.forEach((rowKey, columnMap) -> {
            System.out.println("rowKey:" + rowKey);
            System.out.println("----------行数据----------");
            columnMap.forEach((k, v) -> {
                System.out.println(k + "---->" + v);
            });
        });
    }


    @Test
    public void testTestGetResultScanner() {
    }

    /**
     * 根据行键过滤器查询rowkey含有前缀字符串的数据
     */
    @Test
    public void testGetResultScannerRowkeyPrefixFilter() {
        hbaseService.putData(tableName, "3021_00001", "f2",
                new String[]{"number", "name", "price", "create_time"},
                new String[]{"001", "小米", "1999.00", "null "});

        hbaseService.putData(tableName, "1121_00002", "f2",
                new String[]{"number", "name", "price", "create_time"},
                new String[]{"002", "iphone 11", "6999.00", "null"});
        hbaseService.putData(tableName, "3021_00003", "f2",
                new String[]{"number", "name", "price", "create_time"},
                new String[]{"003", "11 华为", "7999.11", "2021/2/2 16:15"});

        Map<String, Map<String, String>> result = hbaseService.getResultScannerRowkeyPrefixFilter(
                tableName, "11");
        result.forEach((rowKey, columnMap) -> {
            System.out.println("rowKey:" + rowKey);
            System.out.println("=========行数据==========");
            columnMap.forEach((k, v) -> {
                System.out.println(k + "---->" + v);
            });

        });

    }

    /**
     * 根据列名过滤器查询数据
     */
    @Test
    public void testGetResultScannerColumnPrefixFilter() {
        hbaseService.putData(tableName, "666", "f2",
                new String[]{"number"},
                new String[]{"001"});

        hbaseService.putData(tableName, "666", "f2",
                new String[]{"name1"},
                new String[]{"iphone 11"});
        hbaseService.putData(tableName, "777", "f2",
                new String[]{"number2", "name2"},
                new String[]{"003", "11 华为"});

        Map<String, Map<String, String>> result = hbaseService.getResultScannerColumnPrefixFilter(tableName, "name1");
        result.forEach((rowkey, columnMap) -> {
            System.out.println("rowkey:" + rowkey);
            System.out.println("==========行数据=========");
            columnMap.forEach((k, v) -> {
                System.out.println(k + "---->" + v);
            });
        });
    }

    /**
     * 查询行键中包含特定字符串的数据
     */
    @Test
    public void testGetResultScannerRowFilter() {
        Map<String, Map<String, String>> result = hbaseService.getResultScannerRowFilter(tableName, "12");
        result.forEach((rowkey, columnMap) -> {
            System.out.println("rowkey:" + rowkey);
            System.out.println("==========行数据=========");
            columnMap.forEach((k, value) -> {
                System.out.println(k + "---->" + value);
            });
        });

    }

    @Test
    public void testGetResultScannerQualifierFilter() {
    }

    @Test
    public void testGetRowData() {
        Map<String, String> result = hbaseService.getRowData(tableName, "row1");
        System.out.println("==============根据rowKey查询===============");
        for (Map.Entry<String, String> entry : result.entrySet()) {
            System.out.println(entry.getKey() + "---->" + entry.getValue());
        }
        System.out.println("==========================================");
        Map<String, String> result2 = hbaseService.getRowData(tableName, "2021_00001");
        System.out.println("==============根据rowKey查询===============");
        for (Map.Entry<String, String> entry : result2.entrySet()) {
            System.out.println(entry.getKey() + "---->" + entry.getValue());
        }
    }

    @Test
    public void testGetColumnValue() {
        String result = hbaseService.getColumnValue(tableName, "row1", "cf", "b");
        System.out.println("=============精确查询某个单元格的数据==============");
        System.out.println(result);
    }

    @Test
    public void testGetColumnValuesByVersion() {
        // 为表的某个单元格赋值
        hbaseService.setColumnValue(tableName, "2021_00001", "f1", "name", "aa");
        hbaseService.setColumnValue(tableName, "2021_00001", "f1", "name", "bb");
        hbaseService.setColumnValue(tableName, "2021_00001", "f1", "name", "cc");

        // 查询指定版本的记录
        List<String> result = hbaseService.getColumnValuesByVersion(
                tableName, "2021_00001", "f1", "name", 1);
        result.forEach(System.out::println);
    }

    @Test
    public void testPutData() {
        hbaseService.putData(tableName, "2021_00001", "f1",
                new String[]{"number", "name", "price", "create_time"},
                new String[]{"001", "小米", "3999.00", "null"});

        hbaseService.putData(tableName, "2021_00002", "f1",
                new String[]{"number", "name", "price", "create_time"},
                new String[]{"002", "iphone 11", "6999.00", "null"});
        hbaseService.putData(tableName, "2021_00003", "f1",
                new String[]{"number", "name", "price", "create_time"},
                new String[]{"003", "华为", "7999.88", "2021/2/2 16:15"});
        System.out.println("=============插入三条数据==============");
    }

    @Test
    public void testSetColumnValue() {
    }

    /**
     * 删除指定的列
     */
    @Test
    public void testDeleteColumn() {
        // 新增一个测试列
        hbaseService.setColumnValue(tableName, "668866", "f1", "test_column", "hello吖");
        String firstValue = hbaseService.getColumnValue(tableName, "668866", "f1", "test_column");
        System.out.println("第一次取值：" + firstValue);

        // 删除测试列
        hbaseService.deleteColumn(tableName, "668866", "f1", "test_column");

        // 第二次取值
        String secondValue = hbaseService.getColumnValue(tableName, "668866", "f1", "test_column");
        System.out.println("第二次取值：" + secondValue);
    }

    @Test
    public void testDeleteRow() throws InterruptedException {
        hbaseService.putData(tableName, "3021_00001", "f2",
                new String[]{"number", "name", "price", "create_time"},
                new String[]{"001", "小米", "1999.00", "null "});

        // 第一次取值
        Map<String, String> firstValue = hbaseService.getRowData(tableName, "3021_00001");
        System.out.println("第一次取值：" + firstValue);
        firstValue.forEach((k, value) -> {
            System.out.println(k + "---->" + value);
        });

//        Thread.sleep(100L);
        // 删除测试行
        hbaseService.deleteRow(tableName, "666");

        // 第二次取值
        Map<String, String> secondValue = hbaseService.getRowData(tableName, "3021_00001");
        System.out.println("第二次取值：" + secondValue);
        secondValue.forEach((k, v) -> {
            System.out.println(k + "---->" + v);
        });

    }

    /**
     * 删除指定的列族（适用于hbase2.x版本）
     */
//    @Test
//    public void testDeleteColumnFamily() {
//        hbaseService.putData(tableName, "3021_00009", "ff",
//                new String[]{"number", "name", "price", "create_time"},
//                new String[]{"001", "小米", "1999.00", "null "});
//
//        // 第一次取值
//        Map<String, String> firstValue = hbaseService.getRowData(tableName, "3021_00009");
//        System.out.println("第一次取值：" + firstValue);
//        firstValue.forEach((k, value) -> {
//            System.out.println(k + "---->" + value);
//        });
//
//        // 删除测试列族
//        hbaseService.deleteColumnFamily(tableName, "ff");
//
//        // 第二次取值
//        Map<String, String> secondValue = hbaseService.getRowData(tableName, "3021_00009");
//        System.out.println("第二次取值：" + secondValue);
//        secondValue.forEach((k, v) -> {
//            System.out.println(k + "---->" + v);
//        });
//
//
//    }

    @Test
    public void testDeleteTable() {
        hbaseService.deleteTable("test");
        System.out.println("================删除test表==================");
    }
}