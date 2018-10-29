package training.uva.problems;

import java.util.Arrays;

import training.uva.config.Executable;

public class EcologicalBin102 implements Executable {
	private String[][] inputs;
	
	public EcologicalBin102() {
		String[] inputs = {
			"2 2 2 2 2 2 2 2 2",
			"10 90 16 78 1 2 3 100 1000",
			"0 0 0 0 0 0 0 0 0",
			"1 3 3 5 3 3 9 3 3",
			"69 804 872 531 431 698 692 480 859",
			"1 2 3 4 5 6 7 8 9",
			"1000 200 1500 350 5000 1000 1000 2000 13000",
			"5 10 5 20 10 5 10 20 10",
			"60 20 1000 1000 60 20 10000 20 500", 
			"20 1000 50 2000 50 500 1500 20 3000", 
			"20 1000 50 2000 50 5000 1500 20 30",
			"0 0 20 35 0 0 0 40 0"};
		this.inputs = new String[inputs.length][];
		for(int i = 0; i<inputs.length;i++) {
			this.inputs[i] = inputs[i].split(" ");
		}
	}

	@Override
	public void run() {
		for(int k = 0; k<inputs.length; k++) {
			String[] input = inputs[k];
			int[][] botts = new int[3][3];

			for (int i = 0; i < 3; i++) {
				botts[i][0] = Integer.parseInt(input[i * 3]);
				botts[i][1] = Integer.parseInt(input[(i * 3) + 1]);
				botts[i][2] = Integer.parseInt(input[(i * 3) + 2]);
			}

			int nMoved = sortBins(botts);
			int allZeros = 0;
			
			// Print result
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (botts[i][j] != 0) {

						// Verify color of bottle
						switch (j) {
						// Brown
						case 0:
							System.out.print("B");
							break;
							
						// Green
						case 1:
							System.out.print("G");
							break;
							
						// Clear
						case 2:
							System.out.print("C");
							break;
						}
					}
					else allZeros++;
				}
			}
			 
			// All zeros, print with correct lexographical order
			if(allZeros == 9) System.out.print("BCG");
			System.out.println(" "+nMoved);
			System.out.println("--------------------------");
		}
		
	

	}

	public int sortBins(int[][] bottles) {

		boolean[] doneL = { false, false, false };
		int[] sumLines = { 0, 0, 0 };
		int nextLine = -1;
		int bottlesMoved = 0;

		// Determine number of bottles in each bin
		for (int i = 0; i < 3; i++) {
			sumLines[i] += bottles[i][0] + bottles[i][1] + bottles[i][2];
		}

		// Find next biggest line that has not been finished
		nextLine = findMaxLineNotDone(sumLines, doneL);
		while (nextLine >= 0) {

			// Find Col number of biggest number on the line
			int maxInColIndex = findMaxColInLine(nextLine, bottles);

			// Move the bottles on the other bins into this one;
			bottlesMoved += moveBottlesTo(bottles, nextLine, maxInColIndex);

			// Mark as done
			doneL[nextLine] = true;

			// Repeat process
			nextLine = findMaxLineNotDone(sumLines, doneL);
		}

		return bottlesMoved;
	}

	private int moveBottlesTo(int[][] bottles, int i, int j) {
		int res = 0;
		switch (i) {
		// Top
		case 0:
			res += bottles[i + 1][j] + bottles[i + 2][j];
			bottles[i][j] += bottles[i + 1][j] + bottles[i + 2][j];
			bottles[i + 1][j] = 0;
			bottles[i + 2][j] = 0;
			break;

		// Mid
		case 1:
			res += bottles[i - 1][j] + bottles[i + 1][j];
			bottles[i][j] += bottles[i - 1][j] + bottles[i + 1][j];
			bottles[i - 1][j] = 0;
			bottles[i + 1][j] = 0;
			break;

		// Bot
		case 2:
			res += bottles[i - 1][j] + bottles[i - 2][j];
			bottles[i][j] += bottles[i - 1][j] + bottles[i - 2][j];
			bottles[i - 1][j] = 0;
			bottles[i - 2][j] = 0;
			break;
		}
		return res;
	}

	private int findMaxColInLine(int nextLine, int[][] bottles) {
		int max = 0;
		int maxI = -1;
		for (int j = 0; j < 3; j++) {
			if (bottles[nextLine][j] > max) {
				max = bottles[nextLine][j];
				maxI = j;
			}

		}
		return maxI;
	}

	private int findMaxLineNotDone(int[] sumLines, boolean[] doneL) {
		int find = -1;
		int max = 0;
		for (int i = 0; i < 3; i++) {
			if (sumLines[i] > max && doneL[i] == false)
				find = i;
		}
		return find;
	}

}
