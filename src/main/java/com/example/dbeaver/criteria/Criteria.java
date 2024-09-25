package com.example.dbeaver.criteria;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Criteria<T> {
    private boolean distinct = false;
    private List<Condition<T>> conditions = new ArrayList<>();
    private List<Order<T>> orders = new ArrayList<>();
    private int limit = -1;
    private int offset = 0;
    public void addCondition(Condition<T> condition) {
        conditions.add(condition);
    }
    private void addOrder(Order<T> order) {
        orders.add(order);
    }
}
