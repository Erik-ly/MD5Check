import bean.FileInfo;
import utils.FileScanner;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Erik
 * @date 2019/8/15
 */
public class MD5Check {

    private static List<FileInfo> fileInfoCache = new ArrayList<FileInfo>();

    public static void main(String[] args) {

//        final String folderPath = "/home/erik/md5Check/test";
//        int scanFileInterval = 5000;

        //folderPath 需要监控的文件夹
        //扫描间隔时间，单位毫秒
        final String folderPath = args[0];
        String scanFileIntervalStr = args[1];
        int scanFileInterval = Integer.valueOf(scanFileIntervalStr);

        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {

                scanAndCreateMD5(folderPath);

            }
        }, 0, scanFileInterval);

    }

    private static void scanAndCreateMD5(String folderPath){
        List<FileInfo> fileInfoList = FileScanner.getFiles(folderPath);

        //去掉已经存在的
        fileInfoList.removeAll(fileInfoCache);

        //打印更改文件
        for (FileInfo fileInfo : fileInfoList) {
            System.out.println("变化的文件：" + fileInfo.getFilePath());
        }

        //添加新增文件
        fileInfoCache.addAll(fileInfoList);

        if(fileInfoCache.size() == 10000){
            fileInfoCache.clear();
        }

        for (FileInfo fileInfo : fileInfoList) {

            FileUtils.createFileMD5(fileInfo.getFilePath());

        }


    }

}
