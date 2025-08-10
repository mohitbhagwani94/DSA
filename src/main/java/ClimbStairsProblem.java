class ClimbStairsProblem {
    int cache[];
    public int climbStairs(int n) {
        cache = new int[n+1];
        for(int i=0;i<=n;i++){
            cache[i] = -1;
        }
        return dfs(n,0);
    }

    public int dfs(int n, int step){
        if(step>=n){
            return step==n?1:0;
        }
        if(cache[step]!= -1){
            return cache[step];
        }
        return dfs(n,step+1)+dfs(n,step+2);
    }
}
