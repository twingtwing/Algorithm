package 엔지니어_대한민국.Arrays_Strings;
/**
 * 응용 : 이차원 배열안에서 0을 발견하면, 해당 행과 열을 모두 0으로 변환하는 알고리즘
 *        => 발견할때마다, 0을 치환하면 원래 0이었는지 치환하여 0이었는지 판단이 힘듦
 *        => 저장공간을 사용하면, 치환할 행과 열을 따로 저장하여 모두 찾은 후에 치환하는 방법이 있다.
 *        => 저장공간을 사용하지 않는 경우에는, 어차피 0으로 셋팅될 공간을 활용함
 *           행과 열을 0으로 치환할때 미리 0인 데이터는 표시해놓음
 *           치환할경우에는 처음 행의 0을 제외하고 치홚나다.
 *           이제 정보에 따라 치환?
 */
public class MatrixApply2 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,1,1,1},
                {1,0,1,1},
                {1,1,1,0},
                {1,0,1,1}
        };
        printImage(matrix);
        setZeroToAllZero(matrix);
        printImage(matrix);
    }

    private static void setZeroToAllZero(int[][] matrix){
        // 0이 들어갈 위치를 저장할 행과 열의 방번호를 변수에 할당
        int fc = -1;
        int fr = -1;
        for (int row = 0; row <matrix.length; row++){
            for (int col = 0; col < matrix[row].length; col++){
                if (matrix[row][col] == 0){
                    if (fc == -1){ //처음 찾은 0일 경우
                        fc = col;
                        fr = row;
                    }
                    matrix[fr][col] = 0;
                    matrix[row][fc] = 0;
                }
            }
        }
        if (fc == -1) return;
        for (int col = 0; col < matrix[0].length; col ++){
            if (matrix[fr][col] ==0 && col != fc){
                setColsToZerp(col,matrix);
            }
        }
        for (int row = 0; row < matrix.length; row ++){
            if (matrix[row][fc] ==0 ){ //row != fr은 왜 넣지 않지?
                setRowsToZerp(row,matrix);
            }
        }
        setColsToZerp(fc,matrix);
    }

    private static void setColsToZerp(int col, int[][] matrix){
        for (int row = 0; row < matrix.length; row ++){
            matrix[row][col] = 0;
        }
    }

    private static void setRowsToZerp(int row, int[][] matrix){
        for (int col = 0; col < matrix[row].length; col ++){
            matrix[row][col] = 0;
        }

    }

    private static void printImage(int[][] image){
        for (int i= 0; i < image.length;i++){
            for (int j = 0; j<image[i].length; j++){
                System.out.print(image[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
