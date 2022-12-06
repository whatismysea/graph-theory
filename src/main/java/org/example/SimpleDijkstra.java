package org.example;

import java.util.*;

class SimpleDijkstra {
    static int N = 510;
    static int[][] g = new int[N][N]; //稠密图使用邻接矩阵存储
    static int[] dist = new int[N];
    static boolean[] st = new boolean[N]; //相当于s集合，确定了和1号点的最短距离的点加入到s集合中
    static int n, m;
    static int max = 5000000;

    static int dijkstra() {
        //步骤一：初始化
        Arrays.fill(dist, max);
        dist[1] = 0;
        //步骤二：循环n次，将n个点加入s集合中
        for (int i = 0; i < n; i++) {
            //找到当前距离1号点最近的点
            int t = -1;
            for (int j = 1; j <= n; j++) {
                if (st[j]) continue;
                if (t == -1 || dist[j] < dist[t]) {
                    t = j;
                }
            }
            System.out.println("dijkstra find point:" + t);
            st[t] = true;

            //更新其它节点到1号点的距离
            for (int j = 1; j <= n; j++) {
                if (dist[j] > dist[t] + g[t][j]) dist[j] = dist[t] + g[t][j];
            }
        }

        if (dist[n] == max) return -1;
        else return dist[n];
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();

        //初始化g为正无穷，解决重边的问题
        for (int i = 1; i <= n; i++) Arrays.fill(g[i], max);

        while (m-- > 0) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            int c = scan.nextInt();

            g[a][b] = Math.min(g[a][b], c);
        }

        System.out.println(dijkstra());
        //dijkstra();
        //for(int i = 1;i <= n; i++) {
        //    System.out.println(dist[i]);
        //}
    }
}
// 输入
// 3 3
// 1 2 2
// 2 3 1
// 1 3 4