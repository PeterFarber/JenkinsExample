package com.peterfarber.dao;

import com.peterfarber.pojo.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao<User> {

    @Override
    public void create(User object){}

    @Override
    public User retrieveByString(String username){
        User object = null;
        String sql = "SELECT * FROM USERS WHERE USERS_USERNAME = ?";
        try {
            PreparedStatement ps = ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            //Get First Result

            while(rs.next()){
                //Instantiate new User Object
                object = new User();

                //Set the values of the new user object!
                object.setId(rs.getInt(1));
                object.setFirstName(rs.getString(2));
                object.setLastName(rs.getString(3));
                object.setUsername(rs.getString(4));
                object.setPassword(rs.getString(5));
                object.setEmail(rs.getString(6));

                object.setPosition(rs.getInt(7));
                object.setDepartment(rs.getInt(8));

                object.setSupervisor_ID(rs.getInt(9));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Return the User!
        return object;
    }

    public User retrieveById(int id){
        User object = null;
        String sql = "SELECT * FROM USERS WHERE USERS_ID = ?";
        try {
            PreparedStatement ps = ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            //Get First Result

            while(rs.next()){
                //Instantiate new User Object
                object = new User();

                //Set the values of the new user object!
                object.setId(rs.getInt(1));
                object.setFirstName(rs.getString(2));
                object.setLastName(rs.getString(3));
                object.setUsername(rs.getString(4));
                object.setPassword(rs.getString(5));
                object.setEmail(rs.getString(6));

                object.setPosition(rs.getInt(7));
                object.setDepartment(rs.getInt(8));

                object.setSupervisor_ID(rs.getInt(9));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Return the User!
        return object;
    }


    @Override
    public List<User> retrieveAll(){
        return null;
    }

    public List<User> retrieveAllBySupervisorID(int id){
        List<User> objects = new ArrayList<User>();

        String sql = "SELECT * FROM USERS WHERE USERS_SUPERVISOR_ID = ?";
        try {
            PreparedStatement ps = ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            //Get First Result

            while(rs.next()){
                //Instantiate new User Object
                User object = new User();

                //Set the values of the new user object!
                object.setId(rs.getInt(1));
                object.setFirstName(rs.getString(2));
                object.setLastName(rs.getString(3));
                object.setUsername(rs.getString(4));
                object.setPassword(rs.getString(5));
                object.setEmail(rs.getString(6));

                object.setPosition(rs.getInt(7));
                object.setDepartment(rs.getInt(8));

                objects.add(object);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Return the Applications!
        return objects;
    }

    @Override
    public void update(User object){
        String sql = "UPDATE USERS SET USERS_FIRSTNAME = ?, USERS_LASTNAME = ?, USERS_PASSWORD = ?, USERS_EMAIL = ? WHERE USERS_ID = ?";
        try {
            PreparedStatement ps = ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, object.getFirstName());
            ps.setString(2, object.getLastName());
            ps.setString(3, object.getPassword());
            ps.setString(4, object.getEmail());
            ps.setInt(5, object.getId());

            int rs = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id){}

    @Override
    public void createPreparedStmt(User object){}


}
