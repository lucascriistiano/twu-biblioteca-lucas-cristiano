package com.twu.biblioteca.actions;

import com.twu.biblioteca.domain.Item;
import com.twu.biblioteca.service.ItemService;
import com.twu.biblioteca.service.exception.NonExistentItemException;
import com.twu.biblioteca.service.exception.UnavailableItemException;
import com.twu.biblioteca.ui.BibliotecaCLI;
import com.twu.biblioteca.ui.OutputBuilder;
import com.twu.biblioteca.ui.exception.InvalidOptionException;

public class CheckoutItem<T extends Item> extends MenuAction<T> {

    private final ItemService<T> service;
    private final String itemName;
    private OutputBuilder outputBuilder;
    private BibliotecaCLI cli;

    public CheckoutItem(OutputBuilder outputBuilder, BibliotecaCLI cli, ItemService<T> service, String itemName) {
        super(String.format("Checkout %s", itemName));
        this.service = service;
        this.itemName = itemName;
        this.outputBuilder = outputBuilder;
        this.cli = cli;
    }

    @Override
    public boolean run() {
        outputBuilder.addLine(String.format("====== %s Checkout ======", this.itemName));
        outputBuilder.add(String.format("Type the ID of the %s to checkout: ", this.itemName.toLowerCase()));
        cli.showOutput(outputBuilder.buildAndClear());

        try {
            Integer itemID = BibliotecaCLI.parseMenuInput(cli.readInput());
            service.checkoutItem(itemID);

            outputBuilder.addLine(String.format("Thank you! Enjoy the %s", this.itemName.toLowerCase()));
        } catch (InvalidOptionException | NonExistentItemException | UnavailableItemException e) {
            outputBuilder.addLine(String.format("Sorry, that %s is not available", this.itemName.toLowerCase()));
        }
        return true;
    }

}
