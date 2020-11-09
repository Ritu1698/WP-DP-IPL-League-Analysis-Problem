package com.bridgelabz.iplanalyzer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class IPLAnalyser {
    List<IPLRuns> IPLCSVRuns = null;
    List<IPLWickets> IPLCSVWickets = null;


    //Method To Load IPL Runs Data To List From CSV
    public int loadIPLData(String csvFilePath) throws IPLException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            IPLCSVRuns = CSVBuilderFactory.createCSVBuilder().getCSVList(reader, IPLRuns.class);
            return IPLCSVRuns.size();
        } catch (IOException | CSVBuilderException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.IPL_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }

    }

    //Method To Sort Players On Highest Batting Averages
    public String getPlayersWithHighestBattingAverages() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBattingAvg.json")) {
            if (IPLCSVRuns == null || IPLCSVRuns.size() == 0) {
                throw new IPLException("Empty!!!", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLRuns> iplRunsComparator = Comparator.comparing(IPLRuns::getAvg, Comparator.reverseOrder());
            List<IPLRuns> iplRuns = IPLCSVRuns.stream().sorted(iplRunsComparator).collect(Collectors.toList());
            String json = new Gson().toJson(iplRuns);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(iplRuns, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }


    }

    //Method To Sort Players On Highest Striking Rates
    public String getPlayersWithHighestStrikingRates() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBattingSR.json")) {
            if (IPLCSVRuns == null || IPLCSVRuns.size() == 0) {
                throw new IPLException("Empty!!!", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLRuns> iplComparator = Comparator.comparing(IPLRuns::getStrikeRate, Comparator.reverseOrder());
            List<IPLRuns> iplRuns = IPLCSVRuns.stream().sorted(iplComparator).collect(Collectors.toList());
            String json = new Gson().toJson(iplRuns);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(iplRuns, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }


    }

    //Method To Sort Players On Highest 6s & 4s
    public String getPlayersWithTop6sAnd4s() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBattingBoundary.json")) {
            if (IPLCSVRuns == null || IPLCSVRuns.size() == 0) {
                throw new IPLException("Empty!!", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLRuns> iplComparator = Comparator.comparing(IPLRuns::getFoursAndSixes, Comparator.reverseOrder());
            List<IPLRuns> iplRuns = IPLCSVRuns.stream().sorted(iplComparator).collect(Collectors.toList());
            String json = new Gson().toJson(iplRuns);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(iplRuns, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }


    }

    //Method To Sort Players On Highest Striking Rates And 6s & 4s
    public String getPlayersWithTopStrikingRateAnd6sAnd4s() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBattingSRandBoundary.json")) {
            if (IPLCSVRuns == null || IPLCSVRuns.size() == 0) {
                throw new IPLException("Empty!!!", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLRuns> iplComparator = Comparator.comparing(IPLRuns::getSixes, Comparator.reverseOrder()).thenComparing(IPLRuns::getFours, Comparator.reverseOrder()).thenComparing(IPLRuns::getStrikeRate, Comparator.reverseOrder());
            List<IPLRuns> iplRuns = IPLCSVRuns.stream().sorted(iplComparator).collect(Collectors.toList());
            String json = new Gson().toJson(iplRuns);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(iplRuns, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }


    }

    //Method To Sort Players On Highest Striking Rates And Averages
    public String getPlayersWithTopStrikingRateAndAverage() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBattingSRandAvg.json")) {
            if (IPLCSVRuns == null || IPLCSVRuns.size() == 0) {
                throw new IPLException("Empty!!!", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLRuns> iplComparator = Comparator.comparing(IPLRuns::getAvg, Comparator.reverseOrder()).thenComparing(IPLRuns::getStrikeRate, Comparator.reverseOrder());
            List<IPLRuns> iplRuns = IPLCSVRuns.stream().sorted(iplComparator).collect(Collectors.toList());
            String json = new Gson().toJson(iplRuns);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(iplRuns, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }


    }

    //Method To Sort Players On Highest Runs And Averages
    public String getPlayersWithMaxRunsAndAverage() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBattingMaxRuns.json")) {
            if (IPLCSVRuns == null || IPLCSVRuns.size() == 0) {
                throw new IPLException("Empty!!!", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLRuns> iplComparator = Comparator.comparing(IPLRuns::getRuns, Comparator.reverseOrder()).thenComparing(IPLRuns::getAvg, Comparator.reverseOrder());
            List<IPLRuns> iplRuns = IPLCSVRuns.stream().sorted(iplComparator).collect(Collectors.toList());
            String json = new Gson().toJson(iplRuns);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(IPLCSVRuns, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }


    }

    //Method To Load IPL Wickets Data To List From CSV
    public int loadIPLDataWkts(String iplCsvWicketsPath) throws IPLException {
        try (Reader reader = Files.newBufferedReader(Paths.get(iplCsvWicketsPath))) {
            IPLCSVWickets = CSVBuilderFactory.createCSVBuilder().getCSVList(reader, IPLWickets.class);
            return IPLCSVWickets.size();

        } catch (IOException | CSVBuilderException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.IPL_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }

    }

    //Method To Sort Players On Highest Averages
    public String getBowlersWithTopAverage() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBowlingBestAvg.json")) {
            if (IPLCSVWickets == null || IPLCSVWickets.size() == 0) {
                throw new IPLException("Empty!!!", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLWickets> iplComparator = Comparator.comparing(IPLWickets::getAvg, Comparator.reverseOrder());
            List<IPLWickets> iplWickets = IPLCSVWickets.stream().sorted(iplComparator).collect(Collectors.toList());
            String json = new Gson().toJson(iplWickets);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(iplWickets, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }
    }

    //Method To Sort Players On Highest Striking Rates
    public String getBowlersWithHighestStrikingRate() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBowlingBestSR.json")) {
            if (IPLCSVWickets == null || IPLCSVWickets.size() == 0) {
                throw new IPLException("Empty!!!", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLWickets> iplComparator = Comparator.comparing(IPLWickets::getStrikeRate, Comparator.reverseOrder());
            List<IPLWickets> iplWickets = IPLCSVWickets.stream().sorted(iplComparator).collect(Collectors.toList());
            String json = new Gson().toJson(iplWickets);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(iplWickets, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }
    }

    //Method To Sort Players On Highest Economy Rates
    public String getBowlersWithHighestEconomy() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBowlingBestEconomy.json")) {
            if (IPLCSVWickets == null || IPLCSVWickets.size() == 0) {
                throw new IPLException("Empty!!!", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLWickets> iplComparator = Comparator.comparing(IPLWickets::getEconomy, Comparator.reverseOrder());
            List<IPLWickets> iplWickets = IPLCSVWickets.stream().sorted(iplComparator).collect(Collectors.toList());
            String json = new Gson().toJson(iplWickets);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(iplWickets, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }

    }

    //Method To Sort Players On Highest Striking Rates And 4w 5w
    public String getBowlersWithTopStrikingRateAnd4w5w() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBowlingBestSRWithWickets.json")) {
            if (IPLCSVWickets == null || IPLCSVWickets.size() == 0) {
                throw new IPLException("Empty!!!", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLWickets> iplComparator = Comparator.comparing(IPLWickets::getFourWicket, Comparator.reverseOrder()).thenComparing(IPLWickets::getFiveWicket, Comparator.reverseOrder()).thenComparing(IPLWickets::getStrikeRate, Comparator.reverseOrder());
            List<IPLWickets> iplWickets = IPLCSVWickets.stream().sorted(iplComparator).collect(Collectors.toList());
            String json = new Gson().toJson(iplWickets);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(iplWickets, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }

    }

    //Method To Sort Players On Highest Striking Rates And Average
    public String getBowlersWithTopStrikingRateAndAverage() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBowlingBestSRandAvg.json")) {
            if (IPLCSVWickets == null || IPLCSVWickets.size() == 0) {
                throw new IPLException("Empty!!!", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLWickets> iplComparator = Comparator.comparing(IPLWickets::getAvg, Comparator.reverseOrder()).thenComparing(IPLWickets::getStrikeRate, Comparator.reverseOrder());
            List<IPLWickets> iplWickets = IPLCSVWickets.stream().sorted(iplComparator).collect(Collectors.toList());
            String json = new Gson().toJson(iplWickets);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(iplWickets, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }
    }

    //Method To Sort Players On Highest Wickets
    public String getBowlersWithMaxWickets() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBowlingMostWickets.json")) {
            if (IPLCSVWickets == null || IPLCSVWickets.size() == 0) {
                throw new IPLException("No data", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLWickets> iplComparator = Comparator.comparing(IPLWickets::getWickets, Comparator.reverseOrder()).thenComparing(IPLWickets::getAvg, Comparator.reverseOrder());
            List<IPLWickets> iplWickets = IPLCSVWickets.stream().sorted(iplComparator).collect(Collectors.toList());
            String json = new Gson().toJson(iplWickets);
            Gson gson = new GsonBuilder().create();
            gson.toJson(iplWickets, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }
    }

    //Method To Sort Both Type Players On Highest Average
    public String getBestBattingAndBowlingAverage() {
        Comparator<IPLWickets> bowlerComparator = Comparator.comparing(IPLWickets::getWickets).reversed().thenComparing(IPLWickets::getAvg);
        List<IPLWickets> bowlerMaxWicketsAndBestAverage = IPLCSVWickets.stream().filter(bowler -> bowler.getAvg() > 0).sorted(bowlerComparator).collect(Collectors.toList());
        return bowlerMaxWicketsAndBestAverage.get(0).player;
    }

    //Method To Sort Both Type Players On Highest All Rounder
    public String getBestAllRounder(IPLRuns[] runs, IPLWickets[] wickets) {

        for (int i = 0; i < runs.length; i++) {
            for (int j = wickets.length - 1; j >= 0; j--)
                if (runs[i].player.equals(wickets[j].player)) {
                    return runs[i].player;
                }
        }
        return null;
    }

    //Method To Sort Both Type Players On Highest Average But No 100s Or 50s
    public String getNoHundredsAndFiftiesButBestAverage() {
        Comparator<IPLRuns> batsmanComparator = Comparator.comparing(IPLRuns::getAvg, Comparator.reverseOrder());
        List<IPLRuns> batsmanNoHundredsFiftiesButBestAverage = IPLCSVRuns.stream().filter(batsman -> batsman.hundreds == 0 && batsman.fifties == 0)
                .sorted(batsmanComparator).collect(Collectors.toList());
        return batsmanNoHundredsFiftiesButBestAverage.get(0).player;
    }

    //Method To Sort Both Type Players On Highest Average But No 100s Or 50s
    public String getMaximumHundredsAndBestAverage() {
        Comparator<IPLRuns> batsmanComparator = Comparator.comparing(IPLRuns::getHundreds, Comparator.reverseOrder())
                .thenComparing(IPLRuns::getAvg, Comparator.reverseOrder());
        List<IPLRuns> batsmanMaximumHundredsAndBestAverage = IPLCSVRuns.stream().sorted(batsmanComparator).collect(Collectors.toList());
        return batsmanMaximumHundredsAndBestAverage.get(0).player;
    }

    //Method To Get Index
    public int getPlayerIndex(IPLWickets[] iplWickets) {
        for (int i = 0; i < iplWickets.length; i++) {
            if (iplWickets[i].avg != 0) {
                System.out.println(iplWickets[i].player);
                return i;
            }
        }
        return 1;

    }

}
