package com.ly.test;

import com.ly.entity.Car;
import com.ly.entity.CarItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void addItem() {
        Car car = new Car();
        car.addItem(new CarItem(1,"Java从入门到精通",1,new BigDecimal(20),new BigDecimal(20)));
        car.addItem(new CarItem(1,"java从入门到精通",1,new BigDecimal(20),new BigDecimal(20)));
        car.addItem(new CarItem(3,"python从入门到精通",1,new BigDecimal(20),new BigDecimal(20)));

        System.out.println(car);
    }

    @Test
    void deleteItem() {
        Car car = new Car();
        car.addItem(new CarItem(1,"Java从入门到精通",1,new BigDecimal(20),new BigDecimal(20)));
        car.addItem(new CarItem(1,"java从入门到精通",1,new BigDecimal(20),new BigDecimal(20)));
        car.addItem(new CarItem(3,"python从入门到精通",1,new BigDecimal(20),new BigDecimal(20)));

        car.deleteItem(1);
        System.out.println(car);
    }


    @Test
    void clear() {
        Car car = new Car();
        car.addItem(new CarItem(1,"Java从入门到精通",1,new BigDecimal(20),new BigDecimal(20)));
        car.addItem(new CarItem(1,"java从入门到精通",1,new BigDecimal(20),new BigDecimal(20)));
        car.addItem(new CarItem(3,"python从入门到精通",1,new BigDecimal(20),new BigDecimal(20)));

        car.clear();
        System.out.println(car);
    }

    @Test
    void update() {
        Car car = new Car();

        car.addItem(new CarItem(1,"Java从入门到精通",1,new BigDecimal(20),new BigDecimal(20)));

        car.update(1,10);
        System.out.println(car);
    }
}