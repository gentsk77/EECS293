package edu.cwru.yxs626.typeinference;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * A TypeGroup represents all the types that are equivalent to each other.
 * 
 * @author Yue Shu
 */
final class TypeGroup implements Iterable<TypeEntry> {

    /** All the types in the group. */
    private final Set<TypeEntry> typeGroup;

    /** The TypeSystem corresponding to the TypeGroup. */
    private final TypeSystem typeSystem;

    /** The canonical representative of the group. */
    private TypeEntry representative;

    /**
     * Creates a new TypeGroup.
     * 
     * @param typeGroup      the set of TypeEntries in the TypeGroup
     * @param representative the representative of the TypeGroup
     * @param typeSystem     the TypeSystem corresponding to the TypeGroup
     */
    private TypeGroup(Set<TypeEntry> typeGroup, TypeEntry representative, TypeSystem typeSystem) {
        this.typeGroup = typeGroup;
        this.representative = representative;
        this.typeSystem = typeSystem;
    }

    /**
     * Returns the representative of the TypeGroup.
     * 
     * @return the representative of the TypeGroup
     */
    TypeEntry getRepresentative() {
        return this.representative;
    }

    /**
     * Returns the size of the TypeGroup.
     * 
     * @return the size of the TypeGroup
     */
    int size() {
        return typeGroup.size();
    }

    /**
     * Returns a new TypeGroup with the given TypeEntry as its representative.
     * 
     * @param typeEntry  the representative of the TypeGroup
     * @param typeSystem the TypeSystem corresponding to the TypeGroup
     * @return a new TypeGroup
     */
    static final TypeGroup of(TypeEntry typeEntry, TypeSystem typeSystem) {
        Objects.requireNonNull(typeEntry, "Input type entry should not be null");
        Objects.requireNonNull(typeSystem, "Input type system should not be null");

        Set<TypeEntry> typeGroup = new HashSet<>();
        typeGroup.add(typeEntry);

        return new TypeGroup(typeGroup, typeEntry, typeSystem);
    }

    /**
     * Returns an iterator of the TypeGroup.
     * 
     * @return an iterator of the TypeGroup
     */
    @Override
    public Iterator<TypeEntry> iterator() {
        return typeGroup.iterator();
    }
}