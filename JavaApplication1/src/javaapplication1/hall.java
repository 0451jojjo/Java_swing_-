/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author Administrator
 */
public class hall {
    private static String url="jdbc:mysql://localhost:3306/bookstore?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
     private static String user="root";
    private static String password="ysz319";
    private static Connection con;
    private static String ID;
     JMenu fileMenu = new JMenu("大厅");
    // JMenu editMenu = new JMenu("订单");
     JMenu viewMenu = new JMenu("购物车");
     JMenu aboutMenu = new JMenu("我的");
     JFrame jf = new JFrame("大厅");
    public static void main(String[] args) throws Exception
    {
        //M_hall();
        hall h=new hall();
        h.M_hall("20200617001");
    }
    public void M_hall(String ID)  throws Exception
    {
        this.ID=ID;
        Class.forName("com.mysql.cj.jdbc.Driver");
         ImageIcon icon3=new ImageIcon("书2.png");  
        icon3.setImage(icon3.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT));
        jf.setIconImage(icon3.getImage());
        con=DriverManager.getConnection(url,user,password);
        jf.setSize(600, 600);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         JPanel panel=new JPanel(null);
          //大厅装扮
         ImageIcon icon = new ImageIcon("5.jpg");
	JLabel label = new JLabel();   
        icon.setImage(icon.getImage().getScaledInstance(600,230,Image.SCALE_DEFAULT));
	label.setIcon(icon);
        label.setSize(600,230);
        label.setLocation(0, 0);
        panel.add(label);
        
        final JTextField textField = new JTextField(25);
        textField.setFont(new Font(null, Font.PLAIN, 20));
        textField.setSize(350,30);
        textField.setLocation(50, 250);
        panel.add(textField);
        
        // 创建一个按钮，点击后获取文本框中的文本
        JButton btn = new JButton("搜索");
        btn.setContentAreaFilled(false);//按钮透明化
        btn.setFont(new Font("宋体", Font.PLAIN, 20));
        btn.setSize(80,30);
        btn.setLocation(420, 250);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // System.out.println("提交: " + );
                String str=textField.getText();
                Seller_store S=new Seller_store();
                try {
                    S.seller_s(str,ID);
                } catch (Exception ex) {
                    Logger.getLogger(hall.class.getName()).log(Level.SEVERE, null, ex);
                }
                textField.setText("");
                jf.dispose();
            }
        });
        
        //推荐部分最后写
        String sql="select Ynum,Jnum,Knum,Znum,Snum,Qnum from recommend";//查询语句
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery(sql);//将查询结果给rs
        Map<Integer,String> mp=new HashMap<Integer,String>();
        mp.put(1,"言情" );
         mp.put(2,"教程");
         mp.put(3,"科幻" );
         mp.put(4, "侦探");
         mp.put(5,"诗歌" );
         mp.put(6,"其他" );
         Integer num=0;
         
         ArrayList<Pnum> lis = new ArrayList<>();
         while(rs.next())
         {
             Pnum p1=new Pnum(1,rs.getInt("Ynum"));
             Pnum p2=new Pnum(2,rs.getInt("Jnum"));
             Pnum p3=new Pnum(3,rs.getInt("Knum"));
             Pnum p4=new Pnum(4,rs.getInt("Znum"));
             Pnum p5=new Pnum(5,rs.getInt("Snum"));
             Pnum p6=new Pnum(6,rs.getInt("Qnum"));
             lis.add(p1);lis.add(p2);lis.add(p3);
             lis.add(p4);lis.add(p5);lis.add(p6);
         }
        // Arrays.sort(a); 
         Collections.sort(lis);
         for(Pnum x: lis)
         {
             String k=mp.get(x.getSv());
             sql="select * from book where book_type like '%"+k+"%'";
             stmt=con.createStatement();
             rs=stmt.executeQuery(sql);//将查询结果给rs
          //   System.out.println(rs.getRow()+"  "+sql);
             rs.last(); // 将光标移动到最后一行   
             int rowCount = rs.getRow(); // 得到当前行号，即结果集记录数
             rs.beforeFirst();//返回最开始哪一行
             if(rowCount>0) break;
         }
         
        Double price=null;
        String bname="",vid="",newprice="";
         Blob photo = null;
         while(rs.next())
       {
           photo =rs.getBlob("product");
           bname=rs.getString("book_name");//书名
            vid  =rs.getString("vendor_id");//店铺编号
           price=rs.getDouble("price");//价格
           //预处理数据
           String b=String.valueOf(price);
            newprice="￥"+b;
            break;
       }
        panel.add(btn);
        JLabel label03=new JLabel();
         label03.setText("猜你喜欢");
        label03.setFont(new Font("宋体", Font.ITALIC, 25)); 
        label03.setSize(120,30);
        label03.setLocation(30, 290);
        label03.setForeground(Color.PINK);
        panel.add(label03);
        byte[] imageData=photo.getBytes(1,photo.getBinaryStream().available());//得到操作系统默认的编码格式字节数组
       ImageIcon icon1 = new ImageIcon(imageData);
       // ImageIcon icon1 = new ImageIcon("5.png");
	JLabel label01 = new JLabel();   
        icon1.setImage(icon1.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT));
	label01.setIcon(icon1);
        label01.setSize(150,150);
        label01.setLocation(30, 340);
        JLabel label10 = new JLabel();
        label10.setText(bname);
        label10.setFont(new Font(null, Font.PLAIN, 20));  // 设置字体，null 表示使用默认字体
        label10.setBounds(190,340,100,30);
        panel.add(label01);
        panel.add(label10);
        
        JButton btn1 = new JButton("详细了解");
       String id=bname;
      // System.out.println(id);
       btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Seller_store S=new Seller_store();
                try {
                    S.seller_s(id, ID);
                    jf.dispose();
                    // System.out.println("被点击"+bname);
                    // textField.setText("");
                } catch (Exception ex) {
                    Logger.getLogger(Seller_store.class.getName()).log(Level.SEVERE, null, ex);
                }
                //System.out.println("被点击");
               // textField.setText("");
            }
        });
        btn1.setSize(100,40);
        btn1.setLocation(190, 380);
        btn1.setContentAreaFilled(false);//按钮透明化
        btn1.setFont(new Font("黑体",Font.LAYOUT_LEFT_TO_RIGHT,15));
        panel.add(btn1);
        
        JLabel tlabel01=new JLabel();
       tlabel01.setText(newprice);
       tlabel01.setFont(new Font(null,Font.PLAIN,23));
       tlabel01.setForeground(Color.red);
       tlabel01.setSize(100,40);
       tlabel01.setLocation(190, 430);
       panel.add(tlabel01);
        
       /*
         * 创建一个菜单栏
         */
        JMenuBar menuBar = new JMenuBar();

        /*
         * 创建一级菜单
         */
        fileMenu.addMouseListener(new Simple());
       // editMenu.addMouseListener(new Simple());
        viewMenu.addMouseListener(new Simple());
        aboutMenu.addMouseListener(new Simple());
        // 一级菜单添加到菜单栏
        menuBar.add(fileMenu);
       // menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(aboutMenu);
        jf.setJMenuBar(menuBar);
        jf.setContentPane(panel);
        jf.setVisible(true);
    }
     private class Simple implements MouseMotionListener,MouseListener
    {
       
        @Override
         
        public void mouseClicked(MouseEvent e) {
            if(fileMenu.isSelected()==true)
            {
                hall h=new hall();
                try {
                    h.M_hall(ID);
                   jf.dispose();
                    // System.out.println("被点击 fileMenu");
                } catch (Exception ex) {
                    Logger.getLogger(hall.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(viewMenu.isSelected()==true)
            {
                java.awt.EventQueue.invokeLater(new Runnable() {
                 public void run() {
                      new shopping_cart(ID).setVisible(true);
                  }
                });
                jf.dispose();
                //System.out.println("被点击 viewMenu");
            }
            else if(aboutMenu.isSelected()==true)
            {
                java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                try {
                    new buy_mine(ID).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(buy_mine.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(buy_mine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
                jf.dispose();
                //System.out.println("被点击 aboutMenu");
            }
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseDragged(MouseEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseMoved(MouseEvent e) {
          //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
}
