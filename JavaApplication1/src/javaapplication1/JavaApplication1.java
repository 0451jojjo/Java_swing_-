/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class JavaApplication1 {
   private static String url="jdbc:mysql://localhost:3306/bookstore?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
     private static String user="root";
    private static String password="ysz319";
    private static Connection con;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException { 
        
      /*  Map<String,String> mp=new HashMap<String,String>();
        mp.put("Ynum","言情" );
         mp.put("诗歌", "Snum");
         mp.put("侦探", "Znum");
         mp.put("科幻", "Knum");
         mp.put("教程", "Jnum");
         mp.put("其他", "Qnum");*/
        // String sum=;
     //    String ID="1111111";
        // TODO code application logic here
         
         //boolean f=MYSQLConn.inserrecommend(ID,sum);
        //System.out.println(mp.get("Ynum"));
        String id="";
                 Calendar calendar= Calendar.getInstance();
                 SimpleDateFormat dateFormat= new SimpleDateFormat("hhmmss");
                id+=dateFormat.format(calendar.getTime());
                
                System.out.println(id);
    }
   
    
}
