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

    // package-private helper method for testing
    TypeGroup getTypeGroup(TypeEntry typeEntry) {
        return groups.getOrDefault(typeEntry, null);
    }

    /**
     * Update the TypeGroup associated with the given TypeEntry.
     * 
     * @param typeEntry the TypeEntry to be updated
     * @param typeGroup the new TypeGroup of the TypeEntry
     */
    final void setTypeGroup(TypeEntry typeEntry, TypeGroup typeGroup) {
        groups.put(typeEntry, typeGroup);
    }

    /**
     * Perform the unification algorithm on two given TypeEntries and verify whether
     * they could unify.
     * 
     * @param s the TypeEntry to be unified
     * @param t the TypeEntry to be unified
     * @return whether two TypeEntries unify
     */
    public final boolean unify(TypeEntry s, TypeEntry t) {
        Objects.requireNonNull(s, "Input type entry should not be null");
        Objects.requireNonNull(t, "Input type entry should not be null");

        TypeEntry representativeS = representative(s);
        TypeEntry representativeT = representative(t);

        if (representativeS.equals(representativeT)) {
            return true;
        }
        // if both TypeEntry are CompoundTypeEntry that share the same type
        else if (representativeS.hasEqualUnderlyingType(representativeT)) {
            // the appending of the type groups were moved into
            // TypeSystem::unifySubTypesOfTypeEntry
            return unifySubTypesOfTypeEntry(representativeS, representativeT);
        }
        // if either type is variable
        else if (representativeS.isVariable() || representativeT.isVariable()) {
            appendGroupsOfTypeEntry(representativeS, representativeT);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Unify the lists of subTypes of the given TypeEntries.
     * 
     * @param s the TypeEntry to be unified
     * @param t the TypeEntry to be unified
     * @return whether the list of subTypes of the two TypeEntries unify
     */
    private final boolean unifySubTypesOfTypeEntry(TypeEntry s, TypeEntry t) {
        Iterator<TypeEntry> iteratorS = s.getSubTypes().iterator();
        Iterator<TypeEntry> iteratorT = t.getSubTypes().iterator();

        while (iteratorS.hasNext()) {
            if (!unify(iteratorS.next(), iteratorT.next())) {
                return false;
            }
            // will not return anything if unify
        }

        // union the two type groups once we make sure they unify
        appendGroupsOfTypeEntry(s, t);

        return true;
    }

    /**
     * Append the TypeGroup of the given TypeEntries.
     * 
     * @param s the TypeEntry to be appended to
     * @param t the TypeEntry to be appended
     */
    private final void appendGroupsOfTypeEntry(TypeEntry s, TypeEntry t) {
        TypeGroup groupS = groups.get(s);
        TypeGroup groupT = groups.get(t);

        if (groupS.size() > groupT.size()) {
            groupS.append(groupT);
        } else {
            groupT.append(groupS);
        }
    }
}