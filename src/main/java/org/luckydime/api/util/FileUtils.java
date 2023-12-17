package org.luckydime.api.util;

import java.io.File;
import java.util.stream.Stream;

public class FileUtils {
    public static File getStatementFile(String suffix) {
        return Stream.of(new File(System.getProperty("user.home") + "/statements/new").listFiles())
                .filter(f -> f.getName().contains(suffix))
                .findFirst()
                .get();
    }
}