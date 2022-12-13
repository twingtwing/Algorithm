package practice.array;
/**
 * Matrix Rotate(2차원 배열 회전)
 * 추가 공간 없이 배열을 회전시키는 알고리즘
 * !!keypoint!!
 * 임시 변수를 사용하여 퍼즐 이동처럼 반복하여 이동시킨다.
 *
 * 응용
 * 이차원 베열안에서 9을 발견하면, 해당 행과 열을 모두 0으로 변환하는 알고리즘
 * => 해당 0이 치환한 0인지 아닌지에 대한 판단이 필요
 * => 저장공간 미사용
 * 저장공간을 사용하면 쉽지만, 미사용할 경우 이차원 배열을 활용하여 변환한다.
 */
public class Matrix_Rotate {
    public static void main(String[] args) {
        int[][] image = {
                {1,0,0,0,1},
                {0,1,0,1,0},
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
        };

        pringMtrix(image);

        pringMtrix(rotateMatrix(image));
        pringMtrix(rotateMatrix(image));
        pringMtrix(rotateMatrix(image));
        pringMtrix(rotateMatrix(image));

        int[][] matrix = {
                {1,1,1,1},
                {1,0,1,1},
                {1,1,1,0},
                {1,0,1,1}
        };
        pringMtrix(matrix);
        setZeroToAllZero(matrix);
        pringMtrix(matrix);

    }

    // 회전 알고리즘 : 가장 바깥의 테두리부터 회전한다.
    //               테두리를 회전할때, 같은 거리를 가진 4개의 변수를 회전시키는 작업을 반복한다.
    private static int[][] rotateMatrix(int[][] matrix) {
        int tmp; //임시 저장 변수
        // 같은 거리의 4개의 변수를 회전하고, 이를 반복함
        for (int s = 0, e = matrix.length - 1; s < e; s++, e--) { // 가장 겉의 테두리 부터 회전함
            for (int i = s, j = e; i < e; i++, j--) { // 테두리 전테를 회전시킴
                tmp = matrix[s][i];
                matrix[s][i] = matrix[i][e];
                matrix[i][e] = matrix[e][j];
                matrix[e][j] = matrix[j][s];
                matrix[j][s] = tmp;
            }
        }
        return matrix;
    }

    private static void pringMtrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    //응용
    // 가장 처음 찾은 행과 열에 0으로 변환해야할 행과 열을 0으로 저장한다.
    private static void setZeroToAllZero(int [][] matrix){
        int zeroCol = -1;
        int zeroRow = -1;
        for (int row = 0; row < matrix.length; row++){
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 0) {
                    if (zeroCol == -1) {
                        zeroCol = col;
                        zeroRow = row;
                    }
                    matrix[zeroRow][col] = 0;
                    matrix[row][zeroCol] = 0;
                }
            }
        }
        if (zeroCol == -1) return;

        // 주의!! 행과 열 둘중 하나 먼저를 0으로 만들때,
        // 먼저 수행하는 행과 열이 다음에 수행하는 열과 행을 모두 0을 만들지 않도록 수정해야함
        for (int row = 0; row < matrix.length; row++) {
            if (matrix[row][zeroCol] == 0 && row != zeroRow) setZeroCol(matrix, row);
        }
        for (int col = 0; col < matrix[0].length; col++) {
            if (matrix[zeroRow][col] == 0) setZeroRow(matrix, col);
        }

        // 위에서 막았던 가장 처음에 찾은 행을 0으로 만들어준다.
        setZeroCol(matrix, zeroRow);
    }

    private static void setZeroCol(int[][] matrix, int row) {
        for (int col = 0; col < matrix[row].length; col ++) matrix[row][col] = 0;
    }

    private static void setZeroRow(int[][] matrix, int col) {
        for (int row = 0; row < matrix.length; row++) matrix[row][col] = 0;
    }

}
