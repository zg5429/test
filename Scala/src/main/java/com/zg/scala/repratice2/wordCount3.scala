package com.zg.scala.repratice2

/**
 * @Project_name test
 * @Package_name com.zg.scala.repratice2
 * @author zhuguang
 * @date 2020-12-22-19:54
 */
object wordCount3 {
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


    val list1 = list.map {
      case (name, prv, item) => {
        (prv + "-" + item)
      }
    }
//    println(list1)
    val GroupMap = list1.groupBy(word => word)
//    println(GroupMap)

    val list2 = GroupMap.map {
      case (prv_item, prv_item_size) => {
        (prv_item, prv_item_size.size)
      }
    }
//    println(list2)

    //    val itemCount = list2.toList.map(
    //      kv => {
    //        val word = kv._1
    //        val cnt = kv._2
    //        val ks = word.split("-")
    //        (ks(0), (ks(1), cnt))
    //      }
    //    )
    val itemCount = list2.toList.map {
      case (p, c) => {
        val wd = p.split("-")
        val ct = c
        (wd(0), (wd(1), ct))

      }
    }

    println(itemCount)
    //    val stringToTuples = itemCount.groupBy((word)=>word._1)
    val stringToTuples = itemCount.groupBy(_._1)
    val result = stringToTuples.mapValues(
      list => {
        val itemCntList = list.map(_._2)
        println(itemCntList)
        itemCntList.sortBy(_._2)(Ordering.Int.reverse)
      }
    )
//    println(result)


  }

}
