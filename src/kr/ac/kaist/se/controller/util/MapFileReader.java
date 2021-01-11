package kr.ac.kaist.se.controller.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MapFileReader {

    public String readMapFile(String fileName){

        String mapInitString = "";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null){
                sb.append(line);
                line = br.readLine();
            }
            mapInitString = sb.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mapInitString;
    }


}
