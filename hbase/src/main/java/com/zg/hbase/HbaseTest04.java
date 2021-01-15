package com.zg.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * @author zhuguang
 * @Project_name test
 * @Package_name com.zg.hbase
 * @date 2021-01-12-14:41
 */
public class HbaseTest04 {
    public static void main(String[] args) throws IOException {

        final Configuration cfg = HBaseConfiguration.create();
        cfg.set("hbase.zookeeper.quorum", "hadoop102,hadoop103,hadoop104");
        Connection conn = ConnectionFactory.createConnection(cfg);

        Admin admin = conn.getAdmin();

        TableName tableName = TableName.valueOf("atguigu:user");

//        向表中添加数据


//       1. 获取表对象
        Table table = conn.getTable(tableName);

//        2. 向表中添加数据  put 'namespace : tablename','Rowkey','列族:列名','列值'
        String rowkey = "1001";
        Put put = new Put(Bytes.toBytes(rowkey));
        put.addColumn(
                Bytes.toBytes("info"),
                Bytes.toBytes("name"),
                Bytes.toBytes("zhangsan"));


        table.put(put);
        System.out.println("添加成功");
        admin.close();

        conn.close();


    }
}
