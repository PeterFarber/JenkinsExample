package com.peterfarber.dao;

import com.peterfarber.pojo.Attachment;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AttachmentDao implements  Dao <Attachment> {

    @Override
    public void create(Attachment object) {

    }

    @Override
    public Attachment retrieveByString(String id) {
        return null;
    }

    @Override
    public List<Attachment> retrieveAll() {
        return null;
    }

    @Override
    public void update(Attachment object) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void createPreparedStmt(Attachment object) {

        String sql = "INSERT INTO ATTACHMENT (ATTACHMENT_TYPE_ID, ATTACHMENT_PATH, ATTACHMENT_DESCRIPTION, ATTACHMENT_APPLICATION_ID) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
            ps.setInt(1, object.getType());
            ps.setString(2, object.getPath());
            ps.setString(3, object.getDescription());
            ps.setInt(4, object.getApplication_ID());


            int rs = ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
