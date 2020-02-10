package deverour.tower.test;

import java.util.*;

public class match {
    public static HashMap<String, Double> map = new HashMap<>();
    public static HashMap<String, Double> totalmap = new HashMap<>();
    public static ArrayList<String> list = new ArrayList<>();
    public static void main(String []args){
        new match().run();
        for (Map.Entry<String, Double> entry : totalmap.entrySet()) {
            //System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        System.out.println("size:"+totalmap.size());

    }

    public void run(){
       // HashMap<String, Double> map = new HashMap<>();
        //HashMap<String, Double> totalmap = new HashMap<>();
       // ArrayList<String> list = new ArrayList<>();
        map.put("a0 ",256.5);
        map.put("a1 ",100.0);
        map.put("a2 ",456.1);
        map.put("a3 ",4256.1);
        map.put("a4 ",234.67);
        list.add("a0 ");
        list.add("a1 ");
        list.add("a2 ");
        list.add("a3 ");
        list.add("a4 ");
        int startNum = 0;
        int endNum =list.size()-1;

        for(int i = startNum; i <= endNum; i ++){
            //System.out.println(i+"--------------"+i);
            ArrayList<String> templist=new ArrayList();
            templist.add(list.get(i));
            String names="";
            double sums=0.0;
            for (String st:templist){
                sums=sums+map.get(st);
                names=names+st;
            }
            totalmap.put(names,sums);
            String tempStr = "{ " + i + ",";
            //totalmap.put(tempStr,)
            printStr(tempStr);
            int tempI = i+1;
            goOn(tempI, endNum, tempStr,templist);
        }

    }

    public void goOn(int start, int end, String tempStr,ArrayList<String> templist){

        for(int j = start; j <= end; j ++){
            ArrayList<String> templist2=new ArrayList();
            for (String s:templist){
                templist2.add(s);
            }

            String temp = tempStr;
            templist2.add(list.get(j));
            String names="";
            double sums=0.0;
            for (String st:templist2){
                sums=sums+map.get(st);
                names=names+st;
            }
            totalmap.put(names,sums);
            temp += " " + j + ",";
            printStr(temp);
            /*for (String s:templist2){
                System.out.print(s);

            }
            System.out.println();
            System.out.println("***********"+j);*/
            int tempI = j+1;
            goOn(tempI, end, temp,templist2);
        }


    }

    public void printStr(String str){
        str = str.substring(0, str.length() -1) + "}";
        System.out.println(str);
    }


}
