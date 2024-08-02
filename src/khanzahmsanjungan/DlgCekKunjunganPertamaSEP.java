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

import bridging.ApiMobileJKN;
import bridging.BPJSCekReferensiFaskes;
import bridging.BPJSCekReferensiPenyakit;
import bridging.BPJSCekReferensiPoli;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.akses;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

/**
 *
 * @author Kode
 */
public class DlgCekKunjunganPertamaSEP extends javax.swing.JDialog {

    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
    private String umur = "0", sttsumur = "Th";
    private String status = "Baru", BASENOREG = "", URUTNOREG = "", aktifjadwal = "", utc = "", requestJson = "", link = "", URL = "";
    private Properties prop = new Properties();
    private int lebar = 0, tinggi = 0;
    private ApiMobileJKN api = new ApiMobileJKN();
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private BPJSCekReferensiFaskes faskes = new BPJSCekReferensiFaskes(null, false);
    private BPJSCekReferensiPenyakit penyakit = new BPJSCekReferensiPenyakit(null, false);
    private BPJSCekReferensiPoli polivclaim = new BPJSCekReferensiPoli(null, false);
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode nameNode;
    private JsonNode response;

    /**
     * Creates new form DlgAdmin
     *
     * @param parent
     * @param id
     */
    public DlgCekKunjunganPertamaSEP(java.awt.Frame parent, boolean id) {
        super(parent, id);

        initComponents();
        WindowRujukan.setSize(600, 200);

        faskes.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (faskes.getTable().getSelectedRow() != -1) {
                    KdPpkRujukan1.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(), 1).toString());
                    NmPpkRujukan1.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(), 2).toString());
                    KdPpkRujukan1.requestFocus();
                }
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

        faskes.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    faskes.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        penyakit.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (penyakit.getTable().getSelectedRow() != -1) {
                    KdPenyakit1.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 1).toString());
                    NmPenyakit1.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 2).toString());
                    KdPenyakit1.requestFocus();
                }
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

        penyakit.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    penyakit.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        polivclaim.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (polivclaim.getTable().getSelectedRow() != -1) {

                    KdPoli1.setText(polivclaim.getTable().getValueAt(polivclaim.getTable().getSelectedRow(), 1).toString());
                    NmPoli1.setText(polivclaim.getTable().getValueAt(polivclaim.getTable().getSelectedRow(), 2).toString());
                    KdPoli1.requestFocus();

                }
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

        polivclaim.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    polivclaim.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        LblKdPoli = new component.Label();
        LblKdDokter = new component.Label();
        NoReg = new component.TextBox();
        NoRawat = new component.TextBox();
        Biaya = new component.TextBox();
        WindowRujukan = new javax.swing.JDialog();
        internalFrame8 = new widget.InternalFrame();
        BtnCloseIn5 = new widget.Button();
        BtnSimpan5 = new widget.Button();
        lblNoSEP = new widget.Label();
        TanggalRujukKeluar = new widget.Tanggal();
        jLabel12 = new widget.Label();
        KdPpkRujukan1 = new widget.TextBox();
        NmPpkRujukan1 = new widget.TextBox();
        btnPPKRujukan1 = new widget.Button();
        jLabel44 = new widget.Label();
        JenisPelayanan1 = new widget.ComboBox();
        jLabel45 = new widget.Label();
        KdPenyakit1 = new widget.TextBox();
        NmPenyakit1 = new widget.TextBox();
        btnDiagnosa1 = new widget.Button();
        LabelPoli1 = new widget.Label();
        KdPoli1 = new widget.TextBox();
        NmPoli1 = new widget.TextBox();
        btnPoli1 = new widget.Button();
        jLabel46 = new widget.Label();
        TipeRujukan = new widget.ComboBox();
        jLabel47 = new widget.Label();
        Catatan1 = new widget.TextBox();
        jLabel50 = new widget.Label();
        TanggalKunjungRujukan = new widget.Tanggal();
        jLabel40 = new widget.Label();
        WindowMenu = new javax.swing.JDialog();
        btnAdmin5 = new widget.ButtonBig();
        btnAdmin6 = new widget.ButtonBig();
        btnAdmin9 = new widget.ButtonBig();
        btnAdmin7 = new widget.ButtonBig();
        jPanel2 = new javax.swing.JPanel();
        PanelWall = new usu.widget.glass.PanelGlass();
        jPanel1 = new component.Panel();
        NoRMPasien = new component.TextBox();
        jLabel28 = new component.Label();
        BtnClose = new widget.ButtonBig();
        BtnClose2 = new widget.ButtonBig();
        jPanel3 = new javax.swing.JPanel();
        btnAngka8 = new javax.swing.JButton();
        btnAngka7 = new javax.swing.JButton();
        btnAngka9 = new javax.swing.JButton();
        btnAngka4 = new javax.swing.JButton();
        btnAngka5 = new javax.swing.JButton();
        btnAngka6 = new javax.swing.JButton();
        btnAngka2 = new javax.swing.JButton();
        btnAngka1 = new javax.swing.JButton();
        btnAngka3 = new javax.swing.JButton();
        btnAngka0 = new javax.swing.JButton();
        btnAngkaHps = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();

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

        WindowRujukan.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRujukan.setUndecorated(true);
        WindowRujukan.setResizable(false);

        internalFrame8.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 2, true), "::[ Buat Rujukan Keluar VClaim ]::", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N
        internalFrame8.setLayout(null);

        BtnCloseIn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn5.setMnemonic('U');
        BtnCloseIn5.setText("Tutup");
        BtnCloseIn5.setToolTipText("Alt+U");
        BtnCloseIn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn5ActionPerformed(evt);
            }
        });
        internalFrame8.add(BtnCloseIn5);
        BtnCloseIn5.setBounds(720, 160, 100, 30);

        BtnSimpan5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan5.setMnemonic('S');
        BtnSimpan5.setText("Simpan");
        BtnSimpan5.setToolTipText("Alt+S");
        BtnSimpan5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan5ActionPerformed(evt);
            }
        });
        internalFrame8.add(BtnSimpan5);
        BtnSimpan5.setBounds(30, 160, 100, 30);

        lblNoSEP.setText("lblNoSEP");
        internalFrame8.add(lblNoSEP);
        lblNoSEP.setBounds(10, 20, 410, 23);

        TanggalRujukKeluar.setForeground(new java.awt.Color(50, 70, 50));
        TanggalRujukKeluar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-04-2024" }));
        TanggalRujukKeluar.setDisplayFormat("dd-MM-yyyy");
        TanggalRujukKeluar.setOpaque(false);
        TanggalRujukKeluar.setPreferredSize(new java.awt.Dimension(95, 23));
        internalFrame8.add(TanggalRujukKeluar);
        TanggalRujukKeluar.setBounds(110, 60, 90, 23);

        jLabel12.setText("PPK Rujukan :");
        internalFrame8.add(jLabel12);
        jLabel12.setBounds(10, 90, 102, 23);

        KdPpkRujukan1.setEditable(false);
        KdPpkRujukan1.setBackground(new java.awt.Color(245, 250, 240));
        KdPpkRujukan1.setHighlighter(null);
        internalFrame8.add(KdPpkRujukan1);
        KdPpkRujukan1.setBounds(110, 90, 75, 23);

        NmPpkRujukan1.setEditable(false);
        NmPpkRujukan1.setBackground(new java.awt.Color(245, 250, 240));
        NmPpkRujukan1.setHighlighter(null);
        internalFrame8.add(NmPpkRujukan1);
        NmPpkRujukan1.setBounds(190, 90, 200, 23);

        btnPPKRujukan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPPKRujukan1.setMnemonic('X');
        btnPPKRujukan1.setToolTipText("Alt+X");
        btnPPKRujukan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPPKRujukan1ActionPerformed(evt);
            }
        });
        btnPPKRujukan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPPKRujukan1KeyPressed(evt);
            }
        });
        internalFrame8.add(btnPPKRujukan1);
        btnPPKRujukan1.setBounds(390, 90, 28, 23);

        jLabel44.setText("Jns.Pelayanan :");
        internalFrame8.add(jLabel44);
        jLabel44.setBounds(430, 90, 85, 23);

        JenisPelayanan1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1. Rawat Inap", "2. Rawat Jalan" }));
        JenisPelayanan1.setSelectedIndex(1);
        JenisPelayanan1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JenisPelayanan1ItemStateChanged(evt);
            }
        });
        JenisPelayanan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JenisPelayanan1KeyPressed(evt);
            }
        });
        internalFrame8.add(JenisPelayanan1);
        JenisPelayanan1.setBounds(520, 90, 123, 23);

        jLabel45.setText("Diagnosa Rujuk :");
        internalFrame8.add(jLabel45);
        jLabel45.setBounds(10, 120, 102, 23);

        KdPenyakit1.setEditable(false);
        KdPenyakit1.setBackground(new java.awt.Color(245, 250, 240));
        KdPenyakit1.setHighlighter(null);
        internalFrame8.add(KdPenyakit1);
        KdPenyakit1.setBounds(110, 120, 75, 23);

        NmPenyakit1.setEditable(false);
        NmPenyakit1.setBackground(new java.awt.Color(245, 250, 240));
        NmPenyakit1.setHighlighter(null);
        internalFrame8.add(NmPenyakit1);
        NmPenyakit1.setBounds(190, 120, 200, 23);

        btnDiagnosa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDiagnosa1.setMnemonic('X');
        btnDiagnosa1.setToolTipText("Alt+X");
        btnDiagnosa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiagnosa1ActionPerformed(evt);
            }
        });
        btnDiagnosa1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDiagnosa1KeyPressed(evt);
            }
        });
        internalFrame8.add(btnDiagnosa1);
        btnDiagnosa1.setBounds(390, 120, 28, 23);

        LabelPoli1.setText("Poli Tujuan :");
        internalFrame8.add(LabelPoli1);
        LabelPoli1.setBounds(430, 120, 85, 23);

        KdPoli1.setEditable(false);
        KdPoli1.setBackground(new java.awt.Color(245, 250, 240));
        KdPoli1.setHighlighter(null);
        internalFrame8.add(KdPoli1);
        KdPoli1.setBounds(520, 120, 65, 23);

        NmPoli1.setEditable(false);
        NmPoli1.setBackground(new java.awt.Color(245, 250, 240));
        NmPoli1.setHighlighter(null);
        internalFrame8.add(NmPoli1);
        NmPoli1.setBounds(590, 120, 200, 23);

        btnPoli1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPoli1.setMnemonic('X');
        btnPoli1.setToolTipText("Alt+X");
        btnPoli1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPoli1ActionPerformed(evt);
            }
        });
        btnPoli1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPoli1KeyPressed(evt);
            }
        });
        internalFrame8.add(btnPoli1);
        btnPoli1.setBounds(790, 120, 28, 23);

        jLabel46.setText("Tipe Rujukan :");
        internalFrame8.add(jLabel46);
        jLabel46.setBounds(210, 60, 80, 23);

        TipeRujukan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0. Penuh", "1. Partial", "2. Rujuk Balik" }));
        TipeRujukan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TipeRujukanItemStateChanged(evt);
            }
        });
        TipeRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipeRujukanActionPerformed(evt);
            }
        });
        TipeRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TipeRujukanKeyPressed(evt);
            }
        });
        internalFrame8.add(TipeRujukan);
        TipeRujukan.setBounds(290, 60, 130, 23);

        jLabel47.setText("Catatan :");
        internalFrame8.add(jLabel47);
        jLabel47.setBounds(430, 60, 85, 23);

        Catatan1.setHighlighter(null);
        Catatan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Catatan1KeyPressed(evt);
            }
        });
        internalFrame8.add(Catatan1);
        Catatan1.setBounds(520, 60, 298, 23);

        jLabel50.setText("R.Kunjungan :");
        internalFrame8.add(jLabel50);
        jLabel50.setBounds(650, 90, 80, 23);

        TanggalKunjungRujukan.setForeground(new java.awt.Color(50, 70, 50));
        TanggalKunjungRujukan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-04-2024" }));
        TanggalKunjungRujukan.setDisplayFormat("dd-MM-yyyy");
        TanggalKunjungRujukan.setOpaque(false);
        TanggalKunjungRujukan.setPreferredSize(new java.awt.Dimension(95, 23));
        internalFrame8.add(TanggalKunjungRujukan);
        TanggalKunjungRujukan.setBounds(730, 90, 90, 23);

        jLabel40.setText("Tanggal Rujukan :");
        internalFrame8.add(jLabel40);
        jLabel40.setBounds(10, 60, 102, 23);

        WindowRujukan.getContentPane().add(internalFrame8, java.awt.BorderLayout.CENTER);

        WindowMenu.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowMenu.getContentPane().setLayout(new java.awt.GridLayout());

        btnAdmin5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/BPJS_Kesehatan_Logo.png"))); // NOI18N
        btnAdmin5.setText("RUJUKAN BARU");
        btnAdmin5.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        btnAdmin5.setIconTextGap(0);
        btnAdmin5.setPreferredSize(new java.awt.Dimension(150, 45));
        btnAdmin5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdmin5ActionPerformed(evt);
            }
        });
        WindowMenu.getContentPane().add(btnAdmin5);

        btnAdmin6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/BPJS_Kesehatan_Logo.png"))); // NOI18N
        btnAdmin6.setText("SEP KONTROL");
        btnAdmin6.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        btnAdmin6.setIconTextGap(0);
        btnAdmin6.setPreferredSize(new java.awt.Dimension(150, 45));
        btnAdmin6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdmin6ActionPerformed(evt);
            }
        });
        WindowMenu.getContentPane().add(btnAdmin6);

        btnAdmin9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/BPJS_Kesehatan_Logo.png"))); // NOI18N
        btnAdmin9.setText("KONTROL BEDA POLI");
        btnAdmin9.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        btnAdmin9.setIconTextGap(0);
        btnAdmin9.setPreferredSize(new java.awt.Dimension(150, 45));
        btnAdmin9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdmin9ActionPerformed(evt);
            }
        });
        WindowMenu.getContentPane().add(btnAdmin9);

        btnAdmin7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/BPJS_Kesehatan_Logo.png"))); // NOI18N
        btnAdmin7.setText("RUJUK KELUAR");
        btnAdmin7.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        btnAdmin7.setIconTextGap(0);
        btnAdmin7.setPreferredSize(new java.awt.Dimension(150, 45));
        btnAdmin7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdmin7ActionPerformed(evt);
            }
        });
        WindowMenu.getContentPane().add(btnAdmin7);

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

        jPanel2.setBackground(new java.awt.Color(238, 238, 255));
        jPanel2.setForeground(new java.awt.Color(238, 238, 255));

        PanelWall.setBackground(new java.awt.Color(238, 238, 255));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/bpjs-amiz.png"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setForeground(new java.awt.Color(238, 238, 255));
        PanelWall.setPreferredSize(new java.awt.Dimension(500, 150));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(238, 238, 255));

        javax.swing.GroupLayout PanelWallLayout = new javax.swing.GroupLayout(PanelWall);
        PanelWall.setLayout(PanelWallLayout);
        PanelWallLayout.setHorizontalGroup(
            PanelWallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        PanelWallLayout.setVerticalGroup(
            PanelWallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        jPanel2.add(PanelWall);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(238, 238, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 215, 255)), "::[ Cek Data Pasien!!! ]::", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Poppins", 0, 30), new java.awt.Color(0, 131, 62))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(0, 131, 62));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 70));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        NoRMPasien.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 131, 62), 2, true));
        NoRMPasien.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NoRMPasien.setFont(new java.awt.Font("Poppins", 0, 36)); // NOI18N
        NoRMPasien.setPreferredSize(new java.awt.Dimension(350, 75));
        NoRMPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoRMPasienActionPerformed(evt);
            }
        });
        NoRMPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRMPasienKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(NoRMPasien, gridBagConstraints);

        jLabel28.setForeground(new java.awt.Color(0, 131, 62));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("NO JKN / NO RM / NIK");
        jLabel28.setFont(new java.awt.Font("Poppins", 0, 36)); // NOI18N
        jLabel28.setPreferredSize(new java.awt.Dimension(450, 75));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.ipady = 5;
        jPanel1.add(jLabel28, gridBagConstraints);

        BtnClose.setBackground(new java.awt.Color(255, 255, 255));
        BtnClose.setForeground(new java.awt.Color(51, 51, 51));
        BtnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/exit.png"))); // NOI18N
        BtnClose.setMnemonic('U');
        BtnClose.setToolTipText("Alt+U");
        BtnClose.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BtnClose.setHorizontalTextPosition(javax.swing.SwingConstants.TRAILING);
        BtnClose.setIconTextGap(2);
        BtnClose.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnClose.setPreferredSize(new java.awt.Dimension(100, 75));
        BtnClose.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        BtnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 13;
        jPanel1.add(BtnClose, gridBagConstraints);

        BtnClose2.setBackground(new java.awt.Color(255, 255, 255));
        BtnClose2.setForeground(new java.awt.Color(51, 51, 51));
        BtnClose2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/konfirmasi.png"))); // NOI18N
        BtnClose2.setMnemonic('U');
        BtnClose2.setToolTipText("Alt+U");
        BtnClose2.setFont(new java.awt.Font("Poppins", 1, 11)); // NOI18N
        BtnClose2.setIconTextGap(0);
        BtnClose2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        BtnClose2.setPreferredSize(new java.awt.Dimension(100, 75));
        BtnClose2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        BtnClose2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnClose2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 13;
        jPanel1.add(BtnClose2, gridBagConstraints);

        jPanel3.setBackground(new java.awt.Color(238, 238, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(350, 399));

        btnAngka8.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btnAngka8.setText("8");
        btnAngka8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAngka8ActionPerformed(evt);
            }
        });

        btnAngka7.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btnAngka7.setText("7");
        btnAngka7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAngka7ActionPerformed(evt);
            }
        });

        btnAngka9.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btnAngka9.setText("9");
        btnAngka9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAngka9ActionPerformed(evt);
            }
        });

        btnAngka4.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btnAngka4.setText("4");
        btnAngka4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAngka4ActionPerformed(evt);
            }
        });

        btnAngka5.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btnAngka5.setText("5");
        btnAngka5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAngka5ActionPerformed(evt);
            }
        });

        btnAngka6.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btnAngka6.setText("6");
        btnAngka6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAngka6ActionPerformed(evt);
            }
        });

        btnAngka2.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btnAngka2.setText("2");
        btnAngka2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAngka2ActionPerformed(evt);
            }
        });

        btnAngka1.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btnAngka1.setText("1");
        btnAngka1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAngka1ActionPerformed(evt);
            }
        });

        btnAngka3.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btnAngka3.setText("3");
        btnAngka3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAngka3ActionPerformed(evt);
            }
        });

        btnAngka0.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btnAngka0.setText("0");
        btnAngka0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAngka0ActionPerformed(evt);
            }
        });

        btnAngkaHps.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btnAngkaHps.setText("<-");
        btnAngkaHps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAngkaHpsActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btnClear.setText("C");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnAngka4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnAngka5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnAngka6, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnAngka1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnAngka2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnAngka3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnAngka0, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnAngkaHps, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnAngka7, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnAngka8, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnAngka9, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAngka7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAngka8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAngka9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAngka4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAngka5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnAngka6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAngka3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAngka2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAngka1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAngka0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAngkaHps, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jPanel3, gridBagConstraints);

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

    private void NoRMPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoRMPasienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoRMPasienActionPerformed

    private void NoRMPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRMPasienKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            WindowMenu.setSize(jPanel1.getWidth() - 50, jPanel1.getHeight() - 50);
//            WindowMenu.setVisible(true);
            if (!NoRMPasien.getText().equals("")) {
                if (Sequel.cariInteger("select count(referensi_mobilejkn_bpjs.norm) from referensi_mobilejkn_bpjs where referensi_mobilejkn_bpjs.norm='" + NoRMPasien.getText() + "' and referensi_mobilejkn_bpjs.tanggalperiksa=current_date") > 0) {
                    JOptionPane.showMessageDialog(rootPane, "Pasien telah menggunakan Mobile JKN. Silahkan cekin menggunakan menu MobileJKN");
                } else if (Sequel.cariInteger("select count(referensi_mobilejkn_bpjs.nomorkartu) from referensi_mobilejkn_bpjs where referensi_mobilejkn_bpjs.nomorkartu='" + NoRMPasien.getText() + "' and referensi_mobilejkn_bpjs.tanggalperiksa=current_date") > 0) {
                    JOptionPane.showMessageDialog(rootPane, "Pasien telah menggunakan Mobile JKN. Silahkan cekin menggunakan menu MobileJKN");
                } else {
                    if (Sequel.cariInteger("select count(pasien.no_peserta) from pasien where pasien.no_peserta='" + NoRMPasien.getText() + "'") == 1) {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        DlgRegistrasiSEPPertama form = new DlgRegistrasiSEPPertama(null, true);
                        form.tampil(NoRMPasien.getText());
                        form.tampilfingerprint(NoRMPasien.getText());
                        form.setSize(this.getWidth(), this.getHeight());
                        form.setLocationRelativeTo(jPanel1);
                        this.dispose();
                        form.setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                    } else if (Sequel.cariInteger("select count(pasien.no_rkm_medis) from pasien where pasien.no_rkm_medis='" + NoRMPasien.getText() + "'") == 1) {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        DlgRegistrasiSEPPertama form = new DlgRegistrasiSEPPertama(null, true);
                        form.tampil(Sequel.cariIsi("select pasien.no_peserta from pasien where pasien.no_rkm_medis='" + NoRMPasien.getText() + "'"));
                        form.tampilfingerprint(Sequel.cariIsi("select pasien.no_peserta from pasien where pasien.no_rkm_medis='" + NoRMPasien.getText() + "'"));
                        form.setSize(this.getWidth(), this.getHeight());
                        form.setLocationRelativeTo(jPanel1);
                        this.dispose();
                        form.setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());

                    } else if (Sequel.cariInteger("select count(pasien.no_ktp) from pasien where pasien.no_ktp='" + NoRMPasien.getText() + "'") == 1) {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        DlgRegistrasiSEPPertama form = new DlgRegistrasiSEPPertama(null, true);
                        form.tampil(Sequel.cariIsi("select pasien.no_peserta from pasien where pasien.no_ktp='" + NoRMPasien.getText() + "'"));
                        form.tampilfingerprint(Sequel.cariIsi("select pasien.no_peserta from pasien where pasien.no_ktp='" + NoRMPasien.getText() + "'"));
                        form.setSize(this.getWidth(), this.getHeight());
                        form.setLocationRelativeTo(jPanel1);
                        this.dispose();
                        form.setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());

                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Data pasien tidak ditemukan!");
                    }
                }

            } else {
                JOptionPane.showMessageDialog(rootPane, "isian masih kosong ");
            }

        }

    }//GEN-LAST:event_NoRMPasienKeyPressed

    private void BtnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseActionPerformed

        dispose();
    }//GEN-LAST:event_BtnCloseActionPerformed

    private void BtnClose2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnClose2ActionPerformed
//        WindowMenu.setSize(jPanel1.getWidth() - 50, jPanel1.getHeight() - 50);
//        WindowMenu.setLocationRelativeTo(jPanel1);
//        WindowMenu.setVisible(true);
        if (!NoRMPasien.getText().equals("")) {
            if (Sequel.cariInteger("select count(referensi_mobilejkn_bpjs.norm) from referensi_mobilejkn_bpjs where referensi_mobilejkn_bpjs.norm='" + NoRMPasien.getText() + "' and referensi_mobilejkn_bpjs.tanggalperiksa=current_date") > 0) {
                JOptionPane.showMessageDialog(rootPane, "Pasien telah menggunakan Mobile JKN. Silahkan cekin menggunakan menu MobileJKN");
            } else if (Sequel.cariInteger("select count(referensi_mobilejkn_bpjs.nomorkartu) from referensi_mobilejkn_bpjs where referensi_mobilejkn_bpjs.nomorkartu='" + NoRMPasien.getText() + "' and referensi_mobilejkn_bpjs.tanggalperiksa=current_date") > 0) {
                JOptionPane.showMessageDialog(rootPane, "Pasien telah menggunakan Mobile JKN. Silahkan cekin menggunakan menu MobileJKN");
            } else {
                if (Sequel.cariInteger("select count(pasien.no_peserta) from pasien where pasien.no_peserta='" + NoRMPasien.getText() + "'") == 1) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgRegistrasiSEPPertama form = new DlgRegistrasiSEPPertama(null, true);
                    form.tampil(NoRMPasien.getText());
                    form.tampilfingerprint(NoRMPasien.getText());
                    form.setSize(this.getWidth(), this.getHeight());
                    form.setLocationRelativeTo(jPanel1);
                    this.dispose();
                    form.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                } else if (Sequel.cariInteger("select count(pasien.no_rkm_medis) from pasien where pasien.no_rkm_medis='" + NoRMPasien.getText() + "'") == 1) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgRegistrasiSEPPertama form = new DlgRegistrasiSEPPertama(null, true);
                    form.tampil(Sequel.cariIsi("select pasien.no_peserta from pasien where pasien.no_rkm_medis='" + NoRMPasien.getText() + "'"));
                    form.tampilfingerprint(Sequel.cariIsi("select pasien.no_peserta from pasien where pasien.no_rkm_medis='" + NoRMPasien.getText() + "'"));
                    form.setSize(this.getWidth(), this.getHeight());
                    form.setLocationRelativeTo(jPanel1);
                    this.dispose();
                    form.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());

                } else if (Sequel.cariInteger("select count(pasien.no_ktp) from pasien where pasien.no_ktp='" + NoRMPasien.getText() + "'") == 1) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgRegistrasiSEPPertama form = new DlgRegistrasiSEPPertama(null, true);
                    form.tampil(Sequel.cariIsi("select pasien.no_peserta from pasien where pasien.no_ktp='" + NoRMPasien.getText() + "'"));
                    form.tampilfingerprint(Sequel.cariIsi("select pasien.no_peserta from pasien where pasien.no_ktp='" + NoRMPasien.getText() + "'"));
                    form.setSize(this.getWidth(), this.getHeight());
                    form.setLocationRelativeTo(jPanel1);
                    this.dispose();
                    form.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());

                } else {
                    JOptionPane.showMessageDialog(rootPane, "Data pasien tidak ditemukan!");
                }
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "isian masih kosong ");
        }

    }//GEN-LAST:event_BtnClose2ActionPerformed

    private void btnAngka8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAngka8ActionPerformed
        NoRMPasien.setText(NoRMPasien.getText() + "8");
    }//GEN-LAST:event_btnAngka8ActionPerformed

    private void btnAngka7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAngka7ActionPerformed
        NoRMPasien.setText(NoRMPasien.getText() + "7");
    }//GEN-LAST:event_btnAngka7ActionPerformed

    private void btnAngka9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAngka9ActionPerformed
        NoRMPasien.setText(NoRMPasien.getText() + "9");
    }//GEN-LAST:event_btnAngka9ActionPerformed

    private void btnAngka4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAngka4ActionPerformed
        NoRMPasien.setText(NoRMPasien.getText() + "4");
    }//GEN-LAST:event_btnAngka4ActionPerformed

    private void btnAngka5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAngka5ActionPerformed
        NoRMPasien.setText(NoRMPasien.getText() + "5");
    }//GEN-LAST:event_btnAngka5ActionPerformed

    private void btnAngka6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAngka6ActionPerformed
        NoRMPasien.setText(NoRMPasien.getText() + "6");
    }//GEN-LAST:event_btnAngka6ActionPerformed

    private void btnAngka2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAngka2ActionPerformed
        NoRMPasien.setText(NoRMPasien.getText() + "2");
    }//GEN-LAST:event_btnAngka2ActionPerformed

    private void btnAngka1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAngka1ActionPerformed
        NoRMPasien.setText(NoRMPasien.getText() + "1");
    }//GEN-LAST:event_btnAngka1ActionPerformed

    private void btnAngka3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAngka3ActionPerformed
        NoRMPasien.setText(NoRMPasien.getText() + "3");
    }//GEN-LAST:event_btnAngka3ActionPerformed

    private void btnAngka0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAngka0ActionPerformed
        NoRMPasien.setText(NoRMPasien.getText() + "0");
    }//GEN-LAST:event_btnAngka0ActionPerformed

    private void btnAngkaHpsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAngkaHpsActionPerformed
        int length = NoRMPasien.getText().length();
        int number = NoRMPasien.getText().length() - 1;
        String store;
        if (length > 0) {
            StringBuilder back = new StringBuilder(NoRMPasien.getText());
            back.deleteCharAt(number);
            store = back.toString();
            NoRMPasien.setText(store);
        }
    }//GEN-LAST:event_btnAngkaHpsActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        int length = NoRMPasien.getText().length();
        if (length > 0) {
            NoRMPasien.setText("");
        }
    }//GEN-LAST:event_btnClearActionPerformed

    private void BtnCloseIn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn5ActionPerformed
        WindowRujukan.dispose();
    }//GEN-LAST:event_BtnCloseIn5ActionPerformed

    private void BtnSimpan5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan5ActionPerformed

        if (NoRMPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih Data..!!");
        } else {
            try {
                headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.add("X-Cons-ID", koneksiDB.CONSIDAPIBPJS());
                utc = String.valueOf(api.GetUTCdatetimeAsString());
                headers.add("X-Timestamp", utc);
                headers.add("X-Signature", api.getHmac(utc));
                headers.add("user_key", koneksiDB.USERKEYAPIBPJS());
                URL = link + "/Rujukan/2.0/insert";
                requestJson = "{"
                        + "\"request\": {"
                        + "\"t_rujukan\": {"
                        + "\"noSep\": \"" + NoRMPasien.getText() + "\","
                        + "\"tglRujukan\": \"" + Valid.SetTgl(TanggalRujukKeluar.getSelectedItem() + "") + "\","
                        + "\"tglRencanaKunjungan\": \"" + Valid.SetTgl(TanggalKunjungRujukan.getSelectedItem() + "") + "\","
                        + "\"ppkDirujuk\": \"" + KdPpkRujukan1.getText() + "\","
                        + "\"jnsPelayanan\": \"" + JenisPelayanan1.getSelectedItem().toString().substring(0, 1) + "\","
                        + "\"catatan\": \"" + Catatan1.getText() + "\","
                        + "\"diagRujukan\": \"" + KdPenyakit1.getText() + "\","
                        + "\"tipeRujukan\": \"" + TipeRujukan.getSelectedItem().toString().substring(0, 1) + "\","
                        + "\"poliRujukan\": \"" + KdPoli1.getText() + "\","
                        + "\"user\": \"ANJUNGANINDRIATI\""
                        + "}"
                        + "}"
                        + "}";
                System.out.println("JSON : " + requestJson);
                requestEntity = new HttpEntity(requestJson, headers);
                root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                nameNode = root.path("metaData");
                System.out.println("code : " + nameNode.path("code").asText());
                System.out.println("message : " + nameNode.path("message").asText());
                if (nameNode.path("code").asText().equals("200")) {
                    response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
                    //response = root.path("response");

                    if (Sequel.menyimpantf2("bridging_rujukan_bpjs", "?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rujukan", 14, new String[]{
                        NoRMPasien.getText(),
                        Valid.SetTgl(TanggalRujukKeluar.getSelectedItem() + ""),
                        Valid.SetTgl(TanggalKunjungRujukan.getSelectedItem() + ""),
                        KdPpkRujukan1.getText(),
                        NmPpkRujukan1.getText(),
                        JenisPelayanan1.getSelectedItem().toString().substring(0, 1),
                        Catatan1.getText(),
                        KdPenyakit1.getText(),
                        NmPenyakit1.getText(),
                        TipeRujukan.getSelectedItem().toString(), KdPoli1.getText(), NmPoli1.getText(), response.path("rujukan").path("noRujukan").asText(),
                        "APM"
                    }) == true) {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        Map<String, Object> param = new HashMap<>();
                        param.put("namars", akses.getnamars());
                        param.put("alamatrs", akses.getalamatrs());
                        param.put("kotars", akses.getkabupatenrs());
                        param.put("propinsirs", akses.getpropinsirs());
                        param.put("kontakrs", akses.getkontakrs());
                        param.put("norujuk", response.path("rujukan").path("noRujukan").asText());
                        param.put("logo", Sequel.cariGambar("select gambar.bpjs from gambar"));
                        Valid.MyReport("rptBridgingRujukanBPJSpotrait.jasper", param, "::[ Surat Rujukan Keluar VClaim ]::");
                        this.setCursor(Cursor.getDefaultCursor());
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, nameNode.path("message").asText());
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi Bridging : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
                }
            }
        }

    }//GEN-LAST:event_BtnSimpan5ActionPerformed

    private void btnPPKRujukan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPPKRujukan1ActionPerformed

        faskes.setSize(jPanel1.getWidth() - 20, jPanel1.getHeight() - 20);
        faskes.setLocationRelativeTo(jPanel1);
        faskes.setVisible(true);
    }//GEN-LAST:event_btnPPKRujukan1ActionPerformed

    private void btnPPKRujukan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPPKRujukan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPPKRujukan1KeyPressed

    private void JenisPelayanan1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JenisPelayanan1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_JenisPelayanan1ItemStateChanged

    private void JenisPelayanan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JenisPelayanan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JenisPelayanan1KeyPressed

    private void btnDiagnosa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiagnosa1ActionPerformed

        penyakit.setSize(jPanel1.getWidth() - 20, jPanel1.getHeight() - 20);
        penyakit.setLocationRelativeTo(jPanel1);
        penyakit.setVisible(true);
    }//GEN-LAST:event_btnDiagnosa1ActionPerformed

    private void btnDiagnosa1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDiagnosa1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDiagnosa1KeyPressed

    private void btnPoli1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPoli1ActionPerformed

        polivclaim.setSize(jPanel1.getWidth() - 20, jPanel1.getHeight() - 20);
        polivclaim.setLocationRelativeTo(jPanel1);
        polivclaim.setVisible(true);
    }//GEN-LAST:event_btnPoli1ActionPerformed

    private void btnPoli1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPoli1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPoli1KeyPressed

    private void TipeRujukanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TipeRujukanItemStateChanged

    }//GEN-LAST:event_TipeRujukanItemStateChanged

    private void TipeRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipeRujukanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TipeRujukanActionPerformed

    private void TipeRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TipeRujukanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TipeRujukanKeyPressed

    private void Catatan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Catatan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Catatan1KeyPressed

    private void btnAdmin5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdmin5ActionPerformed
        if (!NoRMPasien.getText().equals("")) {
            if (Sequel.cariInteger("select count(referensi_mobilejkn_bpjs.norm) from referensi_mobilejkn_bpjs where referensi_mobilejkn_bpjs.norm='" + NoRMPasien.getText() + "' and referensi_mobilejkn_bpjs.tanggalperiksa=current_date") > 0) {
                JOptionPane.showMessageDialog(rootPane, "Pasien telah menggunakan Mobile JKN. Silahkan cekin menggunakan menu MobileJKN");
            } else if (Sequel.cariInteger("select count(referensi_mobilejkn_bpjs.nomorkartu) from referensi_mobilejkn_bpjs where referensi_mobilejkn_bpjs.nomorkartu='" + NoRMPasien.getText() + "' and referensi_mobilejkn_bpjs.tanggalperiksa=current_date") > 0) {
                JOptionPane.showMessageDialog(rootPane, "Pasien telah menggunakan Mobile JKN. Silahkan cekin menggunakan menu MobileJKN");
            } else {
                if (Sequel.cariInteger("select count(pasien.no_peserta) from pasien where pasien.no_peserta='" + NoRMPasien.getText() + "'") == 1) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgRegistrasiSEPPertama form = new DlgRegistrasiSEPPertama(null, true);
                    form.tampil(NoRMPasien.getText());
                    form.setSize(this.getWidth(), this.getHeight());
                    form.setLocationRelativeTo(jPanel1);
                    this.dispose();
                    form.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                } else if (Sequel.cariInteger("select count(pasien.no_rkm_medis) from pasien where pasien.no_rkm_medis='" + NoRMPasien.getText() + "'") == 1) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgRegistrasiSEPPertama form = new DlgRegistrasiSEPPertama(null, true);
                    form.tampil(Sequel.cariIsi("select pasien.no_peserta from pasien where pasien.no_rkm_medis='" + NoRMPasien.getText() + "'"));
                    form.setSize(this.getWidth(), this.getHeight());
                    form.setLocationRelativeTo(jPanel1);
                    this.dispose();
                    form.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());

                } else if (Sequel.cariInteger("select count(pasien.no_ktp) from pasien where pasien.no_ktp='" + NoRMPasien.getText() + "'") == 1) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgRegistrasiSEPPertama form = new DlgRegistrasiSEPPertama(null, true);
                    form.tampil(Sequel.cariIsi("select pasien.no_peserta from pasien where pasien.no_ktp='" + NoRMPasien.getText() + "'"));
                    form.setSize(this.getWidth(), this.getHeight());
                    form.setLocationRelativeTo(jPanel1);
                    this.dispose();
                    form.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());

                } else {
                    JOptionPane.showMessageDialog(rootPane, "Data pasien tidak ditemukan!");
                }
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "isian masih kosong ");
        }
    }//GEN-LAST:event_btnAdmin5ActionPerformed

    private void btnAdmin6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdmin6ActionPerformed
        if (Sequel.cariInteger("select count(bridging_surat_kontrol_bpjs.no_surat) from bridging_surat_kontrol_bpjs where bridging_surat_kontrol_bpjs.no_surat='" + NoRMPasien.getText() + "'") > 0) {
//                if (Sequel.cariInteger("select DATEDIFF(bridging_surat_kontrol_bpjs.tgl_rencana,CURRENT_DATE) from bridging_surat_kontrol_bpjs where bridging_surat_kontrol_bpjs.no_surat='" + NoRMPasien.getText() + "'") < 0) {
//
//                }
            String tanggalkontrol = Sequel.cariIsi("select bridging_surat_kontrol_bpjs.tgl_rencana from bridging_surat_kontrol_bpjs where bridging_surat_kontrol_bpjs.no_surat='" + NoRMPasien.getText() + "'");
            if (Sequel.cariInteger("select DATEDIFF('" + tanggalkontrol + "',CURRENT_DATE)") > 0) {

                JOptionPane.showMessageDialog(rootPane, "Jadwal kontrol tidak boleh maju");
                NoRMPasien.setText("");
            } else if (Sequel.cariInteger("select count(referensi_mobilejkn_bpjs.nomorreferensi) from referensi_mobilejkn_bpjs where referensi_mobilejkn_bpjs.nomorreferensi='" + NoRMPasien.getText() + "' and referensi_mobilejkn_bpjs.tanggalperiksa=current_date") > 0) {
                JOptionPane.showMessageDialog(rootPane, "Pasien telah menggunakan Mobile JKN. Silahkan cekin menggunakan menu MobileJKN");
            } else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgRegistrasiSEPPertama form = new DlgRegistrasiSEPPertama(null, true);
                form.tampilKontrol(NoRMPasien.getText());
                form.setSize(this.getWidth(), this.getHeight());
                form.setLocationRelativeTo(jPanel1);
                this.dispose();
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }

        } else {
//                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Data surat kontrol tidak ditemukan!");
            JOptionPane.showMessageDialog(rootPane, "Data surat kontrol tidak ditemukan!");
        }
    }//GEN-LAST:event_btnAdmin6ActionPerformed

    private void btnAdmin9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdmin9ActionPerformed
        if (!NoRMPasien.getText().equals("")) {
            if (Sequel.cariInteger("select count(pasien.no_peserta) from pasien where pasien.no_peserta='" + NoRMPasien.getText() + "'") == 1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgRegistrasiSEPPertama form = new DlgRegistrasiSEPPertama(null, true);
                form.tampilPecahSEP(NoRMPasien.getText());
                form.setSize(this.getWidth(), this.getHeight());
                form.setLocationRelativeTo(jPanel1);
                this.dispose();
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else if (Sequel.cariInteger("select count(pasien.no_rkm_medis) from pasien where pasien.no_rkm_medis='" + NoRMPasien.getText() + "'") == 1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgRegistrasiSEPPertama form = new DlgRegistrasiSEPPertama(null, true);
                form.tampilPecahSEP(Sequel.cariIsi("select pasien.no_peserta from pasien where pasien.no_rkm_medis='" + NoRMPasien.getText() + "'"));
                form.setSize(this.getWidth(), this.getHeight());
                form.setLocationRelativeTo(jPanel1);
                this.dispose();
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());

            } else if (Sequel.cariInteger("select count(pasien.no_ktp) from pasien where pasien.no_ktp='" + NoRMPasien.getText() + "'") == 1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgRegistrasiSEPPertama form = new DlgRegistrasiSEPPertama(null, true);
                form.tampilPecahSEP(Sequel.cariIsi("select pasien.no_peserta from pasien where pasien.no_ktp='" + NoRMPasien.getText() + "'"));
                form.setSize(this.getWidth(), this.getHeight());
                form.setLocationRelativeTo(jPanel1);
                this.dispose();
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());

            } else {
                JOptionPane.showMessageDialog(rootPane, "Data pasien tidak ditemukan!");
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "isian masih kosong ");
        }
    }//GEN-LAST:event_btnAdmin9ActionPerformed

    private void btnAdmin7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdmin7ActionPerformed
        WindowRujukan.setSize(jPanel1.getWidth() - 50, jPanel1.getHeight() - 50);
        WindowRujukan.setLocationRelativeTo(jPanel1);
        WindowRujukan.setVisible(true);
        lblNoSEP.setText(NoRMPasien.getText());
    }//GEN-LAST:event_btnAdmin7ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCekKunjunganPertamaSEP dialog = new DlgCekKunjunganPertamaSEP(new javax.swing.JFrame(), true);
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
    private widget.ButtonBig BtnClose;
    private widget.ButtonBig BtnClose2;
    private widget.Button BtnCloseIn5;
    private widget.Button BtnSimpan5;
    private widget.TextBox Catatan1;
    private widget.ComboBox JenisPelayanan1;
    private widget.TextBox KdPenyakit1;
    private widget.TextBox KdPoli1;
    private widget.TextBox KdPpkRujukan1;
    private widget.Label LabelPoli1;
    private component.Label LblKdDokter;
    private component.Label LblKdPoli;
    private widget.TextBox NmPenyakit1;
    private widget.TextBox NmPoli1;
    private widget.TextBox NmPpkRujukan1;
    private component.TextBox NoRMPasien;
    private component.TextBox NoRawat;
    private component.TextBox NoReg;
    private usu.widget.glass.PanelGlass PanelWall;
    private widget.Tanggal TanggalKunjungRujukan;
    private widget.Tanggal TanggalRujukKeluar;
    private widget.ComboBox TipeRujukan;
    private javax.swing.JDialog WindowMenu;
    private javax.swing.JDialog WindowRujukan;
    private widget.ButtonBig btnAdmin5;
    private widget.ButtonBig btnAdmin6;
    private widget.ButtonBig btnAdmin7;
    private widget.ButtonBig btnAdmin9;
    private javax.swing.JButton btnAngka0;
    private javax.swing.JButton btnAngka1;
    private javax.swing.JButton btnAngka2;
    private javax.swing.JButton btnAngka3;
    private javax.swing.JButton btnAngka4;
    private javax.swing.JButton btnAngka5;
    private javax.swing.JButton btnAngka6;
    private javax.swing.JButton btnAngka7;
    private javax.swing.JButton btnAngka8;
    private javax.swing.JButton btnAngka9;
    private javax.swing.JButton btnAngkaHps;
    private javax.swing.JButton btnClear;
    private widget.Button btnDiagnosa1;
    private widget.Button btnPPKRujukan1;
    private widget.Button btnPoli1;
    private widget.InternalFrame internalFrame8;
    private widget.Label jLabel12;
    private component.Label jLabel28;
    private widget.Label jLabel40;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel50;
    private component.Panel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private widget.Label lblNoSEP;
    // End of variables declaration//GEN-END:variables

    public void setPasien(String norm, String kodepoli, String kddokter) {
    }

    private void UpdateUmur() {

    }

    private void isNumber() {
    }
}
