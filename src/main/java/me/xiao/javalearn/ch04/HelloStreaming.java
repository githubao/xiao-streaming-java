package me.xiao.javalearn.ch04;

import me.xiao.javalearn.ch01.Apple;

import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * filter
 * map
 * flatMap
 * limit
 * skip
 * sorted
 * distinct
 *
 * forEach
 * count
 * collect
 *
 * 流入门
 *
 * @author pacman
 * @version 1.0
 * @date: 2017/9/6 16:48
 */

public class HelloStreaming {

    public void filterApples() {
        List<Apple> apples = Arrays.asList(new Apple("Green", 200), new Apple("Red", 100), new Apple("Yellow", 250));

//        weight > 100 并且 按照重量倒序排序的 所有苹果的颜色
        List<String> colors = apples.stream().filter(a -> a.getWeight() > 100).sorted(comparing(Apple::getWeight).reversed()).map(Apple::getColor).collect(toList());

        System.out.println(colors);
    }

    public static void main(String[] args) {
        new HelloStreaming().filterApples();
    }

}
