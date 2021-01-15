package com.zg.scala.repratice2

/**
 * @Project_name test
 * @Package_name com.zg.scala.repratice2
 * @author zhuguang
 * @date 2020-12-22-19:48
 */
object wordCount_2 {
  def main(args: Array[String]): Unit = {
    val list = List(
      ("hello", 4),
      ("hello spark", 3),
      ("hello spark scala", 2),
      ("hello spark scala hive", 1)
    )
    val newList = list.map {
      case (word, cnt) => {
        (word + " ") * cnt
      }
    }
    println(newList)
    val list1 = newList.flatMap(_.split(" "))
    println(list1)
    val list2 = list1.groupBy(word=>word)
    println(list2)

    val wordCount = list2.map {
      case (word, cnt) => {
        (word, cnt.size)
      }
    }
    println(wordCount)







  }

}
