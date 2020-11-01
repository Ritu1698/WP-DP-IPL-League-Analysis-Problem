package com.brigelabz.iplanalyzer;

import com.bridgelabz.iplanalyzer.IPLAnalyser;
import com.bridgelabz.iplanalyzer.IPLException;
import org.junit.Assert;
import org.junit.Test;

public class IPLAnalyserTest {
    private static final String IPL_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    @Test
    public void givenIndianCensusCSVFile_whenLoaded_shouldMatchRecordSizeCorrectly() {
        try {
            IPLAnalyser iplRuns = new IPLAnalyser();
            int numOfRecords = iplRuns.loadIPLData(IPL_CSV_FILE_PATH);
            Assert.assertEquals(101, numOfRecords);
        } catch (IPLException e) {
            e.printStackTrace();
        }
    }
}
