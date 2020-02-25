package edu.cwru.yxs626.typeinference;

import java.util.Objects;

/**
 * AbstractTypeEntry provides a default implementation of some of TypeEntry's
 * methods.
 * 
 * @author Yue Shu
 */
public abstract class AbstractTypeEntry implements TypeEntry {

    /**
     * Return a string representation of the representative of its corresponding
     * TypeGroup in the given TypeSystem.
     * 
     * @param typeSystem the TypeSystem containing this TypeEntry
     * @return a string representation of the representative of this TypeEntry
     */
    abstract protected String basicRepresentativeString(TypeSystem typeSystem);

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

    /**
     * Return the representative's basic representative string.
     * 
     * @param typeSystem the TypeSystem of the TypeEntry
     * @return the representative's basic representative string.
     */
    @Override
    public String representativeString(TypeSystem typeSystem) {
        Objects.requireNonNull(typeSystem, "Input TypeSystem should not be null");
        
        TypeEntry rep = typeSystem.representative(this);

        if (rep != this) {
            return rep.representativeString(typeSystem);
        }

        return basicRepresentativeString(typeSystem);
    }
}