package org.bupt.minisemester.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;
import java.lang.String;

public class NovelSplitter {
    private String content;

    // 构造函数，接收字符串作为参数
    public NovelSplitter(String content) {
        this.content = content;
    }

    public static class Chapter {
        private String title;
        private String content;

        public Chapter(String title, String content) {
            this.title = title;
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }
    }

    public List<Chapter> split() {
        List<Chapter> chapters = new ArrayList<>();

        try {
            // 定义正则表达式模式，匹配“第几回”或“第几章”，其中“几”可以是长度不超过4个汉字的任意字符
            Pattern pattern = Pattern.compile("(第.{1,4}?[回章])");
            Matcher matcher = pattern.matcher(content);

            int start = 0;
            String previousChapterTitle = null;

            // 处理前言部分
            if (matcher.find()) {
                int end = matcher.start();
                if (end > 0) {
                    String preface = content.substring(start, end).trim();
                    if (!preface.isEmpty()) {
                        chapters.add(new Chapter("前言", preface));
                    }
                }
                previousChapterTitle = matcher.group(1);
                start = matcher.end();
            }

            //处理每个章节
            while (matcher.find()) {
                String currentChapterTitle = matcher.group(1);
                int end = matcher.start();

                if (previousChapterTitle != null) {
                    String segment = content.substring(start, end).trim();
                    if (!segment.isEmpty()) {
                        chapters.add(new Chapter(previousChapterTitle, segment));
                    }
                }

                previousChapterTitle = currentChapterTitle;
                start = matcher.end();
            }

            // 处理最后一个章节
            if (previousChapterTitle != null && start < content.length()) {
                String segment = content.substring(start).trim();
                if (!segment.isEmpty()) {
                    chapters.add(new Chapter(previousChapterTitle, segment));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return chapters;
    }
}

