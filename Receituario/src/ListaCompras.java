import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ListaCompras {
	public int geraListaCompras(int[] quantidade, String arquivo) {
		int i = 0;
		String linha = null;
		try {
			BufferedReader lerArquivo = new BufferedReader(new FileReader(arquivo));
			PrintWriter listaCompras = new PrintWriter("Lista de Compras.txt");
			linha = lerArquivo.readLine();
			while(linha != null) {
				String[]fields = linha.split(" - ");
				if(quantidade[i] > 0) {//preenche na lista somente os ingredientes q precisam ser comprados 
					listaCompras.println(fields[0] + " - " + quantidade[i] + " " + fields[1]);
					i++;		
					linha = lerArquivo.readLine();
				}
				else {//se nao houver ingredientes a serem comprados a linha e pulada
					i++;
					linha = lerArquivo.readLine();
				}
			}
			listaCompras.close();
			lerArquivo.close();
			return 0;
		} catch (IOException e) {
			System.err.printf("Erro na criacao do arquivo: %s. \n",
					e.getMessage());
			return 3;
		}
	}
}
