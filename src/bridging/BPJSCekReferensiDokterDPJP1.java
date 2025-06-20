/*
  Dilarang keras menggandakan/mengcopy/menyebarkan/membajak/mendecompile
  Software ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
 */
package bridging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

/**
 *
 * @author dosen
 */
public final class BPJSCekReferensiDokterDPJP1 extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;
    private validasi Valid = new validasi();
    private sekuel Sequel = new sekuel();
    private int i = 0;
    private String URL = "", link = "", utc = "";
    private ApiBPJS api = new ApiBPJS();
    private BPJSCekReferensiPoli spesialis = new BPJSCekReferensiPoli(null, false);
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode nameNode;
    private JsonNode response;

    /**
     * Creates new form DlgKamar
     *
     * @param parent
     * @param modal
     */
    public BPJSCekReferensiDokterDPJP1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(10, 2);
        setSize(628, 674);

        tabMode = new DefaultTableModel(null, new String[]{"No.", "Kode Dokter", "Nama Dokter"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbKamar.setModel(tabMode);

        //tbKamar.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbKamar.getBackground()));
        tbKamar.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbKamar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 3; i++) {
            TableColumn column = tbKamar.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(40);
            } else if (i == 1) {
                column.setPreferredWidth(140);
            } else if (i == 2) {
                column.setPreferredWidth(470);
            }
        }
        tbKamar.setDefaultRenderer(Object.class, new WarnaTable());

        Dokter.setDocument(new batasInput((byte) 100).getKata(Dokter));

        if (koneksiDB.CARICEPAT().equals("aktif")) {
            Dokter.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (Dokter.getText().length() > 2) {
                        tampil(Dokter.getText());
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (Dokter.getText().length() > 2) {
                        tampil(Dokter.getText());
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (Dokter.getText().length() > 2) {
                        tampil(Dokter.getText());
                    }
                }
            });
        }

        spesialis.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (spesialis.getTable().getSelectedRow() != -1) {
                    KdSep.setText(spesialis.getTable().getValueAt(spesialis.getTable().getSelectedRow(), 1).toString());
                    NmSep.setText(spesialis.getTable().getValueAt(spesialis.getTable().getSelectedRow(), 2).toString());
                    KdSep.requestFocus();
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

        spesialis.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    spesialis.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        try {
            link = koneksiDB.URLAPIBPJS();
        } catch (Exception e) {
            System.out.println("E : " + e);
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

        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbKamar = new widget.Table();
        panelGlass6 = new widget.panelisi();
        jLabel15 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel14 = new widget.Label();
        KdSep = new widget.TextBox();
        NmSep = new widget.TextBox();
        BtnPropinsi = new widget.Button();
        jLabel16 = new widget.Label();
        Dokter = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel17 = new widget.Label();
        BtnKeluar = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        setIconImages(null);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Pencarian Data Referensi Dokter DPJP VClaim ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ScrollMouseClicked(evt);
            }
        });

        tbKamar.setName("tbKamar"); // NOI18N
        tbKamar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKamarMouseClicked(evt);
            }
        });
        Scroll.setViewportView(tbKamar);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass6.setName("panelGlass6"); // NOI18N
        panelGlass6.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel15.setText("Tgl Pel");
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass6.add(jLabel15);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-05-2023" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(125, 23));
        DTPCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari1KeyPressed(evt);
            }
        });
        panelGlass6.add(DTPCari1);

        jLabel14.setText("POLI");
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass6.add(jLabel14);

        KdSep.setEditable(false);
        KdSep.setHighlighter(null);
        KdSep.setName("KdSep"); // NOI18N
        KdSep.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass6.add(KdSep);

        NmSep.setEditable(false);
        NmSep.setName("NmSep"); // NOI18N
        NmSep.setPreferredSize(new java.awt.Dimension(200, 23));
        panelGlass6.add(NmSep);

        BtnPropinsi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPropinsi.setMnemonic('3');
        BtnPropinsi.setToolTipText("ALt+3");
        BtnPropinsi.setName("BtnPropinsi"); // NOI18N
        BtnPropinsi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPropinsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPropinsiActionPerformed(evt);
            }
        });
        panelGlass6.add(BtnPropinsi);

        jLabel16.setText("Dokter :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass6.add(jLabel16);

        Dokter.setName("Dokter"); // NOI18N
        Dokter.setPreferredSize(new java.awt.Dimension(200, 23));
        Dokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DokterKeyPressed(evt);
            }
        });
        panelGlass6.add(Dokter);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('6');
        BtnCari.setToolTipText("Alt+6");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelGlass6.add(BtnCari);

        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass6.add(jLabel17);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass6.add(BtnKeluar);

        internalFrame1.add(panelGlass6, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {

        }
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void DokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DokterKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);

        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbKamar.requestFocus();
        }
    }//GEN-LAST:event_DokterKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        if (KdSep.getText().trim().equals("") || NmSep.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih spesialis dulu..!!");
            BtnPropinsi.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            tampil(Dokter.getText());
            tampil2(Dokter.getText());
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {

        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void DTPCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPCari1KeyPressed

    private void BtnPropinsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPropinsiActionPerformed
        spesialis.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        spesialis.setLocationRelativeTo(internalFrame1);
        spesialis.setVisible(true);
    }//GEN-LAST:event_BtnPropinsiActionPerformed

    private void ScrollMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ScrollMouseClicked

    }//GEN-LAST:event_ScrollMouseClicked

    private void tbKamarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKamarMouseClicked
        if (evt.getClickCount() == 1) {
            dispose();
        }
    }//GEN-LAST:event_tbKamarMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            BPJSCekReferensiDokterDPJP1 dialog = new BPJSCekReferensiDokterDPJP1(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Button BtnPropinsi;
    private widget.Tanggal DTPCari1;
    private widget.TextBox Dokter;
    private widget.TextBox KdSep;
    private widget.TextBox NmSep;
    private widget.ScrollPane Scroll;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.panelisi panelGlass6;
    private widget.Table tbKamar;
    // End of variables declaration//GEN-END:variables

    public void tampil(String poli) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        try {
            Valid.tabelKosong(tabMode);
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("X-Cons-ID", koneksiDB.CONSIDAPIBPJS());
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("X-Timestamp", utc);
            headers.add("X-Signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIBPJS());
            requestEntity = new HttpEntity(headers);
            URL = link + "/referensi/dokter/pelayanan/1/tglPelayanan/" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "/Spesialis/" + KdSep.getText();
            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            nameNode = root.path("metaData");
            System.out.println("code : " + nameNode.path("code").asText());
            System.out.println("message : " + nameNode.path("message").asText());
            if (nameNode.path("code").asText().equals("200")) {
//                tabMode.addRow(new Object[]{
//                    "A", "Rawat Inap", ""
//                });
                response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
                //response = root.path("response");
                if (response.path("list").isArray()) {
                    i = 1;
                    for (JsonNode list : response.path("list")) {
                        if (list.path("kode").asText().toLowerCase().contains(poli.toLowerCase())
                                || list.path("nama").asText().toLowerCase().contains(poli.toLowerCase())) {
//                            tabMode.addRow(new Object[]{
//                                i + ".", list.path("kode").asText(), list.path("nama").asText()
//                            });
                        }
                        i++;
                    }
                }
            } else {
                if (!nameNode.path("code").asText().equals("201")) {
                    JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
                }
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

    public void tampil2(String poli) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            URL = link + "/referensi/dokter/pelayanan/2/tglPelayanan/" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "/Spesialis/" + KdSep.getText();
            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            nameNode = root.path("metaData");
            System.out.println("code : " + nameNode.path("code").asText());
            System.out.println("message : " + nameNode.path("message").asText());
            if (nameNode.path("code").asText().equals("200")) {
                tabMode.addRow(new Object[]{
                    "", "", ""
                });
                tabMode.addRow(new Object[]{
                    "B", "Rawat Jalan", ""
                });
                response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
                //response = root.path("response");
                if (response.path("list").isArray()) {
                    i = 1;
                    for (JsonNode list : response.path("list")) {
                        if (list.path("kode").asText().toLowerCase().contains(poli.toLowerCase())
                                || list.path("nama").asText().toLowerCase().contains(poli.toLowerCase())) {
                            tabMode.addRow(new Object[]{
                                i + ".", list.path("kode").asText(), list.path("nama").asText()
                            });
                        }
                        i++;
                    }
                }
            } else {
                if (!nameNode.path("code").asText().equals("201")) {
                    JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
                }
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

    public void setPoli(String KodePoli, String NamaPoli) {
        KdSep.setText(KodePoli);
        NmSep.setText(NamaPoli);
    }

    public JTable getTable() {
        return tbKamar;
    }

    public void carinamadokter(String kodepoli, String namapoli) {
        KdSep.setText(kodepoli);
        NmSep.setText(namapoli);
        BtnCariActionPerformed(null);
        Dokter.requestFocus();
    }
}
