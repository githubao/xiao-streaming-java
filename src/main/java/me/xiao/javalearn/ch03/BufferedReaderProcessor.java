package me.xiao.javalearn.ch03;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * 读取文件的函数式接口
 *
 * @author pacman
 * @version 1.0
 * @date: 2017/9/6 15:17
 */

@FunctionalInterface
public interface BufferedReaderProcessor {
    String process(BufferedReader br) throws IOException;
}
