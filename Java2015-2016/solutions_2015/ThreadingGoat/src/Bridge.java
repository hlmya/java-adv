
public class Bridge {
	private int length;

	public Bridge(int bridgeLength) {
		this.length = bridgeLength;
	}

	public int getLength() {
		return length;
	}

	public boolean isAtLowerEnd(int position) {
		return position == 0;
	}

	public boolean isAtUpperEnd(int position) {
		return position == length;
	}

}
