package bean;

import java.util.Objects;

/**
 * @author Erik
 * @date 2019/8/15
 */
public class FileInfo {

    private String filePath;

    private Long lastModified;

    private Long length;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getLastModified() {
        return lastModified;
    }

    public void setLastModified(Long lastModified) {
        this.lastModified = lastModified;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "filePath='" + filePath + '\'' +
                ", lastModified=" + lastModified +
                ", length=" + length +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileInfo fileInfo = (FileInfo) o;
        return Objects.equals(filePath, fileInfo.filePath) &&
                Objects.equals(lastModified, fileInfo.lastModified) &&
                Objects.equals(length, fileInfo.length);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filePath, lastModified, length);
    }
}
