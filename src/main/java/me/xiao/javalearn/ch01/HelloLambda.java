package me.xiao.javalearn.ch01;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * lambda表达式
 *
 * @author pacman
 * @version 1.0
 * @date: 2017/9/6 12:35
 */

public class HelloLambda {

    public void filterFiles() {
        File[] hiddenFiles = new File("C:\\Users\\BaoQiang\\Desktop\\").listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isHidden();
            }
        });
        System.out.println(Arrays.toString(hiddenFiles));
    }

    public void filterFiles2() {
//        File[] hiddenFiles = new File("C:\\Users\\BaoQiang\\Desktop\\").listFiles(pathname -> pathname.isHidden());
        File[] hiddenFiles = new File("C:\\Users\\BaoQiang\\Desktop\\").listFiles(File::isHidden);
        System.out.println(Arrays.toString(hiddenFiles));
    }

    public static void main(String[] args) {
        new HelloLambda().filterFiles2();
    }

}
