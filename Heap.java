import java.util.*;
public class Heap<C extends Comparable> {
    private ArrayList<C> heap;

    public Heap(){ 
    	heap=new ArrayList<C>();
    }

    public C peek() {
    	if (heap.size()==0) return null;
    	return heap.get(0);
    }
    public int size() { return heap.size(); }
    
    public boolean isEmpty() { return heap.size()==0; }

    private int getParent(int pos) {
    	if (pos==0) return -1;
    	return (pos-1)/2; //integer division
    }
    private int getLeft(int pos) {
    	return 2*pos+1;
    }
    private int getRight(int pos) {
    	return 2*pos+2;
    }
    
    
    public void enqueue(C elt){ 
    	heap.add(elt);
    	int currPos = heap.size()-1;
    	int parentPos = getParent(currPos);
    	
    	while (currPos > 0 && heap.get(currPos).compareTo(heap.get(parentPos))>0) {
    		 Collections.swap(heap, currPos, parentPos);
    		 /* heap.set(currPos, heap.get(parentPos));
    		  * heap.set(parentPos, elt);
    		  */
    		 currPos=parentPos;
    		 parentPos=getParent(currPos);
    	}
    }
    
    private boolean lessThanChild(int pos) {
    	int left = getLeft(pos);
    	int right = getRight(pos);
    	if (right < heap.size()) {
    		boolean cont = heap.get(pos).compareTo(heap.get(left)) < 0;
    		cont = cont || heap.get(pos).compareTo(heap.get(right)) < 0;
    		return cont;
    	}
    	else if (right == heap.size()) {
    		return heap.get(pos).compareTo(heap.get(left)) < 0;
    	}
    	return false; //neither exist
    	
    }
    
    public C dequeue(){
    	if (heap.size() == 0) {
    		return null;
    	}
    	Collections.swap(heap,0,heap.size()-1);
    	C toReturn = heap.remove(heap.size()-1);
    	int currPos = 0;
    	int left = getLeft(currPos);
    	int right = getRight(currPos);
    	// write a function to get if less than one child
    	while(left < heap.size() && lessThanChild(currPos)) {
    		// figure out which one is greater
    		// swap with greater child
    		
    		if (right == heap.size() || heap.get(left).compareTo(heap.get(right)) > 0) { //means if left child is greater than the right the child
    			Collections.swap(heap,currPos,left);
    			currPos = left;
    		}
    		else { 
    			Collections.swap(heap,currPos,right);
    			currPos = right;
    		}
    		left = getLeft(currPos);
    		right = getRight(currPos);
    	}
    	return toReturn;
    }

    private void print(){ System.out.println(heap); }

    public static void main(String[] args){
    	Heap<Integer> myHeap = new Heap<Integer>();
    	myHeap.enqueue(10);
    	myHeap.enqueue(8);
    	myHeap.enqueue(4);
    	myHeap.enqueue(30);
    	myHeap.enqueue(11);
    	myHeap.enqueue(78);
    	myHeap.print();
    	
    	myHeap.dequeue();
    	myHeap.dequeue();
    	myHeap.dequeue();
    	myHeap.dequeue();
    	myHeap.dequeue();
    	myHeap.dequeue();
    	myHeap.dequeue();
    	myHeap.print();
    	
    	
    }

}
