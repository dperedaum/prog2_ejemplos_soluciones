/**
 * 
 */
package uy.edu.um.adt.ntree;

/**
 * @author danielpereda
 *
 */
public interface Tree<T> {

	void add(T oValueToAdd, T oParentValue);
	
	void add(Tree<T> oTreeToAdd, T oParentValue);

	T getValue();

	void setValue(T oValue);

	Tree<T>[] getChilds();

	Tree<T> searchValue(T oValueToSearch);
}
