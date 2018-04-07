package edu.fileManager.Action;

import DBJavaBean.DB;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;
import org.apache.struts2.interceptor.ServletRequestAware;

public class FindFileAction extends ActionSupport implements ServletRequestAware{
    private String title;
    private String userName;
    private ResultSet rs=null;
    private String message=ERROR;
    private HttpServletRequest request;
        public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void message(String msg){
        int type=JOptionPane.YES_NO_OPTION;
        String title="信息提示";
        JOptionPane.showMessageDialog(null,msg,title,type);
    }
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        request=hsr;
    }
    @Override
    public void validate(){
        if(this.getTitle()==null||this.getTitle().length()==0){
            message("文件标题不允许为空！");
            addFieldError("title","文件标题不允许为空！");
        }else{
            try{
                DB mysql=new DB();
                userName=mysql.returnLogin(request);
                rs=mysql.selectFile(request, userName, "title", this.getTitle());
                if(!rs.next()){
                    message("此文件标题不存在！");
                    addFieldError("title","此文件标题不存在！");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public String execute() throws Exception {
        DB mysql=new DB();
        userName=mysql.returnLogin(request);
        String file=mysql.findFile(request, userName, this.getTitle());
        if(file.equals("ok")){
            message=SUCCESS;
        }
        return message;
    }   
}
