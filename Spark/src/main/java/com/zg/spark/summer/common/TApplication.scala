package com.zg.spark.summer.common

import com.zg.spark.summer.controller.WordCountController
import com.zg.spark.summer.util.EnvUtil
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.summer.common
 * @author zhuguang
 * @date 2021-01-03-17:10
 */
trait TApplication {

  //  def start(controller:TContorller) = {
  def start(app:String,master:String="local[*]")(op: => Unit): Unit = {
    val sparkConf = new SparkConf().setMaster(master).setAppName(app)
    val sc = new SparkContext(sparkConf)
    EnvUtil.setEnv(sc)

    //    getController.excute()
    try {
      op
    } catch {
      case e: Exception => println(e.getMessage)
    }


    sc.stop()
  }

  //  def getController():TContorller


}
