package com.john.m2048;

import com.john.m2048.entity.Cube;

/**
 * @Description 运动结果逻辑
 * @Author John.
 * @Date On 2018/9/17
 * @Updater John.
 **/
public class Logic {
    public static void left(Cube[][] cubes) {
        for (int i = 0; i < cubes.length; i++) {
            int indexY = 0;//数字做加和后存放的位置
            for (int j = 1; j < cubes[i].length; j++) {
                if (cubes[i][j].text > 0) {
                    //cube有值
                    if (cubes[i][indexY].text == 0 || (cubes[i][indexY].text > 0 && cubes[i][indexY].text == cubes[i][j].text)) {
                        //上一列位置有值,并且和当前的列的值相同
                        //把该列的值,移动到startY的位置
                        cubes[i][indexY].text = cubes[i][indexY].text + cubes[i][j].text;
                    } else {
                        if (indexY+1==j)continue;
                        cubes[i][indexY + 1].text = cubes[i][j].text;
                    }
                    cubes[i][j].text = 0;
                    indexY += 1;
                } else {
                    continue;
                }
            }
        }
    }

    public static void right(Cube[] cubes) {

    }

    public static void up(Cube[] cubes) {

    }

    public static void down(Cube[] cubes) {

    }
}
