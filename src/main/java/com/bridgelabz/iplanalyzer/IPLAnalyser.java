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

public class IPLAnalyser {
    List<IPLRuns> IPLCSVRuns = null;
    List<IPLWickets> IPLCSVWickets = null;


    public int loadIPLData(String csvFilePath) throws IPLException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            IPLCSVRuns = CSVBuilderFactory.createCSVBuilder().getCSVFList(reader, IPLRuns.class);
            return IPLCSVRuns.size();

        } catch (IOException | CSVBuilderException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.IPL_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }

    }

    public String getPlayersWithTopAverages() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBattingAvg.json")) {
            if (IPLCSVRuns == null || IPLCSVRuns.size() == 0) {
                throw new IPLException("Empty!!!", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLRuns> censusComparator = Comparator.comparing(census -> census.avg);
            this.descendingSort(censusComparator);
            String json = new Gson().toJson(IPLCSVRuns);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(IPLCSVRuns, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }


    }

    public String getPlayersWithHighestStrikingRates() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBattingSR.json")) {
            if (IPLCSVRuns == null || IPLCSVRuns.size() == 0) {
                throw new IPLException("Empty!!!", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLRuns> iplComparator = Comparator.comparing(census -> census.strikeRate);
            this.descendingSort(iplComparator);
            String json = new Gson().toJson(IPLCSVRuns);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(IPLCSVRuns, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }


    }

    public String getPlayersWithTop6sAnd4s() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBattingBoundary.json")) {
            if (IPLCSVRuns == null || IPLCSVRuns.size() == 0) {
                throw new IPLException("Empty!!", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLRuns> iplComparator = Comparator.comparing(census -> census.fours + census.sixes);
            this.descendingSort(iplComparator);
            String json = new Gson().toJson(IPLCSVRuns);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(IPLCSVRuns, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }


    }

    public String getPlayersWithTopStrikingRateAndBoundary() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBattingSRandBoundary.json")) {
            if (IPLCSVRuns == null || IPLCSVRuns.size() == 0) {
                throw new IPLException("Empty!!!", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLRuns> iplComparator = Comparator.comparing(IPLRuns::getSixes).thenComparing(ipl -> ipl.fours).thenComparing(census -> census.strikeRate);
            this.descendingSort(iplComparator);
            String json = new Gson().toJson(IPLCSVRuns);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(IPLCSVRuns, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }


    }

    public String getPlayersWithTopStrikingRateAndAverage() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBattingSRandAvg.json")) {
            if (IPLCSVRuns == null || IPLCSVRuns.size() == 0) {
                throw new IPLException("Empty!!!", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLRuns> iplComparator = Comparator.comparing(IPLRuns::getAvg).thenComparing(census -> census.strikeRate);
            this.descendingSort(iplComparator);
            String json = new Gson().toJson(IPLCSVRuns);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(IPLCSVRuns, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }


    }

    public String getPlayersWithMaxRunsAndAverage() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBattingMaxRuns.json")) {
            if (IPLCSVRuns == null || IPLCSVRuns.size() == 0) {
                throw new IPLException("Empty!!!", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLRuns> iplComparator = Comparator.comparing(IPLRuns::getRuns).thenComparing(ipl -> ipl.avg);
            this.descendingSort(iplComparator);
            String json = new Gson().toJson(IPLCSVRuns);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(IPLCSVRuns, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }


    }

    public int loadIPLDataWkts(String iplCsvWicketsPath) throws IPLException {
        try (Reader reader = Files.newBufferedReader(Paths.get(iplCsvWicketsPath))) {
            IPLCSVWickets = CSVBuilderFactory.createCSVBuilder().getCSVFList(reader, IPLWickets.class);
            return IPLCSVWickets.size();

        } catch (IOException | CSVBuilderException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.IPL_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }

    }

    public String getBowlersWithTopAverage() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBowlingBestAvg.json")) {
            if (IPLCSVWickets == null || IPLCSVWickets.size() == 0) {
                throw new IPLException("Empty!!!", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLWickets> iplComparator = Comparator.comparing(iplWickets -> iplWickets.avg);
            this.descendingSortWickets(iplComparator);
            String json = new Gson().toJson(IPLCSVWickets);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(IPLCSVWickets, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }
    }

    public int getPlayerIndex(IPLWickets[] iplWickets) {
        for (int i = 0; i < iplWickets.length; i++) {
            if (iplWickets[i].avg != 0) {
                System.out.println(iplWickets[i].player);
                return i;
            }
        }
        return 1;

    }

    private void descendingSortWickets(Comparator<IPLWickets> iplComparator) {
        for (int i = 0; i < IPLCSVWickets.size() - 1; i++) {
            for (int j = 0; j < IPLCSVWickets.size() - i - 1; j++) {
                IPLWickets ipl1 = IPLCSVWickets.get(j);
                IPLWickets ipl2 = IPLCSVWickets.get(j + 1);
                if (iplComparator.compare(ipl1, ipl2) < 0) {
                    IPLCSVWickets.set(j, ipl2);
                    IPLCSVWickets.set(j + 1, ipl1);
                }
            }
        }
    }

    private void descendingSort(Comparator<IPLRuns> iplComparator) {
        for (int i = 0; i < IPLCSVRuns.size() - 1; i++) {
            for (int j = 0; j < IPLCSVRuns.size() - i - 1; j++) {
                IPLRuns ipl1 = IPLCSVRuns.get(j);
                IPLRuns ipl2 = IPLCSVRuns.get(j + 1);
                if (iplComparator.compare(ipl1, ipl2) < 0) {
                    IPLCSVRuns.set(j, ipl2);
                    IPLCSVRuns.set(j + 1, ipl1);
                }
            }
        }
    }

    public String getBowlersWithHighestStrikingRate() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBowlingBestSR.json")) {
            if (IPLCSVWickets == null || IPLCSVWickets.size() == 0) {
                throw new IPLException("Empty!!!", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLWickets> iplComparator = Comparator.comparing(iplWickets -> iplWickets.strikeRate);
            this.descendingSortWickets(iplComparator);
            String json = new Gson().toJson(IPLCSVWickets);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(IPLCSVWickets, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }
    }

    public String getBowlersWithHighestEconomy() throws IPLException{
        try (Writer writer = new FileWriter("./src/test/resources/IPLBowlingBestEconomy.json")) {
        if (IPLCSVWickets == null || IPLCSVWickets.size() == 0) {
            throw new IPLException("Empty!!!", IPLException.ExceptionType.NO_DATA);
        }
        Comparator<IPLWickets> iplComparator = Comparator.comparing(iplWickets -> iplWickets.economy);
        this.descendingSortWickets(iplComparator);
        String json = new Gson().toJson(IPLCSVWickets);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(IPLCSVWickets, writer);
        return json;

    } catch (RuntimeException | IOException e) {
        throw new IPLException(e.getMessage(),
                IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
    }

}

    public String getBowlersWithTopStrikingRateAnd4w5w() throws IPLException{
        try (Writer writer = new FileWriter("./src/test/resources/IPLBowlingBestSRWithWickets.json")) {
            if (IPLCSVWickets == null || IPLCSVWickets.size() == 0) {
                throw new IPLException("Empty!!!", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLWickets> iplComparator = Comparator.comparing(IPLWickets::getFourWicket).thenComparing(IPLWickets::getFiveWicket).thenComparing(iplWickets -> iplWickets.strikeRate);
            this.descendingSortWickets(iplComparator);
            String json = new Gson().toJson(IPLCSVWickets);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(IPLCSVWickets, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }

    }

    public String getBowlersWithTopStrikingRateAndAverage() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBowlingBestSRandAvg.json")) {
            if (IPLCSVWickets == null || IPLCSVWickets.size() == 0) {
                throw new IPLException("Empty!!!", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLWickets> iplComparator = Comparator.comparing(IPLWickets::getAvg).thenComparing(iplWickets -> iplWickets.strikeRate);
            this.descendingSortWickets(iplComparator);
            String json = new Gson().toJson(IPLCSVWickets);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(IPLCSVWickets, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }
    }
}
