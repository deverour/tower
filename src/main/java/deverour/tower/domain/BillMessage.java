package deverour.tower.domain;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BillMessage {
    public static final HashSet<String> quyuSet = new HashSet<String>() {
        {
            add("巴南");
            add("北碚");
            add("两江");
            add("璧山");
            add("城口");
            add("大渡口");
            add("大足");
            add("垫江");
            add("丰都");
            add("奉节");
            add("涪陵");
            add("合川");
            add("江北");
            add("江津");
            add("九龙坡");
            add("开州");
            add("梁平");
            add("南岸");
            add("南川");
            add("彭水");
            add("綦江");
            add("黔江");
            add("荣昌");
            add("沙坪坝");
            add("石柱");
            add("铜梁");
            add("潼南");
            add("万州");
            add("巫山");
            add("巫溪");
            add("武隆");
            add("秀山");
            add("永川");
            add("酉阳");
            add("渝北");
            add("渝中");
            add("云阳");
            add("长寿");
            add("忠县");

        }
    };
    public static final HashSet<String> yunyingshangSet = new HashSet<String>() {
        {
            add("移动");
            add("联通");
            add("电信");
        }
    };
    public static final HashSet<String> gongxiangyunyingshangSet = new HashSet<String>() {
        {
            add("移动");
            add("联通");
            add("电信");
            add("移动+联通");
            add("移动+电信");
            add("联通+电信");
            add("联通+移动");
            add("电信+移动");
            add("电信+联通");
            add("移动+联通+电信");
            add("移动+电信+联通");
            add("联通+移动+电信");
            add("联通+电信+移动");
            add("电信+移动+联通");
            add("电信+联通+移动");

        }
    };


}
