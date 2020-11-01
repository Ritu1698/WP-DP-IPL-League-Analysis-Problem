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

    @Test
    public void givenData_whenSortedOnBowlingAverages_shouldReturnHighestStrikingRateBowler() {
        try {
            IPLAnalyser iPLAnalyser = new IPLAnalyser();
            iPLAnalyser.loadIPLDataWkts(IPL_CSV_WICKETS_PATH);
            String sortedIPLData = iPLAnalyser.getBowlersWithHighestStrikingRate();
            IPLWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLWickets[].class);
            int index = iPLAnalyser.getPlayerIndex(iplRuns);
            Assert.assertEquals("Krishnappa Gowtham", iplRuns[index].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void givenData_whenSortedOnEconomyRate_ShouldReturnBowlerWithHighestEconomy() {
        try {
            IPLAnalyser iPLAnalyser = new IPLAnalyser();
            iPLAnalyser.loadIPLDataWkts(IPL_CSV_WICKETS_PATH);
            String sortedIPLData = iPLAnalyser.getBowlersWithHighestEconomy();
            IPLWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLWickets[].class);
            Assert.assertEquals("Ben Cutting", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void givenData_whenSortedOnEconomyRate_ShouldReturnBowlerWithHighestStrikingRateAnd5w4w() {
        try {
            IPLAnalyser iPLAnalyser = new IPLAnalyser();
            iPLAnalyser.loadIPLDataWkts(IPL_CSV_WICKETS_PATH);
            String sortedIPLData = iPLAnalyser.getBowlersWithTopStrikingRateAnd4w5w();
            IPLWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLWickets[].class);
            Assert.assertEquals("Liam Livingstone", iplRuns[iplRuns.length - 1].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void givenData_whenSortedOnEconomyRate_ShouldReturnBowlerWithHighestStrikingRateAndAverage() {
        try {
            IPLAnalyser iPLAnalyser = new IPLAnalyser();
            iPLAnalyser.loadIPLDataWkts(IPL_CSV_WICKETS_PATH);
            String sortedIPLData = iPLAnalyser.getBowlersWithTopStrikingRateAndAverage();
            IPLWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLWickets[].class);
            int index = iPLAnalyser.getPlayerIndex(iplRuns);
            Assert.assertEquals("Krishnappa Gowtham", iplRuns[index].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void givenData_whenSortedOnEconomyRate_ShouldReturnBowlerWithMaxWickets() {
        try {
            IPLAnalyser iPLAnalyser = new IPLAnalyser();
            iPLAnalyser.loadIPLDataWkts(IPL_CSV_WICKETS_PATH);
            String sortedIPLData = iPLAnalyser.getBowlersWithMaxWickets();
            IPLWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLWickets[].class);
            Assert.assertEquals("Liam Livingstone", iplRuns[iplRuns.length - 1].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    @Test
    public void givenData_whenSortedOnBowlingAndBatting_ShouldReturnBestAveragePlayer() throws IPLException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLDataWkts(IPL_CSV_WICKETS_PATH);
        iplAnalyser.loadIPLData(IPL_CSV_RUNS_PATH);
        String bestAvg = iplAnalyser.getBestBattingAndBowlingAverage();
        Assert.assertEquals("Imran Tahir", bestAvg);
    }

    @Test
    public void givenData_whenSortedOnBowlingAndBattingAverages_ShouldReturnBestAllRounderPlayer() throws IPLException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
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
    public void givenIPLDataFindBatsmanWithZeroMilestonesButWithBestAverages() throws IPLException {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        iplAnalyser.loadIPLData(IPL_CSV_RUNS_PATH);
        String sorted = iplAnalyser.getPlayersWithTopHundredsorFifties();
        IPLRuns[] runs = new Gson().fromJson(sorted, IPLRuns[].class);
        String sortedBat = iplAnalyser.getPlayersWithTopAverages();
        IPLRuns[] average = new Gson().fromJson(sortedBat, IPLRuns[].class);

        for (int i = 0; i < runs.length; i++) {
            if (runs[runs.length - 1 - i].player.equals(average[i].player)) {
                Assert.assertEquals("Shane Watson", average[i].player);
                break;
            }
        }
    }


}
