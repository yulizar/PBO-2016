package test;

import java.util.Date;

/**
 *
 * @author Atikawahyu
 */
public class Mahasiswa extends Orang{
    private String nim;
    private String jurusan;
    
    public Mahasiswa (String nama,Date tglLahir, String nim, String jurusan){
        super(nama,tglLahir);
        this.nim = nim;
        this.jurusan = jurusan;
    }

    public String getNim() {
        return nim;
    }

    public String getJurusan() {
        return jurusan;
    }
}
