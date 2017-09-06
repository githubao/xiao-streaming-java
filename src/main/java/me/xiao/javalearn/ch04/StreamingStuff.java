package me.xiao.javalearn.ch04;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * anyMatch
 * noneMatch
 * allMatch
 * <p>
 * findAny
 * findFirst
 * <p>
 * 流式操作
 *
 * @author pacman
 * @version 1.0
 * @date: 2017/9/6 17:10
 */

public class StreamingStuff {
    private void flatMap() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(4, 5);

        List<Integer[]> flatter = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new Integer[]{i, j})).collect(toList());

        flatter.stream().forEach(i -> System.out.println(Arrays.toString(i)));
    }

    private void optional() {
        Optional<Dish> any = menu.stream().filter(Dish::isVegetarian).findAny();
        any.ifPresent(System.out::println);
    }

    private void reduce() {
        List<Integer> numbers = Arrays.asList(2, 3, 4);
//        Integer reduced = numbers.stream().reduce(0, (a, b) -> a + b);
        Integer reduced = numbers.stream().reduce(0, Integer::sum);
        System.out.println(reduced);
    }

    private void reduce2() {
        List<Integer> numbers = Arrays.asList(2, 3, 4);
        Integer sum = numbers.stream().map(i -> 1).reduce(0, Integer::sum);
        System.out.println(sum);

//        numbers.stream().count();
    }

    private void digitStream() {
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        OptionalInt max = intStream.max();
        System.out.println(max.getAsInt());

        Stream<Integer> boxed = menu.stream().mapToInt(Dish::getCalories).boxed();
//        boxed.forEach(System.out::println);


        System.out.println(IntStream.rangeClosed(0, 100).filter(d -> d % 2 == 0).count());
    }

    private void sharedNum() {
        IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}))
                .limit(100)
                .forEach(d -> System.out.println(Arrays.toString(d)));

        IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                                .filter(t -> t[2] % 1 == 0)
                )
                .limit(100)
                .forEach(d -> System.out.println(Arrays.toString(d)));
    }

    List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    public static void main(String[] args) {
//        new StreamingStuff().flatMap();
//        new StreamingStuff().optional();
//        new StreamingStuff().reduce();
//        new StreamingStuff().reduce2();
//        new StreamingStuff().digitStream();
        new StreamingStuff().sharedNum();
    }


}
