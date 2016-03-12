/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Date;
/**
 *
 * @author Atikawahyu
 */
public class Mahasiswa extends Orang {
    private String nim;
    private String jurusan;
    
    public Mahasiswa (String nama,Date tglLahir){
        super(nama,tglLahir);
        this.nim=nim;
        this.jurusan=jurusan;
    }
    
    public String getNim(){
        return nim;
    }
    
    public String getJurusan(){
        return jurusan;
    }
        
}
