package com.uWinOPCTjyx.entity;

public class OpcBianLiang {

    private Integer id;//变量id
    private String mc;//变量名称
    private String sz;//数值
	private String cjsj;//创建时间
    private String xgsj;//修改时间

    public OpcBianLiang() {
    }

    public OpcBianLiang(Integer id, String mc, String sz, String cjsj, String xgsj) {
        this.id = id;
        this.mc = mc;
        this.sz = sz;
        this.cjsj = cjsj;
        this.xgsj = xgsj;
    }

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

    public String getSz() {
		return sz;
	}

	public void setSz(String sz) {
		this.sz = sz;
	}

    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }

    public String getXgsj() {
        return xgsj;
    }

    public void setXgsj(String xgsj) {
        this.xgsj = xgsj;
    }

    @Override
    public String toString() {
        return "OpcBianliang{" +
                "id=" + id +
                ", mc='" + mc + '\'' +
                ", sz='" + sz + '\'' +
                ", cjsj='" + cjsj + '\'' +
                ", xgsj='" + xgsj + '\'' +
                '}';
    }
}
