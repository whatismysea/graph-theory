package org.example;

/***
 * spfa算法在对bellman算法的优化，写法跟dijkstra算法有点像
 * 用一个队列来存那些变小了得数，因为只有前面的数变小了
 * 后面的数才会变小，所以存的是前面变小的数，
 * 然后用一个st数组来判断队列中的数
 * 如果队列中已经有了这个点，就不用重复添加了
 * 如果重复添加，能够ac但是好像会影响效率，重复用过的点
 *
 ***/

import java.util.*;

public class SPFA {
    static int N = 100010, n, m, hh, tt, idx, INF = 0x3f3f3f3f;
    static int[] h = new int[N], e = new int[N], ne = new int[N], w = new int[N];
    static int[] q = new int[N], dist = new int[N];
    static boolean[] st = new boolean[N];

    public static void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    public static void spfa() {
        hh = 0;
        tt = -1;
        Arrays.fill(dist, INF); //将dist一开始全部赋值成0x3f3f3f3f
        dist[1] = 0; //然后一开始的点距离是0
        q[++tt] = 1; //然后将第一个点插入到队列中
        st[1] = true;//标记第一个队列中有这个点
        while (hh <= tt) {
            int t = q[hh++]; //然后将队头弹出
            st[t] = false; //这里就将这个点在队列中取消标记
            for (int i = h[t]; i != -1; i = ne[i]) {  //遍历所有的点
                int j = e[i];
                //如果后面的数比前面的数加t-j之间的位权的和要大，就说明应该更新一下最短距离了
                if (dist[j] > dist[t] + w[i]) {
                    dist[j] = dist[t] + w[i];
                    //然后判断一下是不是队列中有这个点
                    if (!st[j]) {
                        //没有就将这个点插入队列
                        q[++tt] = j;
                        //标记对列中有这个点
                        st[j] = true;
                    }
                }
            }
        }
        //spfa只会更新所有能从起点走到的点，所以如果无解，那么起点就走不到终点，那么终点的距离就是0x3f3f3f3f。
        if (dist[n] == INF) System.out.println("impossible");
        else System.out.println(dist[n]);
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
        spfa();
    }
}

// 输入
// 3 3
// 1 2 5
// 2 3 -3
// 1 3 4