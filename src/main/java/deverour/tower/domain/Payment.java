package deverour.tower.domain;

public class Payment {
    private String id;
    private String shi;

    private String zhifudanhao;
    private String gongdianleixing;
    private String zhifujine;
    private String fukuanriqi;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShi() {
        return shi;
    }

    public void setShi(String shi) {
        this.shi = shi;
    }



    public String getZhifudanhao() {
        return zhifudanhao;
    }

    public void setZhifudanhao(String zhifudanhao) {
        this.zhifudanhao = zhifudanhao;
    }

    public String getGongdianleixing() {
        return gongdianleixing;
    }

    public void setGongdianleixing(String gongdianleixing) {
        this.gongdianleixing = gongdianleixing;
    }

    public String getZhifujine() {
        return zhifujine;
    }

    public void setZhifujine(String zhifujine) {
        this.zhifujine = zhifujine;
    }

    public String getFukuanriqi() {
        return fukuanriqi;
    }

    public void setFukuanriqi(String fukuanriqi) {
        this.fukuanriqi = fukuanriqi;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "shi='" + shi + '\'' +
                ", zhifudanhao='" + zhifudanhao + '\'' +
                ", gongdianleixing='" + gongdianleixing + '\'' +
                ", zhifujine='" + zhifujine + '\'' +
                ", fukuanriqi='" + fukuanriqi + '\'' +
                '}';
    }
}
