package com.rmicp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.rmicp.bo.AdminPost;

@Service("adminService")
public class AdminService {
	private Map<String, AdminPost> adminPosts = new HashMap<String, AdminPost>();

	public AdminService() {
		init();
	}

	@PostFilter("hasPermission(filterObject,'READ')")
	public List<AdminPost> getAll() {
		List<AdminPost> adminList = new ArrayList<AdminPost>();
		for (String key : adminPosts.keySet()) {
			adminList.add(adminPosts.get(key));
		}
		return adminList;
	}

	@PreAuthorize("hasPermission(#post, 'WRITE')")
	public Boolean add(AdminPost post) {
		return true;
	}

	public Boolean edit(AdminPost post) {
		return true;
	}

	public Boolean delete(AdminPost post) {
		return true;
	}

	private void init() {
		// Create new post
		AdminPost post1 = new AdminPost();
		post1.setId(UUID.randomUUID().toString());
		post1.setDate(new Date());
		post1.setMessage("This is admin's post #1");

		// Create new post
		AdminPost post2 = new AdminPost();
		post2.setId(UUID.randomUUID().toString());
		post2.setDate(new Date());
		post2.setMessage("This is admin's post #2");

		// Create new post
		AdminPost post3 = new AdminPost();
		post3.setId(UUID.randomUUID().toString());
		post3.setDate(new Date());
		post3.setMessage("This is admin's post #3");

		// Add to adminPosts
		adminPosts.put(post1.getId(), post1);
		adminPosts.put(post2.getId(), post2);
		adminPosts.put(post3.getId(), post3);
	}
}
