package com.wintweatlh.assignment.utility;

import com.wintweatlh.assignment.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringToDateConversion {
    public static LocalDate convert(String date, String format, String fieldName) throws BaseException{
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            LocalDate localDate = LocalDate.parse(date, formatter);
            return localDate;
        }catch (Exception exception){
            throw BaseException.builder()
                    .errorInfo(String.format("Failed to convert field %s to date", fieldName))
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }
}