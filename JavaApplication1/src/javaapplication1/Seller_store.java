/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.event.*;

/**
 *
 * @author Administrator
 */
public class Seller_store {
     private static String url="jdbc:mysql://localhost:3306/bookstore?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
     private static String user="root";
    private static String password="ysz319";
    private static Connection con;
    private static String ID;
    
    JMenu fileMenu = new JMenu("大厅");
  //  JMenu editMenu = new JMenu("订单");
    JMenu viewMenu = new JMenu("购物车");
    JMenu aboutMenu = new JMenu("我的");
    JFrame jf = new JFrame("网络书城");
   public static void main(String [] args) throws Exception
    {
        Seller_store S=new Seller_store();
                try {
                    S.seller_s("","'20200114101'");
                } catch (Exception ex) {
                    Logger.getLogger(hall.class.getName()).log(Level.SEVERE, null, ex);
                }
      //  seller_s();
    }
    public  void seller_s(String str,String ID) throws Exception
    {
        this.ID=ID;
        Class.forName("com.mysql.cj.jdbc.Driver");
         ImageIcon icon3=new ImageIcon("书2.png");  
        icon3.setImage(icon3.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT));
        jf.setIconImage(icon3.getImage());
        con=DriverManager.getConnection(url,user,password);
       // JFrame jf = new JFrame("网络书城");
        jf.setSize(600, 600);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        String sql="";
        Pattern pattern = Pattern.compile("[0-9]*");
        
         if(str.length()>2&&!pattern.matcher(str).matches()) str=str.substring(0,2);
        sql="select * from book where book_type like '%"+str+"%'or book_name like '%"+str+"%'or vendor_id like '%"+str+"%'";//查询语句
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery(sql);//将查询结果给rs
        
        rs.last(); // 将光标移动到最后一行   
        int rowCount = rs.getRow(); // 得到当前行号，即结果集记录数
        rs.beforeFirst();//返回最开始哪一行
       // System.out.println(rowCount);
        
        JPanel panel = new JPanel(null);
    
        rowCount=rowCount*200+300;
        panel.setPreferredSize(new Dimension(0,rowCount));//规划长度,是为了滚动窗口做准备(只会这种蠢办法)
        int w1=50,h1=50,w2=270,h2=50,w3=350,h3=100,h5=200,h4=150;
       while(rs.next())
       {
           Blob photo =rs.getBlob("product");
           int sales=rs.getInt("sales");//销量
           String bname=rs.getString("book_name");//书名
           String vid  =rs.getString("vendor_id");//店铺编号
           Double price=rs.getDouble("price");//价格
           String bid=rs.getString("book_id");
           //预处理数据
            String s = Integer.toString(sales);
           String XL="已有"+s+"人购买";
           String b=String.valueOf(price);
           String newprice="￥"+b;
           String bcpty=rs.getString("book_type");
           byte[] imageData=photo.getBytes(1,photo.getBinaryStream().available());//得到操作系统默认的编码格式字节数组
          ImageIcon icon = new ImageIcon(imageData);
         // System.out.println(sales+" "+bname+" "+vid+" "+price);
        JLabel label01 = new JLabel();
        label01.setText(bname);
        label01.setFont(new Font(null, Font.PLAIN, 25));  // 设置字体，null 表示使用默认字体
        label01.setSize(300,50);//宽高
        label01.setLocation(w2,h2);//坐标
        panel.add(label01);
        
        
       // ImageIcon icon = new ImageIcon("4.png");//待修改
	JLabel label = new JLabel();   
        icon.setImage(icon.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT));
	label.setIcon(icon);
        label.setLocation(w1,h1);//坐标
        label.setSize(200,200);
        panel.add(label);
        
        JLabel tlabel01=new JLabel();
       tlabel01.setText(newprice);
       tlabel01.setFont(new Font(null,Font.PLAIN,23));
       tlabel01.setForeground(Color.red);
       tlabel01.setSize(100,40);
       tlabel01.setLocation(w2, h3);//坐标
       panel.add(tlabel01);
       
      // String a="已有100人购买";//已经多少人购买
        JLabel tlabel02=new JLabel();
       tlabel02.setText(XL);
       tlabel02.setFont(new Font(null,Font.PLAIN,15));
       tlabel02.setForeground(Color.black);
       tlabel02.setSize(120,40);
       tlabel02.setLocation(w3, h3);//坐标
       panel.add(tlabel02);
       
        JButton btn = new JButton("进店浏览");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Seller_store S=new Seller_store();
                try {
                    S.seller_s(vid,ID);
                    jf.dispose();
                    // System.out.println("被点击"+bname);
                    // textField.setText("");
                } catch (Exception ex) {
                    Logger.getLogger(Seller_store.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btn.setSize(120,40);//大小
        btn.setLocation(w2, h4);//坐标
        btn.setContentAreaFilled(false);//按钮透明化
        btn.setFont(new Font("黑体",Font.LAYOUT_LEFT_TO_RIGHT,18));
        panel.add(btn);
        
       JRadioButton radioBtn01 = new JRadioButton("购买");
        JRadioButton radioBtn02 = new JRadioButton("不购买");

        // 创建按钮组，把两个单选按钮添加到该组
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(radioBtn01);
        btnGroup.add(radioBtn02);
         radioBtn01.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<String,String> mp=new HashMap<String,String>();
                 mp.put("言情", "Ynum");
                 mp.put("诗歌", "Snum");
                 mp.put("侦探", "Znum");
                 mp.put("科幻", "Knum");
                 mp.put("教程", "Jnum");
                 mp.put("其他", "Qnum");
                 String sum=mp.get(bcpty);
                 String id="OA";
                 Calendar calendar= Calendar.getInstance();
                 SimpleDateFormat dateFormat= new SimpleDateFormat("hhmmss");
                id+=dateFormat.format(calendar.getTime());
                
                System.out.println(id);
                 boolean flag=MYSQLConn.insertcard(id,ID,bid,1,price);
                 if(flag)
                 {
                      JOptionPane.showMessageDialog(null, "购买成功","系统提示",JOptionPane.INFORMATION_MESSAGE);
                      boolean f=MYSQLConn.inserrecommend(ID,sum);
                     //  System.out.println(sum+"  "+f);
                     // if(f) System.out.println("成功");
                 }
                 else 
                      JOptionPane.showMessageDialog(null, "购买失败","系统提示",JOptionPane.INFORMATION_MESSAGE);
               // System.out.println("被点击"+bname);
               // textField.setText("");
            }
        });
        radioBtn01.setSize(80,30);
        radioBtn02.setSize(80,30);
        radioBtn01.setLocation(w2, h5);
        radioBtn02.setLocation(w3,h5);
        // 设置第一个单选按钮选中
        radioBtn02.setSelected(true);

        panel.add(radioBtn01);
        panel.add(radioBtn02);
          h1+=220;
          h2+=220;
          h3+=220;
          h4+=220;
          h5+=220;
       }
         JScrollPane scrollPane = new JScrollPane(
                panel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
         /*
         * 创建一个菜单栏
         */
        JMenuBar menuBar = new JMenuBar();

        /*
         * 创建一级菜单
         */
         fileMenu.addMouseListener(new Simple());
     //   editMenu.addMouseListener(new Simple());
        viewMenu.addMouseListener(new Simple());
        aboutMenu.addMouseListener(new Simple());
        // 一级菜单添加到菜单栏
        menuBar.add(fileMenu);
       // menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(aboutMenu);
        jf.setJMenuBar(menuBar);

       // jf.setJMenuBar(menuBar);
         jf.setContentPane(scrollPane);
        jf.setVisible(true);
    }
     private class Simple implements MouseMotionListener,MouseListener
    {
       
        @Override
         
        public void mouseClicked(MouseEvent e) {
            if(fileMenu.isSelected()==true)
            {
                //Seller_browes st=new Seller_browes();
                  hall w=new hall();
              try {
            //st.seller_sb();
                       w.M_hall(ID);
                     jf.dispose();
               } catch (Exception ex) {
                    Logger.getLogger(Seller_store.class.getName()).log(Level.SEVERE, null, ex);
              }
                //System.out.println("被点击 fileMenu");
            }
            else if(viewMenu.isSelected()==true)
            {
                java.awt.EventQueue.invokeLater(new Runnable() {
                 public void run() {
                      new shopping_cart(ID).setVisible(true);
                  }
                });
                jf.dispose();
               // System.out.println("被点击 viewMenu");
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
     
