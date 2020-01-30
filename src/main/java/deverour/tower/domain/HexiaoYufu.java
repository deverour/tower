package deverour.tower.domain;

public class HexiaoYufu {
    private String id;
    private String hexiaodanhao;
    private String hexiaobaozhang;
    private String yufudanhao;
    private String yufubaozhang;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHexiaodanhao() {
        return hexiaodanhao;
    }

    public void setHexiaodanhao(String hexiaodanhao) {
        this.hexiaodanhao = hexiaodanhao;
    }

    public String getHexiaobaozhang() {
        return hexiaobaozhang;
    }

    public void setHexiaobaozhang(String hexiaobaozhang) {
        this.hexiaobaozhang = hexiaobaozhang;
    }

    public String getYufudanhao() {
        return yufudanhao;
    }

    public void setYufudanhao(String yufudanhao) {
        this.yufudanhao = yufudanhao;
    }

    public String getYufubaozhang() {
        return yufubaozhang;
    }

    public void setYufubaozhang(String yufubaozhang) {
        this.yufubaozhang = yufubaozhang;
    }

    @Override
    public String toString() {
        return "HexiaoYufu{" +
                "id='" + id + '\'' +
                ", hexiaodanhao='" + hexiaodanhao + '\'' +
                ", hexiaobaozhang='" + hexiaobaozhang + '\'' +
                ", yufudanhao='" + yufudanhao + '\'' +
                ", yufubaozhang='" + yufubaozhang + '\'' +
                '}';
    }
}
