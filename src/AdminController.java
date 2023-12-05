/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Admin;
import model.AdminDAO;
import model.Jadwal;

/**
 *
 * @author kelompok5
 */
public class AdminController {

    private Main main;
    @FXML
    private Button homeBtn;
    @FXML
    private TextField usernameText;
    @FXML
    private TextField passwordText;
    @FXML
    private Button masukBtn;

    //Menuju Menu Login Admin
    @FXML
    private void home() throws IOException {
        main.showBackHome();
    }

    //method untuk mencari admin berdasarkan db
    @FXML
    private void loginAdmin(ActionEvent actionEvent) throws ClassNotFoundException, SQLException, IOException {
        try {
            Admin adm = AdminDAO.searchAdmin(usernameText.getText(), passwordText.getText());
            showDataJadwal(adm);

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    //menampilkan data Jadwal di TextArea
    @FXML
    private void showDataJadwal(Admin adm) throws ClassNotFoundException, IOException {
        if (adm != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Selamat Anda Berhasil Login");
            alert.showAndWait();
            main.showDataJadwal();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Password Atau Username Anda Salah");
            alert.showAndWait();
        }
    }
}