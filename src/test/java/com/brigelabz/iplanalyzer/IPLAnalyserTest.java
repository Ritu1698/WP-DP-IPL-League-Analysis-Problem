package com.brigelabz.iplanalyzer;

import com.bridgelabz.iplanalyzer.IPLAnalyser;
import com.bridgelabz.iplanalyzer.IPLException;
import com.bridgelabz.iplanalyzer.IPLRuns;
import com.google.gson.Gson;
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

    @Test
    public void givenDataShouldReturn_whenSortedOnBattingAverages_shouldReturnBatsmanWithHighestAverageCricketer() {
        try {
            IPLAnalyser iPLAnalyser = new IPLAnalyser();
            iPLAnalyser.loadIPLData(IPL_CSV_FILE_PATH);
            String sortCensusData = iPLAnalyser.getPlayersWithTopAverages();
            IPLRuns[] iplRuns = new Gson().fromJson(sortCensusData, IPLRuns[].class);
            Assert.assertEquals("MS Dhoni", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void givenDataShouldReturnBatsman_whenSortedOnStrikingRates_shouldReturnHighestStrikingRatedCricketer() {
        try {
            IPLAnalyser iPLAnalyser = new IPLAnalyser();
            iPLAnalyser.loadIPLData(IPL_CSV_FILE_PATH);
            String sortCensusData = iPLAnalyser.getPlayersWithHighestStrikingRates();
            IPLRuns[] iplRuns = new Gson().fromJson(sortCensusData, IPLRuns[].class);
            Assert.assertEquals("Ishant Sharma", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }


}
