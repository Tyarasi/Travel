/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import javafx.fxml.FXML;
/**
 *
 * @author LENOVO
 */
public class AwalController {

    private Main main;

    @FXML
    private void login() throws IOException {
        main.showLoginAdmin();
    }
    
    @FXML
    private void showberangkat() throws IOException {
        main.showHome();
    }
    
     @FXML
    private void showkontak() throws IOException {
        main.showKontak();
    }
}

