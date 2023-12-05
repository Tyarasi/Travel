/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBUtil;

/**
 *
 * @author kelompok5
 */
public class DataDAO {

    //*******************************
    //SELECT an Jadwal
    //*******************************
    public static Jadwal searchJadwal(String id_jadwal) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM jadwal WHERE id_jadwal='" + id_jadwal + "'";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsJadwal = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getJadwalFromResultSet method and get jadwal object
            Jadwal jadwal = getJadwalFromResultSet(rsJadwal);

            //Return Jadwal object
            return jadwal;
        } catch (SQLException e) {
            System.out.println("While searching an jadwal with " + id_jadwal + " , an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    //Use ResultSet from DB as parameter and set Jadwal Object's attributes and return jadwal object.
    private static Jadwal getJadwalFromResultSet(ResultSet rs) throws SQLException {
        Jadwal jad = null;
        if (rs.next()) {
            jad = new Jadwal();
            jad.setIdJadwal(rs.getInt("id_jadwal"));
            jad.setIdBus(rs.getInt("id_travel"));
            jad.setLokasi(rs.getString("lokasi"));
            jad.setTanggalBerangkat(rs.getString("tanggal_berangkat"));
            jad.setJam(rs.getString("jam"));
            jad.setRute(rs.getString("rute"));
            jad.setStatus(rs.getString("status"));
        }
        return jad;
    }

    public static ObservableList<Jadwal> searchJadwals() throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM jadwal";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsJads = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getJadwalList method and get jadwal object
            ObservableList<Jadwal> jadList = getJadwalList(rsJads);

            //Return jadwal object
            return jadList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

    //Select * from jadwals operation
    private static ObservableList<Jadwal> getJadwalList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Jadwal objects
        ObservableList<Jadwal> jadList = FXCollections.observableArrayList();

        while (rs.next()) {
            Jadwal jad = new Jadwal();
            jad.setIdJadwal(rs.getInt("id_jadwal"));
            jad.setIdBus(rs.getInt("id_travel"));
            jad.setLokasi(rs.getString("lokasi"));
            jad.setTanggalBerangkat(rs.getString("tanggal_berangkat"));
            jad.setJam(rs.getString("jam"));
            jad.setRute(rs.getString("rute"));
            jad.setStatus(rs.getString("status"));

            //Add jadwal to the ObservableList
            jadList.add(jad);
        }
        //return jadList (ObservableList of Jadwals)
        return jadList;
    }

    //*************************************
    //UPDATE an jadwal's email address
    //*************************************
    public static void updateJadStatus(String id_jadwal, String jadStatus) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String updateStmt
                = "UPDATE jadwal "
                + "SET STATUS = '" + jadStatus + "' "
                + "WHERE id_jadwal = " + id_jadwal;

        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }

    //*************************************
    //DELETE an employee
    //*************************************
    public static void deleteJadWithId(String id_jadwal) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt
                = "DELETE FROM jadwal "
                + "WHERE id_jadwal =" + id_jadwal;

        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    //*************************************
    //INSERT an employee
    //*************************************
    public static void insertJad(String lokasi, String tanggal_berangkat, String rute, String jam, String status) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt
                = "INSERT INTO jadwal(lokasi,tanggal_berangkat,rute,jam,status ) "
                + "VALUES " + "('" + lokasi + "', '" + tanggal_berangkat + "', '" + rute + "', '" + jam + "', '" + status + ", " + "')";

        //Execute DELETE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
}
