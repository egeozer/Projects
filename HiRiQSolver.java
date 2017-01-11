package proj;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class HiRiQSolver {
	static boolean blackSubs = false;	//condition that will apply either white or black substitution
	static HashSet<Integer> boardSet = new HashSet<Integer>();	//HashSet that contains a table of only unique nodes
																//to avoid dublicates for memory management and to
																//get to the solution faster

	public static void main(String[] args) {

		boolean[] B= new boolean[33];
		HiRiQ W=new HiRiQ((byte) 0) ;

		HiRiQ X=new HiRiQ((byte) 1) ;

		HiRiQ Y=new HiRiQ((byte) 2) ;

		HiRiQ Z=new HiRiQ((byte) 4) ;

		Y.load(B);


		System.out.print(solve(B));	

	}
	
	//substitute method checks for any possible move that's possible on the board given with indexes at a, b and c.
	//if a move is possible, it is then stored in the arrayList possibleMoves. The possible moves is checked either
	//if the condition black is on. If on, black substutions will be made, if off, white substitutions are made

	public static void substitute(boolean [] board, int a, int b, int c,ArrayList<boolean[]> possibleMoves,boolean black){
		boolean[]boardclone = new boolean[33];	//this clone array is made to not change the original array values
		for(int i = 0 ;i<33;i++)
			boardclone[i]=board[i];
		if(black){	//black substitutions
			if((!boardclone[a] && !boardclone[b] && boardclone[c] )
					||( boardclone[a] && !boardclone[b] && !boardclone[c]) ){
				boardclone[a]=!boardclone[a];
				boardclone[b]=!boardclone[b];
				boardclone[c]=!boardclone[c];
				possibleMoves.add(boardclone);
			}

		}
		else{	//white substitutions
			if((!boardclone[a] && boardclone[b] && boardclone[c] )
					||( boardclone[a] && boardclone[b] && !boardclone[c]) ){
				boardclone[a]=!boardclone[a];
				boardclone[b]=!boardclone[b];
				boardclone[c]=!boardclone[c];

				possibleMoves.add(boardclone);
			}
		}

	}

	//checkTriplet method calls the substitute method to check all the triplets if either a white substitution or a 
	//black substitution can be applied depending if BlackSubs is false or not. The specific board is taken as input and
	//returns the ArrayList of all possible moves on the given board.
	public static ArrayList<boolean[]> checkTriplet(boolean B[]){

		ArrayList<boolean[]> possibleMoves = new ArrayList<boolean[]>();

		boolean black = blackSubs;

		substitute(B,0,1,2,possibleMoves,black);
		substitute(B, 3 , 4 , 5 ,possibleMoves,black);   
		substitute(B, 6 , 7 , 8,possibleMoves,black);   
		substitute(B, 7, 8, 9 ,possibleMoves,black);   
		substitute(B, 8 , 9 ,10,possibleMoves,black);   
		substitute(B, 9 ,10,11,possibleMoves,black);   
		substitute(B,10,11,12,possibleMoves,black);   
		substitute(B,13,14,15,possibleMoves,black);   
		substitute(B,14,15,16,possibleMoves,black);   
		substitute(B,15,16,17,possibleMoves,black);   
		substitute(B,16,17,18,possibleMoves,black);   
		substitute(B,17,18,19,possibleMoves,black);   
		substitute(B,20,21,22,possibleMoves,black);   
		substitute(B,21,22,23,possibleMoves,black);   
		substitute(B,22,23,24,possibleMoves,black);   
		substitute(B,23,24,25,possibleMoves,black);   
		substitute(B,24,25,26,possibleMoves,black);   
		substitute(B,27,28,29,possibleMoves,black);   
		substitute(B,30,31,32,possibleMoves,black);   
		substitute(B,12,19,26,possibleMoves,black);   
		substitute(B,11,18,25,possibleMoves,black);   
		substitute(B, 2 , 5 ,10,possibleMoves,black);   
		substitute(B, 5 ,10,17,possibleMoves,black);   
		substitute(B,10,17,24,possibleMoves,black);   
		substitute(B,17,24,29,possibleMoves,black);   
		substitute(B,24,29,32,possibleMoves,black);   
		substitute(B, 1 , 4 , 9 ,possibleMoves,black);   
		substitute(B, 4 , 9 ,16,possibleMoves,black);   
		substitute(B, 9 ,16,23,possibleMoves,black);   
		substitute(B,16,23,28,possibleMoves,black);   
		substitute(B,23,28,31,possibleMoves,black);   
		substitute(B, 0 , 3 , 8 ,possibleMoves,black);   
		substitute(B, 3 , 8 ,15,possibleMoves,black);   
		substitute(B, 8 ,15,22,possibleMoves,black);   
		substitute(B,15,22,27,possibleMoves,black);   
		substitute(B,22,27,30,possibleMoves,black);   
		substitute(B, 7 ,14,21,possibleMoves,black);   
		substitute(B, 6 ,13,20,possibleMoves,black); 

		return possibleMoves;

	}
	//this method makes the tree for the Breadth First search. It takes as input an array of possible moves(see check triplet) 
	//on a specific board. Depending on the possible number of moves, nodes of the tree are made and the queue of the
	//children are made aswell. The last input is for the hashtable. boardConfig has the unique configuration for each
	//boolean array. This is used as keys(for hashtable)
	public static void addToChildren(ArrayList<boolean[]>possibleMoves, Node node, Queue<Node> queue, int boardConfig){
		//Set<Integer> encounteredNumbers = new HashSet<Integer>();

		Integer key = new Integer(boardConfig); //every board has a specific key
		if (!boardSet.contains(key))	//if hashtable already has the current key, then nothing is added into node
		{
			boardSet.add(key);

			for(int i  = 0;i<possibleMoves.size();i++){

				node.addNode(possibleMoves.get(i));
			}
			ArrayList<Node> children = node.getChildren();

			for(int i  = 0; i<children.size();i++){	//queue is determined depending on the possible moves on the current board
				queue.add(children.get(i));
			}
		}
		
	}
	//This method will make the string steps and print the visual steps when there is a solution.
	//
	public static String steps(Node node ){
		String all = "";
		boolean[] arrays3 = new boolean[33];
		boolean[] arrays = new boolean[33];
		boolean[] arrays2 = new boolean[33];
		ArrayList<Integer> triplet = new ArrayList<Integer>();
		ArrayList<String> stringArray = new ArrayList<String>();
		ArrayList<HiRiQ> stepsBoard = new ArrayList<HiRiQ>();

		boolean isWhite = false;
		int c1 = 0;	//counter for the current board
		int c2 = 0;	//counter for the parent board

		while(node.parent!=null){
			arrays=node.getBoard();		//current node
			arrays2=node.parent.getBoard();	//previous parent node
			
			for(int i = 0;i<33;i++){
				if(arrays[i])
					c1++;
				if(arrays2[i])
					c2++;

			}
			if(c2>c1)	//this condition checks if there is more whites on the previous node to determine if a
				//white substitution or a black substitution is made
				isWhite = true;
			else 
				isWhite=false;;
				//here, both previous node and current boards are checked and compared to see which triplets have
				//been changed
				for(int i = 0;i<33;i++){
					if(arrays[i]!=arrays2[i]){
						triplet.add(i);
					}
				}

				if(isWhite)
					stringArray.add(","+triplet.get(0)+"W"+triplet.get(2));
				else
					stringArray.add(","+triplet.get(0)+"B"+triplet.get(2));

				triplet.clear();
				HiRiQ W=new HiRiQ((byte) 0) ;
				W.load(arrays3);
				W.store(node.getBoard());
				stepsBoard.add(W);
				//W.print();
				node=node.parent;
				isWhite=false;
				c1=0;
				c2=0;
		}
		//each step to the solution is visually represented here
		System.out.println("solved with the following steps: ");
		
		for(int i = stringArray.size()-1; i>=0;i--){
			all+=stringArray.get(i);
			System.out.println("Step"+(stringArray.size()-i));
			stepsBoard.get(i).print();
			System.out.println("=====================");
			
			
		}
		System.out.println("Steps in String representation are ...");
		return all;

	}


	//This method will solve the board by looking at each node, to all childrens of the tree until it finds the specific
	//solution board. The algorthim first tries to do only white substitutions until the queue gets empty(no more possible
	//white moves). Then, black substitution is turned on and then gets switched backed to white. If the second time
	//there is no more white substitutions to be made, then the counter "count" gets incremented and now there will be
	//two black substitutions. The counter will increment until a solution is found.
	static String solve(boolean[] board){
		HiRiQ cases=new HiRiQ((byte) 0) ;
		//if(cases.IsSolved())
		//	return "A";
		cases.store(board);
		if(cases.IsSolved())
			return "Already solved!";
		int count = 0;	//counter used for number of times the black substitutions will apply
		int overallCount = 0;	//counter inside the while loop to determine number of operations for solving

		System.out.println("Following board to be solved...Please Wait. ");
		cases.print();

		System.out.println("=====================");

		Queue<Node>queue = new LinkedList<Node>();	//queue of the children
		Node root = new Node(null,board);
		ArrayList<boolean[]> arr =checkTriplet(root.getBoard());
		//System.out.println(arr.size());
		if(arr.size()==0){
			blackSubs = true;
			arr =checkTriplet(root.getBoard());
		}
		addToChildren(arr,root,queue,cases.config);
		blackSubs=false;

		while(true){
			//if(count==0){
			Node node = queue.remove();

			cases.store(node.getBoard());

			if(cases.IsSolved()){
				cases.print();
				
				System.out.println("Number of operations: "+(count+overallCount));
				return steps(node);

			}
			arr =checkTriplet(node.getBoard());
			addToChildren(arr,node,queue,cases.config);


			if(queue.isEmpty()){
				boardSet.clear();
				blackSubs=true;
				count++;
				
				for(int i = 0;i<count;i++){
					

					arr =checkTriplet(node.getBoard());
					cases.store(node.getBoard());
					addToChildren(arr,node,queue,cases.config);
					node = queue.remove();

				}
				blackSubs=false;

			}

			overallCount++;

		}

	}
	//this class Makes the object of Node, which makes it so that I have more control on each specific node. This also makes
	//it easier to go back to the parent node and also to build the tree
	static public class Node {
		ArrayList<Node> children = new ArrayList<Node>();
		Node parent = null;
		boolean[] value = new boolean[33];
		
		
		public Node(Node parent, boolean[] value)
		{
			for(int i = 0;i<33;i++){
				this.value[i]=value[i];
			}
			this.parent = parent;
			
			if (parent != null)
			{
				parent.addNode(this);
			}
		}
		
		public void addNode(Node node)
		{
	
			children.add(node);
		
		}
		public boolean[] getBoard(){
			return value;
		}
		public void addNode(boolean [] board){
			
			new Node(this, board);
			
		}
		public ArrayList getChildren(){
			
			
			return children;
			
		}
	}


	static class HiRiQ{
	
		//int is used to reduce storage to a minimum...
		public int config;
		public byte weight;

		//initialize to one of 5 reachable START config n=0,1,2,3,4
		HiRiQ(byte n)
		{
			if (n==0)
			{config=65536/2;weight=1;}
			else
				if (n==1)
				{config=1626;weight=6;}
				else
					if (n==2)
					{config=-411153748; weight=13; }
					else
						if (n==3)
						{config=-1478227677; weight=20;}
						else
						{config=-2147450879; weight=32;}
		}

		boolean IsSolved()
		{
			return( (config==65536/2) && (weight==1) );
		}

		//transforms the array of 33 booleans to an (int) cinfig and a (byte) weight.
		public void store(boolean[] B)
		{
			int a=1;
			config=0;
			weight=(byte) 0;
			if (B[0]) {weight++;}
			for (int i=1; i<32; i++)
			{
				if (B[i]) {config=config+a;weight++;}
				a=2*a;
			}
			if (B[32]) {config=-config;weight++;}
		}

		//transform the int representation to an array of booleans.
		//the weight (byte) is necessary because only 32 bits are memorized
		//and so the 33rd is decided based on the fact that the config has the
		//correct weight or not.
		public boolean[] load(boolean[] B)
		{
			byte count=0;
			int fig=config;
			B[32]=fig<0;
			if (B[32]) {fig=-fig;count++;}
			int a=2;
			for (int i=1; i<32; i++)
			{
				B[i]= fig%a>0;
				if (B[i]) {fig=fig-a/2;count++;}
				a=2*a;
			}
			B[0]= count<weight;
			return(B);
		}

		//prints the int representation to an array of booleans.
		//the weight (byte) is necessary because only 32 bits are memorized
		//and so the 33rd is decided based on the fact that the config has the
		//correct weight or not.
		public void printB(boolean Z)
		{if (Z) {System.out.print("[ ]");} else {System.out.print("[@]");}}

		public void print()
		{
			byte count=0;
			int fig=config;
			boolean next,last=fig<0;
			if (last) {fig=-fig;count++;}
			int a=2;
			for (int i=1; i<32; i++)
			{
				next= fig%a>0;
				if (next) {fig=fig-a/2;count++;}
				a=2*a;
			}
			next= count<weight;

			count=0;
			fig=config;
			if (last) {fig=-fig;count++;}
			a=2;

			System.out.print("      ") ; printB(next);
			for (int i=1; i<32; i++)
			{
				next= fig%a>0;
				if (next) {fig=fig-a/2;count++;}
				a=2*a;
				printB(next);
				if (i==2 || i==5 || i==12 || i==19 || i==26 || i==29) {System.out.println() ;}
				if (i==2 || i==26 || i==29) {System.out.print("      ") ;};
			}
			printB(last); System.out.println() ;

		}
	}
	
	
	
}