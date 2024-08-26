public class BTK1 {

    static int N = 2;
    static int[] valueBTK = {0, 1, 2, 3};
    static boolean[] visited = new boolean[valueBTK.length];
    static int[] result = new int[N];

    public static void main(String[] args) {
        BTK(0);
    }

    static void BTK(int i) {
        for (int j = 0; j < valueBTK.length; j++) {
            if (i == N) { // vào for cuối cùng và dùng tại điều kiên ở đây để quay lại dệ quy trước
//                System.out.println("-------------BTK 1 ----------");
                print(1);
//                System.out.println("-------------BTK 1 ----------");
//                BTK2(0);
                return;
            }
            if (!visited[j]) {
                visited[j] = true;
                result[i] = valueBTK[j];
                BTK(i + 1);
                visited[j] = false;
            }
        }
    }

    static boolean[] visited2 = new boolean[valueBTK.length];
    static int[] result2 = new int[N];
    static void BTK2(int i) {
        for (int j = 0; j < valueBTK.length; j++) {
            if (i == N) { // vào for cuối cùng và dùng tại điều kiên ở đây để quay lại dệ quy trước
                print(2);
                return;
            }
            if (!visited2[j]) {
                visited2[j] = true;
                result2[i] = valueBTK[j];
                BTK2(i + 1);
                visited2[j] = false;
            }
        }
    }

    /*static void BTK(int i) {
        for (int j = 0; j < valueBTK.length; j++) {
            if (!visited[j]) {
                visited[j] = true;
                result[i] = valueBTK[j];
                if (i == N - 1) { // BTK(0) nên dừng khi N - 1
                    print();
                } else {
                    BTK(i + 1);
                }
                visited[j] = false;
            }
        }
    }*/

/*    static void BTK(int i) {            // i = index for result
        for (int j = 1; j < 4; j++) {   // index of value need BTK
            if (!visited[j - 1]) {
                visited[j - 1] = true;
                result[i] = j;          // set value for result
                if (i == 2) {           // dừng khi i = max index
                    print();
                } else {
                    BTK(i + 1);
                }
                visited[j - 1] = false;
            }
        }
    }*/

    private static void print(int btk) {
        for (int i = 0; i < N; i++) {
            System.out.print(btk == 1 ? result[i] : result2[i]);
        }
        System.out.println();
    }
}
