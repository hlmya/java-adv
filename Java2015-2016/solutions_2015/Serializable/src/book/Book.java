
package book;

import java.io.Serializable;

public class Book implements Serializable {
	private String title;
	private String author;
	private int year;
	private int pageCount;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public Book(String title, String author, int year, int pageCount) {
		super();
		this.title = title;
		this.author = author;
		this.year = year;
		this.pageCount = pageCount;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", year=" + year + ", pageCount=" + pageCount + "]";
	}

}
