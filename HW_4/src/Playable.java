// Burgard Lu (jl4nq)
// Homework 4
// Source: Professor Basit & TA
// https://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/
// https://stackoverflow.com/questions/5585779/how-do-i-convert-a-string-to-an-int-in-java
// https://www.youtube.com/watch?v=78tYnmGKdM4
// http://www.vogella.com/tutorials/JavaRegularExpressions/article.html
// https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html

public interface Playable {
	public void play();

	public String getName(); // returns the name (for PlayList) or title (for Song) of Playable object.

	public int getPlayTimeSeconds(); // For Song: returns the number of seconds in the song. For PlayList: returns the number of seconds in the entire PlayList 

	public int numberOfSongs(); // For Song: returns 1.  For PlayList returns the number of songs in the playlist and all playlists contained within.  

}
