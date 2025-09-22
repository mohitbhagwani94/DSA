package Test;

public class Test {
    int row ;
    int col ;
    int maxArea;
    int area;
    int grid[][];
    public void findNoOfIslandAndMaxArea(int grid[][]){
        this.grid = grid;
        row = grid.length;
        col = grid[0].length;
        int noOfIsland = 0;
        maxArea = 0;
        for(int i = 0; i < row; i++ ){
            for(int j = 0; j < col; j++){
                if(grid[i][j] == 1){
                    noOfIsland++;
                    area = 0;
                    dfs(i,j);
                }
            }
        }
        System.out.println(" noOfIsland :" + noOfIsland);
        System.out.println(" maxArea :" + maxArea);

    }

    public void dfs(int r, int c){

        if(r < 0 || c < 0 || r >= row || c >= col || grid[r][c] == 0 || grid[r][c] == 2 )
            return;

        grid[r][c] = 2;
        area++;

        maxArea = Math.max(maxArea,area);

        dfs( r + 1 ,c );
        dfs( r - 1 ,c );
        dfs( r ,c - 1 );
        dfs( r ,c + 1 );

        dfs( r - 1 ,c - 1 );
        dfs( r - 1 ,c + 1 );
        dfs( r + 1 ,c - 1 );
        dfs( r + 1 ,c + 1 );

    }

    public static void main(String[] args) {
       int grid[][] =  new int[][]{
                {1,1,0,1,0},
                {0,0,0,1,0},
                {1,1,0,1,1},
                {0,0,0,1,0}};
        int grid2[][] =  new int[][]{
                {0,0,0,0,0},
                {1,0,0,0,0}};

       Test t = new Test();

        t.findNoOfIslandAndMaxArea(grid);
    }
}

/*
Sending over email as candidate is not able to copy from zoom
Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1, 9
Example 2:

Input: grid = [
     0 , 1,  2, 3,  4
  0["2","2","0","0","0"],
  1["2","2","0","0","0"],
  2["0","0","2","0","0"],
  3["0","0","0","2","2"]
]

Output: 3(no of Island) , 4 size of the island(max)

constrain 300 X 300
Move allowed up down right left

Step 1: No of island
 */



