package deverour.tower.service;

import deverour.tower.domain.Bill;
import deverour.tower.domain.Reback;
import deverour.tower.domain.User;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface BillService {

    public void saveBills(String filepath) throws Exception;

    public HashMap<String,String> checkBills(String filepath, User user, Set<String> paySet,Set<String> kaipiaobianhaoSet) throws Exception;

    public Set<String> getKaipiaobianhaoSet();

    public List<Bill> findbills(String fengongsilist,String quyulist,String yunyingshanglist,String zhifudanhao,String zhangqi,User user) throws ParseException;

    public List<Reback> findrebacks(String fengongsilist,String quyulist,String zhangqi,String yunyingshanglist,String huikuanbianhao,String issaomiaolist,String ishuikuanlist,String huikuanriqi,User user) throws ParseException;

    public int huikuanMarked(String[] arrhuikuanbianhao,String marked) throws ParseException;

    public int delete(String[] arrhuikuanbianhao);

    public int updatesaomiaoname(Reback reback);

    public String getsaomiaoname(String kaipiaobianhao);

    public void deletesaomiaoname(String kaipiaobianhao);



}
