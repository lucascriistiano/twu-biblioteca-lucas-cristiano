package com.twu.biblioteca.service;

import com.twu.biblioteca.domain.Item;
import com.twu.biblioteca.service.exception.NonExistentItemException;
import com.twu.biblioteca.service.exception.NotCheckedOutItemException;
import com.twu.biblioteca.service.exception.UnavailableItemException;

import java.util.List;

public interface ItemServiceI<T extends Item> {

    List<T> listItems();
    List<T> listAvailableItems();
    void checkoutItem(Integer id) throws UnavailableItemException;
    void returnItem(Integer id) throws NotCheckedOutItemException;
    T findItem(Integer id) throws NonExistentItemException;

}
