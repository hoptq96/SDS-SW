import java.util.Arrays;

public class BTKABC {

    public static void main(String[] args) {
        btk(0);
    }

//    Độ dài của điều kiện dừng và kết quả phải bằng nhau
    static int N = 3;
    static String[] btkResult = new String[N];

//    Độ dài của isVisited và name phải bằng nhau
    static boolean[] isVisited = new boolean[4];
    static String[] name = {"A", "B", "C", "D"};

    private static void btk(int index) {
        if (index == N) { // điều kiện dừng cần return
            System.out.println(Arrays.toString(btkResult));
            return;
        }
        for (int j = 0; j < name.length; j++) { // Giá trị (index của giá trị) cho mảng kết quả
            if (!isVisited[j]) {
                isVisited[j] = true;
                btkResult[index] = name[j];

                btk(index + 1);
                isVisited[j] = false;
            }
        }
    }
}
