package cn.itcast.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao  {

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getIndustryCount() {
		List<Object[]> list = getHibernateTemplate().execute(new HibernateCallback<List>() {
            String sql = "select                             "+
            		     "bd.dict_item_name,                 "+
            		     "count(*) total                     "+
            		     "from                               "+
            		     "cat_customer c,                    "+
            		     "base_dict bd                       "+
            		     "where c.cust_industry = bd.dict_id "+
            		     "GROUP RY c.cust_industry           ";
			@Override
			public List doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				return query.list();
			}
		});
		
		
		return list;
	}

	

}
