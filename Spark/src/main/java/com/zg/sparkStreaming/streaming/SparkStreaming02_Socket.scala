package com.zg.sparkStreaming.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @Project_name test
 * @Package_name com.zg.sparkStreaming.streaming
 * @author zhuguang
 * @date 2021-01-05-15:00
 */
object SparkStreaming02_Socket {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("streaming")
    val ssc = new StreamingContext(sparkConf, Seconds(3))

    val lines: ReceiverInputDStream[String] = ssc.socketTextStream("localhost", 8888)
    val words: DStream[String] = lines.flatMap(_.split(" "))

    val wordToOne: DStream[(String, Int)] = words.map((_, 1))
    val wordToCount: DStream[(String, Int)] = wordToOne.reduceByKey(_ + _)
    wordToCount.print()


    ssc.start()
    ssc.awaitTermination()


  }


}
