// Burgard Lu (jl4nq)
// HW 8
// Sources:
// Professor Basit's OH and lectures
// TA's OH
// Piazza

public class BinaryTreeNode<T> {

	private BinaryTreeNode<T> left;
	private BinaryTreeNode<T> right;
	private T data;

	public BinaryTreeNode(){
		this(null,null,null);
	}

	public BinaryTreeNode(T theData){
		this(theData,null,null);
	}

	public BinaryTreeNode(T theData, BinaryTreeNode<T> leftChild, BinaryTreeNode<T> rightChild){
		data = theData;
		left = leftChild;
		right = rightChild;
	}

	public int size(){
		int size = 0; //the size of the tree

		//The size of the tree rooted at this node is one more than the
		//sum of the sizes of its children.
		if(left != null){
			size = size + left.size();
		}
		if(right != null){
			size = size + right.size();
		}
		return size + 1; 
	}

	public BinaryTreeNode<T> getLeft() {
		return left;
	}

	public void setLeft(BinaryTreeNode<T> left) {
		this.left = left;
	}

	public BinaryTreeNode<T> getRight() {
		return right;
	}

	public void setRight(BinaryTreeNode<T> right) {
		this.right = right;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}


	public BinaryTreeNode<T> deepCopy(){
		BinaryTreeNode<T> node = new BinaryTreeNode<T>(this.data);
		if (this.getLeft() != null) {
			node.setLeft(left.deepCopy());
		}
		if (this.getRight() != null) {
			node.setRight(right.deepCopy());
		}
		return node;
	}


	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BinaryTreeNode) {
			BinaryTreeNode<T> other = (BinaryTreeNode<T>) obj;
			if (this.getRight() == null && this.getLeft() == null) {
				if (this.getData() != null) {
					return (this.getData().equals(other.getData()));
				}
				else return true;
			}
			if (this.getLeft() != null && this.getRight() == null) {
				return (this.getData().equals(other.getData()) && this.getLeft().equals(other.getLeft()));
			}
			if (this.getRight() != null && this.getLeft() == null) {
				return (this.getData().equals(other.getData()) && this.getRight().equals(other.getRight()));
			}
			else 
				return (this.getRight().equals(other.getRight())) && (this.getLeft().equals(other.getLeft())) && (this.getData().equals(other.getData())); 
		}
		else
			return false;
	}

	public int height(){
		int height = 0;
		int leftHeight = 0;
		int rightHeight = 0;

		if (this.left != null) {
			leftHeight = this.left.height();
		}
		if (this.right != null) {
			rightHeight = this.right.height();
		}

		if (leftHeight > rightHeight) {
			height = 1 + leftHeight;
		} else {
			height = 1 + rightHeight;
		}

		return height;
	}

	public boolean full(){
		double fullSize = Math.pow(2, this.height()) - 1;
		if ((double)this.size() == fullSize) return true;
		else return false;

	}

	public void mirror(){
		BinaryTreeNode<T> temp = this.left;
		this.setLeft(this.right);
		this.setRight(temp);
		if (this.getLeft() != null) {
			this.getLeft().mirror();
		}
		if (this.getRight() != null) {
			this.getRight().mirror();
		}
	}

	public String inOrder(){
		String result = "";
		if (this.getLeft() != null) {
			result += this.getLeft().inOrder();
		}
		result += "(" + this.getData().toString() +")";
		if (this.getRight() != null) {
			result += this.getRight().inOrder();
		}
		return result;
	}

}
