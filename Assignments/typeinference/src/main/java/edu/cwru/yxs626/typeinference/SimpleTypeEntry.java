package edu.cwru.yxs626.typeinference;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    /**
     * Returns the list of sub types. For a SimpleType, a empty list would be
     * returned.
     * 
     * @return an empty list
     */
    @Override
    public List<TypeEntry> getSubTypes() {
        return new ArrayList<>();
    }

    /**
     * Determines if this TypeEntry has the equal underlying type as the given
     * TypeEntry.
     * 
     * @return false since two simple type entries could share the same TypeName
     */
    @Override
    public boolean hasEqualUnderlyingType(TypeEntry other) {
        Objects.requireNonNull(other, "Input TypeEntry should not be null");

        return false;
    }

    /**
     * Return a string representation of the representative of its corresponding
     * TypeGroup in the given TypeSystem.
     * 
     * @param typeSystem the TypeSystem containing this TypeEntry
     * @return a string representation of the representative of this TypeEntry
     */
    @Override
    protected String basicRepresentativeString(TypeSystem typeSystem) {
        Objects.requireNonNull(typeSystem, "Input TypeSystem should not be null");

        return typeSystem.representative(this).toString();
    }

}