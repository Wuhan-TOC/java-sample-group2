package com.wuhantoc.javasample;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CodeManager {

    private Map<String, Box> boxMap = new HashMap<>();

    public String generateScannerCode(Box box) {
        String scannerCode = UUID.randomUUID().toString();
        boxMap.put(scannerCode, box);
        return scannerCode;
    }

    public Box verifyScannerCode(String scannerCode) {
        Box box = boxMap.get(scannerCode);
        if (box != null) {
            boxMap.remove(scannerCode);
        }
        return box;
    }

}
