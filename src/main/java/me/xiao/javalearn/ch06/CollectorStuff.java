package me.xiao.javalearn.ch06;

import me.xiao.javalearn.ch04.Dish;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static me.xiao.javalearn.ch04.DishFactory.menu;

/**
 * 收集的操作
 *
 * @author pacman
 * @version 1.0
 * @date: 2017/9/6 19:52
 */

public class CollectorStuff {
    private void collect() {
        Map<Integer, List<Dish>> collect = menu.stream().collect(groupingBy(Dish::getCalories));
        Map<Dish.Type, List<Dish>> collect2 = menu.stream().collect(groupingBy(Dish::getType));
//        menu.stream().collect(maxBy(Comparator.comparingInt(Dish::getCalories))).ifPresent(System.out::println);
        Integer collect3 = menu.stream().collect(summingInt(Dish::getCalories));
        IntSummaryStatistics statistics = menu.stream().collect(summarizingInt(Dish::getCalories));

        String joined = menu.stream().map(Dish::getName).collect(joining(", "));

        System.out.println(joined);

    }

    public static void main(String[] args) {
        new CollectorStuff().collect();
    }

}
