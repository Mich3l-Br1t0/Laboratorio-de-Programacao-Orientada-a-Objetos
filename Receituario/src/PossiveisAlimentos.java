import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class PossiveisAlimentos {
	public ArrayList<String> getListaAlimentos(Reader arquivo){
		
		String linha = null;
		ArrayList<String> listaAlimentos = new ArrayList<String>();
		BufferedReader lerArquivo = new BufferedReader(arquivo);
		try {
			linha = lerArquivo.readLine();
			while(linha != null) {
				String[]fields = linha.split(" - ");
				listaAlimentos.add(fields[0]);//adiciona a parte a esquerda da linha
				linha = lerArquivo.readLine();				
			}
		} catch (IOException e) {
			System.err.printf("Erro na leitura do arquivo: %s. \n",
					e.getMessage());
		}
		return listaAlimentos;
	}
}
