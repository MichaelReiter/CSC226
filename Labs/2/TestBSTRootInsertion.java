public class TestBSTRootInsertion {

	public static void main(String[] args) {
		BSTRootInsertion<Integer> bst = new BSTRootInsertion<Integer>();
		
		bst.put(10);
		bst.put(5);
		bst.put(12);
		
		System.out.println(bst.toString());
	}
}
