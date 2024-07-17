package com.example.myapplication

fun main() {
     var given = 1124

    var product = given.toString().map { it.digitToInt() }.reduce { acc, i -> acc * i }
    var sum = given.toString().map { it.digitToInt() }.sum()

    if(sum == product){
        println("Spy")
    }else{
        println("Not Spy")
    }
}

fun findNeonNumber(given: Int) {

    var squareNumber = given * given // 2025

    var sumOfAll = sumOfDigits(squareNumber)

    if(sumOfAll == given){
        println("Given no is Neon $given")
    }else{
        println("No $given NOT neon")
    }


}

fun sumOfDigits(squareNumber: Int): Any {
    var sum = 0
    var temp = squareNumber
    while (temp > 0){
        var last = temp % 10
        sum = sum + last
        temp = temp / 10
    }
    return sum
}

