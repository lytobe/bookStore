package com.ly.entity;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Car {
//    private Integer totalCount;
//    private BigDecimal totalPrice;
    private Map<Integer,CarItem> items = new LinkedHashMap<Integer,CarItem>();

    public void addItem(CarItem carItem){
        //�Ȳ鿴���ﳵ���Ƿ����и���Ʒ�����У��ۼӣ����ޣ�ֱ�Ӽ�
        CarItem item = items.get(carItem.getId());
        if (item==null){
            //Ϊ��ӹ�
            items.put(carItem.getId(),carItem);
        }
        else{
            //����ӹ�
            item.setCount(item.getCount()+1);//�����ۼ�
            //�����ܽ��
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
            carItem.setCount(count);//�����ۼ�
            //�����ܽ��
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
