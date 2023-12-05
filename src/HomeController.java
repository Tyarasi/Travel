/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Jadwal;
import model.JadwalDAO;

/**
 *
 *
 * @author Kelompok5
 */
public class HomeController {

    private Main main;
    @FXML
    private TextField lokasiText;
    @FXML
    private TextArea resultArea;
    @FXML
    private Button loginBtn;

    //Mencari jadwal
    @FXML
    private void searchJadwal(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            //Get Jadwal information
            Jadwal jad = JadwalDAO.searchJadwal(lokasiText.getText());
            //Display on TextArea
            showJadwal(jad);
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Data belum tersedia DB.\n" + e);
            throw e;
        }
    }

    //Menset jadwal menjadi Text Area
    @FXML
    private void setJadInfoToTextArea(Jadwal jad) {
        resultArea.setText("Rute : " + jad.getLokasi() + "\n" + "Tanggal : " + jad.getTanggalBerangkat() + " \n" + 
                "Jam : " + jad.getRute() + " \n" + "Lokasi Berangkat : " + jad.getJam() + "\n"
                + "Status : " + jad.getStatus());
    }

    //Menampilkan Jadwal di TextArea
    @FXML
    private void showJadwal(Jadwal jad) throws ClassNotFoundException {
        if (jad != null) {
            setJadInfoToTextArea(jad);
        } else {
            resultArea.setText("Lokasi ini belum tersedia!\n");
        }
    }
    
    //Menuju home
    @FXML
    private void home() throws IOException {
        main.showBackHome();
    }

}
