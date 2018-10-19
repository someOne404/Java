//Burgard Lu (jl4nq)
//HW 8
//Sources:
//Professor Basit's OH and lectures
//TA's OH
//Piazza

import static org.junit.Assert.*;

import org.junit.Test;

public class TreeTest {

	//BinaryTree Test
	@Test
	public void testTreeEquals() {
		BinaryTreeNode<Integer> root1 = new BinaryTreeNode<Integer>(3);
		BinaryTreeNode<Integer> root2 = new BinaryTreeNode<Integer>(4);
		BinaryTreeNode<Integer> root3 = new BinaryTreeNode<Integer>(4);
		BinaryTree<Integer> tree1 = new BinaryTree<Integer>(root1);
		BinaryTree<Integer> tree2 = new BinaryTree<Integer>(root2);
		BinaryTree<Integer> tree3 = new BinaryTree<Integer>(root3);

		assertFalse(tree1.equals(tree2));
		assertTrue(tree2.equals(tree3));
	}

	@Test
	public void testTreeDeepCopy() {
		BinaryTreeNode<Integer> root1 = new BinaryTreeNode<Integer>(1);
		BinaryTreeNode<Integer> root2 = new BinaryTreeNode<Integer>(4);
		BinaryTreeNode<Integer> left1 = new BinaryTreeNode<Integer>(2);
		BinaryTreeNode<Integer> right1 = new BinaryTreeNode<Integer>(3);
		BinaryTree<Integer> tree1 = new BinaryTree<Integer>(root1); 
		BinaryTree<Integer> tree2 = new BinaryTree<Integer>(root2);
		tree2.getRoot().setLeft(left1);
		tree2.getRoot().setRight(right1);

		BinaryTree<Integer> tree3 = tree1.deepCopy();
		BinaryTree<Integer> tree4 = tree2.deepCopy();

		assertEquals(tree3, tree1);
		assertEquals(tree4, tree2);
		assertNotSame(tree3, tree1);
		assertNotSame(tree4, tree2);


	}

	@Test
	public void testTreeCombine() {
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(1);
		BinaryTreeNode<Integer> copy_root = new BinaryTreeNode<Integer>(1);
		BinaryTreeNode<Integer> root1 = new BinaryTreeNode<Integer>(4);
		BinaryTreeNode<Integer> root2 = new BinaryTreeNode<Integer>(5);
		BinaryTree<Integer> tree1 = new BinaryTree<Integer>(root1);
		BinaryTree<Integer> tree2 = new BinaryTree<Integer>(root2);
		
		
		BinaryTree<Integer> combinedTree1 = tree1.combine(root, tree2, true);
		BinaryTree<Integer> combinedTree2 = tree1.combine(copy_root, tree2, false);
		BinaryTree<Integer> newTree1 = new BinaryTree<Integer>(root);
		BinaryTree<Integer> newTree2 = new BinaryTree<Integer>(copy_root);

		newTree1.getRoot().setLeft(root1);
		newTree1.getRoot().setRight(root2);

		newTree2.getRoot().setLeft(root2);
		newTree2.getRoot().setRight(root1);

		assertEquals(combinedTree1, newTree1);
		assertEquals(combinedTree2, newTree2);

	}

	@Test
	public void testTreeSize() {
		BinaryTreeNode<Integer> root1 = new BinaryTreeNode<Integer>(4);
		BinaryTreeNode<Integer> root2 = new BinaryTreeNode<Integer>(5);
		BinaryTree<Integer> tree1 = new BinaryTree<Integer>(root1);
		BinaryTree<Integer> tree2 = new BinaryTree<Integer>(root2);

		BinaryTreeNode<Integer> left1 = new BinaryTreeNode<Integer>(4);
		BinaryTreeNode<Integer> right1 = new BinaryTreeNode<Integer>(6);

		tree2.getRoot().setLeft(left1);
		tree2.getRoot().setRight(right1);
		int size1 = tree1.size();
		int size2 = tree2.size();

		assertEquals(size1, 1);
		assertEquals(size2, 3);

	}

	@Test
	public void testTreeHeight() {
		BinaryTreeNode<Integer> root1 = new BinaryTreeNode<Integer>(4);
		BinaryTreeNode<Integer> root2 = new BinaryTreeNode<Integer>(5);
		BinaryTree<Integer> tree1 = new BinaryTree<Integer>(root1);
		BinaryTree<Integer> tree2 = new BinaryTree<Integer>(root2);

		BinaryTreeNode<Integer> left1 = new BinaryTreeNode<Integer>(4);
		BinaryTreeNode<Integer> right1 = new BinaryTreeNode<Integer>(6);

		tree2.getRoot().setLeft(left1);
		tree2.getRoot().setRight(right1);
		int height1 = tree1.height();
		int height2 = tree2.height();

		assertEquals(height1, 1);
		assertEquals(height2, 2);

	}

	@Test
	public void testTreeFull() {
		BinaryTreeNode<Integer> root1 = new BinaryTreeNode<Integer>(1);
		BinaryTreeNode<Integer> root2 = new BinaryTreeNode<Integer>(3);
		BinaryTree<Integer> tree1 = new BinaryTree<Integer>(root1);
		BinaryTree<Integer> tree2 = new BinaryTree<Integer>(root2);

		BinaryTreeNode<Integer> left1 = new BinaryTreeNode<Integer>(4);
		BinaryTreeNode<Integer> right1 = new BinaryTreeNode<Integer>(6);

		tree1.getRoot().setLeft(left1);
		tree2.getRoot().setLeft(left1);
		tree2.getRoot().setRight(right1);

		assertFalse(tree1.full());
		assertTrue(tree2.full());

	}

	@Test
	public void testTreeMirror() {
		BinaryTreeNode<Integer> root1 = new BinaryTreeNode<Integer>(2);
		BinaryTreeNode<Integer> root2 = new BinaryTreeNode<Integer>(3);

		BinaryTree<Integer> tree1 = new BinaryTree<Integer>(root1);
		BinaryTree<Integer> tree2 = new BinaryTree<Integer>(root1);
		BinaryTree<Integer> tree3 = new BinaryTree<Integer>(root2);
		BinaryTree<Integer> tree4 = new BinaryTree<Integer>(root2);

		BinaryTreeNode<Integer> left1 = new BinaryTreeNode<Integer>(4);
		BinaryTreeNode<Integer> left2 = new BinaryTreeNode<Integer>(7);
		BinaryTreeNode<Integer> right1 = new BinaryTreeNode<Integer>(6);
		BinaryTreeNode<Integer> right2 = new BinaryTreeNode<Integer>(8);

		tree1.getRoot().setLeft(left1);
		tree1.getRoot().setRight(right1);

		tree2.getRoot().setRight(left1);
		tree2.getRoot().setLeft(right1);

		tree3.getRoot().setLeft(left2);
		tree3.getRoot().setRight(right2);

		tree4.getRoot().setRight(left2);
		tree4.getRoot().setLeft(right2);

		tree1.mirror();
		tree3.mirror();
		assertTrue(tree2.equals(tree1));
		assertTrue(tree4.equals(tree3));

	}

	@Test
	public void testTreeInOrder() {
		BinaryTreeNode<Integer> root1 = new BinaryTreeNode<Integer>(2);
		BinaryTreeNode<Integer> root2 = new BinaryTreeNode<Integer>(3);
		BinaryTree<Integer> tree1 = new BinaryTree<Integer>(root1);
		BinaryTree<Integer> tree2 = new BinaryTree<Integer>(root2);

		BinaryTreeNode<Integer> left1 = new BinaryTreeNode<Integer>(4);
		BinaryTreeNode<Integer> left2 = new BinaryTreeNode<Integer>(7);
		BinaryTreeNode<Integer> left3 = new BinaryTreeNode<Integer>(10);
		BinaryTreeNode<Integer> right1 = new BinaryTreeNode<Integer>(6);
		BinaryTreeNode<Integer> right2 = new BinaryTreeNode<Integer>(8);

		tree1.getRoot().setLeft(left1);
		tree1.getRoot().setRight(right1);

		tree2.getRoot().setLeft(left2);
		tree2.getRoot().setRight(right2);
		left2.setLeft(left3);

		String s1 = tree1.inOrder();
		String s2 = tree2.inOrder();
		assertEquals(s1, "(4)(2)(6)");
		assertEquals(s2, "(10)(7)(3)(8)");

	}

	//BinaryTreeNode Test
	@Test
	public void testTreeNodeEquals() {
		BinaryTreeNode<Integer> left1 = new BinaryTreeNode<Integer>(4);
		BinaryTreeNode<Integer> left2 = new BinaryTreeNode<Integer>(4);
		BinaryTreeNode<Integer> right1 = new BinaryTreeNode<Integer>(5);
		BinaryTreeNode<Integer> right2 = new BinaryTreeNode<Integer>(5);

		BinaryTreeNode<Integer> node1 = new BinaryTreeNode<Integer>(3, left1, right1);
		BinaryTreeNode<Integer> node2 = new BinaryTreeNode<Integer>(4, left1, right1);
		BinaryTreeNode<Integer> node3 = new BinaryTreeNode<Integer>(4, left2, right2);

		assertFalse(node1.equals(node2));
		assertTrue(node2.equals(node3));
	}

	@Test
	public void testTreeNodeDeepCopy() {
		BinaryTreeNode<Integer> node1 = new BinaryTreeNode<Integer>(2);
		BinaryTreeNode<Integer> node2 = new BinaryTreeNode<Integer>();
		BinaryTreeNode<Integer> node3 = node1.deepCopy();
		BinaryTreeNode<Integer> node4 = node2.deepCopy();

		assertEquals(node1, node3);
		assertEquals(node2, node4);
		assertNotSame(node1, node3);
		assertNotSame(node2, node4);
	}

	@Test
	public void testTreeNodeSize() {
		BinaryTreeNode<Integer> root1 = new BinaryTreeNode<Integer>(4);
		BinaryTreeNode<Integer> root2 = new BinaryTreeNode<Integer>(5);

		BinaryTreeNode<Integer> left1 = new BinaryTreeNode<Integer>(7);
		BinaryTreeNode<Integer> left2 = new BinaryTreeNode<Integer>(8);
		BinaryTreeNode<Integer> right1 = new BinaryTreeNode<Integer>(9);

		root1.setLeft(left1);
		root1.setRight(right1);
		right1.setLeft(left2);
		root2.setLeft(left1);
		int size1 = root1.size();
		int size2 = root2.size();

		assertEquals(size1, 4);
		assertEquals(size2, 2);
	}

	@Test
	public void testTreeNodeHeight() {
		BinaryTreeNode<Integer> root1 = new BinaryTreeNode<Integer>(4);
		BinaryTreeNode<Integer> root2 = new BinaryTreeNode<Integer>(5);

		BinaryTreeNode<Integer> left1 = new BinaryTreeNode<Integer>(4);
		BinaryTreeNode<Integer> left2 = new BinaryTreeNode<Integer>(7);
		BinaryTreeNode<Integer> right1 = new BinaryTreeNode<Integer>(6);

		root1.setLeft(left1);
		left1.setLeft(left2);
		root1.setRight(right1);
		int height1 = root1.height();
		int height2 = root2.height();

		assertEquals(height1, 3);
		assertEquals(height2, 1);
	}

	@Test
	public void testTreeNodeFull() { 
		BinaryTreeNode<Integer> root1 = new BinaryTreeNode<Integer>(2);
		BinaryTreeNode<Integer> root2 = new BinaryTreeNode<Integer>(5);

		BinaryTreeNode<Integer> left1 = new BinaryTreeNode<Integer>(4);
		BinaryTreeNode<Integer> left2 = new BinaryTreeNode<Integer>(7);
		BinaryTreeNode<Integer> left3 = new BinaryTreeNode<Integer>(9);
		BinaryTreeNode<Integer> right1 = new BinaryTreeNode<Integer>(6);

		root1.setLeft(left1);
		root1.setLeft(left1);
		left1.setLeft(left3);

		root2.setLeft(left2);
		root2.setRight(right1);

		assertFalse(root1.full());
		assertTrue(root2.full()); 
	}

	@Test
	public void testTreeNodeMirror() {
		BinaryTreeNode<Integer> root1 = new BinaryTreeNode<Integer>(2);
		BinaryTreeNode<Integer> root2 = new BinaryTreeNode<Integer>(2);

		BinaryTreeNode<Integer> root3 = new BinaryTreeNode<Integer>(4);
		BinaryTreeNode<Integer> root4 = new BinaryTreeNode<Integer>(4);

		BinaryTreeNode<Integer> left1 = new BinaryTreeNode<Integer>(5);
		BinaryTreeNode<Integer> right1 = new BinaryTreeNode<Integer>(6);

		BinaryTreeNode<Integer> left2 = new BinaryTreeNode<Integer>(7);
		BinaryTreeNode<Integer> reverseLeft2 = new BinaryTreeNode<Integer>(7);
		BinaryTreeNode<Integer> left3 = new BinaryTreeNode<Integer>(10);
		BinaryTreeNode<Integer> right2 = new BinaryTreeNode<Integer>(8);
		BinaryTreeNode<Integer> right3 = new BinaryTreeNode<Integer>(10);


		root1.setLeft(left1);
		root1.setRight(right1);
		root2.setLeft(right1);
		root2.setRight(left1);

		root3.setLeft(left2);
		left2.setLeft(left3);
		root3.setRight(right2);


		root4.setLeft(right2);
		root4.setRight(reverseLeft2);
		reverseLeft2.setRight(right3);


		root1.mirror();
		root3.mirror();
		assertTrue(root2.equals(root1));
		assertTrue(root4.equals(root3));

	}

	@Test
	public void testTreeNodeInOrder() {
		BinaryTreeNode<Integer> root1 = new BinaryTreeNode<Integer>(1);
		BinaryTreeNode<Integer> root2 = new BinaryTreeNode<Integer>(2);	
		BinaryTreeNode<Integer> left1 = new BinaryTreeNode<Integer>(3);
		BinaryTreeNode<Integer> left2 = new BinaryTreeNode<Integer>(4);
		BinaryTreeNode<Integer> left3 = new BinaryTreeNode<Integer>(5);
		BinaryTreeNode<Integer> right1 = new BinaryTreeNode<Integer>(6);
		BinaryTreeNode<Integer> right2 = new BinaryTreeNode<Integer>(7);

		root1.setLeft(left1);
		root1.setRight(right1);

		root2.setLeft(left2);
		root2.setRight(right2);
		left2.setLeft(left3);

		String s1 = root1.inOrder();
		String s2 = root2.inOrder();
		assertEquals(s1, "(3)(1)(6)");
		assertEquals(s2, "(5)(4)(2)(7)");
	}

}
