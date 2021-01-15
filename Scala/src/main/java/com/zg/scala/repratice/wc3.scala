package com.zg.scala.repratice

/**
 * @Project_name test
 * @Package_name com.zg.scala.repratice
 * @author zhuguang
 * @date 2020-12-22-14:02
 */
object wc3 {
  def main(args: Array[String]): Unit = {
    val list = List(
      ("zhangsan", "河北", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "鞋"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河南", "衣服"),
      ("wangwu", "河南", "鞋"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "鞋"),
      ("zhangsan", "河北", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "帽子"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河南", "衣服"),
      ("wangwu", "河南", "帽子"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "帽子"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "电脑"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河南", "衣服"),
      ("wangwu", "河南", "电脑"),
      ("zhangsan", "河南", "电脑"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "帽子")
    )

    val words = list.map(
      t => {
        t._2 + "-" + t._3
      }
    )
    //    println(words)
    val wordGroup = words.groupBy(word => word)
    //    println(wordGroup)

    val wordCount = wordGroup.map(
      t => {
        (t._1, t._2.size)
      })

    //    println(wordCount)

    val itemCount = wordCount.toList.map(
      kv => {
        val word = kv._1
        val cnt = kv._2
        val ks = word.split("-")
        (ks(0), (ks(1), cnt))
      }
    )
    println(itemCount)

    val stringToTuples = itemCount.groupBy(_._1)

    //    val stringToTuples = itemCount.groupBy((word)=>{word})
    println(stringToTuples)

    val result = stringToTuples.mapValues(
      list => {
        val itemCntList = list.map(_._2)
        itemCntList.sortBy(_._2)(Ordering.Int.reverse)
      }
    )
    println(result)





  }

}
