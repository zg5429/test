package com.zg.scala.repratice

import scala.util.control.Breaks.breakable
import scala.util.control.Breaks.break

/**
 * @Project_name test
 * @Package_name com.zg.scala.repratice
 * @author zhuguang
 * @date 2020-12-16-18:48
 */
object ScalaLoop {
  def main(args: Array[String]): Unit = {
        breakable {
          for (i <- 1 to 5) {
            if (i == 3) {
              break
            }
            println(i)
          }
        }





  }

}
