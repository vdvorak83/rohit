package com.rmicp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.rmicp.bo.PublicPost;

@Service("publicService")
public class PublicService {
	private Map<String, PublicPost> publicPosts = new HashMap<String, PublicPost>();

	public PublicService() {
		init();
	}

	public List<PublicPost> getAll() {
		List<PublicPost> publicList = new ArrayList<PublicPost>();
		for (String key : publicPosts.keySet()) {
			publicList.add(publicPosts.get(key));
		}
		return publicList;
	}

	public Boolean add(PublicPost post) {
		return true;
	}

	public Boolean edit(PublicPost post) {
		return true;
	}

	public Boolean delete(PublicPost post) {
		return true;
	}

	private void init() {
		// Create new post
		PublicPost post1 = new PublicPost();
		post1.setId(UUID.randomUUID().toString());
		post1.setDate(new Date());
		post1.setMessage("This is admin's post #1");

		// Create new post
		PublicPost post2 = new PublicPost();
		post2.setId(UUID.randomUUID().toString());
		post2.setDate(new Date());
		post2.setMessage("This is admin's post #2");

		// Create new post
		PublicPost post3 = new PublicPost();
		post3.setId(UUID.randomUUID().toString());
		post3.setDate(new Date());
		post3.setMessage("This is admin's post #3");

		// Add to adminPosts
		publicPosts.put(post1.getId(), post1);
		publicPosts.put(post2.getId(), post2);
		publicPosts.put(post3.getId(), post3);
	}
}
