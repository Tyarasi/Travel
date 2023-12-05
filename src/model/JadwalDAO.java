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
public class JadwalDAO {

    //*******************************
    //SELECT an JadwalS
    //*******************************
    public static Jadwal searchJadwal(String lokasi) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM jadwal WHERE lokasi='" + lokasi + "'";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsJadwal = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            Jadwal jadwal = getJadwalFromResultSet(rsJadwal);

            //Return Jadwal object
            return jadwal;
        } catch (SQLException e) {
            System.out.println("While searching an jadwal with " + lokasi + " , an error occurred: " + e);
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
            jad.setJam(rs.getString("jam"));
            jad.setTanggalBerangkat(rs.getString("tanggal_berangkat"));
            jad.setRute(rs.getString("rute"));
            jad.setStatus(rs.getString("status"));
        }
        return jad;
    }
}
