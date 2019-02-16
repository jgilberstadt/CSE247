package studio3;

import java.util.LinkedList;
import java.util.List;

public class UnorderedList<T extends Comparable<T>> implements PriorityQueue<T> {

	public LinkedList<T> list;
	
	public UnorderedList() {
		list = new LinkedList<T>();
	}
	
	@Override
	public boolean isEmpty() {
		//
		if (list.getFirst() == null){
		//
		return true;
		}else {
		return false;
		}
	}

	@Override
	public void insert(T thing) {
		//
		list.add(thing);
		//
	}

	@Override
	public T extractMin() {
		//
		int i = 1;
		int k = 0;
		T p = list.getFirst();
		if (list.getFirst() == null) {
		//
		return null;
		}else {
		for (i = 1; i < list.size(); i++) {
		if (p.compareTo(list.get(i)) > 0){
		k = i;
		p = list.get(i);
		}
		}
		list.remove(k);
		return p;
		}
		
	}
	}

