package com.graphql.springbootgraphql.controller;

import com.graphql.springbootgraphql.model.Book;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class FileController {
    @PostMapping("/upload")
    public String uploadFile(@RequestParam MultipartFile file){
        try {
            InputStream is = file.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            CSVParser csvParser = new CSVParser(br, CSVFormat.DEFAULT);
            int rowNum = 0;

            for (CSVRecord record: csvParser) {
                if (rowNum > 0){
                    System.out.format("%s - %s - %s - %s \n",
                            record.get(0), record.get(1)
                            , record.get(2), record.get(3));
                }
                rowNum++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Uploaded file";
    }

    @GetMapping(value = "/downloadCSV")
    public void downloadCSV(HttpServletResponse response) throws IOException {

        String csvFileName = "books.csv";

        response.setContentType("text/csv");

        // creates mock data
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);

        Book book1 = new Book("Effective Java", "Java Best Practices", 38, "1123");

        Book book2 = new Book("Effective Java", "Java Best Practices", 38, "1123");

        List<Book> listBooks = Arrays.asList(book1, book2);

        // uses the Super CSV API to generate CSV data from the model data
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);

        String[] header = {"id", "name", "pageCount", "authorId"};

        csvWriter.writeHeader(header);

        for (Book aBook : listBooks) {
            csvWriter.write(aBook, header);
        }

        csvWriter.close();
    }
}
