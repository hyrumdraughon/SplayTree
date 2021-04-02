package SPLT_A4;

public class BST_Node {
  String data;
  BST_Node left;
  BST_Node right;
  BST_Node par; //parent...not necessarily required, but can be useful in splay tree
  boolean justMade; //could be helpful if you change some of the return types on your BST_Node insert.
            //I personally use it to indicate to my SPLT insert whether or not we increment size.
  
  BST_Node(String data){ 
    this.data=data;
    this.justMade=true;
  }
  
  BST_Node(String data, BST_Node left,BST_Node right,BST_Node par){ //feel free to modify this constructor to suit your needs
    this.data=data;
    this.left=left;
    this.right=right;
    this.par=par;
    this.justMade=true;
  }

  // --- used for testing  ----------------------------------------------
  //
  // leave these 3 methods in, as is (meaning also make sure they do in fact return data,left,right respectively)

  public String getData(){ return data; }
  public BST_Node getLeft(){ return left; }
  public BST_Node getRight(){ return right; }

  // --- end used for testing -------------------------------------------
  
  public BST_Node containsNode(String s){ //it was me
		if(data.equals(s)) {
			return this;
		}
		if(data.compareTo(s)>0){//s lexiconically less than data
			if(left==null) {
				return this;
			}
			return left.containsNode(s);
		}
		if(data.compareTo(s)<0){
			if(right==null) {
				return this;
			}
			return right.containsNode(s);
		}
		return null; //shouldn't hit
	}
  
  public BST_Node insertNode(String s){
		if(data.compareTo(s)>0){
			if(left==null){
				left=new BST_Node(s);
				left.par = this;
				return left;
			}
			return left.insertNode(s);
		}
		if(data.compareTo(s)<0){
			if(right==null){
				right=new BST_Node(s);
				right.par = this;
				return right;
			}
			return right.insertNode(s);
		}
		return null;//ie we have a duplicate
	}
	
  public BST_Node findMin(){
		if(left!=null) {
			return left.findMin();
		} else {
			return this;
		}
		
	}
	
  public BST_Node findMax(){
		if(right!=null) {
			return right.findMax();
		} else {
			return this;
		}
		
	}
	
  public int getHeight(){
		int l=0;
		int r=0;
		if(left!=null)l+=left.getHeight()+1;
		if(right!=null)r+=right.getHeight()+1;
		return Integer.max(l, r);
	}
	
  public String toString(){
		return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")+",Right: "+((this.right!=null)?right.data:"null");
	}
  
  @SuppressWarnings("unused")
public void splay(BST_Node x) {
	  //splay
	  BST_Node p;
	  BST_Node g;
	  BST_Node n;
	  BST_Node xL;
	  BST_Node xR;
	  BST_Node pL;
	  BST_Node pR;
	  BST_Node gL;
	  BST_Node gR;
	  if (x.par != null) {
		  p = x.par;
	  } else {
		  return; //x is already the root
	  }
	  if (p.par != null) {
		  g = p.par;
		  if (g.par != null) {
			  n = g.par;
		  } else {
			  n = null;
		  }
		  //
		  if (g.left!=null) {gL=g.left;} else {gL=null;}
		  if (g.right!=null) {gR=g.right;} else {gR=null;}
	  } else {
		  g = null;
		  n = null;
	  }
	  //System.out.println("hey hey hey hey");
	  if (x.left!=null) {xL=x.left;} else {xL=null;}
	  if (x.right!=null) {xR=x.right;} else {xR=null;}
	  if (p.left!=null) {pL=p.left;} else {pL=null;}
	  if (p.right!=null) {pR=p.right;} else {pR=null;}
	  //if (g.left!=null) {gL=g.left;} else {gL=null;}
	  //if (g.right!=null) {gR=g.right;} else {gR=null;}
	  //System.out.println("hey hey hey hey");
	  //zig
	  if (g == null) {
		  //zigL and zigR
		  if (p.left == x) {
			  p.left = xR;
			  if(xR!=null) {x.right.par = p;}
			  x.right = p;
			  p.par = x;
			  x.par = null;
		  } else if (p.right == x) {
			  p.right = xL;
			  if(xL!=null) {x.left.par = p;}
			  x.left = p;
			  p.par = x;
			  x.par = null;
		  }		  
	  }
	  //zig-zig
	  else if (g.left == p && p.left == x) {
		  g.left = pR;
		  if(pR!=null) {p.right.par = g;}
		  p.right = g;
		  g.par = p;
		  p.left = xR;
		  if(xR!=null) {x.right.par = p;}
		  x.right = p;
		  p.par = x;
		  if (n != null) {
			  if (n.right == g) {
				  n.right = x;
				  x.par = n;
			  } else if (n.left == g) {
				  n.left = x;
				  x.par = n;
			  }
		  } else {
			  x.par = null;
		  }
	  } else if (g.right == p && p.right == x) {
		  g.right = pL;
		  if(pL!=null) {p.left.par = g;}
		  p.left = g;
		  g.par = p;
		  p.right = xL;
		  if(xL!=null) {x.left.par = p;}
		  x.left = p;
		  p.par = x;
		  if (n != null) {
			  if (n.right == g) {
				  n.right = x;
				  x.par = n;
			  } else if (n.left == g) {
				  n.left = x;
				  x.par = n;
			  }
		  } else {
			  x.par = null;
		  }
	  }
	  //zig-zag
	  else if (g.left == p && p.right == x) {
		  p.right = xL;
		  if(xL!=null) {x.left.par = p;}
		  g.left = xR;
		  if(xR!=null) {x.right.par = g;}
		  x.left = p;
		  p.par = x;
		  x.right = g;
		  g.par = x;
		  if (n != null) {
			  if (n.right == g) {
				  n.right = x;
				  x.par = n;
			  } else if (n.left == g) {
				  n.left = x;
				  x.par = n;
			  }
		  } else {
			  x.par = null;
		  }
	  } else if (g.right == p && p.left == x) {
		  p.left = xR;
		  if(xR!=null) {x.right.par = p;}
		  g.right = xL;
		  if(xL!=null) {x.left.par = g;}
		  x.left = g;
		  g.par = x;
		  x.right = p;
		  p.par = x;
		  if (n != null) {
			  if (n.right == g) {
				  n.right = x;
				  x.par = n;
			  } else if (n.left == g) {
				  n.left = x;
				  x.par = n;
			  }
		  } else {
			  x.par = null;
		  }
	  }
	  //
	  if (x.par == null) {
		  return;
	  } else {
		  splay(x);
	  }
  }

  
  // --- Some example methods that could be helpful ------------------------------------------
  //
  // add the meat of correct implementation logic to them if you wish

  // you MAY change the signatures if you wish...names too (we will not grade on delegation for this assignment)
  // make them take more or different parameters
  // have them return different types
  //
  // you may use recursive or iterative implementations

  /*
  public BST_Node containsNode(String s){ return false; } //note: I personally find it easiest to make this return a Node,(that being the node splayed to root), you are however free to do what you wish.
  public BST_Node insertNode(String s){ return false; } //Really same logic as above note
  public boolean removeNode(String s){ return false; } //I personal do not use the removeNode internal method in my impl since it is rather easily done in SPLT, feel free to try to delegate this out, however we do not "remove" like we do in BST
  public BST_Node findMin(){ return left; } 
  public BST_Node findMax(){ return right; }
  public int getHeight(){ return 0; }

  private void splay(BST_Node toSplay) { return false; } //you could have this return or take in whatever you want..so long as it will do the job internally. As a caller of SPLT functions, I should really have no idea if you are "splaying or not"
                        //I of course, will be checking with tests and by eye to make sure you are indeed splaying
                        //Pro tip: Making individual methods for rotateLeft and rotateRight might be a good idea!
  */

  // --- end example methods --------------------------------------

  
  

  // --------------------------------------------------------------------
  // you may add any other methods you want to get the job done
  // --------------------------------------------------------------------
  
  
}