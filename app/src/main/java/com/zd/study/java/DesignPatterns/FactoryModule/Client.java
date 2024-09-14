package com.zd.study.java.DesignPatterns.FactoryModule;

import com.zd.study.java.DesignPatterns.FactoryModule.inter.Animal;

public class Client {
    public static void main(String[] args) {
        Animal Dog = new AnimalFactory().createAnimal(AnimalType.DOG);
        Dog.speak();
    }
}
