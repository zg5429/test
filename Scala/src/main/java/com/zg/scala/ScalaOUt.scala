package com.zg.scala

import java.io.{File, PrintWriter}

/**
 * @Project_name test
 * @Package_name com.zg.scala
 * @author zhuguang
 * @date 2020-12-15-18:27
 */
object ScalaOUt {
  def main(args: Array[String]): Unit = {

    val writer = new PrintWriter(new File("output/out.txt"))
    writer.write("dslkfnksdanflnas")
    writer.close()


  }

}
