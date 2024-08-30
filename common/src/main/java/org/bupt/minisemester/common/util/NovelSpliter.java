package org.bupt.minisemester.common.util;

import java.io.*;
import java.util.regex.*;
import java.lang.String;

public class NovelSpliter {
    private String content;

    // 构造函数，接收字符串作为参数
    public NovelSpliter(String content) {
        this.content = content;
    }

    public void split() {
        try {
            // 定义正则表达式模式，匹配“第几回”或“第几章”，其中“几”可以是长度不超过4个汉字的任意字符
            Pattern pattern = Pattern.compile("(第.{1,4}?[回章])");
            Matcher matcher = pattern.matcher(content);

            int start = 0;
            int index = 1;
            String previousChapterTitle = null;

            // 处理前言部分
            if (matcher.find()) {
                int end = matcher.start();
                if (end > 0) {
                    String preface = content.substring(start, end).trim();
                    if (!preface.isEmpty()) {
                        writeToFile("前言.txt", "前言\n" + preface);
                        index++;
                    }
                }
                previousChapterTitle = matcher.group(1);
                start = matcher.end();
            }

            while (matcher.find()) {
                String currentChapterTitle = matcher.group(1);
                int end = matcher.start();

                if (previousChapterTitle != null) {
                    String segment = content.substring(start, end).trim();
                    if (!segment.isEmpty()) {
                        writeToFile(previousChapterTitle + ".txt", previousChapterTitle + "\n" + segment);
                        index++;
                    }
                }

                previousChapterTitle = currentChapterTitle;
                start = matcher.end();
            }

            // 处理最后一个章节
            if (previousChapterTitle != null && start < content.length()) {
                String segment = content.substring(start).trim();
                if (!segment.isEmpty()) {
                    writeToFile(previousChapterTitle + ".txt", previousChapterTitle + "\n" + segment);
                }
            }

            System.out.println("文件已成功分割并写入多个文件。");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void writeToFile(String filePath, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(content);
        writer.close();
    }
}

