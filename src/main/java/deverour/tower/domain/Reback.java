package deverour.tower.domain;

import java.util.ArrayList;

public class Reback {
    private String id;
    private String fengongsi;
    private String quyu;
    private String zhangqi;
    private String yunyingshang;
    private String kaipiaobianhao;
    private String jiesuanjine;
    private String issaomiao;
    private String ishuikuan;
    private String huikuanriqi;
    private String shangchuanriqi;
    private String saomiaoname;
    private String iscpy;

    private String quyus;
    private String kehus;
    private String zhangtimes;
    private String huikuantimes;
    private String issaomiaos;
    private String ishuikuans;
    private String msg;
    private String marked;
    private String startDate;
    private String endDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFengongsi() {
        return fengongsi;
    }

    public void setFengongsi(String fengongsi) {
        this.fengongsi = fengongsi;
    }

    public String getQuyu() {
        return quyu;
    }

    public void setQuyu(String quyu) {
        this.quyu = quyu;
    }

    public String getZhangqi() {
        return zhangqi;
    }

    public void setZhangqi(String zhangqi) {
        this.zhangqi = zhangqi;
    }

    public String getYunyingshang() {
        return yunyingshang;
    }

    public void setYunyingshang(String yunyingshang) {
        this.yunyingshang = yunyingshang;
    }

    public String getKaipiaobianhao() {
        return kaipiaobianhao;
    }

    public void setKaipiaobianhao(String kaipiaobianhao) {
        this.kaipiaobianhao = kaipiaobianhao;
    }

    public String getJiesuanjine() {
        return jiesuanjine;
    }

    public void setJiesuanjine(String jiesuanjine) {
        this.jiesuanjine = jiesuanjine;
    }

    public String getIssaomiao() {
        return issaomiao;
    }

    public void setIssaomiao(String issaomiao) {
        this.issaomiao = issaomiao;
    }

    public String getIshuikuan() {
        return ishuikuan;
    }

    public void setIshuikuan(String ishuikuan) {
        this.ishuikuan = ishuikuan;
    }

    public String getHuikuanriqi() {
        return huikuanriqi;
    }

    public void setHuikuanriqi(String huikuanriqi) {
        this.huikuanriqi = huikuanriqi;
    }

    public String getShangchuanriqi() {
        return shangchuanriqi;
    }

    public void setShangchuanriqi(String shangchuanriqi) {
        this.shangchuanriqi = shangchuanriqi;
    }

    public String getSaomiaoname() {
        return saomiaoname;
    }

    public void setSaomiaoname(String saomiaoname) {
        this.saomiaoname = saomiaoname;
    }

    public String getIscpy() {
        return iscpy;
    }

    public void setIscpy(String iscpy) {
        this.iscpy = iscpy;
    }

    public String getQuyus() {
        return quyus;
    }

    public void setQuyus(String quyus) {
        this.quyus = quyus;
    }

    public String getKehus() {
        return kehus;
    }

    public void setKehus(String kehus) {
        this.kehus = kehus;
    }

    public String getZhangtimes() {
        return zhangtimes;
    }

    public void setZhangtimes(String zhangtimes) {
        this.zhangtimes = zhangtimes;
    }

    public String getHuikuantimes() {
        return huikuantimes;
    }

    public void setHuikuantimes(String huikuantimes) {
        this.huikuantimes = huikuantimes;
    }

    public String getIssaomiaos() {
        return issaomiaos;
    }

    public void setIssaomiaos(String issaomiaos) {
        this.issaomiaos = issaomiaos;
    }

    public String getIshuikuans() {
        return ishuikuans;
    }

    public void setIshuikuans(String ishuikuans) {
        this.ishuikuans = ishuikuans;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMarked() {
        return marked;
    }

    public void setMarked(String marked) {
        this.marked = marked;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Reback{" +
                "id='" + id + '\'' +
                ", fengongsi='" + fengongsi + '\'' +
                ", quyu='" + quyu + '\'' +
                ", zhangqi='" + zhangqi + '\'' +
                ", yunyingshang='" + yunyingshang + '\'' +
                ", kaipiaobianhao='" + kaipiaobianhao + '\'' +
                ", jiesuanjine='" + jiesuanjine + '\'' +
                ", issaomiao='" + issaomiao + '\'' +
                ", ishuikuan='" + ishuikuan + '\'' +
                ", huikuanriqi='" + huikuanriqi + '\'' +
                ", shangchuanriqi='" + shangchuanriqi + '\'' +
                ", saomiaoname='" + saomiaoname + '\'' +
                ", iscpy='" + iscpy + '\'' +
                ", quyus='" + quyus + '\'' +
                ", kehus='" + kehus + '\'' +
                ", zhangtimes='" + zhangtimes + '\'' +
                ", huikuantimes='" + huikuantimes + '\'' +
                ", issaomiaos='" + issaomiaos + '\'' +
                ", ishuikuans='" + ishuikuans + '\'' +
                ", msg='" + msg + '\'' +
                ", marked='" + marked + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }

    public static ArrayList<String> getNamelist() {
        ArrayList<String> namelist = new ArrayList<String>() ;
        namelist.add("分公司");
        namelist.add("区域");
        namelist.add("账期");
        namelist.add("运营商");
        namelist.add("回款编号");
        namelist.add("结算金额");
        namelist.add("是否上传扫描件");
        namelist.add("是否回款");
        namelist.add("回款日期");
        namelist.add("签认日期");
        namelist.add("是否包干");

        return namelist;
    }
}
