package com.example.bizmodule

import com.zd.frameworkmodule.inter.IAnimalInterface

class BizUrlManager: IAnimalInterface {
    override fun getAnimalName(): String {
        return "组件biz"
    }
}