package net.minecraft.src;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MEMAIEntrySet<T extends IMEMAIJob> implements
		Iterable<MEMAIEntry<T>> {
	protected final Set<MEMAIEntry<T>> entries = new HashSet<MEMAIEntry<T>>();

	public int size() {
		return entries.size();
	}

	public boolean isEmpty() {
		return entries.isEmpty();
	}

	public boolean contains(Object o) {
		return entries.contains(o);
	}

	public Object[] toArray() {
		return entries.toArray();
	}

	public <A> A[] toArray(A[] a) {
		return entries.toArray(a);
	}

	public boolean add(MEMAIEntry<T> e) {
		return entries.add(e);
	}

	public boolean remove(Object o) {
		return entries.remove(o);
	}

	public boolean containsAll(Collection<?> c) {
		return entries.containsAll(c);
	}

	public boolean addAll(Collection<? extends MEMAIEntry<T>> c) {
		return entries.addAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return entries.retainAll(c);
	}

	public boolean removeAll(Collection<?> c) {
		return entries.removeAll(c);
	}

	public void clear() {
		entries.clear();
	}

	@Override
	public boolean equals(Object o) {
		return entries.equals(o);
	}

	@Override
	public int hashCode() {
		return entries.hashCode();
	}

	@Override
	public Iterator<MEMAIEntry<T>> iterator() {
		return entries.iterator();
	}

}
