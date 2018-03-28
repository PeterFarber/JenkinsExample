package com.peterfarber.dao;


import com.peterfarber.pojo.EventType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventTypeDao implements Dao<EventType>{

    @Override
    public void create(EventType object) {

    }

    @Override
    public EventType retrieveByString(String id) {
        return null;
    }

    @Override
    public List<EventType> retrieveAll() {
        List<EventType> eventTypes = new ArrayList<EventType>();
        String sql = "SELECT * FROM EVENT_TYPE";
        try {
            PreparedStatement ps = ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            //Get First Result
            while(rs.next()) {
                EventType eventType = new EventType();
                //Create new string of position
                eventType.setId(rs.getInt(1));
                eventType.setName(rs.getString(2));

                eventTypes.add(eventType);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Return the position!
        return eventTypes;
    }

    @Override
    public void update(EventType object) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void createPreparedStmt(EventType object) {

    }
}
