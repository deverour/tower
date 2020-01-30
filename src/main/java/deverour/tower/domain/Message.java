package deverour.tower.domain;

public class Message {
    private String isCheck;
    private String msg;
    private String filepath;
    private String total;

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String  toString() {
        return "Message{" +
                "isCheck='" + isCheck + '\'' +
                ", msg='" + msg + '\'' +
                ", filepath='" + filepath + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}
