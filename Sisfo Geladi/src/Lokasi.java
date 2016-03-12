/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;

/**
 *
 * @author Erwin
 */
public class Lokasi {
    private Pembimbing pembimbing;
    private ArrayList<Kelompok> kelompok;
    private String namaPerusahaan;
    
    public Lokasi (String namaPerusahan){
        this.namaPerusahaan = namaPerusahan;
    }
    
    public void setPembimbing(Pembimbing p){
        this.pembimbing = p;
    }
    
    public void createKelompok(int id){
        kelompok = new ArrayList<Kelompok>(id);
        //buat object array list dengan isi object Kelompok
    }
    
    public Kelompok getKelompokById(int id){
        return kelompok.get(id);
    }
    
    public void removeKelompok (int id){
        int i=0;
        if (kelompok.get(i) == null){
            System.out.println("belum ada list kelompok");
        } else {
        for (i=0; i<kelompok.size(); i++) {
            if ( kelompok.get(i).getAnggotaByIndex(i).equals(id)){
                kelompok.remove(i);
            }
        }
    }
    }
}
