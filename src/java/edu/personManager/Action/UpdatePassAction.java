package edu.personManager.Action;

import DBJavaBean.DB;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;
import org.apache.struts2.interceptor.ServletRequestAware;

public class UpdatePassAction extends ActionSupport implements ServletRequestAware{
    private String password1;
    private String password2;
    private String userName;
    private HttpServletRequest request;
    private String message=ERROR;
    public String getPassword1() {
        return password1;
    }
    public void setPassword1(String password1) {
        this.password1 = password1;
    }
    public String getPassword2() {
        return password2;
    }
    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    public void setServletRequest(HttpServletRequest hsr) {
        request=hsr;
    }
    public void message(String msg){
        int type=JOptionPane.YES_NO_OPTION;
        String title="信息提示";
        JOptionPane.showMessageDialog(null,msg,title,type);
    }
    public void validate(){
        if(!(password1.equals(password2))){
            message("两次密码不同！");
            addFieldError("password2","两次密码不同！");
        }
    }
    public String execute() throws Exception {
        DB mysql=new DB();
        userName=mysql.returnLogin(request);
        String pass=mysql.updatePass(request, userName, this.getPassword1());
        if(pass.equals("ok")){
            message=SUCCESS;
        }
        return message;
    } 
}
