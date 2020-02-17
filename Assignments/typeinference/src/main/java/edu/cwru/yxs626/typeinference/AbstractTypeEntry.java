package edu.cwru.yxs626.typeinference;

import java.util.Objects;

/**
 * AbstractTypeEntry provides a default implementation of some of TypeEntry's
 * methods.
 * 
 * @author Yue Shu
 */
public abstract class AbstractTypeEntry implements TypeEntry {

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

    @Override
    public String representativeString(TypeSystem typeSystem) {
        Objects.requireNonNull(typeSystem, "Input TypeSystem should not be null");

        return typeSystem.representative(this).representativeString(typeSystem);
    }
}