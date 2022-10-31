import java.util.*;

public class GeneticAlgorithm {
   private int populationSize;
   private int mutationChance;
   private Queens queensBoard;
   private Queens[] population;
   
   private int numberOfQueens = 8;
   private int randomNumberForFunctions = (int) Math.random();
   
   public GeneticAlgorithm(int populationSize, int mutationChance) {
	  this.populationSize = populationSize;
	  this.mutationChance = mutationChance;
	  int[] firstPositions = new int[numberOfQueens];
      for (int i = 0; i < numberOfQueens; i++) {
         firstPositions[i] = (randomNumberForFunctions * numberOfQueens);
      }
	  population = new Queens[populationSize];
      for (int i = 0; i < populationSize; i++) {
         population[i] = new Queens(firstPositions);
      }
      queensBoard = createBoard();
   }
   
   public void createNewGeneration() {
      if (queensBoard.getFitnessValue() > 0) {
          for (int i = 0; i < populationSize; i ++) {
             Queens[] children = createChildren(this.population); 
             
             population[i] = children[0]; 
             if (i != populationSize - 1) {
               population[i + 1] = children[1];
             }
          }
          queensBoard = createBoard();
      }
   }
   
   
   public void drawFinalPositionOfQueens() {
      int[] positions = queensBoard.getPositions();
      if(queensBoard.getFitnessValue() == 0) {
    	  for (int row = 0; row < 8; row++){
    		  System.out.println("");
    		  System.out.println("---------------------------------");
    		  for (int column = 0; column < 8; column++){	
    			  if(positions[row] == column) {
    				  System.out.print("| " + "Q" + " ");
    			  }
    			  else {
    				  System.out.print("| " + " " + " ");
    			  }
        	  }       
        	  System.out.print("|");
    	  }
    	  System.out.println("");
    	  System.out.println("---------------------------------");
     }
   }
     
   public Queens[] createChildren(Queens[] firstPopulation) {
      Queens[] children = new Queens[2];
      int crossoverPoint = 3;
      int[] firstChild;
      int[] secondChild;
      
      firstChild = joinArrays(firstPopulation[0].getPositions(),firstPopulation[1].getPositions(), crossoverPoint);
      secondChild = joinArrays(firstPopulation[1].getPositions(),firstPopulation[0].getPositions(), crossoverPoint);
      
      children[0] = new Queens(firstChild);
      children[1] = new Queens(secondChild);
      
      return children;
   }
  
   public int[] joinArrays(int[] first, int[] second, int crossOver) {
	   int[] firstParent = new int[crossOver];
	   for (int i = 0; i < firstParent.length; i++) {
	         firstParent[i] = first[0 + i];
	   }
	   int[] secondParent = new int[numberOfQueens - crossOver];
	   for (int i = 0; i < secondParent.length; i++) {
		   secondParent[i] = second[crossOver + i];
	   }
	   int[] child = new int[firstParent.length + secondParent.length];
	      
	   System.arraycopy(firstParent, 0, child, 0, firstParent.length);
	   System.arraycopy(secondParent, 0, child, firstParent.length, secondParent.length);
	   
	   //mutation
	   Random randI = new Random();
       int myRandInt = randI.nextInt(100);
       myRandInt = myRandInt+1;
       
       if (myRandInt <= mutationChance) {
           int randIndex = (int) Math.round(Math.random() * (numberOfQueens - 1));
           child[randIndex] = (int) Math.round(Math.random() * (numberOfQueens - 1));
        }
	   
	   return child;
   }
   
   public Queens createBoard() {
      Queens board = population[0];
      int maxFitness = Integer.MAX_VALUE;
      for (int i = 0; i < populationSize; i++) {
         if (population[i].getFitnessValue() < maxFitness) {
        	 maxFitness = population[i].getFitnessValue();
            board = population[i];
         }
      }
      return board;
   }
   
	public int getPopulationSize() {
		return populationSize;
	}
	
	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}
	
	public int getMutationChance() {
		return mutationChance;
	}
	
	public void setMutationChance(int mutationChance) {
		this.mutationChance = mutationChance;
	}
   

}