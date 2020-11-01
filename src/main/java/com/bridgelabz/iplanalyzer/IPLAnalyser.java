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
    List<IPLRuns> IPLCSVList = null;

    public int loadIPLData(String csvFilePath) throws IPLException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            IPLCSVList = CSVBuilderFactory.createCSVBuilder().getCSVFList(reader, IPLRuns.class);
            return IPLCSVList.size();

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
            if (IPLCSVList == null || IPLCSVList.size() == 0) {
                throw new IPLException("Empty!!!", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLRuns> censusComparator = Comparator.comparing(census -> census.avg);
            this.descendingSort(censusComparator);
            String json = new Gson().toJson(IPLCSVList);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(IPLCSVList, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }


    }

    public String getPlayersWithHighestStrikingRates() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBattingSR.json")) {
            if (IPLCSVList == null || IPLCSVList.size() == 0) {
                throw new IPLException("Empty!!!", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLRuns> iplComparator = Comparator.comparing(census -> census.strikeRate);
            this.descendingSort(iplComparator);
            String json = new Gson().toJson(IPLCSVList);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(IPLCSVList, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }


    }

    public String getPlayersWithTop6sAnd4s() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBattingBoundary.json")) {
            if (IPLCSVList == null || IPLCSVList.size() == 0) {
                throw new IPLException("Empty!!", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLRuns> iplComparator = Comparator.comparing(census -> census.fours + census.sixes);
            this.descendingSort(iplComparator);
            String json = new Gson().toJson(IPLCSVList);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(IPLCSVList, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }


    }

    public String getPlayersWithTopStrikingRateAndBoundary() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBattingSRandBoundary.json")) {
            if (IPLCSVList == null || IPLCSVList.size() == 0) {
                throw new IPLException("Empty!!!", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLRuns> iplComparator = Comparator.comparing(IPLRuns::getSixes).thenComparing(ipl -> ipl.fours).thenComparing(census -> census.strikeRate);
            this.descendingSort(iplComparator);
            String json = new Gson().toJson(IPLCSVList);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(IPLCSVList, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }


    }

    public String getPlayersWithTopStrikingRateAndAverage() throws IPLException {
        try (Writer writer = new FileWriter("./src/test/resources/IPLBattingSRandAvg.json")) {
            if (IPLCSVList == null || IPLCSVList.size() == 0) {
                throw new IPLException("Empty!!!", IPLException.ExceptionType.NO_DATA);
            }
            Comparator<IPLRuns> iplComparator = Comparator.comparing(IPLRuns::getAvg).thenComparing(census -> census.strikeRate);
            this.descendingSort(iplComparator);
            String json = new Gson().toJson(IPLCSVList);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(IPLCSVList, writer);
            return json;

        } catch (RuntimeException | IOException e) {
            throw new IPLException(e.getMessage(),
                    IPLException.ExceptionType.FILE_OR_HEADER_PROBLEM);
        }


    }

    private void descendingSort(Comparator<IPLRuns> iplComparator) {
        for (int i = 0; i < IPLCSVList.size() - 1; i++) {
            for (int j = 0; j < IPLCSVList.size() - i - 1; j++) {
                IPLRuns ipl1 = IPLCSVList.get(j);
                IPLRuns ipl2 = IPLCSVList.get(j + 1);
                if (iplComparator.compare(ipl1, ipl2) < 0) {
                    IPLCSVList.set(j, ipl2);
                    IPLCSVList.set(j + 1, ipl1);
                }
            }
        }
    }


}
