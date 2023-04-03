package com.uWinOPCTjyx.service.serviceImpl;

import com.uWinOPCTjyx.dao.OpcBianLiangMapper;
import com.uWinOPCTjyx.dao.PiCiJiLuUMapper;
import com.uWinOPCTjyx.entity.*;
import com.uWinOPCTjyx.service.PiCiJiLuUService;
import com.uWinOPCTjyx.util.Constant;
import com.uWinOPCTjyx.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PiCiJiLuUServiceImpl implements PiCiJiLuUService {

    @Autowired
    private PiCiJiLuUMapper piCiJiLuUMapper;

    @Autowired
    private OpcBianLiangMapper opcBianLiangMapper;

    public int addPcgcFromPcIdList(List<Integer> pcIdList, Map<String, Object> jlsjMap) {
        // TODO Auto-generated method stub
        int count=0;
        PiCiJiLuU piCiJiLuU=null;
        Integer jlsjId = Integer.valueOf(jlsjMap.get("id").toString());
        Date date = new Date();
        for (Integer pcId : pcIdList) {
            piCiJiLuU=new PiCiJiLuU();
            piCiJiLuU.setPcId(pcId);
            piCiJiLuU.setJlsjId(jlsjId);
            piCiJiLuU.setJlkssj(DateUtil.getTimeStrByFormatStr(date,DateUtil.YEAR_TO_SECOND));
            piCiJiLuU.setJllx(PiCiJiLuU.PI_CI_GUO_CHENG_JI_LU);

            count+=piCiJiLuUMapper.add(piCiJiLuU);
        }
        return count;
    }

    public int addJdgcFromPcList(List<PiCiU> pcList, Map<String, Object> jlsjMap, Map<String, Object> jieDuanMap) {
        // TODO Auto-generated method stub
        int count=0;
        PiCiJiLuU piCiJiLuU=null;
        Integer jlsjId = Integer.valueOf(jlsjMap.get("id").toString());
        String jlsjMc = jlsjMap.get("mc").toString();
        Integer jieDuanId = Integer.valueOf(jieDuanMap.get("id").toString());
        List<OpcBianLiang> fczOBLList=null;
        if(JiLuShiJianM.ZHONG_LIANG_CHA_TEXT.equals(jlsjMc)) {//重量差的事件类型需要根据反应釜号获取重量
            String fczMc= Constant.FU_TEXT+Constant.BAI_FEN_HAO_TEXT+Constant.CHENG_ZHONG_TEXT+"_AV";//釜称重名称
            List<String> fyfhList=new ArrayList<String>();
            for (PiCiU pc : pcList) {
                String fyfh = pc.getFyfh();
                fyfhList.add(fyfh);
            }

            fczOBLList=opcBianLiangMapper.getFczListByFyfhList(fczMc,fyfhList);
        }

        for (PiCiU pc : pcList) {
            piCiJiLuU=new PiCiJiLuU();
            Integer pcId = pc.getId();
            Float zqzl=null;
            if(JiLuShiJianM.ZHONG_LIANG_CHA_TEXT.equals(jlsjMc)) {
                String fyfh = pc.getFyfh();
                for (OpcBianLiang fczOBL : fczOBLList) {
                    if(fyfh.equals(fczOBL.getFyfh())) {
                        zqzl=Float.valueOf(fczOBL.getSz());//因为是刚开始添加的阶段过程记录，设置重量为之前重量
                        break;
                    }
                }
            }

            piCiJiLuU.setPcId(pcId);
            piCiJiLuU.setJlsjId(jlsjId);
            if(JiLuShiJianM.SHI_JIAN_CHA_TEXT.equals(jlsjMc)) {
                Date date = new Date();
                piCiJiLuU.setJlkssj(DateUtil.getTimeStrByFormatStr(date,DateUtil.YEAR_TO_SECOND));
            }
            else if(JiLuShiJianM.ZHONG_LIANG_CHA_TEXT.equals(jlsjMc))//重量差的事件类型需要设置之前重量
                piCiJiLuU.setZqzl(zqzl);
            piCiJiLuU.setJllx(PiCiJiLuU.JIE_DUAN_GUO_CHENG_JI_LU);
            piCiJiLuU.setJdId(jieDuanId);

            count+=piCiJiLuUMapper.add(piCiJiLuU);
        }
//        Date date = new Date();
//
//
//        for (PiCiU pc : pcList) {
//            piCiJiLuU=new PiCiJiLuU();
//            Integer pcId = pc.getId();
//            piCiJiLuU.setPcId(pcId);
//            piCiJiLuU.setJlsjId(jlsjId);
//            if(JiLuShiJianU.SHI_JIAN_CHA_TEXT.equals(jlsjMc))
//                piCiJiLuU.setJlkssj(DateUtil.getTimeStrByFormatStr(date,DateUtil.YEAR_TO_SECOND));
//            piCiJiLuU.setJllx(PiCiJiLuM.JIE_DUAN_GUO_CHENG_JI_LU);
//
//            count+=piCiJiLuUMapper.add(piCiJiLuU);
//        }
        return count;
    }
}
