package me.xiao.javalearn.ch04;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static me.xiao.javalearn.ch04.DishFactory.menu;

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

        flatter.forEach(i -> System.out.println(Arrays.toString(i)));
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

    private void digitStream() throws Throwable {
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        OptionalInt max = intStream.max();
        System.out.println(max.orElseThrow(() -> new RuntimeException("err")));

        Stream<Integer> boxed = menu.stream().mapToInt(Dish::getCalories).boxed();
//        boxed.forEach(System.out::println);


        System.out.println(IntStream.rangeClosed(0, 100).filter(d -> d % 2 == 0).count());
    }

    private void createStream() throws IOException {
        Stream<Integer> stream1 = Stream.of(1, 3, 4);
        Stream<Integer> stream2 = Stream.empty();
        Stream<Integer> stream3 = Arrays.stream(new Integer[]{3, 4, 5});
        Stream<String> stream4 = Files.lines(Paths.get("data.txt"), Charset.defaultCharset());
        Stream<Integer> stream5 = Stream.iterate(0, n -> n + 2).limit(10);
        Stream<int[]> stream6 = Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]});
        Stream<Double> stream7 = Stream.generate(Math::random);

//        !!! 内部状态会改变的流，不能用于并发 !!!
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = previous;
                int nextValue = previous + current;
                previous = current;
                current = nextValue;
                return oldPrevious;
            }
        };
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
                                .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                                .filter(t -> t[2] % 1 == 0)
                )
                .limit(100)
                .forEach(d -> System.out.println(Arrays.toString(d)));
    }


    public static void main(String[] args) throws IOException {
//        new StreamingStuff().flatMap();
//        new StreamingStuff().optional();
//        new StreamingStuff().reduce();
//        new StreamingStuff().reduce2();
//        new StreamingStuff().digitStream();
//        new StreamingStuff().sharedNum();
        new StreamingStuff().createStream();
    }


}
