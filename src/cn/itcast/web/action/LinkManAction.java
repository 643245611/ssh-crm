package cn.itcast.web.action;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


import cn.itcast.domain.Customer;
import cn.itcast.domain.LinkMan;
import cn.itcast.service.CustomerService;
import cn.itcast.service.LinkManService;
import cn.itcast.utils.PageBean;


public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
	private LinkMan linkMan = new LinkMan();
	
	private Integer currentPage;
    private Integer pageSize;
	
	private LinkManService lms ;
	
	public String list() throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(LinkMan.class);
		if(StringUtils.isNotBlank(linkMan.getLkm_name())){
			dc.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		}
		if(linkMan.getCustomer()!=null&&linkMan.getCustomer().getCust_id()!=null){
		dc.add(Restrictions.eq("customer.cust_id", linkMan.getCustomer().getCust_id()));
		}
		PageBean pb = lms.getPageBean(dc,currentPage,pageSize);
		ActionContext.getContext().put("pagebean", pb);
		return "list";
	}
    
	
	public String add() throws Exception {
		lms.save(linkMan);
		return "toList";
	}

	public String toEdit() throws Exception {
		LinkMan lm = lms.getById(linkMan.getLkm_id());
		ActionContext.getContext().put("linkMan", lm);
		return "add";
	}



	@Override
	public LinkMan getModel() {
		return linkMan;
	}



	public LinkManService getLms() {
		return lms;
	}



	public void setLms(LinkManService lms) {
		this.lms = lms;
	}


	public Integer getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}


	public Integer getPageSize() {
		return pageSize;
	}


	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	
	
	
}


