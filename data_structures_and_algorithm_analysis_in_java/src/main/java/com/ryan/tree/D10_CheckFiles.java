package com.ryan.tree;

import com.ryan.util.PrintUtil;

import java.io.File;

/**
 * Created by MUFCRyan on 2017/10/30.
 * 练习 4.10 编写一个程序，该程序列出一个目录中所有的文件和他们的大小。模拟联机代码中的程序
 */

public class D10_CheckFiles {
    private static final String FIRST_FILE_PATH = "D:\\Sources\\Mine";
    public static void main(String[] args){
        D10_CheckFiles checkFiles = new D10_CheckFiles();
        checkFiles.checkFile(new File(FIRST_FILE_PATH), 0);
    }

    private void checkFile(File file, int spaceNum){
        if (file != null){
            StringBuilder space = new StringBuilder();
            for (int i = 0; i < spaceNum; i++) {
                space.append("  ");
            }
            PrintUtil.println(space + file.getName());
            if (file.isDirectory()){
                File[] files = file.listFiles();
                if (files != null){
                    spaceNum ++;
                    for (File subFile : files) {
                        checkFile(subFile, spaceNum);
                    }
                }
            }
        }
    }
}
