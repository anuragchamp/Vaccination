package utils;

import Entity.User;
import Entity.VaccineInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
           if(rs.next()){
               System.out.println(rs.getString(2));
               retuser.setId(rs.getInt(1));
               retuser.setFirstname(rs.getString(2));
               retuser.setLastname(rs.getString(3));
               retuser.setEmail(rs.getString(4));
               retuser.setPassword(rs.getString(5));
               retuser.setPhonenumber(rs.getInt(6));
           }
           else{
               return null;
           }
            connection.close();

        }
        catch (Exception e){

            System.out.println(e);
            return null;
        }
        finally {

        }

        return retuser;
    }



    public List<VaccineInfo> getVaccineInfo(int id){

        PreparedStatement ps = null;

        System.out.println(id);

        ArrayList<VaccineInfo> list = null;
        try {
            ps = connection.prepareStatement("select * from vaccineinfo where userid = ?");
            ps.setInt(1, id);

             ResultSet rs = ps.executeQuery();

            list = new ArrayList<>();


            while(rs.next()){
                VaccineInfo vaccineInfo = new VaccineInfo();
                vaccineInfo.setId(rs.getInt(1));
                vaccineInfo.setUserid(rs.getInt(2));
                vaccineInfo.setTimestamp(rs.getTimestamp(4));
                vaccineInfo.setDoseName(rs.getString(3));
                vaccineInfo.setDoseNo(rs.getInt(5));
                list.add(vaccineInfo);
            }
            connection.close();


        }
        catch (Exception e){
            System.out.println(e);
        }

        return list;


        }



}
