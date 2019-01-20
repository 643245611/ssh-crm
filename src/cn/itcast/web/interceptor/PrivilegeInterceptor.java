package cn.itcast.web.interceptor;

import java.util.Map;

import org.aopalliance.intercept.Invocation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.itcast.domain.User;

public class PrivilegeInterceptor extends MethodFilterInterceptor {

	
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		 User user = (User) session.get("user");
		 if(user!=null){
			 return invocation.invoke();
		 }else{
			 return "toLogin";
		 }
		
	}

}
