package DynamicPrograming;

import java.util.ArrayList;

/**
 * Dynamic Program을 이용한 응용 2,
 * 로봇이 그리드(바둑판) 왼쪽 상단에 위치하고 있고, 아래 혹은 오른쪽으로 밖에 움직이지 못한다.
 * 로봇이 들어가지 못하는 특정한 칸이 있다.
 * => 좌측 상단에서 우측 하단으로 이동하는 알고리즘
 * => 도착점부터 출발점까지 반대로 경로를 재귀함수로 구하고, 출발점에 도착하면 이때까지의 경로를 출력한다.
 */
class Point{
    int row,col;
    Point(int row, int col){
        this.row = row;
        this.col = col;
    }
}

class Solution{
    public ArrayList<Point> findPath(boolean[][] grid){
        if(grid == null || grid.length == 0) return null;
        ArrayList<Point> path = new ArrayList<>();
        if (findPath(grid,grid.length - 1, grid[0].length -1, path)) return path; // 우측 하단 골인을 시작점으로 지정
        else return null;
    }

    private boolean findPath(boolean[][] grid, int row, int col, ArrayList<Point> path){
        if (!isInRange(grid,row,col) || grid[row][col]) return false; // 해당 포인터가 그리드 밖에 있거나, 들어갈 수 없는 포인터인 경우
        // 경로에 추가해야하는 경우
        if ((row == 0 && col == 0) || findPath(grid,row,col-1,path) || findPath(grid,row-1,col,path)){ // 왼쪽으로 가는 경우를 먼저 구하고, 안되면 위쪽으로 가는 경우를 구함
                                                                                                                // or연산자를 통해, 실패한 경우에만 재귀함수를 탄다.
                                                                                                                // 그렇다면, or연산자는 앞에서 true가 나오면, 뒤는 실행하지 않는가?
            Point point = new Point(row,col);
            path.add(point);
            return true;
        }
        return false;
    }

    private boolean isInRange(boolean[][] grid, int row, int col){
        return row >= 0 && row <= grid.length - 1 && col >= 0 && col <= grid[row].length - 1;
    }
}

public class Apply02 {
    public static void main(String[] args) {
        boolean [][] grid = {
                {false,false,false,false},
                {false,false,false,true},
                {true,true,false,false},
                {false,false,false,false}
        };

        Solution sol = new Solution();
        ArrayList<Point> path = sol.findPath(grid);
        if (path != null){
            for (Point p : path){
                System.out.print(p.row+""+p.col+" -> ");
            }
        }

    }
}
