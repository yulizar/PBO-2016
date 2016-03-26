package Model;

import java.util.Date;
import java.io.Serializable;
/**
 *
 * @author Atikawahyu
 */
public abstract class Orang implements Serializable{

    private String nama;
    private Date tglLahir;

    //Pengubahan Ketua kelompok
    public Orang(String nama, Date tglLahir) {
        this.nama = nama;
        this.tglLahir = tglLahir;
    }

    public Orang(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return this.nama;
    }

    //Pengubahan Ketua kelompok

    public int getUmur() {
        Date today = new Date();
        return today.getYear() - tglLahir.getYear();
    }
}
