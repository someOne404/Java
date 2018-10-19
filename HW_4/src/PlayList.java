// Burgard Lu (jl4nq)
// Homework 4
// Source: Professor Basit & TA
// https://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/
// https://stackoverflow.com/questions/5585779/how-do-i-convert-a-string-to-an-int-in-java
// https://www.youtube.com/watch?v=78tYnmGKdM4
// http://www.vogella.com/tutorials/JavaRegularExpressions/article.html
// https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html
import java.util.*;
import java.io.*;
public class PlayList implements Playable{

	private String name; // contains the name of the playlist

	private ArrayList<Playable> playableList; // ArrayList of Playable elements that make up the playlist

	public PlayList() { // empty playlist named "Untitled"
		this.name = "Untitled";
		this.playableList = new ArrayList<Playable>();
	}

	public PlayList(String newName) { // empty playlist
		this.name = newName;
		this.playableList = new ArrayList<Playable>();
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// Setters and Getters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Playable> getPlayableList() {
		return playableList;
	}

	public void setPlayableList(ArrayList<Playable> playableList) {
		this.playableList = playableList;
	}

	@Override
	public String toString(){ /** returns the PlayList in string type **/
		String result = "";
		for (int i = 0; i<playableList.size(); i++) {
			if (playableList.get(i) instanceof PlayList) {
				PlayList pl = (PlayList) playableList.get(i);
				for (int j = 0; j<pl.getPlayableList().size(); j++) {
					result = result + pl.getPlayableList().get(j).getName() + ": " + Integer.toString(pl.getPlayableList().get(j).getPlayTimeSeconds())+ " " + Integer.toString(pl.getPlayableList().get(j).numberOfSongs()) + "\n";
				}

			}
			else
				result = result + playableList.get(i).getName() + ": " + Integer.toString(playableList.get(i).getPlayTimeSeconds())+ " " + Integer.toString(playableList.get(i).numberOfSongs()) + "\n";
		}
		return result;
	}

	public boolean loadSongs(String fileName) { /** loads songs from text file **/
		File temp = new File(fileName);
		try {
			Scanner file = new Scanner(temp);
			while (file.hasNextLine()) {
				String playableTitle = file.nextLine().trim();
				//System.out.println(playableTitle);
				String playableArtist = file.nextLine().trim();
				//System.out.println(playableArtist);
				String shownTime = file.nextLine().trim();
				//System.out.println(shownTime);
				String[] numString = shownTime.split(":");
				int min = Integer.parseInt(numString[0]);
				int sec = Integer.parseInt(numString[1]);
				int total = min * 60 + sec;
				int minutes = (int) total / 60;
				int seconds = total - minutes*60;
				file.nextLine();	
				Song s = new Song(playableArtist, playableTitle, minutes, seconds);
				playableList.add(s);

			}
			file.close();
			return true;
		}
		catch (FileNotFoundException e) {
			System.out.println("File Not Found!");
			e.printStackTrace();
			return false;
		}


	}

	public boolean clear() { /** removes all playable elements in the playlist **/
		int before = playableList.size();
		playableList.clear();
		int after = playableList.size();
		if (before != after) return true;
		else return false;

	}

	public boolean addSong(Song s) { /** adds Song s to the end of the play list **/
		int before = playableList.size();
		playableList.add(s);
		int after = playableList.size();
		if (before != after) return true;
		else return false;

	}

	public Playable removePlayable(int index) {/** removes Playable element at index from the list and returns it**/
		Playable removed = playableList.get(index);
		playableList.remove(index);
		return removed;
	}

	public Playable removePlayable(Playable p) {/** removes every occurrence of Playable p from the list and returns p **/
		for (int i = 0; i < playableList.size(); i++) {
			if (playableList.get(i).equals(p)) {
				playableList.remove(playableList.get(i));
			}
		}
		return p;

	}

	public Playable getPlayable(int index) {/** returns the Playable element at the appropriate index **/
		if (index < playableList.size() && index >= 0) {
			Playable wanted = playableList.get(index);
			return wanted;
		}
		else 
			return null;

	}


	public boolean addPlayList(PlayList pl) {/** adds the PlayList that is being passed to the end of the playableList and returns true unless the playableList already contains a playlist with the same name **/
		for (Playable p: playableList) {
			if (p instanceof PlayList && p.getName().equals(pl.getName())) {
				return false;
			}		
		}
		playableList.add(pl);
		return true;

	}

	public void play() { /** plays the playlist by calling play() on each item in the playlist in order **/
		for (int i = 0; i < playableList.size(); i++) {
			if (playableList.get(i) instanceof PlayList) {
				PlayList pl = (PlayList) playableList.get(i);
				for (int j = 0; j < pl.getPlayableList().size(); j++) {
					pl.getPlayableList().get(j).play();;
				}
			}
			else 
				playableList.get(i).play();
		}

	}

	public void sortByName() { /** sort the Playable items by the value returned by getName() in ascending order **/
		Collections.sort(this.playableList, new CmpByName());
	}

	public void sortByTime() { /** sort the Playable items by the Song or PlayList's total time in seconds, in ascending order (shortest first) **/
		Collections.sort(this.playableList, new CmpByTime());
	}

	@Override
	public int getPlayTimeSeconds() { /** calculate the total length of the playables in the playlist (in seconds) **/
		int sum = 0;
		for (int i = 0; i < playableList.size(); i++) {
			if (playableList.get(i) instanceof PlayList) {
				PlayList pl = (PlayList) playableList.get(i);
				for (int j = 0; j < pl.getPlayableList().size(); j++) {
					sum += pl.getPlayableList().get(j).getPlayTimeSeconds();
				}
			}
			else 
				sum += playableList.get(i).getPlayTimeSeconds();
		}
		return sum;
	}

	@Override
	public int numberOfSongs() { /** calculate the number of songs in the entire playlist **/
		int sum = 0;
		for (int i = 0; i < playableList.size(); i++) {
			if (playableList.get(i) instanceof PlayList) {
				PlayList pl = (PlayList) playableList.get(i);
				for (int j = 0; j < pl.getPlayableList().size(); j++) {
					sum += pl.getPlayableList().get(j).numberOfSongs();
				}
			}
			else 
				sum += playableList.get(i).numberOfSongs();
		}
		return sum;
	}

	//extra work
	public boolean equals(Object o) { /** two Playables are equal if name, playTimeSeconds and numberOfSongs are equal **/
		if (o instanceof Playable) {
			Playable p = (Playable) o;
			return (this.getName().equals(p.getName())) && (this.getPlayTimeSeconds()== p.getPlayTimeSeconds()) && (this.numberOfSongs() == p.numberOfSongs());
		}
		else
			return false;
	}





}
