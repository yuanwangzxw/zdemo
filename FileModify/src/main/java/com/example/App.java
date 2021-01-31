package com.example;

import jdk.nashorn.internal.ir.IfNode;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 */
public class App {

    public static void singleFile(File file) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            String line ;
            List<String> fileContent = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                fileContent.add(line);
            }
            fileContent = update(fileContent);
//            br.close();
//            file.delete();
//            createNewFile(file, fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createNewFile(File file, List<String> fileContent) throws IOException {
        File newFile = new File(file.getAbsolutePath()+"_tmp");
        newFile.createNewFile();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile, true)));
        if (fileContent.size()>0) {
            for (String s : fileContent) {
                bw.write(s);
                bw.newLine();
            }
        }
        bw.close();
        newFile.renameTo(file);
    }

    private static List<String> delete(List<String> fileContent) {
        return fileContent.stream().filter(x -> !x.trim().startsWith("@Transactional")).collect(Collectors.toList());
    }

    private static List<String> removeCondition(List<String> fileContent) {
        return fileContent.stream().filter(x -> !x.trim().startsWith("@ConditionalOnMissingBean")).collect(Collectors.toList());
    }

    private static List<String> update(List<String> fileContent) {
        for (int i = 0; i < fileContent.size(); i++) {
            String line = fileContent.get(i);
            if (matchMethod(line)) {
                String s = line.trim().split("\\s")[1];
                if(s.equals("void")){

                }
            }
        }
        return fileContent;
    }

    private static List<String> addConditional(List<String> fileContent) {
        String condition = "@ConditionalOnMissingBean(name=\"%s\")\n";
        for (int i = 0; i < fileContent.size(); i++) {
            String line = fileContent.get(i);
            if (matchClass(line)) {
                String className = line.split("\\s")[2];
                className=className.substring(0, className.length() - 2);
                className=className.replace("Impl", "ExpandImpl");
                condition= String.format(condition, className);
                line = condition + line;
                fileContent.set(i, line);
            }
        }
        return fileContent;
    }

    //修改serviceImpl泛型
    private static List<String> updateGeneric(List<String> fileContent) {
        boolean first = false;
        for (int i = 0; i < fileContent.size(); i++) {
            String line = fileContent.get(i);
            if(line.trim().startsWith("import")&&!first){
                line = "import com.baomidou.mybatisplus.core.mapper.BaseMapper;\n" + line;
                fileContent.set(i, line);
                first = true;
            }
            if (matchClass(line)) {
                String className = line.split("\\s")[2];
                String replace = line.replace(className, className + "<M extends BaseMapper<T>,T>");
                fileContent.set(i, replace);
            }
        }
        return fileContent;
    }

    private static boolean matchMethod(String currentLine) {
        currentLine = currentLine.trim();
        return !currentLine.startsWith("public class") &&currentLine.startsWith("public") && currentLine.endsWith("{");
    }

    private static boolean matchClass(String currentLine) {
        currentLine = currentLine.trim();
        return currentLine.startsWith("public class")||currentLine.startsWith("class");
    }


    public static void main(String[] args) {
//        File root = new File("C:\\wordir\\crm-core\\crm-mdm\\src\\main\\java\\com\\biz\\crm");
//        List<String> mdmDir = new ArrayList<>();
//        mdmDir.add("cusorg");
//        mdmDir.add("customer");
//        mdmDir.add("customermaterial");
//        mdmDir.add("humanarea");
//        mdmDir.add("material");
//        mdmDir.add("org");
//        mdmDir.add("permission");
//        mdmDir.add("position");
//        mdmDir.add("positioncustomer");
//        mdmDir.add("positioncustomerorg");
//        mdmDir.add("positionlevel");
//        mdmDir.add("positionterminal");
//        mdmDir.add("product");
//        mdmDir.add("productlevel");
//        mdmDir.add("region");
//        mdmDir.add("role");
//        mdmDir.add("rolecustomer");
//        mdmDir.add("roleposition");
//        mdmDir.add("rolepositionlevel");
//        mdmDir.add("terminal");
//        mdmDir.add("user");
//        Stream.of(root.listFiles())
//                .filter(x->mdmDir.contains(x.getName()))
//                .flatMap(x-> {
//                    File serviceImplDir = new File(x.getAbsolutePath() + "/service/impl");
//                    return Stream.of(serviceImplDir.listFiles());
//                })
//                .filter(x->x.getName().endsWith("Impl.java"))
//                .forEach(App::singleFile);
        List<Class<?>> classes = ClassUtil.getClasses("wordir.crm-prd.zdemoFileModify.src.main.java.com.example");
        classes.forEach(x-> System.out.println(x.getName()));
    }
}
