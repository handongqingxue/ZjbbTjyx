package com.uWinOPCTjyx.service.serviceImpl;

import com.uWinOPCTjyx.dao.PiCiJiLuUMapper;
import com.uWinOPCTjyx.entity.*;
import com.uWinOPCTjyx.service.PiCiJiLuUService;
import com.uWinOPCTjyx.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PiCiJiLuUServiceImpl implements PiCiJiLuUService {

    @Autowired
    private PiCiJiLuUMapper piCiJiLuUMapper;

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
            piCiJiLuU.setJllx(PiCiJiLuM.PI_CI_GUO_CHENG_JI_LU);

            count+=piCiJiLuUMapper.add(piCiJiLuU);
        }
        return count;
    }

    public int addJdgcFromPcList(List<PiCiU> pcList, Map<String, Object> jlsjMap, Integer jieDuanId) {
        // TODO Auto-generated method stub
        int count=0;
        PiCiJiLuU piCiJiLuU=null;
        Integer jlsjId = Integer.valueOf(jlsjMap.get("id").toString());
        String jlsjMc = jlsjMap.get("mc").toString();
        Date date = new Date();
        for (PiCiU pc : pcList) {
            piCiJiLuU=new PiCiJiLuU();
            Integer pcId = pc.getId();
            piCiJiLuU.setPcId(pcId);
            piCiJiLuU.setJlsjId(jlsjId);
            if(JiLuShiJianU.SHI_JIAN_CHA_TEXT.equals(jlsjMc))
                piCiJiLuU.setJlkssj(DateUtil.getTimeStrByFormatStr(date,DateUtil.YEAR_TO_SECOND));
            piCiJiLuU.setJllx(PiCiJiLuM.JIE_DUAN_GUO_CHENG_JI_LU);

            count+=piCiJiLuUMapper.add(piCiJiLuU);
        }
        return count;
    }
}
