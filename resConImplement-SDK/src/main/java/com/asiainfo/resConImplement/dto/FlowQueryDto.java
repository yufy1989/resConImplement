package com.asiainfo.resConImplement.dto;

/**
 * 类说明：流量查询传输对象
 * 
 * @author chencq
 * @date 2016年7月27日 下午18:47:23
 */
public class FlowQueryDto extends BaseDto {
	
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 6886834676642107884L;

	private ResultDto result;
	
	public ResultDto getResult() {
		return result;
	}

	public void setResult(ResultDto result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "FlowQueryDto [result=" + result + "]";
	}
}
