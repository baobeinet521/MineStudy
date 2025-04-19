package com.zd.study.zdstrxjava.inter

/**
 * 将泛型声明放在方法
 */
interface Observable2 {
    fun <T> call(t: T): T
}