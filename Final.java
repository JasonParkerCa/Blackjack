/** 

 *ICS 3U
 
 *The program is a game which called Closer to 21. This is the final project.

 *Copyright: Shenjie Wang

 */

//import java IO package
import java.io.*;

public class Final
{
	//declare a two dimension array which can store 1000 times games' history including gamer1,gamer2's names, their scores, and the winner
	static String[][] data = new String[1000][5];
	
	//declare a two dimension array which can store 1000 names and the times of their wins
	static String[][] name = new String[1000][2];
	
	//declare an array which can store the position of the name who is searched
	static int ResultDataBase[] = new int[1000];
	
	//declare an integer variable to store the number of histories
	static int x = 0;
	
	//declare two integer variables which store the sum of numbers gamers received and the other is for the random number 
	static int Gamer1,Gamer2,number;
	
	//declare two boolean variables which is used to judge if the gamer decide to continue or stop receiving numbers
	static boolean Gamer1Choice,Gamer2Choice;
	
	//declare a string variable represented the choice the gamer made
	static String ContinueOrStop;
	
	//declare two string storing 2 gamers'name
	static String Gamer1Name,Gamer2Name;
	
	//declare an input letting gamers type in their names
	static BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
	
	//declare an integer variable storing and tracking the number of gamers
	static int numberOfGamers;
	
	//declare an integer variable storing the number of result for searching gaming histories
	static int numberOfResult;
	
	
	
	public static void main (String[] args) throws IOException
	{
		//use method "LoadDataBase" for reading histories from data.txt
		LoadDataBase();
		
		//use method "LoadName" for reading histories from name.txt
		LoadName();
		
		//declare an integer number as the choice of the menu
		int choice;
		
		//declare an input letting gamers type in their choices of the menu
		BufferedReader inputChoice = new BufferedReader (new InputStreamReader(System.in));
		
		//Display the title and the rules of this game
		System.out.println("Closer to 21\n\n"+
                		   "Game Rules:\n"+
                           "1.Two gamers will receive a random number between 1 and 13\n"+
                           "2.Gamers can choose to continue receiving numbers or stop it\n"+
                           "3.After both gamers choose to stop receiving numbers, calculate the sum of numbers received\n"+
                           "4.The one whose sum is more closer than 21 wins\n");

		//start a loop until the gamer choose 7 "quit"
		do
		{
			
			//print the menu
			System.out.println("\nMain Menu\n"+
					
						       "1. Enter Game\n"+				
						                   
						       "2. Print Gaming History\n"+
					
						       "3. Print The Times of Wins For Gamers\n"+
						                   
							   "4. Search Gaming History\n"+
								          
							   "5. Search The Times of Wins For Gamers\n"+
										   
							   "6. Modify the history\n"+
								           
							   "7. Quit\n\n"+
								           
							   "What is your choice?");
			
			//make "choice" equals the number which the users input
			choice = Integer.valueOf(inputChoice.readLine()).intValue();
			
			switch (choice)
			{
			
			//when the gamer choose 1, use method"Game" for playing games
			case 1:
				Game();
				break;
				
			//when the gamer choose2, use method "ShowDataBase" for printing history
			case 2:
				ShowDataBase();
				break;
				
			//when the gamer choose2, use method "ShowName"
			case 3:
				//for printing gamers'names and their numbers of wins
				ShowName();
				
				//ask gamers if they want to sort the overview
				System.out.println("Do you want to sort it? (Yes or No)");
				
				//store the choice if gamers
				String choiceSort = inputChoice.readLine();
				
				//if gamers type in "Yes" or "yes"
				if (choiceSort.equals("Yes") || choiceSort.equals("yes"))
				{
					
					//gave gamers two ways to sort
					System.out.println("\n1.Sorted from small to big"+
									   "\n2.Sorted from big to small");
					
					//if gamers choose 1
					if (inputChoice.readLine().equals("1"))
					{
						//use method "SortNameS_B" for sorting gamers from weaker to stronger
						SortNameS_B();
					}else{
						
						//use method "SortNameB_S" for sorting gamers from stronger to weaker
						SortNameB_S();
					}
				}
				break;
			
			//when gamers choose 4, use method "SearchDataBase" for searching certain gamer in the histories
			case 4:
				SearchDataBase();
				break;
				
			//when gamers choose 5, use method "SearchName" for searching certain gamer in the overview
			case 5:
				SearchName();
				break;
				
				//when gamers choose 6, use method "Modify" for changing and deleting histories
			case 6:
				Modify();
				break;
				
			}
		} 
		//stop the loop when gamers choose 7
		while (choice!=7);
		
		//use method "WriteDataBase" for updating information to "Data.txt"
		WriteDataBase();
		
		//use method "WriteDataBase" for updating information to "Gamer.txt"
		WriteName();
	}
	
	
	public static void LoadDataBase() throws IOException
	{
		
		//declare an integer variable to store the original number of TV shows
		int beginning =x;
		
		//declare an input reading the file "data.txt"
		BufferedReader dataInput = new BufferedReader (new FileReader("Data.txt"));
		
		//track the number of histories
		x = x + Integer.valueOf(dataInput.readLine()).intValue();
		
		//store the informationm to data array from "data.txt"
		for (int i=beginning;i<x;i++){
			for (int r=0;r<5;r++)
			{
				data[i][r] = dataInput.readLine();
			}
			//skip a line while one time of history is finished being read
			dataInput.readLine();
		}
		//stop reading
		dataInput.close();
	}
	
	
	public static void LoadName() throws IOException
	{
		//declare an input for reading data from "Gamer.txt"
		BufferedReader gamerInput = new BufferedReader (new FileReader("Gamer.txt"));
		
		//store the number on the first line of "Gamer.txt"
		numberOfGamers = Integer.valueOf(gamerInput.readLine()).intValue();
		
		//store name lists of this game into the name array
		for (int i=0;i<numberOfGamers;i++)
		{
			name[i][0] = gamerInput.readLine();
		}
		
		//stop reading
		gamerInput.close();
	}
	
	
	public static void Game() throws IOException
	{
		//declare a boolean to judge if the name has already been in the data
		boolean repeat;
		
		//declare a string to store the choice that if gamers want to have another round
		String again;	

		//ask gamers if they want to start or quit back to the menu
		System.out.println("Are you ready to start? (Yes or No)");
		
		String start = in.readLine();
		
		//if gamers choose to start
		if (start.equals("Yes") || start.equals("yes"))
		{
			
			//start an loop until gamers choose to go back to the menu
			do
			{
				//set the sum of numbers to 0 first
				Gamer1=0;
				Gamer2=0;
				
				//set gamers want to continue the game
				Gamer1Choice = true;
				Gamer2Choice = true;
				
				//reset repeat
				repeat = false;
				//ask gamer1's name
				System.out.println("\nWhat is the name for the first gamer?");
				//store gamer1's name to the data array
				Gamer1Name = in.readLine();
				data[x][0] = Gamer1Name;
				//check if the name has already in the name list
				for (int i=0;i<numberOfGamers;i++){
					if (Gamer1Name.compareToIgnoreCase(name[i][0]) == 0)
					{
						repeat = true;					
					}
				}
				//if it is a new name, store and add it to the name array
				if (repeat==false)
				{
				name[numberOfGamers][0] = Gamer1Name;
				//add 1 to the number of gamers
				numberOfGamers++;
				}
				
				//reset repeat
				repeat = false;
				
				//the same checking and adding process as the last one for gamer2
				System.out.println("\nWhat is the name for the second gamer?");
				Gamer2Name = in.readLine();
				data[x][2] = Gamer2Name;
				for (int i=0;i<numberOfGamers;i++){
					if (Gamer2Name.compareToIgnoreCase(name[i][0]) == 0)
					{
						repeat = true;					
					}
				}
				if (repeat==false)
				{
				name[numberOfGamers][0] = Gamer2Name;
				numberOfGamers++;
				}				
				
				//start a loop until both gamers choose to stop rececving numbers
				do
				{
					
					//if gamer1 is still willing to receive numbers
					if (Gamer1Choice == true)
					{
						//get a number between 1-13 randomly
						number=(int) (Math.round(Math.random()*12)+1);
						
						//calculate the sum of all the random numbers
						Gamer1 = Gamer1 + number;
						
						//print the gamer1's result of this turn and ask if he want to continue or stop
						System.out.println("\n"+Gamer1Name+"'s Turn "+
										   "\nThis time you received: "+number+
										   "\nYour current sum of numbers: "+Gamer1+
										   "\n\nDo you want to continue or stop?");
						ContinueOrStop = in.readLine();
						
						//if gamer1 wants to stop, convert GamerqChoice to false and may stop the loop
						if (ContinueOrStop.equals("Stop") || ContinueOrStop.equals("stop"))
						{
							Gamer1Choice = false;
						}
					}	
					
					//the same process as the last one for gamer2
					if (Gamer2Choice == true)
					{
						number=(int) (Math.round(Math.random()*12)+1);
						Gamer2 = Gamer2 + number;
						System.out.println("\n"+Gamer2Name+"'s Turn "+
								   		   "\nThis time you received: "+number+
								           "\nYour current sum of numbers: "+Gamer2+
								           "\n\nDo you want to continue or stop?");
						ContinueOrStop = in.readLine();
						if (ContinueOrStop.equals("Stop") || ContinueOrStop.equals("stop"))
						{
							Gamer2Choice = false;
						}
					}
				
				//when both gamers choose to stop, stop the loop
				} while(Gamer1Choice == true || Gamer2Choice == true);
				
				//store two gamers' final result to data array
				data[x][1] = Integer.toString(Gamer1);
				data[x][3] = Integer.toString(Gamer2);
				
				//check who is the winner by judging whose sum of numbers is closer to 21, and store the winner's name in data array
				if (Math.abs(Gamer1-21) < Math.abs(Gamer2-21)){
					System.out.println("\n"+Gamer1Name+":"+Gamer1+"    "+Gamer2Name+":"+Gamer2+
							 		   "\n"+Gamer1Name+" wins");
					data[x][4] = Gamer1Name;
				}
				if (Math.abs(Gamer1-21) > Math.abs(Gamer2-21)){
					System.out.println("\n"+Gamer1Name+":"+Gamer1+"    "+Gamer2Name+":"+Gamer2+
									   "\n"+Gamer2Name+" wins");
					data[x][4] = Gamer2Name;
				}
				if (Math.abs(Gamer1-21) == Math.abs(Gamer2-21)){
					System.out.println("\n"+Gamer1Name+":"+Gamer1+"    "+Gamer2Name+":"+Gamer2+
									   "\nA dead heat.");
					//if the sums of numbers for both gamers are equal then display and store "A dead heat"
					data[x][4] = "A dead heat";
				}
				
				//add 1 to the number of histories every time after a round is finished
				x++;
				
				//ask gamers do they want to play again
				System.out.println("Do you want to have another turn? (Yes or No)");
				again=in.readLine();
			}
			//if gamers type in "Yes" or "yes" then continue the loop or go back to the menu
			while(again.equals("Yes") || again.equals("yes"));	
		}
	}
	
	
	public static void ShowDataBase()
	{
		
		//make a table to print all the data stored in the data array
		System.out.println("\n                                                            Game DataBase");
		final Object[][] tableData = new String[x+1][];		
		tableData[0] = new String[] {"Gamer1","Score for Gamer1","Gamer2","Score for Gamer2","Winner"};
		for (int i=0; i<x; i++)
		{
			tableData[i+1] = new String[] {data[i][0],data[i][1],data[i][2],data[i][3],data[i][4]};
		}
		for (final Object[] row : tableData)
		{
			System.out.format("%20s%30s%20s%30s%20s\n", row);
		}
	}
	
	public static void Analyze()
	{
		//declare an integer variable as a counter to count the number of wins for every gamer on the name list
		int numberOfWin = 0;
		
		//check if the winner position of data array has the same string of the names in name array
		for (int i=0;i<numberOfGamers;i++)
		{
			for (int r=0;r<x;r++)
			{
				//if there is the same string appeared, add 1 to numberOfWin
				if (name[i][0].equals(data[r][4]))
				{
					numberOfWin++;
				}
			}
			//store the number of wins in name array at the certain position
			name[i][1]=Integer.toString(numberOfWin);
			//reset numberOfWin for the next name
			numberOfWin = 0;
		}
	}
	
	
	
	public static void ShowName()
	{
		//use mathod "Analyze" for counting the number of wins for every gamer on the name list
		Analyze();
		
		//make a table to print all the data stored in the name array
		System.out.println("\n                        Overview");
		final Object[][] tableData = new String[numberOfGamers+1][];		
		tableData[0] = new String[] {"Gamer","Number of Wins"};
		for (int i=0; i<numberOfGamers; i++)
		{
			tableData[i+1] = new String[] {name[i][0],name[i][1]};
		}
		for (final Object[] row : tableData)
		{
			System.out.format("%20s%20s\n", row);
		}
	}
		
	
	public static void SortNameS_B()
	{
		
		//Binary Sort (From small to big)
		String temp0,temp1;
		for (int i=0; i<numberOfGamers-1;i++)
		{
			for (int r=0;r<(numberOfGamers-1)-i;r++)
			{
				if (name[r+1][1].compareTo(name[r][1])<0)
				{
					
					//swap
					temp0=name[r][0];
					name[r][0]=name[r+1][0];
					name[r+1][0]=temp0;
					temp1=name[r][1];
					name[r][1]=name[r+1][1];
					name[r+1][1]=temp1;
				}
			}
		}
		
		//use method "ShowName" for printing all the data in name array
		ShowName();
	}
	
	
	public static void SortNameB_S()
	{
		
		//Binary Sort (From big to small)
		String temp0,temp1;
		for (int i=0; i<numberOfGamers-1;i++)
		{
			for (int r=0;r<(numberOfGamers-1)-i;r++)
			{
				if (name[r+1][1].compareTo(name[r][1])>0)
				{
					
					//swap
					temp0=name[r][0];
					name[r][0]=name[r+1][0];
					name[r+1][0]=temp0;
					temp1=name[r][1];
					name[r][1]=name[r+1][1];
					name[r+1][1]=temp1;
				}
			}
		}
		
		//use method "ShowName" for printing all the data in name array
		ShowName();
	}
	
	
	public static void SearchDataBase() throws IOException
	{
		
		//declare a string variable to store whose history gamer wants to search
		String SearchDataBase;
		
		//declare an input letting gamer type in whose history gamer wants to search
		BufferedReader inputSearchDataBase = new BufferedReader (new InputStreamReader(System.in));
		
		//ask gamer
		System.out.println("Whose history do you want to search?");
		SearchDataBase = inputSearchDataBase.readLine();
		
		//set number of result as a 0 first
		numberOfResult = 0;
		
		for (int i=0;i<x;i++){
			//if the name gamer wants to seach is the same as either gamer1's name or gamer2's name in data array, the position will be stored in ResultDataBase array
			if (SearchDataBase.equals(data[i][0]) || SearchDataBase.equals(data[i][2]))
			{
				ResultDataBase[numberOfResult] = i;
				//add 1 to numberOfResult
				numberOfResult++;
			}
		}
		
		//if there are results
		if (numberOfResult>0)
		{
			
			//use a table to print all the results
			System.out.println("                                                            Search Result");
			final Object[][] tableData = new String[numberOfResult+1][];		
			tableData[0] = new String[] {"Gamer1","Score for Gamer1","Gamer2","Score for Gamer2","Winner"};
			
			for (int i=0; i<numberOfResult; i++)
			{
				tableData[i+1] = new String[] {data[ResultDataBase[i]][0],data[ResultDataBase[i]][1],data[ResultDataBase[i]][2],data[ResultDataBase[i]][3],data[ResultDataBase[i]][4]};
			}
			
			for (final Object[] row : tableData)
			{
				System.out.format("%20s%30s%20s%30s%20s\n", row);
			}
		
		}else{
			
			//if there is no result, display "Name is not found"
			System.out.println("\nName is not found");
		}
	}
	
	
	public static void SearchName() throws IOException
	{
		
		//use method "Analyze" for counting the number of wins for every gamer on the name list
		Analyze();
		
		//declare 2 string variables for swapping
		String temp0,temp1;
		
		//Bubble Sort
		for (int i=0; i<numberOfGamers-1;i++)
		{
			for (int r=0;r<(numberOfGamers-1)-i;r++)
			{
				if (name[r+1][0].compareTo(name[r][0])<0)
				{
					
					//swap
					temp0=name[r][0];
					name[r][0]=name[r+1][0];
					name[r+1][0]=temp0;
					temp1=name[r][1];
					name[r][1]=name[r+1][1];
					name[r+1][1]=temp1;
				}
			}
		}
		
		//Binary Search for searching the certain name in name array
		String search;
		int bottom = 0, top = numberOfGamers-1, middle, flag=0;
		boolean found = false;
		
		//ask gamers who they want to search
		BufferedReader inputSearch = new BufferedReader (new InputStreamReader(System.in));
		System.out.println("Who do you want to search?");
		search = inputSearch.readLine();
		
		while ((bottom<=top) && found == false)
		{
			middle = (bottom+top)/2;
			if (search.compareTo(name[middle][0])==0)
			{
				flag = middle;
				found = true;
			}else{
				if (search.compareTo(name[middle][0])<0)
				{
					top = middle-1;
				}else{
					bottom =middle+1;
				}
			}
		}
		
		if (found == false)
		{
			//if there is no name, print "Name is not found"
			System.out.println("\nName is not found");
		
		}else{
			
			//if the name is found, print the result
			System.out.println("\nName         Number of Wins\n"+
		                        name[flag][0]+"         "+name[flag][1]);
		}
	}


	public static void Modify() throws IOException{
		
		//use method "SearchDataBase" for providing to gamer all the history of a certain name which gamers want to modify
		SearchDataBase();
		
		//declare an integer variable to store the choice that gamer want to modify which history
		int choiceOfModify;
		
		//declare an integer variable to store the choice that gamer want to modify which part of the certain history
		int choiceOfModifyMenu;
		
		//declare an input letting gamers type in the choice of the menu and which one should be modified
		BufferedReader InputModify = new BufferedReader (new InputStreamReader(System.in));
		
		//if there are search results
		if (numberOfResult>0)
		{
			
			//ask gamers they want to notify which one
			System.out.println("\nWhich one do you want to modify? (Type the number)");
			
			//store the choice of which one
			choiceOfModify = Integer.valueOf(InputModify.readLine()).intValue();
			
			//start a loop until gamers choose 3 or 4
			do
			{
				
				//print the menu
				System.out.println("\n1.Enter New Score of Gamer1"+
						           "\n2.Enter New Score of Gamer2"+
						           "\n3.Delete"+
						           "\n4.Quit");
				
				//store the choice of the menu
				choiceOfModifyMenu =  Integer.valueOf(InputModify.readLine()).intValue();
				
				switch (choiceOfModifyMenu)
				{
				
				//if gamers choose 1, change the score of gamer1
				case 1:
					
					//notify gamers to type
					System.out.println("\nPlease Type");
					
					//save the new score to the array
					data[ResultDataBase[choiceOfModify-1]][1] = InputModify.readLine();
					
					//check if the winner should be changed after the score changed and make sure the winner
                    //"choiceOfModify-1" represents that the position of which is chosen in the array, like if gamers choose the first one and typed in 1, the position will be 0 in the ResultDataBase array
                    //the ResultDataBase array stored the position of the history chosen in data array
					if (Math.abs(Integer.valueOf(data[ResultDataBase[choiceOfModify-1]][1]).intValue()-21) < Math.abs(Integer.valueOf(data[ResultDataBase[choiceOfModify-1]][3]).intValue()-21)){
						data[ResultDataBase[choiceOfModify-1]][4] = data[ResultDataBase[choiceOfModify-1]][0];
					}
					if (Math.abs(Integer.valueOf(data[ResultDataBase[choiceOfModify-1]][1]).intValue()-21) > Math.abs(Integer.valueOf(data[ResultDataBase[choiceOfModify-1]][3]).intValue()-21)){
						data[ResultDataBase[choiceOfModify-1]][4] = data[ResultDataBase[choiceOfModify-1]][2];
					}
					if (Math.abs(Integer.valueOf(data[ResultDataBase[choiceOfModify-1]][1]).intValue()-21) == Math.abs(Integer.valueOf(data[ResultDataBase[choiceOfModify-1]][3]).intValue()-21)){
						data[ResultDataBase[choiceOfModify-1]][4] = "A dead heat";
					}
					
					//create a table to print the result after changed
					final Object[][] tableData = new String[2][];		
					tableData[0] = new String[] {"Gamer1","Score for Gamer1","Gamer2","Score for Gamer2","Winner"};
					for (int i=1; i<2; i++)
					{
						tableData[i] = new String[] {data[ResultDataBase[choiceOfModify-1]][0],data[ResultDataBase[choiceOfModify-1]][1],data[ResultDataBase[choiceOfModify-1]][2],data[ResultDataBase[choiceOfModify-1]][3],data[ResultDataBase[choiceOfModify-1]][4]};
					}
					for (final Object[] row : tableData)
					{
						System.out.format("%20s%30s%20s%30s%20s\n", row);
					}

					break;
					
				//if gamers choose 1, change the score of gamer2
				case 2:
					
					//notify gamers to type
					System.out.println("\nPlease Type");
					
					//save the new score to the array
					data[ResultDataBase[choiceOfModify-1]][3] = InputModify.readLine();
					
					//check if the winner should be changed after the score changed and make sure the winner
					if (Math.abs(Integer.valueOf(data[ResultDataBase[choiceOfModify-1]][1]).intValue()-21) < Math.abs(Integer.valueOf(data[ResultDataBase[choiceOfModify-1]][3]).intValue()-21)){
						data[ResultDataBase[choiceOfModify-1]][4] = data[ResultDataBase[choiceOfModify-1]][0];
					}
					if (Math.abs(Integer.valueOf(data[ResultDataBase[choiceOfModify-1]][1]).intValue()-21) > Math.abs(Integer.valueOf(data[ResultDataBase[choiceOfModify-1]][3]).intValue()-21)){
						data[ResultDataBase[choiceOfModify-1]][4] = data[ResultDataBase[choiceOfModify-1]][2];
					}
					if (Math.abs(Integer.valueOf(data[ResultDataBase[choiceOfModify-1]][1]).intValue()-21) == Math.abs(Integer.valueOf(data[ResultDataBase[choiceOfModify-1]][3]).intValue()-21)){
						data[ResultDataBase[choiceOfModify-1]][4] = "A dead heat";
					}
					
					//create a table to print the result after changed
					final Object[][] tableData2 = new String[2][];		
					tableData2[0] = new String[] {"Gamer1","Score for Gamer1","Gamer2","Score for Gamer2","Winner"};
					for (int i=1; i<2; i++)
					{
						tableData2[i] = new String[] {data[ResultDataBase[choiceOfModify-1]][0],data[ResultDataBase[choiceOfModify-1]][1],data[ResultDataBase[choiceOfModify-1]][2],data[ResultDataBase[choiceOfModify-1]][3],data[ResultDataBase[choiceOfModify-1]][4]};
					}
					for (final Object[] row : tableData2)
					{
						System.out.format("%20s%30s%20s%30s%20s\n", row);
					}
					
					break;

				}

			} 
			//when gamers choose 3 or 4, stop the loop
			while (choiceOfModifyMenu !=4 && choiceOfModifyMenu !=3);
			
			//if gamers choose 3, start deleting process
			if (choiceOfModifyMenu==3)
			{
				for (int i=ResultDataBase[choiceOfModify-1]; i<x-1; i++)
				{
					for (int r=0;r<=4;r++)
					{
						//every information of data array after the deleted one move forward one postion
						data[i][r] = data[i+1][r];
					}
				}
				
				//reduce 1 to the number of histories
				x=x-1;
				
				//notify gamers that the deleting process is complete
				System.out.println("The process is complete.");
			}
		}
	}
	
	
	public static void WriteDataBase() throws IOException
	{
		//declare an output to write the updated information to "Data.txt"
		PrintWriter outputDataBase = new PrintWriter (new FileWriter("Data.txt"));
		
		//write the number of histories on the first line of "Data.txt"
		outputDataBase.println(x);
		
		//output the data from data array
		for (int i=0;i<x;i++)
		{
			for (int r=0;r<5;r++)
			{
				outputDataBase.println(data[i][r]);
			}
			//skip a line when a time of histories is finished being written
			outputDataBase.println();
		}
		
		//stop writing
		outputDataBase.close();
	}
	
	
	public static void WriteName() throws IOException
	{
		//declare an output to write updated data to "Gamer.txt"
		PrintWriter nameOutput = new PrintWriter (new FileWriter("Gamer.txt"));
		
		//write the number of names on the first line of "Gamer.txt"
		nameOutput.println(numberOfGamers);
		
		for (int i=0;i<numberOfGamers;i++)
		{
			//write data stored in the array to the file
			nameOutput.println(name[i][0]);
		}
		
		//stop writing
		nameOutput.close();
	}
}
