package com.zg.scala.repratice

import java.util

/**
 * @Project_name test
 * @Package_name com.zg.scala.repratice
 * @author zhuguang
 * @date 2020-12-18-18:33
 */
object ScalaFunction5 {
  def main(args: Array[String]): Unit = {
    def fun7(op: => Unit) = {
      op
    }
    //    5.2.7 控制抽象
    fun7 {
      println("xx")
    }
    fun7 {
      def fun77(f: (Int, Int) => Int) = {
        f
      }
    }

    //    5.2.6 函数柯里化
    def fun6(i: Int)(j: Int) = {
      i * j
    }


    def fun66(i: Int, j: Int) = {
      i * j
    }

    println(fun6(10)(2))
    println(fun66(8, 8))

    //    递归函数
    def fun8(j: Int): Int = {
      if (j <= 1) {
        1
      } else {
        j * fun8(j - 1)
      }
    }

    println(fun8(80))

    //    5.2.9 惰性函数
    def fun9(): String = {
      println("funciton")
      "zhang"
    }

    lazy val a = fun9()
    println("----------")
    println(a)


  }
}

//	package关键字可以嵌套声明使用
package com {
  package atguigu {
    package bigdata {
      package scala {

        object ScalaPackage {
          def test() = {
            println("pacae test......")
          }
        }

      }

    }

  }

}

//	同一个源码文件中子包可以直接访问父包中的内容，而无需import
package com {
  package bigdata {

    class Test {

    }

    package scala {

      object ScalaPackage {
        def test() = {
          new Test()
        }
      }

    }

  }

}

//	Scala中package也可以看作对象，并声明属性和函数
package com {

  package object atguigu {
    val name: String = "zhangsan"

    def test(): Unit = {
      println(name)
    }
  }
  package atguigu {
    package bigdata {
      package scala {

        object ScalaPackage1 {
          def test(): Unit = {
          }
        }

      }

    }

  }

}

//	Scala中的import语法可以在任意位置使用
object ScalaImport1 {
  def main(args: Array[String]): Unit = {
    import java.util.ArrayList
    new util.ArrayList()
  }

}

//	Scala中可以导包，而不是导类
object ScalaImport2{
  def main(args: Array[String]): Unit = {
    import java.util
    new util.ArrayList()
  }
}

//	Scala中可以在同一行中导入多个类，简化代码
import java.util.{List,ArrayList,Date}


//	Scala中可以屏蔽某个包中的类
import java.sql.{Date=>_,Array=>_, _}


//	Scala中可以给类起别名，简化使用
import java.util.{ArrayList=>AList}
object ScalaImport3{
  def main(args: Array[String]): Unit = {
    new AList()
  }
}

//	Scala中可以使用类的绝对路径而不是相对路径
import _root_.java.util.ArrayList


//	默认情况下，Scala中会导入如下包和对象
import java.lang._
import scala._

