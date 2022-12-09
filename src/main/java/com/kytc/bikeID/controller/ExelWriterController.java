package com.kytc.bikeID.controller;

import com.kytc.bikeID.service.BikeService;
import com.kytc.bikeID.util.BikeExcelExporter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class ExelWriterController {

    private final BikeService bikeService;

    @GetMapping("/excel")
    public void exportToExcelListStolenBike(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=bikes_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);


        BikeExcelExporter excelExporter = new BikeExcelExporter(bikeService.listOfStolenBikes());

        excelExporter.export(response);
    }


}
