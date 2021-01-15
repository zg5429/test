package com.zg.sparkStreaming.streaming

import java.util.Random

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

/**
 * @Project_name test
 * @Package_name com.zg.sparkStreaming.streaming
 * @author zhuguang
 * @date 2021-01-05-15:00
 */
object SparkStreaming05_DIY {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("streaming")
    val ssc = new StreamingContext(sparkConf, Seconds(3))

    val ds =ssc.receiverStream(new MyDataSourceReceiver())
    ds.print()


    ssc.start()
    ssc.awaitTermination()


  }

  class MyDataSourceReceiver extends Receiver[String](StorageLevel.MEMORY_ONLY) {
    private var flg = true

    override def onStart(): Unit = {
      while (flg) {
        val name = "zzz" + new Random().nextInt(20)
        store(name)
        Thread.sleep(500)
      }


    }

    override def onStop(): Unit = {
      flg = false
    }
  }

}
