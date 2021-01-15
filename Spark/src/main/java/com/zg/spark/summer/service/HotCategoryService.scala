package com.zg.spark.summer.service

import com.zg.spark.summer.common.TService
import com.zg.spark.summer.dao.HotCategoryDao

/**
 * @Project_name test
 * @Package_name com.zg.spark.summer.service
 * @author zhuguang
 * @date 2021-01-03-16:51
 */
class HotCategoryService extends TService {
  private val hotCategoryDao = new HotCategoryDao()

  override def analysis() = {
    val lines = hotCategoryDao.readFile("data/user_visit_action.txt")
    lines.cache()

    // TODO 2. 统计品类的点击数量 ( 品类, 点击 )
    // 2.1 将原始数据进行过滤，保留品类的点击数据
    val clickDatas = lines.filter(
      line => {
        val datas = line.split("_")
        datas(6) != "-1"
      }
    )
    // 2.2 将过滤后的数据进行统计
    // ( 品类, 1 )
    val clickCnt = clickDatas.map(
      line => {
        val datas = line.split("_")
        (datas(6), 1)
      }
    ).reduceByKey(_ + _)

    // TODO 3. 统计品类的下单数量
    // 3.1 将原始数据进行过滤，保留品类的下单数据
    val orderDatas = lines.filter(
      line => {
        val datas = line.split("_")
        datas(8) != "null"
      }
    )

    // 3.2 将过滤后的数据进行统计
    // 1-3-2 => 1,3,2 => (1,1),(3,1),(2,1)
    val orderCnt = orderDatas.flatMap(
      line => {
        val datas = line.split("_")
        val ids = datas(8).split(",")
        ids.map(
          id => {
            (id, 1)
          }
        )
      }
    ).reduceByKey(_ + _)

    // TODO 4. 统计品类的支付数量
    // 4.1 将原始数据进行过滤，保留品类的支付数据
    val payDatas = lines.filter(
      line => {
        val datas = line.split("_")
        datas(10) != "null"
      }
    )


    // 4.2 将过滤后的数据进行统计
    // 1-3-2 => 1,3,2 => (1,1),(3,1),(2,1)
    val payCnt = payDatas.flatMap(
      line => {
        val datas = line.split("_")
        val ids = datas(10).split(",")
        ids.map(
          id => {
            (id, 1)
          }
        )

      }
    ).reduceByKey(_ + _)


    // TODO 5. 将统计结果排序（点击，下单，支付）(降序)
    // （品类，点击）
    // （品类，下单）  => (（品类， （点击，下单，支付）)
    // （品类，支付）

    val clickNewCnt = clickCnt.mapValues(
      cnt => (cnt, 0, 0)
    )
    val orderNewCnt = orderCnt.mapValues(
      cnt => (0, cnt, 0)
    )
    val payNewCnt = payCnt.mapValues(
      cnt => (0, 0, cnt)
    )
    val totalCntRdd = clickNewCnt.union(orderNewCnt).union(payNewCnt)
    val allCnt = totalCntRdd.reduceByKey(
      (t1, t2) => {
        (t1._1 + t2._1, t1._2 + t2._2, t1._3 + t2._3)
      }
    )

    // TODO 6. 将排序后的结果取前10名

    val sortCnt = allCnt.sortBy(_._2, false)
    sortCnt.take(10)
  }
}
