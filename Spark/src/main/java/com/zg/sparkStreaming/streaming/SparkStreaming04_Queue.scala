package com.zg.sparkStreaming.streaming

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

/**
 * @Project_name test
 * @Package_name com.zg.sparkStreaming.streaming
 * @author zhuguang
 * @date 2021-01-05-15:00
 */
object SparkStreaming04_Queue {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("streaming")
    val ssc = new StreamingContext(sparkConf, Seconds(3))
    val rddQueue = new mutable.Queue[RDD[Int]]()
    val lines = ssc.queueStream(rddQueue, oneAtATime = false)

    //    val wordToOne: DStream[(String, Int)] = lines.map((_, 1))
    //    val wordToCount: DStream[(String, Int)] = wordToOne.reduceByKey(_ + _)
    //    wordToCount.print()
    lines.print()
    ssc.start()
    for (i <- 1 to 5) {
      rddQueue += ssc.sparkContext.makeRDD(1 to 300, 10)
      Thread.sleep(2000)
    }
    ssc.awaitTermination()


  }


}
