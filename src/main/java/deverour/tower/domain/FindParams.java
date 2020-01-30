package deverour.tower.domain;

import java.util.Arrays;

public class FindParams {
    private String[] fengongsi;
    private String[] quyu;
    private String[] yunyingshang;
    private String zhifudanhao;
    private String zhangqi;

    public String[] getFengongsi() {
        return fengongsi;
    }

    public void setFengongsi(String[] fengongsi) {
        this.fengongsi = fengongsi;
    }

    public String[] getQuyu() {
        return quyu;
    }

    public void setQuyu(String[] quyu) {
        this.quyu = quyu;
    }

    public String[] getYunyingshang() {
        return yunyingshang;
    }

    public void setYunyingshang(String[] yunyingshang) {
        this.yunyingshang = yunyingshang;
    }

    public String getZhifudanhao() {
        return zhifudanhao;
    }

    public void setZhifudanhao(String zhifudanhao) {
        this.zhifudanhao = zhifudanhao;
    }

    public String getZhangqi() {
        return zhangqi;
    }

    public void setZhangqi(String zhangqi) {
        this.zhangqi = zhangqi;
    }

    @Override
    public String toString() {
        return "FindParams{" +
                "fengongsi=" + Arrays.toString(fengongsi) +
                ", quyu=" + Arrays.toString(quyu) +
                ", yunyingshang=" + Arrays.toString(yunyingshang) +
                ", zhifudanhao='" + zhifudanhao + '\'' +
                ", zhangqi='" + zhangqi + '\'' +
                '}';
    }
}
