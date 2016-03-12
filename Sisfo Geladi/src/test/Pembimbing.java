package test;

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