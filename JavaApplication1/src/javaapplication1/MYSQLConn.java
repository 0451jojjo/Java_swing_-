/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;

/**
 *
 * @author 80924
 */
import java.sql.*;
import java.util.*;
public class MYSQLConn {
	 public static Connection JdbcConn() {
			 Connection conn=null;
			 try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		           // System.out.println("加载成功");
		        } catch (Exception ex) {
		        	ex.printStackTrace();
		        	System.out.println("加载失败");
		        }
			
			try {
				 String url="jdbc:mysql://localhost:3306/bookstore?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                                 String user="root";
                                 String password="ysz319";
			    conn=DriverManager.getConnection(url,user,password);
			} catch (SQLException ex) {
				ex.printStackTrace();			  
			}
                        return conn;
		}
         public static Vector selectData(String id){
            String sql="select oa.customer_id,ob.book_id,ob.order_cnt,ob.price_sum,oa.tel,oa.address,oa.remarks,ob.shipment\n" +
                    "from order_master oa,order_detail ob,book b\n" +
                    "where oa.order_id=ob.order_id and b.book_id = ob.book_id and ob.vendor_id=?;";
             Vector<Vector<String>> data=new Vector<Vector<String>>();
             try{
                    Connection conn=JdbcConn();
                    PreparedStatement pst=conn.prepareCall(sql);
                    pst.setString(1,id);
                    ResultSet rs=pst.executeQuery();
                    while(rs.next()){
                    Vector<String> line=new Vector<String>();
                    for(int i=1;i<=8;i++)
                    {
                        //System.out.print(rs.getObject(i).toString());
                        //String t=rs.getString(i);
                        //System.out.println(t);
                        String tmp=rs.getString(i);
                        if(tmp ==null || tmp.isEmpty())
                            line.add("null");
                        else line.add(tmp);
                    }
                        data.add(line);
                    }               
                    rs.close();
                    pst.close();
                    conn.close();
             }catch(SQLException ex)
            {
                ex.printStackTrace();
            }
             return data;
         }
         public static boolean upData(String id){
             boolean r=false;
             try{
                 String sql="update order_detail oa,book b set oa.shipment='2' where oa.shipment='1' and b.vendor_id=? and oa.book_id=b.book_id;";
                Connection conn=JdbcConn();
                PreparedStatement pst=conn.prepareCall(sql);
                pst.setString(1,id);
                int count=pst.executeUpdate();
                if(count>0)
                {
                    r=true;
                 }
                pst.close();
                conn.close();
             }catch(Exception e)
             {
                 return false;
             }
             return r;
         }
         ///新加的
         public static void updateBook(String id){
             try{
                 String sql="update book set  sales=sales+1 where book_id=?;";
                 Connection conn=JdbcConn();
                 PreparedStatement pst=conn.prepareCall(sql);
                 pst.setString(1,id);   
                 pst.executeUpdate();
             }catch(Exception e){
                 e.printStackTrace();
             }
         }
          public static Vector selectOrder(String book_id){
             Vector<String> data1=new Vector<String>();
             try{
                    String sql="select * from book where book_id=?";
                    Connection conn=JdbcConn();
                    PreparedStatement pst=conn.prepareCall(sql);
                    pst.setString(1, book_id);
                    ResultSet rs=pst.executeQuery();
                    while(rs.next()){
                        //System.out.print(rs.getObject(i).toString());
                        //String t=rs.getString(i);
                        //System.out.println(t);
                        data1.add("书名："+rs.getString("book_name")+"\n");
                        data1.add("ISBN："+rs.getString("ISBN")+"\n");
                        data1.add("类型："+rs.getString("book_type")+"\n");
                        data1.add("价格：\n");
                        data1.add(rs.getString("price"));
                    }               
                    rs.close();
                    pst.close();
                    conn.close();
             }catch(SQLException ex)
            {
                ex.printStackTrace();
            }
             return data1;
         }
          public static boolean insertMaster(String id,String cid,String name,String tel,String address,String sum ,String remark){
              boolean flag=false;
              try{
                  String sql="insert into order_master values(?,?,?,?,?,?,?);";
                  Connection conn=JdbcConn();
                  PreparedStatement pst=conn.prepareCall(sql);
                  pst.setString(1,id); 
                  pst.setString(2,cid);
                  pst.setString(3,name); 
                  pst.setString(4,tel);
                  pst.setString(5,address);
                  pst.setString(6,sum); 
                  pst.setString(7,remark);
                  int r=pst.executeUpdate();
                  if(r>0)
                  {
                     flag=true; 
                  }
                  pst.close();
                  conn.close();
              }catch(SQLException ex)
              {
                  ex.getNextException();
              }
              return flag;
          }
          public static boolean insertDetail(String mid,String did,String bid,String num,String price ,String sum,String vid){
              boolean flag=false;
              try{
                  String sql="insert into order_detail values(?,?,?,?,?,?,?,1);";
                  Connection conn=JdbcConn();
                  PreparedStatement pst=conn.prepareCall(sql);
                  pst.setString(1,mid); 
                  pst.setString(2,did);
                  pst.setString(3,bid); 
                  pst.setString(4,num);
                  pst.setString(5,price);
                  pst.setString(6,sum); 
                  pst.setString(7,vid);
                  int r=pst.executeUpdate();
                  pst.close();
                  conn.close();
                  if(r>0)
                  {
                     flag=true; 
                  } 
              }catch(SQLException ex)
              {
                  ex.getMessage();
              }
              return flag;
          }
          public static Vector selectOrderCard(String id){
             Vector<Vector<String>> data=new Vector<Vector<String>>();
             try{
                    String sql="select  oc.order_card_id,b.book_id,b.book_name,c.consumer_name,b.price,oc.book_number\n" +
                                "from  order_card oc,book b,consumer c\n" +
                                "where oc.book_id=b.book_id and b.vendor_id=c.consumer_id and oc.customer_id=?;";
                    Connection conn=JdbcConn();
                    PreparedStatement pst=conn.prepareCall(sql);
                    pst.setString(1,id);
                    ResultSet rs=pst.executeQuery();
                    while(rs.next()){
                        Vector<String> line=new Vector<String>();
                        for(int i=1;i<=6;i++)
                        {
                            line.add(rs.getString(i));
                        }
                        data.add(line);
                    }               
                    rs.close();
                    pst.close();
                    conn.close();
             }catch(SQLException ex)
            {
                ex.printStackTrace();
            }
             return data;
         }
          public static boolean DeleteOrderCard(String id){
              boolean flag=false;
              try{
                  String sql="delete  from order_card where order_card_id =?;";
                  Connection conn=JdbcConn();
                  PreparedStatement pst=conn.prepareCall(sql);
                  pst.setString(1,id);
                  int r=pst.executeUpdate();
                  pst.close();
                  conn.close();
                  if(r>0)
                  {
                     flag=true; 
                  } 
              }catch(SQLException ex)
              {
                  ex.printStackTrace();
              }
              return flag;
          }
          public static void DeleteOrder(String id)
          {
              try{
                  String sql1="delete  from order_detail where order_id =?;";
                  String sql2="delete  from order_master where order_id =?;";
                  Connection conn1=JdbcConn();
                  Connection conn2=JdbcConn();
                  PreparedStatement pst1=conn1.prepareCall(sql1);
                  PreparedStatement pst2=conn2.prepareCall(sql2);
                  pst1.setString(1,id);
                  pst2.setString(1,id);
                  int r1=pst1.executeUpdate();
                  int r2=pst2.executeUpdate();
              }catch(SQLException ex)
              { 
                  ex.printStackTrace();
              }
          }
           public static boolean insertcard(String id,String cid,String bid,int num,Double price){
              boolean flag=false;
              try{
                  String sql="insert into order_card values(?,?,?,?,?);";
                  Connection conn=JdbcConn();
                  PreparedStatement pst=conn.prepareCall(sql);
                  pst.setString(1,id); 
                  pst.setString(2,cid);
                  pst.setString(3,bid); 
                  pst.setInt(4, num);
                  pst.setDouble(5, price);
                  int r=pst.executeUpdate();
                  if(r>0)
                  {
                     flag=true; 
                  }
                  pst.close();
                  conn.close();
              }catch(SQLException ex)
              {
                  ex.getNextException();
              }
              return flag;
          }
           public static boolean upmine(String id,String name,String pass){
             boolean r=false;
             try{
                 String sql="update consumer set consumer_name=?,password=?where consumer_id=?";
                Connection conn=JdbcConn();
                PreparedStatement pst=conn.prepareCall(sql);
                pst.setString(3,id);
                pst.setString(1, name);
                pst.setString(2, pass);
                int count=pst.executeUpdate();
                if(count>0)
                {
                    r=true;
                 }
                pst.close();
                conn.close();
             }catch(Exception e)
             {
                 return false;
             }
             return r;
         }
         public static boolean inserrecommend(String id,String name){
              boolean flag=false;
              try{
                  String sql="update recommend set "+name+"="+name+"+1 where customer_id='"+id+"'";
                  Connection conn=JdbcConn();
                  PreparedStatement pst=conn.prepareCall(sql);
                // pst.setString(1,name);
                // pst.setString(2, name);
                // pst.setString(3, id);
                  int r=pst.executeUpdate();
                  if(r>0)
                  {
                     flag=true; 
                  }
                  pst.close();
                  conn.close();
              }catch(SQLException ex)
              {
                  ex.getNextException();
              }
              return flag;
          }
}



