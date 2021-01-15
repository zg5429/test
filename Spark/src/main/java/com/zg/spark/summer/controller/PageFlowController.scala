package com.zg.spark.summer.controller

import com.zg.spark.summer.common.TContorller
import com.zg.spark.summer.service.{HotCategoryService, PageFlowService}

/**
 * @Project_name test
 * @Package_name com.zg.spark.summer.controller
 * @author zhuguang
 * @date 2021-01-03-16:52
 */
class PageFlowController extends TContorller {
  private val pageFlowService = new PageFlowService()

  override def excute(): Unit = {
    val result = pageFlowService.analysis()
//    result.foreach(println)

  }
}
