package JDBCDemo;

import java.sql.*;
import java.util.Scanner;

public class JDBCDemo {
    public static void main(String[] args) throws Exception{
        JDBCDemo manager=new JDBCDemo();
        manager.Gui();
        Scanner scan=new Scanner(System.in);
        String flag=scan.next();
        while(!flag.equals("g")){
            switch(flag){
                case "a":manager.init();break;
                case "b":manager.create();break;
                case "c":manager.search();break;
                case "d":manager.delete();break;
                case "e":manager.read();break;
                case "f":manager.update();break;
                default:break;
            }
            flag=scan.next();
        }
    }

    public void Gui(){
        System.out.printf("\033[35;2m%70s\n\033[0m","学生信息管理系统");
        System.out.printf("\033[31;1m%15s\033[0m","a.初始化学生信息");
        System.out.printf("\033[32;1m%15s\033[0m","b.插入学生信息");
        System.out.printf("\033[33;1m%15s\033[0m","c.查询学生信息");
        System.out.printf("\033[34;1m%15s\033[0m","d.删除学生信息");
        System.out.printf("\033[35;1m%15s\033[0m","e.查询所有学生信息");
        System.out.printf("\033[36;1m%15s\033[0m","f.修改学生信息");
        System.out.printf("\033[37;1m%15s\033[0m","g.退出系统");
    }

    public void init() throws Exception{
        //1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.用户信息及url
        String url="jdbc:mysql://localhost:3306/student";
        String username="root";
        String password="030506";
        //3.连接数据库
        Connection conn=DriverManager.getConnection(url,username,password);
        //4.获取sql对象
        Statement state=conn.createStatement();

        int flag=state.executeUpdate("truncate manager");

        Scanner scan=new Scanner(System.in);
        System.out.print("请输入学生人数:");
        int stu_num=scan.nextInt();
        if(stu_num>0) System.out.println("请依次输入每位学生的基本信息（学号/姓名/性别/班级/宿舍号");
        else System.out.println("初始化失败！");
        int i=1;
        while(i<=stu_num){
            System.out.println("第"+i+"位同学");
            int stu_no=scan.nextInt();
            String stu_name=scan.next();
            String stu_sex=scan.next();
            String stu_class=scan.next();
            String stu_room=scan.next();
            String sql="insert into manager values ("+stu_no+",'"+stu_name+"','"+stu_sex+"','"+stu_class+"','"+stu_room+"'"+")";
            int num=state.executeUpdate(sql);
            if(num>0) System.out.println("插入成功!");
            else System.out.println("插入失败！");
            i++;
        }
    }

    public void read() throws Exception{
        //1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.用户信息及url
        String url="jdbc:mysql://localhost:3306/student";
        String username="root";
        String password="030506";
        //3.连接数据库
        Connection conn=DriverManager.getConnection(url,username,password);
        //4.获取sql对象
        Statement state=conn.createStatement();

        ResultSet result=state.executeQuery("select * from manager order by StudentNo asc");
        System.out.printf("%-15s%-15s%-15s%-15s%-15s","学号","姓名","性别","班级","宿舍");
        System.out.print("\n");
        while(result.next()){
            System.out.printf("%08d%-7s%-15s%-15s%-15s%-15s",result.getObject("StudentNo"),"",result.getObject("StudentName")
                    ,result.getObject("Sex"),result.getObject("ClassNumber"),
                    result.getObject("RoomNumber"));
            System.out.print("\n");
        }
    }

    public void create() throws Exception{
        //1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.用户信息及url
        String url="jdbc:mysql://localhost:3306/student";
        String username="root";
        String password="030506";
        //3.连接数据库
        Connection conn=DriverManager.getConnection(url,username,password);
        //4.获取sql对象
        Statement state=conn.createStatement();

        Scanner scan=new Scanner(System.in);
        System.out.println("请输入学生信息：");
        int stu_no=scan.nextInt();
        String stu_name=scan.next();
        String stu_sex=scan.next();
        String stu_class=scan.next();
        String stu_room=scan.next();
        String sql="insert into manager values ("+stu_no+",'"+stu_name+"','"+stu_sex+"','"+stu_class+"','"+stu_room+"'"+")";
        int num=state.executeUpdate(sql);
        if(num>0) System.out.println("插入成功！");
        else System.out.println("插入失败！");
    }

    public void update() throws Exception{
        //1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.用户信息及url
        String url="jdbc:mysql://localhost:3306/student";
        String username="root";
        String password="030506";
        //3.连接数据库
        Connection conn=DriverManager.getConnection(url,username,password);
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
                    sql = "update manager set StudentName ='" + temp + "' " + "where StudentNo=" + stu_no;
                    num=state.executeUpdate(sql);
                    if(num>0) System.out.println("修改成功！");
                    else System.out.println("修改失败！");
                    break;
                case "b":
                    sql = "update manager set Sex ='" + temp + "' " + "where StudentNo=" + stu_no;
                    num=state.executeUpdate(sql);
                    if(num>0) System.out.println("修改成功！");
                    else System.out.println("修改失败！");
                    break;
                case "c":
                    sql = "update manager  set ClassNumber ='" + temp + "' " + "where StudentNo=" + stu_no;
                    num=state.executeUpdate(sql);
                    if(num>0) System.out.println("修改成功！");
                    else System.out.println("修改失败！");
                    break;
                case "d":
                    sql = "update manager  set RoomNumber ='" + temp + "' " + "where StudentNo=" + stu_no;
                    num=state.executeUpdate(sql);
                    if(num>0) System.out.println("修改成功！");
                    else System.out.println("修改失败！");
                    break;
                default:
                    break;
            }
            return;
        }
        flag = scan.next();
    }

    public void delete() throws Exception{
        //1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.用户信息及url
        String url="jdbc:mysql://localhost:3306/student";
        String username="root";
        String password="030506";
        //3.连接数据库
        Connection conn=DriverManager.getConnection(url,username,password);
        //4.获取sql对象
        Statement state=conn.createStatement();

        System.out.println("请输入需要删除的学生信息:");
        Scanner scan=new Scanner(System.in);
        int stu_no=scan.nextInt();
        String sql="delete from manager where StudentNo="+stu_no;
        int num=state.executeUpdate(sql);
        if(num>0) System.out.println("删除成功！");
        else System.out.println("删除失败！");
    }

    public void search() throws Exception{
        //1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.用户信息及url
        String url="jdbc:mysql://localhost:3306/student";
        String username="root";
        String password="030506";
        //3.连接数据库
        Connection conn=DriverManager.getConnection(url,username,password);
        //4.获取sql对象
        Statement state=conn.createStatement();

        System.out.println("请输入需要查询的学生学号：");
        Scanner scan=new Scanner(System.in);
        int stu_no=scan.nextInt();
        String sql="select * from manager where StudentNo="+stu_no;
        ResultSet result=state.executeQuery(sql);
        while(result.next()){
            System.out.printf("学号:%08d\n姓名:%-15s\n性别:%-15s\n班级:%-15s\n宿舍:%-15s\n",result.getObject("StudentNo"),result.getObject("StudentName"),
                    result.getObject("Sex"),result.getObject("ClassNumber"),result.getObject("RoomNumber"));
            System.out.println("查询成功！");
        }
    }
}