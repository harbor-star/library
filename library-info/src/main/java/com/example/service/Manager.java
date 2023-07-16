package com.example.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/4/11 13:09
 */
public class Manager {
    public static Object object = new Object();

    public static String readFile(String FilePath) throws IOException {
        BufferedReader br = null;
        String str = null;
        StringBuilder strb = new StringBuilder();
        try {
            br = new BufferedReader(new FileReader(FilePath));
            while ((str = br.readLine()) != null) {
                strb.append(str).append("\n");
            }
        } catch (FileNotFoundException f) {
            System.out.println(FilePath + " does not exist");
            return null;
        } catch (IOException e) {
        } finally {
            if (br != null) {
                br.close();
            }
        }
        String result = strb.toString();
        int length = result.length();
        return result.substring(0, length - 1);
    }

    public static void fileWrite(String data) throws IOException {
        OutputStream outputStream = new FileOutputStream("C:\\Users\\Lenovo\\Desktop\\content\\ai.txt", true);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        outputStreamWriter.write(data);
        outputStreamWriter.close();
        outputStream.close();
    }

    public static void answer() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 8, 2, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        CountDownLatch countDownLatch = new CountDownLatch(2);
        threadPoolExecutor.submit(() -> {
            synchronized (object) {
                try {
                    for (int i = 1; i <= 52; i++) {
                        fileWrite(String.valueOf(i));
                        System.out.print(i);
                        if (i % 2 == 0) {
                            object.wait();
                            object.notify();
                        }
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }
        });

        threadPoolExecutor.submit(() -> {
            synchronized (object) {
                for (char i = 'A'; i <= 'Z'; i++) {
                    try {
                        fileWrite(String.valueOf(i));
                        System.out.print(i);
                        object.notify();
                        object.wait();
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
        threadPoolExecutor.shutdown();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        File file = new File("C:\\Users\\Lenovo\\Desktop\\content\\ai.txt");
        if (file.exists()) {
            boolean boo = file.delete();
            if (!boo) {
                System.out.println("文件删除失败");
            }
        }
        answer();
        System.out.println();
        System.out.println(readFile("C:\\Users\\Lenovo\\Desktop\\content\\ai.txt"));
    }
}
