package cn.itcast.web.action;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.domain.Customer;
import cn.itcast.domain.SaleVisit;
import cn.itcast.domain.User;
import cn.itcast.service.SaleVisitService;
import cn.itcast.utils.PageBean;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {

	private SaleVisit saleVisit = new SaleVisit();
    private SaleVisitService svs;
	
    private Integer currentPage;
    private Integer pageSize;
	
    public String list() throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(SaleVisit.class);
		if(saleVisit.getCustomer()!=null&&saleVisit.getCustomer().getCust_id()!=null){
			dc.add(Restrictions.eq("customer.cust_id", saleVisit.getCustomer().getCust_id()));
		}
		
		PageBean pb = svs.getPageBean(dc,currentPage,pageSize);
		ActionContext.getContext().put("pagebean", pb);
		return "list";
	}
	
	public String add() throws Exception {
		User u = (User) ActionContext.getContext().getSession().get("user");
		saleVisit.setUser(u);
		svs.save(saleVisit);
		return "toList";
	}

   public String toEdit() throws Exception{
	   SaleVisit sv = svs.getById(saleVisit.getVisit_id());
	   ActionContext.getContext().put("saleVisit", sv);
	   return "add";
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

	@Override
	public SaleVisit getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setSvs(SaleVisitService svs) {
		this.svs = svs;
	}
	
}
