public class Queens {
   private int[] positions;
   private final int numberOfQueens = 8;
   private int[] newListOfPositions; 
   private int[] newPositions;
   private int fitness = 0;
   
   public Queens(int[] positions) {
      this.setPositions(positions);
      newPositions = positions;
      fitness = calculateFitnessValue();
   }
   
   public int calculateFitnessValue() {  
      this.checkDiagonals();
      this.checkAttacks();
      return fitness;
   }
   
   private void checkAttacks() {
	   while (newPositions.length > 0) {
		   int totalAttacks = countNumberOfAttackingQueens(newPositions, newPositions[0], 0);
		   if (totalAttacks > 1) {
			   fitness += totalAttacks;
	       }
		   countNumberOfAttackingQueens(newPositions, newPositions[0], newPositions.length - totalAttacks);
		   newPositions = this.newListOfPositions;
	   }   
	   newPositions = positions;
   }
   
   private void checkDiagonals() {
	   int[][] numberOfDiagonals = new int[2][numberOfQueens];
	   for (int i = 0; i < 2; i++) {
		   for (int j = 0; j < numberOfQueens; j++) {
			   if (i == 0)
				   	numberOfDiagonals[i][j] = this.newPositions[j] + j;
	           	else
	           		numberOfDiagonals[i][j] = this.newPositions[j] - j;
		   }
	   }
	  for (int i = 0; i < 2; i++) {
		  while (numberOfDiagonals[i].length > 0) {
			  int numAttacks = countNumberOfAttackingQueens(numberOfDiagonals[i], numberOfDiagonals[i][0] , 0);
			  if (numAttacks > 1) {
	              fitness += numAttacks;
	           }
			  countNumberOfAttackingQueens(numberOfDiagonals[i], numberOfDiagonals[i][0], numberOfDiagonals[i].length - numAttacks);
			  numberOfDiagonals[i] = this.newListOfPositions;
	        }
	  }
   }
   
   private int countNumberOfAttackingQueens(int[] listOfQueens, int num, int lengthOfNewList) {
      int numberOfQueensAtTheSameRow = 0;
      int j = 0;
	  int[] newList = new int[lengthOfNewList];
      boolean calculateNewList = false;
      if(lengthOfNewList != 0) {
          calculateNewList = true;
      }
      for (int i = 0; i < listOfQueens.length; i++) {
         if (listOfQueens[i] == num) {
        	 numberOfQueensAtTheSameRow++;
         }
         else if(calculateNewList){
        	 newList[j] = listOfQueens[i];
             j++;
         } 
      }
      this.newListOfPositions = newList;
      return numberOfQueensAtTheSameRow;
   }
   

	public int getNumberOfQueens() {
		return numberOfQueens;
	}     	
	
	public int[] getPositions() {
	    return positions;
	 }
	 
	 public int getFitnessValue() {
	    return fitness;
	 }

	public void setPositions(int[] positions) {
		this.positions = positions;
	}
}