
package com.example.lutluthfi.jadwalujianfilkom.model;

import com.google.gson.annotations.SerializedName;

public class Cell {

    @SerializedName("content")
    private String mContent;
    @SerializedName("i")
    private Long mI;
    @SerializedName("j")
    private Long mJ;

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public Long getI() {
        return mI;
    }

    public void setI(Long i) {
        mI = i;
    }

    public Long getJ() {
        return mJ;
    }

    public void setJ(Long j) {
        mJ = j;
    }

}
