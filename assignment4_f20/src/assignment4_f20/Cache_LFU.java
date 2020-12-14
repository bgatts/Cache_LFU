package assignment4_f20;

import java.util.HashMap;

public class Cache_LFU implements Cache {
  
  HashMap<String, CacheFrame> map; 
    // allocate from java collections lib
    // do it this way so we all start with default size and 
    // default lambda and default hash function for string keys
  MinBinHeap heap; // your own heap code above
  int limit;       // max num elts the cache can hold
  int size;        // current number elts in the cache
  
  Cache_LFU (int maxElts) {
    this.heap = new MinBinHeap(maxElts);
    this.map = heap.getHash();
    this.limit = maxElts;
    this.size = 0;
  }
  
  // dont change this we need it for grading
  public MinBinHeap getHeap() { return this.heap; }
  public HashMap getHashMap() { return this.map; }

@Override
public int size() {
	return limit;
}

@Override
public int numElts() {
	return size;
}

@Override
public boolean isFull() {
	if(limit == size) {
		return true;
	}
	return false;
}

@Override
public boolean refer(String address) {
	
	if(heap.inHash(address)) {		//if heap is already in hash
		heap.incElt(map.get(address));
		return true;
	}
	
	CacheFrame insert = new CacheFrame(address,1);
	
	if(size == limit) {		//if cache is full
		CacheFrame root = heap.getMin();
		map.remove(root.getValue());	//remove root from map
		heap.delMin();			//delete root from root
		heap.insert(insert);	//insert new item
		return false;
	}
		
	heap.insert(insert);	//insert new item
	size++;
	return false;
}
  
  // =========================================================
  //
  // you fill in code for the other ops in the interface
  //
  //==========================================================
  
}