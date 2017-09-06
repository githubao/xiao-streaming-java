package me.xiao.javalearn.ch01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * predicate sample
 *
 * @author pacman
 * @version 1.0
 * @date: 2017/9/6 13:29
 */

public class HelloPredicate {
    public void filterApple() {
        List<Apple> apples = Arrays.asList(new Apple("green", 200), new Apple("red", 100));
        List<Apple> result = filterGreenApples(apples);
        List<Apple> result2 = filterGreenApples2(apples, HelloPredicate::isGreenApple);
        List<Apple> result3 = filterGreenApples2(apples, (Apple a) -> "green".equals(a.getColor()));
        List<Apple> result4 = apples.stream().filter(a -> "green".equals(a.getColor())).collect(Collectors.toList());
        List<Apple> result5 = apples.parallelStream().filter(a -> "green".equals(a.getColor())).collect(Collectors.toList());

        System.out.println(String.format("%s\n%s\n%s\n%s\n%s", result, result2, result3,result4,result5));
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static List<Apple> filterGreenApples(List<Apple> apples) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterGreenApples2(List<Apple> apples, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        new HelloPredicate().filterApple();
    }
}

