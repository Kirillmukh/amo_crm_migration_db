package com.example.dbeaver.criteria;

import com.example.dbeaver.criteria.conditions.CompositeCondition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Criteria<T> {
    public Criteria(Criteria<T> criteria){
        this.distinct = criteria.distinct;
        this.conditions = new ArrayList<>(criteria.conditions);
        this.orders = new ArrayList<>(criteria.orders);
        this.limit = criteria.limit;
        this.offset = criteria.offset;
    }
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

    public void unionConditionWithAnd() {
        if (conditions.size() <= 1) {
            return;
        }
        List<Condition<T>> newConditions = new ArrayList<>();
        newConditions.add(new CompositeCondition<>(CompositeCondition.LogicalOperator.AND, conditions));
        this.conditions = newConditions;
    }
}
