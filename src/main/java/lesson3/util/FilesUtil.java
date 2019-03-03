package lesson3.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class FilesUtil {

    private FilesUtil () {

    }

    public static List<String> readLinesFromFile(String filename) {
        List<String> lines = new ArrayList<>();
        try (InputStream resourceAsStream = FilesUtil.class.getClassLoader().getResourceAsStream(filename);
             BufferedReader bf = new BufferedReader(new InputStreamReader(Objects.requireNonNull(resourceAsStream))))
        {
            String line;
            while ((line = bf.readLine()) != null) {
                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }
}
