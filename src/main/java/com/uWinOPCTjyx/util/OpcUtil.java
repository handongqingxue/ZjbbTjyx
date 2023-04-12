package com.uWinOPCTjyx.util;

import com.uWinOPCTjyx.service.ProcessVarService;
import javafish.clients.opc.JOpc;
import javafish.clients.opc.SynchReadItemExample;
import javafish.clients.opc.component.OpcGroup;
import javafish.clients.opc.component.OpcItem;
import javafish.clients.opc.exception.*;
import javafish.clients.opc.variant.Variant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
public class OpcUtil {

    public static List<OpcItem> readerOpc(List<String> varNameList){

        SynchReadItemExample test = new SynchReadItemExample();

        JOpc.coInitialize();   //初始化JOpc        JOpc继承父类JCustomOpc

        JOpc jopc = new JOpc("127.0.0.1", "Kepware.KEPServerEX.V6", "OPS3-PC");

        OpcGroup group = new OpcGroup("_System", true, 500, 0.0f);

        if (varNameList.size()>0){
            for (String varName : varNameList) {
                // new Opcitem("K1.Value",true,"");  "K1.Value" 表示要读取opc服务器中的变量名称的值。
                group.addItem(new OpcItem( varName, true, ""));
            }
        }
        jopc.addGroup(group);   //添加组

        OpcGroup responseGroup;

        try {
            jopc.connect();   //连接
            jopc.registerGroups();  //注册组
        } catch (ConnectivityException e1) {
            System.out.println("ConnectivityException="+e1.getMessage());
            //logger.error(e1.getMessage());
        } catch (UnableAddGroupException e) {
            System.out.println("UnableAddGroupException="+e.getMessage());
            //logger.error(e.getMessage());
        } catch (UnableAddItemException e) {
            System.out.println("UnableAddItemException="+e.getMessage());
            //logger.error(e.getMessage());
        }
        synchronized(test) {
            try {
                test.wait(50);
            } catch (InterruptedException e) {
                //logger.error(e.getMessage());
            }
        }
        List<OpcItem> opcItems=null;
        //同步读取
        while (true) {
            try {
                synchronized(test) {
                    test.wait(5000);
                }
                responseGroup = jopc.synchReadGroup(group);
//				ArrayList<OpcItem> opcItems = responseGroup.getItems();
                opcItems = new ArrayList<OpcItem>();
                OpcItem opcItem1 = new OpcItem("'甲醛实际进料重量_F1_AV",false,"111");
                opcItem1.setValue(new Variant("0"));
                opcItems.add(opcItem1);
                for (OpcItem opcItem : opcItems) {
                    System.out.println("Item名:" + opcItem.getItemName() + "  Item值: " + opcItem.getValue());
                }
                break;
            } catch (ComponentNotFoundException e) {
                //logger.error(e.getMessage()); //获取responseGroup错误
                JOpc.coUninitialize();     //错误关闭连接
            }catch (InterruptedException e) {
                //logger.error(e.getMessage());
                JOpc.coUninitialize(); //错误关闭连接
            } catch (SynchReadException e) {
                e.printStackTrace();
            }
        }
        return opcItems;
    }
}
