package edu.cwru.yxs626.typeinference;

/**
 * TypeEntry abstracts the concept of a type within an expression. A difference
 * between a Type and TypeEntry is for example that List is a Type but List<T>
 * is a TypeEntry.
 * 
 * @author Yue Shu
 */
public interface TypeEntry {

    /**
     * Returns the underlying Type of the TypeEntry. (For example, the getType of
     * List<T> returns List).
     * 
     * @return the underlying Type of the TypeEntry
     */
    public Type getType();

    /**
     * Determines whether the underlying Type of the TypeEntry is a variable, such
     * as T.
     * 
     * @return true if the underlying Type of the TypeEntry is a variable, false
     *         elsewise
     */
    public boolean isVariable();
}