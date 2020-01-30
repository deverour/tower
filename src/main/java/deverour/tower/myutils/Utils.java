package deverour.tower.myutils;

import deverour.tower.domain.Bill;
import deverour.tower.domain.Reback;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Utils {
    /*public static String getTimeList(String startTime,String endTime) throws ParseException {
        System.out.println("getTimeList");
        String timeList ="" ;
        SimpleDateFormat oldsdf = new SimpleDateFormat ("MM/dd/yyyy");
        SimpleDateFormat newsdf = new SimpleDateFormat ("yyyy-MM");
        Date startDate =oldsdf.parse(startTime);
        Date endtDate =oldsdf.parse(endTime);

        Calendar calendar = Calendar.getInstance();
        for (Date indexDate =startDate;!indexDate.after(endtDate);){

            String indexTime= newsdf.format(indexDate);
            timeList=timeList+"|"+indexTime;
            calendar.setTime(indexDate);
            calendar.add(Calendar.MONTH, 1);
            indexDate=calendar.getTime();
        }
        timeList=timeList.substring(1);

        return timeList;
    }*/
    public static String getTimeList(String startTime,String endTime) throws ParseException {

        String timeList ="" ;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("yyyyMM");
        Date startDate =simpleDateFormat.parse(startTime);
        Date endtDate =simpleDateFormat.parse(endTime);

        Calendar calendar = Calendar.getInstance();
        for (Date indexDate =startDate;!indexDate.after(endtDate);){

            String indexTime= simpleDateFormat.format(indexDate);
            timeList=timeList+"|"+indexTime;
            calendar.setTime(indexDate);
            calendar.add(Calendar.MONTH, 1);
            indexDate=calendar.getTime();
        }
        timeList=timeList.substring(1);

        return timeList;
    }
    public static String getDateList(Date date) {
        int y =(int)(date.getTime()/1000/3600/24);
        return String.valueOf(y+25570);
    }



    public static String to2Round(String numStr){
        DecimalFormat format = new DecimalFormat("#0.##");
        return format.format(new BigDecimal(numStr));
    }
    public static String to6Round(String numStr){
        DecimalFormat format = new DecimalFormat("#0.######");
        return format.format(new BigDecimal(numStr));
    }

    public static ArrayList<String> getList(Bill bill){
        ArrayList<String> list=new ArrayList<String>();
        list.add(bill.getId());
        list.add(bill.getQuyu());
        list.add(bill.getZhifudanhao());
        list.add(bill.getZhanzhibianma());
        list.add(bill.getDianbiaobianma());
        list.add(bill.getDianbiaobeilv());
        list.add(bill.getShifouzhigongdian());
        list.add(bill.getHuhao());
        list.add(bill.getShiqi());
        list.add(bill.getZhongqi());
        list.add(bill.getQidu());
        list.add(bill.getZhidu());
        list.add(bill.getDiansun());
        list.add(bill.getDianliang());
        list.add(bill.getDianzizonge());
        list.add(bill.getGongxiangyunyingshang());
        list.add(bill.getFentanbili());
        list.add(bill.getJiesuanjine());
        list.add(bill.getZhangqi());
        list.add(bill.getJiesuanyunyingshang());
        list.add(bill.getKaipiaoshijian());
        list.add(bill.getKaipiaobianhao());
        list.add(bill.getShangchuanriqi());
        return list;
    }

    public static ArrayList<String> getRebackList(Reback reback){
        ArrayList<String> list=new ArrayList<String>();
        list.add(reback.getFengongsi());
        list.add(reback.getQuyu());
        list.add(reback.getZhangqi());
        list.add(reback.getYunyingshang());
        list.add(reback.getKaipiaobianhao());
        list.add(reback.getJiesuanjine());
        list.add(reback.getIssaomiao());
        list.add(reback.getIshuikuan());
        list.add(reback.getHuikuanriqi());
        list.add(reback.getShangchuanriqi());
        return list;
    }

    public static String addUUID(String name,String kaipiaobianhao){
        int lastindex =name.lastIndexOf(".");
        String uuid = UUID.randomUUID().toString().replace("-","");
        if (lastindex<0){
            return kaipiaobianhao+"_"+uuid;
        }else {
            String newname2=name.substring(lastindex);
            return kaipiaobianhao+"_"+uuid+newname2;
        }
    }

    public static String getFinalFileName(String fileNamePrefix,String filename){
        int lastindex =filename.lastIndexOf(".");
        String fileNameSuffix = filename.substring(lastindex);
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode(fileNamePrefix,"UTF-8")+fileNameSuffix;
        }catch (UnsupportedEncodingException e){

            e.printStackTrace();
        }
        return finalFileName;
    }

    public static String getRealName(String name){
        int lastindex =name.lastIndexOf("\\");
        if(lastindex>0){

            return name.substring(lastindex+1);
        }else {
            return name;
        }


    }


}
