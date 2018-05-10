
package com.example.lutluthfi.jadwalujianfilkom.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class JadwalResponse {

    @SerializedName("fileName")
    private String mFileName;
    @SerializedName("npages")
    private Long mNpages;
    @SerializedName("pages")
    private List<Page> mPages;

    public String getFileName() {
        return mFileName;
    }

    public void setFileName(String fileName) {
        mFileName = fileName;
    }

    public Long getNpages() {
        return mNpages;
    }

    public void setNpages(Long npages) {
        mNpages = npages;
    }

    public List<Page> getPages() {
        return mPages;
    }

    public void setPages(List<Page> pages) {
        mPages = pages;
    }

}
