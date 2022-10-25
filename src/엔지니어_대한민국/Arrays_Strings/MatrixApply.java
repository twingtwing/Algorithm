package 엔지니어_대한민국.Arrays_Strings;
/**
 * Matrix(이차원배열) 회전 : 특정한 공간없이 배열을 회전시키는 알고리즘
 *                        => 배열방중 데이터1개를 임시변수에 저장 시키고, 삭제한다. 그러면 해당 자리가 비워지므로 회전할방향의 데이터를 이 위치에 저장시킨다.
 *                           그러면 이제 그 데이터위치가 비었으므로, 이 과정을 반복하면, 더이상 회전할 데이터는 없고, 자리 한 곳이 비어있게 되는데 그 곳이 임시변수가 저장한 데이터가 있을 위치이므로 저장한다.
 *                           이와같은 작업을 다른 데이터들도 계속 반복하면, 이차원 배열이 회전되어 있다.
 */
public class MatrixApply {
    public static void main(String[] args) {
        int[][] image = {
                {1,0,0,0,1},
                {0,1,0,1,0},
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
        };
        printImage(image);

        printImage(rotateImage(image));
        printImage(rotateImage(image));
        printImage(rotateImage(image));
        printImage(rotateImage(image));
    }
    private static int[][] rotateImage(int[][] image){
        int tmp; // 임시 저장 변수
        for (int s = 0, e = image.length - 1; s < e; s++,e--){
            for (int i = s, j = e; i < e; i++, j--){
                tmp = image[s][i];
                image[s][i] = image[i][e];
                image[i][e] = image[e][j];
                image[e][j] = image[j][s];
                image[j][s] = tmp;
            }
        }
        return image;
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
