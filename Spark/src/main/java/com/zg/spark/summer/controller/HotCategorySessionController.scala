package com.zg.spark.summer.controller

import com.zg.spark.summer.common.TContorller
import com.zg.spark.summer.service.{HotCategoryService, HotCategorySessionService}

/**
 * @Project_name test
 * @Package_name com.zg.spark.summer.controller
 * @author zhuguang
 * @date 2021-01-03-16:52
 */
class HotCategorySessionController extends TContorller {
  private val hotCategorySessionService = new HotCategorySessionService()
  private val hotCategoryService = new HotCategoryService()

  override def excute(): Unit = {
    val top10: Array[(String, (Int, Int, Int))] = hotCategoryService.analysis()
    val result = hotCategorySessionService.analysis(top10)
    result.foreach(println)

  }
}
