import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeituraEntradaTeclado implements AnaliseInput {
	
	public List<String> leituraInput(String arquivo) {
		Scanner sc = new Scanner(System.in);
		List<String> linhas = new ArrayList<>();
		do {
		System.out.println("Digite o que sera copiado: ");
		linhas.add(sc.nextLine());
		System.out.println("Deseja digitar mais (S/N): ");
		}while(sc.nextLine().contentEquals("S"));
		sc.close();
		return linhas;
	}

}
