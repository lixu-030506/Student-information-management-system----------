package JDBCDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class input {
    public void create() throws Exception{
        //1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.用户信息及url
        String url="";//数据库url
        String username="";//用户名(填自己的,下同)
        String password="";//用户密码
        //3.连接数据库
        Connection conn= DriverManager.getConnection(url,username,password);
        //4.获取sql对象
        Statement state=conn.createStatement();

        Scanner scan=new Scanner(System.in);
        System.out.println("请输入学生信息：");
        int stu_no=scan.nextInt();//学号
        String stu_name=scan.next();
        String stu_sex=scan.next();
        String stu_class=scan.next();
        String stu_room=scan.next();
        String sql="insert into student values ("+stu_no+",'"+stu_name+"','"+stu_sex+"','"+stu_class+"','"+stu_room+"'"+")";
        int num=state.executeUpdate(sql);
        if(num>0) System.out.println("插入成功！");
        else System.out.println("插入失败！");
    }

}
