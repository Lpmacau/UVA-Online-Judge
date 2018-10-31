package training.uva.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import training.uva.config.Executable;
import training.uva.utils.Graph;

public class StackingBoxes103 implements Executable {
	private Map<Integer, List<List<Integer>>> boxes;
	private ArrayList<String> boxOrder;

	public StackingBoxes103() {
		String[] inputs = { "22 3", "44 71 27", "37 91 79", "96 10 74", "52 35 19", "40 98 85", "67 79 43", "93 29 13",
				"37 87 17", "75 24 74", "36 31 11", "89 80 84", "75 31 39", "20 88 87", "14 89 70", "25 30 93",
				"28 20 72", "11 30 78", "87 36 50", "54 72 21", "20 28 57", "68 50 25", "80 45 58", "5 2", "35 45",
				"72 49", "74 75", "65 84", "96 44", "29 1", "54", "13", "74", "42", "93", "72", "65", "37", "20", "97",
				"13", "77", "43", "93", "17", "44", "38", "54", "48", "98", "63", "40", "85", "81", "92", "62", "72",
				"96", "58", "26 6", "65 41 49 98 87 92", "13 26 17 30 75 65", "72 36 59 28 63 69", "56 11 47 95 77 84",
				"81 98 69 31 87 60", "27 14 52 68 32 68", "29 97 92 13 48 95", "19 25 53 38 79 14", "84 38 62 58 33 17",
				"32 83 76 48 81 23", "33 79 96 52 56 28", "10 79 55 18 15 91", "39 69 97 98 48 14", "52 23 79 18 39 72",
				"43 68 88 93 15 55", "10 89 44 81 50 58", "12 74 39 22 25 63", "65 39 94 59 63 16", "44 28 89 72 52 80",
				"76 53 80 42 77 35", "91 33 78 14 77 20", "22 14 99 98 35 55", "12 30 26 83 12 75", "83 45 64 23 66 85",
				"46 10 88 27 19 58", "83 73 51 89 15 93", "15 6", "82 64 50 42 36 40", "35 39 58 63 47 29",
				"57 77 49 37 67 73", "59 13 62 88 29 57", "10 99 50 30 17 93", "80 55 74 79 14 45", "46 40 55 98 91 13",
				"72 87 27 88 98 89", "33 32 12 30 86 45", "45 51 88 77 92 20", "50 12 42 88 35 87", "62 24 69 71 77 51",
				"21 15 58 48 71 31", "65 86 36 26 46 93", "97 64 27 49 16 58", "29 5", "23 30 83 49 59",
				"20 43 93 75 61", "33 72 97 74 13", "46 26 43 75 38", "82 30 76 15 88", "15 82 46 11 77",
				"59 98 62 58 52", "27 76 65 23 73", "51 29 83 59 15", "81 72 41 32 58", "66 10 37 98 49",
				"72 71 43 59 18", "19 60 68 95 50", "70 26 84 56 45", "81 37 45 56 42", "82 96 96 49 71",
				"81 94 78 69 69", "78 15 55 87 89", "19 35 43 37 42", "46 98 22 12 99", "99 30 90 60 18",
				"44 56 16 94 95", "59 57 25 64 74", "99 18 85 77 35", "38 99 83 58 25", "86 52 41 60 63",
				"50 35 60 10 25", "18 69 45 14 86", "50 12 58 94 10", "27 2", "39 26", "63 17", "64 46", "75 13",
				"26 25", "21 45", "15 22", "41 41", "98 92", "27 81", "37 65", "39 25", "53 50", "72 55", "12 42",
				"66 65", "10 96", "90 90", "93 77", "24 70", "64 49", "87 79", "33 99", "59 11", "49 43", "43 31",
				"76 85", "27 5", "35 20 10 91 64", "77 51 94 40 72", "81 32 58 39 36", "18 95 49 79 77",
				"87 62 61 95 85", "48 87 56 64 47", "53 98 32 20 24", "39 47 95 59 33", "40 30 95 90 68",
				"69 81 90 39 95", "43 75 56 19 23", "27 24 99 86 80", "10 20 29 84 57", "57 98 98 99 18",
				"48 17 94 66 20", "85 34 47 41 20", "66 40 15 59 71", "83 40 25 40 36", "58 88 96 74 98",
				"94 85 65 50 54", "93 79 43 62 17", "42 82 38 57 76", "53 17 73 98 12", "40 92 48 86 61",
				"18 21 89 73 39", "72 72 39 96 33", "31 58 51 90 65", "16 1", "36", "58", "18", "15", "37", "70", "72",
				"25", "88", "52", "73", "39", "26", "38", "56", "17", "9 9", "80 94 76 27 45 67 60 87 46",
				"94 87 22 65 51 72 58 41 68", "86 30 13 64 45 41 66 37 63", "32 50 94 82 93 80 43 95 32",
				"11 27 16 86 92 39 36 12 17", "87 14 10 46 88 79 21 61 51", "37 74 41 71 41 96 46 95 81",
				"94 88 28 81 46 49 26 65 45", "83 72 37 95 26 84 56 94 35" };

		String[] inputs2 = {"5 2","3 7","8 10","5 2","9 11","21 18"};
		
		String[] inputs3 = {"8 6",
				"5 2 20 1 30 10",
				"23 15 7 9 11 3",
				"40 50 34 24 14 4",
				"9 10 11 12 13 14",
				"31 4 18 8 27 17",
				"44 32 13 19 41 19",
				"1 2 3 4 5 6",
				"80 37 47 18 21 9"};
		
		int nBoxes, nDim = 0;
		this.boxes = new HashMap<Integer, List<List<Integer>>>();
		this.boxOrder = new ArrayList<String>();

		// Parse all input lines
		for (int i = 0, k = 0; i < inputs.length; i += nBoxes, k++) {
			String[] props = inputs[i].split(" ");
			String key = inputs[i];
			boxes.put(k, new ArrayList<List<Integer>>());
			boxOrder.add(key);

			// Parse number of boxes and dimensions
			nBoxes = Integer.parseInt(props[0]) + 1;
			nDim = Integer.parseInt(props[1]);

			for (int j = i + 1; j < i + nBoxes; j++) {
				String[] spliter = inputs[j].split(" ");
				int[] a = Arrays.stream(spliter).mapToInt(x -> Integer.parseInt(x)).toArray();
				List<Integer> listBox = new ArrayList<Integer>();
				Arrays.stream(a).forEach(x -> listBox.add(x));
				boxes.get(k).add(listBox);
			}

		}
	}

	@Override
	public void run() {

		// Create structure that will hold that graph data
		List<Graph> gra = new ArrayList<Graph>();
		
		List<List<List<Integer>>> boxesThatFit = new ArrayList<List<List<Integer>>>();

		// Order all entries by increasing value
		for (Map.Entry<Integer, List<List<Integer>>> e : boxes.entrySet()) {
			// Order each box values
			for (List<Integer> l : e.getValue()) {
				l.sort(Comparator.naturalOrder());
			}
			boxesThatFit.add(new ArrayList<List<Integer>>());
		}

		// Iterate over multiple inputs
		// for(int i = 0; i<boxes.size(); i++) {
		for (int i = 0; i < boxes.size(); i++) {
			List<List<Integer>> boxesOfInput = boxes.get(i);
			int l = boxesOfInput.size();

			// Create graph
			gra.add(new Graph(l));

			boxesThatFit.add(i, new ArrayList<List<Integer>>());
			for (int lol = 0; lol < l; lol++) {

				boxesThatFit.get(i).add(new ArrayList<Integer>());
			}

			// Iterate over every box on input i
			for (int j = 0; j < l; j++) {

				int itself = j;
				// Iterate against every other box of input i
				for (int k = 0; k < l; k++) {

					// Dont compare against itself
					if (itself == k)
						continue;

					// Obtain comparison of boxes (-1 == doesn't fit)
					int cmp = compareBoxes(boxesOfInput.get(j), boxesOfInput.get(k));

					// First box fits in second
					if (cmp > 0) {
						boxesThatFit.get(i).get(j).add(k);
						
						// Create edge
						gra.get(i).addEdge(j,k);
					}

					// System.out.println(cmp + " "+boxesOfInput.get(j)+" "+ boxesOfInput.get(k));
				}
			}

			/*
			 * List<List<Integer>> boxThatFitsIn = boxesThatFit.get(i); List<Integer> res =
			 * new ArrayList<Integer>(); List<Integer> ignore = new ArrayList<Integer>();
			 * int maiorSeq = 0; int maiorSeqI = 0; for(int c = 0; c<boxThatFitsIn.size();
			 * c++) { if(boxThatFitsIn.get(c).size() > maiorSeq) { maiorSeqI = c; maiorSeq =
			 * boxThatFitsIn.get(c).size(); } }
			 * 
			 * res.add(maiorSeqI+1); ignore.add(maiorSeqI);
			 * 
			 * 
			 * int next = findBestMatch(boxThatFitsIn, maiorSeqI,ignore); while(next != -1)
			 * { res.add(next+1); ignore.add(next); next = findBestMatch(boxThatFitsIn,
			 * next,ignore); }
			 * 
			 * System.out.println(res.size()); System.out.println(res);
			 */
		
		}
		
		//gra.get(0).topologicalSort();
		gra.get(0).longerPath();
		//graphs.get(1).printGraph();
		System.out.println("finito");

	}

	private int findBestMatch(List<List<Integer>> boxThatFitsIn, int maiorSeqI, List<Integer> ignore) {
		int percentageBest = 0;
		int matchesBest = 0;
		int matchBestI = -1;
		int nMatches = 0;

		List<Integer> bigCopy = new ArrayList<Integer>();
		bigCopy.addAll(boxThatFitsIn.get(maiorSeqI));

		// Find the sequence with biggest match
		for (int i = 0; i < bigCopy.size(); i++) {
			// If we don't need to ignore
			if (!ignore.contains(i)) {
				nMatches = getNumberMatches(boxThatFitsIn.get(i), bigCopy);

				// Check if number of matches if bigger
				if (nMatches > matchesBest) {
					matchesBest = nMatches;
					matchBestI = i;
				} else if (nMatches == matchesBest) {
					System.out.println("Conflict! " + i + " e " + maiorSeqI);
				}
			}
		}
		return matchBestI;
	}

	private int getNumberMatches(List<Integer> list, List<Integer> bigCopy) {

		int nMatches = 0;
		int noMatch = 0;

		for (int i = 0; i < list.size(); i++) {
			if (bigCopy.contains(list.get(i)))
				nMatches++;
			else
				noMatch++;
		}

		return nMatches;
	}

	private int compareBoxes(List<Integer> list, List<Integer> list2) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).compareTo(list2.get(i)) > 0)
				return -1;
		}
		return 1;
	}

}
