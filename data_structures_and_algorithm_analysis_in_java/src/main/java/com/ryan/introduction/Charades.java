package com.ryan.introduction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MUFCRyan on 2017/10/11.
 * 求解字谜
 */

public class Charades {
    // 思路
    // 1. 每个单词均可用一个四元组表示，四元组包含：所在行、列，方向、长度
    // 2. 总共四种方向：水平、垂直、左上<-->右下、左下<-->右上
    // 3. 每个方向都遍历寻找出所有的最长的字符串
    // 4. 若最长字符串包含或等于要查询的单词就找出了该单词

    // 字谜方阵
    public String[][] puzzle = {
        {"t","h","i","s"},
        {"w","a","t","s"},
        {"o","a","h","g"},
        {"f","g","g","t"}
    };

    // 单词词典
    String[] words = {"this", "two", "fat", "that"};

    class Word{
        String content = "";
        int rowStart;
        int rowEnd;
        int columnStart;
        int columnEnd;
        int length = content.length();
        int listLength;
    }

    List<Word> longWordList = new ArrayList<>();
    List<Word> queryWordList = new ArrayList<>();

    public static void main(String[] args){
        Charades charades = new Charades();
        List<Word> words = charades.findWords(charades.puzzle, charades.words);
        for (Word word : words) {
            System.out.println(word.content);
        }
    }

    private List<Word> findWords(String[][] puzzle, String[] words){
        if (puzzle == null || words == null)
            return queryWordList;
        if (puzzle.length == 0 || words.length == 0)
            return queryWordList;

        // 寻找最长字符串
        // 1. 水平方向
        for (int row = 0; row < puzzle.length; row++) {
            int columnLength = puzzle[row].length;
            Word word = new Word();
            word.rowStart = row;
            word.rowEnd = row;
            word.columnStart = 0;
            word.columnEnd = columnLength;
            for (int column = 0; column < puzzle[row].length; column++) {
                word.content += puzzle[row][column];
            }
            longWordList.add(word);
        }

        for (int row = puzzle.length - 1; row >= 0; row--) {
            int columnLength = puzzle[row].length;
            Word word = new Word();
            word.rowStart = row;
            word.rowEnd = row;
            word.columnStart = columnLength;
            word.columnEnd = 0;
            for (int column = puzzle[row].length - 1; column >= 0; column--) {
                word.content += puzzle[row][column];
            }
            longWordList.add(word);
        }

        List<Word> tempWordList = new ArrayList<>();
        // 2. 垂直方向
        for (int row = 0; row < puzzle.length; row++) {
            for (int column = 0; column < puzzle[row].length; column++) {
                if (tempWordList.size() <= column){
                    Word word = new Word();
                    word.rowStart = 0;
                    word.rowEnd = row;
                    word.columnStart = column;
                    word.columnEnd = column;
                    word.content += puzzle[row][column];
                    tempWordList.add(word);
                } else {
                    Word word = tempWordList.get(column);
                    word.rowEnd = row;
                    word.content += puzzle[row][column];
                }
            }
        }
        longWordList.addAll(tempWordList);
        tempWordList.clear();

        for (int row = puzzle.length - 1; row >= 0 ; row--) {
            for (int column = 0; column < puzzle[row].length; column++) {
                if (tempWordList.size() <= column){
                    Word word = new Word();
                    word.rowStart = row;
                    word.rowEnd = row;
                    word.columnStart = column;
                    word.columnEnd = column;
                    word.content += puzzle[row][column];
                    tempWordList.add(word);
                } else {
                    Word word = tempWordList.get(column);
                    word.rowEnd = row;
                    word.content += puzzle[row][column];
                }
            }
        }
        longWordList.addAll(tempWordList);
        tempWordList.clear();

        // 确定最短单词的字符数
        int minWordLength = words[0].length();
        for (String word : words) {
            if (word.length() < minWordLength)
                minWordLength = word.length();
        }

        // 3. 左上<-->右下方向
            // 3.1 左上 --> 右下
                // 3.1.1 对角线，控制条件：row = column
                for (int row = 0; row < puzzle.length; row++) {
                    for (int column = 0; column < puzzle[row].length; column++) {
                        if (row == column){
                            if (tempWordList.size() == 0){
                                Word word = new Word();
                                word.rowStart = 0;
                                word.rowEnd = row;
                                word.columnStart = 0;
                                word.columnEnd = column;
                                word.content += puzzle[row][column];
                                tempWordList.add(word);
                            } else {
                                Word word = tempWordList.get(0);
                                word.rowEnd = row;
                                word.columnEnd = column;
                                word.content += puzzle[row][column];
                            }
                        }
                    }
                }
                longWordList.addAll(tempWordList);
                tempWordList.clear();

                // 3.1.1 对角线上方，控制条件 column' = row + length - subListLength
                for (int row = 0; row < puzzle.length; row++) {
                    for (int column = 0; column < puzzle[row].length; column++) {
                        if (column > 0){ // 从对角线上方的元素数组中选取
                            // 确定当前循环组成的字符串长度不小于最短单词的长度
                            int subListLength = puzzle[row].length - column;
                            if (subListLength >= minWordLength){
                                // 确定当前循环的元素在目标序列中
                                if (column == row + puzzle[row].length -  subListLength){
                                    boolean hasSubWord = false;
                                    if (tempWordList.size() == 0)
                                        hasSubWord = false;
                                    else {
                                        for (Word word : tempWordList) {
                                            if (word.listLength == subListLength){
                                                hasSubWord = true;
                                                break;
                                            }
                                        }
                                    }
                                    if (!hasSubWord){
                                        Word word = new Word();
                                        word.rowStart = 0;
                                        word.rowEnd = row;
                                        word.columnStart = 0;
                                        word.columnEnd = column;
                                        word.content += puzzle[row][column];
                                        word.listLength = subListLength;
                                        tempWordList.add(word);
                                    } else {
                                        Word word = tempWordList.get(column);
                                        if (subListLength == word.listLength){ // 同样的子序列元素才添加
                                            word.rowEnd = row;
                                            word.columnEnd = column;
                                            word.content += puzzle[row][column];
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                longWordList.addAll(tempWordList);
                tempWordList.clear();

                // 3.1.2 对角线下方，控制条件 row' = column + y, column' = column，y = length - row
                for (int row = 0; row < puzzle.length; row++) {
                    for (int column = 0; column < puzzle[row].length; column++) {
                        if (row > 0){ // 从对角线下方的元素数组中选取
                            // 确定当前循环组成的字符串长度不小于最短单词的长度
                            int subListLength = puzzle.length - row;
                            if (subListLength >= minWordLength){
                                // 确定当前循环的元素在目标序列中
                                if (row == column + puzzle.length -  subListLength){
                                    boolean hasSubWord = false;
                                    if (tempWordList.size() == 0)
                                        hasSubWord = false;
                                    else {
                                        for (Word word : tempWordList) {
                                            if (word.listLength == subListLength){
                                                hasSubWord = true;
                                                break;
                                            }
                                        }
                                    }
                                    if (!hasSubWord){
                                        Word word = new Word();
                                        word.rowStart = 0;
                                        word.rowEnd = row;
                                        word.columnStart = 0;
                                        word.columnEnd = column;
                                        word.content += puzzle[row][column];
                                        word.listLength = subListLength;
                                        tempWordList.add(word);
                                    } else {
                                        Word word = tempWordList.get(column);
                                        if (subListLength == word.listLength){ // 同样的子序列元素才添加
                                            word.rowEnd = row;
                                            word.columnEnd = column;
                                            word.content += puzzle[row][column];
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                longWordList.addAll(tempWordList);
                tempWordList.clear();

            // 3.2 右下 --> 左上

        // 4. 左下<-->右上方向


        // 5. 遍历长字符串列表选择匹配的单词赋值并返回
        for (String stringWord : words) {
            for (Word longWord : longWordList) {
                if (longWord.content.contains(stringWord)){
                    longWord.content = stringWord;
                    queryWordList.add(longWord);
                }
            }
        }
        return queryWordList;
    }
}
