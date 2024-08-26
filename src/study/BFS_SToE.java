package study;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFS_SToE {
    static int row, col, strR, strC, endR, endC;
    static Queue<Node> queue;
    static String[][] map;
    static int[][] distance;
    static int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static BufferedReader bf;

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(Files.newInputStream(Paths.get("./src/Study/BFS_SToE.txt")));
        bf = new BufferedReader(new InputStreamReader(System.in));

        int totalTc = Integer.parseInt(bf.readLine());
        for (int tc = 1; tc <= totalTc; tc++) {
            init();
            bfs();
            System.out.println("#" + tc + " " + (distance[endR][endC] - 1));
        }
    }

    //    queue = 1 , 2 [ 3, 3]
    private static void bfs() {
        Node node = new Node(strR, strC);
        queue.add(node);

        distance[strR][strC] = 1;

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            int nextX, nextY;
            for (int[] direct : direction) {
                nextX = curNode.x + direct[0];
                nextY = curNode.y + direct[1];

                if (nextX >= 0 && nextY >= 0 && nextX < row && nextY < col && !map[nextX][nextY].equals("X") && distance[nextX][nextY] == 0) {
                    if (map[nextX][nextY].equals("E")) {
                        distance[nextX][nextY] = distance[curNode.x][curNode.y] + 1;
                        queue.clear();
                        return;
                    }

                    queue.add(new Node(nextX, nextY));
                    distance[nextX][nextY] = distance[curNode.x][curNode.y] + 1;
                }
            }
        }
    }

    private static void init() throws IOException {

        String[] rc = bf.readLine().split(" ");
        row = Integer.parseInt(rc[0]);
        col = Integer.parseInt(rc[1]);
        strR = Integer.parseInt(rc[2]);
        strC = Integer.parseInt(rc[3]);

        map = new String[row][col];
        queue = new LinkedList<>();
        distance = new int[row][col];

        for (int i = 0; i < row; i++) {
            String line = bf.readLine();
            for (int j = 0; j < col; j++) {
                map[i][j] = String.valueOf(line.charAt(j));
                if (map[i][j].equals("E")) {
                    endR = i;
                    endC = j;
                }
            }
            System.out.println(Arrays.toString(map[i]));
        }
    }
}
