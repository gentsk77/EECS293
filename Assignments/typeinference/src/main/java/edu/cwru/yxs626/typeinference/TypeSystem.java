package edu.cwru.yxs626.typeinference;

import java.util.Map;

public final class TypeSystem {

    private Map<TypeEntry, TypeGroup> groups;

    public final TypeEntry add(TypeEntry typeEntry) {
        // check whether the typeEntry is already in the groups
        // if the typeEntry is not in the groups yet
        if (!groups.containsKey(typeEntry)) {
            TypeGroup typeGroup = TypeGroup.of(typeEntry, this);
            groups.put(typeEntry, typeGroup);
        }
        // else has no effect

        return typeEntry;
    }

    TypeEntry representative(TypeEntry s) {
        if (!groups.containsKey(s)) {
            throw new IllegalStateException("TypeEntry " + s.toString() + " is not in the type system",
                    new MissingTypeEntryException(s, this));
        }

        return groups.get(s).getRepresentative();
    }
}