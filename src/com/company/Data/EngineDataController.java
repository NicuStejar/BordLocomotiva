package com.company.Data;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Nicu Stejar on 25-Feb-16.
 */
public class EngineDataController {
    private DataReader dataReader;
    private EngineData engineData;

    public EngineDataController() {
        try {
            this.dataReader = new DataReader("GetData.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.engineData = new EngineData();
        this.dataReader.setEngineData(this.engineData);
    }

    public EngineData getEngineData() {
        return engineData;
    }

    public void run() throws IOException {
        this.dataReader.read();
    }
}
