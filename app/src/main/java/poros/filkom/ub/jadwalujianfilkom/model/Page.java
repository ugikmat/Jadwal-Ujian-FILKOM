
package poros.filkom.ub.jadwalujianfilkom.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Page {

    @SerializedName("number")
    private Long mNumber;
    @SerializedName("tables")
    private List<Table> mTables;

    public Long getNumber() {
        return mNumber;
    }

    public void setNumber(Long number) {
        mNumber = number;
    }

    public List<Table> getTables() {
        return mTables;
    }

    public void setTables(List<Table> tables) {
        mTables = tables;
    }

}
