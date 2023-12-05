/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author kelompok5
 */
public class Jadwal {

    //Declare Employees Table Columns
    private final IntegerProperty id_jadwal;
    private final IntegerProperty id_bus;
    private final StringProperty lokasi;
    private final StringProperty tanggal_berangkat;
    private final StringProperty jam;
    private final StringProperty rute;
    private final StringProperty status;

    //Constructor
    public Jadwal() {
        this.id_jadwal = new SimpleIntegerProperty();
        this.id_bus = new SimpleIntegerProperty();
        this.lokasi = new SimpleStringProperty();
        this.tanggal_berangkat = new SimpleStringProperty();
        this.jam = new SimpleStringProperty();
        this.rute = new SimpleStringProperty();
        this.status = new SimpleStringProperty();

    }

    //id_jadwal
    public int getIdJadwal() {
        return id_jadwal.get();
    }

    public void setIdJadwal(int id_jadwal) {
        this.id_jadwal.set(id_jadwal);
    }

    public IntegerProperty idJadwalProperty() {
        return id_jadwal;
    }

    //id_bus
    public int getIdBus() {
        return id_bus.get();
    }

    public void setIdBus(int id_bus) {
        this.id_bus.set(id_bus);
    }

    public IntegerProperty idBusProperty() {
        return id_bus;
    }

    //lokasi
    public String getLokasi() {
        return lokasi.get();
    }

    public void setLokasi(String lokasi) {
        this.lokasi.set(lokasi);
    }

    public StringProperty lokasiProperty() {
        return lokasi;
    }

    //tanggal_berangkat
    public Object getTanggalBerangkat() {
        return tanggal_berangkat.get();
    }

    public void setTanggalBerangkat(String tanggal_berangkat) {
        this.tanggal_berangkat.set(tanggal_berangkat);
    }

    public StringProperty tanggalBerangkatProperty() {
        return tanggal_berangkat;
    }

    //rute
    public String getJam() {
        return jam.get();
    }

    public void setJam(String jam) {
        this.jam.set(jam);
    }

    public StringProperty jamProperty() {
        return jam;
    }

    //rute
    public String getRute() {
        return rute.get();
    }

    public void setRute(String rute) {
        this.rute.set(rute);
    }

    public StringProperty ruteProperty() {
        return rute;
    }

    //status
    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public StringProperty statusProperty() {
        return status;
    }
}
