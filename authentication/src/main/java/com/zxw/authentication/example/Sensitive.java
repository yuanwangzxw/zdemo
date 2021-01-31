package com.zxw.authentication.example;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class Sensitive {

    private TreeNode root = new TreeNode();

    //构建前缀树
    public void addSensitiveWord(String word) {
        TreeNode currentNode = root;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            TreeNode node = currentNode.getSubNode(c);
            if (node == null) {
                //如果没有获取到对应节点，就新建
//                tempNode.addSubNode(c, new TreeNode());//这么直接写是错的，没有引用,导致上层节点无法获取
                node = new TreeNode();
                currentNode.addSubNode(c, node);
            }
            currentNode = node;
            if (i == word.length() - 1) {
                //表明这是某个敏感词的最后一个节点
                currentNode.isEnd = true;
            }
        }
    }

    public String filter(String text) {
        if (StringUtils.isBlank(text)) {
            return null;
        }
        TreeNode currentNode = root;
        //position，begin游标，但begin只指向合法字符，当position匹配失败，可以退到begin继续遍历
        int begin = 0, position = 0;
        StringBuilder sb = new StringBuilder();
        while (position < text.length()) {
            char c = text.charAt(position);
            if (isSymbol(c)) {
                if (currentNode == root) {
                    sb.append(c);
                    begin++;
                }
                position++;
                continue;
            }
            currentNode = currentNode.getSubNode(c);
            if (currentNode == null) {
                //没有匹配到，2种情况：
                //1.前一个字符匹配，那说明begin到这里不匹配，position退到begin并++，重新匹配后面的
                //2.前一个字符不匹配，说明是合法字符，begin和position同时++就行了
                sb.append(text.charAt(begin));
                position = ++begin;
                currentNode = root;
            } else if (currentNode.isEnd) {
                //完全匹配到最后一个符，begin到position的位置，换成*号
                sb.append("*");
                begin = ++position;
                currentNode = root;
            } else {
                //匹配到，2种情况，前一个字符也匹配或前一个字符不匹配，begin保持不变
                position++;
            }
        }
        //如果遍历完了，begin小于position，说明最后一段不是敏感词，则加入统计
        sb.append(text.substring(begin));
        return sb.toString();
    }

    //判断特殊符号
    private boolean isSymbol(Character c) {
        // 0x2E80-0x9FFF是东亚文字范围
        return !CharUtils.isAsciiAlphanumeric(c) && (c < 0x2E80 || c > 0x9FFF);
    }


    class TreeNode {
        //一个字符存对应一个hashmap
        private Map<Character, TreeNode> subNodes = new HashMap<>();

        private boolean isEnd = false;

        public TreeNode getSubNode(Character character) {
            return subNodes.get(character);
        }

        public void addSubNode(Character c, TreeNode treeNode) {
            subNodes.put(c, treeNode);
        }
    }

    public static void main(String[] args) {
        Sensitive sensitive = new Sensitive();
        sensitive.addSensitiveWord("王八犊子");
        sensitive.addSensitiveWord("王八壳子");
        String filter = sensitive.filter("嗨呀王八子啊");
        System.out.println("filter = " + filter);
    }
}
