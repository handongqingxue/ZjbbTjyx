package com.uWinOPCTjyx.entity;

public class JieDuanU {

    /**
     * 加甲醛阶段名称
     */
    public static final String JIA_JIA_QUAN_TEXT="加甲醛";
    /**
     * 一次加助剂阶段名称
     */
    public static final String YI_CI_JIA_ZHU_JI_TEXT="一次加助剂";
    /**
     * 加粉阶段名称
     */
    public static final String JIA_FEN_TEXT="加粉";
    /**
     * 升温阶段名称
     */
    public static final String SHENG_WEN_TEXT="升温";
    /**
     * 二次加助剂阶段名称
     */
    public static final String ER_CI_JIA_ZHU_JI_TEXT="二次加助剂";
    /**
     * 降温阶段名称
     */
    public static final String JIANG_WEN_TEXT="降温";
    /**
     * 排胶阶段名称
     */
    public static final String PAI_JIAO_TEXT="排胶";

    private Integer id;//阶段id
    private String mc;//名称
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getMc() {
        return mc;
    }
    public void setMc(String mc) {
        this.mc = mc;
    }
}
