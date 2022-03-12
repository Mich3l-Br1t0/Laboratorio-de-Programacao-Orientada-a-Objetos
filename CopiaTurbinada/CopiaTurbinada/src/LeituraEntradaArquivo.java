import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeituraEntradaArquivo implements AnaliseInput {
		
	public List<String> leituraInput(String arquivo) {//leitura base, sempre estara presente 
		List <String> conteudo = new ArrayList<>();
		try {
			if(arquivo.contains(" ")) {//para arquivos .cript
				String fields[] = arquivo.split(" ");
				String nome[] = fields[1].split("\\.");
				arquivo = nome[0];
			}
			
			BufferedReader buffer = new BufferedReader
					(new FileReader(arquivo + ".txt"));
			String linha = buffer.readLine();
			while(linha != null){
				conteudo.add(linha);
				linha = buffer.readLine();
			}
			buffer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conteudo;
	}

}
