package NestedLightWeightSum2;

import java.util.List;

public class NestedLightWeightSum2 {
    /**
     * @param nestedList: a list of NestedInteger
     * @return: the sum
     */

    public int findMaxDepth(List<NestedInteger> nestedList) {
        int depth = 1;
        for(NestedInteger item : nestedList){
            if(!item.isInteger()){
                depth = Math.max(depth,1+findMaxDepth(item.getList()));
            }
        }
        return depth;
    }

    public int calculatedWeight(List<NestedInteger> nestedList, int weight) {
        int depthWeightSum = 0;
        for(NestedInteger item : nestedList){
            if(item.isInteger())
                depthWeightSum +=  weight * item.getInteger();
            else
                depthWeightSum += calculatedWeight(item.getList(), weight-1);
        }
        return depthWeightSum;
    }

    public int depthSumInverse(List<NestedInteger> nestedList) {
        int maxDepth =  findMaxDepth(nestedList);

        int weight = calculatedWeight(nestedList,maxDepth);
        return weight;
    }

}

