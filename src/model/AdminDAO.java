/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import util.DBUtil;

/**
 *
 * @author kelompok5
 */
public class AdminDAO {
    //*******************************
    //SELECT an JadwalS
    //*******************************

    public static Admin searchAdmin(String username, String password) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM admin WHERE username='" + username + "' AND password='" + password + "'";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsAdmin = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            Admin admin = getAdminFromResultSet(rsAdmin);

            //Return Jadwal object
            return admin;
        } catch (SQLException e) {
            System.out.println("While searching an admin with " + username + " , an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    //Use ResultSet from DB as parameter and set Admin Object's attributes and return admin object.
    private static Admin getAdminFromResultSet(ResultSet rs) throws SQLException {
        Admin adm = null;
        if (rs.next()) {
            adm = new Admin();
            adm.setIdAdmin(rs.getInt("id_admin"));
            adm.setUsername(rs.getString("username"));
            adm.setPassword(rs.getString("password"));
        }
        return adm;
    }

}
