
package edu.personManager.Action;

import DBJavaBean.DB;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
//import javax.swing.JOptionPane;
import org.apache.struts2.interceptor.ServletRequestAware;

public class UpdateMessAction extends ActionSupport implements ServletRequestAware {
    private String name;
    private String sex;
    private String birth;
    private String nation;
    private String edu;
    private String work;
    private String phone;
    private String place;
    private String email;
    private String userName;
    private HttpServletRequest request;
    private String message=ERROR;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getBirth() {
        return birth;
    }
    public void setBirth(String birth) {
        this.birth = birth;
    }
    public String getNation() {
        return nation;
    }
    public void setNation(String nation) {
        this.nation = nation;
    }
    public String getEdu() {
        return edu;
    }
    public void setEdu(String edu) {
        this.edu = edu;
    }
    public String getWork() {
        return work;
    }
    public void setWork(String work) {
        this.work = work;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
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
            addFieldError("name","用户姓名不允许为空!");
        }
        if(getSex()==null||getSex().length()==0){
            addFieldError("sex","用户性别不允许为空!");
        }
//         if(!(getSex().equals("男"))||!(getSex().equals("女"))){
//            addFieldError("sex","用户性别符合标准!");
//        }
        if(getBirth()==null||getBirth().length()==0){
            addFieldError("birth","用户生日不允许为空!");
        }else{
            if(getBirth().length()!=10){
                addFieldError("birth","用户生日格式为'yyyy-mm-dd'!");
            }else{
                String an=this.getBirth().substring(4, 5);
                String bn=this.getBirth().substring(7, 8);
                if(!(an.equals("-"))||!(bn.equals("-"))){
                    addFieldError("birth","用户生日格式为'yyyy-mm-dd'!");
                }
            }
        }
        if(getNation()==null||getNation().length()==0){
            addFieldError("nation","用户民族不允许为空!");
        }
        if(getEdu()==null||getEdu().length()==0){
            addFieldError("edu","用户学历不允许为空!");
        }
        if(getWork()==null||getWork().length()==0){
            addFieldError("work","用户工作不允许为空!");
        }
        if(getPhone()==null||getPhone().length()==0){
            addFieldError("phone","用户电话不允许为空!");
        }
        if(getPlace()==null||getPlace().length()==0){
            addFieldError("place","用户地址不允许为空!");
        }
        if(getEmail()==null||getEmail().length()==0){
            addFieldError("email","用户email不允许为空!");
        }
    }
    public String execute() throws Exception {
        DB mysql=new DB();
        System.out.println(this.getName()+this.getSex()+this.getBirth()+this.getNation()+this.getEdu()+this.getWork()+this.getPhone()+this.getPlace()+this.getEmail());
        userName=mysql.returnLogin(request);
        String mess=mysql.updateMess(request, userName, this.getName(), this.getSex(), this.getBirth(), this.getNation(), this.getEdu(), this.getWork(), this.getPhone(), this.getPlace(), this.getEmail());
        if(mess.equals("ok")){
            message=SUCCESS;
        }
        return message;
    }  
}
