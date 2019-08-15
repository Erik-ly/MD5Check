# 功能
定时扫描监控文件夹的文件变化，并生成变化文件的MD5文件。

文件变化是指新增或修改文件，当文件名称、文件大小、文件修改时间发生变化时均视为修改文件。

监控文件夹中以`.md5`结尾的文件不会生成该文件的MD5值文件。

# 使用方法
```
jar -jar MD5Check-1.0-SNAPSHOT.jar folderPath scanFileInterval
```

如`java -jar MD5Check-1.0-SNAPSHOT.jar /home/erik/md5Check/test 5000`，即每隔5000毫秒扫描`/home/erik/md5Check/test`目录下的文件变化，如果有变化则生成或更新该文件的MD5值文件
