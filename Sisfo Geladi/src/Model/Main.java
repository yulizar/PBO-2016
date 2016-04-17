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

        ap.load();
//        ArrayList<Pembimbing> ll = ap.getDaftarPembimbing();
//        ll.forEach(System.out::println);
        new Control.Controller(ap);
//        ArrayList<Lokasi> lk = ap.getDaftarLokasi();
//        lk.forEach(System.out::println);
        ap.mainMenu();
        System.out.println(ap.getDaftarLokasi().get(0).getKelompok().toString());
    }
}
