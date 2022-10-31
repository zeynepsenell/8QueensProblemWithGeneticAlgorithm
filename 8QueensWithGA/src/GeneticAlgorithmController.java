import java.util.Scanner;

public class GeneticAlgorithmController {

	private GeneticAlgorithm algorithm;
	int population = -1;
	int mutationChance = -1;
	
	public GeneticAlgorithmController() { 
		this.getValues();
		algorithm = new GeneticAlgorithm(population, mutationChance);
	}
	
	public static void main(String[] args) {
		GeneticAlgorithmController controller = new GeneticAlgorithmController();
	    controller.startTheAlgorithm();
	}
	
	private void getValues() {
		Scanner scanner = new Scanner(System.in);
		while(population <= 0 || population > 200) {
			System.out.println("Please give the population number (between 0-200): ");
			population = scanner.nextInt();
		}
		while(mutationChance < 0 || mutationChance >100) {
			System.out.println("Please give the mutation chance (between 0-100): ");
			mutationChance = scanner.nextInt();
		}
		scanner.close();
	}
		   
	private void startTheAlgorithm() {
		Queens bestBoard = algorithm.createBoard();
		int fitness = bestBoard.getFitnessValue();
		while (fitness > 0) {
			bestBoard = algorithm.createBoard();
			fitness = bestBoard.getFitnessValue();
			if(fitness == 0) {
				algorithm.drawFinalPositionOfQueens();
				break;
			}
			algorithm.createNewGeneration();
		}
	}
}
