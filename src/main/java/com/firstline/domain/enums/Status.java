package com.firstline.procedure.scheduling.domain.enums;

public enum Status {
    PLANNED(1), IN_PROGRESS(2), FINISHED(3);

    private int id;

    Status(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Status valueOf(int id) {
        for (Status status : values()) {
            if(status.id == id)
                return status;
        }
       throw new IllegalArgumentException();
    }

}
