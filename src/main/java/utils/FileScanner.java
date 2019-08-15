package utils;

import bean.FileInfo;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * 扫描遍历文件夹获取 pom 文件列表
 * @author Erik
 * @date 2019/7/3
 */
public class FileScanner {

    public static List<FileInfo> getFiles(String folderPath){
        final List<FileInfo> fileList = new ArrayList<FileInfo>();

        try {
            Files.walkFileTree(Paths.get(folderPath), new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                    String filePath = file.toString();

                    if (filePath.endsWith("md5")){
                        return FileVisitResult.CONTINUE;
                    }

                    File file1 = new File(filePath);
                    long lastModifiedTime = file1.lastModified();
                    long length = file1.length();

                    FileInfo fileInfo = new FileInfo();
                    fileInfo.setFilePath(filePath);
                    fileInfo.setLastModified(lastModifiedTime);
                    fileInfo.setLength(length);

                    fileList.add(fileInfo);

                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileList;
    }


}
