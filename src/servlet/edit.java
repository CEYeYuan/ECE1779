package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class edit extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		String orderid=req.getParameter("orderid");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String mail = req.getParameter("mail");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String status = req.getParameter("status");
		String url="edit.jsp?orderid="+orderid;
		
	/*	SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String orderid = df.format(new Date()) + UUID.randomUUID();*/
		
		DatastoreService ds=DatastoreServiceFactory.getDatastoreService();
		//String user = req.getUserPrincipal().getName(); 
		
	/*	Entity e=new Entity("order");
		e.setProperty("name", name);
		e.setProperty("user", user);
		e.setProperty("orderid", orderid);
		e.setProperty("status", "pending");
		e.setUnindexedProperty("phone", phone);
		e.setUnindexedProperty("mail", mail);
		e.setUnindexedProperty("email", email);
		e.setUnindexedProperty("address", address);
		
		ds.put(e);*/
		
		
		Query q=new Query("order").addFilter("orderid", FilterOperator.EQUAL, orderid);
		PreparedQuery pq=ds.prepare(q);
		
		for(Entity u1:pq.asIterable()){
			
			//u1.setProperty("orderid", orderid);
			u1.setUnindexedProperty("address", address);
			u1.setUnindexedProperty("email", email);
			u1.setUnindexedProperty("name", name);
			u1.setUnindexedProperty("phone", phone);
			u1.setUnindexedProperty("status", status);
			
		}
		
		out.print("<script>alert('Update successfully!');</script>");
    	out.print("<script>window.location.href='"+url+"'</script>");
	}
}