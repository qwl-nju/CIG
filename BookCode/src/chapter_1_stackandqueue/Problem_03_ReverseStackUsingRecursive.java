package chapter_1_stackandqueue;

import java.util.Stack;

public class Problem_03_ReverseStackUsingRecursive {

	public static void reverse(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		}
		int i = getAndRemoveLastElement(stack);
		reverse(stack);
		stack.push(i);
	}

	public static int getAndRemoveLastElement(Stack<Integer> stack) {
		int result = stack.pop();
		if (stack.isEmpty()) {
			return result;
		} else {
			int last = getAndRemoveLastElement(stack);
			stack.push(result);
			return last;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//QWL
	public static void reverse2(Stack<Integer> stack) {
		if(stack.isEmpty()){
			return;
		}else{
			int last = getAndRemoveLastElement2(stack);
			reverse2(stack);
			stack.push(last);
		}
	}
	
	public static int getAndRemoveLastElement2(Stack<Integer> stack) {
		int res = stack.pop();
		if(stack.isEmpty()){
			return res;
		}else{
			int last = getAndRemoveLastElement2(stack);
			stack.push(res);
			return last;
		}
	}

	public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();
		test.push(1);
		test.push(2);
		test.push(3);
		test.push(4);
		test.push(5);
		test.push(6);
		test.push(7);
		test.push(8);
		
		
		reverse(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}
		
		System.out.println("=======================");
		
		test = new Stack<Integer>();
		test.push(1);
		test.push(2);
		test.push(3);
		test.push(4);
		test.push(5);
		test.push(6);
		test.push(7);
		test.push(8);
		reverse2(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}
		

	}

}
