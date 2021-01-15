package com.zg.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author zhuguang
 * @Project_name test
 * @Package_name com.zg.hbase
 * @date 2021-01-12-14:41
 */
public class HbaseTest04_2 {
    public static void main(String[] args) throws IOException {

        final Configuration cfg = HBaseConfiguration.create();
        cfg.set("hbase.zookeeper.quorum", "hadoop102,hadoop103,hadoop104");
        Connection conn = ConnectionFactory.createConnection(cfg);

        Admin admin = conn.getAdmin();

        TableName tableName = TableName.valueOf("atguigu:user");

//        查询数据


//       1. 获取表对象
        Table table = conn.getTable(tableName);

//        scan(tabel)  get(rowkey)
        Scan scan = new Scan();
        ResultScanner scanner = table.getScanner(scan);
        Iterator<Result> iterator = scanner.iterator();
        while (iterator.hasNext()) {
            Result next = iterator.next();


            Cell[] cells = next.rawCells();
            for (Cell cell : cells) {
//                获取rowkey
                byte[] rk = CellUtil.cloneRow(cell);
                String rks = Bytes.toString(rk);


                //获取列族
                byte[] cf = CellUtil.cloneFamily(cell);
                String cfs = Bytes.toString(cf);
//获取列名
                byte[] cq = CellUtil.cloneQualifier(cell);
                String cqs = Bytes.toString(cq);
//获取列值
                byte[] cv = CellUtil.cloneValue(cell);
                String cvs = Bytes.toString(cv);


                System.out.println(rks+" "+cfs + " " + cqs + " " + cvs);

            }


        }

        admin.close();
        conn.close();


    }
}
