import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class QuantidadeAlimentos {
	public int getListaAlimentos(String arquivoReceitas, 
			ArrayList<String> possiveisAlimentos, 
			int[] quantidade) {
		//ContaCaracteres num = new ContaCaracteres();
		ChecaReceita cr = new ChecaReceita();
		String linhaTxt = null;
		
		try {
			BufferedReader lerTxt = new BufferedReader(new FileReader(arquivoReceitas));//Abrir arquivo contendo os nomes das receitas
			linhaTxt = lerTxt.readLine();
			while(linhaTxt != null){
				if(linhaTxt.contains(".txt")==false) {
					lerTxt.close();
					throw new FormatoException();
				}
				BufferedReader lerReceita = new BufferedReader(new FileReader(linhaTxt));//ler a receita da respectiva linha 
				String linhaReceita = lerReceita.readLine();
				

				for(int i = 0; i < 4 ; i++) {
					linhaReceita = lerReceita.readLine();//pular as primeiras 4 linhas			
				}

				while(linhaReceita.isEmpty() == false) {
					String[]fields = linhaReceita.split(" - ");
					if(cr.checaReceita(fields[0], possiveisAlimentos) >= 0)//checa se os ingredientes da receita pertence aos possiveis
						quantidade[cr.checaReceita(fields[0], possiveisAlimentos)] += Integer.parseInt(fields[1]);

					else {
						lerReceita.close();
						lerTxt.close();
						throw new ReceitaException();
					}
					linhaReceita = lerReceita.readLine();
				}
				lerReceita.close();
				linhaTxt = lerTxt.readLine();//proxima linha do arquivo de receitas
			}
			lerTxt.close();//voltar para o inicio do arquivo, sera necessario pois ele sera lido futuramente
			return 0;
		}
		catch (FormatoException e) {
			System.out.println("Erro: Receita nao esta no formato adequado");
			e.printStackTrace();
			return 1;
		}
		catch (ReceitaException e) {
			System.out.println("Erro: Alimento nao esta na lista de possiveis");
			e.printStackTrace();
			return 2;
		}
		catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
			return 3;
		}
		catch (RuntimeException e) {
			System.out.println("Erro Inesperado");
			e.printStackTrace();
			return 4;
		}
	}
}
