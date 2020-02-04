package edu.cwru.yxs626.typeinference;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A TypeSystem contains and manages all the TypeGroups corresponding to it.
 * 
 * @author Yue Shu
 */
public final class TypeSystem {

    /** Maps TypeEntry to the TypeGroup it belongs. */
    private Map<TypeEntry, TypeGroup> groups;

    /**
     * Create a new TypeSystem.
     */
    public TypeSystem() {
        groups = new HashMap<>();
    }

    /**
     * Add a TypeEntry to the TypeSystem by creating a TypeGroup that only contains
     * the TypeEntry.
     * 
     * @param typeEntry the TypeEntry to be added to the TypeSystem
     * @return the TypeEntry
     */
    public final TypeEntry add(TypeEntry typeEntry) {
        Objects.requireNonNull(typeEntry, "Input type entry should not be null");

        // check whether the typeEntry is already in the groups
        // if the typeEntry is not in the groups yet
        if (!groups.containsKey(typeEntry)) {
            TypeGroup typeGroup = TypeGroup.of(typeEntry, this);
            groups.put(typeEntry, typeGroup);
        }
        // else has no effect

        return typeEntry;
    }

    /**
     * Returns the representative corresponding to the TypeGroup of the given
     * TypeEntry.
     * 
     * @param s the TypeEntry
     * @return the reprensentative corresponding to the TypeGroup of the given
     *         TypeEntry
     */
    TypeEntry representative(TypeEntry s) {
        Objects.requireNonNull(s, "Input type entry should not be null");

        // throw exception if the TypeEntry is not in the system
        if (!groups.containsKey(s)) {
            throw new IllegalStateException("TypeEntry " + s.toString() + " is not in the type system",
                    new MissingTypeEntryException(s, this));
        }
        // else the groups contains the key, pass the test

        return groups.get(s).getRepresentative();
    }
}