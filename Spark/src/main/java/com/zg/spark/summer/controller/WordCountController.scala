package com.zg.spark.summer.controller

import com.zg.spark.summer.common.TContorller
import com.zg.spark.summer.service.WordCountService

/**
 * @Project_name test
 * @Package_name com.zg.spark.summer.controller
 * @author zhuguang
 * @date 2021-01-03-15:26
 */
class WordCountController extends TContorller{
  private val wordCountService = new WordCountService()

  def excute(): Unit = {
    val result = wordCountService.analysis()
    result.foreach(println)
  }

}
