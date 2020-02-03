package edu.cwru.yxs626.typeinference;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

final class TypeGroup implements Iterable<TypeEntry>{

    private final Set<TypeEntry> typeGroup;

    private final TypeSystem typeSystem;

    private TypeEntry representative;

    private TypeGroup(Set<TypeEntry> typeGroup, TypeEntry representative, TypeSystem typeSystem) {
        this.typeGroup = typeGroup;
        this.representative = representative;
        this.typeSystem = typeSystem;
    }

    TypeEntry getRepresentative() {
        return this.representative;
    }

    int size() {
        return typeGroup.size();
    }

    static final TypeGroup of(TypeEntry typeEntry, TypeSystem typeSystem) {
        if (typeEntry == null || typeSystem == null) {
            throw new NullPointerException("None of the input argument should be null.");
        }
        // else pass the tester

        Set<TypeEntry> typeGroup = new HashSet<>();
        typeGroup.add(typeEntry);
        return new TypeGroup(typeGroup, typeEntry, typeSystem);
    }

	@Override
	public Iterator<TypeEntry> iterator() {
		return typeGroup.iterator();
	}
}