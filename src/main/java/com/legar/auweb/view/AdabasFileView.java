package com.legar.auweb.view;

import com.legar.auweb.dto.AdabasFileDto;
import com.legar.auweb.dto.AdabasFieldDto;
import java.util.List;

/**
 * View component for displaying Adabas File details and its associated fields.
 */
public class AdabasFileView {

    private AdabasFileDto adabasFile;

    public AdabasFileView(AdabasFileDto adabasFile) {
        this.adabasFile = adabasFile;
    }

    public void display() {
        if (adabasFile == null) {
            System.out.println("No Adabas File data available.");
            return;
        }

        System.out.println("=== Adabas File Details ===");
        System.out.println("Name:     " + adabasFile.getName());
        System.out.println("Database: " + adabasFile.getDatabase());
        System.out.println("URI:      " + adabasFile.getUri());
        
        displayFields(adabasFile.getFields());
    }

    private void displayFields(List<AdabasFieldDto> fields) {
        if (fields == null || fields.isEmpty()) {
            System.out.println("No fields defined for this file.");
            return;
        }

        System.out.println("\n--- Fields ---");
        for (AdabasFieldDto field : fields) {
            System.out.printf("Field: %s (%s) | Type: %s | Length: %d | Decimals: %d%n",
                    field.getName(),
                    field.getShortName(),
                    field.getType(),
                    field.getLength(),
                    field.getDecimals());
        }
    }
}
