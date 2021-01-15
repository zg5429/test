package com.zg.scala.repratice

import scala.io.Source

/**
 * @Project_name test
 * @Package_name com.zg.scala.repratice
 * @author zhuguang
 * @date 2020-12-22-8:32
 */
object exam1 {
  def main(args: Array[String]): Unit = {
    val list1 = Source.fromFile("D:/input1/test.txt").getLines().toList
    val list = List(
      ("hello", 4),
      ("hello spark", 3),
      ("hello spark scala", 2),
      ("hello spark scala hive", 1)
    )

    val lines: List[String] = list.map(
      t => {
        (t._1 + " ") * t._2
      }
    )
    println(lines)

    val words: List[String] = lines.flatMap(_.split(" "))
    println(words)

    val wordGroupMap: Map[String, List[String]] = words.groupBy(word => word)
//    println(wordGroupMap)
    val wordCount = wordGroupMap.map(kv => {
      (kv._1, kv._2.size)
    })

    val sortList: List[(String, Int)] = wordCount.toList.sortBy(_._2)(Ordering.Int.reverse)


    val top3 = sortList.take(3)

    println(top3)


  }

}
