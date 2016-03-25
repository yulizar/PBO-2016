package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Erwin
 */
public class Main {

    public static void main(String[] args) {
        Aplikasi ap = new Aplikasi();
        Lokasi l = new Lokasi("Telkom");
        Lokasi l1= new Lokasi("Bandung");
        ap.addLokasi(l);
        ap.addLokasi(l1);
        ap.viewAllLokasi();
        ap.mainMenu();
//        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//        Date tl = null;
//
//        Lokasi l = new Lokasi("Telkom");
//
//        Kelompok k = new Kelompok(1);
//        l.createKelompok(k);
//        
//        Pembimbing p = new Pembimbing("Pak Bambs",6701020);
//        l.setPembimbing(p);
//        System.out.println("Kelompok "+l.getKelompokById(1).getId());
//        System.out.println("Nama Pembimbing : "+l.getPembimbing().getNama()+"\n");
//        System.out.println("Beranggotakan : ");
//        /*----------------------------------------------------------------------*/
//        try {
//            tl = format.parse("14-07-1996");
//        } catch (Exception e) {
//            System.out.println("Error");
//        }
//        Mahasiswa m = new Mahasiswa("Erwin", tl, "1301144031", "Infomratika");
//
//        l.getKelompokById(1).addAnggota(m);
//
//        System.out.println("Nama : "+l.getKelompokById(1).getAnggotaByNim("1301144031").getNama());
//        System.out.println("Umur : "+l.getKelompokById(1).getAnggotaByIndex(1).getUmur());
//
//        /*----------------------------------------------------------------------*/
//        try {
//            tl = format.parse("12-01-1980");
//        } catch (Exception e) {
//            System.out.println("Error");
//        }
//        Mahasiswa m1 = new Mahasiswa("Gintoki", tl, "111111111", "Samurai");
//        l.getKelompokById(1).addAnggota(m1);
//
//        System.out.println("Nama : "+l.getKelompokById(1).getAnggotaByIndex(2).getNama());
//        System.out.println("Umur : "+l.getKelompokById(1).getAnggotaByIndex(2).getUmur());
    }
}
