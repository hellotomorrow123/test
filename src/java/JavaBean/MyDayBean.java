
package JavaBean;

import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

public class MyDayBean implements ServletRequestAware{
    private String Day;
    private String thing;
    private ResultSet rs=null;
    private HttpServletRequest request;
    public MyDayBean(){
    }
   public String getDay() {
        return Day;
    }
    public void setDay(String Day) {
        this.Day = Day;
    }
    public String getThing() {
        return thing;
    }
    public void setThing(String thing) {
        this.thing = thing;
    }
    public void setServletRequest(HttpServletRequest hsr) {
        request=hsr;
    }
}
