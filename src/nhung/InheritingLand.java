package nhung;

import java.io.FileNotFoundException;

public class InheritingLand {

    static int length;
    static int[][] map = {
            {1,2,3,2,1},
            {2,3,2,1,2}};
    static int finalA, finalB;

    public static void main(String[] args) throws FileNotFoundException {
        length = map[0].length;
        devideLand();
        print();
    }

    static void devideLand() {
//        A = tổng map1, B = tổng map2.
//        tempA, tempB là 2 biến tạm để lưu giá trị
        int A = 0, B = 0, tempA, tempB;
        for (int i = 0; i < length - 1; i++) {
            A += map[0][i];
            B += map[1][i];
            tempA = A; // lưu giá trị tạm khi tính đến index là i,
            tempB = B; // vì ở dưới sẽ công cả giá trị tại j nên sau khi quay lại tăng i thì cần có giá trị trước đó để tiếp tục + giá trị

            for (int j = length - 1; j > i; j--) {
                A += map[1][j];
                B += map[0][j];
            }
            // i == 0 là lần đầu tính toán nên phải gán giá trị, điều kiện còn lại là cho những lần tính sau
            if (i == 0 || (A <= B && finalA < A) ||Math.abs(A - B) < Math.abs(finalA - finalB)) {
                finalA = A;
                finalB = B;
            }

            A = tempA; // trả lại giá trị tại i cho A và B để tiếp tục tăng i
            B = tempB;
        }
    }

    private static void print() {
        System.out.println("sum1= " + finalA + " ,sum2=" + finalB);
    }

}
