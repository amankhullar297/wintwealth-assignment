package com.wintweatlh.assignment.service.implementation;

import com.wintweatlh.assignment.exception.BaseException;
import com.wintweatlh.assignment.service.ExcelService;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService  {
    @Override
    public List<List<String>> readFromExcel(InputStream inputStream, int sheetNumber) throws BaseException {
        try{
            Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet sheet = workbook.getSheetAt(sheetNumber);
            List<List<String>> objects = new ArrayList<>();

            for (Row row : sheet) {
                List<String> objectList = new ArrayList<>();

            row:
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case BLANK:
                            break row;

                        default:
                            objectList.add(getCellValue(cell));
                    }
                }

                if(!objectList.isEmpty())
                    objects.add(objectList);
            }

            return objects;
        }
        catch (IllegalArgumentException illegalArgumentException){
            throw BaseException.builder().errorInfo(illegalArgumentException.getMessage()).status(HttpStatus.BAD_REQUEST).build();
        }
        catch (IOException ioException){
            throw BaseException.builder().errorInfo(ioException.getMessage()).status(HttpStatus.BAD_REQUEST).build();
        }
        catch (POIXMLException poixmlException){
            throw BaseException.builder().errorInfo(poixmlException.getMessage()).status(HttpStatus.BAD_REQUEST).build();
        }
        catch (Exception exception){
            throw BaseException.builder().errorInfo(exception.getMessage()).status(HttpStatus.BAD_REQUEST).build();
        }
    }

    private String getCellValue(Cell cell) {
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }
}
