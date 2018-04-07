package edu.friendManager.Action;

import DBJavaBean.DB;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
//import javax.swing.JOptionPane;
import org.apache.struts2.interceptor.ServletRequestAware;

public class AddFriAction extends ActionSupport implements ServletRequestAware{
    private String name;
    private String phone;
    private String email;
    private String workplace;
    private String place;
    private String QQ;
    private ResultSet rs=null;
    private String message=ERROR;
    private HttpServletRequest request;
    private String userName=null;
        public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getWorkplace() {
        return workplace;
    }
    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public String getQQ() {
        return QQ;
    }
    public void setQQ(String QQ) {
        this.QQ = QQ;
    }
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        request=hsr;
    }
//    public void message(String msg){
//        int type=JOptionPane.YES_NO_OPTION;
//        String title="信息提示";
//        JOptionPane.showMessageDialog(null,msg,title,type);
//    }
    @Override
    public void validate(){
        if(getName()==null||getName().length()==0){
            addFieldError("name","用户姓名不允许为空");
        }else{
            try {
                DB mysql=new DB();
                userName=mysql.returnLogin(request);
                rs=mysql.selectFri(request, userName, this.getName());
                if(rs.next()){
                    addFieldError("name","此用户已存在!");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if(getPhone()==null||getPhone().length()==0){
            addFieldError("phone","用户电话不允许为空");
        }
        if(getEmail()==null||getEmail().length()==0){
            addFieldError("email","邮箱地址不允许为空");
        }
        if(getWorkplace()==null||getWorkplace().length()==0){
            addFieldError("workplace","工作单位不允许为空");
        }
        if(getPlace()==null||getPlace().length()==0){
            addFieldError("place","家庭住址不允许为空");
        }
        if(getQQ()==null||getQQ().length()==0){
            addFieldError("QQ","用户QQ不允许为空");
        }
    }
   public String execute() throws Exception{
        DB mysql=new DB();
        userName=mysql.returnLogin(request);
        String fri=mysql.insertFri(request, userName, this.getName(), this.getPhone(), this.getEmail(), this.getWorkplace(), this.getPlace(), this.getQQ());
        if(fri.equals("ok")){
            message=SUCCESS;
        }else if(fri.equals("one")){
            message=INPUT;
        }
        return message;
    }
}
