package org.example;

import java.util.*;

public class BellmanFord {
    static int N = 510, M = 10010, n, m, k, max = 0x3f3f3f3f;
    static int[] dist = new int[N];//从一号点到n号点的距离
    static Node[] edgs = new Node[M];//结构体
    static int[] back = new int[N];//备份数组

    public static void bellman_ford() {
        Arrays.fill(dist, max);//初始化一开始全部都是max
        dist[1] = 0;//然后第一个点到距离是0


        for (int i = 0; i < k; i++) { //因为不超过k条边，所以只需要遍历k次，就可以找出最短距离，反之则没有

            back = Arrays.copyOf(dist, n + 1);//备份:因为是从1开始存到n，所以需要n+1

            for (int j = 0; j < m; j++) { //因为总共m条边，所以遍历m次

                Node edg = edgs[j]; //每一条边的结构体
                int a = edg.a;
                int b = edg.b;
                int c = edg.c;
                dist[b] = Math.min(dist[b], back[a] + c); //用上面的点来更新后面的点
            }
        }

        //这里为什么是max/2呢？
        //因为到不了最后的n点，然后存在负权边能够到达n，将n的值更新了之后，变得比max小，防止出现这种情况
        if (dist[n] > max / 2) System.out.println("impossible");
        else System.out.println(dist[n]);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();
        k = scan.nextInt();
        for (int i = 0; i < m; i++) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            int c = scan.nextInt();
            edgs[i] = new Node(a, b, c);
        }
        bellman_ford();
    }
}

class Node {
    int a, b, c;

    public Node(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}

// 输入
// 3 3 1
// 1 2 1
// 2 3 1
// 1 3 3