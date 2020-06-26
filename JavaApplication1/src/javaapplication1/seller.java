/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;

/**
 *
 * @author 80924
 */
public class seller extends javax.swing.JFrame {

    /**
     * Creates new form seller
     */
    String jtextfield1 = null;
    String jtextfield2 = null;
    String jtextfield3 = null;
    String jtextfield4 = null;
    String jtextfield5 = null;
    String jtextfield6 = null;
    String jtextfield7 = null;
    Vector V_update = new Vector();//添加操作时储存所有jtextfield的内容
    private static String ID;
    Object jtabletext = null;
    Object jtabletextID = null;
    int r, c;//列表选择行列数

    public seller(String id) {
        initComponents();
        this.setTitle("seller_home");
        this.setLocation(700, 300);
         ImageIcon icon3=new ImageIcon("书2.png");  
        icon3.setImage(icon3.getImage().getScaledInstance(16,16,Image.SCALE_DEFAULT));
        this.setIconImage(icon3.getImage());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.ID=id;
    }
    

    PreparedStatement ps = null;
//构建表，连接sql、
    public void test() {

        Connection conn = null;
        Statement stmt = null;
        try {
            //1.驱动注册程序
            //Class.forName("com.mysql.jdbc.Driver");
            //2.获取连接对象
            // conn = DriverManager.getConnection(url, user, password);
            // MYSQLConn mysqlcon = new MYSQLConn();

            PreparedStatement ps = null;

            try {
                // The newInstance() call is a work around for some
                // broken Java implementations

                Class.forName("com.mysql.cj.jdbc.Driver");
                // System.out.println("加载成功");
            } catch (Exception ex) {
                System.out.println("加载失败");
                // handle the error
            }

            try {
                String url = "jdbc:mysql://localhost:3306/bookstore?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
                String user = "root";
                String password = "ysz319";
                conn
                        = DriverManager.getConnection(url, user, password);

             //   System.out.println("连接成功");

            } catch (SQLException ex) {
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                System.out.println("连接失败");
            }

            //3.创建Statement
            stmt = conn.createStatement();
            //4.准备sql
            String sql = "select book_id,book_name,ISBN,vendor_id,book_type,price,sales from book where vendor_id ='"+ID+"'";
            
            ResultSet rs = stmt.executeQuery(sql);

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            while (rs.next()) {
                //构造一个空向量V_Add
                Vector V_Add = new Vector();
                //获取sql文本字段中的内容。
                V_Add.add(rs.getString("book_id"));
                V_Add.add(rs.getString("book_name"));
                V_Add.add(rs.getString("ISBN"));
                V_Add.add(rs.getString("vendor_id"));
                V_Add.add(rs.getString("book_type"));
                V_Add.add(rs.getString("price"));
                V_Add.add(rs.getString("sales"));
                //添加完信息后表格自动添加一行，将vector的东西以行为单位放入表中
                dtm.addRow(V_Add);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            //7.关闭连接(顺序:后打开的先关闭)
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField8 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "book_id", "book_name", "ISBN", "vendor_id", "book_type", "price", "sales"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jScrollPane1.setViewportView(jScrollPane2);

        jLabel2.setText("book_id:");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel3.setText("book_name:");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel5.setText("vendor_id:");

        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jLabel6.setText("book_type:");

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jButton1.setText("添加");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("查找");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel8.setText("price:");

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel4.setText("ISBN:");

        jLabel7.setText("sales:");

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jButton4.setText("修改");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setText("删除");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jButton5.setText("返回");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(87, 87, 87)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField5)
                            .addComponent(jTextField4)
                            .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE))
                        .addGap(146, 146, 146)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jButton5))
                        .addGap(212, 212, 212)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(407, 407, 407)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(38, 38, 38)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jButton1)
                        .addGap(39, 39, 39)
                        .addComponent(jButton2)
                        .addGap(35, 35, 35)
                        .addComponent(jButton4)
                        .addGap(64, 64, 64))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(jButton5))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here: 添加

        String sql = "insert into book values(?,?,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            // System.out.println("加载成功");
        } catch (Exception ex) {
            System.out.println("加载失败");
            // handle the error
        }

        try {
            String url = "jdbc:mysql://localhost:3306/bookstore?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
            String user = "root";
            String password = "ysz319";

            conn = DriverManager.getConnection(url, user, password);

           // System.out.println("连接成功_添加");

            //把文本框里面的东西输入的sql中，1234带掉列数
            ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, jtextfield1);
            ps.setString(2, jtextfield2);
            ps.setString(3, jtextfield3);
            ps.setString(4, jtextfield7);
            ps.setString(5, jtextfield4);
            ps.setString(6, jtextfield5);
            ps.setString(7, "");
            ps.setString(8, jtextfield6);
            ps.executeUpdate();

        } catch (SQLException ex) {
            // handle any errors 
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.out.println("连接失败_添加");
        }
        //更新表
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        V_update.add(jtextfield1);
        V_update.add(jtextfield2);
        V_update.add(jtextfield3);
        V_update.add(jtextfield7);
        V_update.add(jtextfield4);
        V_update.add(jtextfield5);
        V_update.add(jtextfield6);
        dtm.addRow(V_update);

//文本框清空
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");

        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume();
                    KeyboardFocusManager.
                            getCurrentKeyboardFocusManager().focusNextComponent();
                }
            }
        });
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here: 删除
        jtabletext = jTable1.getValueAt(jTable1.getSelectedRow(), 0);
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            // System.out.println("加载成功");
        } catch (Exception ex) {
            System.out.println("加载失败");
            // handle the error
        }

        try {
            String url = "jdbc:mysql://localhost:3306/bookstore?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
            String user = "root";
            String password = "123456789";

            conn = DriverManager.getConnection(url, user, password);

          //  System.out.println("连接成功_删除");

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            String sql;
            r = 0;
            c = 0;
            r = jTable1.getSelectedRow();//行
            c = jTable1.getSelectedColumn();//列
//            System.out.println(r + "test");
//            System.out.println(c + "test");
            jtabletextID = jTable1.getValueAt(r, 0);
            jtabletext = jTable1.getValueAt(r, c);

            dtm.removeRow(r);

            switch (c) {
                case 0:
                    ps = (PreparedStatement) conn.prepareStatement("delete from book where book_id = '" + jtabletext + "'");
                    ps.executeUpdate();

                case 1:
                    ps = (PreparedStatement) conn.prepareStatement("delete from book where book_name= '" + jtabletext + "'and book_id='" + jtabletextID + "'");
                    ps.executeUpdate();

                case 2:
                    ps = (PreparedStatement) conn.prepareStatement("delete from book where ISBN= '" + jtabletext + "'and book_id='" + jtabletextID + "'");
                    ps.executeUpdate();

                case 3:
                    ps = (PreparedStatement) conn.prepareStatement("delete from book where vendor_id= '" + jtabletext + "'and book_id='" + jtabletextID + "'");
                    ps.executeUpdate();

                case 4:
                    ps = (PreparedStatement) conn.prepareStatement("delete from book where book_type= '" + jtabletext + "'and book_id='" + jtabletextID + "'");
                    ps.executeUpdate();

                case 5:
                    ps = (PreparedStatement) conn.prepareStatement("delete from book where price= '" + jtabletext + "'and book_id='" + jtabletextID + "'");
                    ps.executeUpdate();

                case 6:
                    ps = (PreparedStatement) conn.prepareStatement("delete from book where sales= '" + jtabletext + "'and book_id='" + jtabletextID + "'");
                    ps.executeUpdate();

            }

        } catch (SQLException ex) {
            // handle any errors 
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here: 修改

        jtabletext = jTable1.getValueAt(jTable1.getSelectedRow(), 0);
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            // System.out.println("加载成功");
        } catch (Exception ex) {
            System.out.println("加载失败");
            // handle the error
        }

        try {
            String url = "jdbc:mysql://localhost:3306/bookstore?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
            String user = "root";
            String password = "123456789";

            conn = DriverManager.getConnection(url, user, password);

           // System.out.println("连接成功_修改");

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();

            r = 0;
            c = 0;
            r = jTable1.getSelectedRow();//行
            c = jTable1.getSelectedColumn();//列
//            System.out.println(jTable1.getValueAt(r, 1) + "test22");
//            System.out.println(jTable1.getValueAt(r, 2) + "test22");
//            System.out.println(jTable1.getValueAt(r, 3) + "test22");
//            System.out.println(jTable1.getValueAt(r, 4) + "test22");
//            System.out.println(jTable1.getValueAt(r, 5) + "test22");
//            System.out.println(jTable1.getValueAt(r, 6) + "test22");
//            System.out.println(jTable1.getValueAt(r, 0) + "test22");
////修改数据库  
            if (!"".equals(jTextField1.getText().trim())) {
                ps = (PreparedStatement) conn.prepareStatement("update  book set book_id='" + jTextField1.getText().trim() + "'where book_id= '" + jTable1.getValueAt(r, 0) + "'");
                ps.executeUpdate();
                dtm.setValueAt(jTextField1.getText().trim(), r, 0);
                System.out.println(jTable1.getValueAt(r, 0) + "test1");
            } 
            System.out.println(jTextField2.getText().trim());
            if (!"".equals(jTextField2.getText().trim())) {
                ps = (PreparedStatement) conn.prepareStatement("update  book set book_name='" + jTextField2.getText().trim() + "'where book_id= '" + jTable1.getValueAt(r, 0) + "'");
                ps.executeUpdate();
                dtm.setValueAt(jTextField2.getText().trim(), r, 1);
//                System.out.println(jTable1.getValueAt(r, 1) + "test2");
            } 
            if (!"".equals(jTextField3.getText().trim())) {
                ps = (PreparedStatement) conn.prepareStatement("update  book set ISBN='" + jTextField3.getText().trim() + "'  where book_id= '" + jTable1.getValueAt(r, 0) + "'");
                ps.executeUpdate();
                dtm.setValueAt(jTextField3.getText().trim(), r, 2);
//                System.out.println(jTable1.getValueAt(r, 2) + "test3");
            }
            if (!"".equals(jTextField7.getText().trim())) {
                ps = (PreparedStatement) conn.prepareStatement("update  book set vendor_id='" + jTextField7.getText().trim() + "' where book_id= '" + jTable1.getValueAt(r, 0) + "'");
                ps.executeUpdate();
                dtm.setValueAt(jTextField7.getText().trim(), r, 3);
//                System.out.println(jTable1.getValueAt(r, 3) + "test4");
            }
            if (!"".equals(jTextField4.getText().trim())) {
                ps = (PreparedStatement) conn.prepareStatement("update  book set book_type='" + jTextField4.getText().trim() + "'where book_id= '" + jTable1.getValueAt(r, 0) + "'");
                ps.executeUpdate();
                dtm.setValueAt(jTextField4.getText().trim(), r, 4);
//                System.out.println(jTable1.getValueAt(r, 4) + "test5");
            }
            if (!"".equals(jTextField5.getText().trim())) {
                ps = (PreparedStatement) conn.prepareStatement("update  book set price='" + jTextField5.getText().trim() + "'where book_id= '" + jTable1.getValueAt(r, 0) + "'");
                ps.executeUpdate();
                dtm.setValueAt(jTextField5.getText().trim(), r,5);
                System.out.println(jTable1.getValueAt(r, 5) + "price");
            }
            if(!"".equals(jTextField6.getText().trim())){
                ps = (PreparedStatement) conn.prepareStatement("update  book set sales= '" + jTextField6.getText().trim() + "' where book_id= '" + jTable1.getValueAt(r, 0) + "'");
                ps.executeUpdate();
                dtm.setValueAt(jTextField6.getText().trim(), r,6  );
//                System.out.println(jTable1.getValueAt(r, 6) + "test7");
            }

        } catch (SQLException ex) {
            // handle any errors 
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.out.println("连接失败_修改");
        }
        System.out.println("test");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:     book_id

        jtextfield1 = jTextField1.getText().trim();

      //jTextField1.addMouseListener(new mouse());
//        public void insertUpdate(DocumentEvent e) {
//			String aaa = p1_bipolarText.getText().trim();  
//            System.out.println("2->"+aaa);
//		}

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:book_name

        jtextfield2 = jTextField2.getText().trim();
        // V_update.add(jtextfield2);
//System.out.println(jtextfield2 + "aaaaa");
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:  book_type

        jtextfield4 = jTextField4.getText().trim();
        //V_update.add(jtextfield4);

    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:  ISBN

        jtextfield3 = jTextField3.getText().trim();
        //V_update.add(jtextfield3);
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here: vector_id

        jtextfield7 = jTextField7.getText().trim();
        //V_update.add(jtextfield7);

    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:  price

        jtextfield5 = jTextField5.getText().trim();
        //V_update.add(jtextfield5);
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:  sales

        jtextfield6 = jTextField6.getText().trim();
        //V_update.add(jtextfield6);

    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here: 查找

        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        String sql;

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(dtm);
        jTable1.setRowSorter(sorter);
        //实现过滤，search为正则表达式，该方法支持参数为null和空字符串，因此不用对输入进行校验
        String search;
//
//        if (jTextField2.getText().trim() != null) {
//            search = jTextField2.getText().trim();
//        } else if (jTextField1.getText().trim() != null) {
//            search = jTextField1.getText().trim();
//        } else if (jTextField3.getText().trim() != null) {
//            search = jTextField3.getText().trim();
//        } else if (jTextField4.getText().trim() != null) {
//            search = jTextField4.getText().trim();
//        } else if (jTextField5.getText() != null) {
//            search = jTextField5.getText();//查找价钱没反应
//        } else if (jTextField6.getText().trim() != null) {
//            search = jTextField6.getText().trim();
//        } else {
//            search = jTextField7.getText().trim();
//        }
      search = jTextField8.getText().trim();
        sorter.setRowFilter(RowFilter.regexFilter(search));


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
         String cty="";
        hall h=new hall();
        Sellers_hall S=new Sellers_hall();
         try {
             String url = "jdbc:mysql://localhost:3306/bookstore?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
                String user = "root";
                String password = "ysz319";
                 Class.forName("com.mysql.cj.jdbc.Driver");
                 Connection con=DriverManager.getConnection(url,user,password);
               String sql="select ctype from consumer where consumer_id like '"+ID+"'";//查询语句
               Statement stmt=con.createStatement();
              ResultSet rs=stmt.executeQuery(sql);//将查询结果给rs
            // System.out.println(ID);
              while(rs.next()){
                 cty=rs.getString("ctype");
              }
           //   System.out.println(cty);
               if(cty.equals("1")) h.M_hall(ID);
               else S.S_hall(ID);
                   this.dispose();
                    // System.out.println("被点击 fileMenu");
                } catch (Exception ex) {
                    Logger.getLogger(hall.class.getName()).log(Level.SEVERE, null, ex);
                }
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(seller.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(seller.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(seller.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(seller.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // new seller().setVisible(true);
//                DefaultTableModel  tableModel = new DefaultTableModel(); 

//             public boolean isCellEditable(int row, int column)
//                  {
//                             return false;//表格不允许被编辑
//                  }
//      }; 
                seller ss = new seller("2222222");

                //ss.jTable1.setModel(t);
                ss.setVisible(true);
                ss.test();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
