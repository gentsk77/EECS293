package edu.cwru.yxs626.typeinference;

import java.util.List;

public final class ArityException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 8032310909534583677L;

    private final CompoundType compoundType;

    private final List<TypeEntry> subTypes;

    public ArityException(CompoundType compoundType, List<TypeEntry> subTypes) {
        this.compoundType = compoundType;
        this.subTypes = subTypes;
    }

    public CompoundType getCompoundType() {
        return this.compoundType;
    }

    public List<TypeEntry> getSubTypes() {
        return this.subTypes;
    }
}