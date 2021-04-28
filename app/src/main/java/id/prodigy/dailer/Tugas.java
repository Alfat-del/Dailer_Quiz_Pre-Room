package id.prodigy.dailer;

import java.sql.Time;
import java.util.Date;

public class Tugas {
    private String kategoriTugas;
    private String pelajaran;
    private String topik;
    private String waktuDeadline;

    private String tanggalDeadline;
    private String catatan;



    private boolean expandable;

    public Tugas(String pelajaran, String topik, String waktuDeadline, String tanggalDeadline, String kategoriTugas, String catatan) {
        this.pelajaran = pelajaran;
        this.topik = topik;
        this.waktuDeadline = waktuDeadline;
        this.tanggalDeadline = tanggalDeadline;
        this.kategoriTugas = kategoriTugas;
        this.catatan = catatan;
        this.expandable = false;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getPelajaran() {
        return pelajaran;
    }

    public void setPelajaran(String pelajaran) {
        this.pelajaran = pelajaran;
    }

    public String getTopik() {
        return topik;
    }

    public void setTopik(String topik) {
        this.topik = topik;
    }

    public String getWaktuDeadline() {
        return waktuDeadline;
    }

    public void setWaktuDeadline(String waktuDeadline) {
        this.waktuDeadline = waktuDeadline;
    }

    public String getTanggalDeadline() {
        return tanggalDeadline;
    }

    public void setTanggalDeadline(String tanggalDeadline) {
        this.tanggalDeadline = tanggalDeadline;
    }

    public String getKategoriTugas() {
        return kategoriTugas;
    }

    public void setKategoriTugas(String kategoriTugas) {
        this.kategoriTugas = kategoriTugas;
    }

}
