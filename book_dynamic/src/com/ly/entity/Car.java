package com.ly.entity;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Car {
//    private Integer totalCount;
//    private BigDecimal totalPrice;
    private Map<Integer,CarItem> items = new LinkedHashMap<Integer,CarItem>();

    public void addItem(CarItem carItem){
        //先查看购物车中是否已有该商品，若有，累加，若无，直接加
        CarItem item = items.get(carItem.getId());
        if (item==null){
            //为添加过
            items.put(carItem.getId(),carItem);
        }
        else{
            //已添加过
            item.setCount(item.getCount()+1);//数量累加
            //更新总金额
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    public void deleteItem(Integer id){
        items.remove(id);

    }

    public void clear(){
        items.clear();
    }

    public void update(Integer id,Integer count){
        CarItem carItem = items.get(id);
        if (carItem!=null){
            carItem.setCount(count);//数量累加
            //更新总金额
            carItem.setTotalPrice(carItem.getPrice().multiply(new BigDecimal(carItem.getCount())));
        }
    }



    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer,CarItem>entry :items.entrySet()){
            totalCount+=entry.getValue().getCount();
        }
        return  totalCount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer,CarItem>entry:items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CarItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CarItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Car{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
