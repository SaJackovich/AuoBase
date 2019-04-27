package com.project.db.constant;

public enum JourneyStatus {

    OPENED("opened"),
    CLOSED("closed"),
    PROCESS("process"),
    CANCELED("canceled");

    private String status;

    JourneyStatus(String status) {
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

}
