/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andhika
 */
public class Pembimbing extends Orang{
    private long nip;
    
    public Pembimbing(String nama, long nip){
        super(nama);
        this.nip = nip;
    }

    public long getNip() {
        return nip;
    }
}