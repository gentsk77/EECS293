package edu.cwru.yxs626.typeinference;

import java.util.Collections;
import java.util.List;

/**
 * ArityException is the exception thrown when the arity doesn't match.
 * 
 * @author Yue SHu
 */
public final class ArityException extends Exception {

    /** The Serial Version UID auto-generated by IDE. */
    private static final long serialVersionUID = 8032310909534583677L;

    /** The CompoundType related to the ArityException. */
    private final CompoundType compoundType;

    /** The list of sub types related to the ArityException. */
    private final List<TypeEntry> subTypes;

    /**
     * Creates a new ArityException.
     * 
     * @param compoundType the CompoundType related to the ArityException
     * @param subTypes     the list of sub types related to the ArityException
     */
    public ArityException(CompoundType compoundType, List<TypeEntry> subTypes) {
        this.compoundType = compoundType;
        this.subTypes = subTypes;
    }

    /**
     * Returns the CompoundType of the ArityException.
     * 
     * @return the CompoundType of the ArityException
     */
    public CompoundType getCompoundType() {
        return this.compoundType;
    }

    /**
     * Returns the list of sub types of the ArityException.
     * 
     * @return the list of sub types of the ArityException
     */
    public List<TypeEntry> getSubTypes() {
        return Collections.unmodifiableList(subTypes);
    }
}