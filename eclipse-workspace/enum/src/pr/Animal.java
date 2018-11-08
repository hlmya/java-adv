package pr;

public class Animal {

//	public static final int DOG = 0; // We dont need const when use ENUM
	
	public static void main(String[] args) {
		AnimalEnum animal = AnimalEnum.DOG;
		
		switch(animal) {
		case CAT:
			break;
		case DOG:
			System.out.println("Switch-case DOG");
			break;
		case MOUSE:
			break;
		default:
			break;
		}
		
		System.out.println("AnimalEnum.DOG: " + AnimalEnum.DOG); // DOG
		System.out.println("AnimalEnum.DOG.toString1: " + AnimalEnum.DOG.toString1()); //This animal is called: choco
		System.out.println("DOG.getname(): " + AnimalEnum.DOG.getValue()); //choco
		System.out.println("DOG.getClass(): " + AnimalEnum.DOG.getClass()); //class pr.AnimalEnum
		System.out.println("DOG.ordinal(): " + AnimalEnum.DOG.ordinal()); // 1 (index)
		System.out.println(AnimalEnum.DOG instanceof Enum); // true
		
		AnimalEnum animal2 = AnimalEnum.valueOf("MOUSE");
		System.out.println(animal2); //MOUSE
		
		AnimalEnum animal3 = AnimalEnum.getEnumNameByEnumValue("nino");
		System.out.println(animal3 instanceof Enum); // true
		System.out.println(animal3); //CAT
		System.out.println(animal3.toString()); //CAT
	}

}
