package org.luckydime.api.util;

import java.io.File;
import java.util.stream.Stream;

public class FileUtil {
    public static File getStatementFile(String suffix) {
        return Stream.of(new File(System.getProperty("user.home") + "/lucky-dime/statements/new").listFiles())
                .filter(f -> f.getName().contains(suffix))
                .findFirst()
                .get();
    }
}