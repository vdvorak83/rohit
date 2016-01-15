package com.rmicp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.rmicp.bo.AdminPost;
import com.rmicp.bo.PersonalPost;

@Service("personalService")
public class PersonalService {
	private Map<String, PersonalPost> personalPosts = new HashMap<String, PersonalPost>();

	public PersonalService() {
		init();
	}

	public List<PersonalPost> getAll() {
		List<PersonalPost> personalList = new ArrayList<PersonalPost>();
		for (String key : personalPosts.keySet()) {
			personalList.add(personalPosts.get(key));
		}
		return personalList;
	}

	public Boolean add(PersonalPost post) {
		return true;
	}

	public Boolean edit(PersonalPost post) {
		return true;
	}

	public Boolean delete(PersonalPost post) {
		return true;
	}

	private void init() {
		// Create new post
		PersonalPost post1 = new PersonalPost();
		post1.setId(UUID.randomUUID().toString());
		post1.setDate(new Date());
		post1.setMessage("This is admin's post #1");

		// Create new post
		PersonalPost post2 = new PersonalPost();
		post2.setId(UUID.randomUUID().toString());
		post2.setDate(new Date());
		post2.setMessage("This is admin's post #2");

		// Create new post
		PersonalPost post3 = new PersonalPost();
		post3.setId(UUID.randomUUID().toString());
		post3.setDate(new Date());
		post3.setMessage("This is admin's post #3");

		// Add to adminPosts
		personalPosts.put(post1.getId(), post1);
		personalPosts.put(post2.getId(), post2);
		personalPosts.put(post3.getId(), post3);
	}
}
