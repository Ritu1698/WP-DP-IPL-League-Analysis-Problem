package com.brigelabz.iplanalyzer;

import com.bridgelabz.iplanalyzer.IPLAnalyser;
import com.bridgelabz.iplanalyzer.IPLException;
import com.bridgelabz.iplanalyzer.IPLRuns;
import com.bridgelabz.iplanalyzer.IPLWickets;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IPLAnalyserTest {
    private static final String IPL_CSV_RUNS_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_CSV_WICKETS_PATH = "src/test/resources/IPL2019FactsheetMostWkts.csv";
    IPLAnalyser iplAnalyser = null;

    //Initializing
    @Before
    public void setUP() {
        iplAnalyser = new IPLAnalyser();
    }

    //Loading IPL Runs Data Testcase
    @Test
    public void myTestA_givenIPLRunsCSVFile_whenLoaded_shouldMatchSizeCorrectly() {
        try {
            int numOfRecords = iplAnalyser.loadIPLData(IPL_CSV_RUNS_PATH);
            Assert.assertEquals(101, numOfRecords);
        } catch (IPLException e) {
            e.printStackTrace();
        }
    }

    //Highest Batting Average Player Testcase
    @Test
    public void myTestB_givenData_whenSortedOnBattingAverages_shouldReturnHighestAverageCricketer() {
        try {
            iplAnalyser.loadIPLData(IPL_CSV_RUNS_PATH);
            String sortCensusData = iplAnalyser.getPlayersWithHighestBattingAverages();
            IPLRuns[] iplRuns = new Gson().fromJson(sortCensusData, IPLRuns[].class);
            Assert.assertEquals("MS Dhoni", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    //Highest Batting Striking Rates Player Testcase
    @Test
    public void myTestC_givenData_whenSortedOnStrikingRates_shouldReturnHighestStrikeRatedCricketer() {
        try {

            iplAnalyser.loadIPLData(IPL_CSV_RUNS_PATH);
            String sortCensusData = iplAnalyser.getPlayersWithHighestStrikingRates();
            IPLRuns[] iplRuns = new Gson().fromJson(sortCensusData, IPLRuns[].class);
            Assert.assertEquals("Ishant Sharma", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    //Highest Batting 6s & 4s Securing Player Testcase
    @Test
    public void myTestD_givenData_whenSortedOn6sAnd4s_shouldReturnHighestBoundaryCricketer() {
        try {

            iplAnalyser.loadIPLData(IPL_CSV_RUNS_PATH);
            String sortedIPLData = iplAnalyser.getPlayersWithTop6sAnd4s();
            IPLRuns[] iplRuns = new Gson().fromJson(sortedIPLData, IPLRuns[].class);
            Assert.assertEquals("Andre Russell", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    //Highest Batting Striking Rates And 6s & 4s Securing Player Testcase
    @Test
    public void myTestE_givenData_whenSorted_shouldReturnHighest4s6sAndStrikingRateCricketer() {
        try {
            iplAnalyser.loadIPLData(IPL_CSV_RUNS_PATH);
            String sortedIPLData = iplAnalyser.getPlayersWithTopStrikingRateAnd6sAnd4s();
            IPLRuns[] iplRuns = new Gson().fromJson(sortedIPLData, IPLRuns[].class);
            Assert.assertEquals("Andre Russell", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    //Highest Batting Striking Rates And Averages Player Testcase
    @Test
    public void myTestF_givenData_whenSorted_ShouldReturnHighestAvgAndStrikingRateCricketer() {
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

    //Highest Batting Striking Rates And Averages Player Testcase
    @Test
    public void myTestF_givenData_whenSorted_ShouldReturnMaxRunsAndAverageCricketer() {
        try {

            iplAnalyser.loadIPLData(IPL_CSV_RUNS_PATH);
            String sortedIPLData = iplAnalyser.getPlayersWithMaxRunsAndAverage();
            IPLRuns[] iplRuns = new Gson().fromJson(sortedIPLData, IPLRuns[].class);
            Assert.assertEquals("David Warner ", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    //Loading IPL Wickets Data Testcase
    @Test
    public void myTestG_givenIPLRunsWicketsFile_whenLoaded_shouldMatchRecordSizeCorrectly() {
        try {
            int numOfRecords = iplAnalyser.loadIPLDataWkts(IPL_CSV_WICKETS_PATH);
            Assert.assertEquals(99, numOfRecords);
        } catch (IPLException e) {
            e.printStackTrace();
        }
    }

    //Highest Bowling Averages Player Testcase
    @Test
    public void myTestH_givenData_whenSorted_shouldReturnHighestAverageBowler() {
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

    //Highest Bowling Striking Rates Player Testcase
    @Test
    public void myTestI_givenData_whenSorted_shouldReturnHighestStrikingRateBowler() {
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

    //Highest Bowling Economy Rate Player Testcase
    @Test
    public void myTestJ_givenData_whenSorted_ShouldReturnBowlerWithHighestEconomy() {
        try {
            iplAnalyser.loadIPLDataWkts(IPL_CSV_WICKETS_PATH);
            String sortedIPLData = iplAnalyser.getBowlersWithHighestEconomy();
            IPLWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLWickets[].class);
            Assert.assertEquals("Ben Cutting", iplRuns[0].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    //Highest Bowling Striking Rate And 4w 5w Player Testcase
    @Test
    public void myTestK_givenData_whenSorted_ShouldReturnBowlerWithHighestStrikingRateAnd5w4w() {
        try {
            iplAnalyser.loadIPLDataWkts(IPL_CSV_WICKETS_PATH);
            String sortedIPLData = iplAnalyser.getBowlersWithTopStrikingRateAnd4w5w();
            IPLWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLWickets[].class);
            Assert.assertEquals("Liam Livingstone", iplRuns[iplRuns.length - 1].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    //Highest Bowling Striking Rate And Average Player Testcase
    @Test
    public void myTestM_givenData_whenSorted_ShouldReturnBowlerWithHighestStrikingRateAndAverage() {
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

    //Highest Bowling Wickets Player Testcase
    @Test
    public void myTestN_givenData_whenSorted_ShouldReturnBowlerWithMaxWickets() {
        try {
            iplAnalyser.loadIPLDataWkts(IPL_CSV_WICKETS_PATH);
            String sortedIPLData = iplAnalyser.getBowlersWithMaxWickets();
            IPLWickets[] iplRuns = new Gson().fromJson(sortedIPLData, IPLWickets[].class);
            Assert.assertEquals("Liam Livingstone", iplRuns[iplRuns.length - 1].player);
        } catch (IPLException e) {
            e.printStackTrace();

        }
    }

    //Highest Bowling Or Batting Average Player Testcase
    @Test
    public void myTestO_givenData_whenSortedOnBowlingAndBatting_ShouldReturnBestAveragePlayer() throws IPLException {
        iplAnalyser.loadIPLDataWkts(IPL_CSV_WICKETS_PATH);
        iplAnalyser.loadIPLData(IPL_CSV_RUNS_PATH);
        String bestAvg = iplAnalyser.getBestBattingAndBowlingAverage();
        Assert.assertEquals("Imran Tahir", bestAvg);
    }

    //Highest Bowling Or Batting All Rounder Player Testcase
    @Test
    public void myTestP_givenData_whenSortedOnBowlingAndBattingAverages_ShouldReturnBestAllRounderPlayer() throws IPLException {
        iplAnalyser.loadIPLDataWkts(IPL_CSV_WICKETS_PATH);
        String sorted = iplAnalyser.getBowlersWithMaxWickets();
        IPLWickets[] wickets = new Gson().fromJson(sorted, IPLWickets[].class);
        iplAnalyser.loadIPLData(IPL_CSV_RUNS_PATH);
        String sortedBat = iplAnalyser.getPlayersWithMaxRunsAndAverage();
        IPLRuns[] runs = new Gson().fromJson(sortedBat, IPLRuns[].class);
        String bestAllRounder = iplAnalyser.getBestAllRounder(runs, wickets);
        Assert.assertEquals("Andre Russell", bestAllRounder);
    }

    //Highest Batting 100s And Average Player Testcase
    @Test
    public void myTestQ_givenData_whenSorted_shouldReturnMaximumHundredsAndBestAveragePlayer() throws IPLException {
        iplAnalyser.loadIPLData(IPL_CSV_RUNS_PATH);
        String player = iplAnalyser.getMaximumHundredsAndBestAverage();
        Assert.assertEquals("David Warner ", player);

    }

    //Highest Batting Average But Zero 100s And 50s Player Testcase
    @Test
    public void myTestR_givenIPLBattingFile_whenSorted_shouldReturnZeroHitHundredsAndFiftiesButBestAveragePlayer() throws IPLException {
        iplAnalyser.loadIPLDataWkts(IPL_CSV_WICKETS_PATH);
        String player = iplAnalyser.getNoHundredsAndFiftiesButBestAverage();
        Assert.assertEquals("Marcus Stoinis", player);

    }
}
