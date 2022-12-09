package com.kytc.bikeID.util;

import com.kytc.bikeID.dto.BikeDto;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BikeExcelExporter {

    private final XSSFWorkbook workbook;

    private XSSFSheet sheet;


    private final List<BikeDto> bikes;


    public BikeExcelExporter(List<BikeDto> bikes) {

        this.bikes = bikes;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {

        sheet = workbook.createSheet("StolenBikes");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Bike ID", style);
        createCell(row, 1, "Bike Type", style);
        createCell(row, 2, "Bike Brand", style);
        createCell(row, 3, "Bike Model", style);
        createCell(row, 4, "Bike Color", style);
        createCell(row, 5, "Frame number", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {

        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {

        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (BikeDto dto : bikes) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, dto.getId(), style);
            createCell(row, columnCount++, dto.getBikeType(), style);
            createCell(row, columnCount++, dto.getBikeBrand(), style);
            createCell(row, columnCount++, dto.getBikeModel(), style);
            createCell(row, columnCount++, dto.getBikeColor(), style);
            createCell(row, columnCount, dto.getFrameNumber(), style);


        }
    }

    public void export(HttpServletResponse response) throws IOException {

        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }

}
