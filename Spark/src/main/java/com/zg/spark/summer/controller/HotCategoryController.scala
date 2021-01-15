package com.zg.spark.summer.controller

import com.zg.spark.summer.common.TContorller
import com.zg.spark.summer.service.HotCategoryService

/**
 * @Project_name test
 * @Package_name com.zg.spark.summer.controller
 * @author zhuguang
 * @date 2021-01-03-16:52
 */
class HotCategoryController extends TContorller {
  private val hotCategoryService = new HotCategoryService()

  override def excute(): Unit = {
    val result = hotCategoryService.analysis()
    result.foreach(println)

  }
}
