/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Date;
/**
 *
 * @author AtikaWahyu
 */
public abstract class Orang {
    private String nama;
    private Date tglLahir;
    
    public Orang (String nama, Date tglLahir){
        this.nama=nama;
        tglLahir = new Date(1996-1900,7,14);
    }

    public Orang (String nama){
        this.nama=nama;
    }
    
    public String getNama(){
        return this.nama;
    }
    
    public int getUmur(){
        Date today = new Date();
        return today.getYear()-tglLahir.getYear();
    }
    
}


