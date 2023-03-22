package searchTree;

import java.util.Collection;

/**
 * This class is used to represent the empty search tree: a search tree that
 * contains no entries.
 * 
 * This class is a singleton class: since all empty search trees are the same,
 * there is no need for multiple instances of this class. Instead, a single
 * instance of the class is created and made available through the static field
 * SINGLETON.
 * 
 * The constructor is private, preventing other code from mistakenly creating
 * additional instances of the class.
 *  
 */
 public class EmptyTree<K extends Comparable<K>,V> implements Tree<K,V> {
	/**
	 * This static field references the one and only instance of this class.
	 * We won't declare generic types for this one, so the same singleton
	 * can be used for any kind of EmptyTree.
	 */
	private static EmptyTree SINGLETON = new EmptyTree();

	public static  <K extends Comparable<K>, V> EmptyTree<K,V> getInstance() {
		return SINGLETON;
	}

	/**
	 * Constructor is private to enforce it being a singleton
	 *  
	 */
	private EmptyTree() {
		// Nothing to do
	}
	
	//This method return null sicne the tree is empty.
	public V search(K key) {
		return null;
	}
	
	//This returns a NonEmptyTree in place of the empty tree.
	public NonEmptyTree<K, V> insert(K key, V value) {
		NonEmptyTree<K,V> newTree = new NonEmptyTree<K, V>(key, value, this, this);
		return newTree;
	}

	//Since the tree is empty, the current tree is just returned
	public Tree<K, V> delete(K key) {
		return this;
	}
	
	//This throws a TreeIsEmptyException since there is no max value in an empty tree.
	public K max() throws TreeIsEmptyException {
		throw new TreeIsEmptyException();
	}
	
	//This throws a TreeIsEmptyException since there is no minimum value in an empty tree.
	public K min() throws TreeIsEmptyException {
		throw new TreeIsEmptyException();
	}

	//Returns 0 as the tree is empty.
	public int size() {
		return 0;
	}

	//This does nothing since the tree is empty.
	public void addKeysToCollection(Collection<K> c) {
		return;
	}

	//The tree is empty so are no keys to subTree so the method returns the current tree.
	public Tree<K,V> subTree(K fromKey, K toKey) {
		return this;
	}
}