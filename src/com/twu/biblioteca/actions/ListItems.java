package com.twu.biblioteca.actions;

import com.twu.biblioteca.domain.Item;
import com.twu.biblioteca.service.ItemService;
import com.twu.biblioteca.ui.OutputBuilder;

import java.util.List;

public abstract class ListItems<T extends Item> extends MenuAction {

    private final String itemName;
    private final ItemService<T> service;
    private OutputBuilder outputBuilder;

    public ListItems(OutputBuilder outputBuilder, ItemService<T> service, String itemName) {
        this.itemName = itemName;
        this.setDescription(String.format("List of %ss", itemName));

        this.outputBuilder = outputBuilder;
        this.service = service;
    }

    @Override
    public boolean run() {
        outputBuilder.addLine(String.format("====== %ss List ======", itemName));
        List<T> items = service.listAvailableItems();
        printFormattedItemsList(items);
        outputBuilder.addBlankLine();

        return true;
    }

    public OutputBuilder getOutputBuilder() {
        return outputBuilder;
    }

    public abstract void printFormattedItemsList(List<T> items);

}
