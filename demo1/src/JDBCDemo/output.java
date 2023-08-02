package JDBCDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class output {
    public void read() throws Exception{
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

        ResultSet result=state.executeQuery("select * from student order by StudentNo asc");
        System.out.printf("%-15s%-15s%-15s%-15s%-15s","学号","姓名","性别","班级","宿舍");
        System.out.print("\n");
        while(result.next()){
            System.out.printf("%08d%-7s%-15s%-15s%-15s%-15s",result.getObject("StudentNo"),"",result.getObject("StudentName")
                    ,result.getObject("Sex"),result.getObject("ClassNumber"),
                    result.getObject("RoomNumber"));
            System.out.print("\n");
        }
    }

}
