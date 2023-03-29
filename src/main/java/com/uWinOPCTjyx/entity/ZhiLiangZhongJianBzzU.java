package com.uWinOPCTjyx.entity;

public class ZhiLiangZhongJianBzzU {

	private Integer id;//标准值id
	private Integer zlx;//值类型
	private Integer scjllx;//生产记录类型
	private Double bzzsx;//标准值上限
	private Double bzzxx;//标准值下限
	private String dw;//单位

	public ZhiLiangZhongJianBzzU() {
		
	}

	public ZhiLiangZhongJianBzzU(Integer id, Integer zlx, Integer scjllx, Double bzzsx, Double bzzxx, String dw) {
		this.id = id;
		this.zlx = zlx;
		this.scjllx = scjllx;
		this.bzzsx = bzzsx;
		this.bzzxx = bzzxx;
		this.dw = dw;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getZlx() {
		return zlx;
	}

	public void setZlx(Integer zlx) {
		this.zlx = zlx;
	}

	public Integer getScjllx() {
		return scjllx;
	}

	public void setScjllx(Integer scjllx) {
		this.scjllx = scjllx;
	}

	public Double getBzzsx() {
		return bzzsx;
	}

	public void setBzzsx(Double bzzsx) {
		this.bzzsx = bzzsx;
	}

	public Double getBzzxx() {
		return bzzxx;
	}

	public void setBzzxx(Double bzzxx) {
		this.bzzxx = bzzxx;
	}

	public String getDw() {
		return dw;
	}

	public void setDw(String dw) {
		this.dw = dw;
	}
}