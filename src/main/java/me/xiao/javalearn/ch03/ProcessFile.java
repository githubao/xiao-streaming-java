package me.xiao.javalearn.ch03;

import java.io.*;

/**
 * TODO description
 *
 * @author pacman
 * @version 1.0
 * @date: 2017/9/6 15:19
 */

public class ProcessFile {

    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\BaoQiang\\Desktop\\1.txt")))) {
            return p.process(br);
        }
    }

    public void process() throws IOException {
        String line = processFile((BufferedReader br) -> br.readLine());
        String lines = processFile((BufferedReader br) -> br.readLine() + br.readLine());

        System.out.println(String.format("%s\n%s", line, lines));
    }

    public static void main(String[] args) throws IOException {
        new ProcessFile().process();
    }

}
