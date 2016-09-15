public class Board {
	
	int x,y;
	
	int[][] board;
	
	public Board(int rowSize, int columnSize){
	
		x= columnSize+1;
		y= rowSize+1;
		board = new int[y][x];
		boardInit(board, y, x);

	}
	
	
	public int[][] boardInit(int[][] board,int rowSize,int columnSize){
		
		board[0][0] = 0;
		for (int i = 1; i < (columnSize); i++){
			board[0][i] = i;
			
		}
		for (int i = 1; i < (rowSize); i++){
			board[i][0] = i;
			
		}
		return board;
	}
	
	 public void printBoard(){
		 for(int i = 0; i < y; i++){
			 for(int j =0; j < x; j++){
				 if(i==0&&j==0){
					 System.out.print("\t"); 
				 }
				 else{
				 System.out.print("\t" +board[i][j]);
				 }
				} 
			 System.out.print("\n\n");
		 }	 
	 }
	 
	 public boolean placeShip(int frontx,int fronty,int backx,int backy, int length){
		 try{
		 	
		 if(board[fronty][frontx] == 0 && board[backy][backx] == 0 && (frontx<=backx && fronty==backy)){
			 int valid = 0;
			 for(int j = 1; j < length; j++){
				 if(board[fronty][frontx+j] != 0){
					 valid = 1;
					 System.out.println("Ship already in this location");
					 break;
				 }
				 else{
					 valid = 0;
				 }
			 }
			 for(int i = 0; i < length; i++){
				 if(valid == 0){
				 board[fronty][frontx+i] = 1;
				 }
			 }
			 return false;
		 }
		 else if(board[fronty][frontx] == 0 && board[backy][backx] == 0 && (frontx>=backx && fronty==backy)){
			 int valid = 0;
			 for(int j = 1; j < length; j++){
				 if(board[fronty][frontx+j] != 0){
					 valid = 1;
					 System.out.println("Ship already in this location");
					 break;
				 }
				 else{
					 valid = 0;
				 }
			 }
			 for(int i = 0; i < length; i++){
				 if(valid == 0){
				 board[fronty][frontx-i] = 1;
				 }
			 }
			 return false;
		 }
		 else if(board[fronty][frontx] == 0 && board[backy][backx] == 0 && (frontx==backx && fronty>=backy)){
			 int valid = 0;
			 for(int j = 1; j < length; j++){
				 if(board[fronty][frontx+j] != 0){
					 valid = 1;
					 System.out.println("Ship already in this location");
					 break;
				 }
				 else{
					 valid = 0;
				 }
			 }
			 for(int i = 0; i < length; i++){
				 if(valid == 0){
				 board[fronty-i][frontx] = 1;
				 }
			 }
			 return false;
		 }
		 else if(board[fronty][frontx] == 0 && board[backy][backx] == 0 && (frontx==backx && fronty<=backy)){
			 int valid = 0;
			 for(int j = 1; j < length; j++){
				 if(board[fronty][frontx+j] != 0){
					 valid = 1;
					 System.out.println("Ship already in this location");
					 break;
				 }
				 else{
					 valid = 0;
				 }
			 }
			 for(int i = 0; i < length; i++){
				 if(valid == 0){
				 board[fronty+i][frontx] = 1;
				 }
			 }
			 return false;
		 }

		else if(frontx==backy && fronty==backx){
			System.out.println("Ship position invalid");
			return true;
			
		}
		else{
			System.out.println("Ship already in this position");
			return true;
		}
		}
		 catch(ArrayIndexOutOfBoundsException aie){
			 System.out.println("This position is NOT on the board!!");
			 return true;
		 }
		 
		 
	 }
	 
	 public void boardShipUpdate(int xCoord, int yCoord, int shipValue){
		 if(shipValue == 0){
			 board[yCoord][xCoord]= -1; 
		 }
		 else if(shipValue == 2){
			 board[yCoord][xCoord] = 2; 
		 }
		 else if(shipValue == 3){
			 board[yCoord][xCoord] = 3;
			 
		 }
		 
	 }
	 
	/* public void aiShipPlace(int frontx, int fronty, int shipSize){
		 try{
			 
			 int opp = frontx%2;
			 int xOry = fronty%2;
			 if(board[fronty][frontx] != 0){
				 return true;
				 
			 }
			 
			 else{
				 board[fronty][frontx] = 1;
			 }
			 
			 if(opp == 0 && xOry == 0){
				 for(int i = 0; i<shipSize; i++){
				 int backy = fronty;
				 int backx = frontx - i;
				 	if(board[backy][backx]==1){
					 return true;
				 	}
				 	if(i == shipSize-1){
					 for(int j = 0; j<shipSize; j++){
						 int backy1 = fronty;
						 int backx1 = frontx - j;
						 board[backy1][backx1] = 1;
					 }
					 return false;
				 	}
				 }
			 }
			 else if(opp == 0 && xOry == 1){
				 for(int i = 0; i<shipSize; i++){
				 int backy = fronty - i;
				 int backx = frontx;
				 	if(board[backy][backx]==1){
					 return true;
				 	}
				 	if(i == shipSize-1){
					 for(int j = 0; j<shipSize; j++){
						 int backy1 = fronty - j;
						 int backx1 = frontx;
						 board[backy1][backx1] = 1;
					 }
					 return false;
				 	}
				 }
			 }
			 else if(opp == 1 && xOry == 0){
				 for(int i = 0; i<shipSize; i++){
				 int backy = fronty;
				 int backx = frontx + i;
				 	if(board[backy][backx]==1){
					 return true;
				 	}
				 	if(i == shipSize-1){
					 for(int j = 0; j<shipSize; j++){
						 int backy1 = fronty;
						 int backx1 = frontx + j;
						 board[backy1][backx1] = 1;
					 }
					 return false;
				 	}
				 }
			 }
			 if(opp == 1 && xOry == 1)
			 else{
				 for(int i = 0; i<shipSize; i++){
				 int backy = fronty + i;
				 int backx = frontx;
				 	if(board[backy][backx]==1){
					 return true;
				 	}
				 	if(i == shipSize-1){
					 for(int j = 0; j<shipSize; j++){
						 int backy1 = fronty + j;
						 int backx1 = frontx;
						 board[backy1][backx1] = 1;
					 }
					 return false;
				 	}
				 }
			 }
			 
		 }
		 catch(ArrayIndexOutOfBoundsException aie){
			 return true;
		 }
		 return false;
	 }*/

}
