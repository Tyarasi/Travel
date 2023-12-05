/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author LENOVO
 */
public class MusikController implements Initializable {

    boolean firstTime = true;
    @FXML
    private ToggleButton playPause;
    @FXML
    private MediaView player;
    @FXML
    private Button prevButton, nextButton;
    @FXML
    private Slider volume;
    @FXML
    private ImageView albumArt;
    @FXML
    private Button browse;
    private ArrayList<Media> mediaFiles = new ArrayList();
    private int counter = -1;

    private Main main;

    //Game
    @FXML
    private Label numberToSearch;
    @FXML
    private FlowPane cardsArea;
    @FXML
    private Label cardsClicked;
    private int counter1 = 0;
    private String theNumber;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Memulai Musik
        Platform.runLater(() -> {
            playPause.requestFocus();
        });
        //Memulai Game
        starterCard();

    }
    
    //Melakukan Pause dan Play Musik
    @FXML
    private void playPauseClicked(ActionEvent event) {
        if (firstTime) {
            File file = null;
            Stage stage = (Stage) playPause.getScene().getWindow();
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter fileExtension = new FileChooser.ExtensionFilter("Audio files", "*.mp3", "*.wav");
            fileChooser.getExtensionFilters().add(fileExtension);
            file = fileChooser.showOpenDialog(stage);
            System.out.println(file);
            if (file != null) {

                Media media;
                media = new Media(file.toURI().toASCIIString());
                mediaFiles.add(media);
                MediaPlayer mediaplayer = new MediaPlayer(media);
                prevButton.setDisable(false);
                nextButton.setDisable(false);
                ++counter;
                player = new MediaView(mediaplayer);
                mediaplayer.setAutoPlay(true);
                player.getMediaPlayer().getMedia().getMetadata().addListener((MapChangeListener.Change<? extends String, ? extends Object> change) -> {
                    if (change.wasAdded()) {
                        if (change.getKey().equals("image")) {
                            Image art = (Image) change.getValueAdded();
                            System.out.println();
                            double artWidth = art.getWidth(), viewWidth = albumArt.getFitWidth();
                            albumArt.setX(200);
                            albumArt.setImage(art);
                            albumArt.setX(200);
                        }
                    }
                });
                volume.valueProperty().addListener((Observable observable) -> {
                    if (volume.isValueChanging()) {
                        System.out.println(volume.getValue());
                        player.getMediaPlayer().setVolume(volume.getValue() / 100);
                    }
                });
                player.getMediaPlayer().setOnEndOfMedia(() -> {
                    playPause.setSelected(false);
                });
                firstTime = false;

            } else {
                playPause.setSelected(false);
            }
        } else {
            System.out.println("I'm here");
            if (playPause.isSelected()) {
                player.getMediaPlayer().play();
            } else {
                player.getMediaPlayer().pause();
            }
        }
    }
    
    //Melakukan Mencari Musik
    @FXML
    private void browseClicked(ActionEvent event) {
        firstTime = false;
        Stage stage = (Stage) playPause.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter fileExtension = new FileChooser.ExtensionFilter("Audio files", "*.mp3", "*.wav");
        fileChooser.getExtensionFilters().add(fileExtension);
        File file = fileChooser.showOpenDialog(stage);
        Media media;
        media = new Media(file.toURI().toASCIIString());
        mediaFiles.add(media);
        MediaPlayer mediaplayer = new MediaPlayer(media);
        try {
            player.getMediaPlayer().dispose();
        } catch (Exception e) {

        }
        prevButton.setDisable(false);
        nextButton.setDisable(false);
        playPause.setSelected(true);
        ++counter;
        mediaplayer.setAutoPlay(true);
        player = new MediaView(mediaplayer);
        player.getMediaPlayer().getMedia().getMetadata().addListener((MapChangeListener.Change<? extends String, ? extends Object> change) -> {
            if (change.wasAdded()) {
                if (change.getKey().equals("image")) {
                    Image art = (Image) change.getValueAdded();
                    System.out.println();
                    double artWidth = art.getWidth(), viewWidth = albumArt.getFitWidth();
                    albumArt.setX(50);
                    albumArt.setImage(art);
                    albumArt.setX(50);
                }
            }
        });
        volume.valueProperty().addListener((Observable observable) -> {
            if (volume.isValueChanging()) {
                System.out.println(volume.getValue());
                player.getMediaPlayer().setVolume(volume.getValue() / 100);
            }
        });
        player.getMediaPlayer().setOnEndOfMedia(() -> {
            playPause.setSelected(false);
        });
    }
    
    //Melakukan Musik Sebelumnya
    @FXML
    private void prevClicked(ActionEvent event) {
        if (counter == 0) {
            player.getMediaPlayer().seek(Duration.ZERO);
        } else {
            player.getMediaPlayer().dispose();
            albumArt.setImage(null);
            player = new MediaView(new MediaPlayer(mediaFiles.get(--counter)));
            player.getMediaPlayer().play();
            playPause.setSelected(true);
            player.getMediaPlayer().setOnEndOfMedia(() -> {
                playPause.setSelected(false);
            });
            player.getMediaPlayer().getMedia().getMetadata().addListener((MapChangeListener.Change<? extends String, ? extends Object> change) -> {
                if (change.wasAdded()) {
                    if (change.getKey().equals("image")) {
                        Image art = (Image) change.getValueAdded();
                        System.out.println("I'm in");
                        double artWidth = art.getWidth(), viewWidth = albumArt.getFitWidth();
                        albumArt.setX(50);
                        albumArt.setImage(art);
                        albumArt.setX(50);
                    }
                }
            });
        }
    }
    
    //Melakukan Musik Selanjutnya
    @FXML
    private void nextClicked(ActionEvent event) {
        if (counter + 1 == mediaFiles.size()) {
            player.getMediaPlayer().stop();
            playPause.setSelected(false);
        } else {
            player.getMediaPlayer().dispose();
            albumArt.setImage(null);
            player = new MediaView(new MediaPlayer(mediaFiles.get(++counter)));
            playPause.setSelected(true);
            player.getMediaPlayer().play();
            player.getMediaPlayer().setOnEndOfMedia(() -> {
                playPause.setSelected(false);
            });
            player.getMediaPlayer().getMedia().getMetadata().addListener((MapChangeListener.Change<? extends String, ? extends Object> change) -> {
                if (change.wasAdded()) {
                    if (change.getKey().equals("image")) {
                        Image art = (Image) change.getValueAdded();
                        System.out.println();
                        double artWidth = art.getWidth(), viewWidth = albumArt.getFitWidth();
                        albumArt.setX(50);
                        albumArt.setImage(art);
                        albumArt.setX(50);
                    }
                }
            });
        }
    }

    //Game
    public void starterCard() {
        List<Integer> numbers = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < 9; i++) {
            numbers.add(rand.nextInt(10));
        }

        for (int i : numbers) {
            cardsArea.getChildren().add(createCard(i));
        }

        numberToSearch.setText("Cari Angka : "
                + numbers.get(new Random().nextInt(numbers.size() - 1)));

        theNumber = numberToSearch.getText().substring(13);

    }
    
    //Membuat Kotak Pesan 
    public StackPane createCard(int number) {

        StackPane stack = new StackPane();
        Rectangle rectangle = new Rectangle(30.0, 25.0);
        rectangle.setFill(Color.GRAY);
        rectangle.setArcHeight(10.0);
        rectangle.setArcWidth(10.0);

        Text text = new Text("" + number);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        text.setFill(Color.WHITE);

        stack.getChildren().addAll(rectangle, text);

        return stack;
    }
    
    //Memulai Game
    @FXML
    public void startGame() {
        for (int i = 0; i < cardsArea.getChildren().size(); i++) {
            StackPane stack = (StackPane) cardsArea.getChildren().get(i);
            stack.getChildren().add(new Rectangle(30.0, 25.0, Color.BLACK));

            stack.setOnMouseClicked(event -> {

                FadeTransition trans = new FadeTransition(Duration.seconds(1), stack.getChildren().get(2));
                trans.setFromValue(1.0);
                trans.setToValue(0);
                trans.play();

                counter1++;

                Text text1 = (Text) stack.getChildren().get(1);
                if (text1.getText().equals(theNumber)) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Wow, Kamu Menemukan Angka " + theNumber);
                    alert.showAndWait();
                }
                cardsClicked.setText("Jumlah di Tebak : " + counter1);
            });
        }

        FXCollections.shuffle(cardsArea.getChildren());
    }

    //Menuju Menu Login Admin
    @FXML
    private void home() throws IOException {
        main.showDataJadwal();
    }
}
