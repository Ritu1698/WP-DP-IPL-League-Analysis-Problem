package com.brigelabz.iplanalyzer;

import com.bridgelabz.iplanalyzer.IPLAnalyser;
import com.bridgelabz.iplanalyzer.IPLException;
import com.bridgelabz.iplanalyzer.IPLRuns;
import com.bridgelabz.iplanalyzer.IPLWickets;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class IPLAnalyserTest {
    private static final String IPL_CSV_RUNS_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_CSV_WICKETS_PATH = "src/test/resources/IPL2019FactsheetMostWkts.csv";
    @Test
    public void givenIPLRunsCSVFile_whenLoaded_shouldMatchRecordSizeCorrectly() {
        try {
            IPLAnalyser iplRuns = new IPLAnalyser();
            int numOfRecords = iplRuns.loadIPLData(IPL_CSV_RUNS_PATH);
            Assert.assertEquals(101, numOfRecords);
        } catch (IPLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenData_whenSortedOnBattingAverages_shouldReturnBatsmanWithHighestAverageCricketer() {
        try {
            IPLAnalyser iPLAnalyser = new IPLAnalyser();
            iPLAnalyser.loadIPLData(IPL_CSV_RUNS_PATH);
            String sortCensusData = iPLAnalyser.getPlayersWithTopAverages();
            IPLRuns[] iplRuns = new Gson().fromJson(sortCensusData, IPLRuns[].class);
            Assert.assertEquals("MS Dhoni", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void givenData_whenSortedOnStrikingRates_shouldReturnHighestStrikingRatedCricketer() {
        try {
            IPLAnalyser iPLAnalyser = new IPLAnalyser();
            iPLAnalyser.loadIPLData(IPL_CSV_RUNS_PATH);
            String sortCensusData = iPLAnalyser.getPlayersWithHighestStrikingRates();
            IPLRuns[] iplRuns = new Gson().fromJson(sortCensusData, IPLRuns[].class);
            Assert.assertEquals("Ishant Sharma", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void givenData_whenSortedOn6sAnd4s_shouldReturnHighestBoundaryCricketer() {
        try {
            IPLAnalyser iPLAnalyser = new IPLAnalyser();
            iPLAnalyser.loadIPLData(IPL_CSV_RUNS_PATH);
            String sortedIPLData = iPLAnalyser.getPlayersWithTop6sAnd4s();
            IPLRuns[] iplRuns = new Gson().fromJson(sortedIPLData, IPLRuns[].class);
            Assert.assertEquals("Andre Russell", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void givenData_whenSorted_shouldReturnHighestBoundaryAndStrikingRateCricketer() {
        try {
            IPLAnalyser iPLAnalyser = new IPLAnalyser();
            iPLAnalyser.loadIPLData(IPL_CSV_RUNS_PATH);
            String sortedIPLData = iPLAnalyser.getPlayersWithTopStrikingRateAndBoundary();
            IPLRuns[] iplRuns = new Gson().fromJson(sortedIPLData, IPLRuns[].class);
            Assert.assertEquals("Andre Russell", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void givenData_whenSorted_ShouldReturnHighestAvgAndStrikingRateCricketer() {
        try {
            IPLAnalyser iPLAnalyser = new IPLAnalyser();
            iPLAnalyser.loadIPLData(IPL_CSV_RUNS_PATH);
            String sortedIPLData = iPLAnalyser.getPlayersWithTopStrikingRateAndAverage();
            IPLRuns[] iplRuns = new Gson().fromJson(sortedIPLData, IPLRuns[].class);
            System.out.println(iplRuns[0].player);
            Assert.assertEquals("MS Dhoni", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void givenData_whenSorted_ShouldReturnMaxRunsAndAverageCricketer() {
        try {
            IPLAnalyser iPLAnalyser = new IPLAnalyser();
            iPLAnalyser.loadIPLData(IPL_CSV_RUNS_PATH);
            String sortedIPLData = iPLAnalyser.getPlayersWithMaxRunsAndAverage();
            IPLRuns[] iplRuns = new Gson().fromJson(sortedIPLData, IPLRuns[].class);
            Assert.assertEquals("David Warner ", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void givenIPLRunsWicketsFile_whenLoaded_shouldMatchRecordSizeCorrectly() {
        try {
            IPLAnalyser iplRuns = new IPLAnalyser();
            int numOfRecords = iplRuns.loadIPLDataWkts(IPL_CSV_WICKETS_PATH);
            Assert.assertEquals(99, numOfRecords);
        } catch (IPLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenData_whenSortedOnBowlingAverages_shouldReturnHighestAverageBowler() {
        try {
            IPLAnalyser iPLAnalyser = new IPLAnalyser();
            iPLAnalyser.loadIPLDataWkts(IPL_CSV_WICKETS_PATH);
            String sortedIPLData = iPLAnalyser.getBowlersWithTopAverage();
            IPLWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLWickets[].class);
            int index = iPLAnalyser.getPlayerIndex(iplRuns);
            Assert.assertEquals("Krishnappa Gowtham", iplRuns[index].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }
}
