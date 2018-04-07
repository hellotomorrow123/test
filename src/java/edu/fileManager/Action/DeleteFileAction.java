package edu.fileManager.Action;

import DBJavaBean.DB;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

public class DeleteFileAction extends ActionSupport implements ServletRequestAware{
    private String message=ERROR;
    private String userName;
    private String title;
    private String filePath;
    private HttpServletRequest request;
    
    public String DeleteFile(String FilePath){
        try{
            String filePath=FilePath;
            filePath=filePath.toString();
            File deleFile=new File(filePath);
            deleFile.delete();
            return "ok";
        }catch(Exception e){
            return null;
        }
    }
    public void setServletRequest(HttpServletRequest hsr) {
        request=hsr;
    }
    public String execute() throws Exception {
        DB mysql=new DB();
        userName=mysql.returnLogin(request);
        title=mysql.returnFile(request,"title");
        filePath=mysql.returnFile(request, "filePath");
        String tit=mysql.deleteFile(request, userName, title);
        if(tit.equals("ok")){
            String del=DeleteFile(filePath);
            if(del.equals("ok")){
                message=SUCCESS;
            }
        }
        return message;
    }
}
