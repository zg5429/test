package com.zg.spark.summer.application

import com.zg.spark.summer.common.TApplication
import com.zg.spark.summer.controller.WordCountController
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.summer.application
 * @author zhuguang
 * @date 2021-01-03-15:22
 */
object WordCountApplication extends App with TApplication {

  //  start(new WordCountController)

//  使用抽象方法
  //  override def getController() = new WordCountController()

  //控制抽象
  start("WordCount") {
    val controller = new WordCountController()
    controller.excute()
  }
}
