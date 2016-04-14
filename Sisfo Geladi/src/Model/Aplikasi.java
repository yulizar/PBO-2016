package Model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;

/**
 *
 * @author Erwin
 */
public class Aplikasi {

    Scanner inputInteger = new Scanner(System.in);
    Scanner inputString = new Scanner(System.in);
    Scanner inputDate = new Scanner(System.in);
    Scanner inputLong = new Scanner(System.in);
    private ArrayList<Pembimbing> daftarPembimbing;
    private ArrayList<Mahasiswa> daftarMahasiswa; // Pembimbing dan mahasiswa dapat dijadikan 1 array
    private ArrayList<Lokasi> daftarLokasi; //Sudah ada array list kelompok di dalam lokasi, jadi ga perlu array list kelompok
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

    public Aplikasi() {
        daftarPembimbing = new ArrayList<>();
        daftarMahasiswa = new ArrayList<>();
        daftarLokasi = new ArrayList<>();
    }

    public Object getObject(String file)
            throws FileNotFoundException, IOException,
            ClassNotFoundException, EOFException {
        ObjectInputStream ois
                = new ObjectInputStream(new FileInputStream(file));
        return ois.readObject();
    }

    public void saveObject(Object o, String file)
            throws FileNotFoundException, IOException {
        FileOutputStream fout = new FileOutputStream(file);
        ObjectOutputStream oout = new ObjectOutputStream(fout);
        oout.writeObject(o);
        oout.flush();
    }

    public void load() throws IOException, FileNotFoundException, ClassNotFoundException {
        try {
            daftarPembimbing = (ArrayList<Pembimbing>) getObject("pembimbing.txt");
            daftarMahasiswa = (ArrayList<Mahasiswa>) getObject("mahasiswa.txt");
            daftarLokasi = (ArrayList<Lokasi>) getObject("lokasi.txt");
        } catch (IOException ex) {
            System.out.println("Ada error");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error : " + ex.getMessage());
        }

    }

    /*Array Pembimbing*/
    public void addPembimbing(String nama, long nip) {
        daftarPembimbing.add(new Pembimbing(nama, nip));
    }

    public Pembimbing searchPembimbing(long nip) {
        for (Pembimbing p1 : daftarPembimbing) {
            if (p1.getNip() == nip) {
                return p1;
            }
        }
        return null;
    }

    public void deletePembimbing(long nip) {
        for (Pembimbing p1 : daftarPembimbing) {
            if (p1.getNip() == nip) {
                daftarPembimbing.remove(p1);
            }
        }
    }

    public ArrayList<Pembimbing> getPembimbingFromFile() {
        try {
            FileInputStream fs = new FileInputStream("pembimbing.txt");
            ObjectInputStream os = new ObjectInputStream(fs);
            return (ArrayList<Pembimbing>) os.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    /*Array Mahasiswa*/
    public void addMahasiswa(String nama, Date tglLahir, String nim, String jurusan) {
        daftarMahasiswa.add(new Mahasiswa(nama, tglLahir, nim, jurusan));
    }

    public Mahasiswa searchMahasiswa(String nim) {
        for (Mahasiswa m1 : daftarMahasiswa) {
            if (m1.getNim().equals(nim)) {
                return m1;
            }
        }
        return null;
    }

    public void deleteMahasiswa(String nim) {
        for (Mahasiswa m1 : daftarMahasiswa) {
            if (m1.getNim().equals(nim)) {
                daftarMahasiswa.remove(m1);
            }
        }
    }

    public ArrayList<Mahasiswa> getMahasiswaFromFile() {
        try {
            FileInputStream fs = new FileInputStream("mahasiswa.txt");
            ObjectInputStream os = new ObjectInputStream(fs);
            return (ArrayList<Mahasiswa>) os.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    /*Array Lokasi*/

    public void addLokasi(Lokasi l) {
        daftarLokasi.add(l);
    }

    public Lokasi searchLokasi(int id) {
        for (Lokasi l1 : daftarLokasi) {
            if (l1.getId() == id) {
                return l1;
            }
        }
        return null;
    }

    public void deleteLokasi(String nama) {
        for (Lokasi l1 : daftarLokasi) {
            if (l1.getNamaPerusahaan().equals(nama)) {
                daftarLokasi.remove(l1);
            }
        }
    }

    public void viewAllLokasi() {
        daftarLokasi.forEach(System.out::println);
    }

    public ArrayList<Lokasi> getLokasiFromFile() {
        try {
            FileInputStream fs = new FileInputStream("lokasi.txt");
            ObjectInputStream os = new ObjectInputStream(fs);
            return (ArrayList<Lokasi>) os.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void menuDaftarGeladi(Mahasiswa m) {
        System.out.println("Lokasi \n");
        viewAllLokasi();
        try {
            System.out.print("Pilihan Lokasi : ");
            int inputLokasi = inputInteger.nextInt();
            Lokasi searchLokasi = searchLokasi(inputLokasi);
            searchLokasi.getKelompok().forEach(System.out::println);
            System.out.println("Pilih Kelompok mana yang anda ingin bergabung : ");
            int inputKelompok = inputInteger.nextInt();
            Kelompok kelompokById = searchLokasi.getKelompokById(inputKelompok);
            if (kelompokById.getId() == 10) {
                System.out.println("Maaf Kelompok ini penuh");
            } else {
                kelompokById.addAnggota(m);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void menuDataPembimbing() {
//        menu menampilkan semua data pembimbing dan Tambah pembimbing
        int pilihanMenu = 1;
        while (pilihanMenu != 0) {

            System.out.println("Menu Data Pembimbing");
            System.out.println("1. Data Pembimbing");
            System.out.println("2. Tambah Pembimbing");
            System.out.println("0. Back");
            System.out.print("Pilihan anda : ");
            try {
                int inputMenu = inputInteger.nextInt();
                switch (inputMenu) {
                    case 1:
                        viewAllPembimbing();//Aman
                        break;
                    case 2:
                        try {
                            System.out.print("Masukkan nama \t :");
                            String inputNama = inputString.nextLine();//Gamau nge input pas awal ??
                            System.out.print("Masukkan nip \t :");
                            long inputNip = inputLong.nextLong();
                            addPembimbing(inputNama, inputNip);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 0:
                        pilihanMenu = 0;
                        break;
                    default:
                        System.out.println("Menu yang anda masukkan salah");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Inputan yang harus anda masukkan berupa angka 1/2");
            }
        }
    }

    public void viewAllPembimbing() {
//        mengoutputkan semua data Pembimbing
        daftarPembimbing.forEach(System.out::println);
    }

    public void menuLokasiAdmin() {
        int inputMenu = 1;
        do {
            try {
                System.out.println("Menu Lokasi");
                System.out.println("1. List Lokasi");
                System.out.println("2. Daftar Kelompok per Lokasi");
                System.out.println("3. Set Pembimbing");
                System.out.println("0. Exit");
                System.out.print("Pilihan Anda : ");
                inputMenu = inputInteger.nextInt();
                switch (inputMenu) {
                    case 1:
                        viewAllLokasi();//Aman
                        break;
                    case 2:
                        viewAllLokasi();
                        System.out.print("Pilih Id Lokasi : ");
                        int inputLokasi = inputInteger.nextInt();
                        Lokasi tampung = searchLokasi(inputLokasi);
                        //VIew all Mahasiswa dari satu lokasi yang ditunjuk
                        tampung.getKelompok().forEach(System.out::println);//Belum Selesai
                        break;
                    case 3:
                        //Mengset Pembimbing
                        Pembimbing pSet = null;
                        daftarPembimbing.forEach(System.out::println);
                        System.out.print("Pilih NIP Pembimbing yang akan di set : ");
                        long nip = inputLong.nextLong();
                        pSet = searchPembimbing(nip);
                        viewAllLokasi();
                        System.out.print("Pilih id Lokasi yang akan di set Pembimbing : ");
                        int pilihanLokasi = inputInteger.nextInt();
                        Lokasi l = searchLokasi(pilihanLokasi);
                        System.out.println(l.getNamaPerusahaan());
                        l.setPembimbing(pSet);
                        System.out.println(l.getPembimbing().getNama());
                        break;
                    default:
                        System.out.println("Menu yang anda masukkan salah");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Inputan yang anda masukkan salah");
            }
        } while (inputMenu != 0);
    }

    public void menuLokasiMahasiswa() {
        System.out.println("Menu Lokasi");
        System.out.println("1. Pilih Lokasi");
        System.out.println("2. Pilih Kelompok");
        System.out.print("Pilihan Anda : ");
        try {
            int inputMenu = inputInteger.nextInt();
            switch (inputMenu) {
                case 1:
                    viewAllLokasi();
                    System.out.print("Pilih id Lokasi : ");
                    int inputLokasi = inputInteger.nextInt();
                    Lokasi searchLokasi = searchLokasi(inputLokasi);
                    try {
                        searchLokasi.getKelompok().forEach(System.out::println);
                    } catch (InputMismatchException e) {
                        System.out.println("Salah input");
                    }
                    break;
//                case 2: 
//                    break;
                default:
                    System.out.println("Menu yang anda masukkan salah");
            }
        } catch (InputMismatchException e) {
            System.out.println("Inputan yang anda masukkan salah");
        }
    }

    public void menuKelompok() {
        int inputMenu = 1;
        do {
            System.out.println("Menu Kelompok");
            System.out.println("1. List Kelompok");
            System.out.println("2. Tambah Kelompok");
            System.out.println("3. Hapus Kelompok");
            System.out.println("0. back");
            System.out.print("Pilihan Anda : ");
            inputMenu = inputInteger.nextInt();
            switch (inputMenu) {
                case 1:
                    viewAllLokasi();
                    System.out.print("Pilih id Lokasi untuk melihat seluruh kelompok : ");
                    int inputLokasi = inputInteger.nextInt();
                    Lokasi lokasiKelompok = searchLokasi(inputLokasi);
                    System.out.println(lokasiKelompok);
                    viewAllKelompok(lokasiKelompok);//Done
                    break;
                case 2:
//            daftarLokasi.
                    viewAllLokasi();
                    System.out.print("Pilih id Lokasi untuk menambah kelompok : ");
                    int pilihLokasi = inputInteger.nextInt();
                    Lokasi pilihKelompok = searchLokasi(pilihLokasi);
                    Kelompok k = new Kelompok();
                    pilihKelompok.createKelompok(k);
                    viewAllKelompok(pilihKelompok);
//            Menambah Kelompok
                    break;
                case 3:
                    viewAllLokasi();//under construction
                    System.out.print("Pilih id Lokasi untuk melihat seluruh kelompok : ");
                    int idLokasi = inputInteger.nextInt();
                    Lokasi chooseKelompok = searchLokasi(idLokasi);
                    viewAllKelompok(chooseKelompok);
                    System.out.print("Masukkan id kelompok yang akan di hapus : ");
                    try {
                        int inputIdx = inputInteger.nextInt();
                        for (int i = 0; i < chooseKelompok.getKelompok().size(); i++) {
                            if (chooseKelompok.getKelompokById(i).getId() == inputIdx) {
                                chooseKelompok.removeKelompok(i);
                            }
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Inputan anda salah");
                    }
                    break;
                case 0:
                    inputMenu = 0;
                    break;
                default : 
                    System.out.println("Menu yang anda inputkan salah");
                    inputMenu = 1;
                    break;
            }
        } while (inputMenu != 0);
    }

    public void viewAllKelompok(Lokasi l) {
//        Menampilkan kelompok berdasarkan lokasi
        System.out.println(l.getKelompok());
        
    }

    public void create() {
        try {
            Mahasiswa m1 = new Mahasiswa("Erwin", format.parse("14-07-1996"), "1301144031", "Informatika");
            m1.setUsername(m1.getNim());
            System.out.println(m1.getUsername());
            m1.setPassword("mahasiswa123");
            Mahasiswa m2 = new Mahasiswa("Gintoki", format.parse("17-09-1987"), "1301140199", "Elektro");
            m2.setUsername(m2.getNim());
            m2.setPassword("mahasiswa123");
            Mahasiswa m3 = new Mahasiswa("Sinpachi", format.parse("20-01-1999"), "1301141233", "Elektro");
            m2.setUsername(m3.getNim());
            m2.setPassword("mahasiswa123");
            Mahasiswa m4 = new Mahasiswa("Kagura", format.parse("10-08-2000"), "1301143123", "Elektro");
            m2.setUsername(m4.getNim());
            m2.setPassword("mahasiswa123");
            Mahasiswa m5 = new Mahasiswa("Sadaharu", format.parse("17-09-1997"), "1301149432", "Elektro");
            m2.setUsername(m5.getNim());
            m2.setPassword("mahasiswa123");
            daftarMahasiswa.add(m1);
            daftarMahasiswa.add(m2);
            daftarMahasiswa.add(m3);
            daftarMahasiswa.add(m4);
            daftarMahasiswa.add(m5);

            Lokasi l1 = new Lokasi("Telkom Bandung");
            Lokasi l2 = new Lokasi("Telkom Jakarta");
            Lokasi l3 = new Lokasi("Telkom Semarang");
            Lokasi l4 = new Lokasi("Telkom Medan");
            Lokasi l5 = new Lokasi("Telkom Pontianak");
            Lokasi l6 = new Lokasi("Telkom Makassar");
            daftarLokasi.add(l1);
            daftarLokasi.add(l2);
            daftarLokasi.add(l3);
            daftarLokasi.add(l4);
            daftarLokasi.add(l5);
            daftarLokasi.add(l6);

            Pembimbing p1 = new Pembimbing("Bambs", 67012345);
            Pembimbing p2 = new Pembimbing("Adam", 67023132);
            Pembimbing p3 = new Pembimbing("Yuslan", 6704123);
            daftarPembimbing.add(p1);
            daftarPembimbing.add(p2);
            daftarPembimbing.add(p3);
            
            Kelompok k = new Kelompok();
            k.addAnggota(m1);
            k.addAnggota(m2);
            daftarLokasi.get(0).createKelompok(k);
            

            Admin admin = new Admin("Admin");
            admin.setUsername("admin");
            admin.setPassword("admin123");

            FileOutputStream f1 = new FileOutputStream("mahasiswa.txt");
            ObjectOutputStream obj1 = new ObjectOutputStream(f1);

            FileOutputStream f2 = new FileOutputStream("lokasi.txt");
            ObjectOutputStream obj2 = new ObjectOutputStream(f2);

            FileOutputStream f3 = new FileOutputStream("admin.txt");
            ObjectOutputStream obj3 = new ObjectOutputStream(f3);

            FileOutputStream f4 = new FileOutputStream("pembimbing.txt");
            ObjectOutputStream obj4 = new ObjectOutputStream(f4);

            obj1.writeObject(daftarMahasiswa);
            obj2.writeObject(daftarLokasi);
            obj3.writeObject(admin);
            obj4.writeObject(daftarPembimbing);

            obj1.flush();
            obj2.flush();
            obj3.flush();
            obj4.flush();

//            obj1.close();
//            obj2.close();
//            obj3.close();
//            obj4.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void menuSatu() {
        int pilihanMenu = 1;
        while (pilihanMenu != 0) {
            System.out.println("\t \t \t Menu Admin");
            System.out.println("Pilihan Menu");
            System.out.println("1. Menu Data Pembimbing");
            System.out.println("2. Menu Lokasi");
            System.out.println("3. Menu Kelompok");
            System.out.println("0. Back");
            System.out.print("Pilihan Anda : ");
            int inputMenu = inputInteger.nextInt();
            switch (inputMenu) {
                case 1:
                    menuDataPembimbing(); //Done
                    break;
                case 2:
                    menuLokasiAdmin();//Done
                    break;
                case 3:
                    menuKelompok();
                    break;
                case 0:
                    pilihanMenu = 0;
                    break;
                default:
                    System.out.println("Menu yang anda masukkan salah");
                    break;
            }
        }
    }//Sector Clear

    public void menuDua(Mahasiswa m) {
        int pilihanMenu = 1;
        while (pilihanMenu != 0) {
            System.out.println("\t \t \t Menu Mahasiswa");
            System.out.println("1. Daftar Geladi");
            System.out.println("2. Menu Lokasi");
            System.out.println("0. Back");
            System.out.print("Pilihan Anda : ");
            int inputMenu = inputInteger.nextInt();
            switch (inputMenu) {
                case 1:
                    menuDaftarGeladi(m);
                    break;
                case 2:
                    menuLokasiMahasiswa();
                    break;
                case 0:
                    pilihanMenu = 0;
                    break;
                default:
                    System.out.println("Menu yang anda masukkan salah");
                    break;
            }
        }
    }

    public void mainMenu() throws IOException, FileNotFoundException, ClassNotFoundException{
        File file1 = new File("mahasiswa.txt");
        File file2 = new File("lokasi.txt");
        File file3 = new File("admin.txt");
        File file4 = new File("pembimbing.txt");
        if ((file1.exists() && file2.exists()) && file3.exists() && file4.exists()) {
        } else {
            create();
        }
        load();
//        Lokasi l = daftarLokasi.get(0);
//        ArrayList<Kelompok> lk = l.getKelompok();
//        Kelompok k = new Kelompok();
//        k.addAnggota(new Mahasiswa("asdasd", format.parse("14-07-1999"), "123123123", "PBO"));
//        
//        System.out.println(l);
//        System.out.println(k);
        int pilihanMenu = 1;
       
        while (pilihanMenu != 0) {
            System.out.println("\t \t \t Selamat Datang di Program Sisfo Geladi \n");
            System.out.println("Pilihan Menu ");
            System.out.println("1. Login Admin");
            System.out.println("2. Login Mahasiswa");
            System.out.println("0. Exit");
            System.out.print("Pilihan Anda : ");
            try {
                int inputMenu = inputInteger.nextInt();
                switch (inputMenu) {
                    case 1:
                        System.out.print("Masukkan Username : ");
                        String inputUser = inputString.next();
                        System.out.print("Masukkan Password : ");
                        String inputPass = inputString.next();
                        try {
                            FileInputStream fInput = new FileInputStream("admin.txt");
                            ObjectInputStream output = new ObjectInputStream(fInput);
                            Admin adm = (Admin) output.readObject();
                            if ((adm.getUsername().equals(inputUser)) && (adm.getPassword().equals(inputPass))) {
                                System.out.println("Berhasil");
                                menuSatu();
                            } else {
                                System.out.println("Username/ Password yang anda masukkan salah");
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.print("Masukkan NIM : ");
                        String inputUserMhs = inputString.nextLine();
                        System.out.print("Masukkan Password : ");
                        String inputPassMhs = inputString.nextLine();
                        try {
                            FileInputStream fInput2 = new FileInputStream("mahasiswa.txt");
                            ObjectInputStream output2 = new ObjectInputStream(fInput2);

                            ArrayList<Mahasiswa> mhs = (ArrayList) output2.readObject();
                            for (Mahasiswa m : mhs) {
                                if (m.getUsername().equals(inputUserMhs) && m.getPassword().equals(inputPassMhs)) {
                                    menuDua(m);
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Username/ password yang anda masukkan salah");
                        }
                        break;
                    case 0:
                        pilihanMenu = 0;
                        break;
                    default:
                        System.out.println("Menu yang anda masukkan salah");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Inputan anda salah");
                pilihanMenu = 0;
            }
        }
    }
}
