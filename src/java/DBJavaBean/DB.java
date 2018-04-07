
package DBJavaBean;

import JavaBean.UserNameBean;
import JavaBean.MyDayBean;
import JavaBean.MyFileBean;
import JavaBean.MyFriBean;
import JavaBean.MyMessBean;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import org.apache.struts2.interceptor.ServletRequestAware;

//实现ServletRequestAware 通过IoC方式直接访问Servlet，并通过 request获取 session对象
public class DB implements ServletRequestAware{
    private String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String url="jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=personmessage";
    private String user="sa";
    private String password="123456";
    private Connection con=null;
    private Statement st=null;
    private ResultSet rs=null;
    private HttpServletRequest request;
    public DB(){
    }
   public String getDriverName() {
        return driverName;
    }
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setServletRequest(HttpServletRequest hsr) {
        request=hsr;
    }
    //完成连接数据库操作，并生成容器返回
    public Statement getStatement(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    	String url="jdbc:sqlserver://localhost:1433;DatabaseName=personmessage";
	    	Connection con=DriverManager.getConnection(url,"sa","123456");
	    	return con.createStatement();
            /*Class.forName(getDriverName());
            con=DriverManager.getConnection(getUrl(), getUser(), getPassword());
            return con.createStatement();*/
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
   //完成注册，把用户的注册信息录入到数据库中
  public String insertMess(HttpServletRequest request,String userName,String password,String name,String sex,String birth,String nation,String edu,String work,String phone,String place,String email){
        try{
            String sure=null;
            rs=selectMess(request,userName);
            //判断是否用户名已存在，如果存在返回one
            if(rs.next()){
                sure="one";
            }else{
                String sql="insert into myuser"+"(userName,password,name,sex,birth,nation,edu,mywork,phone,place,email)"+"values("+"'"+userName+"'"+","+"'"+password+"'"+","+"'"+name+"'"+","+"'"+sex+"'"+","+"'"+birth+"'"+","+"'"+nation+"'"+","+"'"+edu+"'"+","+"'"+work+"'"+","+"'"+phone+"'"+","+"'"+place+"'"+","+"'"+email+"'"+")";
                st=getStatement();
                int row=st.executeUpdate(sql);
                if(row==1){
                    //调用，myMessage方法，更新session中保存的用户信息
                    String mess=myMessage(request,userName);
                    if(mess.equals("ok")){
                        sure="ok";
                    }else{
                        sure=null;
                    }
                }else{
                    sure=null;
                }
            }
            return sure;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //更新注册的个人信息
    public String updateMess(HttpServletRequest request,String userName,String name,String sex,String birth,String nation,String edu,String work,String phone,String place,String email){
        try{
            String sure=null;
            String sql="update myuser set name='"+name+"',sex='"+sex+"',birth='"+birth+"',nation='"+nation+"',edu='"+edu+"',mywork='"+work+"',phone='"+phone+"',place='"+place+"',email='"+email+"' where userName='"+userName+"'";
            st=getStatement();
            int row=st.executeUpdate(sql);
            if(row==1){
                //调用，myMessage方法，更新session中保存的用户信息
                String mess=myMessage(request,userName);
                if(mess.equals("ok")){
                    sure="ok";
                }else{
                    sure=null;
                }
            }else{
                sure=null;
            }
            return sure;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //查询个人信息，并返回 rs
    public ResultSet selectMess(HttpServletRequest request,String userName){
        try{
            String sql="select * from myuser where userName='"+userName+"'";
            st=getStatement();
            return st.executeQuery(sql);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //把个人信息通过MyMessBean，保存到session对象中
    public String myMessage(HttpServletRequest request,String userName){
        try{
            ArrayList listName=null;
            HttpSession session=request.getSession();
            listName=new ArrayList();
            rs=selectMess(request,userName);
            while(rs.next()){
                MyMessBean mess=new MyMessBean();
                mess.setName(rs.getString("name"));
                mess.setSex(rs.getString("sex"));
                mess.setBirth(rs.getString("birth"));
                mess.setNation(rs.getString("nation"));
                mess.setEdu(rs.getString("edu"));
                mess.setWork(rs.getString("mywork"));
                mess.setPhone(rs.getString("phone"));
                mess.setPlace(rs.getString("place"));
                mess.setEmail(rs.getString("email"));
                listName.add(mess);
                session.setAttribute("MyMess", listName);
            }
            return "ok";
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //添加联系人
    public String insertFri(HttpServletRequest request,String userName,String name,String phone,String email,String workplace,String place,String QQ){
        try{
            String sure=null;
            rs=selectFri(request,userName,name);
            //判断通讯人姓名是否已存在
            if(rs.next()){
                sure="one";
            }else{
                String sql="insert into myfriends"+"(userName,name,phone,email,workplace,place,QQ)"+"values("+"'"+userName+"'"+","+"'"+name+"'"+","+"'"+phone+"'"+","+"'"+email+"'"+","+"'"+workplace+"'"+","+"'"+place+"'"+","+"'"+QQ+"'"+")";
                st=getStatement();
                int row=st.executeUpdate(sql);
                if(row==1){
                    //调用myFridnds方法，更新session中保存的通讯录中的信息
                    String fri=myFriends(request,userName);
                    if(fri.equals("ok")){
                        sure="ok";
                    }else{
                        sure=null;
                    }
                }else{
                    sure=null;
                }
            }  
            return sure;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //删除联系人
    public String deleteFri(HttpServletRequest request,String userName,String name){
        try{
            String sure=null;
            String sql="delete from myfriends where userName='"+userName+"' and name='"+name+"'";
            st=getStatement();
            int row=st.executeUpdate(sql);
            if(row==1){
                //调用myFridnds方法，更新session中保存的通讯录中的信息
                String fri=myFriends(request,userName);
                if(fri.equals("ok")){
                    sure="ok";
                }else{
                    sure=null;
                }
            }else{
                sure=null;
            }
            return sure;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //修改联系人
    public String updateFri(HttpServletRequest request,String userName,String friendName,String name,String phone,String email,String workplace,String place,String QQ){
        try{
            String sure=null;
            //先删除该联系人的信息
            String del=deleteFri(request,userName,friendName );
            if(del.equals("ok")){
                //重新录入修改后的信息
                String in=insertFri(request,userName,name,phone,email,workplace,place,QQ);
                if(in.equals("ok")){
                    //调用myFridnds方法，更新session中保存的通讯录中的信息
                    String fri=myFriends(request,userName);
                    if(fri.equals("ok")){
                        sure="ok";
                    }else{
                        sure=null;
                    }
                }else{
                    sure=null;
                }
            }else{
                sure=null;
            }
            return sure;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //查询联系人
    public ResultSet selectFri(HttpServletRequest request,String userName,String name){
        try{
            String sql="select * from myfriends where userName='"+userName+"' and name='"+name+"'";
            st=getStatement();
            return st.executeQuery(sql);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //获取通讯录中所有联系人的信息
    public ResultSet selectFriAll(HttpServletRequest request,String userName){
        try{
            String sql="select * from myfriends where userName='"+userName+"'";
            st=getStatement();
            return st.executeQuery(sql);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //获取通讯录中所有联系人的信息，并把他们保存到session对象中
    public String myFriends(HttpServletRequest request,String userName){
        try{
            ArrayList listName=null;
            HttpSession session=request.getSession();
            listName=new ArrayList();
            rs=selectFriAll(request,userName);
            if(rs.next()){
                rs=selectFriAll(request,userName);
                while(rs.next()){
                    MyFriBean mess=new MyFriBean();
                    mess.setName(rs.getString("name"));
                    mess.setPhone(rs.getString("phone"));
                    mess.setEmail(rs.getString("email"));
                    mess.setWorkplace(rs.getString("workplace"));
                    mess.setPlace(rs.getString("place")); 
                    mess.setQQ(rs.getString("QQ"));
                    listName.add(mess);
                    session.setAttribute("friends", listName);
                }
            }else{
                session.setAttribute("friends", listName);
            }
            return "ok";
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //添加日程
    public String insertDay(HttpServletRequest request,String userName,String date,String thing){
        try{
            String sure=null;
            rs=selectDay(request,userName,date);
            //判断是否日程已有安排
            if(rs.next()){
                sure="one";
            }else{
                String sql="insert into mydate"+"(userName,date,thing)"+"values("+"'"+userName+"'"+","+"'"+date+"'"+","+"'"+thing+"'"+")";
                st=getStatement();
                int row=st.executeUpdate(sql);
                if(row==1){
                    //调用myDayTime方法，更新session对象中保存的日程信息
                    String day=myDayTime(request,userName);
                    if(day.equals("ok")){
                        sure="ok";
                    }else{
                        sure=null;
                    }
                }else{
                    sure=null;
                }
            }
            return sure;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //删除日程
    public String deleteDay(HttpServletRequest request,String userName,String date){
        try{
            String sure=null;
            String sql="delete from mydate where userName='"+userName+"' and date='"+date+"'";
            st=getStatement();
            int row=st.executeUpdate(sql);
            if(row==1){
                //调用myDayTime方法，更新session对象中保存的日程信息
                String day=myDayTime(request,userName);
                if(day.equals("ok")){
                    sure="ok";
                }else{
                    sure=null;
                }
            }else{
                sure=null;
            }
            return sure;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //修改日程
    public String updateDay(HttpServletRequest request,String userName,String Day,String date,String thing){
        try{
            String sure=null;
            //先删除该日程
            String del=deleteDay(request,userName,Day);
            if(del.equals("ok")){
                //从新录入修改后的信息
                String in=insertDay(request,userName,date,thing);
                if(in.equals("ok")){
                    //调用myDayTime方法，更新session对象中保存的日程信息
                    String day=myDayTime(request,userName);
                    if(day.equals("ok")){
                        sure="ok";
                    }else{
                        sure=null;
                    }
                }else{
                    sure=null;
                }
            }else{
                sure=null;
            }
            return sure;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //查询日程
    public ResultSet selectDay(HttpServletRequest request,String userName,String date){
        try{
            String sql="select * from mydate where userName='"+userName+"' and date='"+date+"'";
            st=getStatement();
            return st.executeQuery(sql);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //查询所有的日程信息
    public ResultSet selectDayAll(HttpServletRequest request,String userName){
        try{
            String sql="select * from mydate where userName='"+userName+"'";
            st=getStatement();
            return st.executeQuery(sql);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //查询所有的日程信息，并把他们保存到session对象中
    public String myDayTime(HttpServletRequest request,String userName){
        try{
            ArrayList listName=null;
            HttpSession session=request.getSession();
            listName=new ArrayList();
            rs=selectDayAll(request,userName);
            if(rs.next()){
                rs=selectDayAll(request,userName);
                while(rs.next()){
                    MyDayBean mess=new MyDayBean();
                    mess.setDay(rs.getString("date"));
                    mess.setThing(rs.getString("thing"));
                    listName.add(mess);
                    session.setAttribute("day", listName);
                }
            }else{
                session.setAttribute("day", listName);
            }   
            return "ok";
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //保存上传文件的信息
    public String insertFile(HttpServletRequest request,String userName,String title,String name,String contentType,String size,String filePath){
        try{
            String sure=null;
            //查询文件标题是否已存在
            rs=selectFile(request,userName,"title",title);
            if(rs.next()){
                sure="title";
            }else{
                //查询文件名是否已存在
                rs=selectFile(request,userName,"name",name);
                if(rs.next()){
                    sure="name";
                }else{
                    String sql="insert into myfile"+"(userName,title,name,contentType,size,filePath)"+"values("+"'"+userName+"'"+","+"'"+title+"'"+","+"'"+name+"'"+","+"'"+contentType+"'"+","+"'"+size+"'"+","+"'"+filePath+"'"+")";
                    st=getStatement();
                    int row=st.executeUpdate(sql);
                    if(row==1){
                        //调用myFile方法，更新session中保存的文件信息
                        String file=myFile(request,userName);
                        if(file.equals("ok")){
                            sure="ok";
                        }else{
                            sure=null;
                        }
                    }else{
                        sure=null;
                    }
                }
            }
            return sure;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //删除文件
    public String deleteFile(HttpServletRequest request,String userName,String title){
        try{
            String sure=null;
            String sql="delete from myfile where userName='"+userName+"' and title='"+title+"'";
            st=getStatement();
            int row=st.executeUpdate(sql);
            if(row==1){
                //调用myFile方法，更新session中保存的文件信息
                String file=myFile(request,userName);
                if(file.equals("ok")){
                    sure="ok";
                }else{
                    sure=null;
                }
            }else{
                sure=null;
            }
            return sure;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //修改文件
    public String updateFile(HttpServletRequest request,String userName,String Title,String title,String name,String contentType,String size,String filePath){
        try{
            String sure=null;
            //先删除该文件
            String del=deleteFile(request,userName,Title);
            if(del.equals("ok")){
                //从新录入修改后的信息
                String in=insertFile(request,userName,title,name,contentType,size,filePath);
                if(in.equals("ok")){
                    //调用myFile方法，更新session中保存的文件信息
                    String file=myFile(request,userName);
                    if(file.equals("ok")){
                        sure="ok";
                    }else{
                        sure=null;
                    }
                }else{
                    sure=null;
                }
            }else{
                sure=null;
            }
            return sure;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //查询文件
    public ResultSet selectFile(HttpServletRequest request,String userName,String type,String name){
        try{
            String sql="select * from myfile where userName='"+userName+"' and "+type+"='"+name+"'";
            st=getStatement();
            return st.executeQuery(sql);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //查询所有的文件信息
    public ResultSet selectFileAll(HttpServletRequest request,String userName){
        try{
            String sql="select * from myfile where userName='"+userName+"'";
            st=getStatement();
            return st.executeQuery(sql);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //查询所有的文件信息，并把他们保存到session对象中
    public String myFile(HttpServletRequest request,String userName){
        try{
            ArrayList listName=null;
            HttpSession session=request.getSession();
            listName=new ArrayList();
            rs=selectFileAll(request,userName);
            if(rs.next()){
                rs=selectFileAll(request,userName);
                while(rs.next()){
                    MyFileBean mess=new MyFileBean();
                    mess.setTitle(rs.getString("title"));
                    mess.setName(rs.getString("name"));
                    mess.setContentType(rs.getString("contentType"));
                    mess.setSize(rs.getString("size"));
                    listName.add(mess);
                    session.setAttribute("file", listName);
                }
            }else{
                session.setAttribute("file", listName);
            }   
            return "ok";
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //查询登录名，密码是否存在
    public ResultSet selectLogin(HttpServletRequest request,String userName,String password){
        try{
            String sql="select * from myuser where userName='"+userName+"' and password='"+password+"'";
            st=getStatement();
            return st.executeQuery(sql);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //把登录人的信息保存到session对象中
    public String myLogin(HttpServletRequest request,String userName){
        try{
            ArrayList listName=null;
            HttpSession session=request.getSession();
            listName=new ArrayList();
            rs=selectMess(request,userName);
            if(rs.next()){
                rs=selectMess(request,userName);
                while(rs.next()){
                    UserNameBean mess=new UserNameBean();
                    mess.setUserName(rs.getString("userName"));
                    mess.setPassword(rs.getString("password"));
                    listName.add(mess);
                    session.setAttribute("userName", listName);
                }
            }else{
                session.setAttribute("userName", listName);
            }
            return "ok";
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //返回登录用户的用户名
    public String returnLogin(HttpServletRequest request){
        String LoginName=null;
        HttpSession session=request.getSession();
        ArrayList login=(ArrayList)session.getAttribute("userName");
            if(login==null||login.size()==0){
                LoginName=null;
            }else{
                for(int i=login.size()-1;i>=0;i--){
                    UserNameBean nm=(UserNameBean)login.get(i);
                    LoginName=nm.getUserName();
                }
            }
            return LoginName;
    }
    /*调用myLogin、myMessage、myFriends、myDayTime、myFile方法，把所有的和用户有关的信息全部保存到session对象中,该方法，登录成功后调用*/
    public String addList(HttpServletRequest request,String userName){
        String sure=null;
        String login=myLogin(request,userName);
        String mess=myMessage(request,userName);
        String fri=myFriends(request,userName);
        String day=myDayTime(request,userName);
        String file=myFile(request,userName);
        if(login.equals("ok")&&mess.equals("ok")&&fri.equals("ok")&&day.equals("ok")&&file.equals("ok")){
            sure="ok";
        }else{
            sure=null;
        }
        return sure;
    }
    //修改用户密码
    public String updatePass(HttpServletRequest request,String userName,String password){
        try{
            String sure=null;
            String sql="update myuser set password='"+password+"' where userName='"+userName+"'";
            st=getStatement();
            int row=st.executeUpdate(sql);
            if(row==1){
                String mess=myLogin(request,userName);
                if(mess.equals("ok")){
                    sure="ok";
                }else{
                    sure=null;
                }
            }else{
                sure=null;
            }
            return sure;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //查找联系人，并保存其信息保存到session对象中
    public String findFri(HttpServletRequest request,String userName,String name){
        try{
            ArrayList listName=null;
            HttpSession session=request.getSession();
            listName=new ArrayList();
            rs=selectFri(request,userName,name);
            if(rs.next()){
                rs=selectFri(request,userName,name);
                while(rs.next()){
                    MyFriBean mess=new MyFriBean();
                    mess.setName(rs.getString("name"));
                    mess.setPhone(rs.getString("phone"));
                    mess.setEmail(rs.getString("email"));
                    mess.setWorkplace(rs.getString("workplace"));
                    mess.setPlace(rs.getString("place")); 
                    mess.setQQ(rs.getString("QQ"));
                    listName.add(mess);
                    session.setAttribute("findfriend", listName);
                }
            }else{
                session.setAttribute("findfriend", listName);
            }
                
            return "ok";
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //从查找到的联系人session对象中获取联系人姓名，并返回
    public String returnFri(HttpServletRequest request){
        String FriendName=null;
        HttpSession session=request.getSession();
        ArrayList login=(ArrayList)session.getAttribute("findfriend");
            if(login==null||login.size()==0){
                FriendName=null;
            }else{
                for(int i=login.size()-1;i>=0;i--){
                    MyFriBean nm=(MyFriBean)login.get(i);
                    FriendName=nm.getName();
                }
            }
            return FriendName;
    }
    //查找日程，并把日程信息保存到session对象中
    public String findDay(HttpServletRequest request,String userName,String date){
        try{
            ArrayList listName=null;
            HttpSession session=request.getSession();
            listName=new ArrayList();
            rs=selectDay(request,userName,date);
            if(rs.next()){
                rs=selectDay(request,userName,date);
                while(rs.next()){
                    MyDayBean mess=new MyDayBean();
                    mess.setDay(rs.getString("date"));
                    mess.setThing(rs.getString("thing"));
                    listName.add(mess);
                    session.setAttribute("findday", listName);
                }
            }else{
                session.setAttribute("findday", listName);
            }
                
            return "ok";
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //从查找到的日程session中获取日程信息，并返回
    public String returnDay(HttpServletRequest request){
        String date=null;
        HttpSession session=request.getSession();
        ArrayList login=(ArrayList)session.getAttribute("findday");
            if(login==null||login.size()==0){
                date=null;
            }else{
                for(int i=login.size()-1;i>=0;i--){
                    MyDayBean nm=(MyDayBean)login.get(i);
                    date=nm.getDay();
                }
            }
            return date;
    }
    //查找文件信息，并把文件的信息保存到session对象中
    public String findFile(HttpServletRequest request,String userName,String title){
        try{
            ArrayList listName=null;
            HttpSession session=request.getSession();
            listName=new ArrayList();
            rs=selectFile(request,userName,"title",title);
            if(rs.next()){
                rs=selectFile(request,userName,"title",title);
                while(rs.next()){
                    MyFileBean mess=new MyFileBean();
                    mess.setTitle(rs.getString("title"));
                    mess.setName(rs.getString("name"));
                    mess.setContentType(rs.getString("contentType"));
                    mess.setSize(rs.getString("size"));
                    mess.setFilePath(rs.getString("filePath"));
                    listName.add(mess);
                    session.setAttribute("findfile", listName);
                }
            }else{
                session.setAttribute("findfile", listName);
            }
                
            return "ok";
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //根据不同的条件，从查找到的文件session对象中获取文件相应的文件信息
    public String returnFile(HttpServletRequest request,String face){
        String file=null;
        HttpSession session=request.getSession();
        ArrayList login=(ArrayList)session.getAttribute("findfile");
            if(login==null||login.size()==0){
                file=null;
            }else{
                for(int i=login.size()-1;i>=0;i--){
                    MyFileBean nm=(MyFileBean)login.get(i);
                    if(face.equals("title")){
                        file=nm.getTitle();
                    }else if(face.equals("filePath")){
                        file=nm.getFilePath();
                    }if(face.equals("fileName")){
                        file=nm.getName();
                    }
                }
            }
            return file;
    }
    //一个带参数的信息提示框，供找错是用
    public void message(String msg){
        int type=JOptionPane.YES_NO_OPTION;
        String title="信息提示";
        JOptionPane.showMessageDialog(null,msg,title,type);
    }
}
