package deverour.tower.mapper;

import deverour.tower.domain.Bili;
import deverour.tower.domain.HexiaoYufu;
import deverour.tower.domain.Payment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AdminMapper {
    @Insert("insert into payment (shi,zhifudanhao,gongdianleixing,zhifujine,fukuanriqi) values (#{shi},#{zhifudanhao},#{gongdianleixing},#{zhifujine},#{fukuanriqi}) ")
    public int savePayment(Payment payment);

    @Insert("insert IGNORE into hexiaoyufu (id,hexiaodanhao,hexiaobaozhang,yufudanhao,yufubaozhang) values (#{id},#{hexiaodanhao},#{hexiaobaozhang},#{yufudanhao},#{yufubaozhang})")
    public int saveHexiaoYufu(HexiaoYufu hexiaoYufu);

    @Insert("insert into bili(dianbiaobianma,zhanzhibianma,caifenbili,kehu,kehubili,jiesuanmoshi,kaipiaoleixing,riqi) values (#{dianbiaobianma},#{zhanzhibianma},#{caifenbili},#{kehu},#{kehubili},#{jiesuanmoshi},#{kaipiaoleixing},#{riqi})")
    public int saveBili(Bili bili);

    @Select("select zhifudanhao from payment")
    public Set<String> getPaySet();
}


