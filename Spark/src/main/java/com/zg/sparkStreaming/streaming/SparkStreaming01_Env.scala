package com.zg.sparkStreaming.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @Project_name test
 * @Package_name com.zg.sparkStreaming.streaming
 * @author zhuguang
 * @date 2021-01-05-15:00
 */
object SparkStreaming01_Env {
  def main(args: Array[String]): Unit = {
    //    创建环境
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("streaming")
    val ssc = new StreamingContext(sparkConf, Seconds(3))

    //启动数据采集器
    ssc.start()

    //    阻塞当前线程 直到采集器停止
    ssc.awaitTermination()
    //不能关闭
    //    ssc.stop()


  }


}
