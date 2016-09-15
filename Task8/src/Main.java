import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

//Some changes...
public class Main {
	 static boolean j =true;
	 static boolean inPlay = true;
	 static boolean hit = true;
	 static int wd;
	 static int ht;
	 
	public static void main(String[] args) {
		System.out.println("Press (s) then Enter to start new game");
		Scanner sc = new Scanner(System.in);
		String gameMode = sc.nextLine();
		
		List<Ship> player1Ships = new ArrayList<Ship>();
		List<Ship> player2Ships = new ArrayList<Ship>();
		
		if(gameMode.equals("s")){
			ht = 12;
			wd = 12;
		}
		
		Board board = new Board(ht, wd);
		Board boardPlayer2View = new Board(ht,wd);
		Board board1 = new Board(ht, wd);
		Board board1Player1View = new Board(ht,wd);
		
		System.out.println("Player 1 board...");
		board.printBoard();
		
		shipPlacement(board, player1Ships,1);
		
		System.out.println("Computer board...");
		board1.printBoard();
		//aiShipPlace(board1, player2Ships,2);}
		shipPlacement(board1, player2Ships,2);
		
		int counter = 7;
		
		while(inPlay){
			hit = true;
			while(hit && inPlay){
				System.out.println("Player 1's turn");
				System.out.println("Enter the co-ordinates to fire at!!");
				int x = sc.nextInt();
				int y = sc.nextInt();
				int shipUpdate = 0;
			//int shipStatus = 0;
				shipUpdate = missileLaunch(x,y, board1,board1Player1View, player2Ships);
				switch(shipUpdate){
				case 0:
					board1.boardShipUpdate(x,y,shipUpdate);
					board1Player1View.boardShipUpdate(x,y,shipUpdate);
					hit = false;
					break;
			
				case 2:
					board1.boardShipUpdate(x,y,shipUpdate);
					board1Player1View.boardShipUpdate(x,y,shipUpdate);
					hit = true;
					break;
				
				case 3:
					board1.boardShipUpdate(x,y,shipUpdate);
					board1Player1View.boardShipUpdate(x,y,shipUpdate);
					hit = true;
					inPlay = gameOver(player2Ships, 1, counter, board1Player1View);
					break;
			
				default:
					break;
			}
			board1Player1View.printBoard();
		}

			inPlay = gameOver(player2Ships, 1, counter, board1Player1View);
			
			hit = true;
			
			while(hit && inPlay){
				System.out.println("Player 2's turn");
				System.out.println("Enter the co-ordinates to fire at!!");
				int x = (int) (Math.random() * 11 + 1);
				int y = (int) (Math.random() * 11 + 1);
				
				//int x = sc.nextInt();
				//int y = sc.nextInt();
				int shipUpdate = 0;
				//int shipStatus = 0;
				shipUpdate = missileLaunch(x,y, board,boardPlayer2View, player1Ships);
				switch(shipUpdate){
				case 0:
					board.boardShipUpdate(x,y,shipUpdate);
					boardPlayer2View.boardShipUpdate(x,y,shipUpdate);
					hit = false;
					break;
				
				case 2:
					board.boardShipUpdate(x,y,shipUpdate);
					boardPlayer2View.boardShipUpdate(x,y,shipUpdate);
					hit = true;
					break;
					
				case 3:
					board.boardShipUpdate(x,y,shipUpdate);
					boardPlayer2View.boardShipUpdate(x,y,shipUpdate);
					hit = true;
					inPlay = gameOver(player1Ships, 2, counter, boardPlayer2View);
					break;
				
				default:
					break;
				}
				boardPlayer2View.printBoard();
			}
			inPlay = gameOver(player1Ships, 2, counter, boardPlayer2View);
		}
		System.exit(0);
		
		}
		

	public static boolean gameOver(List<Ship> playerShips, int player, int counter, Board playerBoard){
		
		for(Ship ship: playerShips){
			if(ship.getDestroyed()==true){
				counter--;
			}
		}
		if(counter == 0){
			System.out.println("-------------Game Over-------------");
			System.out.println("Player "+player+" Wins!!");
			playerBoard.printBoard();
			System.exit(0);
			return false;
		}
		else{
			return true;
		}
	}

	
	public static int missileLaunch(int xCoord, int yCoord, Board bd, Board oppBd, List<Ship> playerShips){
		int rtn = 0;
		try{
			for(Ship ship: playerShips){
				if(ship.getBackx() == xCoord && ship.getFrontx() == xCoord && ((ship.getFronty() <= yCoord && yCoord <= ship.getBacky()) || (ship.getFronty() >= yCoord && yCoord >= ship.getBacky()))){
						if(ship.getDestroyed()==true){
							System.out.println("Ship already destroyed!");
							rtn = 3;
								
						}
						else{
							ship.setHit_count(ship.getHit_count()+1);
							if(ship.getHit_count() == ship.getLength()){
								int x,y;
								System.out.println("Ship destroyed!");
								ship.setDestroyed(true);
									for(int i = 0; i < ship.getLength(); i++){
											x = ship.getBackx();
											y = (ship.getBacky() - i);
											bd.boardShipUpdate(x,y,3);
											oppBd.boardShipUpdate(x,y,3);
									}
								System.out.println("Fire again!");
								rtn = 3;
									
							}
							else{
									
								System.out.println("Thats a hit!");
								System.out.println("Fire again!");
								rtn = 2;
									
							}
						}
					}
					else if(ship.getBacky() == yCoord && ship.getFronty() == yCoord && ((ship.getFrontx() <= xCoord && xCoord <= ship.getBackx()) || (ship.getFrontx() >= xCoord && xCoord >= ship.getBackx()))){
						if(ship.getDestroyed()==true){
							System.out.println("Ship already destroyed!");
							rtn = 3;
								
						}
						else{
							ship.setHit_count(ship.getHit_count()+1);
								
							if(ship.getHit_count() == ship.getLength()){
								int x,y;
								System.out.println("Ship destroyed!");
								ship.setDestroyed(true);
								for(int i = 0; i < ship.getLength(); i++){
									x = (ship.getBackx() - i);
									y = ship.getBacky();
									bd.boardShipUpdate(x,y,3);
									oppBd.boardShipUpdate(x,y,3);
							}
								System.out.println("Fire again!");
								rtn = 3;
									
							}
							else{
									
								System.out.println("Thats a hit!");
								System.out.println("Fire again!");
								rtn = 2;
									
							}
						}
				}
			}
		
			if(rtn == 0){
				System.out.println("You missed");
				rtn = 0;
			}
		
		return rtn;
		}
		catch(ArrayIndexOutOfBoundsException aie1){
			 System.out.println("This position is NOT on the board!!");
			 rtn = 3;
			 return rtn;
		}
			
	}
	
		
	
	
	public static void shipPlacement(Board board, List<Ship> playerShips, int player){
		int fx = 0;
		int fy = 0;
		int bx = 0;
		int by = 0;
		boolean i =true;
		Scanner sc1 = new Scanner(System.in);
		while(i){
			System.out.println("Enter the co-ordinates of the front of your Patrol Boat...x*enter*y");
			fx = sc1.nextInt();
			fy = sc1.nextInt();
			System.out.println("Enter the co-ordinates of the back of your Patrol Boat(size1x2)...x*enter*y");
			bx = sc1.nextInt();
			by = sc1.nextInt();
			
			Ship patrol1 = new Ship("patrolBoat1", 2, fx, fy, bx, by, false, 0);
			playerShips.add(patrol1);
			i = board.placeShip(patrol1.getFrontx(), patrol1.getFronty(), patrol1.getBackx(), patrol1.getBacky(), 2);
			System.out.println("Player "+player+" board...");
			board.printBoard();
		}
		i =true;
		while(i){
			System.out.println("Enter the co-ordinates of the front of your Patrol Boat...x*enter*y");
			fx = sc1.nextInt();;
			fy = sc1.nextInt();
			System.out.println("Enter the co-ordinates of the back of your Patrol Boat(size1x2)...x*enter*y");
			bx = sc1.nextInt();
			by = sc1.nextInt();
			Ship patrol2 = new Ship("patrolBoat2", 2, fx, fy, bx, by, false, 0);
			playerShips.add(patrol2);
			i = board.placeShip(patrol2.getFrontx(), patrol2.getFronty(), patrol2.getBackx(), patrol2.getBacky(), 2);
			System.out.println("Player "+player+" board...");
			board.printBoard();
	}
		i = true;
		while(i){
			System.out.println("Enter the co-ordinates of the front of your BattleShip...x*enter*y");
			fx = sc1.nextInt();;
			fy = sc1.nextInt();
			System.out.println("Enter the co-ordinates of the back of your BattleShip(size1x3)...x*enter*y");
			bx = sc1.nextInt();
			by = sc1.nextInt();
			Ship BattleShip = new Ship("BattleShip", 3, fx, fy, bx, by, false, 0);
			playerShips.add(BattleShip);
			i = board.placeShip(BattleShip.getFrontx(), BattleShip.getFronty(), BattleShip.getBackx(), BattleShip.getBacky(), 3);
			System.out.println("Player "+player+" board...");
			board.printBoard();
	}
		i = true;
		while(i){
			System.out.println("Enter the co-ordinates of the front of your BattleShip...x*enter*y");
			fx = sc1.nextInt();;
			fy = sc1.nextInt();
			System.out.println("Enter the co-ordinates of the back of your BattleShip(size1x3)...x*enter*y");
			bx = sc1.nextInt();
			by = sc1.nextInt();
			Ship BattleShip2 = new Ship("BattleShip2", 3, fx, fy, bx, by, false, 0);
			playerShips.add(BattleShip2);
			i = board.placeShip(BattleShip2.getFrontx(), BattleShip2.getFronty(), BattleShip2.getBackx(), BattleShip2.getBacky(), 3);
			System.out.println("Player "+player+" board...");
			board.printBoard();
	}
		i = true;
		while(i){
			System.out.println("Enter the co-ordinates of the front of your Sub...x*enter*y");
			fx = sc1.nextInt();;
			fy = sc1.nextInt();
			System.out.println("Enter the co-ordinates of the back of your Sub(size1x3)...x*enter*y");
			bx = sc1.nextInt();
			by = sc1.nextInt();
			Ship sub = new Ship("sub", 3, fx, fy, bx, by, false, 0);
			playerShips.add(sub);
			i = board.placeShip(sub.getFrontx(), sub.getFronty(), sub.getBackx(), sub.getBacky(), 3);
			System.out.println("Player "+player+" board...");
			board.printBoard();
	}
		i = true;
		while(i){
			System.out.println("Enter the co-ordinates of the front of your Destroyer...x*enter*y");
			fx = sc1.nextInt();;
			fy = sc1.nextInt();
			System.out.println("Enter the co-ordinates of the back of your Destroyer(size1x4)...x*enter*y");
			bx = sc1.nextInt();
			by = sc1.nextInt();
			Ship destroyer = new Ship("destroyer", 4, fx, fy, bx, by, false, 0);
			playerShips.add(destroyer);
			i = board.placeShip(destroyer.getFrontx(), destroyer.getFronty(), destroyer.getBackx(), destroyer.getBacky(), 4);
			System.out.println("Player "+player+" board...");
			board.printBoard();
	}
		i = true;
		while(i){
			System.out.println("Enter the co-ordinates of the front of your Carrier...x*enter*y");
			fx = sc1.nextInt();;
			fy = sc1.nextInt();
			System.out.println("Enter the co-ordinates of the back of your Carrier(size1x5)...x*enter*y");
			bx = sc1.nextInt();
			by = sc1.nextInt();
			Ship carrier = new Ship("carrier", 5, fx, fy, bx, by, false, 0);
			playerShips.add(carrier);
			i = board.placeShip(carrier.getFrontx(), carrier.getFronty(), carrier.getBackx(), carrier.getBacky(), 5);
			System.out.println("Player "+player+" board...");
			board.printBoard();
	}
	
	}
/*
	public static void aiShipPlace(Board board, List<Ship> playerShips, int player){
		
		int fx = 0;
		int fy = 0;
		int bx = 0;
		int by = 0;
		int opp = 0;
		boolean isShipNotInPosition = true; 
		boolean i =true;
		Scanner sc1 = new Scanner(System.in);
		
		while(i){
			fx = (int) (Math.random() * 11 + 1);
			fy = (int) (Math.random() * 11 + 1);
			opp = (int) (Math.random() * 3 + 1);
			
			
			switch(opp){
			case 1: bx = fx - 1;
					by = fy;
					break;
			case 2: bx = fx + 1;
					by = fy;
					break;
			case 3: bx = fx;
					by = fy - 1;
					break;
			case 4: bx = fx;
					by = fy + 1;
					break;
			default:
					break;
			}
			
			Ship patrol1 = new Ship("patrolBoat1", 2, fx, fy, bx, by, false, 0);
			playerShips.add(patrol1);
			System.out.println(""+fx+" "+fy+" "+ bx+" "+ by);
			if(isShipNotInPosition == true){
				i = true;
				patrol1 = null;
			}
			else{
				i = board.placeShip(patrol1.getFrontx(), patrol1.getFronty(), patrol1.getBackx(), patrol1.getBacky(), 2);
			}
			System.out.println("Player "+player+" board...");
			board.printBoard();
		}
		System.out.println("patrol boat placed");
		i = true;
		while(i){
			fx = (int) (Math.random() * 11 + 1);
			fy = (int) (Math.random() * 11 + 1);
			opp = (int) (Math.random() * 3 + 1);
			
			switch(opp){
			case 1: bx = fx - 1;
					by = fy;
					break;
			case 2: bx = fx + 1;
					by = fy;
					break;
			case 3: bx = fx;
					by = fy - 1;
					break;
			case 4: bx = fx;
					by = fy + 1;
					break;
			default:
					break;
			}
			Ship patrol2 = new Ship("patrolBoat2", 2, fx, fy, bx, by, false, 0);
			playerShips.add(patrol2);
			System.out.println(""+fx+" "+fy+" "+ bx+" "+ by);
			
			if(isShipNotInPosition == true){
				i = true;
				patrol2 = null;
			}
			else{
				i = board.placeShip(patrol2.getFrontx(), patrol2.getFronty(), patrol2.getBackx(), patrol2.getBacky(), 2);
			}
			System.out.println("Player "+player+" board...");
			board.printBoard();
		}
		System.out.println("patrol boat placed");
		i = true;
		while(i){
			fx = (int) (Math.random() * 11 + 1);
			fy = (int) (Math.random() * 11 + 1);
			opp = (int) (Math.random() * 3 + 1);
			
			switch(opp){
			case 1: bx = fx - 2;
					by = fy;
					break;
			case 2: bx = fx + 2;
					by = fy;
					break;
			case 3: bx = fx;
					by = fy - 2;
					break;
			case 4: bx = fx;
					by = fy + 2;
					break;
			default:
					break;
			}
			Ship BattleShip = new Ship("BattleShip", 3, fx, fy, bx, by, false, 0);
			playerShips.add(BattleShip);
			System.out.println(""+fx+" "+fy+" "+ bx+" "+ by);
			i = board.placeShip(BattleShip.getFrontx(), BattleShip.getFronty(), BattleShip.getBackx(), BattleShip.getBacky(), 3);
			
			System.out.println("Player "+player+" board...");
			board.printBoard();
		}
		System.out.println("BattleShip placed");
		i = true;
		while(i){
			fx = (int) (Math.random() * 11 + 1);
			fy = (int) (Math.random() * 11 + 1);
			opp = (int) (Math.random() * 3 + 1);
			
			switch(opp){
			case 1: bx = fx - 2;
					by = fy;
					break;
			case 2: bx = fx + 2;
					by = fy;
					break;
			case 3: bx = fx;
					by = fy - 2;
					break;
			case 4: bx = fx;
					by = fy + 2;
					break;
			default:
					break;
			}
			Ship BattleShip2 = new Ship("BattleShip2", 3, fx, fy, bx, by, false, 0);
			playerShips.add(BattleShip2);
			System.out.println(""+fx+" "+fy+" "+ bx+" "+ by);
			i = board.placeShip(BattleShip2.getFrontx(), BattleShip2.getFronty(), BattleShip2.getBackx(), BattleShip2.getBacky(), 3);
			
			System.out.println("Player "+player+" board...");
			board.printBoard();
		}
		System.out.println("BattleShip placed");
		i = true;
		while(i){
			fx = (int) (Math.random() * 11 + 1);
			fy = (int) (Math.random() * 11 + 1);
			opp = (int) (Math.random() * 3 + 1);
			
			switch(opp){
			case 1: bx = fx - 2;
					by = fy;
					break;
			case 2: bx = fx + 2;
					by = fy;
					break;
			case 3: bx = fx;
					by = fy - 2;
					break;
			case 4: bx = fx;
					by = fy + 2;
					break;
			default:
					break;
			}
			Ship sub = new Ship("sub", 3, fx, fy, bx, by, false, 0);
			playerShips.add(sub);
			System.out.println(""+fx+" "+fy+" "+ bx+" "+ by);
			i = board.placeShip(sub.getFrontx(), sub.getFronty(), sub.getBackx(), sub.getBacky(), 3);
			
			System.out.println("Player "+player+" board...");
			board.printBoard();
		}
		System.out.println("sub placed");
		i = true;
		while(i){
			fx = (int) (Math.random() * 11 + 1);
			fy = (int) (Math.random() * 11 + 1);
			opp = (int) (Math.random() * 3 + 1);
			
			switch(opp){
			case 1: bx = fx - 3;
					by = fy;
					break;
			case 2: bx = fx + 3;
					by = fy;
					break;
			case 3: bx = fx;
					by = fy - 3;
					break;
			case 4: bx = fx;
					by = fy + 3;
					break;
			default:
					break;
			}
			Ship destroyer = new Ship("destroyer", 4, fx, fy, bx, by, false, 0);
			playerShips.add(destroyer);
			System.out.println(""+fx+" "+fy+" "+ bx+" "+ by);
			i = board.placeShip(destroyer.getFrontx(), destroyer.getFronty(), destroyer.getBackx(), destroyer.getBacky(), 4);
			
			System.out.println("Player "+player+" board...");
			board.printBoard();
		}
		System.out.println("destroyer");
		i = true;
		while(i){
			fx = (int) (Math.random() * 11 + 1);
			fy = (int) (Math.random() * 11 + 1);
			opp = (int) (Math.random() * 3 + 1);
			
			switch(opp){
			case 1: bx = fx - 4;
					by = fy;
					break;
			case 2: bx = fx + 4;
					by = fy;
					break;
			case 3: bx = fx;
					by = fy - 4;
					break;
			case 4: bx = fx;
					by = fy + 4;
					break;
			default:
					break;
			}
			Ship carrier = new Ship("carrier", 5, fx, fy, bx, by, false, 0);
			playerShips.add(carrier);
			System.out.println(""+fx+" "+fy+" "+ bx+" "+ by);
			isShipNotInPosition = board.placeShip(carrier.getFrontx(), carrier.getFronty(), carrier.getBackx(), carrier.getBacky(), 5);
			if(isShipNotInPosition == true){
				i = true;
				carrier = null;
			}
			else{
				i = board.placeShip(carrier.getFrontx(), carrier.getFronty(), carrier.getBackx(), carrier.getBacky(), 5);
			}
			System.out.println("Player "+player+" board...");
			board.printBoard();
		}
		System.out.println("carrier");
	}
	*/
	
}
	
