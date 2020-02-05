package deverour.tower.service.impl;

import deverour.tower.controller.BillController;
import deverour.tower.domain.Bill;
import deverour.tower.domain.Group;
import deverour.tower.domain.Reback;
import deverour.tower.domain.User;
import deverour.tower.mapper.BillMapper;
import deverour.tower.mapper.LoginMapper;
import deverour.tower.myutils.MathUtil;
import deverour.tower.myutils.Utils;
import deverour.tower.poi.ExcelRead;
import deverour.tower.poi.LogicCheck;
import deverour.tower.service.BillService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("BillService")
public class BillServiceImpl implements BillService {

    @Autowired
    private BillMapper billMapper;


    @Autowired
    private LoginMapper loginMapper;

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})//开启事务
    public void saveBills(String filepath) throws Exception {
        System.out.println("BillService.saveBills.run()");
        Date date = new Date();
        System.out.println("date.getTime()/86400000+22570:"+((date.getTime()/86400000)+25570));
        String shangchuanriqi = String.valueOf(((date.getTime()+28800000)/86400000)+25569);
        File file = new File(filepath);
        System.out.println(filepath);
       // List<User> users= null;
        ExcelRead excelRead = new ExcelRead(file.getPath(),2);
        long t1=System.currentTimeMillis();
        int index=1;
        //System.out.println("index:"+index);
        Bill bill ;
        Reback reback = new Reback();
        double total=0.0;
        int counts=0;
        List<List<String>> lists=excelRead.getMyDataList();
        bill= MathUtil.getBill(lists.get(0));
        HashMap<String,String> fengongsimap=Group.fengongsiMap();
        reback.setFengongsi(fengongsimap.get(bill.getQuyu()));
        reback.setQuyu(bill.getQuyu());
        reback.setZhangqi(bill.getZhangqi());
        reback.setYunyingshang(bill.getJiesuanyunyingshang());
        reback.setKaipiaobianhao(bill.getKaipiaobianhao());

                


        for (List<String> billList:lists){
            bill= MathUtil.getBill(billList);
            bill.setShangchuanriqi(shangchuanriqi);
            total=total+ NumberUtils.toDouble(Utils.to2Round(bill.getJiesuanjine()));
            counts =billMapper.savebill(bill)+counts;
            index++;
        }
        //System.out.println("index:"+index);
        reback.setJiesuanjine(Utils.to2Round(String.valueOf(total)));
        reback.setIssaomiao("否");
        reback.setIshuikuan("否");
        reback.setHuikuanriqi("");
        reback.setShangchuanriqi(shangchuanriqi);
        billMapper.saveReback(reback);
        System.out.println("新增签认明细："+counts);



    }

    @Override
    public HashMap<String,String> checkBills(String filepath, User user, Set<String> paySet,Set<String> kaipiaobianhaoSet) throws Exception {
        File file = new File(filepath);
        ExcelRead excelRead = new ExcelRead(file.getPath(),2);
        HashMap<String,String> m= LogicCheck.billCheck(excelRead.getMyDataList(),user,paySet,kaipiaobianhaoSet);
        return m;
    }

    @Override
    public Set<String> getKaipiaobianhaoSet() {

        return billMapper.getKaipiaobianhaoSet();
    }

    @Override
    public List<Bill> findbills(String fengongsilist,String quyulist,String yunyingshanglist,String zhifudanhao,String zhangqi,User user) throws ParseException {
        Bill bill = new Bill();



        String quyus = "";

        if (quyulist.equals("all")){
            if (fengongsilist.equals("all")){
                for (String str : Group.getGroupMap().get(user.getGroup())){
                    quyus=quyus+"|"+str;
                }
            }else {
                String[] strArrfen = fengongsilist.split("-");
                for (int i = 1; i < strArrfen.length; ++i){
                    for (String str :Group.getGroupMap().get(strArrfen[i])){
                        quyus=quyus+"|"+str;
                    }
                }
            }
        }else {
            String[] strArr = quyulist.split("-");
            for (int i = 1; i < strArr.length; ++i){
                quyus=quyus+"|"+strArr[i];
            }

        }
        quyus=quyus.substring(1);
        //System.out.println("quyus:"+quyus);

        String kehus = "";
        if (yunyingshanglist.equals("all")){
            kehus="移动|联通|电信";
        }else {
            String[] strArrkehu = yunyingshanglist.split("-");
            //System.out.println(strArrkehu.length); //这里输出3
            for (int i = 1; i < strArrkehu.length; ++i) {
                kehus = kehus + "|" + strArrkehu[i];
            }
            kehus = kehus.substring(1);

        }
        //System.out.println("kehus:"+kehus);
        String nowTime=zhangqi.substring(0,10);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String shangchuanriqi = formatter.format(date);
        if (!shangchuanriqi.equals(nowTime)){
            String startTime=zhangqi.substring(6,10)+zhangqi.substring(0,2);
            String endTime=zhangqi.substring(19,23)+zhangqi.substring(13,15);
            String timeList = Utils.getTimeList(startTime,endTime);
            bill.setTimes(timeList);
            //System.out.println("有时间");
           // System.out.println(timeList);
        }




        bill.setQuyus(quyus);
        if (zhifudanhao.length()>1){
            bill.setZhifudanhao(zhifudanhao);
        }

        bill.setKehus(kehus);



        return billMapper.findbills(bill);
    }

    @Override
    public List<Reback> findrebacks(String fengongsilist,String quyulist,String zhangqi,String yunyingshanglist,String huikuanbianhao,String issaomiaolist,String ishuikuanlist,String huikuanriqi,User user) throws ParseException {

        Reback reback = new Reback();


        //区域
        String quyus = "";
        if (quyulist.equals("all")){
            if (fengongsilist.equals("all")){
                for (String str : Group.getGroupMap().get(user.getGroup())){
                    quyus=quyus+"|"+str;
                }
            }else {
                String[] strArrfen = fengongsilist.split("-");
                for (int i = 1; i < strArrfen.length; ++i){
                    for (String str :Group.getGroupMap().get(strArrfen[i])){
                        quyus=quyus+"|"+str;
                    }
                }
            }
        }else {
            String[] strArr = quyulist.split("-");
            for (int i = 1; i < strArr.length; ++i){
                quyus=quyus+"|"+strArr[i];
            }
        }
        quyus=quyus.substring(1);
        //System.out.println("区域:"+quyus);
        reback.setQuyus(quyus);





        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String nowTime = formatter.format(date);

        //账期
        String zhangqistartTime=zhangqi.substring(0,10);
        if (!zhangqistartTime.equals(nowTime)){
            String startTime=zhangqi.substring(6,10)+zhangqi.substring(0,2);
            String endTime=zhangqi.substring(19,23)+zhangqi.substring(13,15);
            String timeList = Utils.getTimeList(startTime,endTime);
            reback.setZhangtimes(timeList);

        }
        //System.out.println("账期:"+reback.getZhangtimes());

        //回款日期
        String huikuanstartTime=huikuanriqi.substring(0,10);
        String huikuanendTime=huikuanriqi.substring(13,23);

        if (!huikuanstartTime.equals(nowTime)){
            Date startDate=formatter.parse(huikuanstartTime);
            Date endtDate=formatter.parse(huikuanendTime);
            reback.setStartDate(Utils.getDateList(startDate));
            reback.setEndDate(Utils.getDateList(endtDate));
        }
        //System.out.println("回款日期:"+reback.getStartDate()+"-"+reback.getEndDate());





        //运营商
        String kehus = "";
        if (yunyingshanglist.equals("all")){
            kehus="移动|联通|电信";
        }else {
            String[] strArrkehu = yunyingshanglist.split("-");
            //System.out.println(strArrkehu.length); //这里输出3
            for (int i = 1; i < strArrkehu.length; ++i) {
                kehus = kehus + "|" + strArrkehu[i];
            }
            kehus = kehus.substring(1);
        }
        //System.out.println("运营商:"+kehus);
        reback.setKehus(kehus);

        //开票编号
        if (huikuanbianhao.length()>1){
            reback.setKaipiaobianhao(huikuanbianhao);
        }
        //System.out.println("开票编号:"+reback.getKaipiaobianhao());


        //是否扫描
        String issaomiaos = "";
        if (issaomiaolist.equals("all")){
            issaomiaos="是|否";
        }else {
            String[] strArrsaomiao = issaomiaolist.split("-");
            for (int i = 1; i < strArrsaomiao.length; ++i) {
                issaomiaos = issaomiaos + "|" + strArrsaomiao[i];
            }
            issaomiaos = issaomiaos.substring(1);
        }
        //System.out.println("是否扫描:"+issaomiaos);
        reback.setIssaomiaos(issaomiaos);

        //是否扫回款
        String ishuikuans = "";
        if (ishuikuanlist.equals("all")){
            ishuikuans="是|否";
        }else {
            String[] strArrhuikuan = ishuikuanlist.split("-");
            for (int i = 1; i < strArrhuikuan.length; ++i) {
                ishuikuans = ishuikuans + "|" + strArrhuikuan[i];
            }
            ishuikuans = ishuikuans.substring(1);

        }
        //System.out.println("是否扫回款:"+ishuikuans);
        reback.setIshuikuans(ishuikuans);



        return billMapper.findrebacks(reback);
    }

    @Override
    public int huikuanMarked(String[] arrhuikuanbianhao,String marked) throws ParseException {
        int counts = 0;
        Reback reback = new Reback();
        if (marked.equals("是")){
            Date date = new Date();
            System.out.println("date.getTime()/86400000+22570:"+((date.getTime()/86400000)+25570));
            String today = String.valueOf(((date.getTime()+28800000)/86400000)+25569);

            for (String huikuanbianhao : arrhuikuanbianhao){
                reback.setMarked(marked);
                reback.setKaipiaobianhao(huikuanbianhao);
                reback.setHuikuanriqi(today);
                counts = counts + billMapper.huikuanMarked(reback);
            }
        }else {
            for (String huikuanbianhao : arrhuikuanbianhao){
                reback.setMarked(marked);
                reback.setKaipiaobianhao(huikuanbianhao);
                reback.setHuikuanriqi("");
                counts = counts + billMapper.huikuanMarked(reback);
            }

        }

        return counts;
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})//开启事务
    public int delete(String[] arrhuikuanbianhao) {
        int counts = 0;
        Reback reback = new Reback();
        Bill bill=new Bill();
        for (String huikuanbianhao : arrhuikuanbianhao){
            reback.setKaipiaobianhao(huikuanbianhao);
            bill.setKaipiaobianhao(huikuanbianhao);

            billMapper.deleteBill(bill);
            counts = counts + billMapper.deleteReback(reback);
        }
        //System.out.println("counts:"+counts);
        return counts;
    }

    @Override
    public int updatesaomiaoname(Reback reback) {

        return billMapper.updatesaomiaoname(reback);
    }

    @Override
    public String getsaomiaoname(String kaipiaobianhao) {
        Reback reback = new Reback();
        System.out.println("Service.kaipiaobianhao:"+kaipiaobianhao);
        reback.setKaipiaobianhao(kaipiaobianhao);
        return billMapper.getsaomiaoname(reback).get(0);
    }

    @Override
    public void deletesaomiaoname(String saomiaoname) {
        System.out.println("Service.deletesaomiaoname:"+saomiaoname);
        File filepath = new File(BillController.SCANPATH+"\\"+saomiaoname);
        String n=BillController.SCANPATH+"\\"+saomiaoname;
        if (filepath.isFile() && filepath.exists()) {
            filepath.delete();
        }
    }




}
