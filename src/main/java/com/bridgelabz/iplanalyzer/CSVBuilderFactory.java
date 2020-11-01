package com.bridgelabz.iplanalyzer;

public class CSVBuilderFactory {
    public static ICSVBuilder createCSVBuilder() {
        return new OpenCsvBuilder();
    }
}
