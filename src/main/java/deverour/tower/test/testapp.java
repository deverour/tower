package deverour.tower.test;

import deverour.tower.poi.ExcelRead;
import deverour.tower.poi.LogicCheck;
import deverour.tower.service.BillService;
import deverour.tower.service.impl.BillServiceImpl;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;

public class testapp {
    @Autowired
    BillServiceImpl billService;
    public static void main(String[] args) throws Exception {
        int lastindex ="ewtgwryywery".lastIndexOf("\"");
        System.out.println(lastindex);

    }
}
