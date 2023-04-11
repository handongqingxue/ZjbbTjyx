package javafish.clients.opc.connect;

import com.uWinOPCTjyx.util.APIUtil;
import javafish.clients.opc.component.OpcItem;

import java.util.ArrayList;
import java.util.List;

public class ReaderWriterOpc {

    public void writerOpc(){
        List<OpcItem> params = new ArrayList<OpcItem>();


        APIUtil.addTriggerVarFromOpc(params);
    }

}
