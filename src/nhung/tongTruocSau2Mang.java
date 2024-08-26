package nhung;

public class tongTruocSau2Mang {

    static int sum1 = 0, sum2 = 0;
    static int[][] arr = {
            {1, 2, 2, 3, 4, 5, 6},
            {2, 4, 4, 3, 6, 7, 1}};

    public static void main(String[] args) {
        solution1(arr[0].length / 2);
    }

    private static void solution1(int index) {
        for (int i = 0; i < arr[0].length; i++) {
            if (i <= index) {
                sum1 += arr[0][i]; // Phần trước của mảng 1
                sum2 += arr[1][i]; // Phần trước của mảng 2
            } else {
                sum1 += arr[1][i]; // Phần sau của mảng 1
                sum2 += arr[0][i]; // Phần sau của mảng 2
            }
        }
        System.out.println("sum1= " + sum1 + " sum2= " + sum2);
    }

}
