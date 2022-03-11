/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytask.taskdb;

import java.sql.*;

import mytask.entity.TaskEntity;

/**
 *
 * @author Eranga
 */
public class TaskDBClient {

    protected Connection connection() {
        Connection myConnection;
        try {
            Class.forName("org.sqlite.JDBC");
            myConnection = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/mytaskdb.db");
            //myConnection = DriverManager.getConnection("jdbc:mysql://221.121.145.117:3306/mpsv2gun_mpvs2", "mpsv2gun_qcav2", "]cuy*x&cU2jk");
            return myConnection;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public boolean postTask(TaskEntity taskEntity) {
        try {
            
            PreparedStatement prepStmt = this.connection()
                    .prepareStatement("INSERT INTO mt_tasks (task, isDone, createdAt, doneAt, createdBy) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            prepStmt.setString(1, taskEntity.task);
            prepStmt.setBoolean(2, taskEntity.isDone);
            prepStmt.setString(3, taskEntity.taskCreatedAt);
            prepStmt.setString(4, taskEntity.taskCompletedAt);
            prepStmt.setInt(5, taskEntity.createdBy);

            int affectedRows = prepStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Inserting failed, no rows affected.");
            }
            
            try(ResultSet generatedKeys = prepStmt.getGeneratedKeys()) {
                if(generatedKeys.next()) {
                    taskEntity.id = generatedKeys.getLong(1);
                }
            } catch(Exception ex) {
                 throw new SQLException("Creating user failed, no ID obtained.");
            }
            
            return true;
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
