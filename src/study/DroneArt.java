package study;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DroneArt {
//    Cho N, M , k (N = row, M = col, K = totalDrone)
//    Tìm khảng cách ngắn nhất để di chuyển drone từ mapA sang mapB
//    Drone tại mapA khi sáng đèn mà cùng vị trí đó tại B không sáng thì là drone cần di chuyển (đèn sáng là 1)
//    Những drone dư (không sáng đèn tại A) thì có thể đến bất cứ vị trí nào mà không mất bước đi
//    Chú ý tổng drone >= map thì không cần di chuyển

    static int row, coll, totalDrone, min, droneNeedMove;
    static ArrayList<Node> ALightDrone, BLightDrone;
    static Scanner sc;

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + "-" + y;
        }
    }

    public static void main(String[] args) throws IOException {
        sc = new Scanner(Files.newInputStream(Paths.get("./src/study/DroneArt.txt")));
        int tc = sc.nextInt();
        for (int i = 1; i <= tc; i++) {
            init();
            process();
            System.out.println("#" + i + " " + min);
        }
    }

    private static void process() {

        btkA(0);
    }

    static boolean[] visited1, visited2;
    static Node[] result1, result2;

    private static void btkA(int i) {
        if (i == droneNeedMove) {
//            System.out.println();
//            System.out.println(Arrays.toString(result1));
//            System.out.println();
            btkB(0);
            return;
        }

        for (int j = 0; j < ALightDrone.size(); j++) {
            if (!visited1[j]) {
                visited1[j] = true;
                result1[i] = ALightDrone.get(j);

                btkA(i + 1);
                visited1[j] = false;
            }
        }
    }

    private static void btkB(int i) {
        if (i == droneNeedMove) {
//            System.out.println(Arrays.toString(result2));

            min = Math.min(min, distance());
            return;
        }

        for (int j = 0; j < BLightDrone.size(); j++) {
            if (!visited2[j]) {
                visited2[j] = true;
                result2[i] = BLightDrone.get(j);

                btkB(i + 1);
                visited2[j] = false;
            }
        }
    }

    private static int distance() {
        int temp = 0;
        for (int i = 0; i < droneNeedMove; i++) {
            temp += Math.abs(result1[i].x - result2[i].x) + Math.abs(result1[i].y - result2[i].y);
        }
        return temp == 0 ? Integer.MAX_VALUE : temp;
    }

    private static void init() {
        row = sc.nextInt();
        coll = sc.nextInt();
        totalDrone = sc.nextInt();

        min = Integer.MAX_VALUE;
        ALightDrone = new ArrayList<>();
        BLightDrone = new ArrayList<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < coll; j++) {
                if (sc.nextInt() == 1) {
                    ALightDrone.add(new Node(i, j));
                }
            }
        }

        int dupLocation = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < coll; j++) {
                if (sc.nextInt() == 1) {
                    boolean isDuplicate = false;
                    for (int k = 0; k < ALightDrone.size(); k++) {
                        Node node = ALightDrone.get(k);
                        if (node.x == i && node.y == j) {
                            isDuplicate = true;
                            ALightDrone.remove(k);
                            dupLocation++;
                            break;
                        }
                    }
                    if (!isDuplicate) {
                        BLightDrone.add(new Node(i, j));
                    }
                }
            }
        }

        visited1 = new boolean[ALightDrone.size()];
        visited2 = new boolean[BLightDrone.size()];

        droneNeedMove = BLightDrone.size() - (totalDrone - ALightDrone.size()); // tru di drone không sáng tai A (drone chờ có thể sáng tại bất cứ đâu)
        droneNeedMove += dupLocation;

        if (totalDrone >= (row * coll)) {
            min = 0;
            return;
        }
        if (totalDrone < BLightDrone.size()) {
            min = 0;
            return;
        }
        if (ALightDrone.isEmpty() && BLightDrone.isEmpty()) {
            min = 0;
            return;
        }

        result1 = new Node[droneNeedMove];
        result2 = new Node[droneNeedMove];
    }
}
