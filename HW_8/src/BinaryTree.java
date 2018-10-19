// Burgard Lu (jl4nq)
// HW 8
// Sources:
// Professor Basit's OH and lectures
// TA's OH
// Piazza

public class BinaryTree<T> {

	private BinaryTreeNode<T> root;

	public BinaryTree() {
		this(null);
	}

	public BinaryTree(BinaryTreeNode<T> newRoot) {
		this.root = newRoot;
	}

	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	public void setRoot(BinaryTreeNode<T> root) {
		this.root = root;
	}


	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BinaryTree) {
			BinaryTree<T> other = (BinaryTree<T>) obj;
			if (this.getRoot() == null) {
				if (other.getRoot() != null) return false;
				else return true;
			}
			else
				return (this.getRoot().equals(other.getRoot())); 
		}
		else
			return false;
	}

	public BinaryTree<T> deepCopy() {
		BinaryTreeNode<T> newRoot = this.root.deepCopy();
		BinaryTree<T> t = new BinaryTree<T>(newRoot);
		return t;
	}

	public BinaryTree<T> combine(BinaryTreeNode<T> newRoot, BinaryTree<T> t,
			boolean left) {
		BinaryTree<T> tree = new BinaryTree<T>(newRoot.deepCopy());
		if (left == true) {
			tree.getRoot().setLeft(this.deepCopy().getRoot());
			tree.getRoot().setRight(t.deepCopy().getRoot());
		}
		else {
			tree.getRoot().setRight(this.deepCopy().getRoot());
			tree.getRoot().setLeft(t.deepCopy().getRoot());
		}
		return tree;
	}

	public int size() {
		if (root != null) {
			return root.size();
		}
		else
			return 0;
	}

	public int height(){
		if (root != null) {
			return root.height();
		}
		else
			return 0;
	}

	public boolean full(){
		if (root != null) {
			return root.full();
		}
		return true;
	}

	public void mirror(){
		if (this.root!=null) {
			root.mirror();
		}
	}

	public String inOrder(){
		String s = "";
		if (this.root != null) {
			s += this.root.inOrder();
		}
		return s;
	}
}
