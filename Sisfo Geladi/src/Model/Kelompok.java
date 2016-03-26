package Model;

import java.util.ArrayList;

/**
 *
 * @author Andhika
 */
public class Kelompok {

    private ArrayList<Mahasiswa> anggota = new ArrayList(); //Pengubahan ketua kelompok
    private int id; //penambahan ketua kelompok
    private static int count = 1;

    //penambahan ketua kelompok
    public Kelompok() {
        id = count;
        count++;

    }

    public void addAnggota(Mahasiswa m) {
        anggota.add(m);
    }

    public Mahasiswa getAnggotaByIndex(int idx) {
        return anggota.get(idx - 1);
    }

    public Mahasiswa getAnggotaByNim(String nim) {
        for (Mahasiswa anggota1 : anggota) {
            if (anggota1.getNim().equals(nim)) {
                return anggota1;
            }
        }
        return null;
    }

    //Pengubahan Ketua kelompok
    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Kelompok " + getId() + '{' + "anggota=" + anggota + '}';
    }
}
