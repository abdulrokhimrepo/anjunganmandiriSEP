/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * DlgAdmin.java
 *
 * Created on 04 Des 13, 12:59:34
 */
package khanzahmsanjungan;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.akses;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import org.bouncycastle.crypto.engines.TnepresEngine;

/**
 *
 * @author Kode
 */
public class DlgRegistrasiWalkIn extends javax.swing.JDialog {

    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps3;
    private ResultSet rs, rs3;
    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
    private String umur = "0", sttsumur = "Th", hari = "", kode_dokter = "", kode_poli = "", nama_instansi, alamat_instansi, kabupaten, propinsi, kontak, email;
    private String status = "Baru", BASENOREG = "", URUTNOREG = "", aktifjadwal = "";
    private Properties prop = new Properties();
    private File file;
    private DlgCariPoli poli = new DlgCariPoli(null, true);
    private DlgCariDokter2 dokter = new DlgCariDokter2(null, true);
    private FileWriter fileWriter;
    private String iyem;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode response;
    private FileReader myObj;
    private Calendar cal = Calendar.getInstance();
    private int day = cal.get(Calendar.DAY_OF_WEEK);

    /**
     * Creates new form DlgAdmin
     *
     * @param parent
     * @param id
     */
    public DlgRegistrasiWalkIn(java.awt.Frame parent, boolean id) {
        super(parent, id);
        initComponents();

        try {
            ps = koneksi.prepareStatement(
                    "select nm_pasien,concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) asal,"
                    + "namakeluarga,keluarga,pasien.kd_pj,penjab.png_jawab,if(tgl_daftar=?,'Baru','Lama') as daftar, "
                    + "TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) as tahun, "
                    + "(TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12)) as bulan, "
                    + "TIMESTAMPDIFF(DAY, DATE_ADD(DATE_ADD(tgl_lahir,INTERVAL TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) YEAR), INTERVAL TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12) MONTH), CURDATE()) as hari from pasien "
                    + "inner join kelurahan inner join kecamatan inner join kabupaten inner join penjab "
                    + "on pasien.kd_kel=kelurahan.kd_kel and pasien.kd_pj=penjab.kd_pj "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab "
                    + "where pasien.no_rkm_medis=?");
        } catch (Exception ex) {
            System.out.println(ex);
        }

        try {
            ps = koneksi.prepareStatement("select nama_instansi, alamat_instansi, kabupaten, propinsi, aktifkan, wallpaper,kontak,email,logo from setting");
            rs = ps.executeQuery();
            while (rs.next()) {
                nama_instansi = rs.getString("nama_instansi");
                alamat_instansi = rs.getString("alamat_instansi");
                kabupaten = rs.getString("kabupaten");
                propinsi = rs.getString("propinsi");
                kontak = rs.getString("kontak");
                email = rs.getString("email");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                kode_poli = "";
                NamaPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
                kode_poli = poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString();

            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                kode_dokter = "";
                NamaDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                kode_dokter = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                tampilPenjab();
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            aktifjadwal = prop.getProperty("JADWALDOKTERDIREGISTRASI");
            URUTNOREG = prop.getProperty("URUTNOREG");
            BASENOREG = prop.getProperty("BASENOREG");
        } catch (Exception ex) {
            aktifjadwal = "";
            URUTNOREG = "";
            BASENOREG = "";
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LblKdPoli = new component.Label();
        LblKdDokter = new component.Label();
        NoReg = new component.TextBox();
        NoRawat = new component.TextBox();
        Biaya = new component.TextBox();
        TAlmt = new component.Label();
        TPngJwb = new component.Label();
        THbngn = new component.Label();
        NoTelpPasien = new component.Label();
        jPanel1 = new component.Panel();
        jPanel2 = new component.Panel();
        jLabel10 = new component.Label();
        jLabel11 = new component.Label();
        lblNamaPasien = new component.Label();
        jLabel28 = new component.Label();
        jLabel29 = new component.Label();
        jLabel30 = new component.Label();
        jLabel31 = new component.Label();
        TanggalPeriksa = new widget.Tanggal();
        lblNoRM = new component.Label();
        jLabel32 = new component.Label();
        jLabel33 = new component.Label();
        jLabel35 = new component.Label();
        cmbCaraBayar = new widget.ComboBox();
        btnSimpan1 = new component.Button();
        NamaPoli = new widget.TextBox();
        jLabel36 = new component.Label();
        TNoRw = new widget.TextBox();
        btnSimpan2 = new component.Button();
        NamaDokter = new widget.TextBox();
        jPanel3 = new javax.swing.JPanel();
        btnSimpan = new component.Button();
        btnKeluar = new component.Button();

        LblKdPoli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LblKdPoli.setText("Norm");
        LblKdPoli.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        LblKdPoli.setPreferredSize(new java.awt.Dimension(20, 14));

        LblKdDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LblKdDokter.setText("Norm");
        LblKdDokter.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        LblKdDokter.setPreferredSize(new java.awt.Dimension(20, 14));

        NoReg.setPreferredSize(new java.awt.Dimension(320, 30));
        NoReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoRegActionPerformed(evt);
            }
        });
        NoReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRegKeyPressed(evt);
            }
        });

        NoRawat.setPreferredSize(new java.awt.Dimension(320, 30));
        NoRawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoRawatActionPerformed(evt);
            }
        });
        NoRawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRawatKeyPressed(evt);
            }
        });

        Biaya.setPreferredSize(new java.awt.Dimension(320, 30));
        Biaya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BiayaActionPerformed(evt);
            }
        });
        Biaya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BiayaKeyPressed(evt);
            }
        });

        TAlmt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TAlmt.setText("Norm");
        TAlmt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TAlmt.setPreferredSize(new java.awt.Dimension(20, 14));

        TPngJwb.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TPngJwb.setText("Norm");
        TPngJwb.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TPngJwb.setPreferredSize(new java.awt.Dimension(20, 14));

        THbngn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        THbngn.setText("Norm");
        THbngn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        THbngn.setPreferredSize(new java.awt.Dimension(20, 14));

        NoTelpPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        NoTelpPasien.setText("Norm");
        NoTelpPasien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        NoTelpPasien.setPreferredSize(new java.awt.Dimension(20, 14));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new java.awt.BorderLayout(1, 1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 215, 255)), "PENDAFTARAN POLIKLINIK ONSITE", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Poppins", 0, 24), new java.awt.Color(0, 131, 62))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 70));
        jPanel1.setLayout(new java.awt.BorderLayout(0, 1));

        jPanel2.setBackground(new java.awt.Color(238, 238, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(390, 120));
        jPanel2.setLayout(null);

        jLabel10.setForeground(new java.awt.Color(0, 131, 62));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("No Rekam Medis / Nama");
        jLabel10.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(20, 14));
        jPanel2.add(jLabel10);
        jLabel10.setBounds(20, 50, 250, 40);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText(":");
        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(20, 14));
        jPanel2.add(jLabel11);
        jLabel11.setBounds(270, 60, 20, 23);

        lblNamaPasien.setForeground(new java.awt.Color(0, 131, 62));
        lblNamaPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNamaPasien.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        lblNamaPasien.setPreferredSize(new java.awt.Dimension(20, 14));
        jPanel2.add(lblNamaPasien);
        lblNamaPasien.setBounds(470, 50, 410, 40);

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText(":");
        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel28.setPreferredSize(new java.awt.Dimension(20, 14));
        jPanel2.add(jLabel28);
        jLabel28.setBounds(270, 100, 20, 23);

        jLabel29.setForeground(new java.awt.Color(0, 131, 62));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("Tanggal Periksa");
        jLabel29.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel29.setPreferredSize(new java.awt.Dimension(20, 14));
        jPanel2.add(jLabel29);
        jLabel29.setBounds(20, 90, 250, 40);

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText(":");
        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel30.setPreferredSize(new java.awt.Dimension(20, 14));
        jPanel2.add(jLabel30);
        jLabel30.setBounds(270, 150, 20, 23);

        jLabel31.setForeground(new java.awt.Color(0, 131, 62));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Poli Tujuan");
        jLabel31.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel31.setPreferredSize(new java.awt.Dimension(20, 14));
        jPanel2.add(jLabel31);
        jLabel31.setBounds(20, 140, 250, 40);

        TanggalPeriksa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "13-06-2023" }));
        TanggalPeriksa.setDisplayFormat("dd-MM-yyyy");
        TanggalPeriksa.setOpaque(false);
        TanggalPeriksa.setPreferredSize(new java.awt.Dimension(95, 23));
        TanggalPeriksa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TanggalPeriksaItemStateChanged(evt);
            }
        });
        TanggalPeriksa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TanggalPeriksaActionPerformed(evt);
            }
        });
        TanggalPeriksa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalPeriksaKeyPressed(evt);
            }
        });
        jPanel2.add(TanggalPeriksa);
        TanggalPeriksa.setBounds(290, 90, 190, 40);

        lblNoRM.setForeground(new java.awt.Color(0, 131, 62));
        lblNoRM.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNoRM.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        lblNoRM.setPreferredSize(new java.awt.Dimension(20, 14));
        jPanel2.add(lblNoRM);
        lblNoRM.setBounds(290, 50, 170, 40);

        jLabel32.setForeground(new java.awt.Color(0, 131, 62));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("Dokter Tujuan");
        jLabel32.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel32.setPreferredSize(new java.awt.Dimension(20, 14));
        jPanel2.add(jLabel32);
        jLabel32.setBounds(20, 180, 250, 40);

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText(":");
        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel33.setPreferredSize(new java.awt.Dimension(20, 14));
        jPanel2.add(jLabel33);
        jLabel33.setBounds(270, 190, 20, 23);

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText(":");
        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel35.setPreferredSize(new java.awt.Dimension(20, 14));
        jPanel2.add(jLabel35);
        jLabel35.setBounds(270, 230, 20, 23);

        cmbCaraBayar.setForeground(new java.awt.Color(0, 131, 62));
        cmbCaraBayar.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        cmbCaraBayar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCaraBayarItemStateChanged(evt);
            }
        });
        cmbCaraBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbCaraBayarKeyPressed(evt);
            }
        });
        jPanel2.add(cmbCaraBayar);
        cmbCaraBayar.setBounds(290, 220, 520, 40);

        btnSimpan1.setForeground(new java.awt.Color(0, 131, 62));
        btnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/pilih.png"))); // NOI18N
        btnSimpan1.setMnemonic('S');
        btnSimpan1.setToolTipText("Alt+S");
        btnSimpan1.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        btnSimpan1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSimpan1.setPreferredSize(new java.awt.Dimension(300, 45));
        btnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpan1ActionPerformed(evt);
            }
        });
        btnSimpan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSimpan1KeyPressed(evt);
            }
        });
        jPanel2.add(btnSimpan1);
        btnSimpan1.setBounds(810, 140, 70, 40);

        NamaPoli.setEditable(false);
        NamaPoli.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        NamaPoli.setPreferredSize(new java.awt.Dimension(72, 28));
        NamaPoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NamaPoliKeyPressed(evt);
            }
        });
        jPanel2.add(NamaPoli);
        NamaPoli.setBounds(290, 140, 520, 40);

        jLabel36.setForeground(new java.awt.Color(0, 131, 62));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("Cara Bayar");
        jLabel36.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel36.setPreferredSize(new java.awt.Dimension(20, 14));
        jPanel2.add(jLabel36);
        jLabel36.setBounds(20, 220, 250, 40);

        TNoRw.setEditable(false);
        TNoRw.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        TNoRw.setPreferredSize(new java.awt.Dimension(72, 28));
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        jPanel2.add(TNoRw);
        TNoRw.setBounds(290, 260, 520, 40);

        btnSimpan2.setForeground(new java.awt.Color(0, 131, 62));
        btnSimpan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/pilih.png"))); // NOI18N
        btnSimpan2.setMnemonic('S');
        btnSimpan2.setToolTipText("Alt+S");
        btnSimpan2.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        btnSimpan2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSimpan2.setPreferredSize(new java.awt.Dimension(300, 45));
        btnSimpan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpan2ActionPerformed(evt);
            }
        });
        btnSimpan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSimpan2KeyPressed(evt);
            }
        });
        jPanel2.add(btnSimpan2);
        btnSimpan2.setBounds(810, 180, 70, 40);

        NamaDokter.setEditable(false);
        NamaDokter.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        NamaDokter.setPreferredSize(new java.awt.Dimension(72, 28));
        NamaDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NamaDokterKeyPressed(evt);
            }
        });
        jPanel2.add(NamaDokter);
        NamaDokter.setBounds(290, 180, 520, 40);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(238, 238, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(615, 200));

        btnSimpan.setForeground(new java.awt.Color(0, 131, 62));
        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/konfirmasi.png"))); // NOI18N
        btnSimpan.setMnemonic('S');
        btnSimpan.setText("Konfirmasi");
        btnSimpan.setToolTipText("Alt+S");
        btnSimpan.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        btnSimpan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSimpan.setPreferredSize(new java.awt.Dimension(300, 45));
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        btnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSimpanKeyPressed(evt);
            }
        });
        jPanel3.add(btnSimpan);

        btnKeluar.setForeground(new java.awt.Color(0, 131, 62));
        btnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/reset.png"))); // NOI18N
        btnKeluar.setMnemonic('K');
        btnKeluar.setText("Batal");
        btnKeluar.setToolTipText("Alt+K");
        btnKeluar.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        btnKeluar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnKeluar.setPreferredSize(new java.awt.Dimension(300, 45));
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });
        btnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnKeluarKeyPressed(evt);
            }
        });
        jPanel3.add(btnKeluar);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void NoRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoRegActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoRegActionPerformed

    private void NoRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRegKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoRegKeyPressed

    private void NoRawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoRawatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoRawatActionPerformed

    private void NoRawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRawatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoRawatKeyPressed

    private void BiayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BiayaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BiayaActionPerformed

    private void BiayaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BiayaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BiayaKeyPressed

    private void TanggalPeriksaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalPeriksaKeyPressed

    }//GEN-LAST:event_TanggalPeriksaKeyPressed

    private void cmbCaraBayarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCaraBayarItemStateChanged
//        tentukanPilihan();
    }//GEN-LAST:event_cmbCaraBayarItemStateChanged

    private void cmbCaraBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbCaraBayarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCaraBayarKeyPressed

    private void btnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnKeluarActionPerformed(null);
        }
    }//GEN-LAST:event_btnKeluarKeyPressed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void btnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnSimpanActionPerformed(null);
        }
    }//GEN-LAST:event_btnSimpanKeyPressed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed

        if (lblNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "No RM Kosong");
        } else if (kode_poli == "") {
            JOptionPane.showMessageDialog(rootPane, "Pilih poli terlebih dahulu");
        } else if (kode_dokter == "") {
            JOptionPane.showMessageDialog(rootPane, "Pilih Dokter terlebih dahulu");
        } else if (Sequel.cariInteger("select count(jadwal_cuti_libur.kd_dokter) from jadwal_cuti_libur where jadwal_cuti_libur.tanggallibur='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem().toString() + "") + "' and jadwal_cuti_libur.kd_dokter='" + kode_dokter + "' and jadwal_cuti_libur.kd_poli='" + kode_poli + "' ") > 0) {
            JOptionPane.showMessageDialog(rootPane, "Maaf, dokter  tidak berpraktek pada tanggal yang anda pilih ");
        } else if (Sequel.cariInteger("select count(no_rkm_medis) from reg_periksa where kd_pj='A09' and no_rkm_medis='" + lblNoRM.getText() + "' and kd_poli='" + kode_poli + "' and kd_dokter='" + kode_dokter + "' and tgl_registrasi='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem() + "") + "' ") > 0) {
            JOptionPane.showMessageDialog(rootPane, "Maaf, anda sudah terdaftar pada hari ini dengan dokter yang sama ");
        } else {
            isNumber();
            String biayareg = Sequel.cariIsi("SELECT registrasilama FROM poliklinik WHERE kd_poli='" + kode_poli + "'");
            UpdateUmur();
            isCekPasien();
            if (Sequel.menyimpantf2("reg_periksa", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 19,
                    new String[]{NoReg.getText(), NoRawat.getText(), Valid.SetTgl(TanggalPeriksa.getSelectedItem() + ""), Sequel.cariIsi("select current_time()"),
                        kode_dokter, lblNoRM.getText(), kode_poli, TPngJwb.getText(), TAlmt.getText(), THbngn.getText(), biayareg, "Belum",
                        "Lama", "Ralan", "A09", umur, sttsumur, "Belum Bayar", status}) == true) {
                MnCetakRegisterActionPerformed(NoRawat.getText());
                NoReg.setText("");
                TNoRw.setText("");
                NoRawat.setText("");
                lblNoRM.setText("");
                TPngJwb.setText("");
                TAlmt.setText("");
                THbngn.setText("");
                umur = "";
                sttsumur = "";

                kode_poli = "";
                NamaPoli.setText("");
                NamaDokter.setText("");
                kode_dokter = "";
                JOptionPane.showMessageDialog(rootPane, "Berhasil");
                this.dispose();

            }

        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void TanggalPeriksaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TanggalPeriksaItemStateChanged
        tentukanHari();
        kode_poli = "";
        NamaPoli.setText("");
        NamaDokter.setText("");
        kode_dokter = "";
//        poli.tampil(hari);
//        poli.setSize(jPanel2.getWidth() - 50, jPanel2.getHeight() - 50);
//        poli.setLocationRelativeTo(jPanel2);
//        poli.setVisible(true);
    }//GEN-LAST:event_TanggalPeriksaItemStateChanged

    private void btnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpan1ActionPerformed
        poli.tampil(hari);
        poli.setSize(jPanel1.getWidth() - 50, jPanel1.getHeight() - 50);
        poli.setLocationRelativeTo(jPanel2);
        poli.setVisible(true);
    }//GEN-LAST:event_btnSimpan1ActionPerformed

    private void btnSimpan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSimpan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSimpan1KeyPressed

    private void NamaPoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NamaPoliKeyPressed

    }//GEN-LAST:event_NamaPoliKeyPressed

    private void TanggalPeriksaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TanggalPeriksaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TanggalPeriksaActionPerformed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNoRwKeyPressed

    private void btnSimpan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpan2ActionPerformed
        dokter.tampil(hari, kode_poli);
        dokter.setSize(jPanel1.getWidth() - 50, jPanel1.getHeight() - 50);
        dokter.setLocationRelativeTo(jPanel2);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnSimpan2ActionPerformed

    private void btnSimpan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSimpan2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSimpan2KeyPressed

    private void NamaDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NamaDokterKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaDokterKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgRegistrasiWalkIn dialog = new DlgRegistrasiWalkIn(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.TextBox Biaya;
    private component.Label LblKdDokter;
    private component.Label LblKdPoli;
    private widget.TextBox NamaDokter;
    private widget.TextBox NamaPoli;
    private component.TextBox NoRawat;
    private component.TextBox NoReg;
    private component.Label NoTelpPasien;
    private component.Label TAlmt;
    private component.Label THbngn;
    private widget.TextBox TNoRw;
    private component.Label TPngJwb;
    private widget.Tanggal TanggalPeriksa;
    private component.Button btnKeluar;
    private component.Button btnSimpan;
    private component.Button btnSimpan1;
    private component.Button btnSimpan2;
    private widget.ComboBox cmbCaraBayar;
    private component.Label jLabel10;
    private component.Label jLabel11;
    private component.Label jLabel28;
    private component.Label jLabel29;
    private component.Label jLabel30;
    private component.Label jLabel31;
    private component.Label jLabel32;
    private component.Label jLabel33;
    private component.Label jLabel35;
    private component.Label jLabel36;
    private component.Panel jPanel1;
    private component.Panel jPanel2;
    private javax.swing.JPanel jPanel3;
    private component.Label lblNamaPasien;
    private component.Label lblNoRM;
    // End of variables declaration//GEN-END:variables

    public void setPasien(String norm) {
        System.out.println(norm);
        lblNoRM.setText(norm);
        lblNamaPasien.setText(Sequel.cariIsi("select pasien.nm_pasien from pasien where pasien.no_rkm_medis='" + norm + "'"));
        if (!lblNoRM.getText().equals("") && !lblNamaPasien.getText().equals("")) {
            tentukanHari();
//            cmbDokterTujuan.setVisible(false);
//            tentukanPilihan();
//            tampilPoli();
        }

    }

    public void isCek(String norm) {

    }

    private void UpdateUmur() {
        Sequel.mengedit("pasien", "no_rkm_medis=?", "umur=CONCAT(CONCAT(CONCAT(TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()), ' Th '),CONCAT(TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12), ' Bl ')),CONCAT(TIMESTAMPDIFF(DAY, DATE_ADD(DATE_ADD(tgl_lahir,INTERVAL TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) YEAR), INTERVAL TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12) MONTH), CURDATE()), ' Hr'))", 1, new String[]{lblNoRM.getText()});
    }

    private void isNumber() {
        if (BASENOREG.equals("booking")) {
            switch (URUTNOREG) {
                case "poli":
                    if (Sequel.cariInteger("select ifnull(MAX(CONVERT(no_reg,signed)),0) from booking_registrasi where kd_poli='" + kode_poli + "' and tanggal_periksa='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem().toString()) + "'")
                            >= Sequel.cariInteger("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_poli='" + kode_poli + "' and tgl_registrasi='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem().toString()) + "'")) {
                        Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from booking_registrasi where kd_poli='" + kode_poli + "' and tanggal_periksa='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem().toString()) + "'", "", 3, NoReg);
                    } else {
                        Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_poli='" + kode_poli + "' and tgl_registrasi='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem().toString()) + "'", "", 3, NoReg);
                    }
                    break;
                case "dokter":
                    if (Sequel.cariInteger("select ifnull(MAX(CONVERT(no_reg,signed)),0) from booking_registrasi where kd_dokter='" + kode_dokter + "' and tanggal_periksa='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem().toString()) + "'")
                            >= Sequel.cariInteger("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='" + kode_dokter + "' and tgl_registrasi='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem().toString()) + "'")) {
                        Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from booking_registrasi where kd_dokter='" + kode_dokter + "' and tanggal_periksa='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem().toString()) + "'", "", 3, NoReg);
                    } else {
                        Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='" + kode_dokter + "' and tgl_registrasi='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem().toString()) + "'", "", 3, NoReg);
                    }
                    break;
                case "dokter + poli":
                    if (Sequel.cariInteger("select ifnull(MAX(CONVERT(no_reg,signed)),0) from booking_registrasi where kd_dokter='" + kode_dokter + "' and kd_poli='" + kode_poli + "' and tanggal_periksa='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem().toString()) + "'")
                            >= Sequel.cariInteger("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='" + kode_dokter + "' and kd_poli='" + kode_poli + "' and tgl_registrasi='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem().toString()) + "'")) {
                        Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from booking_registrasi where kd_dokter='" + kode_dokter + "' and kd_poli='" + kode_poli + "' and tanggal_periksa='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem().toString()) + "'", "", 3, NoReg);
                    } else {
                        Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='" + kode_dokter + "' and kd_poli='" + kode_poli + "' and tgl_registrasi='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem().toString()) + "'", "", 3, NoReg);
                    }
                    break;
                default:
                    if (Sequel.cariInteger("select ifnull(MAX(CONVERT(no_reg,signed)),0) from booking_registrasi where kd_poli='" + kode_poli + "' and tanggal_periksa='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem().toString()) + "'")
                            >= Sequel.cariInteger("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_poli='" + kode_poli + "' and tgl_registrasi='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem().toString()) + "'")) {
                        Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from booking_registrasi where kd_poli='" + kode_poli + "' and tanggal_periksa='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem().toString()) + "'", "", 3, NoReg);
                    } else {
                        Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_poli='" + kode_poli + "' and tgl_registrasi='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem().toString()) + "'", "", 3, NoReg);
                    }
                    break;
            }
        } else {
            switch (URUTNOREG) {
                case "poli":
                    Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_poli='" + kode_poli + "' and tgl_registrasi='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem().toString()) + "'", "", 3, NoReg);
                    break;
                case "dokter":
                    Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='" + kode_dokter + "' and tgl_registrasi='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem().toString()) + "'", "", 3, NoReg);
                    break;
                case "dokter + poli":
                    Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='" + kode_dokter + "' and kd_poli='" + kode_poli + "' and tgl_registrasi='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem().toString()) + "'", "", 3, NoReg);
                    break;
                default:
                    Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='" + kode_dokter + "' and tgl_registrasi='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem().toString()) + "'", "", 3, NoReg);
                    break;
            }
        }

        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_rawat,6),signed)),0) from reg_periksa where tgl_registrasi='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem().toString()) + "' ", Valid.SetTgl(TanggalPeriksa.getSelectedItem().toString()).replaceAll("-", "/") + "/", 6, NoRawat);
    }

    private void tampilPenjab() {
        try {
            file = new File("./cache/anjunganpenjamin.iyem");
            file.createNewFile();
            fileWriter = new FileWriter(file);
            iyem = "";
            ps = koneksi.prepareStatement("select * from penjab where status='1' AND kd_pj='A09' order by kd_pj");
            cmbCaraBayar.removeAllItems();
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    cmbCaraBayar.addItem(rs.getString(2).replaceAll("\"", ""));
                    iyem = iyem + "{\"NamaPenjab\":\"" + rs.getString(2).replaceAll("\"", "") + "\",\"KodePenjab\":\"" + rs.getString(1) + "\"},";
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
            fileWriter.write("{\"anjunganpenjamin\":[" + iyem.substring(0, iyem.length() - 1) + "]}");
            fileWriter.flush();
            fileWriter.close();
            iyem = null;

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

//    private void tampilDokter() {
//        if (!kode_poli.equals("")) {
//
//            try {
//                file = new File("./cache/anjungandokter.iyem");
//                file.createNewFile();
//                fileWriter = new FileWriter(file);
//                iyem = "";
//                ps = koneksi.prepareStatement("SELECT\n"
//                        + "	dokter.nm_dokter, \n"
//                        + "	jadwal.kd_dokter, jadwal.jam_mulai,jadwal.jam_selesai\n"
//                        + "FROM\n"
//                        + "	dokter\n"
//                        + "	INNER JOIN\n"
//                        + "	jadwal\n"
//                        + "	ON \n"
//                        + "		dokter.kd_dokter = jadwal.kd_dokter\n"
//                        + "		where jadwal.hari_kerja='" + hari + "' and jadwal.kd_poli='" + kode_poli + "'");
//                cmbDokterTujuan.removeAllItems();
//                try {
//                    rs = ps.executeQuery();
//                    while (rs.next()) {
//                        cmbDokterTujuan.addItem(rs.getString(1) + " " + rs.getString(3) + " s/d " + rs.getString(4));
//
//                        iyem = iyem + "{\"NamaDokter\":\"" + rs.getString(1).replaceAll("\"", "") + "\",\"KodeDokter\":\"" + rs.getString(2) + "\"},";
//                    }
//                } catch (Exception e) {
//                    System.out.println("Notifikasi resultset dokter : " + e);
//                } finally {
//                    if (rs != null) {
//                        rs.close();
//                    }
//                    if (ps != null) {
//                        ps.close();
//                    }
//                }
//                fileWriter.write("{\"anjungandokter\":[" + iyem.substring(0, iyem.length() - 1) + "]}");
//                fileWriter.flush();
//                fileWriter.close();
//                iyem = null;
//
//            } catch (Exception e) {
//                System.out.println("Notifikasi dokter : " + e);
//            }
//        } else {
//            JOptionPane.showMessageDialog(rootPane, "Pilih poli terlebih dahulu");
//        }
//
//        tampilPenjab();
//    }
//    private void tampilPoli() {
//
//        try {
//            file = new File("./cache/anjunganpoli.iyem");
//            file.createNewFile();
//            fileWriter = new FileWriter(file);
//            iyem = "";
//            ps = koneksi.prepareStatement("SELECT\n"
//                    + "	poliklinik.nm_poli, \n"
//                    + "	poliklinik.kd_poli, \n"
//                    + "	jadwal.hari_kerja\n"
//                    + "FROM\n"
//                    + "	jadwal\n"
//                    + "	INNER JOIN\n"
//                    + "	poliklinik\n"
//                    + "	ON \n"
//                    + "		jadwal.kd_poli = poliklinik.kd_poli\n"
//                    + "		where poliklinik.status='1' and jadwal.hari_kerja='" + hari + "'\n"
//                    + "		group by jadwal.kd_poli");
//            cmbPoliTujuan.removeAllItems();
//            try {
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    System.out.println(rs.getString(1));
//                    cmbPoliTujuan.addItem(rs.getString(1).replaceAll("\"", ""));
//                    iyem = iyem + "{\"NamaPoli\":\"" + rs.getString(1).replaceAll("\"", "") + "\",\"KodePoli\":\"" + rs.getString(2) + "\"},";
//                }
//            } catch (Exception e) {
//                System.out.println("Notifikasi resultset : " + e);
//            } finally {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (ps != null) {
//                    ps.close();
//                }
//            }
//            System.out.println("disini");
//            fileWriter.write("{\"anjunganpoli\":[" + iyem.substring(0, iyem.length() - 1) + "]}");
//            fileWriter.flush();
//            fileWriter.close();
//            iyem = null;
//
//        } catch (Exception e) {
//            System.out.println("Notifikasi poli : " + e);
//        }
//    }
    private void tentukanHari() {
        try {
            java.sql.Date hariperiksa = java.sql.Date.valueOf(Valid.SetTgl(TanggalPeriksa.getSelectedItem().toString()));
            cal.setTime(hariperiksa);
            day = cal.get(Calendar.DAY_OF_WEEK);
            switch (day) {
                case 1:
                    hari = "AKHAD";
                    break;
                case 2:
                    hari = "SENIN";
                    break;
                case 3:
                    hari = "SELASA";
                    break;
                case 4:
                    hari = "RABU";
                    break;
                case 5:
                    hari = "KAMIS";
                    break;
                case 6:
                    hari = "JUMAT";
                    break;
                case 7:
                    hari = "SABTU";
                    break;
                default:
                    break;
            }
            System.out.println(hari);

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

    }

    private void isCekPasien() {
        try {
            ps3 = koneksi.prepareStatement("select nm_pasien,concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) asal,"
                    + "namakeluarga,keluarga,pasien.kd_pj,penjab.png_jawab,if(tgl_daftar=?,'Baru','Lama') as daftar, "
                    + "TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) as tahun,pasien.no_peserta, "
                    + "(TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12)) as bulan, "
                    + "TIMESTAMPDIFF(DAY, DATE_ADD(DATE_ADD(tgl_lahir,INTERVAL TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) YEAR), INTERVAL TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12) MONTH), CURDATE()) as hari,pasien.no_ktp,pasien.no_tlp "
                    + "from pasien inner join kelurahan on pasien.kd_kel=kelurahan.kd_kel "
                    + "inner join kecamatan on pasien.kd_kec=kecamatan.kd_kec "
                    + "inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab "
                    + "inner join penjab on pasien.kd_pj=penjab.kd_pj "
                    + "where pasien.no_rkm_medis=?");
            try {
                ps3.setString(1, Valid.SetTgl(TanggalPeriksa.getSelectedItem() + ""));
                ps3.setString(2, lblNoRM.getText());
                rs = ps3.executeQuery();
                while (rs.next()) {
                    TAlmt.setText(rs.getString("asal"));
                    TPngJwb.setText(rs.getString("namakeluarga"));
                    THbngn.setText(rs.getString("keluarga"));
                    NoTelpPasien.setText(rs.getString("no_tlp"));
                    umur = "0";
                    sttsumur = "Th";
                    if (rs.getInt("tahun") > 0) {
                        umur = rs.getString("tahun");
                        sttsumur = "Th";
                    } else if (rs.getInt("tahun") == 0) {
                        if (rs.getInt("bulan") > 0) {
                            umur = rs.getString("bulan");
                            sttsumur = "Bl";
                        } else if (rs.getInt("bulan") == 0) {
                            umur = rs.getString("hari");
                            sttsumur = "Hr";
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
            } finally {
                if (rs != null) {
                    rs.close();
                }

                if (ps3 != null) {
                    ps3.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        status = "Baru";
        if (Sequel.cariInteger("select count(no_rkm_medis) from reg_periksa where no_rkm_medis=? and kd_poli=?", lblNoRM.getText(), kode_poli) > 0) {
            status = "Lama";
        }

    }

    private void MnCetakRegisterActionPerformed(String norawat) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", Sequel.cariIsi("select nama_instansi from setting"));
        param.put("alamatrs", Sequel.cariIsi("select alamat_instansi from setting"));
        param.put("kotars", Sequel.cariIsi("select kabupaten from setting"));
        param.put("propinsirs", Sequel.cariIsi("select propinsi from setting"));
        param.put("kontakrs", Sequel.cariIsi("select kontak from setting"));
        param.put("emailrs", Sequel.cariIsi("select email from setting"));
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReportqryabdul("rptBuktiRegister.jasper", "report", "::[ Bukti Register ]::",
                "select IF ((SELECT count( booking_registrasi.no_rkm_medis ) FROM booking_registrasi WHERE booking_registrasi.STATUS = 'Terdaftar'  AND booking_registrasi.no_rkm_medis = reg_periksa.no_rkm_medis AND booking_registrasi.tanggal_periksa = reg_periksa .tgl_registrasi AND kd_dokter = reg_periksa.kd_dokter )= 1,CONCAT( 'A', reg_periksa.no_reg ),CONCAT( 'W', reg_periksa.no_reg ) ) AS no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,pasien.no_tlp,"
                + "reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.umur as umur,poliklinik.nm_poli,"
                + "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,reg_periksa.stts_daftar,penjab.png_jawab "
                + "from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join penjab "
                + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                + "and reg_periksa.kd_pj=penjab.kd_pj and reg_periksa.kd_poli=poliklinik.kd_poli where reg_periksa.no_rawat='" + norawat + "' ", param);
        System.out.println(norawat);
        this.setCursor(Cursor.getDefaultCursor());

    }

}
