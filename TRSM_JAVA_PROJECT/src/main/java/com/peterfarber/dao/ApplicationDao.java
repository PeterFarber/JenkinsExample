package com.peterfarber.dao;

import com.peterfarber.pojo.Application;
import com.peterfarber.pojo.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApplicationDao implements  Dao <Application> {


    @Override
    public void create(Application object) {

    }

    @Override
    public Application retrieveByString(String id) {
        Application object = null;
        String sql = "SELECT * FROM APPLICATION WHERE APPLICATION_ID = ?";
        try {
            PreparedStatement ps = ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            //Get First Result

            while(rs.next()){
                //Instantiate new User Object
                object = new Application();

                //Set the values of the new user object!
                object.setId(rs.getInt(1));
                object.setStatus(rs.getInt(2));
                object.setTimestamp(rs.getString(3));
                object.setLocation(rs.getString(4));
                object.setDescription(rs.getString(5));
                object.setCost(rs.getDouble(6));
                object.setGradingFormat(rs.getInt(7));
                object.setEventType(rs.getInt(8));
                object.setJustification(rs.getString(9));
                object.setHoursMissed(rs.getInt(10));
                object.setGradeNeeded(rs.getInt(11));
                object.setFinalGrade(rs.getInt(12));
                object.setCreatedTimestamp(rs.getString(13));
                object.setUpdatedTimestamp(rs.getString(14));
                object.setEmployeeID(rs.getInt(15));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public List<Application> retrieveAll() {
        List<Application> objects = new ArrayList<Application>();

        String sql = "SELECT * FROM APPLICATION";
        try {
            PreparedStatement ps = ConnectionFactory.getInstance().getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                //Instantiate new User Object
                Application object = new Application();

                //Set the values of the new user object!
                object.setId(rs.getInt(1));
                object.setStatus(rs.getInt(2));
                object.setTimestamp(rs.getString(3).toString());
                object.setLocation(rs.getString(4));
                object.setDescription(rs.getString(5));
                object.setCost(rs.getDouble(6));
                object.setGradingFormat(rs.getInt(7));
                object.setEventType(rs.getInt(8));
                object.setJustification(rs.getString(9));
                object.setHoursMissed(rs.getInt(10));
                object.setGradeNeeded(rs.getInt(11));
                object.setFinalGrade(rs.getInt(12));
                object.setCreatedTimestamp(rs.getString(13).toString());
                object.setUpdatedTimestamp(rs.getString(14).toString());
                object.setEmployeeID(rs.getInt(15));

                UserDao userDao = new UserDao();
                User user = userDao.retrieveById(object.getEmployeeID());

                object.setEmployeeName(user.getFirstName() + " " + user.getLastName());

                objects.add(object);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Return the Applications!
        return objects;
    }

    public List<Application> retrieveAllByEmployeeId(int id) {
        List<Application> objects = new ArrayList<Application>();

        String sql = "SELECT * FROM APPLICATION WHERE APPLICATION_EMPLOYEE_ID = ?";
        try {
            PreparedStatement ps = ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                //Instantiate new User Object
                Application object = new Application();

                //Set the values of the new user object!
                object.setId(rs.getInt(1));
                object.setStatus(rs.getInt(2));
                object.setTimestamp(rs.getString(3).toString());
                object.setLocation(rs.getString(4));
                object.setDescription(rs.getString(5));
                object.setCost(rs.getDouble(6));
                object.setGradingFormat(rs.getInt(7));
                object.setEventType(rs.getInt(8));
                object.setJustification(rs.getString(9));
                object.setHoursMissed(rs.getInt(10));
                object.setGradeNeeded(rs.getInt(11));
                object.setFinalGrade(rs.getInt(12));
                object.setCreatedTimestamp(rs.getString(13).toString());
                object.setUpdatedTimestamp(rs.getString(14).toString());
                object.setEmployeeID(id);

                UserDao userDao = new UserDao();
                User user = userDao.retrieveById(id);

                object.setEmployeeName(user.getFirstName() + " " + user.getLastName());

                objects.add(object);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Return the Applications!
        return objects;
    }

    private void getResults(List<Application> objects, ResultSet rs) throws SQLException {

    }

    @Override
    public void update(Application object) {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        String sql = "UPDATE APPLICATION SET APPLICATION_STATUS = ?, APPLICATION_TIMESTAMP = ?, APPLICATION_LOCATION = ?, APPLICATION_DESCRIPTION = ?,APPLICATION_COST = ?, " +
                "APPLICATION_GRADING_FORMAT_ID = ?,APPLICATION_EVENT_TYPE_ID = ?,APPLICATION_JUSTIFICATION = ?,APPLICATION_HOURS_MISSED = ?,APPLICATION_GRADE_NEEDED = ?, " +
                "APPLICATION_FINAL_GRADE = ?,APPLICATION_CREATED = ?,APPLICATION_UPDATED = ?,APPLICATION_EMPLOYEE_ID = ? WHERE APPLICATION_ID = ?";
        try {
            PreparedStatement ps = ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1, object.getStatus());
            ps.setString(2, object.getTimestamp());
            ps.setString(3, object.getLocation());
            ps.setString(4, object.getDescription());
            ps.setDouble(5, object.getCost());
            ps.setInt(6, object.getGradingFormat());
            ps.setInt(7, object.getEventType());
            ps.setString(8, object.getJustification());
            ps.setInt(9, object.getHoursMissed());
            ps.setInt(10, object.getGradeNeeded());
            ps.setInt(11, object.getFinalGrade());
            ps.setString(12, object.getCreatedTimestamp());
            ps.setString(13, timestamp.toString());
            ps.setInt(14, object.getEmployeeID());

            ps.setInt(15, object.getId());


            int rs = ps.executeUpdate();

        }catch (SQLException e){

        }
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void createPreparedStmt(Application object) {
        List<Application> objects = new ArrayList<Application>();

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        String sql = "INSERT INTO APPLICATION (APPLICATION_STATUS, APPLICATION_TIMESTAMP, APPLICATION_LOCATION, APPLICATION_DESCRIPTION,APPLICATION_COST, " +
                "APPLICATION_GRADING_FORMAT_ID,APPLICATION_EVENT_TYPE_ID,APPLICATION_JUSTIFICATION,APPLICATION_HOURS_MISSED,APPLICATION_GRADE_NEEDED, " +
                "APPLICATION_FINAL_GRADE,APPLICATION_CREATED,APPLICATION_UPDATED,APPLICATION_EMPLOYEE_ID) VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1, object.getStatus());
            ps.setString(2, object.getTimestamp());
            ps.setString(3, object.getLocation());
            ps.setString(4, object.getDescription());
            ps.setDouble(5, object.getCost());
            ps.setInt(6, object.getGradingFormat());
            ps.setInt(7, object.getEventType());
            ps.setString(8, object.getJustification());
            ps.setInt(9, object.getHoursMissed());
            ps.setInt(10, object.getGradeNeeded());
            ps.setInt(11, object.getFinalGrade());
            ps.setString(12, timestamp.toString());
            ps.setString(13, timestamp.toString());
            ps.setInt(14, object.getEmployeeID());

            int rs = ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
