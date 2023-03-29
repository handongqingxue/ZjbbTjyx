package com.uWinOPCTjyx.entity;

public class OpcBianLiang {
	
	public static final int LX_M=1;//M类
	public static final int LX_U=2;//U类

    private Integer id;//变量id
    private String mc;//变量名称
    private String sz;//数值
    private Integer lx;//1 M类  2 U类
	private String cjsj;//创建时间
    private String xgsj;//修改时间

    public OpcBianLiang() {
    }

    public OpcBianLiang(Integer id, String mc, String sz, Integer lx, String cjsj, String xgsj) {
        this.id = id;
        this.mc = mc;
        this.sz = sz;
        this.lx = lx;
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

	public Integer getLx() {
		return lx;
	}

	public void setLx(Integer lx) {
		this.lx = lx;
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
                ", lx='" + lx + '\'' +
                ", cjsj='" + cjsj + '\'' +
                ", xgsj='" + xgsj + '\'' +
                '}';
    }
}
