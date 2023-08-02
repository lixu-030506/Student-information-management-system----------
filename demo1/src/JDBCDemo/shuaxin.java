package JDBCDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class shuaxin {
    public void update() throws Exception{
        //1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.用户信息及url
        String url="";
        String username="";
        String password="";
        //3.连接数据库
        Connection conn= DriverManager.getConnection(url,username,password);
        //4.获取sql对象
        Statement state=conn.createStatement();

        System.out.println("请输入学生学号：");
        Scanner scan=new Scanner(System.in);
        int stu_no=scan.nextInt();
        System.out.println("请输入需要修改的信息：(a.姓名,b.性别,c.班级,d.宿舍,q.退出)");
        String flag=scan.next();
        while(!flag.equals("q")) {
            String temp = scan.next();
            String sql;
            int num;
            switch (flag) {
                case "a":
                    sql = "update student set StudentName ='" + temp + "' " + "where StudentNo=" + stu_no;
                    num=state.executeUpdate(sql);
                    if(num>0) System.out.println("修改成功！");
                    else System.out.println("修改失败！");
                    break;
                case "b":
                    sql = "update student set Sex ='" + temp + "' " + "where StudentNo=" + stu_no;
                    num=state.executeUpdate(sql);
                    if(num>0) System.out.println("修改成功！");
                    else System.out.println("修改失败！");
                    break;
                case "c":
                    sql = "update student set ClassNumber ='" + temp + "' " + "where StudentNo=" + stu_no;
                    num=state.executeUpdate(sql);
                    if(num>0) System.out.println("修改成功！");
                    else System.out.println("修改失败！");
                    break;
                case "d":
                    sql = "update student set RoomNumber ='" + temp + "' " + "where StudentNo=" + stu_no;
                    num=state.executeUpdate(sql);
                    if(num>0) System.out.println("修改成功！");
                    else System.out.println("修改失败！");
                    break;
                default:
                    break;
            }
            flag = scan.next();
        }
    }

}
