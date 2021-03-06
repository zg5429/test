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
public class HbaseTest03_API_1 {
    public static void main(String[] args) throws IOException {

        final Configuration cfg = HBaseConfiguration.create();
        cfg.set("hbase.zookeeper.quorum", "hadoop102,hadoop103,hadoop104");
        Connection conn = ConnectionFactory.createConnection(cfg);
        //        对命名空间进行操作
//        1。获取管理员对象（权限）
        Admin admin = conn.getAdmin();

        TableName tableName = TableName.valueOf("atguigu:user");
        if (admin.tableExists(tableName)) {
            System.out.println("表已经存在");

//            删除表之前需要将表禁用s
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
            System.out.println("表已经删除");
        }
        admin.close();

        conn.close();


    }
}
