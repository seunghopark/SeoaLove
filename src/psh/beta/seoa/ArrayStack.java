package psh.beta.seoa;

public class ArrayStack {
	
	private int top;
	private int maxSize;
	private Object[] stackArray;
	
	public ArrayStack(int maxSize){
		
		this.maxSize = maxSize;
		
		this.stackArray = new Object[maxSize];
		
		this.top = -1;
		
	}
	
	public boolean empty(){
		return (top==-1);
	}
	
	public boolean full(){
		return (top==maxSize-1);
	}
	
	public void push(Object item){
		
		if(full()) throw new ArrayIndexOutOfBoundsException((top+1)+">="+maxSize);
		stackArray[++top] = item;
		//Array 변수의 특정 번째에 데이터가 없는 경우, 즉 Array변수의 데이터가 0부터 7까지 있는데 8번재의 데이터를 찾으려고 시도 할때 발생되는 에러 입니다.
		
	}
	
	public Object peek(){
		if(empty()) throw new ArrayIndexOutOfBoundsException(top);
		return stackArray[top];
	}
	
	public Object pop(){
		Object item = peek();
		top--;
		
		return item;
	}
	
}
