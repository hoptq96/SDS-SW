public class BTK2 {
    static int N = 3; //độ dài của 1 chuỗi đáp án
    static int[] resultArr = new int[N];
    static boolean[] visited = new boolean[N];

    static int count = 0;

    static void btk(int index) { // index = index of result
        if (index == N) { // đk dừng khi index = max index
            // count++;
            print();
            return;
        }
        for (int j = 0; j < 3; j++) {  // value of result
            if (!visited[j]) {
                visited[j] = true;
                resultArr[index] = j;   // set value for result
                btk(index + 1);
                visited[j] = false;
            }

        }
    }

//    static void btk(int i) { // i = index of result
//        for (int j = 1; j < 5; j++) {  // value of result
//            if (!visited[j - 1]) {
//                visited[j - 1] = true;
//                resultArr[i] = j;   // set value for result
//                if (i == 2) { // dừng khi i = max index
////                    count++;
//                    print();
//                } else {
//                    btk(i + 1);
//                }
//                visited[j - 1] = false;
//            }
//
//        }
//    }

    static void print() {
        for (int i = 0; i < N; i++) {
            System.out.print(resultArr[i]);
        }
        System.out.println("   ");
    }

    public static void main(String[] args) {
        btk(0);
        System.out.println("count: " + count);
    }
}
