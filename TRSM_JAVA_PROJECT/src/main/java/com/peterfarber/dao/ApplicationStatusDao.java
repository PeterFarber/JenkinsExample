package com.peterfarber.dao;

import com.peterfarber.pojo.ApplicationStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationStatusDao implements Dao <ApplicationStatus>{
    @Override
    public void create(ApplicationStatus object) {

    }

    @Override
    public ApplicationStatus retrieveByString(String id) {
        ApplicationStatus applicationStatus = null;
        String sql = "SELECT * FROM APPLICATION_STATUS WHERE APPLICATION_STATUS_ID = ?";
        try {
            PreparedStatement ps = ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            //Get First Result
            rs.next();

            //Create new string of position
            applicationStatus.setId(rs.getInt(1));
            applicationStatus.setName(rs.getString(2));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Return the position!
        return applicationStatus;
    }

    @Override
    public List<ApplicationStatus> retrieveAll() {
        List<ApplicationStatus> applicationStatuses = new ArrayList<ApplicationStatus>();
        String sql = "SELECT * FROM APPLICATION_STATUS";
        try {
            PreparedStatement ps = ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            //Get First Result
            while(rs.next()) {
                ApplicationStatus applicationStatus = new ApplicationStatus();
                //Create new string of position
                applicationStatus.setId(rs.getInt(1));
                applicationStatus.setName(rs.getString(2));

                applicationStatuses.add(applicationStatus);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Return the position!
        return applicationStatuses;
    }

    @Override
    public void update(ApplicationStatus object) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void createPreparedStmt(ApplicationStatus object) {

    }
}
