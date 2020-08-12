package deverour.tower.service.impl;

import deverour.tower.domain.Administrator;
import deverour.tower.mapper.AdminMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorService implements InitializingBean {
    @Autowired
    private AdminMapper adminMapper;

    public static Boolean switchs;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<Administrator> administrators = adminMapper.findAdminAll();
        for (Administrator administrator : administrators) {
            if (administrator.getFields().equals("switch")) {
                String values = administrator.getValue();
                if (values.equals("1")) {
                    switchs = true;
                } else {
                    switchs = false;
                }
            }
        }
    }

    public void turnOn() {
        adminMapper.updateAdmin("1");
        switchs = true;

    }


    public void turnOff() {
        adminMapper.updateAdmin("0");
        switchs = false;

    }
}

