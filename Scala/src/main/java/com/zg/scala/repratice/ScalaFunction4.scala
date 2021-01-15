package com.zg.scala.repratice

/**
 * @Project_name test
 * @Package_name com.zg.scala.repratice
 * @author zhuguang
 * @date 2020-12-17-10:34
 */
object ScalaFunction4 {
  def main(args: Array[String]): Unit = {
    /*

//    1)	无参，无返回值

def f1() = {
print("1)\t无参，无返回值")
}

//    2)	无参，有返回值
def f2(): String = {
return "2)\t无参，有返回值"
}

//    3)	有参，无返回值

def f3(s: String) = {
print("3)\t有参，无返回值")
}

//    4)	有参，有返回值
def f4(s: String): String = {
return "4)\t有参，有返回值"
}

//    5)	多参，无返回值
def f5(name: String, age: Int) = {
print("5)\t多参，无返回值")
}

//    6)	多参，有返回值
def f6(name: String, age: Int): String = {
"name" + name + "age" + age
}

//    1)	可变参数
def f7(name: String*) = {
println(name)
}

//    2)	参数默认值
def f8(name: String, age: Int = 555) = {
println(name + age)
}

//    1)	省略return关键字
def f9(name: String): String = {
"1)\t省略return关键字"
}

//    2)	省略花括号
def f10(name: String): String = "2)\t省略花括号"

//    3)	省略返回值类型
def f11(name: String) = "3)\t省略返回值类型"

//    4)	省略参数列表
def f12 = "4)\t省略参数列表"

//    5)	省略等号
def f13(): Unit = {
"5)\t省略等号"
}
//    1)	省略名称和关键字
() => {
println("1)\t省略名称和关键字")
}


//      函数作为值
def fun14(): String = {
" 函数作为值"
}

val a = fun14
val b = fun14 _

*/
    //       函数作为参数
    def fun15(f: (Int, Int) => Int): Int = {
      f(3, 3)
    }

    def f16(i: Int, j: Int): Int = {
      i + j
    }

    def f17(i: Int, j: Int): Int = {
      i / j
    }

    println(fun15(f16 _))
    println(fun15(f17 _))

    //    5.2.3 函数作为返回值
    def f18(i: Int): Int = {
      i * i
    }

    def f1818() = {
      f18 _
    }

    println(f1818()(8))



    //    匿名函数

    //    def f19(F: (Int, Int) => Int): Int = {
    //      F(8, 8)
    //    }
    //
    //    println(f19((x: Int, y: Int) => {
    //      x + y
    //    }))
    //    println(f19((x: Int, y: Int) => x * y))
    //    println(f19((x, y) => x / y))
    //    println(f19((_ % _)))


    //    闭包
    def f20() = {
      val i = 20

      def f2020() = {
        i * 2
      }

      f2020 _

    }

    println(f20()())


  }

}
