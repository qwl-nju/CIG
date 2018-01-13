package chapter_1_stackandqueue;

import java.util.Collections;
import java.util.Stack;

public class Problem_02_TwoStacksImplementQueue {

	public static class TwoStacksQueue {
		public Stack<Integer> stackPush;
		public Stack<Integer> stackPop;

		public TwoStacksQueue() {
			stackPush = new Stack<Integer>();
			stackPop = new Stack<Integer>();
		}

		public void add(int pushInt) {
			stackPush.push(pushInt);
		}

		public int poll() {
			if (stackPop.empty() && stackPush.empty()) {
				throw new RuntimeException("Queue is empty!");
			} else if (stackPop.empty()) {
				while (!stackPush.empty()) {
					stackPop.push(stackPush.pop());
				}
			}
			return stackPop.pop();
		}

		public int peek() {
			if (stackPop.empty() && stackPush.empty()) {
				throw new RuntimeException("Queue is empty!");
			} else if (stackPop.empty()) {
				while (!stackPush.empty()) {
					stackPop.push(stackPush.pop());
				}
			}
			return stackPop.peek();
		}
	}

	// QWL
	public static class TwoStacksQueue2 {
		private Stack<Integer> s1 = new Stack<>();
		private Stack<Integer> s2 = new Stack<>();

		public void add(int num) {
			s1.push(num);
		}

		public int poll() {
			if (s2.isEmpty()) {
				if (s1.isEmpty()) {
					throw new NullPointerException("null");
				} else {
					while (!s1.isEmpty()) {
						s2.push(s1.pop());
					}
				}
			}
			return s2.pop();
		}
		
		public int peek() {
			if (s2.isEmpty()) {
				if (s1.isEmpty()) {
					throw new NullPointerException("null");
				} else {
					while (!s1.isEmpty()) {
						s2.push(s1.pop());
					}
				}
			}
			return s2.peek();
		}
	}

	public static void main(String[] args) {
		TwoStacksQueue test = new TwoStacksQueue();
		test.add(1);
		test.add(2);
		test.add(3);
		System.out.println(test.peek());
		System.out.println(test.poll());
		System.out.println(test.peek());
		System.out.println(test.poll());
		System.out.println(test.peek());
		System.out.println(test.poll());
		
		System.out.println("=========================");
		
		TwoStacksQueue2 test2 = new TwoStacksQueue2();
		test2.add(1);
		test2.add(2);
		test2.add(3);
		System.out.println(test2.peek());
		System.out.println(test2.poll());
		System.out.println(test2.peek());
		System.out.println(test2.poll());
		System.out.println(test2.peek());
		System.out.println(test2.poll());
	}

}
