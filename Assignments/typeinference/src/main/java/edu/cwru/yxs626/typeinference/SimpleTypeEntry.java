package edu.cwru.yxs626.typeinference;

import java.util.ArrayList;
import java.util.List;

/**
 * SimpleTypeEntry provides a default implementation of some of the TypeEntry
 * methods that are adequate for simple type entries, such as Integer or T, but
 * not for compound types, such as List<T>.
 * 
 * @author Yue Shu
 */
public abstract class SimpleTypeEntry extends AbstractTypeEntry implements Type {

    /**
     * Returns the underlying Type of the SimpleTypeEntry. (For example, the getType
     * of List<T> returns List).
     * 
     * @return the underlying Type of the SimpleTypeEntry
     */
    @Override
    public Type getType() {
        return this;
    }

    @Override
    public List<TypeEntry> getSubTypes() {
        return new ArrayList<>();
    }
}