package org.example;

public class NestedLightWeightSum2 {
    /**
     * @param nestedList: a list of NestedInteger
     * @return: the sum
     */

    public int findMaxDepth(List<NestedInteger> nestedList) {
        int depth = 1;
        for(NestedInteger item : nestedList){
            if(!item.isInteger()){
                depth = Math.max(depth,1+findMaxDepth(item));
            }
        }
        return depth;
    }

    public long calculatedWeight(List<NestedInteger> nestedList, int weight) {
        long depthWeightSum = 0;
        for(NestedInteger item : nestedList){
            if(item.isInteger()){
                depthWeightSum +=  weight * item.getInteger();
            } else {
                depthWeightSum += calculatedWeight(item.getList(), weight-1);
            }
        }
        return depthWeightSum;
    }

    public int depthSumInverse(List<NestedInteger> nestedList) {
        int maxDepth =  findMaxDepth(nestedList);

        int weigth = calculatedWeight(List<NestedInteger> nestedList,maxDepth);
        return weight;
    }

}
