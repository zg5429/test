package com.zg.spark.summer.controller

import com.zg.spark.summer.common.TContorller
import com.zg.spark.summer.service.{AvgTimePerWebIdService, HotCategoryService}

/**
 * @Project_name test
 * @Package_name com.zg.spark.summer.controller
 * @author zhuguang
 * @date 2021-01-03-16:52
 */
class AvgTimePerWebIdController extends TContorller {
  private val avgTimePerWebIdService = new AvgTimePerWebIdService()

  override def excute(): Unit = {
    val result = avgTimePerWebIdService.analysis()
    result.foreach(println)

  }
}
