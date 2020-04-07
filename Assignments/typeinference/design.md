# Type Inference Extension Design Document

Yue Shu
EECS 293
Assignment 10

## General Design Logic

Generally, the project will be supporting two features, type conversion and type inheritance, in addition to the original type inference system. As a result, I will keep the legacy code base we have from programming assignment 2 - 5, and add a few more classes and methods to support the new features we'd like to have.

## Architecture Design

### Overview


### Type Conversion



### Type Inheritance

To support the automatic type unification feature in the scope of type inheritance, I will add one more Java class, TypeHierarchy to the project.

A TypeHierarchy is used to represent the hierarichal relationship between Types. For example, we can have Number, Integer, Double, and Long in the numerical type family, and thus they will be put in the same TypeHierarchy. In this example we have one and only one ancestor, which is type Number. The below figure can represent the hierarchical structure of the examle.

![number family example](assets/number_eg.png)

Another example could be more complicated, where we can have multiple ancestors, since multiple implementations on the interfaces are allowed in the reality.

![abstract list family example](assets/abstractlist_eg.png)

In the example above, we will have two independent ancestors in the family tree since an ArrayList can both be an AbstractCollection and share the attributes as a Cloneable. As a result, the data structure to represent a type hierarchy cannot be a tree, although intuitively it does look like one.

In my class design, to represent the actual inheritance attribute of the type hierarchy, I decided to use an acyclic graph with directional edges infering the directed `base` -> `derived` relationship in the inheritance. In actual use case, the structure highlighted in red should not be allowed since a cycle is created. A directional edge should only be used to address immediate inheritence between the base and the derived class. Therefore, we should use the structure highlighted in green instead, which essentially represents the same structure.

![invalid exmaple](assets/invalid_eg.png)  versus ![valid example](assets/valid_eg.png)

#### Detailed Class Design

A TypeHierarchy will be a package default class similar to TypeGroup. Primarily, it has a `private final Map<Type, List<Type>> typeHierarchy`, a `private final Set<Type> ancestor` along with its getter, and a `private final TypeSystem typeSytem`. Its private constructor `private TypeHierarchy(Map<Type, List<Type>> typeHierarchy, Set<Type> ancestor, TypeSystem typeSytem)` should not throw any exception. It has a builder `static final TypeHierarchy of(Type type, TypeSystem typeSytem)` will invoke the constructor and create a new TypeHierarchy object. The typeHierarchy will then be initialized to be a one-key map only containing the given type and the value will be an empty ArrayList. The ancestor will be a one-element set only containing the type as well. The typeSytem will be the given type system.

Besides the methods and fields mentioned above, the TypeHierarchy has a `int size()` that is delecated to its typeHierchy, and a `public Iterator<Map.Entry<Type, List<Type>>> iterator()` that is delecated to the entrySet of its typeHierarchy. The major functionality of TypeHierarchy is achieved through its `final void appendBetween(TypeHierarchy other, Type base, Type derived)`. The method will append the entire type hierarchy of the derived type to the base type, make the typeHierarchy of derived in the typeSystem this typeHierarchy,and additionally add an edge directing from the base to the derived type in the typeHierarchy. Finally, the method will check whether derived was a root of other, and if not, it will add all the ancestors of other to the ancestor of this TypeHierarchy.

