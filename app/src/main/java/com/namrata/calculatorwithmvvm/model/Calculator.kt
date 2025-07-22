package com.namrata.calculatorwithmvvm.model

object Calculator {
   fun calculate(expression:String):String{
       var exp = expression
       var prefix =""

           if (exp.startsWith("-")){
               prefix = "-"
               exp = exp.substring(1)
           }

           return try {

               when {
                   exp.contains("-") -> {
                       val splitValue = exp.split("-")
                       var one = splitValue[0]
                       var two = splitValue[1]
                       removeZeroAfterDot(((one.toDouble() - two.toDouble())).toString())

                   }

                   exp.contains("*") -> {
                       val splitValue = exp.split("*")
                       var one = splitValue[0]
                       var two = splitValue[1]
                       removeZeroAfterDot(((one.toDouble() * two.toDouble())).toString())
                   }

                   exp.contains("+") -> {
                       val splitValue = exp.split("+")
                       var one = splitValue[0]
                       var two = splitValue[1]
                       removeZeroAfterDot(((one.toDouble() + two.toDouble())).toString())
                   }

                   exp.contains("/") -> {
                       val splitValue = exp.split("/")
                       var one = splitValue[0]
                       var two = splitValue[1]
                        removeZeroAfterDot(((one.toDouble() / two.toDouble())).toString())
                   }

                   else -> {
                       exp
                   }
               }
        } catch (e: Exception) {
           e.printStackTrace()
           "Error"

       }
   }
    private fun removeZeroAfterDot(result: String):String{
        var value = result
        if (result.contains(".0")){
            value = result.substring(0, result.length - 2)
        }
        return value
    }

}