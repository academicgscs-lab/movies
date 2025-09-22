package org.example.persistence.xml.loaders;

import java.util.Optional;
import java.util.Vector;

public interface Loadable<T>{
    Optional<Vector<T>> load();
}
