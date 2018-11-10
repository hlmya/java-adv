
package counter;

public class Counter {
	int counter = 0;

	int getNext() {
		++counter;
		return counter;
	}
}
