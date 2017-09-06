package me.xiao.javalearn.ch03;

import me.xiao.javalearn.ch01.Apple;

import java.util.Comparator;
import java.util.function.*;

/**
 * java8 内置的函数式接口
 *
 * @author pacman
 * @version 1.0
 * @date: 2017/9/6 15:35
 */

public class FuctionalBuildin {

    /**
     * Predict<T> T->boolean
     * Consumer<T> T->void
     * Function<T,R> T->R
     * Supplier<T> ()->T
     * UnaryOperator<T> T->T
     * BinaryOperator<T> (T,T)->T
     * BiPredicate<L,R> (L,R)->boolean
     * BiConsumer<T,U> (T,U)->void
     * BiFuction<T,U,R> (T,U)->R
     */
    public static void main(String[] args) {
//        lambda
        Comparator<Integer> integerComparator = Comparator.naturalOrder();
        Comparator<Integer> integerComparator2 = Integer::compareTo;
        Comparator<Integer> integerComparator3 = (Integer a, Integer b) -> a.compareTo(b);
        ToIntBiFunction<Integer, Integer> integerComparator5 = (Integer a, Integer b) -> a.compareTo(b);
        BiFunction<Integer, Integer, Integer> integerComparator4 = (Integer a, Integer b) -> a.compareTo(b);

        Comparator<Apple> comparator1 = Comparator.comparing(a -> a.getWeight());
        Comparator<Apple> comparator2 = Comparator.comparing(Apple::getWeight);

//        重量逆序然后颜色顺序比较
        Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor);

//        非绿色而且重量大于150g的苹果
        Predicate<Apple> green = a -> "green".equals(a.getColor());
        Predicate<Apple> notGreenAndHighWeight = green.negate().and((Apple a) -> a.getWeight() > 150);

//        (x+1) * 2
        Function<Integer, Integer> function1 = (Integer x) -> x + 1;
        Function<Integer, Integer> function2 = (Integer x) -> x * 2;
        Function<Integer, Integer> function3 = function1.andThen(function2);

        Function<Integer, Integer> function4 = function2.compose(function1);

    }
}
