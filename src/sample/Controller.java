package sample;


import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.datamodel.DataModel;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class Controller {

    @FXML
    private TextField userInput;
    @FXML
    private Label convertedTextMorse;
    @FXML
    private Label convertedTextPhonetic;
    @FXML
    private Hyperlink wikipediaLink;

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
    }

    @FXML
    public void handleKeyReleased(){
        String input = userInput.getText().toUpperCase();
//        System.out.println(input);
        String convertedStringMorse = "";
        String convertedStringNATO = "";

        if (input.length() == 1){
            if (dataModel.getMorseData().containsKey(input) && dataModel.getPhoneticData().containsKey(input)){
                convertedStringMorse = dataModel.getMorseData().get(input);
                convertedStringNATO = dataModel.getPhoneticData().get(input);
//                System.out.println(convertedStringMorse + convertedStringNATO);
                convertedTextMorse.setText(convertedStringMorse);
                convertedTextPhonetic.setText(convertedStringNATO);
            }
        } else if (input.length() > 1){
            for(int i = 0; i < input.length(); i++){
                String temp = String.valueOf(input.charAt(i));
//                System.out.println(temp + "temp");
                if(dataModel.getMorseData().containsKey(temp) && dataModel.getPhoneticData().containsKey(temp)){
                    convertedStringMorse += dataModel.getMorseData().get(temp) + " , ";
                    convertedStringNATO += dataModel.getPhoneticData().get(temp) + " ";
                    convertedTextMorse.setText(convertedStringMorse);
                    convertedTextPhonetic.setText(convertedStringNATO);
                }
            }
        }
    }
}
