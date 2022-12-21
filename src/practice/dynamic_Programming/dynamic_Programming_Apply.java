package practice.dynamic_Programming;

import java.util.ArrayList;

/**
 * Dynamic Program
 * 1. 최소 비용으로 계단 오르기
 * 계단을 오를때마다 지정된 비용을 지불애햐하고, 계단은 1 ~ 2 칸만 가능하다. 계단 시작점은 0 혹은 1 이다.
 * !! key point : 뒤에서부터 시작하여 값을 구한다.
 * 2. 로봇이 grid 왼쪽 상단에 위치하고 있고, 아래 혹은 오른똑으로 밖에 움직이지 못한다.
 * 좌측 상단에서 우측하단으로 이동하는 경로를 구하는 알고리즘
 * 최소비용 계단과 마찬가지로 뒤에서부터 시작한다.
 */
public class dynamic_Programming_Apply {
    // 응용 1
    public static int minCostStaris(int[] stairCost) {
        int one = 0;
        int two = 0;
        int current;
        for (int i = stairCost.length - 1; i >= 0; i--) {
            // i위치에서 1 ~ 2 칸전에서 내려온 비용중에 최소 비용을 찾음
            current = stairCost[i] + Math.min(one,two);
            two = one; //현재 위치에서 1칸 전(다음 위치와 2칸 차이)
            one = current; // 현재위치(다음위치와 1칸 차이)
        }
        // 시작 위치를 0 혹은 1로 할지 구함
        return Math.min(one,two);
    }

    public static void main(String[] args) {
        int[] cost = new int[] {1,2,3,4,5,6,7};
        int result = minCostStaris(cost);
        System.out.println(result);
        
        boolean [][] grid = {
                {false,false,false,false},
                {false,false,false,true},
                {true,true,false,false},
                {false,false,false,false}
        };

        Solution sol = new Solution();
        ArrayList<Point> path = sol.findPath(grid);
        if (path != null) {
            for (Point p : path) {
                System.out.print(p.row + "" + p.col + " -> ");
            }
        }
    }
}

// 응용 2
class Point{ // 위치를 저장하는 객체
    int row;
    int col;
    Point(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class Solution{
    public ArrayList<Point> findPath(boolean[][] grid) {
        if (grid == null || grid.length == 0) return null;
        ArrayList<Point> path = new ArrayList<>();
        // 좌측 상단 -> 우측 하단 이므로 우측 하단 (row,col)-> 좌측 상단(0,0)으로 이동
        return findPath(grid, grid.length - 1, grid[0].length - 1, path) ? path : null;
    }

    private boolean findPath(boolean[][] grid, int row, int col, ArrayList<Point> path) {
        // 범위에 벗어나거나, 장애물에 도달 하였을 경우
        if (!isInRange(grid,row,col) || grid[row][col]) return false;

        // 좌측상단(0,0)은 무조건 경로에 추가하는 동시에 재귀함수를 멈추어야한다.
        if ((row == 0 && col == 0) || findPath(grid,row, col -1, path) || findPath(grid, row - 1, col, path)){
            Point point = new Point(row,col);
            path.add(point);
            return true;
        }
        return false;
    }

    private boolean isInRange(boolean[][] grid, int row, int col) {
        return row >= 0 && row <= grid.length - 1 && col >= 0 && col <= grid[0].length -1;
    }

}


