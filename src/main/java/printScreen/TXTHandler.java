package printScreen;

import jdk.jshell.execution.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TXTHandler {
    public static String content(String filename) {
        InputStream inputStream = TXTHandler.class.getResourceAsStream(filename);
        if (inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                return stringBuilder.toString();
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }
        return null;
    }
}
