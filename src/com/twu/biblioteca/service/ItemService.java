package com.twu.biblioteca.service;

import com.twu.biblioteca.domain.Item;
import com.twu.biblioteca.domain.ItemStatus;
import com.twu.biblioteca.service.exception.NonExistentItemException;
import com.twu.biblioteca.service.exception.NotCheckedOutItemException;
import com.twu.biblioteca.service.exception.UnavailableItemException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ItemService<T extends Item> implements ItemServiceI<T> {

    private List<T> items;

    public ItemService() {
        this.items = new ArrayList<>();
    }

    public ItemService(List<T> items) {
        this.items = items;
    }

    @Override
    public List<T> listItems() {
        return items;
    }

    @Override
    public List<T> listAvailableItems() {
        return items.stream().filter(b -> b.getStatus() == ItemStatus.AVAILABLE).collect(Collectors.toList());
    }

    @Override
    public void checkoutItem(Integer id) throws UnavailableItemException {
        T item = findItem(id);
        if (item.getStatus() == ItemStatus.NOT_AVAILABLE) {
            throw new UnavailableItemException();
        }
        item.setStatus(ItemStatus.NOT_AVAILABLE);
    }

    @Override
    public void returnItem(Integer id) throws NotCheckedOutItemException {
        T item = findItem(id);
        if (item.getStatus() == ItemStatus.AVAILABLE) {
            throw new NotCheckedOutItemException();
        }
        item.setStatus(ItemStatus.AVAILABLE);
    }

    @Override
    public T findItem(Integer id) throws NonExistentItemException {
        T item = items.stream()
                .filter(b -> b.getId().equals(id))
                .findAny()
                .orElse(null);
        if (item == null) {
            throw new NonExistentItemException();
        }
        return item;
    }

}
