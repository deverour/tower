package deverour.tower.mapper;

import deverour.tower.domain.Bill;
import deverour.tower.domain.Reback;
import deverour.tower.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resources;
import java.util.List;
import java.util.Set;

@Repository
public interface BillMapper {


    @Insert("insert into bills (quyu,zhifudanhao,zhanzhibianma,dianbiaobianma,dianbiaobeilv,shifouzhigongdian,huhao,shiqi,zhongqi,qidu,zhidu,diansun,dianliang,dianzizonge,gongxiangyunyingshang,fentanbili,jiesuanjine,zhangqi,jiesuanyunyingshang,kaipiaoshijian,kaipiaobianhao,shangchuanriqi) values (#{quyu},#{zhifudanhao},#{zhanzhibianma},#{dianbiaobianma},#{dianbiaobeilv},#{shifouzhigongdian},#{huhao},#{shiqi},#{zhongqi},#{qidu},#{zhidu},#{diansun},#{dianliang},#{dianzizonge},#{gongxiangyunyingshang},#{fentanbili},#{jiesuanjine},#{zhangqi},#{jiesuanyunyingshang},#{kaipiaoshijian},#{kaipiaobianhao},#{shangchuanriqi}) ")
    public int savebill(Bill bill);

    @Insert("insert into rebacks (fengongsi,quyu,zhangqi,yunyingshang,kaipiaobianhao,jiesuanjine,issaomiao,ishuikuan,huikuanriqi) values (#{fengongsi},#{quyu},#{zhangqi},#{yunyingshang},#{kaipiaobianhao},#{jiesuanjine},#{issaomiao},#{ishuikuan},#{huikuanriqi}) ")
    public int saveReback(Reback reback);

    @Select("select kaipiaobianhao from bills")
    public Set<String> getKaipiaobianhaoSet();

    @Select("<script> select * from bills where 1=1<if test='zhifudanhao != null'>" +
            "and zhifudanhao REGEXP #{zhifudanhao} </if> "+
            "and jiesuanyunyingshang REGEXP #{kehus} and quyu REGEXP #{quyus} "+
            "<if test='times != null'>"+
            "and zhangqi REGEXP #{times} </if> "+
            "</script>")
    public List<Bill> findbills(Bill bill);

    @Select("<script> select * from rebacks where 1=1" +
            "<if test='zhangtimes != null'>"+
            "and zhangqi REGEXP #{zhangtimes} </if> "+
            "<if test='startDate != null'>"+
            "and huikuanriqi >= #{startDate} and #{endDate}>= huikuanriqi </if> "+
            "<if test='kaipiaobianhao != null'>" +
            "and kaipiaobianhao REGEXP #{kaipiaobianhao} </if> "+
            "and quyu REGEXP #{quyus} and yunyingshang REGEXP #{kehus} and issaomiao REGEXP #{issaomiaos} and ishuikuan REGEXP #{ishuikuans}"+
            "</script>")
    public List<Reback> findrebacks(Reback reback);


    @Select("select saomiaoname from rebacks where kaipiaobianhao = #{kaipiaobianhao}")
    public List<String> getsaomiaoname(Reback reback);

    @Update("update rebacks set ishuikuan =#{marked},huikuanriqi=#{huikuanriqi} where kaipiaobianhao = #{kaipiaobianhao}")
    public int huikuanMarked(Reback reback);

    @Delete("delete from rebacks where kaipiaobianhao = #{kaipiaobianhao}")
    public int deleteReback(Reback reback);

    @Delete("delete from bills where kaipiaobianhao = #{kaipiaobianhao}")
    public int deleteBill(Bill bill);

    @Update("update rebacks set saomiaoname =#{saomiaoname},issaomiao=#{issaomiao} where kaipiaobianhao = #{kaipiaobianhao}")
    public int updatesaomiaoname(Reback reback);



}
