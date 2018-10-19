// Burgard Lu (jl4nq)
// Homework 4
// Source: Professor Basit & TA
// https://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/
// https://stackoverflow.com/questions/5585779/how-do-i-convert-a-string-to-an-int-in-java
// https://www.youtube.com/watch?v=78tYnmGKdM4
// http://www.vogella.com/tutorials/JavaRegularExpressions/article.html
// https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html

import java.util.Comparator;

public class CmpByName implements Comparator<Playable>{
	public int compare(Playable p1, Playable p2) {
		int retVal = p1.getName().compareTo(p2.getName());
		return retVal;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}


