package com.zg.scala.repratice2

import scala.io.Source

/**
 * @Project_name test
 * @Package_name com.zg.scala.repratice2
 * @author zhuguang
 * @date 2020-12-22-19:37
 */
object wordCount_1 {
  def main(args: Array[String]): Unit = {
    val list = Source.fromFile("data/word.txt").getLines().toList
    println(list)
    val list1 = list.flatMap(_.split(" "))
    println(list1)
    val groupmap = list1.groupBy(word => word)
    println(groupmap)
    val wordcount = groupmap.map {
      case (wd, (value)) => {
        (wd, value.size)
      }
    }
    println(wordcount)




  }

}
