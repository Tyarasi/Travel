/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.util.Duration;

/**
 *
 * @author kelompok5
 */
public class Main extends Application {

    private static Stage primaryStage;
    private static BorderPane home;
    private static GridPane home2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Main.primaryStage = primaryStage;
        Main.primaryStage.setTitle("Loket Travel Rumbai");

        showAwal();
    }

    public static void showAwal() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Awal.fxml"));
        home = (BorderPane) loader.load();
        Scene scene = new Scene(home);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showHome() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Home.fxml"));
            home = (BorderPane) loader.load();
            home.setStyle("-fx-background-image: url(\"/Image/kuning.jpg\");-fx-background-size:700 600;");
            Scene scene = new Scene(home);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
        }
    }

    public static void showLoginAdmin() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Admin.fxml"));
        home = (BorderPane) loader.load();
        home.setStyle("-fx-background-image: url(\"/Image/kuning.jpg\");-fx-background-size:700 600;");
        Scene scene = new Scene(home);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showBackHome() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Awal.fxml"));
        home = (BorderPane) loader.load();
        home.setStyle("-fx-background-image: url(\"/Image/kuning.jpg\");-fx-background-size:700 600;");
        Scene scene = new Scene(home);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    public static void showDataJadwal() throws IOException {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/DataJadwal.fxml"));
            BorderPane home = loader.load();
            Scene scene = new Scene(home);
            primaryStage.setScene(scene);
            primaryStage.show();
    }

    public static void showMusik() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/MusikGame.fxml"));
        home = (BorderPane) loader.load();
        Scene scene = new Scene(home);
        primaryStage.setResizable(false);
        primaryStage.setTitle("MUSIK");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    public static void showKontak() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Kontak.fxml"));
        home = (BorderPane) loader.load();
        home.setStyle("-fx-background-image: url(\"/Image/kuning.jpg\");-fx-background-size:700 600;");
        Scene scene = new Scene(home);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
