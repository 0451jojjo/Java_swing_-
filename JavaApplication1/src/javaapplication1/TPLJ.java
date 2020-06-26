/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;

/**
 *
 * @author Administrator
 */
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.*;
import java.sql.*;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
/**
 * 将本地文件的图片传到数据库的test的image表中并下载到本机桌面
 */
public class TPLJ {
 
  private static String url="jdbc:mysql://localhost:3306/bookstore?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
  private static String user="root";
  private static String password="ysz319";
  private static Connection con;
    private static String FileName;
 
  public static void main(String[] args) throws Exception {
  //  Class.forName("com.mysql.jdbc.Driver");
    Class.forName("com.mysql.cj.jdbc.Driver");
    con=DriverManager.getConnection(url,user,password);
    shangchuan();
  //  xiazai();    
  }
  //添加图片到数据库test4的file表
  public static void shangchuan() throws Exception{
    String sql="insert into book values('B00011','挪威的森林','9787532742929','20200114101','其他',18.00,?,980)";
    PreparedStatement ptmt=con.prepareStatement(sql);
   File f =new File("挪威的森林.jpg");


            FileInputStream input= new FileInputStream(f);
           // ptmt.setString(1,"杰克逊");
            ptmt.setBinaryStream(1, input,(int)f.length());
            ptmt.executeUpdate();
            System.out.println("插入成功");
            ptmt.close();
            input.close();
 
  }
  //从数据库中把图片下载至桌面
  public static void xiazai() throws Exception{
    String sql="select content from image ";//在我这里3.jpg是第三张图片
    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery(sql);//将查询结果给rs
    
    rs.last(); // 将光标移动到最后一行   
    int rowCount = rs.getRow(); // 得到当前行号，即结果集记录数
     rs.beforeFirst();
    if(rs.next()){
        Blob photo =rs.getBlob("content");
        byte[] imageData=photo.getBytes(1,photo.getBinaryStream().available());
       ImageIcon icon = new ImageIcon(imageData);
        JFrame jf = new JFrame("测试窗口");
        jf.setSize(600, 600);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       int w=rowCount*200+100;
        // 创建内容面板，指定布局为 null，则使用绝对布局
        JPanel panel = new JPanel(null);
        panel.setPreferredSize(new Dimension(0,w));
        JLabel label01 = new JLabel();
        label01.setText("解密黑科技");
        label01.setFont(new Font(null, Font.PLAIN, 25));  // 设置字体，null 表示使用默认字体
        label01.setSize(180,50);//宽高
        label01.setLocation(270,50);//坐标
        panel.add(label01);
        // 创建按钮 
        
        //ImageIcon icon = new ImageIcon("4.png");
	JLabel label = new JLabel();   
        icon.setImage(icon.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT));
	label.setIcon(icon);
        label.setLocation(50,50);
        label.setSize(200,200);
        panel.add(label);
        jf.setContentPane(panel);
        jf.setVisible(true);
     /* InputStream is=rs.getBinaryStream("content");
      //.getBinaryStream():a Java input stream that delivers the database column value as a stream of uninterpreted bytes
      FileOutputStream fos=new FileOutputStream("d:\\Documents\\NetBeansProjects\\美女.jpg");
      byte[] buffer=new byte[1024];
      int len=0;
      while((len=is.read(buffer))!=-1){
        fos.write(buffer,0,len);//将数据库的图片写出
      }*/
      System.out.println("下载成功！已下载至桌面，请查看"+rowCount);
    }else{
      System.out.println("图片不存在！");
    }
    con.close();
  }
 
 
}
