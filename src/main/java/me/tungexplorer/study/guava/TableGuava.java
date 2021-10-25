package me.tungexplorer.study.guava;

import java.util.Map;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class TableGuava {
    private static final Table<String, String, Integer> table = HashBasedTable.create();

    public static void main(String[] args) {
        Map<String, Integer> tung1 = table.row("Tung1");

        table.put("Tung","SinhVien", 1);
        table.put("Tung","SinhVien", 2);
        System.out.println(tung1);
    }
}
