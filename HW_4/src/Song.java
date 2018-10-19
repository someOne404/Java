// Burgard Lu (jl4nq)
// Homework 4
// Source: Professor Basit & TA
// https://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/
// https://stackoverflow.com/questions/5585779/how-do-i-convert-a-string-to-an-int-in-java
// https://www.youtube.com/watch?v=78tYnmGKdM4
// http://www.vogella.com/tutorials/JavaRegularExpressions/article.html
// https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html

public class Song implements Playable, Comparable<Song>{

	private String artist; // the artist performing the song

	private String title; // the title of the song

	private int minutes; // number of min in length

	private int seconds; // number of seconds of the song (always less than 60)

	public Song(String artist, String title) {
		this.artist = artist;
		this.title = title;
		this.minutes = 0;
		this.seconds = 0;
	}

	public Song(String artist, String title, int minutes, int seconds) {
		this.artist = artist;
		this.title = title;
		this.minutes = minutes;
		this.seconds = seconds;
	}

	public Song(Song s) {
		this.artist = s.getArtist();
		this.title = s.getTitle();
		this.minutes = s.getMinutes();
		this.seconds = s.getSeconds();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// getters and setters 
	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public boolean equals(Object o) { /** two songs are equal if all four fields are equal **/
		if (o instanceof Song) {
			Song s = (Song) o;
			return (this.artist.equals(s.artist)) && (this.title.equals(s.title)) && (this.minutes==s.minutes) && (this.seconds==s.seconds); 
		}
		else
			return false;
	}

	public String toString() { /** return the song in string **/

		return "{Song: title = " + title + " artist = " + artist + "}";

	}

	public void play() { /** to show that the song has been played **/

		System.out.printf("Playing Song: artist = %-20s title = %s\n", artist, title);

	}

	@Override
	public String getName() { /** returns the title for Song **/
		return this.getTitle();
	}

	@Override
	public int getPlayTimeSeconds() { /** returns the number of seconds in the song **/
		int sum = (int) (this.getMinutes() * 60 + this.getSeconds());
		return sum;
	}

	@Override
	public int numberOfSongs() { /** returns the number of song **/
		return 1;
	}

	@Override
	public int compareTo(Song s) { /** compares songs based on their artists and titles, compare artists first, and then titles **/
		int retVal1 = this.artist.compareTo(s.artist);
		int retVal2 = this.title.compareTo(s.title);
		if (retVal1 == 0) {
			if (retVal2 !=0) return retVal2;
			else return 0;		
		}
		if (retVal1 != 0) {
			return retVal1;		
		}
		return 0;

	}	

}
