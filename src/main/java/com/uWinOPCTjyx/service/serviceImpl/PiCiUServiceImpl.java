package com.uWinOPCTjyx.service.serviceImpl;

import com.uWinOPCTjyx.entity.*;
import com.uWinOPCTjyx.dao.*;
import com.uWinOPCTjyx.service.*;
import com.uWinOPCTjyx.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PiCiUServiceImpl implements PiCiUService {

    @Autowired
    private PiCiUMapper piCiUMapper;
    @Autowired
    private PiCiMMapper piCiMMapper;

    public int add(PiCiU piCiU){
        return piCiUMapper.add(piCiU);
    }

    public List<PiCiU> getList() {
        return piCiUMapper.getList();
    }

    public int addByBlksOBLList(List<OpcBianLiang> blksOBLList) {
        int count=0;//计数器
        PiCiU piCiU=null;//空对象
        for (OpcBianLiang blksOBL : blksOBLList) {//遍历opc变量集合
            Integer scnf = Integer.valueOf(DateUtil.getYYYY());//获取年份
            Integer scbh = piCiMMapper.getMaxScbhByScnf(scnf);//记录生产编号：查询年份，找到最大的生产编号值；
            if(scbh==null)//如果没有就等于是新的年份，生产编号要赋值为1，要从1重新开始记录生产编号
                scbh=1;
            else
                scbh++;//如果是对象里面的年份(获取的当前年份)生产编号就+1以此类推
            String mc = blksOBL.getMc();//获取变量名称
            int fyfhStartLoc = mc.indexOf("_")+1;//找到变量名名中_的字符串的位置
            int fyfhEndLoc = mc.indexOf("_AV");//找到变量名名中_AV的字符串的位置
            String fyfh=mc.substring(fyfhStartLoc, fyfhEndLoc);
            piCiU=new PiCiU();//创建对象给对象赋值
            piCiU.setScnf(scnf);
            piCiU.setScbh(scbh);
            piCiU.setFyfh(fyfh);
            count+=piCiUMapper.add(piCiU);//执行添加操作
        }
        return count;
    }
}
