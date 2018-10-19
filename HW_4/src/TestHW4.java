// Burgard Lu (jl4nq)
// Homework 4
// Source: Professor Basit & TA
// https://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/
// https://stackoverflow.com/questions/5585779/how-do-i-convert-a-string-to-an-int-in-java
// https://www.youtube.com/watch?v=78tYnmGKdM4
// http://www.vogella.com/tutorials/JavaRegularExpressions/article.html
// https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestHW4 {

	//Song
	@Test
	public void testEquals() {
		Song s1 = new Song("Lady Gaga", "Poker face", 3, 58);
		Song s2 = new Song("Taylor Swift", "Gorgeous", 3, 29);
		Song s3 = new Song("Lady Gaga", "Poker face", 3, 58);
		assertFalse(s1.equals(s2));
		assertTrue(s1.equals(s3));
	}

	@Test
	public void testToStringSong() { 
		Song s1 = new Song("Lady Gaga", "Poker face");
		Song s2 = new Song("Taylor Swift", "Gorgeous");
		String result1 = s1.toString();
		String result2 = s2.toString();
		assertEquals("{Song: title = Poker face artist = Lady Gaga}",result1);
		assertEquals("{Song: title = Gorgeous artist = Taylor Swift}",result2);


	}


	@Test
	public void testCompareToSong() {
		Song s1 = new Song("Lady Gaga", "Born This Way", 4, 20);
		Song s2 = new Song("Lady Gaga", "Telephone", 3, 41);
		Song s3 = new Song("Charlie Puth", "We Don't Talk Anymore", 3, 37);

		int result1 = s1.compareTo(s2);
		int result2 = s1.compareTo(s3);
		assertEquals(result1, -18);
		assertEquals(result2, 9);
	}


	@Test
	public void testGetNameSong() {
		Song s1 = new Song("Lady Gaga", "Poker face", 3, 58);
		Song s2 = new Song("Taylor Swift", "Gorgeous", 3, 29);
		String result1 = s1.getName();
		String result2 = s2.getName();
		assertEquals("Poker face", result1);
		assertEquals("Gorgeous", result2);

	}

	@Test
	public void testGetPlayTimeSecondsSong() {
		Song s1 = new Song("Lady Gaga", "Poker face", 3, 58);
		Song s2 = new Song("Taylor Swift", "Gorgeous", 3, 29);
		int result1 = s1.getPlayTimeSeconds();
		int result2 = s2.getPlayTimeSeconds();
		assertEquals(238, result1);
		assertEquals(209, result2);
	}

	@Test
	public void testNumberOfSongsSong() {
		Song s1 = new Song("Lady Gaga", "Poker face", 3, 58);
		Song s2 = new Song("Taylor Swift", "Gorgeous", 3, 29);
		int result1 = s1.numberOfSongs();
		int result2 = s2.numberOfSongs();
		assertEquals(1, result1);
		assertEquals(1, result2);
	}

	//PlayList
	@Test
	public void testToStringPlayList() {		
		PlayList pl1 = new PlayList("PlayList 1");
		PlayList pl2 = new PlayList("PlayList 2"); // to be added to pl1
		ArrayList<Playable> playable1 = new ArrayList<Playable>();
		ArrayList<Playable> playable2 = new ArrayList<Playable>();
		Song s1 = new Song("Lady Gaga", "Poker face", 3, 58);
		Song s2 = new Song("Taylor Swift", "Gorgeous", 3, 29);
		Song s3 = new Song("Charlie Puth", "We Don't Talk Anymore", 3, 37);
		playable1.add(s1);
		playable2.add(s2);
		playable2.add(s3);
		pl1.setPlayableList(playable1);
		pl2.setPlayableList(playable2);

		String output1 = "Poker face: 238 1\n";
		assertEquals(output1, pl1.toString());

		pl1.addPlayList(pl2);
		String output2 = "Poker face: 238 1\nGorgeous: 209 1\nWe Don't Talk Anymore: 217 1\n";
		assertEquals(output2, pl1.toString());
	}


	@Test
	public void testLoadSongs() {
		PlayList pl = new PlayList();
		assertTrue(pl.loadSongs("MyFile.txt"));
		assertFalse(pl.loadSongs("MyFile2.txt"));

	}

	@Test
	public void testClear() {
		PlayList pl1 = new PlayList("List 1");
		PlayList pl2 = new PlayList("List 2");
		ArrayList<Playable> playable1 = new ArrayList<Playable>();
		ArrayList<Playable> playable2 = new ArrayList<Playable>();
		Song s1 = new Song("Lady Gaga", "Poker face", 3, 58);
		Song s2 = new Song("Taylor Swift", "Gorgeous", 3, 29);
		playable1.add(s1);
		playable1.add(s2);
		pl1.setPlayableList(playable1);
		pl2.setPlayableList(playable2);
		boolean result1 = pl1.clear();
		boolean result2 = pl2.clear();
		assertTrue(result1);
		assertFalse(result2);


	}

	@Test
	public void testAddSong() {
		PlayList pl1 = new PlayList("List 1");
		ArrayList<Playable> playable1 = new ArrayList<Playable>();
		Song s1 = new Song("Lady Gaga", "Poker face", 3, 58);
		Song s2 = new Song("Taylor Swift", "Gorgeous", 3, 29);
		playable1.add(s1);
		pl1.setPlayableList(playable1);
		assertTrue(pl1.addSong(s1));
		assertTrue(pl1.addSong(s2));

	}

	@Test
	public void testRemovePlayable1() {
		PlayList pl1 = new PlayList("List 1");
		ArrayList<Playable> playable1 = new ArrayList<Playable>();
		Song s1 = new Song("Lady Gaga", "Poker face", 3, 58);
		Song s2 = new Song("Taylor Swift", "Gorgeous", 3, 29);
		playable1.add(s1);
		playable1.add(s2);
		pl1.setPlayableList(playable1);
		pl1.addSong(s1);
		pl1.addSong(s2);
		assertEquals(pl1.getPlayableList().get(1), pl1.removePlayable(1));
		assertEquals(pl1.getPlayableList().get(2), pl1.removePlayable(2));

	}

	@Test
	public void testRemovePlayable2() {
		PlayList pl1 = new PlayList("List 1");
		ArrayList<Playable> playable1 = new ArrayList<Playable>();
		Song s1 = new Song("Lady Gaga", "Poker face", 3, 58);
		Song s2 = new Song("Taylor Swift", "Gorgeous", 3, 29);
		playable1.add(s1);
		playable1.add(s2);
		pl1.setPlayableList(playable1);
		pl1.addSong(s1);
		pl1.addSong(s2);
		assertEquals(s1, pl1.removePlayable(s1));
		assertEquals(s2, pl1.removePlayable(s2));


	}

	@Test
	public void testGetPlayable() {
		PlayList pl1 = new PlayList("List 1");
		ArrayList<Playable> playable1 = new ArrayList<Playable>();
		Song s1 = new Song("Lady Gaga", "Poker face", 3, 58);
		Song s2 = new Song("Taylor Swift", "Gorgeous", 3, 29);
		playable1.add(s1);
		playable1.add(s2);
		pl1.setPlayableList(playable1);
		assertEquals(s1, pl1.getPlayable(0));
		assertEquals(s2, pl1.removePlayable(1));
	}

	@Test
	public void testAddPlayList() {
		PlayList pl1 = new PlayList("List 1");
		PlayList pl2 = new PlayList("List 2"); // to be added to pl1
		PlayList pl3 = new PlayList("List 2");
		ArrayList<Playable> playable1 = new ArrayList<Playable>();
		ArrayList<Playable> playable2 = new ArrayList<Playable>();
		Song s1 = new Song("Lady Gaga", "Poker face", 3, 58);
		Song s2 = new Song("Taylor Swift", "Gorgeous", 3, 29);
		Song s3 = new Song("Charlie Puth", "We Don't Talk Anymore", 3, 37);
		playable1.add(s1);
		playable2.add(s2);
		playable2.add(s3);
		pl1.setPlayableList(playable1);
		pl2.setPlayableList(playable2);
		assertTrue(pl1.addPlayList(pl2));
		assertFalse(pl1.addPlayList(pl3));
	}


	@Test
	public void testSortByName() {
		PlayList pl1 = new PlayList("PlayList 1");
		PlayList pl2 = new PlayList("PlayList 2"); // to be added to pl1
		ArrayList<Playable> playable1 = new ArrayList<Playable>();
		ArrayList<Playable> playable2 = new ArrayList<Playable>();
		Song s1 = new Song("Lady Gaga", "Telephone", 3, 41);
		Song s2 = new Song("Taylor Swift", "Gorgeous", 3, 29);
		Song s3 = new Song("Charlie Puth", "We Don't Talk Anymore", 3, 37);
		Song s4 = new Song("Adam Lambert", "Outlaws of Love", 3, 47);
		playable1.add(s1);
		playable1.add(s4);
		playable2.add(s2);
		playable2.add(s3);
		pl1.setPlayableList(playable1);
		pl2.setPlayableList(playable2);

		pl1.addPlayList(pl2);
		pl1.sortByName();
		pl2.sortByName();

		PlayList resultList1 = new PlayList("PlayList 1");
		PlayList resultList2 = new PlayList("PlayList 2"); 
		ArrayList<Playable> result1 = new ArrayList<Playable>();
		ArrayList<Playable> result2 = new ArrayList<Playable>();

		result1.add(s4);
		resultList1.setPlayableList(result1);
		resultList1.addPlayList(resultList2);
		resultList1.addSong(s1);

		result2.add(s2);
		result2.add(s3);
		resultList2.setPlayableList(result2);
		assertEquals(resultList1, pl1);
		assertEquals(resultList2, pl2);
	}

	@Test
	public void testSortByTime() {
		PlayList pl1 = new PlayList("PlayList 1");
		PlayList pl2 = new PlayList("PlayList 2"); // to be added to pl1
		ArrayList<Playable> playable1 = new ArrayList<Playable>();
		ArrayList<Playable> playable2 = new ArrayList<Playable>();
		Song s1 = new Song("Lady Gaga", "Telephone", 3, 41);
		Song s2 = new Song("Taylor Swift", "Gorgeous", 3, 29);
		Song s3 = new Song("Charlie Puth", "We Don't Talk Anymore", 3, 37);
		Song s4 = new Song("Adam Lambert", "Outlaws of Love", 3, 47);
		playable1.add(s4);
		playable1.add(s1);
		playable2.add(s2);
		playable2.add(s3);
		pl1.setPlayableList(playable1);
		pl2.setPlayableList(playable2);

		pl1.addPlayList(pl2);
		pl1.sortByName();
		pl2.sortByName();

		PlayList resultList1 = new PlayList("PlayList 1");
		PlayList resultList2 = new PlayList("PlayList 2"); 
		ArrayList<Playable> result1 = new ArrayList<Playable>();
		ArrayList<Playable> result2 = new ArrayList<Playable>();

		result1.add(s1);
		result1.add(s4);
		resultList1.setPlayableList(result1);
		resultList1.addPlayList(resultList2);

		result2.add(s2);
		result2.add(s3);
		resultList2.setPlayableList(result2);
		assertEquals(resultList1, pl1);
		assertEquals(resultList2, pl2);




	}


	@Test
	public void testGetNamePlayList() {
		PlayList pl1 = new PlayList("PlayList 1");
		PlayList pl2 = new PlayList(); 
		assertEquals("PlayList 1", pl1.getName());
		assertEquals("Untitled", pl2.getName());
	}

	@Test
	public void testGetPlayTimeSecondsPlayList() {
		PlayList pl1 = new PlayList("PlayList 1");
		PlayList pl2 = new PlayList("PlayList 2"); // to be added to pl1
		ArrayList<Playable> playable1 = new ArrayList<Playable>();
		ArrayList<Playable> playable2 = new ArrayList<Playable>();
		Song s1 = new Song("Lady Gaga", "Poker face", 3, 58);
		Song s2 = new Song("Taylor Swift", "Gorgeous", 3, 29);
		Song s3 = new Song("Charlie Puth", "We Don't Talk Anymore", 3, 37);
		playable1.add(s1);
		playable2.add(s2);
		playable2.add(s3);
		pl1.setPlayableList(playable1);
		pl2.setPlayableList(playable2);

		int result1 = 238;
		int result2 = 3 * 60 + 29 + 3 * 60 + 37;
		assertEquals(result1, pl1.getPlayTimeSeconds());
		assertEquals(result2, pl2.getPlayTimeSeconds());

	}

	@Test
	public void testNumberOfSongsPlayList() {
		PlayList pl1 = new PlayList("PlayList 1");
		PlayList pl2 = new PlayList("PlayList 2"); // to be added to pl1
		ArrayList<Playable> playable1 = new ArrayList<Playable>();
		ArrayList<Playable> playable2 = new ArrayList<Playable>();
		Song s1 = new Song("Lady Gaga", "Poker face", 3, 58);
		Song s2 = new Song("Taylor Swift", "Gorgeous", 3, 29);
		Song s3 = new Song("Charlie Puth", "We Don't Talk Anymore", 3, 37);
		playable1.add(s1);
		playable2.add(s2);
		playable2.add(s3);
		pl1.setPlayableList(playable1);
		pl2.setPlayableList(playable2);

		int result1 = 1;
		int result2 = 3;

		assertEquals(result1, pl1.numberOfSongs());
		pl1.addPlayList(pl2);
		assertEquals(result2, pl1.numberOfSongs());
	}




}
