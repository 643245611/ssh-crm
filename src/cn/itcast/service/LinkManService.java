package cn.itcast.service;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.domain.LinkMan;
import cn.itcast.utils.PageBean;

public interface LinkManService {

	void save(LinkMan linkman);
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);
	LinkMan getById(Long lkm_id);
}
