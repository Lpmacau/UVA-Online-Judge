package training.uva.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import training.uva.config.Executable;

public class Blocks101 implements Executable {

	@Override
	public void run() {
		String[] input1 = { "24", "move 2 onto 1", "move 5 over 1", "move 3 onto 2", "move 23 over 0",
				"pile 22 over 17", "move 4 onto 3", "move 5 over 1", "move 23 onto 3", "pile 1 over 10",
				"move 9 over 8", "move 11 over 8", "pile 3 over 8", "pile 8 over 3", "move 20 over 19",
				"pile 19 over 18", "pile 18 onto 15", "move 15 over 3", "pile 20 onto 19", "pile 19 onto 18",
				"pile 18 over 17", "quit" };

		String[] input2 = {"10",
				"move 9 onto 1",
				"move 8 over 1",
				"move 7 over 1",
				"move 6 over 1",
				"pile 8 over 6",
				"pile 8 over 5",
				"move 2 over 1",
				"move 4 over 9",
				"quit"};
		
		
		long start = System.currentTimeMillis();
		Map<Integer,Stack<Integer>> matrix = blocks(input1);
		long end = System.currentTimeMillis();
		
		
		for(Map.Entry<Integer, Stack<Integer>> entry : matrix.entrySet()) {
			System.out.print(entry.getKey()+":");
			entry.getValue().forEach(x -> System.out.print(" "+x));
			System.out.print("\n");
		}
		
		System.out.println(end - start + "ms");

	}

	private Map<Integer,Stack<Integer>> blocks(String[] input1) {
		int nBlocks = Integer.parseInt(input1[0]);
		Map<Integer, Stack<Integer>> blockRows = new HashMap<Integer, Stack<Integer>>();

		for (int i = 0; i < nBlocks; i++) {
			Stack<Integer> l = new Stack<Integer>();
			l.push(i);
			blockRows.put(i, l);
		}

		// Command1 A Command2 B
		// Run while found string "quit"
		for (int i = 1; !input1[i].equals("quit"); i++) {
			String[] parts = input1[i].split(" ");
			String command1 = parts[0];
			int blockA = Integer.parseInt(parts[1]);
			String command2 = parts[2];
			int blockB = Integer.parseInt(parts[3]);

			// If A = B -> ignore
			if (blockA == blockB)
				continue;

			// If blockRows[ix].contains(A) && contains(B) -> ignore
			int rowA = findInMatrix(blockRows, blockA);
			int rowB = findInMatrix(blockRows, blockB);
			if (rowA == rowB)
				continue;

			// Execute move command
			executeMove(blockRows,command1, command2, blockA, rowA, blockB, rowB);
		}
		
		return blockRows;
	}

	private void executeMove(Map<Integer, Stack<Integer>> blockRows, String command1, String command2, int blockA, int rowA, int blockB, int rowB) {
		switch (command1) {
		case "move":
			if (command2.equals("onto")) {
				// move A onto B
				// return blocks on top of A && B into ori. places
				// move A on top of blockRows[i] dat contains B
				returnToOrigins(blockRows,blockA,rowA);
				returnToOrigins(blockRows,blockB,rowB);
				moveSingle(blockRows,blockA,rowA,rowB);
			} else {
				// move A over B
				// return blocks on top of A into ori. places
				// move A on top of blockRows[i] dat contains B
				returnToOrigins(blockRows,blockA,rowA);
				moveSingle(blockRows,blockA,rowA,rowB);
			}
			break;

		case "pile":
			if (command2.equals("onto")) {
				// pile A onto B
				// return blocks on top of B into ori. places
				// move A and blocks above into top of blockRows[i] dat contains B
				returnToOrigins(blockRows,blockB,rowB);
				moveStack(blockRows,blockA,rowA,rowB);
			} else {
				// pile A over B
				// move A and blocks above into top of blockRows[i] dat contains B
				moveStack(blockRows,blockA,rowA,rowB);
			}
			break;
		}

	}

	private void moveStack(Map<Integer, Stack<Integer>> blockRows, int blockA, int rowA, int rowB) {
		try {
			// Pop blocks from rowA until blockA is found
			// 		Save this blocks into aux stack
			// 		push aux stack on top of blockRows[rowB]
			Stack<Integer> row = blockRows.get(rowA);
			Stack<Integer> aux = new Stack<Integer>();
			
			// While blockA isnt found, pop every block X found in row and save in aux
			while(row.peek() != blockA) {
				// Pop block on top on rowA
				aux.push(row.pop());
			}
			// Remove blockA and push it to rowB
			blockRows.get(rowB).push(row.pop());
			
			// Add aux stack to blockRows[rowB]
			while(!aux.empty()) {
				blockRows.get(rowB).push(aux.pop());
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	private void moveSingle(Map<Integer, Stack<Integer>> blockRows, int blockA, int rowA, int rowB) {
		try {
			// Move blockA found in rowA into top of rowB containing blockB
			int nBlockA = blockRows.get(rowA).pop();
			if(nBlockA != blockA) System.out.println("NAO SAO IGUAIS!?!?!");
			blockRows.get(rowB).push(blockA);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	private void returnToOrigins(Map<Integer, Stack<Integer>> blockRows, int blockA, int rowA) {
		try{
			Stack<Integer> row = blockRows.get(rowA);
			int currentPop;
			
			// While blockA isnt found, pop every block X found in rowA into their origin rows
			while(row.peek() != blockA) {
				// Pop block on top on rowA
				currentPop = row.pop();
				// Put in origin stack
				blockRows.get(currentPop).push(currentPop);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
	}

	public int findInMatrix(Map<Integer, Stack<Integer>> blockRows, int block) {
		int pos = -1;
		for (Map.Entry<Integer, Stack<Integer>> rowEntry : blockRows.entrySet()) {
			if (rowEntry.getValue().search((block)) != -1)
				return rowEntry.getKey();
		}
		return pos;
	}

}
