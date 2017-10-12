package com.ryan.introduction;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by MUFCRyan on 2017/10/12.
 * 根据 include 读入文件引用链并输出文件名
 * 因为暂时不好找到 C 文件的原因，故此处只是找一次 Java 文件而已
 */

public class IncludeFile {
    private static final String INCLUDE_SYMBOL = "import ";
    private static final String FILE_SUFFIX = ".java";
    private static final String FIRST_FILE_PATH = "com/ryan/introduction/PrintDouble" + FILE_SUFFIX;

    public static void main(String[] args){
        //File file = new File("MyFile.txt");
        //System.out.println(file.getPath());
        IncludeFile includeFile = new IncludeFile();
        includeFile.readFilesByInclude(FIRST_FILE_PATH);
    }

    String readFilesByInclude(String fileName){
        BufferedReader mReader = null;
        try {
            mReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = mReader.readLine()) != null){
                if (line.startsWith(INCLUDE_SYMBOL)){
                    line += FILE_SUFFIX;
                    String newFileName = line.replace(INCLUDE_SYMBOL, "");
                    if (!newFileName.equals(fileName)){
                        return readFilesByInclude(newFileName);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (mReader != null)
                try {
                    mReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        System.out.println(fileName);
        return fileName;
    }
}
