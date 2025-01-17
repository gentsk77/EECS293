package edu.cwru.yxs626.typeinference;

import java.util.List;

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

    /**
     * Return the list of sub types of the TypeEntry.
     * 
     * @return the list of sub types of the TypeEntry
     */
    public List<TypeEntry> getSubTypes();

    /**
     * Determines if this TypeEntry has the equal underlying type as the given
     * TypeEntry.
     * 
     * @return true if this TypeEntry has the equal underlying type as the given one
     */
    public boolean hasEqualUnderlyingType(TypeEntry other);

    /**
     * Return the representative's basic representative string.
     * 
     * @param typeSystem the TypeSystem of the TypeEntry
     * @return the representative's basic representative string.
     */
    String representativeString(TypeSystem typeSystem);
}