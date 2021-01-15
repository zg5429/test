package com.zg.scala.repratice

import scala.io.{BufferedSource, Source}

/**
 * @Project_name test
 * @Package_name com.zg.scala.repratice
 * @author zhuguang
 * @date 2020-12-22-9:08
 */
object wordcount {
  def main(args: Array[String]): Unit = {
    // TODO WordCount

    // 1. 读取文件获取原始数据
    //    line
    val source: BufferedSource = Source.fromFile("D:/input1/word.txt")
    val list: List[String] = source.getLines().toList
    source.close()
    println(list)

    // 2. 将原始数据拆分成一个一个的单词（分词效果）
    //    line => word
    val words: List[String] = list.flatMap(_.split(" "))
    println(words)

    // 3. 将相同的单词放置在一个组中
    //   word => List(word, word, word)
    val wordGroupMap: Map[String, List[String]] = words.groupBy(word => word)
      println(wordGroupMap)
    // 4. 将分组后的结果进行统计
    //  （word, list） => (word, list.size)
    val wordCount = wordGroupMap.map(kv => {
      (kv._1, kv._2.size)
    })

    // 5. 将统计结果根据数量进行排序（降序）
    val sortList: List[(String, Int)] = wordCount.toList.sortBy(_._2)(Ordering.Int.reverse)

    // 6. 将排序后的结果取前3名（Top3）
    val top3 = sortList.take(3)

    println(top3)
  }

}
