/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fungsi;

import AESsecurity.EnkripsiAES;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author khanzasoft
 */
public class koneksiDB {

    private static Connection connection = null;
    private static final Properties prop = new Properties();
    private static final MysqlDataSource dataSource = new MysqlDataSource();
    private static String var = "";

    public koneksiDB() {
    }

    public static Connection condb() {
        if (connection == null) {
            try {
                prop.loadFromXML(new FileInputStream("setting/database.xml"));
                dataSource.setURL("jdbc:mysql://" + EnkripsiAES.decrypt(prop.getProperty("HOST")) + ":" + EnkripsiAES.decrypt(prop.getProperty("PORT")) + "/" + EnkripsiAES.decrypt(prop.getProperty("DATABASE")) + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true&useCompression=true");
                dataSource.setUser(EnkripsiAES.decrypt(prop.getProperty("USER")));
                dataSource.setPassword(EnkripsiAES.decrypt(prop.getProperty("PAS")));
                connection = dataSource.getConnection();
                String databasestatus = "";
                if (EnkripsiAES.decrypt(prop.getProperty("HOST")).equals("192.168.15.111")) {
                    databasestatus = " DATABASE PRODUCTION";
                } else if (EnkripsiAES.decrypt(prop.getProperty("HOST")).equals("192.168.15.115")) {
                    databasestatus = " DATABASE BACKUP";
                } else {
                    databasestatus = " DATABASE DUMMY";
                }
                System.out.println("  Berhasil terhubung dengan basis data Indriati Boyolali : " + databasestatus + "\n\n"
                        + " +-+-+ +-+-+-+-+-+-+-+-+ +-+-+-+-+-+-+-+-+-+ +-+-+-+-+-+-+ +-+-+\n"
                        + "           |R|S| |I|N|D|R|I|A|T|I| |B|O|Y|O|L|A|L|I|            \n"
                        + " +-+-+ +-+-+-+-+-+-+-+-+ +-+-+-+-+-+-+-+-+-+ +-+-+-+-+-+-+-+-+-+\n"
                        + " |W|E| |W|O|R|K| |F|O|R| |H|E|A|L|T|H|I|E|R| |I|N|D|O|N|E|S|I|A|\n"
                        + " +-+-+ +-+-+-+-+ +-+-+-+ +-+-+-+-+-+-+-+-+-+ +-+-+-+-+-+-+-+-+-+\n\n"
                        + "  Licensi yang dianut di software ini dapat dilihat di : \n"
                        + "  https://en.wikipedia.org/wiki/Aladdin_Free_Public_License \n\n"
                        + "  Informasi dan panduan bisa dicek pada menu EBOOK \n"
                        + "  SIMRS ini diadopsi dari SIMRS Khanza, disesuaikan sesuai kebutuhan.\n"
                        + "  Ganti password anda secara berkala !\n"
                        + "                                                                           ");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Koneksi Putus : " + e);
            }
        }
        return connection;
    }

    public static String HOST() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("HOST"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String DATABASE() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("DATABASE"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String PORT() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("PORT"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String USER() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("USER"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String CARICEPAT() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("CARICEPAT");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String HOSTHYBRIDWEB() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("HOSTHYBRIDWEB"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String HYBRIDWEB() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("HYBRIDWEB");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String PORTWEB() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("PORTWEB");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String ANTRIAN() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("ANTRIAN");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String ALARMAPOTEK() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("ALARMAPOTEK");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String FORMALARMAPOTEK() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("FORMALARMAPOTEK");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String ALARMLAB() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("ALARMLAB");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String FORMALARMLAB() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("FORMALARMLAB");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String ALARMRADIOLOGI() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("ALARMRADIOLOGI");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String FORMALARMRADIOLOGI() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("FORMALARMRADIOLOGI");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String ALARMRSISRUTE() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("ALARMRSISRUTE");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String MENUTRANSPARAN() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("MENUTRANSPARAN");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String URLAPIBPJS() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("URLAPIBPJS");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String URLFINGERPRINTBPJS() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("URLFINGERPRINTBPJS");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String URLAPLIKASIFINGERPRINTBPJS() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("URLAPLIKASIFINGERPRINTBPJS");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String URLAPLIKASIFRISTA() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("URLAPLIKASIFRISTA");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String URLAPLIKASIFINGERPRINTBPJSV2() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("URLAPLIKASIFINGERPRINTBPJSV2");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String USERFINGERPRINTBPJS() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("USERFINGERPRINTBPJS"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String PASSWORDFINGERPRINTBPJS() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("PASSWORDFINGERPRINTBPJS"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String URLAPIMOBILEJKN() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("URLAPIMOBILEJKN");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String SECRETKEYAPIBPJS() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("SECRETKEYAPIBPJS"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String USERKEYAPIBPJS() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("USERKEYAPIBPJS"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String CONSIDAPIBPJS() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("CONSIDAPIBPJS"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String SECRETKEYAPIMOBILEJKN() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("SECRETKEYAPIMOBILEJKN"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String USERKEYAPIMOBILEJKN() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("SECRETKEYAPIMOBILEJKN"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String CONSIDAPIMOBILEJKN() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("CONSIDAPIMOBILEJKN"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String URLAPIAPLICARE() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("URLAPIAPLICARE");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String SECRETKEYAPIAPLICARE() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("SECRETKEYAPIAPLICARE"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String CONSIDAPIAPLICARE() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("CONSIDAPIAPLICARE"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String URLAPIPCARE() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("URLAPIPCARE");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String SECRETKEYAPIPCARE() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("SECRETKEYAPIPCARE"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String CONSIDAPIPCARE() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("CONSIDAPIPCARE"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String PASSPCARE() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("PASSPCARE"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String USERPCARE() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("USERPCARE"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String DIVREGPCARE() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("DIVREGPCARE");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String KACABPCARE() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("KACABPCARE");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String URLAPISISRUTE() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("URLAPISISRUTE");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String IDSISRUTE() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("IDSISRUTE"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String PASSSISRUTE() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("PASSSISRUTE"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String URLAPISIRS() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("URLAPISIRS");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String IDSIRS() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("IDSIRS"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String PASSSIRS() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("PASSSIRS"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String URLAPISITT() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("URLAPISITT");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String IDSITT() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("IDSITT"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String PASSSITT() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("PASSSITT"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String KABUPATENSITT() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("KABUPATENSITT");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String KAMARAKTIFRANAP() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("KAMARAKTIFRANAP");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String DOKTERAKTIFKASIRRALAN() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("DOKTERAKTIFKASIRRALAN");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String POLIAKTIFKASIRRALAN() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("POLIAKTIFKASIRRALAN");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String RUANGANAKTIFINVENTARIS() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("RUANGANAKTIFINVENTARIS");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String BASENOREG() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("BASENOREG");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String URUTNOREG() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("URUTNOREG");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String JADWALDOKTERDIREGISTRASI() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("JADWALDOKTERDIREGISTRASI");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String IPPRINTERTRACER() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("IPPRINTERTRACER");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String AKTIFKANPRINTBARCODEOTOMATIS() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("AKTIFKANPRINTBARCODEOTOMATIS");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String URLAPIINHEALTH() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("URLAPIINHEALTH");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String TOKENINHEALTH() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("TOKENINHEALTH"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String PEMBULATANHARGAOBAT() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("PEMBULATANHARGAOBAT");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String AKTIFKANBATCHOBAT() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("AKTIFKANBATCHOBAT");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String CETAKRINCIANOBAT() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("CETAKRINCIANOBAT");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String AKTIFKANBILLINGPARSIAL() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("AKTIFKANBILLINGPARSIAL");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String URLDUKCAPILJAKARTA() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("URLDUKCAPILJAKARTA");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String USERDUKCAPILJAKARTA() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("USERDUKCAPILJAKARTA"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String PASSDUKCAPILJAKARTA() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("PASSDUKCAPILJAKARTA"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String VAR1DUKCAPILJAKARTA() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("VAR1DUKCAPILJAKARTA");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String VAR2DUKCAPILJAKARTA() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("VAR2DUKCAPILJAKARTA");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String URLDUKCAPIL() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("URLDUKCAPIL");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String USERDUKCAPIL() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("USERDUKCAPIL"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String PASSDUKCAPIL() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("PASSDUKCAPIL"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String IPUSERDUKCAPIL() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("IPUSERDUKCAPIL");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String AKTIFKANTRACKSQL() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("AKTIFKANTRACKSQL");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String HOSTWSLICA() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("HOSTWSLICA");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String KEYWSLICA() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = EnkripsiAES.decrypt(prop.getProperty("KEYWSLICA"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String AKTIFKANREALTIMEKIRIM() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("AKTIFKANREALTIMEKIRIM");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String AKTIFKANWAJIBANTROL() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("AKTIFKANWAJIBANTROL");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String URLWEBSOCKET() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("URLWEBSOCKET");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String ADDANTROLMOBILEJKN() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("ADDANTROLMOBILEJKN");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

}
