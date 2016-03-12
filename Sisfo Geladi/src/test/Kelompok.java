package test;


import java.util.ArrayList;
/**
 *
 * @author Andhika
 */
public class Kelompok {
    private ArrayList<Mahasiswa> anggota = new ArrayList(); //Pengubahan ketua kelompok
    private int id; //penambahan keta kelompok
    
    //penambahan ketua kelompok
    public Kelompok (int id){
        this.id = id;
    }
    
    public void addAnggota (Mahasiswa m){
        anggota.add(m);
    }
    
    public Mahasiswa getAnggotaByIndex (int idx){
        return anggota.get(idx-1);
    }
    
    public Mahasiswa getAnggotaByNim(String nim){
        for (Mahasiswa anggota1 : anggota) {
            if (anggota1.getNim().equals(nim)) {
                return anggota1;
            }
        }       
        return null;
    }
    
    //Pengubahan Ketua kelompok
    public int getId(){
        return id;
    }
}
