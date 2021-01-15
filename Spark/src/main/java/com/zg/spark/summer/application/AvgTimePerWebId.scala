package com.zg.spark.summer.application

import com.zg.spark.summer.common.TApplication
import com.zg.spark.summer.controller.{AvgTimePerWebIdController, HotCategorySessionController}

/**
 * @Project_name test
 * @Package_name com.zg.spark.summer.application
 * @author zhuguang
 * @date 2021-01-03-16:54
 */
object AvgTimePerWebId extends App with TApplication{

  start("TimeAVG"){
    val controller = new AvgTimePerWebIdController()
    controller.excute()

  }
}
