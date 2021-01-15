package com.zg.spark.summer.util

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Project_name test
 * @Package_name com.zg.spark.summer.util
 * @author zhuguang
 * @date 2021-01-03-17:32
 */
object EnvUtil {
  private val envLocal = new ThreadLocal[SparkContext]

  def setEnv(sc: SparkContext) = {
    envLocal.set(sc)
  }

  def getEnv() = {
    envLocal.get()
  }

  def clear() = {
    envLocal.remove()
  }

}
