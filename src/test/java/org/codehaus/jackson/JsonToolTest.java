package org.codehaus.jackson;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

/**
 * depend jars: jackson-core-asl-1.9.2.jar jackson-mapper-asl-1.9.2.jar
 * 
 * @author jasonyao
 * 
 */
public class JsonToolTest {

	@Test
	public void beanToJsonString() throws Exception {
		OutputStream os = new ByteArrayOutputStream();
		UserBean bean = prepareUserBean();
		JsonGenerator generator = new ObjectMapper().getJsonFactory().createJsonGenerator(os, JsonEncoding.UTF8);
		generator.writeObject(bean);
		System.out.println(os.toString());
		if (generator != null) {
			generator.close();
		}
		if (os != null) {
			os.close();
		}
	}

	@Test
	public void jsonStringToBean() throws Exception {
		String json = "{\"address\":\"singapore\",\"name\":\"jason\",\"id\":1,\"email\":\"jason.yao525@gmail.com\",\"hoppy\":[\"reading\",\"playing\"]"
				+ ",\"roles\":[{\"rolename\":\"query\",\"rolecode\":\"q\"},{\"rolename\":\"delete\",\"rolecode\":\"d\"}]}";
		ObjectMapper mapper = new ObjectMapper();
		UserBean bean = mapper.readValue(json, UserBean.class);
		Assert.assertEquals("singapore", bean.getAddress());
		Assert.assertEquals("jason", bean.getName());
		Assert.assertEquals(1, bean.getId());
		Assert.assertEquals("jason.yao525@gmail.com", bean.getEmail());
		Assert.assertEquals("reading", bean.getHoppy()[0]);
		Assert.assertEquals("playing", bean.getHoppy()[1]);
		Assert.assertEquals("delete", bean.getRoles().get(1).getRolename());
	}

	private UserBean prepareUserBean() {
		UserBean bean = new UserBean();
		bean.setAddress("singapore");
		bean.setEmail("jason.yao525@gmail.com");
		bean.setHoppy(new String[] { "reading", "playing" });
		bean.setId(1);
		bean.setName("jason");
		// prepare list of roles
		List<Role> roles = new ArrayList<Role>();
		Role role1 = new Role();
		role1.setRolename("query");
		role1.setRolecode("q");
		Role role2 = new Role();
		role2.setRolename("delete");
		role2.setRolecode("d");
		roles.add(role1);
		roles.add(role2);
		bean.setRoles(roles);
		return bean;
	}

}

class UserBean {
	private int id;
	private String name;
	private String address;
	private String email;
	private String[] hoppy;
	private List<Role> roles;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String[] getHoppy() {
		return hoppy;
	}

	public void setHoppy(String[] hoppy) {
		this.hoppy = hoppy;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}

class Role {
	private String rolename;
	private String rolecode;

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

}