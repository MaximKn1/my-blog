package com.example.demo.utility;

import com.example.demo.exceptions.FileReadingException;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Slf4j
public final class Utils {
    private Utils() {

    }

    /**
     * Reads file to string.
     *
     * @param fileLocation File location in relation to resources
     * @return File as String
     */
    public static String readFile(final String fileLocation) {
        if (fileLocation == null) {
            throw new FileReadingException("File location provided is null");
        }

        InputStream inputStream = Utils.class.getClassLoader().getResourceAsStream(fileLocation);
        if (inputStream == null) {
            throw new FileReadingException("Input stream is null");
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while (bufferedReader.ready()) {
                stringBuilder.append(bufferedReader.readLine());
            }
        } catch (Exception e) {
            log.warn(e.getLocalizedMessage());
        }

        return stringBuilder.toString();
    }
}
