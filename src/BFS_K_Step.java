import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS_K_Step {
    static Scanner sc;
    static Queue queue = new LinkedList();
    static int[][] bumpMap;
    static int row, coll, kStep;
    static String[][] map;
    static int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/BFS_Bump.txt"));
        sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int i = 1; i <= tc; i++) {
            readFile();
            bfs();
            System.out.println("#" + i + " " + bumpMap[row - 1][coll - 1]);
        }
    }

    private static void readFile() {
        row = sc.nextInt();
        coll = sc.nextInt();
        kStep = sc.nextInt();
        map = new String[row][coll];
        bumpMap = new int[row][coll];
        String line;

        sc.nextLine();
        for (int i = 0; i < row; i++) {
            line = sc.nextLine();
            for (int j = 0; j < coll; j++) {
                map[i][j] = String.valueOf(line.charAt(j));
//                System.out.print(map[i][j] + "");
                bumpMap[i][j] = -1;  // vi step co the = 0 -> khong xac dinh dc da duyet qua chua
            }
//            System.out.println();
        }

//        System.out.println();
    }

    private static void bfs() {
        queue.add(new Node(0, 0));
        int nextX, nextY, backX, backY, bump;
        Node node;
        bumpMap[0][0] = 0;
        while (!queue.isEmpty()) {
            node = (Node) queue.poll();
            for (int[] arr : direction) {
                bump = bumpMap[node.x][node.y];

                for (int k = 0; k < kStep; k++) {
                    nextX = node.x + arr[0] + (k * arr[0]);
                    nextY = node.y + arr[1] + (k * arr[1]);
                    backX = nextX - arr[0];
                    backY = nextY - arr[1];

                    if (nextX == row - 1 && nextY == coll - 1) {
                        if (k != kStep - 1) {
                            bump++;
                        }
                        bumpMap[nextX][nextY] = bumpMap[nextX][nextY] == -1 ? bump : Math.min(bumpMap[nextX][nextY], bump);
                        break;
                    }
                    if (nextX >= row || nextY >= coll || nextX < 0 || nextY < 0 || "X".equals(map[nextX][nextY])) {
                        if (k > 0) {
                            bump++;
                            if (bumpMap[backX][backY] == -1 || bump < bumpMap[backX][backY]) {
                                queue.add(new Node(backX, backY));
                                bumpMap[backX][backY] = bump;
                            }
                        }
                        break;
                    }
                    if (k == kStep - 1) {
                        if (bumpMap[nextX][nextY] == -1 || bump < bumpMap[nextX][nextY]) {
                            queue.add(new Node(nextX, nextY));
                            bumpMap[nextX][nextY] = bump;
                        }
                    }
                }
            }

        }
    }

}