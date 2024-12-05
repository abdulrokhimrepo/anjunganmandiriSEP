/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.koneksiDB;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

/**
 *
 * @author khanzasoft
 */
public class BPJSCekJadwalDokter {

    public String cobnmAsuransi = "", cobnoAsuransi = "", cobtglTAT = "", cobtglTMT = "",
            hakKelasketerangan = "", hakKelaskode = "", informasidinsos = "", informasinoSKTM = "",
            informasiprolanisPRB = "", jenisPesertaketerangan = "", jenisPesertakode = "",
            mrnoMR = "", mrnoTelepon = "", nama = "", nik = "", noKartu = "", pisa = "",
            provUmumkdProvider = "", provUmumnmProvider = "", sex = "", statusPesertaketerangan = "",
            statusPesertakode = "", tglCetakKartu = "", tglLahir = "", tglTAT = "",
            tglTMT = "", umurumurSaatPelayanan = "", umurumurSekarang = "", informasi = "", utc = "", kodesubspesialis = "", hari = "", kapasitas = "", namahari = "", jampraktek = "", kodepoli = "", kodedokter = "";
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    private ApiBPJS api = new ApiBPJS();
    private String URL = "";
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode nameNode;
    private JsonNode response;
    private HttpHeaders headers;

    public BPJSCekJadwalDokter() {
        super();
        try {
            URL = koneksiDB.URLAPIBPJS() + "/jadwaldokter/kodepoli/";
        } catch (Exception e) {
            System.out.println("E : " + e);
        }
    }

    public void tampil(String kodepoli, String tanggalperiksa, String kodedokter) {

        jampraktek = "";
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("x-cons-id", koneksiDB.CONSIDAPIMOBILEJKN());
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("x-timestamp", utc);
            headers.add("x-signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIMOBILEJKN());
            requestEntity = new HttpEntity(headers);
            URL = koneksiDB.URLAPIMOBILEJKN() + "/jadwaldokter/kodepoli/" + kodepoli + "/tanggal/" + tanggalperiksa + "";
            System.out.println(URL);
            root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            nameNode = root.path("metadata");
//            System.out.println(requestEntity);

            if (nameNode.path("code").asText().equals("200")) {
                response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
                //response = root.path("response");
                if (response.isArray()) {
                    for (JsonNode list : response) {
                        if (list.path("kodedokter").asText().equals(kodedokter) && list.path("kodepoli").asText().equals(kodepoli)) {
                            jampraktek = list.path("jadwal").asText();
                            namahari = list.path("namahari").asText();
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi Peserta : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
            }
        }
    }

}
