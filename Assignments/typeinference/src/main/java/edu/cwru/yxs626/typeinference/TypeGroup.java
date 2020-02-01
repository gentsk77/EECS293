package edu.cwru.yxs626.typeinference;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

final class TypeGroup implements Iterable<TypeEntry>{

    private final Set<TypeEntry> typeGroup;

    private TypeEntry representative;

    private TypeGroup(Set<TypeEntry> typeGroup, TypeEntry representative) {
        this.typeGroup = typeGroup;
        this.representative = representative;
    }

    TypeEntry getRepresentative() {
        return this.representative;
    }

    int size() {
        return typeGroup.size();
    }

    static final TypeGroup of(TypeEntry typeEntry) {
        if (typeEntry == null) {
            throw new NullPointerException("The input TypeEntry should not be null.");
        }
        // else pass the tester

        Set<TypeEntry> typeGroup = new HashSet<>();
        typeGroup.add(typeEntry);
        return new TypeGroup(typeGroup, typeEntry);
    }

	@Override
	public Iterator<TypeEntry> iterator() {
		return typeGroup.iterator();
	}
}