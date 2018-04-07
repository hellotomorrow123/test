package edu.fileManager.Action;

import DBJavaBean.DB;
import com.opensymphony.xwork2.ActionSupport;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

public class DownFileAction extends ActionSupport implements ServletRequestAware{
    private String message=ERROR;
    private HttpServletRequest request;
    public String getDownloadFileName() {
       DB mysql=new DB();
       String downFileName = mysql.returnFile(request, "fileName");
       try {
        downFileName = new String(downFileName.getBytes(), "ISO8859-1");
       } catch (Exception e) {
        e.printStackTrace();
       }
       return downFileName;
    }
    public InputStream getInputStream() throws Exception{
        DB mysql=new DB();
        String downFileName = mysql.returnFile(request,"fileName");
        String path="/save/"+downFileName;
        return ServletActionContext.getServletContext().getResourceAsStream(path);
    }
    public void setServletRequest(HttpServletRequest hsr) {
        request=hsr;
    }
    public String execute() throws Exception{
        message=SUCCESS;
        return message;
    }
}
