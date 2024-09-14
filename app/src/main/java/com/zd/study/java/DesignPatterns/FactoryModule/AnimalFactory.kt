package com.zd.study.java.DesignPatterns.FactoryModule

import com.zd.study.java.DesignPatterns.FactoryModule.inter.Animal

class AnimalFactory {
    fun createAnimal(type: AnimalType): Animal?{
        return when (type) {
            AnimalType.CAT -> {
                Cat()
            }

            AnimalType.DOG -> {
                Dog()
            }
            else -> {
                null
                throw IllegalArgumentException("Invalid animal type: " + type);
            }
        }
    }
}