package ru.evteev.sportapp.domain;

public class Sport {
    public int id;
    public String name;

    public Sport(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return id + ". " + name;
    }
}
