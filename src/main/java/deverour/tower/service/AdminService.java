package deverour.tower.service;

import org.springframework.beans.factory.InitializingBean;

import java.util.Set;

public interface AdminService extends InitializingBean {

    public String savePayment(String filepath) throws Exception;

    public String saveHexiaoYufu(String filepath) throws Exception;

    public String saveBili(String filepath) throws Exception;

    //public Set<String> getPaySet();
}
