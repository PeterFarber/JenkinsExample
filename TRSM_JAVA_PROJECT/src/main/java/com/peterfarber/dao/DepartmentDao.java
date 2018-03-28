package com.peterfarber.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepartmentDao implements Dao<String>{

    @Override
    public void create(String object) {

    }

    @Override
    public String retrieveByString(String id) {
        String position = null;
        String sql = "SELECT * FROM DEPARTMENT WHERE DEPARTMENT_ID = ?";
        try {
            PreparedStatement ps = ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            //Get First Result
            rs.next();

            //Create new string of position
            position = rs.getString("DEPARTMENT_NAME");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Return the position!
        return position;
    }

    @Override
    public List<String> retrieveAll() {
        return null;
    }

    @Override
    public void update(String object) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void createPreparedStmt(String object) {

    }
}
