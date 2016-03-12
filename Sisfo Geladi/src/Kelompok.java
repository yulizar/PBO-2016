/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
/**
 *
 * @author Andhika
 */
public class Kelompok {
    private ArrayList<Mahasiswa> anggota = new ArrayList<Mahasiswa>();
    
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
}