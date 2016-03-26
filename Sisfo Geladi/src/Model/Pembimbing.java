package Model;
import java.io.Serializable;
/**
 *
 * @author Andhika
 */
public class Pembimbing extends Orang implements Serializable{
    private String nip;
    
    public Pembimbing(String nama, String nip){
        super(nama);
        this.nip = nip;
    }

    public String getNip() {
        return nip;
    }

    @Override
    public String toString() {
        return "Pembimbing : "+super.getNama()+ ", nip= " + nip;
    }
}