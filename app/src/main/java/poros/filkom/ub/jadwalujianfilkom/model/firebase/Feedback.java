package poros.filkom.ub.jadwalujianfilkom.model.firebase;

public class Feedback {

    private String nama;
    private String description;

    public Feedback(String nama, String description) {
        this.nama = nama;
        this.description = description;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
