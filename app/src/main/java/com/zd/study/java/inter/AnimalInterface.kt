package com.zd.study.java.inter

interface AnimalInterface {
    companion object{
        private var name = ""

    }
    abstract fun price()
    fun clickAnimal()
}