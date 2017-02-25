package com.project.my.avl;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AvlTreeTest {
	private AvlTreeA avlTree;

	@Before
	public void setUp() throws Exception {
		avlTree = new AvlTreeA();
	}

	@Test
	public void testInsert() {
		for (int i = 1; i < 10; i++) {
			avlTree.insert(i);
		}
	}

	// @Test
	// public void testDelete() {
	// fail("Not yet implemented");
	// }

	@Test
	public void testSearch() {
		testInsert();
		assertTrue(avlTree.search(3));
	}

}
