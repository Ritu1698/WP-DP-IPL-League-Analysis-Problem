package com.brigelabz.iplanalyzer;

import com.bridgelabz.iplanalyzer.IPLAnalyser;
import com.bridgelabz.iplanalyzer.IPLException;
import com.bridgelabz.iplanalyzer.IPLRuns;
import com.bridgelabz.iplanalyzer.IPLWickets;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IPLAnalyserTest {
    private static final String IPL_CSV_RUNS_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_CSV_WICKETS_PATH = "src/test/resources/IPL2019FactsheetMostWkts.csv";
    IPLAnalyser iplAnalyser = null;

    @Before
    public void setUP() {
        iplAnalyser = new IPLAnalyser();
    }

    @Test
    public void givenIPLRunsCSVFile_whenLoaded_shouldMatchRecordSizeCorrectly() {
        try {

            int numOfRecords = iplAnalyser.loadIPLData(IPL_CSV_RUNS_PATH);
            Assert.assertEquals(101, numOfRecords);
        } catch (IPLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenData_whenSortedOnBattingAverages_shouldReturnBatsmanWithHighestAverageCricketer() {
        try {
            iplAnalyser.loadIPLData(IPL_CSV_RUNS_PATH);
            String sortCensusData = iplAnalyser.getPlayersWithTopAverages();
            IPLRuns[] iplRuns = new Gson().fromJson(sortCensusData, IPLRuns[].class);
            Assert.assertEquals("MS Dhoni", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void givenData_whenSortedOnStrikingRates_shouldReturnHighestStrikingRatedCricketer() {
        try {

            iplAnalyser.loadIPLData(IPL_CSV_RUNS_PATH);
            String sortCensusData = iplAnalyser.getPlayersWithHighestStrikingRates();
            IPLRuns[] iplRuns = new Gson().fromJson(sortCensusData, IPLRuns[].class);
            Assert.assertEquals("Ishant Sharma", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void givenData_whenSortedOn6sAnd4s_shouldReturnHighestBoundaryCricketer() {
        try {

            iplAnalyser.loadIPLData(IPL_CSV_RUNS_PATH);
            String sortedIPLData = iplAnalyser.getPlayersWithTop6sAnd4s();
            IPLRuns[] iplRuns = new Gson().fromJson(sortedIPLData, IPLRuns[].class);
            Assert.assertEquals("Andre Russell", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void givenData_whenSorted_shouldReturnHighestBoundaryAndStrikingRateCricketer() {
        try {
            iplAnalyser.loadIPLData(IPL_CSV_RUNS_PATH);
            String sortedIPLData = iplAnalyser.getPlayersWithTopStrikingRateAndBoundary();
            IPLRuns[] iplRuns = new Gson().fromJson(sortedIPLData, IPLRuns[].class);
            Assert.assertEquals("Andre Russell", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void givenData_whenSorted_ShouldReturnHighestAvgAndStrikingRateCricketer() {
        try {
            iplAnalyser.loadIPLData(IPL_CSV_RUNS_PATH);
            String sortedIPLData = iplAnalyser.getPlayersWithTopStrikingRateAndAverage();
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

            iplAnalyser.loadIPLData(IPL_CSV_RUNS_PATH);
            String sortedIPLData = iplAnalyser.getPlayersWithMaxRunsAndAverage();
            IPLRuns[] iplRuns = new Gson().fromJson(sortedIPLData, IPLRuns[].class);
            Assert.assertEquals("David Warner ", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void givenIPLRunsWicketsFile_whenLoaded_shouldMatchRecordSizeCorrectly() {
        try {
            int numOfRecords = iplAnalyser.loadIPLDataWkts(IPL_CSV_WICKETS_PATH);
            Assert.assertEquals(99, numOfRecords);
        } catch (IPLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenData_whenSortedOnBowlingAverages_shouldReturnHighestAverageBowler() {
        try {
            iplAnalyser.loadIPLDataWkts(IPL_CSV_WICKETS_PATH);
            String sortedIPLData = iplAnalyser.getBowlersWithTopAverage();
            IPLWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLWickets[].class);
            int index = iplAnalyser.getPlayerIndex(iplRuns);
            Assert.assertEquals("Krishnappa Gowtham", iplRuns[index].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void givenData_whenSortedOnBowlingAverages_shouldReturnHighestStrikingRateBowler() {
        try {
            iplAnalyser.loadIPLDataWkts(IPL_CSV_WICKETS_PATH);
            String sortedIPLData = iplAnalyser.getBowlersWithHighestStrikingRate();
            IPLWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLWickets[].class);
            int index = iplAnalyser.getPlayerIndex(iplRuns);
            Assert.assertEquals("Krishnappa Gowtham", iplRuns[index].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void givenData_whenSortedOnEconomyRate_ShouldReturnBowlerWithHighestEconomy() {
        try {
            iplAnalyser.loadIPLDataWkts(IPL_CSV_WICKETS_PATH);
            String sortedIPLData = iplAnalyser.getBowlersWithHighestEconomy();
            IPLWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLWickets[].class);
            Assert.assertEquals("Ben Cutting", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void givenData_whenSortedOnEconomyRate_ShouldReturnBowlerWithHighestStrikingRateAnd5w4w() {
        try {
            iplAnalyser.loadIPLDataWkts(IPL_CSV_WICKETS_PATH);
            String sortedIPLData = iplAnalyser.getBowlersWithTopStrikingRateAnd4w5w();
            IPLWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLWickets[].class);
            Assert.assertEquals("Liam Livingstone", iplRuns[iplRuns.length - 1].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void givenData_whenSortedOnEconomyRate_ShouldReturnBowlerWithHighestStrikingRateAndAverage() {
        try {
            iplAnalyser.loadIPLDataWkts(IPL_CSV_WICKETS_PATH);
            String sortedIPLData = iplAnalyser.getBowlersWithTopStrikingRateAndAverage();
            IPLWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLWickets[].class);
            int index = iplAnalyser.getPlayerIndex(iplRuns);
            Assert.assertEquals("Krishnappa Gowtham", iplRuns[index].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void givenData_whenSortedOnEconomyRate_ShouldReturnBowlerWithMaxWickets() {
        try {
            iplAnalyser.loadIPLDataWkts(IPL_CSV_WICKETS_PATH);
            String sortedIPLData = iplAnalyser.getBowlersWithMaxWickets();
            IPLWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLWickets[].class);
            Assert.assertEquals("Liam Livingstone", iplRuns[iplRuns.length - 1].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void givenData_whenSortedOnBowlingAndBatting_ShouldReturnBestAveragePlayer() throws IPLException {
        iplAnalyser.loadIPLDataWkts(IPL_CSV_WICKETS_PATH);
        iplAnalyser.loadIPLData(IPL_CSV_RUNS_PATH);
        String bestAvg = iplAnalyser.getBestBattingAndBowlingAverage();
        Assert.assertEquals("Imran Tahir", bestAvg);
    }

    @Test
    public void givenData_whenSortedOnBowlingAndBattingAverages_ShouldReturnBestAllRounderPlayer() throws IPLException {
        iplAnalyser.loadIPLDataWkts(IPL_CSV_WICKETS_PATH);
        String sorted = iplAnalyser.getBowlersWithMaxWickets();
        IPLWickets[] wickets = new Gson().fromJson(sorted, IPLWickets[].class);
        iplAnalyser.loadIPLData(IPL_CSV_RUNS_PATH);
        String sortedBat = iplAnalyser.getPlayersWithMaxRunsAndAverage();
        IPLRuns[] runs = new Gson().fromJson(sortedBat, IPLRuns[].class);
        String bestAllRounder = iplAnalyser.getBestAllRounder(runs, wickets);
        Assert.assertEquals("Andre Russell", bestAllRounder);
    }

    @Test
    public void givenIPLBattingFile_whenSorted_shouldReturnMaximumHundredsAndBestAveragePlayer() throws IPLException {
        iplAnalyser.loadIPLData(IPL_CSV_RUNS_PATH);
        String player = iplAnalyser.getBatsmanWithMaximumHundredsAndBestAverage();
        Assert.assertEquals("David Warner ", player);

    }

    @Test
    public void givenIPLBattingFile_whenSorted_shouldReturnZeroHitHundredsAndFiftiesButBestAveragePlayer() throws IPLException {
        iplAnalyser.loadIPLDataWkts(IPL_CSV_WICKETS_PATH);
        String player = iplAnalyser.getBatsmanWithNoHundredsAndFiftiesButBestAverage();
        Assert.assertEquals("Marcus Stoinis", player);

    }
}
