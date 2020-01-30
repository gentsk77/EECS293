package edu.cwru.yxs626.typeinference;

/**
 * AbstractTypeEntry provides a default implementation of some of TypeEntry's
 * methods.
 * 
 * @author Yue Shu
 */
public abstract class AbstractTypeEntry implements TypeEntry {

    /**
     * Determines whether the underlying Type of the AbstractTypeEntry is a
     * variable, such as T.
     * 
     * @return true if the underlying Type of the AbstractTypeEntry is a variable,
     *         false elsewise
     */
    @Override
    public boolean isVariable() {
        return false;
    }
}