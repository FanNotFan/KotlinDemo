//package com.demo.kotlin.dataclass
//
//
//data class CancellationPolicyTest (val policyName: String, val currency: String)
//
//fun main() {
//    var cancellationPolicy: CancellationPolicyTest = CancellationPolicyTest("FLEXED","USD")
//    var cancellationPolicy2: CancellationPolicyTest = CancellationPolicyTest("FLEXED","USD")
//    println(cancellationPolicy.toString())
//    println(cancellationPolicy.equals(cancellationPolicy2))
//    println(cancellationPolicy == cancellationPolicy2)
//    println(cancellationPolicy === cancellationPolicy2)
//
//    println(cancellationPolicy.component1())
//    println(cancellationPolicy.component2())
//
//    // 数据类 解构
//    val(policyName, currency) = cancellationPolicy
//    println(policyName)
//    println(currency)
//
//    // copy 函数
//    var cancellationPolicy3 = cancellationPolicy2.copy()
//    println(cancellationPolicy3 === cancellationPolicy2)
//    // 选择性修改
//    var cancellationPolicy4 = cancellationPolicy2.copy(policyName = "STRICT")
//    println(cancellationPolicy4.toString())
//}