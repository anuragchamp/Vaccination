package utils;

import Entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionToDataBase {

    public  Connection connection;


    public void setConnection(){

        try{
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vaccination","root","qwerty10");

            if(connection == null){
                System.out.println("not connected");
            }
            else{
                System.out.println("connected");
            }

        }
        catch (Exception e){

        }

    }


    public int insertUser(User user){
        PreparedStatement ps = null;
        int retvalue = 0;
        try{
           ps = connection.prepareStatement("insert into users(firstname , lastname , useremail , phonenumber , userpassword) values(?,?,?,?,?)");
           ps.setString(1 , user.getFirstname() );
           ps.setString(2 , user.getLastname());
           ps.setString(3 , user.getEmail());
           ps.setLong(4 , user.getPhonenumber());
           ps.setString(5 , user.getPassword());
           retvalue = ps.executeUpdate();
            connection.close();

        }
        catch (Exception e){
            System.out.println(e);
        }



        return retvalue;
    }

    public User getUser(User user){
        PreparedStatement ps = null;

        System.out.println(user);
        User retuser = new User();
        try{
            ps = connection.prepareStatement("select * from users where useremail = ? and userpassword = ?");
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());

           ResultSet rs =  ps.executeQuery();

           rs.next();

           if(rs != null){
               System.out.println(rs.getString(2));
               retuser.setFirstname(rs.getString(2));
               retuser.setLastname(rs.getString(3));
               retuser.setEmail(rs.getString(4));
               retuser.setPassword(rs.getString(5));
               retuser.setPhonenumber(rs.getInt(6));
           }
           else{
               return null;
           }

        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
        return retuser;
    }



}
