package ru.otus;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class HelloOtus {
    public static void main(String[] args) {
        List<String> words = ImmutableList.of("Hello", " ", "Otus");
        for (String word : words){
            System.out.print(word);
        }
    }
}
