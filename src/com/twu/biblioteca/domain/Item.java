package com.twu.biblioteca.domain;

public abstract class Item {

    private Integer id;
    private ItemStatus status;

    public Item(Integer id) {
        this(id, ItemStatus.AVAILABLE);
    }

    public Item(Integer id, ItemStatus status) {
        this.id = id;
        this.status = ItemStatus.AVAILABLE;
    }

    public Integer getId() {
        return id;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

}
