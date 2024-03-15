 package db;
import java.io.FileInputStream;
import java.sql.*; 
public class Admin {
 /*   private static Connection connect()
    {  
      try
      {
         Class.forName("com.mysql.jdbc.Driver");
         return DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory","root","manoj");
      }
      catch(Exception ex) { return null;}
    }
    in oracle we have nested table - dynamic & varray - static , in oracle we can design
    own datatype also, that is called object. 
    object - it is a kind of user define composite data type, like structure in C & C++, 
             if like class in Java. Types of objects 3 - Types
    1. column object - when object contain single column of a table. 
    2. row object - when object contain the whole row of table. 
    3. ref object - when object contain the ref of a object from another table row object.
         tblflats - row objects,  emp table - flat ref
    
    how to create object - 
    create type address as object
    (
       hno number(4),
       sector varchar(20),
       street varchar(20), 
       city varchar(50),
       state varchar(30),
       pin varchar(6)
    ); 
    -- to create table with column object
    create table tblperson
    (
       id number(4), 
       name varchar(20), 
       addr address
    );
    insert into tblPerson values(101,'Farhan',address(420,'Sector-20','Vijay Nagar','GZB','U.P.','2021001'));
    select id,name,e.hno, e.street,e.city,e.state,e.pin from tblperson e; 
    select * from tblEmp; - as object mode. 
    -- table design with row object. 
    create table tblflats of address;
    -- ref object 
    create table tblEmp 
    (
      empno number(4) primary key,
      ename varchar(20),
      sal number(7,2),
      addr ref address scope is flats   -- addr is the ref object of address type, scope of record is fetch from flats table. 
    );
   
 */
    private static Connection connect() throws Exception
    {
      //Class.forName("com.mysql.jdbc.Driver");
      return DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory","root","shivam");
    }
    public static ResultSet getPerson(String id) throws Exception
    {
        return connect().createStatement().executeQuery("select * from Person where id ="+id);
    }
    public static String addPerson(String name,String photo)
    {
       try
       {
          PreparedStatement s = connect().prepareStatement("insert into person(name,photo) values(?,?)");
          s.setString(1,name);
          FileInputStream r = new FileInputStream(photo);
          s.setBinaryStream(2, r);
          s.execute();
          return "Person Record Successfully Inserted.....";
       }catch(Exception ex){ return "Exception is - "+ex; }
    }
    public static String checkId(String userId,String pass)
    {
       try
       {
          String str = String.format("select wmode from tblLogin where userid='%s' and binary pass='%s'",userId,pass);
          System.out.println(str);  
          ResultSet rs = connect().createStatement().executeQuery(str);
          if(rs.next())
              return rs.getString(1); // return wmode - admin / Emp
          else 
              return null;
       }catch(Exception ex) { return null;}
    }
    public static String addSalesman(String name,String mob,String mailid,String dob,String company,String addr)
    {
       try
       {
          String str = String.format("insert into tblSalesman(name,mob,mailid,dob,company,addr,dor) values('%s','+91-%s','%s','%s','%s','%s',curdate())",name,mob,mailid,dob,company,addr);
          Statement s = connect().createStatement();
          int x = s.executeUpdate(str);
          if(x==1)
              return "Salesman Record Successfully Inserted.....";
          else 
              return "Sorry Salesman record not inserted.";
       }catch(Exception ex){ return "Exception is - "+ex; }
    }
    public static ResultSet getSalesmanId() throws Exception
    {
        return connect().createStatement().executeQuery("select id from tblSalesman order by 1");
    }
    public static ResultSet getProductId() throws Exception
    {
        return connect().createStatement().executeQuery("select id from tblPurchase order by 1");
    }
    public static ResultSet getPurchase(String id) throws Exception
    {
        return connect().createStatement().executeQuery("select * from tblPurchase where id = "+id);
    }
    public static ResultSet getSalesmanName(String id) throws Exception
    {
        return connect().createStatement().executeQuery("select name from tblSalesman where id ="+id);
    }
    public static ResultSet getSalesman(String id) throws Exception
    {
        return connect().createStatement().executeQuery("select * from tblSalesman where id ="+id);
    }
    
    public static String updSalesman(String id,String name,String mob,String mailid,String dob,String company,String addr)
    {
       try
       {
          String str = String.format("update tblSalesman set name='%s',mob='%s',mailid='%s',dob='%s',company='%s',addr='%s' where id=%s",name,mob,mailid,dob,company,addr,id);
          Statement s = connect().createStatement();
          int x = s.executeUpdate(str);
          if(x==1)
              return "Salesman Record Successfully Updated.....";
          else 
              return "Sorry Salesman record not Updated.";
       }catch(Exception ex){ return "Exception is - "+ex; }
    }
    public static String delSalesman(String id)
    {
       try
       {
          String str = String.format("delete from tblSalesman where id=%s",id);
          Statement s = connect().createStatement();
          int x = s.executeUpdate(str);
          if(x==1)
              return "Salesman Record Successfully Deleted.....";
          else 
              return "Sorry Salesman record not Found.";
       }catch(Exception ex){ return "Exception is - "+ex; }
    }
    public static String addPurchase(String name,String descp,String sid,String qty,String mrp,String price,String mfg,String exp)
    {
       try
       {
          String str = String.format("insert into tblPurchase(name,descp,salesman,qty,mrp,price,mfgdate,expdate,purdate) values('%s','%s',%s,%s,%s,%s,'%s','%s',now())",name,descp,sid,qty,mrp,price,mfg,exp);
          System.out.println(str);
          Statement s = connect().createStatement();
          int x = s.executeUpdate(str); // Purchase table me record insert kiya.
          if(x==1)
          {
              ResultSet rs = s.executeQuery("select * from tblMaster where name= '"+name+"'"); // check item by name in master table  
              if(rs.next())//purchase item already exist in master table - update in master table.
              {
                 str = "Update tblMaster set qty = qty+"+qty+",price="+price+",mrp="+mrp+" where name= '"+name+"'";
                 System.out.println(str);
                 s.executeUpdate(str);
              }    
              else // if record not exist in master table - insert into master table.
              {
                 str = String.format("insert into tblMaster(name,descp,qty,mrp,price,mfgdate,expdate) values('%s','%s',%s,%s,%s,'%s','%s')",name,descp,qty,mrp,price,mfg,exp);
                 System.out.println(str);
                 s.execute(str);
              }
              return "Product Successfully Purchased...";
          }
          else 
              return "Sorry Purchase record not inserted.";
       }catch(Exception ex){ return "Exception is - "+ex; }
    }
    public static String updPurchase(String id,String name,int qty,String mrp,String price,String mfg,String exp)
    {
       try
       {
          String str = String.format("update tblPurchase set qty=qty+%d,mrp=%s,price=%s,mfgdate='%s',expdate='%s' where id=%s",qty,mrp,price,mfg,exp,id);
          System.out.println(str);
          Statement s = connect().createStatement();
          int x = s.executeUpdate(str); // Purchase table me record insert kiya.
          if(x==1)
          {
             str = "Update tblMaster set qty = qty+"+qty+",price="+price+",mrp="+mrp+" where name= '"+name+"'";
             System.out.println(str);
             s.executeUpdate(str);
             return "Purchase Successfully Updated.";
          }    
          else 
              return "Sorry Purchase record not inserted.";
       }catch(Exception ex){ return "Exception is - "+ex; }
    }
   
    public static String addCustomer(String name,String mobile,String mailid,String dob,String addr)
    {
       try
       {
          String str = String.format("insert into tblCustomer(name,mobile,mailid,dob,addr,dor) values('%s','%s','%s','%s','%s',now())",name,mobile,mailid,dob,addr);
          Statement s = connect().createStatement();
          s.executeUpdate(str);
          return "Customer Record Successfully Inserted.....";
       }catch(Exception ex){ return "Exception is - "+ex; }
    }
    public static ResultSet getCustomerId() throws Exception
    {
        return connect().createStatement().executeQuery("select concat(id,' - ',name) from tblCustomer order by id");
    }
    public static ResultSet getCustomer(String id) throws Exception
    {
        return connect().createStatement().executeQuery("select * from tblCustomer where id = "+id);
    }
    public static String updCustomer(String id,String name,String mobile,String mailid,String dob,String addr)
    {
       try
       {
          String str = String.format("update tblCustomer set name='%s',mobile='%s',mailid='%s',dob='%s',addr='%s' where id=%s",name,mobile,mailid,dob,addr,id);
          Statement s = connect().createStatement();
          int x = s.executeUpdate(str);
          if(x==1)
            return "Customer Record Successfully Updated.....";
          else
            return "Customer Record not Updated.....";
              
       }catch(Exception ex){ return "Exception is - "+ex; }
    }
    public static String delCustomer(String id)
    {
       try
       {
          String str = String.format("delete from tblCustomer where id=%s",id);
          Statement s = connect().createStatement();
          int x = s.executeUpdate(str);
          if(x==1)
            return "Customer Record Successfully Deleted.....";
          else
            return "Customer Record not found.....";
              
       }catch(Exception ex){ return "Exception is - "+ex; }
    }
    // ***  Sales Form ki Kahani ***
    public static ResultSet getSalesProdId() throws Exception
    {
        return connect().createStatement().executeQuery("select id,name from tblMaster order by 1");
    }
    public static ResultSet getSalesProductDetails(String id) throws Exception
    {
        return connect().createStatement().executeQuery("select descp,qty,price+price*.15 as price from tblMaster where id="+id);
    }
    public static ResultSet getSalesCustId() throws Exception
    {
        return connect().createStatement().executeQuery("select id,name from tblCustomer order by 1");
    }
    public static ResultSet getSalesCustDetails(String id) throws Exception
    {
        return connect().createStatement().executeQuery("select mobile,addr from tblCustomer where id="+id);
    }
    public static String addSales(String prid, String prname,String prdesc,String qty,String price,String total,String custdetails,String paidamount)
    {
       try
       {
          String str = String.format("insert into tblSales(prid,prname,prdesc,qty,price,total,custdetails,saledate,paidamount) values(%s,'%s','%s',%s,%s,%s,'%s',now(),%s)",prid,prname,prdesc,qty,price,total,custdetails,paidamount);
           System.out.println(str);
          Statement s = connect().createStatement();
          int x = s.executeUpdate(str);
          if(x==1) // if record insert in Sales table
          {
              str = String.format("update tblMaster set qty = qty-%s where id = %s",qty,prid);
              s.execute(str);
          }
          return "Product Order Successfully Placed......";
       }catch(Exception ex){ return "Exception is - "+ex; }
    }
    public static String updSales(String sid,String prid, String prname,String prdesc,String qty,int masterqty,String price,String total,String custdetails,String paidamount)
    {
       try
       {
          String str = String.format("update tblSales set prid='%s',prname='%s',prdesc='%s',qty=%s,price=%s,total=%s,custdetails='%s',paidamount=%s, saledate=now() where id=%s",prid,prname,prdesc,qty,price,total,custdetails,paidamount,sid);
          System.out.println(str);
          Statement s = connect().createStatement();
          int x = s.executeUpdate(str);
          if(x==1) // if record insert in Sales table
          {
              System.out.println(masterqty);
              str = String.format("update tblMaster set qty = qty+%s where id = %s",masterqty,prid);
              s.execute(str);
          }
          return "Product Order Successfully Updated......";
       }catch(Exception ex){ return "Exception is - "+ex; }
    }
   
    public static ResultSet getSalesId() throws Exception
    {
        return connect().createStatement().executeQuery("select id from tblSales order by 1");
    }
    public static ResultSet getSales(String sid) throws Exception
    {
        return connect().createStatement().executeQuery("select * from tblSales where id = "+sid);
    }
    public static String delSales(String sid,String prid,String masterqty)
    {
       try
       {
          String str = String.format("delete from tblSales where id=%s",sid);
          System.out.println(str);
          Statement s = connect().createStatement();
          int x = s.executeUpdate(str);
          if(x==1) // if record insert in Sales table
          {
              String ar[] = prid.split("-");
              str = String.format("update tblMaster set qty = qty+%s where id = %s",masterqty,ar[0]);
              s.execute(str);
          }
          return "Product Order Successfully Deleted......";
       }catch(Exception ex){ return "Exception is - "+ex; }
    }
    // ******  Customer Report *******
    public static ResultSet getCustomerReport() throws Exception
    {
        return connect().createStatement().executeQuery("select * from tblCustomer order by 1");
    }
    public static ResultSet getSalesmanReport() throws Exception
    {
        return connect().createStatement().executeQuery("select * from tblSalesman order by 1");
    }
    public static ResultSet getSalesReport() throws Exception
    {
        return connect().createStatement().executeQuery("select * from tblSales order by 1");
    }
    public static ResultSet getPurchaseReport() throws Exception
    {
        return connect().createStatement().executeQuery("select * from tblPurchase order by 1");
    }
    public static ResultSet getMasterReport() throws Exception
    {
        return connect().createStatement().executeQuery("select * from tblMaster order by 1");
    }
    public static ResultSet getMasterRecord(String id) throws Exception
    {
        return connect().createStatement().executeQuery("select * from tblMaster where id = "+id);
    }
    public static ResultSet getMasterId() throws Exception
    {
        return connect().createStatement().executeQuery("select id from tblMaster order by 1");
    }
    public static ResultSet getPurchaseReport(String year) throws Exception
    {
        return connect().createStatement().executeQuery("select * from tblpurchase where DATE_FORMAT(purdate,'%m-%Y') like '%"+year+"'");
    }
    public static ResultSet getSalesReport(String year) throws Exception
    {
        return connect().createStatement().executeQuery("select * from tblSales where DATE_FORMAT(saledate,'%m-%Y') like '%"+year+"'");
    }
    
    
    
    
    
}
/*
SELECT * FROM tblmaster t;

SELECT * FROM tblpurchase t;

create table tblCustomer
(
   id int(4) primary key auto_Increment,
   name varchar(35),
   mobile varchar(14),
   mailid varchar(40),
   dob date,
   addr varchar(150),
   dor datetime
);
insert into tblCustomer(name,mobile,mailid,dob,addr,dor) values('Arif Chaudhary','+91-9211223344','arif123@gmail.com','1976-10-20','Hno - 23, Sector - 9, Vijay Nagar Ghaziabad - 201001, Near Water Tank',now());

select * from tblCustomer;


DROP TABLE IF EXISTS `inventory`.`tblsales`;
CREATE TABLE  `inventory`.`tblsales` (
  `id` int NOT NULL AUTO_INCREMENT,
  `prid` int DEFAULT NULL,
  `prName` varchar(30) DEFAULT NULL,
  `prdesc` varchar(50) DEFAULT NULL,
  `qty` int DEFAULT NULL,
  `price` decimal(7,2) DEFAULT NULL,
   total decimal(7,2),
  `custDetails` varchar(150) DEFAULT NULL,
  `saledate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);
*/