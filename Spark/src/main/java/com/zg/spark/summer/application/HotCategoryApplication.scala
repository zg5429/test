package com.zg.spark.summer.application

import com.zg.spark.summer.common.TApplication
import com.zg.spark.summer.controller.{HotCategoryController, HotCategorySessionController, WordCountController}
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.summer.application
 * @author zhuguang
 * @date 2021-01-03-16:54
 */
object HotCategoryApplication extends App with TApplication{

  start("HotCategory"){
    val controller = new HotCategorySessionController()
    controller.excute()

  }
}
