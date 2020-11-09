package com.bridgelabz.iplanalyzer;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder {
    <E> List<E> getCSVList(Reader reader, Class csvClass) throws CSVBuilderException;
}
