/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Admin;
import Model.Aplikasi;
import Model.Lokasi;
import Model.Pembimbing;
import Model.Kelompok;
import Model.Mahasiswa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import View.*;
import ViewAdmin.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Erwin
 */
public class Controller extends MouseAdapter implements ActionListener {

    private Aplikasi model;
    private View view;

    public Controller(Aplikasi model) {
        this.model = model;
        MainMenu m = new MainMenu();
        m.setVisible(true);
        m.addListener(this);
        view = m;
        try {
            model.load();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (view instanceof MainMenu) {
            MainMenu m = (MainMenu) view;
            if (source.equals(m.getBtnMain1())) {
                LoginAdmin la = new LoginAdmin();
                la.setVisible(true);
                la.addListener(this);
                m.dispose();
                view = la;
            } else if (source.equals(m.getBtnMain2())) {
                LoginMhs lm = new LoginMhs();
                lm.setVisible(true);
                lm.addListener(this);
                m.dispose();
                view = lm;
            }
        } else if (view instanceof LoginAdmin) {
            LoginAdmin la = (LoginAdmin) view;
            if (source.equals(la.getBtnLogIn())) {
                String inputUser = la.getTfUsername();
                String inputPass = la.getTfPass();
                try {
                    Admin adm = (Admin) model.getObject("admin.txt");
                    if (source.equals(la.getBtnLogIn()) && inputUser.equals(adm.getUsername()) && inputPass.equals(adm.getPassword())) {
                        MainMenuAdmin ma = new MainMenuAdmin();
                        la.dispose();
                        ma.setVisible(true);
                        ma.addListener(this);
                        view = ma;
                    } else {
                        JOptionPane.showMessageDialog(la, "Username / Password Salah");
                    }
                } catch (IOException ex) {
                    System.out.println("error " + ex.getMessage());
                } catch (ClassNotFoundException ex) {
                    System.out.println("error " + ex.getMessage());
                }
            } else if (source.equals(la.getBtnBack())) {
                MainMenu mm = new MainMenu();
                mm.setVisible(true);
                mm.addListener(this);
                la.dispose();
                view = mm;
            }
        } else if (view instanceof MainMenuAdmin) {//Main Menu Admin Sampe ada **
            MainMenuAdmin ma = (MainMenuAdmin) view;
            if (source.equals(ma.getBtnAdmin1())) {
                MenuDataPembimbing md = new MenuDataPembimbing();
                md.setVisible(true);
                md.addListener(this);
                ma.dispose();
                view = md;
            } else if (source.equals(ma.getBtnAdmin2())) {
                MenuLokasi ml = new MenuLokasi();
                ml.setVisible(true);
                ml.addListener(this);
                ma.dispose();
                view = ml;
            } else if (source.equals(ma.getBtnAdmin3())) {
                MenuKelompok mk = new MenuKelompok();
                mk.setVisible(true);
                mk.addListener(this);
                ma.dispose();
                view = mk;
            } else if (source.equals(ma.getBtnAdmin4())) {
                MainMenu m = new MainMenu();
                m.setVisible(true);
                m.addListener(this);
                ma.dispose();
                view = m;
            }
        } else if (view instanceof MenuDataPembimbing) {//MEnu data pembimbing dulu ini sampai rambu berikut
            MenuDataPembimbing md = (MenuDataPembimbing) view;
            if (source.equals(md.getBtnPembimbing1())) {
                ListPembimbing lp = new ListPembimbing();
                lp.setVisible(true);
                lp.addListener(this);
                md.dispose();
                view = lp;
            } else if (source.equals(md.getBtnPembimbing2())) {
                TambahPembimbing tp = new TambahPembimbing();
                tp.setVisible(true);
                tp.addListener(this);
                md.dispose();
                view = tp;
            } else if (source.equals(md.getBtnBack())) {
                MainMenuAdmin ma = new MainMenuAdmin();
                ma.setVisible(true);
                ma.addListener(this);
                md.dispose();
                view = ma;
            }
        } else if (view instanceof ListPembimbing) {
            ListPembimbing lp = (ListPembimbing) view;
            if (source.equals(lp.getBtnBack())) {
                MenuDataPembimbing md = new MenuDataPembimbing();
                md.setVisible(true);
                md.addListener(this);
                lp.dispose();
                view = md;
            } else if (source.equals(lp.getBtnRefresh())) {// MAsih belom bisa nge get list Pembimbing
                try {
                    ArrayList<Pembimbing> listP = model.getDaftarPembimbing();
                    for (int i = 0; i < listP.size(); i++) {
                        lp.getTblPembimbing().setValueAt(listP.get(i).getNama(), i, 0);
                        lp.getTblPembimbing().setValueAt(listP.get(i).getNip(), i, 1);
                    }
                } catch (Exception e) {
                    lp.viewErrorMsg(e.getMessage());
                }
            }
        } else if (view instanceof TambahPembimbing) {
            TambahPembimbing tp = (TambahPembimbing) view;
            if (source.equals(tp.getBtnDaftar())) {
                String nama = tp.getTextFieldNama();
                long nip = tp.getTextFieldNip();
                model.addPembimbing(nama, nip);
                try {
                    model.saveObject(model.getDaftarPembimbing(), "pembimbing.txt");
                } catch (IOException ex) {
                    tp.viewErrorMsg(ex.getMessage());
                }
                tp.reset();
            } else if (source.equals(tp.getBtnBack())) {
                MainMenuAdmin ma = new MainMenuAdmin();
                ma.setVisible(true);
                ma.addListener(this);
                tp.dispose();
                view = ma;
            }
        } else if (view instanceof MenuLokasi) {
            MenuLokasi ml = (MenuLokasi) view;
            if (source.equals(ml.getBtnList())) {
                ListLokasi ll = new ListLokasi();
                ll.setVisible(true);
                ll.addListener(this);
                ml.dispose();
                view = ll;
            } else if (source.equals(ml.getBtnDaftarKelompok())) {
                DaftarKelompok dk = new DaftarKelompok();
                dk.setVisible(true);
                dk.addListener(this);
                ml.dispose();
                view = dk;
            } else if (source.equals(ml.getBtnSetPembimbing())) {
                SetPembimbing sp = new SetPembimbing();
                sp.setVisible(true);
                sp.addListener(this);
                ml.dispose();
                view = sp;
            } else if (source.equals(ml.getBtnBack())) {
                MainMenuAdmin ma = new MainMenuAdmin();
                ma.setVisible(true);
                ma.addListener(this);
                ml.dispose();
                view = ma;
            }
        } else if (view instanceof ListLokasi) {//Tinggal List Lokasi
            ListLokasi ll = (ListLokasi) view;

            if (source.equals(ll.getBtnBack())) {
                MenuLokasi mk = new MenuLokasi();
                mk.setVisible(true);
                mk.addListener(this);
                ll.dispose();
                view = mk;
            } else if (source.equals(ll.getBtnRefresh())) {
                ArrayList<Lokasi> listLokasi = model.getDaftarLokasi();
                String[] list = new String[listLokasi.size()];
                for (int i = 0; i < listLokasi.size(); i++) {
                    list[i] = listLokasi.get(i).getNamaPerusahaan();
                }
                ll.setListLokasi(list);
            }
        } else if (view instanceof SetPembimbing) {// Done dude
            SetPembimbing sp = (SetPembimbing) view;
            if (source.equals(sp.getBtnSet())) {
                String pembimbing = sp.getPembimbing();
                int lokasi = sp.getLokasi();
                model.setPembimbingOfLokasi(pembimbing, lokasi);
                JOptionPane.showMessageDialog(sp, pembimbing);
            } else if (source.equals(sp.getBtnBack())) {
                MenuLokasi ml = new MenuLokasi();
                ml.setVisible(true);
                ml.addListener(this);
                sp.dispose();
                view = ml;
            }
        } else if (view instanceof MenuKelompok) {
            MenuKelompok mk = (MenuKelompok) view;
            if (source.equals(mk.getBtnList())) {
                DaftarKelompok lk = new DaftarKelompok();
                lk.setVisible(true);
                lk.addListener(this);
                mk.dispose();
                view = lk;
            } else if (source.equals(mk.getBtnTambah())) {
                TambahKelompok tk = new TambahKelompok();
                tk.setVisible(true);
                tk.addListener(this);
                mk.dispose();
                view = tk;
            } else if (source.equals(mk.getBtnHapus())) {
                HapusKelompok hk = new HapusKelompok();
                hk.setVisible(true);
                hk.addListener(this);
                mk.dispose();
                view = hk;
            } else if (source.equals(mk.getBtnBack())) {
                MainMenuAdmin ma = new MainMenuAdmin();
                ma.setVisible(true);
                ma.addListener(this);
                mk.dispose();
                view = ma;
            }
        } else if (view instanceof DaftarKelompok) {
            DaftarKelompok dk = (DaftarKelompok) view;

            if (source.equals(dk.getBtnBack())) {
                MenuLokasi ml = new MenuLokasi();
                ml.setVisible(true);
                ml.addListener(this);
                dk.dispose();
                view = ml;
            } else if (source.equals(dk.getBtnRefresh())) {// Belom selesai
                try {
                    model.load();
                    ArrayList<Lokasi> listLokasi = model.getDaftarLokasi();
                    String[] list = new String[listLokasi.size()];
                    for (int i = 0; i < listLokasi.size(); i++) {
                        list[i] = listLokasi.get(i).getNamaPerusahaan();
                    }
                    dk.setListLokasi(list);
                } catch (Exception e) {
                    e.getMessage();
                }
                dk.setKelompok("");
            }
        } else if (view instanceof TambahKelompok) {//DOne
            TambahKelompok tk = (TambahKelompok) view;

            if (source.equals(tk.getBtnTambah())) {
                String lokasi = tk.getComboPilih();
                ArrayList<Lokasi> listLok = model.getDaftarLokasi();
                for (Lokasi ll : listLok) {
                    if (ll.getNamaPerusahaan().equals(lokasi)) {
                        ll.createKelompok(new Kelompok());
                    }
                }
                JOptionPane.showMessageDialog(tk, "Berhasil Menambah Kelompok");
            } else if (source.equals(tk.getBtnBack())) {
                MenuKelompok mk = new MenuKelompok();
                mk.setVisible(true);
                mk.addListener(this);
                tk.dispose();
                view = mk;
            }
        } else if (view instanceof HapusKelompok) {
            HapusKelompok hk = (HapusKelompok) view;
            if (source.equals(hk.getBtnRefresh())) {
                try {
                    model.load();
                    ArrayList<Lokasi> listLokasi = model.getDaftarLokasi();
                    String[] list = new String[listLokasi.size()];
                    for (int i = 0; i < listLokasi.size(); i++) {
                        list[i] = listLokasi.get(i).getNamaPerusahaan();
                    }
                    hk.setListLokasi(list);
                } catch (Exception e) {
                    e.getMessage();
                }
            } else if (source.equals(hk.getBtnBack())) {
                MenuKelompok mk = new MenuKelompok();
                mk.setVisible(true);
                mk.addListener(this);
                hk.dispose();
                view = mk;
            } else if (source.equals(hk.getBtnHapus())) {
                
            }
        } /*BATAS VIEW ADMIN & VIEW MAHASISWA*/ else if (view instanceof LoginMhs) {
            LoginMhs lm = (LoginMhs) view;
            if (source.equals(lm.getBtnLogIn())) {
                String inputNim = lm.getTfNim();
                String inputPass = lm.getTfPass();
                try {
                    ArrayList<Mahasiswa> mhs = (ArrayList<Mahasiswa>) model.getObject("mahasiswa.txt");
                    for (Mahasiswa m : mhs) {
                        if (m.getUsername().equals(lm.getTfNim()) && m.getPassword().equals(lm.getTfPass())) {
                            MenuPendaftaranGeladi mg = new MenuPendaftaranGeladi();
                            mg.setVisible(true);
                            mg.addListener(this);
                            lm.dispose();
                            view = mg;
                            break;
                        }
                    }
                } catch (IOException ex) {
                    System.out.println("error " + ex.getMessage());
                } catch (ClassNotFoundException ex) {
                    System.out.println("error " + ex.getMessage());
                }
            } else if (source.equals(lm.getBtnBack())) {
                MainMenu mm = new MainMenu();
                mm.setVisible(true);
                mm.addListener(this);
                lm.dispose();
                view = mm;
            }
        } else if (view instanceof MenuPendaftaranGeladi) {
            MenuPendaftaranGeladi mg = (MenuPendaftaranGeladi) view;
            if (source.equals(mg.getBtnLok())) {
                MenuLokasiMhs mlm = new MenuLokasiMhs();
                mlm.setVisible(true);
                mlm.addListener(this);
                mg.dispose();
                view = mlm;
            } else if (source.equals(mg.getBtnDaftar())) {
                FormPendaftaran fp = new FormPendaftaran();
                fp.setVisible(true);
                fp.addListener(this);
                mg.dispose();
                view = fp;
            }
        } else if (view instanceof MenuLokasiMhs) {
            MenuLokasiMhs mlm = (MenuLokasiMhs) view;
            
            if (source.equals(mlm.getBtnBack1())){
                MenuPendaftaranGeladi mg = new MenuPendaftaranGeladi();
                mg.setVisible(true);
                mg.addListener(this);
                mlm.dispose();
                view = mg;
            }
        } else if (view instanceof FormPendaftaran) {
            FormPendaftaran fp = (FormPendaftaran) view;
            if (source.equals(fp.getBtnDaftar())) {
                String inputNama = fp.getjNama();
                String inputNim = fp.getjNim();
                int lokasi = fp.getComboLokasi();
                int klp = fp.getComboKelompok();
                Date d = fp.getTanggalLahir();
                String jurusan = fp.getJurusan();
                model.getDaftarLokasi().get(lokasi).getKelompokById(klp).addAnggota(new Mahasiswa(inputNama, d, inputNim, jurusan));
                // ngisi segala macam, ntar di save ke file lokasi geladi
                JOptionPane.showMessageDialog(fp, "Pendaftaran Berhasil");
            } else if (source.equals(fp.getBtnBack())) {
                MenuPendaftaranGeladi ms = new MenuPendaftaranGeladi();
                ms.setVisible(true);
                ms.addListener(this);
                fp.dispose();
                view = ms;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (view instanceof DaftarKelompok) {
            DaftarKelompok dk = (DaftarKelompok) view;
            int lokasi = dk.getSelectedLokasi();
            JOptionPane.showMessageDialog(dk, lokasi);
            if (lokasi != 0) {
                dk.setKelompok(model.getDaftarLokasi().get(lokasi).getKelompok().toString());
            }
        } else if (view instanceof MenuLokasiMhs){
            MenuLokasiMhs ms = (MenuLokasiMhs) view;
            int lokasi = ms.getLokasi();
            if (lokasi != 0){
                ms.setListKelompok((String[]) model.getDaftarLokasi().get(lokasi).getKelompok().toArray());
            }
        }
    }
}
