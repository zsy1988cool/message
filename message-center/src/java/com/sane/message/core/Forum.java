package com.sane.message.core;

public enum Forum {
    SCHOOL(1), HOSPITAL(2);

    private int identifier;

    private Forum(int idy) {
        identifier = idy;
    }
}
