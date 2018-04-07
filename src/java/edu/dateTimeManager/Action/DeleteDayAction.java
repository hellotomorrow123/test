package edu.dateTimeManager.Action;

import DBJavaBean.DB;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

public class DeleteDayAction extends ActionSupport implements ServletRequestAware{
    private String message=ERROR;
    private String userName;
    private String day;
    private HttpServletRequest request;
        public void setServletRequest(HttpServletRequest hsr) {
        request=hsr;
    }
    public String execute() throws Exception {
        DB mysql=new DB();
        userName=mysql.returnLogin(request);
        day=mysql.returnDay(request);
        String dd=mysql.deleteDay(request, userName, day);
        if(dd.equals("ok")){
            message=SUCCESS;
        }
        return message;
    }

    
}
