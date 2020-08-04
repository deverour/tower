package deverour.tower.domain;

public class Payment {
    private String id;

    private String zhifudanhao;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZhifudanhao() {
        return zhifudanhao;
    }

    public void setZhifudanhao(String zhifudanhao) {
        this.zhifudanhao = zhifudanhao;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", zhifudanhao='" + zhifudanhao + '\'' +
                '}';
    }
}
