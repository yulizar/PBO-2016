package Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Erwin
 */
public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        Aplikasi ap = new Aplikasi();

//        ap.load();
//        ArrayList<Pembimbing> ll = ap.getDaftarPembimbing();
//        ll.forEach(System.out::println);
        new Control.Controller(ap);
        ap.mainMenu();
//        System.out.println(ap.getKelompok("Telkom Bandung").toString());
//        ArrayList<Lokasi> lk = ap.getDaftarLokasi();
//        lk.forEach(System.out::println);
//        Lokasi l = new Lokasi("ABC");
//        Lokasi l1= new Lokasi("KKKKKK");
//        Kelompok k = new Kelompok();
//        try {
//            k.addAnggota(new Mahasiswa("Agus", format.parse("14-07-1996"), "1301144031", "Elektro"));
//        } catch (ParseException ex) {
//            System.out.println(ex.getMessage());
//        }
//        l.createKelompok(k);
//        lk.add(l);
//        System.out.println(lk.get(0).getKelompok());
    }
}
