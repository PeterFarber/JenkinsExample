package com.peterfarber.dao;


import com.peterfarber.pojo.GradingFormat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GradingFormatDao implements Dao<GradingFormat>{


    @Override
    public void create(GradingFormat object) {

    }

    @Override
    public GradingFormat retrieveByString(String id) {
        GradingFormat gradingFormat = null;
        String sql = "SELECT * FROM GRADING_FORMAT WHERE GRADING_FORMAT_ID = ?";
        try {
            PreparedStatement ps = ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            //Get First Result
            rs.next();

            //Create new string of position
            gradingFormat.setId(rs.getInt(1));
            gradingFormat.setName(rs.getString(2));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Return the position!
        return gradingFormat;
    }

    @Override
    public List<GradingFormat> retrieveAll() {
        List<GradingFormat> gradingFormats = new ArrayList<GradingFormat>();
        String sql = "SELECT * FROM GRADING_FORMAT";
        try {
            PreparedStatement ps = ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            //Get First Result
            while(rs.next()) {
                GradingFormat gradingFormat = new GradingFormat();
                //Create new string of position
                gradingFormat.setId(rs.getInt(1));
                gradingFormat.setName(rs.getString(2));

                gradingFormats.add(gradingFormat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Return the position!
        return gradingFormats;
    }

    @Override
    public void update(GradingFormat object) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void createPreparedStmt(GradingFormat object) {

    }
}
