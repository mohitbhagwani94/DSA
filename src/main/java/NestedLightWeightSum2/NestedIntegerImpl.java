package NestedLightWeightSum2;

import java.util.*;

public class NestedIntegerImpl implements NestedInteger {
    private Integer value;
    private List<NestedInteger> list;

    // Constructors
    public NestedIntegerImpl() {
        this.list = new ArrayList<>();
    }

    public NestedIntegerImpl(int value) {
        this.value = value;
    }

    // Interface Methods
    @Override
    public boolean isInteger() {
        return value != null;
    }

    @Override
    public Integer getInteger() {
        return value;
    }

    @Override
    public List<NestedInteger> getList() {
        return list;
    }

    // Utility Methods for easy building
    public void add(NestedInteger ni) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(ni);
    }

    @Override
    public String toString() {
        return isInteger() ? value.toString() : list.toString();
    }
}
