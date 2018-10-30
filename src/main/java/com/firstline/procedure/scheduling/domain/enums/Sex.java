package com.firstline.procedure.scheduling.domain.enums;

public enum Sex {
    MALE(1), FEMALE(2);

    private int id;

    Sex(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Sex valueOf(int id) {
        for (Sex sex : values()) {
            if(sex.id == id)
                return sex;
        }
        throw new IllegalArgumentException();
    }

}
