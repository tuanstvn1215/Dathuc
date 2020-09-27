package dsDathuc;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class History {

    private String dathuc1;
    private String dathuc2;
    private String ketqua;
    private int loai;
    private String thoigian;

    public History() {
        dathuc1 = new String();
        dathuc2 = new String();
        ketqua = new String();
        loai = -1;
        thoigian = new String();
    }

    public History(String dathuc1, String dathuc2, String ketqua, int loai) {
        this.dathuc1 = dathuc1;
        if (loai == 4) {
            this.dathuc2 = new String();
        } else {
            this.dathuc2 = dathuc2;
        }
        this.ketqua = ketqua;
        this.loai = loai;
        Date date= new Date(System.currentTimeMillis());
        this.thoigian = new String(date.toLocaleString());
    }
    
    public String layDathuc1(){
        return this.dathuc1;
    }
        public String layDathuc2(){
        return this.dathuc2;
    }
    public String layKetqua(){
        return this.ketqua;
    }
    public String layThoigian(){
        return this.thoigian;
    }
    public int layLoai(){
        return this.loai;
    }
    public void luuvaoFile(String duongdan){
        try {
            FileOutputStream fos= new FileOutputStream(duongdan,true);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeBytes(dathuc1+"'"+dathuc2+"'"+ketqua+"'"+loai+"'"+thoigian+"\n");
            fos.close();
            dos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        History his = new History("1", "2", "3", 0);
    }

}
