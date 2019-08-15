package utils;

import com.google.common.hash.Hashing;
import com.google.common.io.Files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Erik
 * @date 2019/4/16 16:09
 */
public class FileUtils {

    public static String createFileMD5(String filePath){

        String md5FilePath = "";
        File file = new File(filePath);
        String md5Str = "";

        /**
         * Google Guava
         */
        try {
            md5Str = Files.hash(file, Hashing.md5()).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 将md5值写入到文件中
         */
        if (!md5Str.equals("")){

            //修改文件后缀名
            md5FilePath = changeSuffix(filePath, "md5");
            //生成md5文件
            fileLinesWrite(md5FilePath,md5Str,false);
        }

        return md5FilePath;
    }


    /**
     * 文件数据写入（如果文件夹和文件不存在，则先创建，再写入）
     * @param filePath 文件路径
     * @param content 写入内容
     * @param flag true:如果文件存在且存在内容，则内容换行追加；false:如果文件存在且存在内容，则内容替换
     * @return
     */
    public static String fileLinesWrite(String filePath,String content,boolean flag){
        String filedo = "write";
        FileWriter fw = null;
        try {
            File file=new File(filePath);
            //如果文件夹不存在，则创建文件夹
            if (!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }

            //如果文件不存在，则创建文件,写入第一行内容
            if(!file.exists()){
                file.createNewFile();
                fw = new FileWriter(file);
                filedo = "create";

            //如果文件存在,则追加或替换内容
            }else{
                fw = new FileWriter(file, flag);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println(content);
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filedo;
    }

    /**
     * 更改文件后缀名
     * @param filePath 文件路径或文件名
     * @param suffix 后缀名（不需要加"."）
     * @return 更改后缀名后的文件
     */
    public static String changeSuffix(String filePath,String suffix){
        String changedFilePath = "";

        changedFilePath = filePath +  "." + suffix;

//        if (filePath.contains(".")){
//            changedFilePath = filePath.substring(0, filePath.lastIndexOf(".")) + "." + suffix;
//        }else {
//            changedFilePath = filePath +  "." + suffix;
//        }

        return changedFilePath;
    }


}
