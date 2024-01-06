package org.luckydime.api.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;

@Slf4j
public class ExceptionUtil {
    public static void logErrorAndThrowException(String exceptionMessage, ParseException e) {
        log.error(exceptionMessage);
        throw new RuntimeException(exceptionMessage, e);
    }
}