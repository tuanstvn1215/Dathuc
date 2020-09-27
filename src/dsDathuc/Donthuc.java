package dsDathuc;

public class Donthuc {

    private int mu;
    private float so;

    public Donthuc() {
        this.so = 0;
        this.mu = 0;
    }
    
    public Donthuc(Donthuc donthuc) {
        this.mu = donthuc.layMu();
        this.so = donthuc.laySo();
    }

    public Donthuc(float so, int mu) {
        if (so == 0) {
            this.so = 0;
            this.mu = 0;
        } else {
            this.so = so;
            this.mu = mu;
        }

    }

    public Donthuc(String donthuc) {
        String[] pt = donthuc.split("x");
        if (donthuc.contains("x")) {
            if (donthuc.charAt(0) != 'x' && donthuc.charAt(donthuc.length() - 1) != 'x') {
                so = Float.parseFloat(pt[0]);
                mu = Integer.parseInt(pt[1]);

            }
            if (donthuc.charAt(0) == 'x' && donthuc.charAt(donthuc.length() - 1) == 'x') {
                so = 1;
                mu = 1;
            }

            if (donthuc.charAt(0) == 'x' && donthuc.charAt(donthuc.length() - 1) != 'x') {
                so = 1;
                mu = Integer.parseInt(pt[1]);
            }

            if (donthuc.charAt(0) != 'x' && donthuc.charAt(donthuc.length() - 1) == 'x') {
                if(donthuc.charAt(0) == '-'){
                  so = -1;
                }else{
                  so = Float.parseFloat(pt[0]);
                  
                }
                mu = 1;
            }

        } else {
            if (pt[0].equals("")) {
                so = 0;
            } else {
                so = Float.parseFloat(pt[0]);
            }
            mu = 0;
        }
    }

    // cộng với đơn thức(donthuc) cùng số mũ -trả về 1 đơn thức
    public Donthuc congDonthuc(Donthuc donthuc) {
        if (this.mu == donthuc.layMu()) {
            int mu = this.mu;
            float so = this.so + donthuc.laySo();
            Donthuc ketqua = new Donthuc(so, mu);
            return ketqua;
        } else {
            throw new Error("chỉ cộng được với đơn thức cùng số mũ");
        }
    }

    // trừ cho đơn thuc(donthuc) cùng số mũ - trả về 1 đơn thức
    public Donthuc truDonthuc(Donthuc donthuc) {
        if (this.mu == donthuc.layMu()) {
        int mu = new Integer(this.mu);
            float so = this.so - donthuc.laySo();
            Donthuc ketqua = new Donthuc(so, mu);
            return ketqua;
        } else {
            throw new Error("chỉ trừ được cho đơn thức cùng số mũ");
        }
    }

    public Donthuc daohamDonthuc() {
        float so = new Float(this.so);
        int mu = new Integer(this.mu);
        if (this.mu == 0) {
            so = 0;
            mu = 0;
        } else {
            mu = this.mu - 1;
            so = so * this.mu;
        }
        Donthuc ketqua = new Donthuc(so, mu);
        return ketqua;
    }
    
    public Donthuc nhanDonthuc(Donthuc donthuc) {
        float so = new Float(this.so);
        int mu = new Integer(this.mu);
        mu = mu + donthuc.layMu();
        so = so * donthuc.laySo();
        Donthuc ketqua = new Donthuc(so, mu);
        return ketqua;
    }

    public Donthuc chiaDonthuc(Donthuc donthuc) {
        float so = new Float(this.so);
        int mu = new Integer(this.mu);
        Donthuc ketqua = new Donthuc();
        if (donthuc.layMu() <= mu) {
            mu = mu - donthuc.layMu();
            so = so / donthuc.laySo();
            ketqua = new Donthuc(so, mu);

        }

        return ketqua;
    }

    public int layMu() {
        return this.mu;
    }

    public float laySo() {
        return this.so;
    }

    // cộng thêm số cho đơn thức
    public Donthuc congDonthuc(float so) {
        Donthuc donthuc = new Donthuc(this.so + so, this.mu);
        return donthuc;
    }
    public Donthuc tinhDonthuc(float x){
        float so = new Float(this.so);
        int mu=0;
        
        so=(float)Math.pow(x, this.mu)*this.so;
        return new Donthuc(so,mu);       
    }
    @Override
    public String toString() {
        String str, so, mu;
        so = new String();
        mu = Integer.toString(this.mu);
        if (this.so != 1) {
            so = Float.toString(this.so);
        }
        str = so + "x^" + mu;
        if (this.mu == 0) {
            if (this.so == 1) {
                so = "1";
            }
            str = so;
        }
        if (this.mu == 1) {
            if (this.so == 1) {
                so = "";
            }
            str = so + "x";
        }
        return str;
    }

    public void printf() {

        System.out.print(this.so + "x^" + this.mu);
    }

    public static void main(String[] args) {
        Donthuc dt = new Donthuc(1, 1);
        Donthuc dt2 = new Donthuc(1, 1);
        dt = dt.nhanDonthuc(dt2);
        System.out.println(dt.toString());
    }
}
