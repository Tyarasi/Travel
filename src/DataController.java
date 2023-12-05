/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DataDAO;
import model.Jadwal;

/**
 *
 *
 * @author Kelompok5
 */
public class DataController {
    
    ObservableList<String> statusList = FXCollections.observableArrayList("Tersedia", "Penuh");
    @FXML
    private Main main;
    @FXML
    private TextField jadIdText;
    @FXML
    private TextArea resultArea;
    @FXML
    private ChoiceBox newStatusText;
    @FXML
    private TextField lokasiText;
    @FXML
    private DatePicker dateText;
    @FXML
    private TextField jamText;
    @FXML
    private TextField ruteText;
    @FXML
    private ChoiceBox statusText;
    @FXML
    private TableView jadwalTable;
    @FXML
    private TableColumn<Jadwal, Integer> jadIdColumn;
    @FXML
    private TableColumn<Jadwal, String> jadLokasiColumn;
    @FXML
    private TableColumn<Jadwal, String> jadDateColumn;
    @FXML
    private TableColumn<Jadwal, String> jadJamColumn;
    @FXML
    private TableColumn<Jadwal, String> jadRuteColumn;
    @FXML
    private TableColumn<Jadwal, String> jadStatusColumn;

    @FXML
    private void statusdata() {
        statusText.setValue("Tersedia");
        statusText.setItems(statusList);
    }
    
    @FXML
    private void statusdatanew() {
        newStatusText.setValue("Tersedia");
        newStatusText.setItems(statusList);
    }

    //For MultiThreading
    private Executor exec;

    //Initializing the controller class.
    //This method is automatically called after the fxml file has been loaded.
    @FXML
    private void initialize() {
        /*
        The setCellValueFactory(...) that we set on the table columns are used to determine
        which field inside the Jadwal objects should be used for the particular column.
        The arrow -> indicates that we're using a Java 8 feature called Lambdas.
        (Another option would be to use a PropertyValueFactory, but this is not type-safe

        We're only using StringProperty values for our table columns in this example.
        When you want to use IntegerProperty or DoubleProperty, the setCellValueFactory(...)
        must have an additional asObject():
         */

        //For multithreading: Create executor that uses daemon threads:
        exec = Executors.newCachedThreadPool((runnable) -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });

        jadIdColumn.setCellValueFactory(cellData -> cellData.getValue().idJadwalProperty().asObject());
        jadLokasiColumn.setCellValueFactory(cellData -> cellData.getValue().lokasiProperty());
        jadDateColumn.setCellValueFactory(cellData -> cellData.getValue().tanggalBerangkatProperty());
        jadJamColumn.setCellValueFactory(cellData -> cellData.getValue().jamProperty());
        jadRuteColumn.setCellValueFactory(cellData -> cellData.getValue().ruteProperty());
        jadStatusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
    }

    //Search an Jadwal
    @FXML
    private void searchJadwal(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            //Get Jadwal information
            Jadwal jad = DataDAO.searchJadwal(jadIdText.getText());
            //Populate Jadwal on TableView and Display on TextArea
            populateAndShowJadwal(jad);
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Error occurred while getting jadwal information from DB.\n" + e);
            throw e;
        }
    }

    //Search all jadwals
    @FXML
    private void searchJadwals(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Jadwals information
            ObservableList<Jadwal> jadData = DataDAO.searchJadwals();
            //Populate jadwals on TableView
            populateJadwals(jadData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting jadwals information from DB.\n" + e);
            throw e;
        }
    }

    //Populate Jadwals for TableView with MultiThreading (This is for example usage)
    private void fillJadwalTable(ActionEvent event) throws SQLException, ClassNotFoundException {
        Task<List<Jadwal>> task = new Task<List<Jadwal>>() {
            @Override
            public ObservableList<Jadwal> call() throws Exception {
                return DataDAO.searchJadwals();
            }
        };

        task.setOnFailed(e -> task.getException().printStackTrace());
        task.setOnSucceeded(e -> jadwalTable.setItems((ObservableList<Jadwal>) task.getValue()));
        exec.execute(task);
    }

    //Populate Jadwal
    @FXML
    private void populateJadwal(Jadwal jad) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<Jadwal> jadData = FXCollections.observableArrayList();
        //Add jadwal to the ObservableList
        jadData.add(jad);
        //Set items to the jadwalTable
        jadwalTable.setItems(jadData);
    }

    //Set Jadwal information to Text Area
    @FXML
    private void setJadInfoToTextArea(Jadwal jad) {
        resultArea.setText(jad.getLokasi());
    }

    //Populate Jadwal for TableView and Display Jadwal on TextArea
    @FXML
    private void populateAndShowJadwal(Jadwal jad) throws ClassNotFoundException {
        if (jad != null) {
            populateJadwal(jad);
            setJadInfoToTextArea(jad);
        } else {
            resultArea.setText("Jadwal Ini tidak tersedia!\n");
        }
    }

    //Populate Jadwals for TableView
    @FXML
    private void populateJadwals(ObservableList<Jadwal> jadData) throws ClassNotFoundException {
        //Set items to the jadwalTable
        jadwalTable.setItems(jadData);
    }

    //Update jadwal's email with the email which is written on newEmailText field
    @FXML
    private void updateJadwalStatus(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            DataDAO.updateJadStatus(jadIdText.getText(), newStatusText.getValue().toString());
            resultArea.setText("Lokasi has been updated for, jadwal id: " + jadIdText.getText() + "\n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while updating email: " + e);
        }
    }

    //Insert an jadwal to the DB
    @FXML
    private void insertJadwal(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            DataDAO.insertJad(lokasiText.getText(), dateText.getValue().toString(), jamText.getText(), ruteText.getText(), statusText.getValue().toString());
            resultArea.setText("Jadwal inserted! \n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while inserting jadwal " + e);
            throw e;
        }
    }

    //Delete an jadwal with a given jadwal Id from DB
    @FXML
    private void deleteJadwal(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            DataDAO.deleteJadWithId(jadIdText.getText());
            resultArea.setText("Jadwal deleted! Jadwal id: " + jadIdText.getText() + "\n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while deleting jadwal " + e);
            throw e;
        }
    }

    @FXML
    private void home() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Anda Berhasil LogOut!!");
        alert.showAndWait();
        main.showBackHome();
    }
    
    @FXML
    private void gamenmusic() throws IOException {
        main.showMusik();
    }
    
}
