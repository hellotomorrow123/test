/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBean;

/**
 *
 * @author abc
 */
public class MyFileBean {
    private String title;
    private String name;
    private String contentType;
    private String size;
    private String filePath;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContentType() {
        return contentType;
    }
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
