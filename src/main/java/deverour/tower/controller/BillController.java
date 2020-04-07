package deverour.tower.controller;


import com.alibaba.fastjson.JSON;
import deverour.tower.domain.*;
import deverour.tower.mapper.LoginMapper;
import deverour.tower.myutils.Utils;
import deverour.tower.poi.ExcelWrite;
import deverour.tower.service.BillService;
import deverour.tower.service.AdminService;
import deverour.tower.service.UserService;
import deverour.tower.service.impl.AdminServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@ResponseBody
@Controller
@RequestMapping("/bills")
public class BillController {

    public static final String SCANPATH = "D:\\SystemInfo\\logs\\ScanFile_Upload";
    public static final String UPLOAD_TEMP="D:\\SystemInfo\\Cache\\TEMP";

    @Autowired
    private UserService UserService;

    @Autowired
    private BillService billService;
    @Autowired
    private AdminService adminService;


    @RequestMapping("/updatesaomiao")
    public Message updatesaomiaoname(HttpServletRequest request,@RequestParam("file") MultipartFile upload,String kaipiaobianhao) throws Exception {
        System.out.println("------------------------");
        System.out.println("BillController.updatesaomiaoname.run()");
        Reback reback=new Reback();
        Message message = new Message();
        String saomiaoname = Utils.getRealName(upload.getOriginalFilename());
        saomiaoname = Utils.addUUID(saomiaoname,kaipiaobianhao);
        upload.transferTo(new File(SCANPATH,saomiaoname));
        reback.setKaipiaobianhao(kaipiaobianhao);
        reback.setIssaomiao("是");
        reback.setSaomiaoname(saomiaoname);
        billService.updatesaomiaoname(reback);
        message.setMsg("上传成功");

        return message;
    }

    @RequestMapping("/deletesaomiao")
    public Message deletesaomiao(String kaipiaobianhao) throws Exception {
        System.out.println("------------------------");
        System.out.println("BillController.deletesaomiao.run()");
        Reback reback=new Reback();
        Message message = new Message();
        reback.setKaipiaobianhao(kaipiaobianhao);
        reback.setIssaomiao("否");
        String saomiaoname = billService.getsaomiaoname(kaipiaobianhao);
        reback.setSaomiaoname("");
        billService.updatesaomiaoname(reback);
        billService.deletesaomiaoname(saomiaoname);
        System.out.println("删除扫描件成功");
        message.setMsg("删除扫描件成功");
        return message;
    }

    @RequestMapping("/downloadsaomiao")
    public ResponseEntity<byte[]> downloadsaomiao(String kaipiaobianhao) throws IOException {
        System.out.println("------------------------");
        System.out.println("BillController.downloadsaomiao.run()");
        String saomiaoname = billService.getsaomiaoname(kaipiaobianhao);
        //System.out.println("saomiaoname:"+saomiaoname);
        if (saomiaoname.equals("")){
            saomiaoname="default_shaomiao.jpg";
        }

        String finalFileName = Utils.getFinalFileName(kaipiaobianhao,saomiaoname);

        InputStream is= new FileInputStream(SCANPATH+"\\"+saomiaoname);

        byte[] body = null;
        body = new byte[is.available()];
        is.read(body);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attchement;filename=" + finalFileName);
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> entity = new ResponseEntity<>(body, headers, statusCode);
        System.out.println("查询成功,开始下载");
        return entity;

    }


    @RequestMapping("/check")
    public Message check(HttpServletRequest request, HttpSession httpSession,@RequestParam("file") MultipartFile upload) throws Exception {
        System.out.println("------------------------");
        System.out.println("BillController.check.run()");
        Set<String> paySet = AdminServiceImpl.set;
        Set<String> kaipiaobianhaoSet =billService.getKaipiaobianhaoSet();
        User user = (User)httpSession.getAttribute("user");
        //String path = request.getSession().getServletContext().getRealPath("/uploads");
        String path =UPLOAD_TEMP;
        //System.out.println("path:"+path);
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        String filename = Utils.getRealName(upload.getOriginalFilename());
        System.out.println("filename:"+filename);
        String uuid = UUID.randomUUID().toString().replace("-","");
        filename = uuid+"-"+filename;
        upload.transferTo(new File(path,filename));
        String filepath = file.getPath()+"\\"+filename;
        HashMap<String,String> map=billService.checkBills(filepath,user,paySet,kaipiaobianhaoSet);
        String msg = map.get("msg");
        Message message = new Message();

        if(msg.length()>0){
            message.setIsCheck("fail");
            message.setMsg(msg);
        }else{
            message.setIsCheck("success");
            message.setTotal(map.get("total"));

        }
        message.setFilepath(filepath);
        //System.out.println("msgpath:"+message.getFilepath());
        //System.out.println();
        return message;
    }

    @RequestMapping("/cpycheck")
    public Message cpycheck(HttpServletRequest request, HttpSession httpSession,@RequestParam("file") MultipartFile upload) throws Exception {
        System.out.println("------------------------");
        System.out.println("BillController.cpycheck.run()");

        Set<String> kaipiaobianhaoSet =billService.getKaipiaobianhaoSet();
        User user = (User)httpSession.getAttribute("user");
        //String path = request.getSession().getServletContext().getRealPath("/uploads");
        String path =UPLOAD_TEMP;
        //System.out.println("path:"+path);
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        String filename = Utils.getRealName(upload.getOriginalFilename());
        System.out.println("filename:"+filename);
        String uuid = UUID.randomUUID().toString().replace("-","");
        filename = uuid+"-"+filename;
        upload.transferTo(new File(path,filename));
        String filepath = file.getPath()+"\\"+filename;
        HashMap<String,String> map=billService.checkCpys(filepath,user,kaipiaobianhaoSet);
        String msg = map.get("msg");
        Message message = new Message();

        if(msg.length()>0){
            message.setIsCheck("fail");
            message.setMsg(msg);
        }else{
            message.setIsCheck("success");
            message.setTotal(map.get("total"));

        }
        message.setFilepath(filepath);
        //System.out.println("msgpath:"+message.getFilepath());
        //System.out.println();
        return message;
    }

    @RequestMapping("/save")
    public Message save(HttpServletRequest request) throws Exception {
        System.out.println("------------------------");
        System.out.println("BillController.save.run()");
        Message message = new Message();
        String filepath = request.getParameter("filepath");
        System.out.println("filepath:"+filepath);
        billService.saveBills(filepath);
        message.setMsg("上传成功");
        return message;
    }

    @RequestMapping("/cpysave")
    public Message cypsave(HttpServletRequest request) throws Exception {
        System.out.println("------------------------");
        System.out.println("BillController.save.run()");
        Message message = new Message();
        String filepath = request.getParameter("filepath");
        System.out.println("filepath:"+filepath);
        billService.saveCpys(filepath);
        message.setMsg("上传成功");
        return message;
    }

    @RequestMapping("/savepayment")
    public Message savepayment(HttpServletRequest request,@RequestParam("file") MultipartFile upload) throws Exception {
        System.out.println("------------------------");
        System.out.println("BillController.savepayment.run()");
        Message message = new Message();
        //String path = request.getSession().getServletContext().getRealPath("/uploads");
        String path =UPLOAD_TEMP;
        //System.out.println("path:"+path);
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        String filename = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-","");
        filename = uuid+"-"+filename;
        upload.transferTo(new File(path,filename));
        String filepath = file.getPath()+"\\"+filename;
        String msg =adminService.savePayment(filepath);
        if (msg.equals("导入模板错误")){
            message.setMsg("导入模板错误");
            return message;
        }else {
            message.setIsCheck("success");
            message.setMsg(msg);
            return message;
        }

    }

    @RequestMapping("/savehexiaoyufu")
    public Message savehexiaoyufu(HttpServletRequest request,@RequestParam("file") MultipartFile upload) throws Exception {
        System.out.println("------------------------");
        System.out.println("BillController.savehexiaoyufu.run()");
        Message message = new Message();
        //String path = request.getSession().getServletContext().getRealPath("/uploads");
        String path =UPLOAD_TEMP;
        //System.out.println("path:"+path);
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        String filename = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-","");
        filename = uuid+"-"+filename;
        upload.transferTo(new File(path,filename));
        String filepath = file.getPath()+"\\"+filename;
        String msg = adminService.saveHexiaoYufu(filepath);
        if (msg.equals("导入模板错误")){
            message.setMsg("导入模板错误");
            return message;
        }else {
            message.setIsCheck("success");
            message.setMsg(msg);
            return message;
        }
    }

    @RequestMapping("/savebili")
    public Message savebili(HttpServletRequest request,@RequestParam("file") MultipartFile upload) throws Exception {
        System.out.println("------------------------");
        System.out.println("BillController.savebili.run()");
        Message message = new Message();
        //String path = request.getSession().getServletContext().getRealPath("/uploads");
        String path =UPLOAD_TEMP;
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        String filename = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-","");
        filename = uuid+"-"+filename;
        upload.transferTo(new File(path,filename));
        String filepath = file.getPath()+"\\"+filename;
        String msg = adminService.saveBili(filepath);
        if (msg.equals("导入模板错误")){
            message.setMsg("导入模板错误");
            return message;
        }else {
            message.setIsCheck("success");
            message.setMsg(msg);
            return message;
        }
    }


    @RequestMapping("/user")
    public Object getUser(HttpServletRequest request, HttpSession httpSession, HttpServletResponse response) throws Exception {
        //System.out.println("getUser.run()");
        Object user = httpSession.getAttribute("user");
        //System.out.println("user:"+user);
        return user;
    }

    @RequestMapping("/changepassword")
    public Message changepassword(HttpSession httpSession,HttpServletRequest request) throws Exception {
        System.out.println("------------------------");
        System.out.println("BillController.changepassword.run()");
        Message message=new Message();
        String username = request.getParameter("cusername");
        String oldpassword = request.getParameter("oldpassword");
        String newpassword = request.getParameter("newpassword");
        String confirmpassword = request.getParameter("confirmpassword");
        System.out.println("username:"+username);
        System.out.println("oldpassword:"+oldpassword);
        System.out.println("newpassword:"+newpassword);
        System.out.println("confirmpassword:"+confirmpassword);
        String msg =UserService.changepassword(username,oldpassword,newpassword);
        message.setIsCheck(msg);




        return message;

    }

    @RequestMapping("/querybill")
    public Message querybill(HttpSession httpSession,@RequestParam(value = "fengongsilist") String fengongsilist,@RequestParam(value = "quyulist") String quyulist,
                     @RequestParam(value = "yunyingshanglist") String yunyingshanglist,@RequestParam(value = "zhifudanhao") String zhifudanhao,@RequestParam(value = "zhangqi") String zhangqi) throws Exception {
        System.out.println("------------------------");
        System.out.println("BillController.querybill.run()");
        User user = (User)httpSession.getAttribute("user");
        List<Bill> bills =billService.findbills(fengongsilist,quyulist,yunyingshanglist,zhifudanhao,zhangqi,user);
        double sum=0.0;
        int count=0;
        for (Bill b:bills){
            sum=sum+Double.parseDouble(b.getJiesuanjine());
            count++;
        }

        Message message = new Message();
        message.setMsg(count+"条明细，    合计金额："+Utils.to2Round(String.valueOf(sum)));
        return message;

    }

    @RequestMapping("/querycpy")
    public Message querycpy(HttpSession httpSession,@RequestParam(value = "fengongsilist") String fengongsilist,@RequestParam(value = "quyulist") String quyulist,
                             @RequestParam(value = "yunyingshanglist") String yunyingshanglist,@RequestParam(value = "huikuanbianhao") String huikuanbianhao,@RequestParam(value = "zhangqi") String zhangqi) throws Exception {
        System.out.println("------------------------");
        System.out.println("BillController.querycpy.run()");
        User user = (User)httpSession.getAttribute("user");
        List<Cpy> Cpys =billService.findcpys(fengongsilist,quyulist,yunyingshanglist,huikuanbianhao,zhangqi,user);
        double sum=0.0;
        int count=0;
        for (Cpy c:Cpys){
            sum=sum+Double.parseDouble(c.getJiesuanjine());
            count++;
        }

        Message message = new Message();
        message.setMsg(count+"条明细，    合计金额："+Utils.to2Round(String.valueOf(sum)));
        return message;

    }

    @RequestMapping("/exportbill")
    public ResponseEntity<byte[]> exportbill(HttpSession httpSession,HttpServletRequest request) throws Exception {
        System.out.println("------------------------");
        System.out.println("BillController.exportbill.run()");
        User user = (User)httpSession.getAttribute("user");
        String fengongsilist = request.getParameter("fengongsilist");
        String quyulist = request.getParameter("quyulist");
        String yunyingshanglist = request.getParameter("yunyingshanglist");
        String zhifudanhao = request.getParameter("zhifudanhao");
        String zhangqi = request.getParameter("zhangqi");
        /*System.out.println("fengongsilist:"+fengongsilist);
        System.out.println("quyulist:"+quyulist);
        System.out.println("yunyingshanglist:"+yunyingshanglist);
        System.out.println("zhifudanhao:"+zhifudanhao);
        System.out.println("zhangqi:"+zhangqi);*/
        List<Bill> bills =billService.findbills(fengongsilist,quyulist,yunyingshanglist,zhifudanhao,zhangqi,user);
        InputStream is= ExcelWrite.WriteAll(bills);
        byte[] body = null;
        body = new byte[is.available()];
        is.read(body);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attchement;filename=" + URLEncoder.encode("代垫签认明细","UTF-8")+".xlsx");
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> entity = new ResponseEntity<>(body, headers, statusCode);
        System.out.println("查询成功,开始下载");
        return entity;

    }

    @RequestMapping("/exportcpy")
    public ResponseEntity<byte[]> exportcpy(HttpSession httpSession,HttpServletRequest request) throws Exception {
        System.out.println("------------------------");
        System.out.println("BillController.exportCpy.run()");
        User user = (User)httpSession.getAttribute("user");
        String fengongsilist = request.getParameter("fengongsilist");
        String quyulist = request.getParameter("quyulist");
        String yunyingshanglist = request.getParameter("yunyingshanglist");
        String huikuanbianhao = request.getParameter("huikuanbianhao");
        String zhangqi = request.getParameter("zhangqi");
        /*System.out.println("fengongsilist:"+fengongsilist);
        System.out.println("quyulist:"+quyulist);
        System.out.println("yunyingshanglist:"+yunyingshanglist);
        System.out.println("zhifudanhao:"+zhifudanhao);
        System.out.println("zhangqi:"+zhangqi);*/
        List<Cpy> cpys =billService.findcpys(fengongsilist,quyulist,yunyingshanglist,huikuanbianhao,zhangqi,user);
        InputStream is= ExcelWrite.WriteCpyAll(cpys);
        byte[] body = null;
        body = new byte[is.available()];
        is.read(body);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attchement;filename=" + URLEncoder.encode("包干签认明细","UTF-8")+".xlsx");
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> entity = new ResponseEntity<>(body, headers, statusCode);
        System.out.println("查询成功,开始下载");
        return entity;

    }


    @RequestMapping("/exportreback")
    public ResponseEntity<byte[]> exportreback(HttpSession httpSession,HttpServletRequest request) throws Exception {
        System.out.println("------------------------");
        System.out.println("BillController.getreback.run()");
        User user = (User)httpSession.getAttribute("user");
        String fengongsilist = request.getParameter("fengongsilist");
        String quyulist = request.getParameter("quyulist");
        String zhangqi = request.getParameter("zhangqi");
        String yunyingshanglist = request.getParameter("yunyingshanglist");
        String huikuanbianhao = request.getParameter("huikuanbianhao");
        String issaomiaolist = request.getParameter("issaomiaolist");
        String ishuikuanlist = request.getParameter("ishuikuanlist");
        String huikuanriqi = request.getParameter("huikuanriqi");


        List<Reback> rebacks=billService.findrebacks(fengongsilist,quyulist,zhangqi,yunyingshanglist,huikuanbianhao,issaomiaolist,ishuikuanlist,huikuanriqi,user);
        InputStream is= ExcelWrite.WriteRebacks(rebacks);
        byte[] body = null;
        body = new byte[is.available()];
        is.read(body);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attchement;filename=" + URLEncoder.encode("回款流程明细","UTF-8")+".xlsx");
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> entity = new ResponseEntity<>(body, headers, statusCode);
        System.out.println("查询成功,开始下载");
        return entity;


    }

    @RequestMapping("/queryreback")
    public List<String> queryreback(HttpSession httpSession,HttpServletRequest request) throws ParseException {
        System.out.println("------------------------");
        System.out.println("BillController.getreback.run()");
        User user = (User)httpSession.getAttribute("user");
        String fengongsilist = request.getParameter("fengongsilist");
        String quyulist = request.getParameter("quyulist");
        String zhangqi = request.getParameter("zhangqi");
        String yunyingshanglist = request.getParameter("yunyingshanglist");
        String huikuanbianhao = request.getParameter("huikuanbianhao");
        String issaomiaolist = request.getParameter("issaomiaolist");
        String ishuikuanlist = request.getParameter("ishuikuanlist");
        String huikuanriqi = request.getParameter("huikuanriqi");
       /* System.out.println("fengongsilist:"+fengongsilist);
        System.out.println("quyulist:"+quyulist);
        System.out.println("zhangqi:"+zhangqi);
        System.out.println("yunyingshanglist:"+yunyingshanglist);
        System.out.println("huikuanbianhao:"+huikuanbianhao);
        System.out.println("issaomiaolist:"+issaomiaolist);
        System.out.println("ishuikuanlist:"+ishuikuanlist);
        System.out.println("huikuanriqi:"+huikuanriqi);*/


        List<Reback> rebacks=billService.findrebacks(fengongsilist,quyulist,zhangqi,yunyingshanglist,huikuanbianhao,issaomiaolist,ishuikuanlist,huikuanriqi,user);
        List<String> list=new ArrayList<>();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Reback reback:rebacks){
            String huikuanDate="";

            //System.out.println("reback.getHuikuanriqi():"+reback.getHuikuanriqi());
            if (reback.getHuikuanriqi().length()>=5){
                //System.out.println("reback.getHuikuanriqi():"+(long)(NumberUtils.toInt(reback.getHuikuanriqi())-25570)*86400000);
                date.setTime((long)(NumberUtils.toInt(reback.getHuikuanriqi())-25569)*86400000);
                 huikuanDate = simpleDateFormat.format(date);
            }
            String jiesuanmoshi="";
            String iscpy =reback.getIscpy();
            if(iscpy.equals("是")){
                jiesuanmoshi="包干";
            }else {
                jiesuanmoshi="代垫";
            }

            list.add(reback.getFengongsi()+"*"+reback.getQuyu()+"*"+reback.getZhangqi()+"*"+reback.getYunyingshang()+"*"+jiesuanmoshi+"*"+reback.getKaipiaobianhao()+"*"+reback.getJiesuanjine()+"*"+reback.getIssaomiao()+"*"+reback.getIshuikuan()+"*"+huikuanDate);
        }

        return list;
    }


    @RequestMapping("/marked")
    public Message marked(String[] arrhuikuanbianhao,String marked) throws ParseException {
        System.out.println("------------------------");
        System.out.println("BillController.marked.run()");
        Message message = new Message();
        if( arrhuikuanbianhao==null||arrhuikuanbianhao.length==0){
            message.setMsg("没有可标记数据");
        }else {
            int msg =billService.huikuanMarked(arrhuikuanbianhao,marked);
            message.setMsg("成功："+msg+" 条数据");
        }
            return message;
    }

    @RequestMapping("/delete")
    public Message delete(String[] arrhuikuanbianhao) {
        System.out.println("------------------------");
        System.out.println("BillController.delete.run()");
        Message message = new Message();
        if( arrhuikuanbianhao==null||arrhuikuanbianhao.length==0){
            message.setMsg("未勾选需要删除的数据(已回款明细无法删除，请核实后联系市公司撤销打标)");
        }else {
            int msg =billService.delete(arrhuikuanbianhao);
            message.setMsg("删除："+msg+" 笔回款");
        }
        return message;
    }






}
