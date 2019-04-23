
public class BinaryHeap {
    private int[] data;
    private int size;

    public BinaryHeap(){
        data = new int [10];
        size=0; //size
    }

    //GROWS ARRAY: if arr becomes full
    protected void grow_array(){

        int [] temp= new int [data.length*2];
        int i;

        for(i=0; i< data.length; i++){
                temp[i]= data[i];
        }
        this.data=temp;
    }

    //RETURNS: parent of current node being looked at
    private int getParent(int current){
        if((current-1)/2 < 0 ||(current-1)/2 >= size)
            return -1;
        return (current-1)/2;
    }

    //RETURNS: left child of node currently being looked at if its there
    private int get_LeftChild(int current){
        if(2*current+1 >= size || 2*current+1 <0)
            return -1;
        return 2*current+1;
    }

    //RETURNS: right child of node currently being looked at if its there
    private int get_RightChild(int current){
        if(2*current+2 >= size || 2*current+2 <0)
            return -1;
        return 2*current+2;
    }

    public void shiftdown(int current_node){

        //is a leaf should i do this?
        // if(data[current] == -1)
        //     return;

        //has two children
        int right= get_RightChild(current_node);
        int left = get_LeftChild(current_node);
        if(get_RightChild(current_node) != -1 && get_LeftChild(current_node) != -1){
            if(data[left] < data[right]) {
            //if left node is less than right node check check if left less than current node 
                if(data[left]< data[current_node]){
                    swap(data, get_LeftChild(current_node), current_node);
                    current_node = get_LeftChild(current_node);
                    shiftdown(current_node); //recursive call
                }
            }
            else{ 
                //right node is less than left node check if right is also less than current
                if(data[right]<data[current_node]){
                    swap(data, get_RightChild(current_node), current_node);
                    current_node = get_RightChild(current_node);
                    shiftdown(current_node);
                }

                swap(data, getParent(current_node), left);
            }
        }
        //has one child-> left
        else if(get_LeftChild(current_node)!= -1 && data[current_node]> data[left]){
            swap(data, get_LeftChild(current_node), current_node);
            current_node = get_LeftChild(current_node);
            shiftdown(current_node);
        }
    }

    public void swap( int[] arr, int curr, int parent){
        //curr is child value, parent is parent
        int temp = arr[parent]; //temp saves index where it was
        arr[parent]= arr[curr];
        arr[curr] = temp;

    }

    //insert item into heap giving priority to the smallest number
    public void insert(int item){
        if(size == data.length)
            grow_array();
        data[size++]= item;
        int current_node = size-1;
        while(data[getParent(current_node)] > data[current_node] && getParent(current_node)>=0){
            swap(data, current_node, getParent(current_node));
            current_node = getParent(current_node);
        }
    }

    //remove smallest element in the heap
    public int remove (){
        if(size==0)
            return -1;
        swap(data, size-1, 0);
        --size;
        if(size > 0){
            shiftdown(0);
        }
        return data[size];
    }
}