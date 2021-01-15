package com.zg.spark.summer.common

import com.zg.spark.summer.util.EnvUtil

/**
 * @Project_name test
 * @Package_name com.zg.spark.summer.common
 * @author zhuguang
 * @date 2021-01-03-16:43
 */
trait TDao {
  def readFile(path: String) = {
    val rdd = EnvUtil.getEnv().textFile(path)
    rdd
  }

}
