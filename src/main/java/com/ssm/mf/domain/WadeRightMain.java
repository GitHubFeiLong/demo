/**
 * 
 */
package com.ssm.mf.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

/**   
 * @ClassName:  WadeRightMain   
 * @Description:TODO   
 * @author: cfl
 * @date:  2019年3月20日 上午9:12:34     
 */
@Alias("wadeRightMain")
public class WadeRightMain implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -47230230107063258L;
	private Integer wadeRight;    // 涉权总数
	private Integer assign;    // 交办
	private Integer inspect;    // 核查
	private Integer publicity;    // 公示
	private Integer illegal;    // 不合规处理
	private Integer mocratic;    // 说明
	public Integer getWadeRight() {
		return wadeRight;
	}
	public void setWadeRight(Integer wadeRight) {
		this.wadeRight = wadeRight;
	}
	public Integer getAssign() {
		return assign;
	}
	public void setAssign(Integer assign) {
		this.assign = assign;
	}
	public Integer getInspect() {
		return inspect;
	}
	public void setInspect(Integer inspect) {
		this.inspect = inspect;
	}
	public Integer getPublicity() {
		return publicity;
	}
	public void setPublicity(Integer publicity) {
		this.publicity = publicity;
	}
	public Integer getIllegal() {
		return illegal;
	}
	public void setIllegal(Integer illegal) {
		this.illegal = illegal;
	}
	public Integer getMocratic() {
		return mocratic;
	}
	public void setMocratic(Integer mocratic) {
		this.mocratic = mocratic;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assign == null) ? 0 : assign.hashCode());
		result = prime * result + ((illegal == null) ? 0 : illegal.hashCode());
		result = prime * result + ((inspect == null) ? 0 : inspect.hashCode());
		result = prime * result + ((mocratic == null) ? 0 : mocratic.hashCode());
		result = prime * result + ((publicity == null) ? 0 : publicity.hashCode());
		result = prime * result + ((wadeRight == null) ? 0 : wadeRight.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WadeRightMain other = (WadeRightMain) obj;
		if (assign == null) {
			if (other.assign != null)
				return false;
		} else if (!assign.equals(other.assign))
			return false;
		if (illegal == null) {
			if (other.illegal != null)
				return false;
		} else if (!illegal.equals(other.illegal))
			return false;
		if (inspect == null) {
			if (other.inspect != null)
				return false;
		} else if (!inspect.equals(other.inspect))
			return false;
		if (mocratic == null) {
			if (other.mocratic != null)
				return false;
		} else if (!mocratic.equals(other.mocratic))
			return false;
		if (publicity == null) {
			if (other.publicity != null)
				return false;
		} else if (!publicity.equals(other.publicity))
			return false;
		if (wadeRight == null) {
			if (other.wadeRight != null)
				return false;
		} else if (!wadeRight.equals(other.wadeRight))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "WadeRightMain [wadeRight=" + wadeRight + ", assign=" + assign + ", inspect=" + inspect + ", publicity="
				+ publicity + ", illegal=" + illegal + ", mocratic=" + mocratic + "]";
	}
	
	
	

}
