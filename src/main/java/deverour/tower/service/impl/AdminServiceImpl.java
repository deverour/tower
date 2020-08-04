package deverour.tower.service.impl;


import deverour.tower.domain.Bili;
import deverour.tower.domain.HexiaoYufu;
import deverour.tower.domain.Payment;
import deverour.tower.mapper.AdminMapper;
import deverour.tower.myutils.MathUtil;
import deverour.tower.poi.ExcelRead;
import deverour.tower.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("PaymentService")
public class AdminServiceImpl implements AdminService  {


    @Autowired
    AdminMapper adminMapper;
    @Override
    public String savePayment(String filepath) throws Exception {
        System.out.println("AdminService.savePayment");
        File file = new File(filepath);
        ArrayList<String> list=MathUtil.getTitle(filepath);
        System.out.println("1>>>>"+list.get(0));
        if (list.get(0).equals("支付单号") ){
            ExcelRead excelRead = new ExcelRead(file.getPath(),2);
            int index=1;
            System.out.println("index:"+index);
            int counts=0;
            Set set = adminMapper.getPaySet();
            for (List<String> paymentList:excelRead.getMyDataList()){
                String zhifudanhao = paymentList.get(0);
                if (!set.contains(zhifudanhao)){
                    counts = adminMapper.savePayment(zhifudanhao)+counts;
                    set.add(zhifudanhao);
                    index++;
                }

            }

            return "新增支付数据："+counts;
        }else {
            return "导入模板错误";
        }
    }

    @Override
    public String saveHexiaoYufu(String filepath) throws Exception {
        System.out.println("AdminService.saveHexiaoYufu");
        File file = new File(filepath);
        ArrayList<String> list=MathUtil.getTitle(filepath);
        if (list.get(0).equals("核销单号") && list.get(2).equals("预付单号")) {
            ExcelRead excelRead = new ExcelRead(file.getPath(), 2);
            int index = 1;
            System.out.println("index:" + index);
            HexiaoYufu hexiaoYufu = new HexiaoYufu();
            int counts = 0;

            for (List<String> List : excelRead.getMyDataList()) {
                hexiaoYufu.setId(List.get(0) + "-" + List.get(2));
                hexiaoYufu.setHexiaodanhao(List.get(0));
                hexiaoYufu.setHexiaobaozhang(List.get(1));
                hexiaoYufu.setYufudanhao(List.get(2));
                hexiaoYufu.setYufubaozhang(List.get(3));
                counts = adminMapper.saveHexiaoYufu(hexiaoYufu) + counts;
                index++;
            }
            return "新增核销预付单："+counts;
        }else {
            return "导入模板错误";
        }
    }

    @Override
    public String saveBili(String filepath) throws Exception {
        System.out.println("AdminService.saveBili");
        File file = new File(filepath);
        ArrayList<String> list=MathUtil.getTitle(filepath);
        if (list.get(1).equals("站址编码") && list.get(5).equals("结算模式") && list.get(7).equals("取数日期")) {
            ExcelRead excelRead = new ExcelRead(file.getPath(), 2);
            int index = 1;
            System.out.println("index:" + index);
            Bili bili = new Bili();
            int counts = 0;
            for (List<String> List : excelRead.getMyDataList()) {
                bili.setDianbiaobianma(List.get(0));
                bili.setZhanzhibianma(List.get(1));
                bili.setCaifenbili(List.get(2));
                bili.setKehu(List.get(3));
                bili.setKehubili(List.get(4));
                bili.setJiesuanmoshi(List.get(5));
                bili.setKaipiaoleixing(List.get(6));
                bili.setRiqi(List.get(7));
                counts = adminMapper.saveBili(bili) + counts;
                index++;
            }

            return "新增结算模式明细：" + counts;
        }else {
            return "导入模板错误";
        }
    }

    @Override
    public Set<String> getPaySet() {

        return adminMapper.getPaySet();
    }



}
