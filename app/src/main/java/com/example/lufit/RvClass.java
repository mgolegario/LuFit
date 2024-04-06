package com.example.lufit;

public class RvClass {

    public static final int LAYOUT_UM = 1;
    public static final int LAYOUT_DOIS = 2;

    private final int viewType;
    private String message;

    public RvClass(int viewType, String message) {
        this.viewType = viewType;
        this.message = message;
    }

    public int getViewType() {
        return viewType;
    }

    public String getMessage() {
        return message;
    }
}
