package com.example.backend.service;

import com.example.backend.model.DataItem;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {
    private final String FILE_PATH = "src/main/resources/daftar.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<DataItem> getAllData() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            file.createNewFile();
            objectMapper.writeValue(file, new ArrayList<>());
        }
        return objectMapper.readValue(file, new TypeReference<List<DataItem>>() {});
    }

    public void saveData(DataItem dataItem) throws IOException {
        List<DataItem> dataList = getAllData();
        dataList.add(dataItem);
        objectMapper.writeValue(new File(FILE_PATH), dataList);
    }
}
