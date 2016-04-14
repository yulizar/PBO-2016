package Model;
import java.io.Serializable;
/**
 *
 * @author Andhika
 */
public class Pembimbing extends Orang implements Serializable{
    private long nip;
    
    public Pembimbing(String nama, long nip){
        super(nama);
        this.nip = nip;
    }

    public long getNip() {
        return nip;
    }

    @Override
    public String toString() {
        return "Pembimbing : "+super.getNama()+ ", nip= " + nip;
    }
}