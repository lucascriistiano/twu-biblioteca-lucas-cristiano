package com.twu.biblioteca.ui;

import java.util.ArrayList;
import java.util.List;

public class OutputBuilder {

    private List<String> outputLines;

    public OutputBuilder() {
        this.outputLines = new ArrayList<>();
    }

    public void clear() {
        this.outputLines = new ArrayList<>();
    }

    public void add(String line) {
        outputLines.add(line);
    }

    public void addLine(String line) {
        outputLines.add(String.format("%s\n", line));
    }

    public void addBlankLine() {
        outputLines.add("\n");
    }

    public List<String> getOutputLines() {
        return outputLines;
    }

    public String build() {
        StringBuilder stringBuilder = new StringBuilder();
        outputLines.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    public String buildAndClear() {
        String result = build();
        clear();
        return result;
    }
}
