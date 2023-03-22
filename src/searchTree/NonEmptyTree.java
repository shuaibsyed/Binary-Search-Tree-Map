package searchTree;

import java.util.Collection;

/**
 * This class represents a non-empty search tree. An instance of this class
 * should contain:
 * <ul>
 * <li>A key
 * <li>A value (that the key maps to)
 * <li>A reference to a left Tree that contains key:value pairs such that the
 * keys in the left Tree are less than the key stored in this tree node.
 * <li>A reference to a right Tree that contains key:value pairs such that the
 * keys in the right Tree are greater than the key stored in this tree node.
 * </ul>
 *  
 */
public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	private Tree<K,V> left, right;
	private K key;
	private V value;

	public NonEmptyTree(K key, V value, Tree<K,V> left, Tree<K,V> right) { 
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}

	/*This method searches for the key in the parameter and returns the value of that key if found, 
	otherwise it returns null*/
	public V search(K key) {
		V returnValue = null;
		if(this.key.compareTo(key) == 0) {
			return returnValue = this.value;
		} else if(key.compareTo(this.key) < 0) {
			returnValue = left.search(key);
			//Searches the left side of the tree
		} else {
			returnValue = right.search(key);
			//Searches the right side of the tree
		}
		return returnValue;
	}

	/* This method inserts a key value pair into the tree. If the current tree is empty then the key value pair 
	 * becomes the root of the tree. If the key is already in the tree then the value is updated. If the key is greater
	 *  than the root key than it is added to the right otherwise to the left.
	 */
	public NonEmptyTree<K, V> insert(K key, V value) {
		if(key.compareTo(this.key) == 0) {
			this.value = value;
			return this;
			//value update
		} else if(key.compareTo(this.key) > 0) {
			right = right.insert(key, value);
			return this;
			//key is added to the right
		} else {
			left = left.insert(key, value);
			return this;
			//key is added to the left
		}
	}

	/* This method deletes the speicfied key in the tree. If key is not there, then nothing happens and current tree is
	 * returned. First the mtehod checks the current key. If it is found then checks the left and replaces it with the 
	 * max key or it checks the right and replaces it with the minimum key.
	 * Also it catches a TreeIsEmptyException if the tree is empty.
	 */
	public Tree<K, V> delete(K key) {
		if(key.compareTo(this.key) == 0) {
			try {
				this.key = left.max();
				this.value = left.search(this.key);
				left = EmptyTree.getInstance();
				return this;
				//This block deletes the key and replaces it with the left max.
			} catch (TreeIsEmptyException e) {
				try {
					this.key = right.min();
					this.value = right.search(this.key);
					right = EmptyTree.getInstance();
					return this;
					//this block deletes the key and replaces it with the right minimum.
				} catch (TreeIsEmptyException f) {
					return EmptyTree.getInstance();
					//returns an empty tree if exception is thrown.
				}
			}
		} else if(key.compareTo(this.key) > 0) {
			right = right.delete(key);
		} else {
			left = left.delete(key);
		}
		return this;
	}

	/* This method goes through the right side of the tree and returns the max key. Also catches the 
	 * TreeIsEmptyException and returns the current key.
	 */
	public K max() {
		K max;
		try {
			max = right.max();
		} catch (TreeIsEmptyException e) {
			return key;
		}
		return max;
	}

	/* This method  goes through the left side of the  tree and returns the minimum key. Also catches the 
	 * TreeIsEmptyException and returns the current key.
	 */
	public K min() {
		K min;
		try {
			min =  left.min();
		} catch (TreeIsEmptyException e) {
			return key;
		}
		return min;
	}

	/*This method goes through the entire tree and counts all the keys in the tree and returns the sum.
	 * 
	 */
	public int size() {
		return 1 + left.size() + right.size();
	}

	/* This method takes the tree and adds keys to a collection. This method does thta with an in-order traversal.
	 * 
	 */
	public void addKeysToCollection(Collection<K> c) {
		left.addKeysToCollection(c);
		c.add(this.key);
		right.addKeysToCollection(c);
	}

	/*This method returns a subtree of the big tree. Returns all of the keys between the fromKey and toKey.
	 * 
	 */
	public Tree<K,V> subTree(K fromKey, K toKey) {
		if(this.key.compareTo(fromKey) < 0) {
			return right.subTree(fromKey, toKey);
		} else if(this.key.compareTo(toKey) > 0) {
			return left.subTree(fromKey, toKey);
		} else {
			NonEmptyTree<K, V> subSubTree = new NonEmptyTree<K, V>(key, value, EmptyTree.getInstance(), 
					EmptyTree.getInstance());
			subSubTree.left = left.subTree(fromKey, toKey);
			subSubTree.right = right.subTree(fromKey, toKey);
			return subSubTree;
		}
	}
}