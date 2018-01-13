package chapter_1_stackandqueue;

import java.util.Stack;

public class Problem_05_StackSortStack {

	public static void sortStackByStack(Stack<Integer> stack) {
		Stack<Integer> help = new Stack<Integer>();
		while (!stack.isEmpty()) {
			int cur = stack.pop();
			while (!help.isEmpty() && help.peek() < cur) {
				stack.push(help.pop());
			}
			help.push(cur);
		}
		while (!help.isEmpty()) {
			stack.push(help.pop());
		}
	}
	
	
	//QWL
	public static void sortStackByStack2(Stack<Integer> stack) {
		Stack<Integer> help = new Stack<>();
		while(!stack.isEmpty()){
			if(help.isEmpty() || stack.peek() <= help.peek()){
				help.push(stack.pop());
			}else{
				int temp = stack.pop();
				while(!help.isEmpty() && help.peek() < temp){
					stack.push(help.pop());
				}
				help.push(temp);
			}
		}
		
		while(!help.isEmpty()){
			stack.push(help.pop());
		}
	}

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(3);
		stack.push(1);
		stack.push(6);
		stack.push(2);
		stack.push(5);
		stack.push(7);
		stack.push(-1);
		stack.push(0);
		stack.push(9);
		stack.push(4);
		stack.push(11);
		sortStackByStack(stack);
		while(!stack.isEmpty())
			System.out.println(stack.pop());
		
		System.out.println("===============================");
		
		stack = new Stack<Integer>();
		stack.push(3);
		stack.push(1);
		stack.push(6);
		stack.push(2);
		stack.push(5);
		stack.push(7);
		stack.push(-1);
		stack.push(0);
		stack.push(9);
		stack.push(4);
		stack.push(11);
		sortStackByStack2(stack);
		while(!stack.isEmpty())
			System.out.println(stack.pop());

	}

}
