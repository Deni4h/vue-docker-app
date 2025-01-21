package com.example.backend.controller;

import com.example.backend.model.DataItem;
import com.example.backend.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/data") // Path untuk API
@CrossOrigin(origins = "http://192.168.122.100:8087") // Mengizinkan akses dari frontend
public class DataController {

    @Autowired
    private FileService fileService;

    // GET /api/data: Mengambil semua data
    @GetMapping
    public List<DataItem> getAllData() throws IOException {
        // Mengambil data melalui service
        return fileService.getAllData();
    }

    // POST /api/data: Menyimpan data
    @PostMapping
    public String saveData(@RequestBody DataItem dataItem) throws IOException {
        // Menyimpan data melalui service
        fileService.saveData(dataItem);
        return "Data berhasil disimpan!";
    }
}
