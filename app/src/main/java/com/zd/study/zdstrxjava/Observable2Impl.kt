package com.zd.study.zdstrxjava

import com.zd.study.zdstrxjava.inter.Observable2

/**
 * 泛型声明在方法上,则除去该声明有 T 泛型的方法之外,其他方法不识别 T 类型
 */
class Observable2Impl: Observable2 {

    override fun <T> call(t: T): T {
        TODO("Not yet implemented")
    }
}