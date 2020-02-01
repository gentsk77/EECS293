package edu.cwru.yxs626.typeinference;

public final class MissingTypeEntryException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1795089750842043370L;

    private final TypeEntry typeEntry;

    private final TypeSystem typeSystem;

    public MissingTypeEntryException(TypeEntry typeEntry, TypeSystem typeSystem) {
        this.typeEntry = typeEntry;
        this.typeSystem = typeSystem;
    }

    public TypeEntry getTypeEntry() {
        return this.typeEntry;
    }

    public TypeSystem getTypeSystem() {
        return this.typeSystem;
    }
}