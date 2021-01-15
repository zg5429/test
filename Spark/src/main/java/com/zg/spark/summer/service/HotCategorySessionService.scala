package com.zg.spark.summer.service

import com.zg.spark.summer.bean.UserVisitAction
import com.zg.spark.summer.common.TService
import com.zg.spark.summer.dao.{HotCategoryDao, HotCategorySessionDao}
import org.apache.spark.rdd.RDD

/**
 * @Project_name test
 * @Package_name com.zg.spark.summer.service
 * @author zhuguang
 * @date 2021-01-03-16:51
 */
class HotCategorySessionService extends TService {
  private val hotCategorysessionDao = new HotCategorySessionDao()

  override def analysis(data: Any) = {
    //需求一的结果
    val array: Array[(String, (Int, Int, Int))] = data.asInstanceOf[Array[(String, (Int, Int, Int))]]
    val top10Ids: Array[String] = array.map(_._1)

    //将原始数据进行过滤 只保留前10热门品类的数据
    val actionRDD = hotCategorysessionDao.readFile("data/user_visit_action.txt")

    //    将原始数据转化内类（对象）使用
    val actionObject = actionRDD.map(
      line => {
        val datas = line.split("_")
        UserVisitAction(
          datas(0),
          datas(1).toLong,
          datas(2),
          datas(3).toLong,
          datas(4),
          datas(5),
          datas(6).toLong,
          datas(7).toLong,
          datas(8),
          datas(9),
          datas(10),
          datas(11),
          datas(12).toLong
        )

      }
    )
    val filterActionObjs = actionObject.filter(
      action => {
        if (action.click_category_id != -1) {
          top10Ids.contains(action.click_category_id.toString)
        } else {
          false
        }
      }
    )


    val reduceActionObjs = filterActionObjs.map(
      action=>{
        ((action.click_category_id,action.session_id),1)
      }
    ).reduceByKey(_+_)

   val transformActionObjs =  reduceActionObjs.map{
      case ((category,sesseion),sum)=>{
        (category,(sesseion,sum))
      }
    }


    val groupAcrionObjs: RDD[(Long, Iterable[(String, Int)])] = transformActionObjs.groupByKey()


    groupAcrionObjs.mapValues(
      iter=>{
        iter.toList.sortBy(_._2)(Ordering.Int.reverse).take(10)
      }
    ).collect




  }
}
