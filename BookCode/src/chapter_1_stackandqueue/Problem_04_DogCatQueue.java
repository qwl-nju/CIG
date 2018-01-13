package chapter_1_stackandqueue;

import java.util.LinkedList;
import java.util.Queue;

public class Problem_04_DogCatQueue {

	public static class Pet {
		private String type;

		public Pet(String type) {
			this.type = type;
		}

		public String getPetType() {
			return this.type;
		}
	}

	public static class Dog extends Pet {
		public Dog() {
			super("dog");
		}
	}

	public static class Cat extends Pet {
		public Cat() {
			super("cat");
		}
	}

	public static class PetEnterQueue {
		private Pet pet;
		private long count;

		public PetEnterQueue(Pet pet, long count) {
			this.pet = pet;
			this.count = count;
		}

		public Pet getPet() {
			return this.pet;
		}

		public long getCount() {
			return this.count;
		}

		public String getEnterPetType() {
			return this.pet.getPetType();
		}
	}

	public static class DogCatQueue {
		private Queue<PetEnterQueue> dogQ;
		private Queue<PetEnterQueue> catQ;
		private long count;

		public DogCatQueue() {
			this.dogQ = new LinkedList<PetEnterQueue>();
			this.catQ = new LinkedList<PetEnterQueue>();
			this.count = 0;
		}

		public void add(Pet pet) {
			if (pet.getPetType().equals("dog")) {
				this.dogQ.add(new PetEnterQueue(pet, this.count++));
			} else if (pet.getPetType().equals("cat")) {
				this.catQ.add(new PetEnterQueue(pet, this.count++));
			} else {
				throw new RuntimeException("err, not dog or cat");
			}
		}

		public Pet pollAll() {
			if (!this.dogQ.isEmpty() && !this.catQ.isEmpty()) {
				if (this.dogQ.peek().getCount() < this.catQ.peek().getCount()) {
					return this.dogQ.poll().getPet();
				} else {
					return this.catQ.poll().getPet();
				}
			} else if (!this.dogQ.isEmpty()) {
				return this.dogQ.poll().getPet();
			} else if (!this.catQ.isEmpty()) {
				return this.catQ.poll().getPet();
			} else {
				throw new RuntimeException("err, queue is empty!");
			}
		}

		public Dog pollDog() {
			if (!this.isDogQueueEmpty()) {
				return (Dog) this.dogQ.poll().getPet();
			} else {
				throw new RuntimeException("Dog queue is empty!");
			}
		}

		public Cat pollCat() {
			if (!this.isCatQueueEmpty()) {
				return (Cat) this.catQ.poll().getPet();
			} else
				throw new RuntimeException("Cat queue is empty!");
		}

		public boolean isEmpty() {
			return this.dogQ.isEmpty() && this.catQ.isEmpty();
		}

		public boolean isDogQueueEmpty() {
			return this.dogQ.isEmpty();
		}

		public boolean isCatQueueEmpty() {
			return this.catQ.isEmpty();
		}

	}

	// QWL

	public static class Petd {
		private Pet pet;
		private boolean isVaild;

		public Petd() {
		}

		public Petd(Pet pet, boolean isVaild) {
			this.pet = pet;
			this.isVaild = isVaild;
		}

		public void setPet(Pet pet) {
			this.pet = pet;
		}

		public Pet getPet() {
			return pet;
		}

		public boolean isVaild() {
			return isVaild;
		}

		public void setVaild(boolean isVaild) {
			this.isVaild = isVaild;
		}
	}

	public static class DogCatQueue2 {
		private int catCount = 0;
		private int dogCount = 0;
		private Queue<Petd> petds = new LinkedList<>();

		public DogCatQueue2() {
		}

		
		public int getDogCount() {
			return dogCount;
		}
		
		public int getCatCount() {
			return catCount;
		}
		
		public boolean isDogQueueEmpty() {
			return dogCount <= 0 ? true : false;
		}

		public boolean isCatQueueEmpty() {
			return catCount == 0 ? true : false;
		}

		public boolean isEmpty() {
			return isCatQueueEmpty() && isDogQueueEmpty();
		}

		public void add(Pet pet) {
			if (pet.type.equals("cat")) {
				this.catCount++;
			} else if (pet.type.equals("dog")) {
				this.dogCount++;
			}
			petds.add(new Petd(pet, true));
		}

		public Pet pollAll() {
			if (isCatQueueEmpty() && isDogQueueEmpty()) {
				return null;
			}
			for (Petd petd : petds) {
				if (petd.isVaild() && petd.getPet().type.equals("cat")) {
					this.catCount--;
					petd.setVaild(false);
					return petd.getPet();
				} else if (petd.isVaild() && petd.getPet().type.equals("dog")) {
					this.dogCount--;
					petd.setVaild(false);
					return petd.getPet();
				}
			}
			return null;
		}

		public Pet pollDog() {
			if (isDogQueueEmpty()) {
				return null;
			}
			for (Petd petd : petds) {
				if (petd.isVaild() && petd.getPet().type.equals("dog")) {
					this.dogCount--;
					petd.setVaild(false);
					return petd.getPet();
				}
			}
			return null;
		}

		public Pet pollCat() {
			if (isCatQueueEmpty()) {
				return null;
			}
			for (Petd petd : petds) {
				if (petd.isVaild() && petd.getPet().type.equals("cat")) {
					this.catCount--;
					petd.setVaild(false);
					return petd.getPet();
				}
			}
			return null;
		}

	}

	public static void main(String[] args) {
		
		DogCatQueue test = new DogCatQueue();

		Pet dog1 = new Dog();
		Pet cat1 = new Cat();
		Pet dog2 = new Dog();
		Pet cat2 = new Cat();
		Pet dog3 = new Dog();
		Pet cat3 = new Cat();

		test.add(dog1);
		test.add(cat1);
		test.add(dog2);
		test.add(cat2);
		test.add(dog3);
		test.add(cat3);

		test.add(dog1);
		test.add(cat1);
		test.add(dog2);
		test.add(cat2);
		test.add(dog3);
		test.add(cat3);

		test.add(dog1);
		test.add(cat1);
		test.add(dog2);
		test.add(cat2);
		test.add(dog3);
		test.add(cat3);
		while (!test.isDogQueueEmpty()) {
			System.out.println(test.pollDog().getPetType());
		}
		while (!test.isEmpty()) {
			System.out.println(test.pollAll().getPetType());
		}
		
		System.out.println("====================================");
		
		
		
		DogCatQueue2 test2 = new DogCatQueue2();

		test2.add(dog1);
		test2.add(cat1);
		test2.add(dog2);
		test2.add(cat2);
		test2.add(dog3);
		test2.add(cat3);

		test2.add(dog1);
		test2.add(cat1);
		test2.add(dog2);
		test2.add(cat2);
		test2.add(dog3);
		test2.add(cat3);

		test2.add(dog1);
		test2.add(cat1);
		test2.add(dog2);
		test2.add(cat2);
		test2.add(dog3);
		test2.add(cat3);
		while (!test2.isDogQueueEmpty()) {
			System.out.println(test2.pollDog().getPetType());
		}
		while (!test2.isEmpty()) {
			System.out.println(test2.pollAll().getPetType());
		}
		System.out.println("====================================");

	}

}
