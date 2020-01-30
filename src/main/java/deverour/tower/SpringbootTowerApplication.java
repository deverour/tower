package deverour.tower;

import deverour.tower.domain.Bill;
import deverour.tower.mapper.BillMapper;
import deverour.tower.poi.ExcelRead;
import deverour.tower.poi.LogicCheck;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("deverour.tower.mapper")
public class SpringbootTowerApplication {

    @Autowired
    private BillMapper billMapper;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringbootTowerApplication.class, args);

    }

}
