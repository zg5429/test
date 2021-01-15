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
public class HbaseTest04_3 {
    public static void main(String[] args) throws IOException {

        final Configuration cfg = HBaseConfiguration.create();
        cfg.set("hbase.zookeeper.quorum", "hadoop102,hadoop103,hadoop104");
        Connection conn = ConnectionFactory.createConnection(cfg);

        Admin admin = conn.getAdmin();

        TableName tableName = TableName.valueOf("atguigu:user");

//        删除数据


//       1. 获取表对象
        Table table = conn.getTable(tableName);

        Delete delete = new Delete(Bytes.toBytes("1001"));
        table.delete(delete);

        table.close();

        admin.close();
        conn.close();


    }
}
