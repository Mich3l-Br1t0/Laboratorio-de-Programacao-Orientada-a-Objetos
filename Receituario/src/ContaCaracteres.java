import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ContaCaracteres {
	public int contaCaracteres(String arquivo) {
		int numeroCaracteres = 0; 
		int aux = 0;
		try {
			BufferedReader lerArquivo = new BufferedReader(new FileReader (arquivo));
			do{
				aux = lerArquivo.read();
				numeroCaracteres ++;
			}while(aux != -1);
			lerArquivo.close();
		} catch (IOException e) {
			System.out.println("Nao foi possivel contar o numero de caracteres" + e.getMessage());
		}

		return numeroCaracteres + 1;
	}
}
