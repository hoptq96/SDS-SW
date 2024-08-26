package study;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SubwayTranfer {

    static int N, M, S, E;
    static int[] totalSubEachLine;
    static ArrayList<Integer>[] subsInLine;
    static ArrayList<Integer>[] linesHaveSub;
    static BufferedReader bf;
    static Queue<Node> queue = new LinkedList();
    static boolean[] visited;
    static int resultLineTransfer = -1;

    static class Node {
        int sub, cntLine;

        public Node(int sub, int cntLine) {
            this.sub = sub;
            this.cntLine = cntLine;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/study/SubwayTranfer.txt"));
        bf = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(bf.readLine());

        for (int i = 1; i <= tc; i++) {
            init();
            process();
            System.out.println("#" + i + " " + resultLineTransfer);
        }
    }

    private static void process() {

//        put all sub of start to queue
        for (int line : linesHaveSub[S]) {
            for (int sub : subsInLine[line]) {
                Node node = new Node(sub, 0);
                queue.add(node);

                visited[sub] = true;
            }
        }

//        bfs
        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.sub == E) {
                resultLineTransfer = node.cntLine;
            }

            for (int line : linesHaveSub[node.sub]) {
                for (int sub : subsInLine[line]) {
                    if (!visited[sub]) {
                        visited[sub] = true;
                        queue.add(new Node(sub, node.cntLine + 1));
                    }
                }
            }
        }
    }

    private static void init() throws IOException {
        String[] line = bf.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        S = Integer.parseInt(line[2]);
        E = Integer.parseInt(line[3]);

        totalSubEachLine = new int[M];
        line = bf.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            totalSubEachLine[i] = Integer.parseInt(line[i]);
        }

        subsInLine = new ArrayList[M];
        linesHaveSub = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            linesHaveSub[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            subsInLine[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            line = bf.readLine().split(" ");
            for (int j = 0; j < totalSubEachLine[i]; j++) {
                int sub = Integer.parseInt(line[j]);

                subsInLine[i].add(sub);
                linesHaveSub[sub].add(i);
            }
        }

    }

}
