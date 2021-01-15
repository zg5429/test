package com.zg.scala.repratice

import scala.io.Source

/**
 * @Project_name test
 * @Package_name com.zg.scala.repratice
 * @author zhuguang
 * @date 2020-12-22-8:32
 */
object exam {
  def main(args: Array[String]): Unit = {
    val list = Source.fromFile("D:/input1/test.txt").getLines().toList
    println(list)

    //    val wordList = list.flatMap(_.split(" "))
    val wordlist1 = list.filter(_.contains("hello"))
    //    println(wordList)
    println(wordlist1)

//    wordlist1.foreach(println)
    val wordList2 = list.flatMap(_.split(" "))
    println(wordList2)
    println("--------------")
    val wordGroupMap: Map[String, List[String]] = wordList2.groupBy(word => word)
    println(wordGroupMap)




    val wordCount = wordGroupMap.map(kv => {
      (kv._1, kv._2.size)
    })

    // 5. 将统计结果根据数量进行排序（降序）
    val sortList: List[(String, Int)] = wordCount.toList.sortBy(_._2)(Ordering.Int.reverse)

    // 6. 将排序后的结果取前3名（Top3）
    val top3 = sortList.take(10)

    println(top3)



  }

}
