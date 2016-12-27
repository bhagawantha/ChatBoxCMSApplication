package com.chatbox.cms.entity;


/**
 * @author Bhagawantha Parasuraman
 *
 */

	import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

	public class TopicAttachmentEntityTest
	{
		
		TopicAttachmentEntity instance = null;
		@Before
		public void setUp()
		{
			instance = new TopicAttachmentEntity();
		}

		@After
		public void tearDown()
		{
			instance = null;
		}

		/*
		 * Testing Conditon(s): Default
		 */
		@Test
		public void test_method_TopicAttachmentEntity_0_branch_0()
		{
			System.out.println("Now Testing Method:TopicAttachmentEntity Branch:0");
			
			//Constructor
			TopicAttachmentEntity instance = new TopicAttachmentEntity();
			
			//Check Test Verification Points
			assertEquals(instance, instance);
			
		}

		/*
		 * Testing Conditon(s): Default
		 */
		@Test
		public void test_method_TopicAttachmentEntity_1_branch_0()
		{
			System.out.println("Now Testing Method:TopicAttachmentEntity Branch:0");
			
			//Constructor
			TopicAttachmentEntity instance1 = new TopicAttachmentEntity(new TopicEntity(), "D:/my.txt", "system", new Date(), "SYSTEM", new Date());
			
			//Check Test Verification Points
			assertEquals(instance1, instance1);
			
		}

		/*
		 * Testing Conditon(s): Default
		 */
		@Test
		public void test_method_getAttachmentId_2_branch_0()
		{
			System.out.println("Now Testing Method:getAttachmentId Branch:0");
			
			//Constructor
			TopicAttachmentEntity instance = new TopicAttachmentEntity(); 
			Integer attachmentId = 20;
			instance.setAttachmentId(attachmentId);
			//Check Return value
			assertEquals(attachmentId, instance.getAttachmentId());
		}
		/*
		 * Testing Conditon(s): Default
		 */
		@Test
		public void test_method_getTopicEntity_4_branch_0()
		{
			System.out.println("Now Testing Method:getTopicEntity Branch:0");
			
			//Constructor
			TopicAttachmentEntity instance = new TopicAttachmentEntity();
			TopicEntity te = new TopicEntity();
			instance.setTopicEntity(te);
			
			//Check Return value
			assertEquals(te, instance.getTopicEntity());
		}

		
		/*
		 * Testing Conditon(s): Default
		 */
		@Test
		public void test_method_getFileLocation_6_branch_0()
		{
			System.out.println("Now Testing Method:getFileLocation Branch:0");
			
			//Constructor
			TopicAttachmentEntity instance = new TopicAttachmentEntity();
			
			//Get expected result and result
			String result = "D:/Txt.txt";
			
			instance.setFileLocation(result);
			
			//Check Return value
			assertEquals(result, instance.getFileLocation());
		}


}