package com.wuhantoc.javasample;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

public class ScannerCodeManager {

    private Map<String, Box> boxMap = new HashMap<>();

    public String generateScannerCode(Box box) {
        String scannerCode = UUID.randomUUID().toString();
        boxMap.put(scannerCode, box);
        box.setAvailable(false);
        return scannerCode;
    }

    public Box verifyScannerCode(String scannerCode) {
        Box box = boxMap.get(scannerCode);
        if (box != null) {
            Iterator<Entry<String, Box>> iterator = boxMap.entrySet().iterator();
            while (iterator.hasNext()) {
                if (iterator.next().equals(scannerCode)) {
                    iterator.remove();
                    break;
                }
            }
            box.setAvailable(true);
        }
        return box;
    }

}
