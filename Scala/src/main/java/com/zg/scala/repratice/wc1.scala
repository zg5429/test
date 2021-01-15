package com.zg.scala.repratice

/**
 * @Project_name test
 * @Package_name com.zg.scala.repratice
 * @author zhuguang
 * @date 2020-12-22-14:02
 */
object wc1 {
  def main(args: Array[String]): Unit = {
    val list = List(
      ("hello", 4),
      ("hello spark", 3),
      ("hello spark scala", 2),
      ("hello spark scala hive", 1)
    )

    val lines = list.map(
      t => {
        (t._1 + " ") * t._2
      }
    )
    println(lines)

    val wordlist = lines.flatMap(_.split(" "))
    println(wordlist)
    val wordsGroup = wordlist.groupBy(word=>word)
    println(wordsGroup)
    val wordcount = wordsGroup.map(
      kv => {
        (kv._1, kv._2.size)
      }
    )
    val sortList = wordcount.toList.sortBy(_._2)(Ordering.Int.reverse)
    println(sortList)











  }

}
