package assignment4_f20;

import java.util.HashMap;

public class MinBinHeap implements Heap {
  private CacheFrame[] array; // load this array
  private int size;      // how many items currently in the heap
  private int arraySize; // Everything in the array will initially
                         // be null. This is ok! Just build out
                         // from array[1]
  public HashMap<String, CacheFrame> map;
  
  public MinBinHeap(int nelts) {
    this.array = new CacheFrame[nelts+1];  // remember we dont use slot 0
    this.arraySize = nelts+1;
    this.size = 0;
    this.array[0] = new CacheFrame(null, 0); // 0 not used, so this is arbitrary
    this.map = new HashMap<>();
  }

  // Please do not remove or modify this method! Used to test your entire Heap.
  @Override
  public CacheFrame[] getHeap() { return this.array; }

@Override
public void insert(CacheFrame elt) {
	size++;		//increment size of array elements
	array[size]= elt;		//add element to array (not in right position yet
	elt.setSlot(size);
	
	String hashKey = elt.getValue();
	
	map.put(hashKey, elt);		//add element to hashmap
	
	
	
	int index = elt.getSlot();
	
	
	while (index >1) {
			
		
		if(array[index].getPriority() < array[index/2].getPriority()) {		//check if smaller than root
			CacheFrame temp = array[index];
			int slotNum = temp.getSlot();
			
			array[index].setSlot(array[index/2].getSlot());
			array[index/2].setSlot(slotNum);

			
			array[index] = array[index/2];
			array[index/2]=temp;
			
			index = index/2;
			
			continue;
		}
		
		index = 1;
	}	
	
}

@Override
public void delMin() {
	
	if(array[1]==null) {			//check if heap is empty
		return;
	}
	if(size ==1) {
		array[1] = null;
		return;
	}
	array[1] = array[size];						//set root to final position
	array[1].setSlot(1);
	array[size] = null;
	size--;

	
	int index = 1;		//used to keep track of index during sort
	
	
	
	while (index >0) {
		if(index*2>size) {
			break;
		}
		
		
		if(index*2 == size) {
			if(array[index].getPriority() > array[index*2].getPriority()) {				
				CacheFrame temp = array[index];
				int slotNum = temp.getSlot();
				
				array[index].setSlot(array[index*2].getSlot());
				array[index*2].setSlot(slotNum);

				
				array[index] = array[index*2];
				array[index*2]=temp;
				
				index = index*2;
			}
			break;
		}
		
		
		if(array[index*2].getPriority() < array[(index*2)+1].getPriority()) {	//if left branch is smaller than left branch
			if(array[index].getPriority() > array[index*2].getPriority()) {				
				CacheFrame temp = array[index];
				int slotNum = temp.getSlot();
				
				array[index].setSlot(array[index*2].getSlot());
				array[index*2].setSlot(slotNum);

				
				array[index] = array[index*2];
				array[index*2]=temp;
				
				index = index*2;
				
				continue;
			}
		}

		if(array[index*2].getPriority() > array[(index*2)+1].getPriority()) {	//if right branch is smaller than left branch
			if(array[index].getPriority() > array[(index*2)+1].getPriority()) {
				CacheFrame temp = array[index];
				int slotNum = temp.getSlot();
				
				array[index].setSlot(array[(index*2)+1].getSlot());
				array[(index*2)+1].setSlot(slotNum);

				
				array[index] = array[(index*2)+1];
				array[(index*2)+1]=temp;
				
				index = (index*2)+1;
				
				continue;
			}		
		}
		index = size;
	}
	
}

@Override
public CacheFrame getMin() {
	return array[1];
}

@Override
public int size() {
	return size;
}

@Override
public void incElt(CacheFrame elt) {
	
	elt.setPriority(elt.getPriority()+1);		//set element priority
	int index = elt.getSlot();
	
	//checklist for each shift:
	//set temporary
	//swap values
	//reset index
	
	
	while (index >0) {
		if(index*2 > size) {
			index = 0;
			continue;
		}
		
		if(array[index*2].getPriority() < array[(index*2)+1].getPriority()) {	//if left branch is smaller than left branch
			if(array[index].getPriority() > array[index*2].getPriority()) {				
				CacheFrame temp = array[index];
				int slotNum = temp.getSlot();
				
				array[index].setSlot(array[index*2].getSlot());
				array[index*2].setSlot(slotNum);

				
				array[index] = array[index*2];
				array[index*2]=temp;
				
				index = index*2;
				
				continue;
			}
		}
		if(array[index*2].getPriority() > array[(index*2)+1].getPriority()) {	//if right branch is smaller than left branch
			if(array[index].getPriority() > array[(index*2)+1].getPriority()) {
				CacheFrame temp = array[index];
				int slotNum = temp.getSlot();
				
				array[index].setSlot(array[(index*2)+1].getSlot());
				array[(index*2)+1].setSlot(slotNum);

				
				array[index] = array[(index*2)+1];
				array[(index*2)+1]=temp;
				
				index = (index*2)+1;
				
				continue;
			}		
		}
		index = 0;
	}

	
}

@Override
public void decElt(CacheFrame elt) {
	
	if(elt.getPriority() == 1) {
		return;
	}
	
	elt.setPriority(elt.getPriority()-1);		//set element priority
	
	//checklist for each shift:
	//set temporary
	//swap values
	//reset index
	
	
	int index = elt.getSlot();
	
	
	while (index >1) {
			
		
		if(array[index].getPriority() < array[index/2].getPriority()) {		//check if smaller than root
			CacheFrame temp = array[index];
			int slotNum = temp.getSlot();
			
			array[index].setSlot(array[index/2].getSlot());
			array[index/2].setSlot(slotNum);

			
			array[index] = array[index/2];
			array[index/2]=temp;
			
			index = index/2;
			
			continue;
		}
		
		index = 1;
	}	
	
}


public boolean inHash(String elt) {
	if(map.containsKey(elt)) {
		return true;
	}
	return false;
}


public HashMap<String,CacheFrame> getHash() {		//return hashmap
	return map;
}


  //===============================================================
  //
  // here down you implement the ops in the interface
  //
  //===============================================================

}