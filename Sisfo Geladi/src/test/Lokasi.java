package test;

import java.util.ArrayList;

/**
 *
 * @author Erwin
 */
public class Lokasi {

    private Pembimbing pembimbing;
    private ArrayList<Kelompok> kelompok;
    private String namaPerusahaan;

    public Lokasi(String namaPerusahan) {
        this.kelompok = new ArrayList();
        this.namaPerusahaan = namaPerusahan;
    }

    public void setPembimbing(Pembimbing p) {
        this.pembimbing = p;
    }

    public void createKelompok(Kelompok k) {
        kelompok.add(k);
    }

    public Kelompok getKelompokById(int id) {
        for (Kelompok kelompok1 : kelompok) {
            if (kelompok1.getId() == id) {
                return kelompok1;
            }
        }
        return null;
    }

    public void removeKelompok(int id) {
        int i = 0;
        if (kelompok.get(i) == null) {
            System.out.println("belum ada list kelompok");
        } else {
            for (i = 0; i < kelompok.size(); i++) {
                if (kelompok.get(i).getAnggotaByIndex(i).equals(id)) {
                    kelompok.remove(i);
                }
            }
        }
    }

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public Pembimbing getPembimbing() {
        return pembimbing;
    }
}
