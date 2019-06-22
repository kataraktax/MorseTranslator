package sample.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import sample.model.DataModel;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class Controller {

    @FXML
    private Label convertedTextMorse;
    @FXML
    private Label convertedTextPhonetic;
    @FXML
    private Hyperlink wikipediaLink;
    @FXML
    private JFXTextField userInput;

    private static DataModel dataModel;

    public void initialize(){
        dataModel = new DataModel();
        try {
            dataModel.loadsData();
        } catch (IOException e){
            e.printStackTrace();

        }

        wikipediaLink.setOnAction(event -> {
            if (Desktop.isDesktopSupported()){
                try {
                    Desktop.getDesktop().browse(new URI("https://en.wikipedia.org/wiki/Samuel_Morse"));
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });

        userInput.setOnKeyReleased(event -> {
            String input = userInput.getText().toUpperCase();
            String convertedStringMorse = "";
            String convertedStringNATO = "";

            if (input.length() == 1){
                if (dataModel.getMorseData().containsKey(input) && dataModel.getPhoneticData().containsKey(input)){
                    convertedStringMorse = dataModel.getMorseData().get(input);
                    convertedStringNATO = dataModel.getPhoneticData().get(input);
                    convertedTextMorse.setText(convertedStringMorse);
                    convertedTextPhonetic.setText(convertedStringNATO);
                }
            } else if (input.length() > 1){
                for(int i = 0; i < input.length(); i++){
                    String temp = String.valueOf(input.charAt(i));
                    if(dataModel.getMorseData().containsKey(temp) && dataModel.getPhoneticData().containsKey(temp)){
                        convertedStringMorse += dataModel.getMorseData().get(temp) + " , ";
                        convertedStringNATO += dataModel.getPhoneticData().get(temp) + " ";
                        convertedTextMorse.setText(convertedStringMorse);
                        convertedTextPhonetic.setText(convertedStringNATO);
                    }
                }
            }

        });
    }
}



