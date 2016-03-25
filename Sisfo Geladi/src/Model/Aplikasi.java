package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
        
        File file = new File("mahasiswa.txt");
        File file1 = new File("lokasi.txt");
        File file2 = new File("admin.txt");
        if (!file.exists() && !file1.exists() && !file2.exists()){
            create();
        }
    }

    public void create() {
        try {
            Mahasiswa m1 = new Mahasiswa("Erwin", format.parse("14-07-1996"), "1301144031", "Informatika");
            Mahasiswa m2 = new Mahasiswa("Gintoki", format.parse("17-09-1997"), "1301140199", "Elektro");
            daftarMahasiswa.add(m1);
            daftarMahasiswa.add(m2);

            Lokasi l1 = new Lokasi("Telkom");
            daftarLokasi.add(l1);

            Pembimbing p1 = new Pembimbing("Bambs", 67012345);
            daftarPembimbing.add(p1);

            Admin admin = new Admin("Admin");
            admin.setUsername("admin123");
            admin.setPassword("qwerty");

            FileOutputStream f1 = new FileOutputStream("mahasiswa.txt");
            ObjectOutputStream obj1 = new ObjectOutputStream(f1);

            FileOutputStream f2 = new FileOutputStream("lokasi.txt");
            ObjectOutputStream obj2 = new ObjectOutputStream(f2);

            FileOutputStream f3 = new FileOutputStream("admin.txt");
            ObjectOutputStream obj3 = new ObjectOutputStream(f3);

            obj1.writeObject(daftarMahasiswa);
            obj2.writeObject(daftarLokasi);
            obj3.writeObject(admin);

            obj1.flush();
            obj2.flush();
            obj3.flush();

            obj1.close();
            obj2.close();
            obj3.close();
        } catch (Exception e) {

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

    /*Array Lokasi*/
    public void addLokasi(Lokasi l) {
        daftarLokasi.add(l);
    }

    public Lokasi searchLokasi(String nama) {
        for (Lokasi l1 : daftarLokasi) {
            if (l1.getNamaPerusahaan().equals(nama)) {
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

    public void menuDaftarGeladi() {
        int pilLokasi;
        System.out.println("Lokasi \n");
        viewAllLokasi();
        System.out.print("Pilihan Lokasi : ");
        int inputLokasi = inputInteger.nextInt();

    }

    public void menuDataPembimbing() {
//        menu menampilkan semua data pembimbing dan Tambah pembimbing
        System.out.println("Menu Data Pembimbing");
        System.out.println("1. Data Pembimbing");
        System.out.println("2. Tambah Pembimbing");
        System.out.print("Pilihan anda : ");
        int inputMenu = inputInteger.nextInt();
        if (inputMenu == 1) {
            viewAllPembimbing();
        } else if (inputMenu == 2) {
            System.out.println("Masukkan nama \t :");
            String inputNama = inputString.nextLine();
            System.out.println("Masukkan nip \t :");
            long inputNip = inputLong.nextLong();

            addPembimbing(inputNama, inputNip);
        } else {
            System.out.println("Salah memasukkan menu");
        }
    }

    public void viewAllPembimbing() {
//        mengoutputkan semua data Pembimbing
        for (Lokasi l : daftarLokasi) {
            System.out.println(l.getPembimbing());
        }
    }

    public void menuLokasi() {
        System.out.println("Menu Lokasi");
        System.out.println("1. List Lokasi");
        System.out.println("2. Daftar Kelompok per Lokasi");
        System.out.println("3. Set Pembimbing");
        System.out.print("Pilihan Anda : ");
        int inputMenu = inputInteger.nextInt();
        if (inputMenu == 1) {
            viewAllLokasi();
        } else if (inputMenu == 2) {
            viewAllLokasi();
            System.out.println("Pilih Lokasi : ");
            String inputLokasi = inputString.nextLine();
            Lokasi tampung = searchLokasi(inputLokasi);
            //VIew all Mahasiswa dari satu lokasi yang ditunjuk

        } else if (inputMenu == 3) {
            //Mengset Pembimbing
        }
    }

    public void menuKelompok() {
        System.out.println("Menu Kelompok");
        System.out.println("1. List Kelompok");
        System.out.println("2. Tambah Kelompok");
        System.out.println("3. Hapus Kelompok");
        System.out.print("Pilihan Anda : ");
        int inputMenu = inputInteger.nextInt();
        if (inputMenu == 1) {
            viewAllKelompok();
        } else if (inputMenu == 2) {
//            daftarLokasi.

            viewAllLokasi();
//            Menambah Kelompok
            System.out.println("Pilih Nama Lokasi : ");
            String inputLokasi = inputString.nextLine();
            Lokasi tampung = searchLokasi(inputLokasi);

        } else if (inputMenu == 3) {
            viewAllKelompok();
            System.out.println("Masukkan id kelompok yang akan di hapus");
            int inputIdx = inputInteger.nextInt();
//            Lokasi tampung = search
        }
    }

    public void viewAllKelompok() {
//        Menampilkan kelompok berdasarkan lokasi

    }

    public void menuSatu() {
        System.out.println("\t \t \t Menu Admin");
        System.out.println("Pilihan Menu");
        System.out.println("1. Menu Data Pembimbing");
        System.out.println("2. Menu Lokasi");
        System.out.println("3. Menu Kelompok");
        System.out.println("0. Back");
        System.out.print("Pilihan Anda : ");
        int pilihanMenu = inputInteger.nextInt();
        switch (pilihanMenu) {
            case 1:
                menuDataPembimbing();
                break;
            case 2:
                menuLokasi();
                break;
            case 3:
                menuKelompok();
                break;
            case 0:
                mainMenu();
                break;
            default:
                System.out.println("Menu yang anda masukkan salah");
                break;
        }
    }

    public void menuDua() {
        System.out.println("\t \t \t Menu Mahasiswa");
        System.out.println("1. Daftar Geladi");
        System.out.println("2. Menu Lokasi");
        System.out.println("0. Back");
        System.out.print("Pilihan Anda : ");
        int pilihanMenu = inputInteger.nextInt();
        switch (pilihanMenu) {
            case 1:
                menuDaftarGeladi();
                break;
            case 2:
                menuLokasi();
                break;
            case 0:
                mainMenu();
                break;
            default:
                System.out.println("Menu yang anda masukkan salah");
                break;
        }

    }

    public void mainMenu() {
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
                        String inputUser = inputString.nextLine();
                        System.out.print("Masukkan Password : ");
                        String inputPass = inputString.nextLine();
                        try {
                            FileInputStream fInput = new FileInputStream("admin.txt");
                            ObjectInputStream output = new ObjectInputStream(fInput);
                            Admin adm = (Admin)output.readObject();
                        } catch (Exception e) {
                            e.getMessage();
                        }
                        menuSatu();
                        break;
                    case 2:
                        System.out.print("Masukkan Username : ");
                        String inputUserMhs = inputString.nextLine();
                        System.out.print("Masukkan Password : ");
                        String inputPassMhs = inputString.nextLine();
                        menuDua();
                        break;
                    case 0:
                        pilihanMenu = 0;
                        break;
                    default:
                        System.out.println("Menu yang anda masukkan salah");
                }
            } catch (InputMismatchException e) {
                System.out.println("Anda memasukkan pilihan menu yang salah");
            }
        }
    }
}
