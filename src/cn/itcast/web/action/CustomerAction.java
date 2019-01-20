package cn.itcast.web.action;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


import cn.itcast.domain.Customer;
import cn.itcast.service.CustomerService;
import cn.itcast.utils.PageBean;


public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	private Customer customer = new Customer();
	
	private CustomerService cs ;
	
	private File photo;
	private String photoFileName;
	
    private Integer currentPage;
    private Integer pageSize;
	
    
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setCs(CustomerService cs) {
		this.cs = cs;
	}

	public String list() throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
		if(StringUtils.isNotBlank(customer.getCust_name())){
			dc.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
		}
		
		PageBean pb = cs.getPageBean(dc,currentPage,pageSize);
		ActionContext.getContext().put("pagebean", pb);
		return "list";
	}
    
	
	public String add() throws Exception {
		photo.renameTo(new File("D:/wenjian"));
		
		cs.save(customer);
		return "toList";
	}
    public String toEdit() throws Exception{
        Customer c = cs.getById(customer.getCust_id());	
        ActionContext.getContext().put("customer", c);
        return "edit";
    }
    
    public String industryCount() throws Exception{
        List<Object[]> list = cs.getIndustryCount();
        ActionContext.getContext().put("list", list);
        return "industryCount";
    }
    
    
	@Override
	public Customer getModel() {
		return customer;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	
	
}

