
package com.example.lutluthfi.jadwalujianfilkom.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Table {

    @SerializedName("cells")
    private List<Cell> mCells;

    public List<Cell> getCells() {
        return mCells;
    }

    public void setCells(List<Cell> cells) {
        mCells = cells;
    }

}
