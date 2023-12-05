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
public class KontakController {
    
     private Main main;
     
    //Menuju Menu Login Admin
    @FXML
    private void home() throws IOException {
        main.showBackHome();
    }
}
