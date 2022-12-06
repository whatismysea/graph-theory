package org.example;

import java.util.*;

public class OptimizeDijkstra {
    static int N = 150010, n, m, idx, max = 5000000;
    static int[] h = new int[N], e = new int[N], ne = new int[N], w = new int[N]; // 稀疏图用邻接表存
    static int[] dist = new int[N];
    static boolean[] st = new boolean[N];

    public static void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    public static int dijkstra() {
        //优先队列，保证每次取出都是最小值
        //维护当前未在st中标记过且离源点最近的点   小跟堆
        PriorityQueue<PIIs> queue = new PriorityQueue<PIIs>();
        Arrays.fill(dist, max);
        dist[1] = 0;
        queue.add(new PIIs(0, 1));
        while (!queue.isEmpty()) {
            //1、找到当前未在s中出现过且离源点最近的点
            PIIs p = queue.poll();
            int distance = p.getFirst();
            int t = p.getSecond();
            if (st[t]) continue;
            //2、将该点进行标记
            st[t] = true;
            //3、用t更新其他点的距离
            for (int i = h[t]; i != -1; i = ne[i]) {
                int j = e[i];// i只是个下标，e中在存的是i这个下标对应的点和值。
                if (dist[j] > distance + w[i]) {
                    dist[j] = distance + w[i];
                    queue.add(new PIIs(dist[j], j));
                }
            }
        }
        if (dist[n] == max) return -1;
        return dist[n];
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();
        Arrays.fill(h, -1);
        while (m-- > 0) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            int c = scan.nextInt();
            add(a, b, c);
        }
        int res = dijkstra();
        System.out.println(res);
    }
}

class PIIs implements Comparable<PIIs> {
    private int first;//距离值
    private int second;//点编号

    public int getFirst() {
        return this.first;
    }

    public int getSecond() {
        return this.second;
    }

    public PIIs(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int compareTo(PIIs o) {
        // TODO 自动生成的方法存根
        return Integer.compare(first, o.first);
    }
}


// 输入
// 3 3
// 1 2 2
// 2 3 1
// 1 3 4