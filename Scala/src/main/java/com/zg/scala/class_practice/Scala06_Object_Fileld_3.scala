package com.zg.scala.class_practice

import com.zg.scala.class_practice.parent.child1.Emp

/**
 * @Project_name test
 * @Package_name com.zg.scala.class_practice
 * @author zhuguang
 * @date 2020-12-19-9:11
 */
object Scala06_Object_Fileld_3 {
  def main(args: Array[String]): Unit = {
//   访问权限
    val user = new User()
    val emp = new parent.child1.Emp()
//    println(emp.name)
//   println(emp.age)
//   println(emp.email)
    println(emp.password)



  }


  class User {
    var name: String = _
    val age: Int = 0
  }


}

package parent{
  package child1{
    class Emp{
        private var name:String="zhansgsan"
        private[parent] var age:Int=30
      protected var email:String="aaaa"
      val password:String="adfasdfa"
      def test()={
        println(name)
        println(age)
        println(email)
        println(password)
      }
    }
    package child2{
      class Student extends parent.child1.Emp {
        def test1()={
//          println(this.name)
//          println(this.age)
          println(this.email)
          println(this.password)

        }

      }

    }

  }
}