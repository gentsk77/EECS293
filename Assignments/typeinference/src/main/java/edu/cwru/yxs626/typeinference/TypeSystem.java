package edu.cwru.yxs626.typeinference;

import java.util.HashMap;
import java.util.Iterator;
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

        TypeGroup typeGroup = TypeGroup.of(typeEntry, this);
        // changed to putIfAbsent
        groups.putIfAbsent(typeEntry, typeGroup);

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

    final void setTypeGroup(TypeEntry typeEntry, TypeGroup typeGroup) {
        // TODO: check if exist?
        groups.put(typeEntry, typeGroup);
    }

    // TODO: clean up logic structure
    public final boolean unify(TypeEntry s, TypeEntry t) {
        Objects.requireNonNull(s, "Input type entry should not be null");
        Objects.requireNonNull(t, "Input type entry should not be null");

        TypeEntry representativeS = representative(s);
        TypeEntry representativeT = representative(t);

        if (representativeS.equals(representativeT)) {
            return true;
        }

        else if (representativeS.hasEqualUnderlyingType(representativeT)) {
            TypeGroup groupS = groups.get(representativeS);
            TypeGroup groupT = groups.get(representativeT);

            groupS.append(groupT);

            Iterator<TypeEntry> iteratorS = representativeS.getSubTypes().iterator();
            Iterator<TypeEntry> iteratorT = representativeT.getSubTypes().iterator();

            while (iteratorS.hasNext()) {
                if (!unify(iteratorS.next(), iteratorT.next())) {
                    return false;
                }
            }

            return true;
        }

        else if (representativeS.isVariable() || representativeT.isVariable()) {
            TypeGroup groupS = groups.get(representativeS);
            TypeGroup groupT = groups.get(representativeT);

            groupS.append(groupT);

            return true;
        }

        else {
            return false;
        }
    }
}