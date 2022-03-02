package com.demo.kotlin.dataclass

import com.alibaba.fastjson.JSON


data class User(var name: String, var age: Int) {}

fun main() {
    var jsonString = "{\"name\":\"haha\",\"versionID\":1}"
    val user: User? = JSON.parseObject(jsonString,User::class.java)

    println(user)
}