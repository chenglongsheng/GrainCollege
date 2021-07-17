package com.atguigu.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenglongsheng
 * @create 2021-07-17 9:34
 */
public class TestEasyExcel {

    public static void main(String[] args) {
        String fileName = "F:\\guli_edu\\write.xlsx";
        EasyExcel.write(fileName, DemoData.class).sheet("学生列表").doWrite(getData());
    }

    private static List<DemoData> getData() {

        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("Tom" + i);

            list.add(data);
        }
        return list;
    }

}
