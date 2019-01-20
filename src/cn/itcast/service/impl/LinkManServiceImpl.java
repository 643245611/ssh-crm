package cn.itcast.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.dao.LinkManDao;
import cn.itcast.domain.Customer;
import cn.itcast.domain.LinkMan;
import cn.itcast.service.LinkManService;
import cn.itcast.utils.PageBean;

public class LinkManServiceImpl implements LinkManService {
    private LinkManDao lmd;
	
    public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		// TODO Auto-generated method stub
		Integer totalcount = lmd.getTotalCount(dc);
		PageBean pb = new PageBean(currentPage,totalcount,pageSize);
		List<LinkMan> list= lmd.getPageList(dc,pb.getStart(),pb.getPageSize());
		pb.setList(list);
		return pb;
	}
    
    
    
    
    
	public void save(LinkMan linkman) {
		lmd.saveOrUpdate(linkman);

	}

	public LinkManDao getLmd() {
		return lmd;
	}

	public void setLmd(LinkManDao lmd) {
		this.lmd = lmd;
	}





	
	public LinkMan getById(Long lkm_id) {
		
		return lmd.getById(lkm_id);
	}
    
}
