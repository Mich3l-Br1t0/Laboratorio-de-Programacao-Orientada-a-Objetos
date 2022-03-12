import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CriaTxt {

	private static String nomeArquivo;
	
	public static void setNomeArquivo(String nome) {
		nomeArquivo = nome + ".txt";//nome do arquivo q ira ser modificado
	}

	public static void deletaArquivo(String nome) {
		File arquivo = new File(nome);
		arquivo.delete();
	}
	
	public static void geraArquivoTxt(String nome) {
		try {
			nomeArquivo = nome + ".txt";
			File arquivo = new File(nomeArquivo);
			arquivo.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void geraArquivoCript(String nome) {
		try {
			nomeArquivo = nome + ".cript";
			File arquivo = new File(nomeArquivo);
			arquivo.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public static void escritor(List<String> conteudo) {
		try {
			BufferedWriter buffWrite = new BufferedWriter
					(new FileWriter(nomeArquivo, true));
			for(String linha:conteudo) {
				buffWrite.append(linha + "\n");//adiciono a linha ao arquivo txt
			}
			buffWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
