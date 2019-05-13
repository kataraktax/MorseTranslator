package sample.datamodel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class DataModel {

    private static HashMap<String, String> morseData;
    private static HashMap<String, String> phoneticData;

    public DataModel() {
        morseData = new HashMap<>();
        phoneticData = new HashMap<>();
    }

    public Map<String, String> getMorseData() {
        return new HashMap<>(morseData);
    }

    public Map<String, String> getPhoneticData() {
        return new HashMap<>(phoneticData);
    }

    public void loadsData() throws IOException {
        morseData = new HashMap<>();
        phoneticData = new HashMap<>();
        String fileNameMorse = "morseCode.txt";
        Path path1 = Paths.get(fileNameMorse);
        String fileNamePhonetic = "phoneticAlphabet.txt";
        Path path2 = Paths.get(fileNamePhonetic);

        String input1;
        try (BufferedReader bufferedReader1 = Files.newBufferedReader(path1)) {
            while ((input1 = bufferedReader1.readLine()) != null) {
                String[] inputStringArr = input1.split(",");
                String key = inputStringArr[0];
                String value = inputStringArr[1];

                morseData.put(key, value);
            }
        }
        String input2;
        try (BufferedReader bufferedReader2 = Files.newBufferedReader(path2)) {
            while ((input2 = bufferedReader2.readLine()) != null) {
                String[] inputStringArr = input2.split(",");
                String key = inputStringArr[0];
                String value = inputStringArr[1];

                phoneticData.put(key, value);
            }
        }
    }
}

