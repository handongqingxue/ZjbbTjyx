package com.zjbbTjyx.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PlanResult {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//����jackson����  
    private static final ObjectMapper MAPPER = new ObjectMapper();  
    private Boolean success; 
	//��Ӧҵ��״̬ 
    private Integer status;  
    //��Ӧ��Ϣ  
    private String msg;  
    //��Ӧ�е�����  
    private Object data;  
    
    private String url;
  
    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static ObjectMapper getMAPPER() {  
        return MAPPER;  
    }
	 
    public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "PlanResult [status=" + status + ", msg=" + msg + ", data=" + data + ", url=" + url + "]";
	}  
}
