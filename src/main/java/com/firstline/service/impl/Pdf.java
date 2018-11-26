package com.firstline.service.impl;

public enum Pdf {
    PDF_BOX,
    ITEXT;


    @Override
    public String toString() {
        switch (this) {
            case PDF_BOX:
                return "_infoBox.pdf";
            case ITEXT:
                return "_info.pdf";
            default:
                throw new IllegalArgumentException();
        }
    }
}
