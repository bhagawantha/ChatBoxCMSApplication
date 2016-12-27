/**
 * 
 */
package com.chatbox.cms.entity;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Bhagawantha Parasuraman
 *
 */
public class TopicEntityTest {

	TopicEntity topicEntity = null;
	Date dat =new Date();

	@Before
	public void setUp() {
		topicEntity = new TopicEntity();
		topicEntity.setCreatedBy("System");
		topicEntity.setCreatedOn(dat);
		topicEntity.setTopicId(20);
	}

	@After
	public void tearDown() {
		topicEntity = null;
	}

	@Test
	public void getTopicIdTest1() {
		assertEquals(new Integer(20),topicEntity.getTopicId());
	}
	
	@Test
	public void getCreatedOnTest() {
		assertEquals(dat,topicEntity.getCreatedOn());
	}
	
	@Test
	public void getCreatedByTest() {
		assertEquals("System",topicEntity.getCreatedBy());
	}
}
