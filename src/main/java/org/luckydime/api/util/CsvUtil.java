package org.luckydime.api.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvUtil {
    public static Stream<String> getLinesFromCsv(File csvFile) {
        FileReader fileReader;
        try {
            fileReader = new FileReader(csvFile, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new BufferedReader(fileReader).lines();
    }

    public static Stream<String> getLinesFromFileUsingCharset(File csvFile, Charset charset) {
        FileReader fileReader;
        try {
            fileReader = new FileReader(csvFile, charset);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new BufferedReader(fileReader).lines();
    }

    public static Stream<String> getLinesFromCsv(String fileNameSufix) {
        File csvFile = FileUtil.getStatementFile(fileNameSufix);
        return getLinesFromCsv(csvFile);
    }

    public static Map<String, String> getMapFromCsv(File csvFile) throws IOException {
        return getLinesFromCsv(csvFile)
                .collect(Collectors.toMap(v -> v.split(",")[0], v -> v.split(",")[1]));
    }

    public static String[] getValuesFromLine(String line) {
        line = line.replaceAll("\",\"", ";");
        line = line.replaceAll("\"", "");
        String lineUtf8 = new String(line.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        return lineUtf8.split(";");
    }
}