package com.atguigu.Recursion;

/**
 * @author z
 * @createdate 2019-08-02 19:02
 */
public class MiGong {
    public static void main(String[] args) {
        // 先创建一个二维数组，模拟迷宫
        int[][] map = new int[8][7];
        // 使用1 表示墙
        // 上下全部置为1
        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // 左右全部置为1
        for (int i = 1; i < map.length - 1; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板, 1 表示
        map[3][1] = 1;
        map[3][2] = 1;
        // 输出地图
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("------------------------");
        //使用递归回溯给小球找路
        findWay(map, 1, 1);
        //输出新的地图, 小球走过，并标识过的递归
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
    }
    //使用递归回溯来给小球找路
    //说明
    //1. map 表示地图
    //2. i,j 表示从地图的哪个位置开始出发 (1,1)
    //3. 如果小球能到 map[6][5] 位置，则说明通路找到.
    //4. 约定： 当map[i][j] 为 0 表示该点没有走过 当为 1 表示墙  ； 2 表示通路可以走 ； 3 表示该点已经走过，但是走不通
    //5. 在走迷宫时，需要确定一个策略(方法) 下->右->上->左 , 如果该点走不通，再回溯

    /**
     * @param map
     * @param i   从哪个位置开始找
     * @param j
     * @return 如果找到通路，就返回true, 否则返回false
     */
    public static boolean findWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            //如果当前点没走过
            if (map[i][j] == 0) {
                //假定可以走通
                map[i][j] = 2;
                //下{
                if (findWay(map, i+1, j )) {
                    return true;
                }
                //右
                else if (findWay(map, i , j+1)) {
                    return true;
                }
                //上
                else if (findWay(map, i - 1, j)) {
                    return true;
                }
                //左
                else if (findWay(map, i, j - 1)) {
                    return true;
                }
                else {
                    //说明该点是走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
             // 如果map[i][j] != 0 , 可能是 1， 2， 3
            } else {
                return false;
            }

        }
    }
}
