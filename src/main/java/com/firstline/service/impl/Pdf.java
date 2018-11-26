package com.firstline.service.impl;

public enum Pdf {
    PDF_BOX("_infoBox.pdf"),
    ITEXT("_info.pdf");

    Pdf(String path) {
        this.path = path;
    }

    private String path;

    public String getPath() {
        return path;
    }

}
