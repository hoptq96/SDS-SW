import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS_Bump {

    static int row, coll, KStep;
    static Queue<Node> queue = new LinkedList<>();
    static String[][] map;
    static int[][] bumpMap; // visited
    static Scanner sc;
    static int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    static class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        sc = new Scanner(new FileInputStream("./src/BFS_Bump.txt"));
        int tc = sc.nextInt();
        for (int i = 1; i <= tc; i++) {
            init();
            bfs();
            System.out.println("#" + i + " " + bumpMap[row - 1][coll - 1]);
        }
    }

    private static void bfs() {
        Node node = new Node(0, 0);
        queue.add(node);
        bumpMap[0][0] = 0;

        int nextX, nextY, bump, backX, backY;
        while (!queue.isEmpty()) {
            node = queue.poll();

            for (int i = 0; i < direction.length; i++) {
                bump = bumpMap[node.x][node.y];

                for (int k = 0; k < KStep; k++) {
                    nextX = node.x + direction[i][0] + (k * direction[i][0]);
                    nextY = node.y + direction[i][1] + (k * direction[i][1]);
                    backX = nextX - direction[i][0];
                    backY = nextY - direction[i][1];

                    if (nextX == row - 1 && nextY == coll - 1) {
                        if (k != KStep - 1) {
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

                    if (k == KStep - 1) {
                        if (bumpMap[nextX][nextY] == -1 || bump < bumpMap[nextX][nextY]) {
                            queue.add(new Node(nextX, nextY));
                            bumpMap[nextX][nextY] = bump;
                        }
                    }
                }
            }
        }
    }

    private static void init() {
        row = sc.nextInt();
        coll = sc.nextInt();
        KStep = sc.nextInt();
        sc.nextLine();

        map = new String[row][coll];
        bumpMap = new int[row][coll];
        String line;
        for (int i = 0; i < row; i++) {
            line = sc.nextLine();
            for (int j = 0; j < coll; j++) {
                map[i][j] = String.valueOf(line.charAt(j));
                bumpMap[i][j] = -1;
            }
        }
    }
}
