import java.util.Scanner;

public class Media
{
	public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
		System.out.println("Escreva 5 valores numéricos:");
		float sum = 0;
		for(int i = 0; i < 5; i++){
		    float readNumber = Float.parseFloat(input.nextLine());
		    sum+= readNumber;
		}
		sum/=5;
		
		System.out.println("Média dos cinco valores: " + sum);
		input.close();
	}
}

// float arr[] = new float[5];