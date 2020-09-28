package dsDathuc;

public class Dathuc {

    private Donthuc[] dsDonthuc;
    private int length;

    public Dathuc() {
        length = 0;
        dsDonthuc = new Donthuc[0];
    }

    public Dathuc(Dathuc dathuc) {
        this.dsDonthuc = new Donthuc[dathuc.length];
        for (int i = 0; i < dathuc.length; i++) {
            this.dsDonthuc[i] = new Donthuc(dathuc.layDonthuc(i));
            length = dathuc.length;
        }
    }

    public Dathuc(Donthuc[] ds) {
        this.dsDonthuc = ds;
    }

    public Dathuc(String string) {
        Donthuc donthuc;
        Dathuc dathuc=new Dathuc();
        string = string.toLowerCase();
        string = string.replace(" ", "");
        string = string.replace("x^0", "");
        string = string.replace("--", "+");
        string = string.replace("++", "+");
        string = string.replace("+-", "-");
        string = string.replace("+", ",");
        string = string.replace("-", ",-");
        string = string.replace("x^", "x");
        String[] arrStr = string.split(",");
        for (String arrStr1 : arrStr) {
            donthuc = new Donthuc(arrStr1);
            dathuc.themDonthuc(donthuc);
        }
        dathuc = dathuc.thugonDathuc();
        this.dsDonthuc = new Donthuc[dathuc.length];
        for (int i = 0; i < dathuc.length; i++) {
            this.dsDonthuc[i] = new Donthuc(dathuc.layDonthuc(i));
            length = dathuc.length;
        }

    }
    
    public void themDonthuc(Donthuc donthuc) {

        Donthuc[] dsDonthuc;
        dsDonthuc = new Donthuc[this.length + 1];
        for (int i = 0; i < this.length; i++) {
            dsDonthuc[i] = this.dsDonthuc[i];
        }
        dsDonthuc[this.length] = new Donthuc(donthuc.laySo(), donthuc.layMu());
        this.dsDonthuc = new Donthuc[this.length + 1];
        this.dsDonthuc = dsDonthuc;
        this.length++;
    }

    // lấy ra toàn bộ đơn thức
    public Donthuc[] laydsDonthuc() {
        return dsDonthuc;
    }

    // lấy ra 1 đơn thức
    public Donthuc layDonthuc(int i) {
        return dsDonthuc[i];
    }

    ;

   // sửa 1 đơn thức tại vị trí i
   public void suaDonthuc(int i/* vị trí */, float so, int mu) {
        this.dsDonthuc[i] = new Donthuc(so, mu);
    }

    public void suaDonthuc(int i/* vị trí */, Donthuc donthuc) {
        this.dsDonthuc[i] = donthuc;
    }

    // trả về độ dài của đa thức
    public int layDodai() {
        return this.length;
    }

    // trả về 1 da thức đã sắp xếp theo số mũ tăng dần
    public Dathuc sapxepDathuc() {
        Dathuc dathuc = new Dathuc(this);
        for (int i = 0; i < this.length - 1; i++) {
            for (int j = i; j < this.length; j++) {
                if (dathuc.layDonthuc(j).layMu() < dathuc.layDonthuc(i).layMu()) {
                    dathuc.doichoDonthuc(j, i);
                }
            }
        }
        return dathuc;
    }

    public void doichoDonthuc(int i, int j/* vị trí 2 đơn thức cần đổi chỗ */) {
        Donthuc donthuc;
        donthuc = this.dsDonthuc[i];
        this.dsDonthuc[i] = this.dsDonthuc[j];
        this.dsDonthuc[j] = donthuc;
    }

    // Trả về 1 đa thức đã thu gọn
    public Dathuc thugonDathuc() {
        Dathuc dathuc1 = new Dathuc(this);
        // sắp xếp lại
        dathuc1 = dathuc1.sapxepDathuc();
        // xử lí thu gọn
        Dathuc dathuc = new Dathuc();
        Donthuc donthuc = new Donthuc();
        int i;
        // vì ô đầu tiên là rỗng nên thêm 1 đơn thức vào
        dathuc.themDonthuc(donthuc);
        for (i = 0; i < dathuc1.layDodai(); i++) {
            if (dathuc1.layDonthuc(i).layMu() > dathuc.layDonthuc(dathuc.layDodai() - 1).layMu()) {
                donthuc = dathuc1.layDonthuc(i);
                dathuc.themDonthuc(donthuc);
            } else {
                donthuc = dathuc.layDonthuc(dathuc.layDodai() - 1).congDonthuc(dathuc1.layDonthuc(i).laySo());
                dathuc.suaDonthuc(dathuc.layDodai() - 1, donthuc);
            }
        }
        for (i = dathuc.layDodai() - 1; i >= 0; i--) {
            if (dathuc.layDonthuc(i).laySo() == 0) {
                dathuc = dathuc.xoaDonthuc(i);
            }
        }
        return dathuc;
    }
//xoá 1 đơn thức tại vị trí thứ i

    public Dathuc xoaDonthuc(int i) {
        Dathuc ketqua = new Dathuc();
        for (int j = 0; j < this.length; j++) {
            if (j == i) {
                continue;
            }
            ketqua.themDonthuc(this.layDonthuc(j));
        }

        return ketqua;
    }

    public Dathuc nhanDonthuc(Donthuc donthuc) {
        Dathuc ketqua = new Dathuc();
        for (int j = 0; j < this.length; j++) {
            ketqua.themDonthuc(this.layDonthuc(j).nhanDonthuc(donthuc));
        }
        ketqua = ketqua.thugonDathuc();
        return ketqua;
    }
//trừ cho đa thức dathuc2 trả về đa thức kết quả

    public Dathuc truDathuc(Dathuc dathuc2) {
        float so;
        int mu;
        Dathuc ketqua = new Dathuc(this);
        dathuc2 = dathuc2.thugonDathuc();
        Donthuc donthuc;
        int j;

        for (j = 0; j < dathuc2.layDodai(); j++) {
            so = -dathuc2.layDonthuc(j).laySo();
            mu = dathuc2.layDonthuc(j).layMu();
            donthuc = new Donthuc(so, mu);
            ketqua.themDonthuc(donthuc);
        }
        ketqua = ketqua.thugonDathuc();

        return ketqua;
    }

    //nhân cho đa thức dathuc2 trả về đa thức kết quả
    public Dathuc nhandDathuc(Dathuc dathuc2) {
        Dathuc ketqua = new Dathuc();
        Donthuc donthuc;
        for (int i = 0; i < this.layDodai(); i++) {
            for (int j = 0; j < dathuc2.layDodai(); j++) {
                donthuc = this.layDonthuc(i).nhanDonthuc(dathuc2.layDonthuc(j));
                ketqua.themDonthuc(donthuc);
            }
        }
        ketqua = ketqua.thugonDathuc();
        return ketqua;
    }

    public Dathuc[] chiaDathuc(Dathuc dathucChia) {
        boolean i = true;
        Dathuc[] ketqua = new Dathuc[2];
        Dathuc dathucdu = new Dathuc();
        Donthuc dtketqua = new Donthuc();
        ketqua[0] = new Dathuc();
        ketqua[1] = this;

        Dathuc dathucBichia = new Dathuc(this);
        dathucChia = dathucChia.thugonDathuc();
        dathucBichia = dathucBichia.thugonDathuc();
        if(dathucChia.layDodai()==0){
            throw new Error("không thể chia đa thức cho 0");
        }
        while (true) {
            if (dathucBichia.layDodai() == 0) {
                ketqua[1] = dathucBichia;
                break;
            } else if (dathucChia.layDonthuc(dathucChia.layDodai() - 1).layMu() > dathucBichia.layDonthuc(dathucBichia.layDodai() - 1).layMu()) {
                ketqua[1] = dathucBichia;
                break;
            }
            dtketqua = dathucBichia.layDonthuc(dathucBichia.layDodai() - 1).chiaDonthuc(dathucChia.layDonthuc(dathucChia.layDodai() - 1));
            dathucBichia = dathucBichia.truDathuc(dathucChia.nhanDonthuc(dtketqua));
            ketqua[0].themDonthuc(dtketqua);

        }
        return ketqua;
    }

    // Cộng với đa thức(dathuc ) trả về 1 đa thức đã cộng
    public Dathuc CongDathuc(Dathuc dathuc2) {
        Dathuc ketqua = new Dathuc(this);
        dathuc2 = dathuc2.thugonDathuc();
        Donthuc donthuc;
        int j;

        for (j = 0; j < dathuc2.layDodai(); j++) {
            donthuc = new Donthuc(dathuc2.layDonthuc(j));
            ketqua.themDonthuc(donthuc);
        }
        ketqua = ketqua.thugonDathuc();
        return ketqua;
    }

    public Dathuc daohamDathuc(int bac) {
        Dathuc ketqua = new Dathuc(this);
        for (int i = 1; i <= bac; i++) {
            ketqua = ketqua.daohamDathuc();
        }
        return ketqua;
    }

    public Dathuc daohamDathuc() { //đạo hàm đa thức trả về 1 đa thức
        Dathuc ketqua = new Dathuc();
        for (int i = 0; i < this.layDodai(); i++) {
            ketqua.themDonthuc(this.layDonthuc(i).daohamDonthuc());
        }
        for (int i = 0; i < ketqua.layDodai(); i++) {
            ketqua = ketqua.thugonDathuc();
        }
        return ketqua;
    }
    public Dathuc Tinhgiatri(float x){
        float so;
        int mu;
        Donthuc donthuc;
        Dathuc ketqua=new Dathuc();
        for(int i=0;i<=this.layDodai()-1;i++){
            donthuc=this.layDonthuc(i).tinhDonthuc(x);
            System.out.println(donthuc.toString());
            ketqua.themDonthuc(donthuc);
        }
        ketqua=ketqua.thugonDathuc();
        return ketqua;
    }
    // chuyển đa thức thành chuỗi
    @Override
    public String toString() {
        String ketqua = new String();
        for (int i = 0; i < this.length; i++) {
            ketqua += this.dsDonthuc[i] + " + ";
        }
        if (ketqua.equals("")) {
            return "0";
        }ketqua=ketqua.replace("+ -", "-");
        ketqua = ketqua.substring(0, ketqua.length() - 2);
        ketqua = ketqua.trim();

        return ketqua;
    }

    public static void main(String[] args) throws Exception {

        Dathuc dathuc = new Dathuc("x");
        Dathuc dt = new Dathuc("2");
        Dathuc dt2 = dathuc.CongDathuc(dt);
        dathuc=dathuc.Tinhgiatri(1);
        String asdd= dathuc.toString();
        System.out.println(asdd);
    }
}
