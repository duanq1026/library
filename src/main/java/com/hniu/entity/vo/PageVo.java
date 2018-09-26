package com.hniu.entity.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @ClassName: PageUtil
 * @Description: 分页工具
 * @author yangf
 * @date 2018年4月29日 下午7:07:08
 * @version 1.0
 */
public final class PageVo<T> implements Serializable {
	
	private Integer page = 1; // 当前页
	private Integer rows = 6; //显示行数
	private Long total; //总记录数
	private Integer totalPage; //总页数
	private List<T> dateList; //封装数据
	private String keyword;

	public PageVo() {
	}

	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		if(page<1) {
			page=1;
		}
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		if(total%rows==0) {
			totalPage = Math.toIntExact((total / rows));
		}else {
			totalPage = Math.toIntExact(total / rows + 1);
		}
		if(page>totalPage&&totalPage>0) {
			page = totalPage;
		}
		this.total = total;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}



	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<T> getDateList() {
		return dateList;
	}

	public void setDateList(List<T> dateList) {
		this.dateList = dateList;
	}
}
