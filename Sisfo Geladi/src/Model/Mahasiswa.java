package Model;

import java.util.Date;
import java.io.Serializable;

/**
 *
 * @author Atikawahyu
 */
public class Mahasiswa extends Orang implements Serializable {

    private String nim;
    private String jurusan;
    private String username;
    private String password;

    public Mahasiswa(String nama, Date tglLahir, String nim, String jurusan) {
        super(nama, tglLahir);
        this.nim = nim;
        this.jurusan = jurusan;
    }

    public String getNim() {
        return nim;
    }

    public String getJurusan() {
        return jurusan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Mahasiswa{" + "nim=" + nim + ", jurusan=" + jurusan + '}';
    }

}
