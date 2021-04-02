package SPLT_A4;

public class SPLT implements SPLT_Interface{
  private BST_Node root;
  private int size;
  
  public SPLT() {
    this.size = 0;
  } 
  
  public BST_Node getRoot() { //please keep this in here! I need your root node to test your tree!
    return root;
  }

  @Override
	public boolean insert(String s) { //It was me
		// TODO Auto-generated method stub
		if (empty()) {
			root = new BST_Node(s);
			size += 1;
			return true;
		}
		BST_Node contains = insertContains(s);
		if (contains.getData().equals(s)) {
			splay(contains);
			return false;
		} else {
			splay(root.insertNode(s));
			size += 1;
			return true;
		}
	}
  	
  	private BST_Node insertContains(String s) {
		// TODO Auto-generated method stub
		if (empty()) {
			return null;
		}
		return root.containsNode(s);
	} 

	@Override
	public boolean remove(String s) {  //DIO
		// TODO Auto-generated method stub
		if (!contains(s)) {
			return false;
		} else if (size == 1) {
			root = null;
			size--;
			return true;
		} else {
			if (root.right == null) {
				root = root.left;
				root.par = null;
				size--;
				return true;
			}
			BST_Node r = root.right;
			if (root.left == null) {
				root = r;
				root.par = null;
				size--;
				return true;
			}
			BST_Node l = root.left;
			root = null; l.par = null; r.par = null; //detach the root (s)
			root = l;
			findMax();
			root.right = r;
			root.right.par = root;
			size--;
			return true;
		}
	}

	@Override
	public String findMin() {
		// TODO Auto-generated method stub
		if (empty()) {
			return null;
		} else {
			BST_Node min = root.findMin();
			splay(min);
			return min.data;
		}
	}

	@Override
	public String findMax() {
		// TODO Auto-generated method stub
		if (empty()) {
			return null;
		} else {
			BST_Node max = root.findMax();
			splay(max);
			return max.data;
		}
	}

	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean contains(String s) {
		// TODO Auto-generated method stub
		if (empty()) {
			return false;
		}
		BST_Node r = root.containsNode(s);
		if (r.getData().equals(s)) {
			splay(r);
			return true;
		} else {
			splay(r);
			return false;
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		if (empty()) {
			return -1;
		} else {
			return root.getHeight();
		}
	}
	
	private void splay(BST_Node toSplay) {
		if (root == toSplay) {
			return;
		} else {
			toSplay.splay(toSplay);
		}
		if (toSplay.par == null) {
			root = toSplay;
		}
	}

}