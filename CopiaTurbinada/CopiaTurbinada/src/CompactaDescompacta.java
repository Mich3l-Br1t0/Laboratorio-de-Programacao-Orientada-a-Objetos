import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class CompactaDescompacta extends Decorador {

	private static final int TAMANHO_BUFFER = 4096;

	public CompactaDescompacta(AnaliseInput newEntrada) {
		super(newEntrada);
	}

	public CompactaDescompacta(AnaliseOutput newSaida) {
		super(newSaida);
	}

	public List<String> leituraInput(String arquivo) {

		try {
			String auxiliar = "";
			if(arquivo.contains(".cript")) {
				auxiliar = arquivo;//armazena os valores originais
				String fields[] = arquivo.split(" ");
				String nome[] = fields[1].split("\\.");
				arquivo = nome[0];
			}
			ZipInputStream zin;
			zin = new ZipInputStream
					(new BufferedInputStream
							(new FileInputStream(arquivo + ".zip")));

			ZipEntry e = zin.getNextEntry();

			unzip(zin, e.getName());

			zin.close();

			if(auxiliar.contains(".cript"))
				arquivo = auxiliar;
		}catch (IOException e1) {
			e1.printStackTrace();
		}
		return tempEntrada.leituraInput(arquivo);
	}

	public void copiaOutput(List<String> conteudo) {//descompacta o arquivo que sera alterado e depois compacta
		try {
			String[] nome;
			String tipo;
			if(informacao.contains(".cript")) {
				String fields[] = informacao.split(" ");
				nome = fields[1].split("\\.");
				tipo = ".cript";
			}
			else {
				nome = informacao.split("\\.");
				tipo = ".txt";
			}
			ZipInputStream zin;
			zin = new ZipInputStream
					(new BufferedInputStream
							(new FileInputStream(nome[0] + ".zip")));

			ZipEntry e = zin.getNextEntry();

			unzip(zin, e.getName());
			zin.close();
			//arquivo a ser modificado foi descompactado
			tempSaida.copiaOutput(conteudo);

			zip(nome[0] + ".zip", nome[0] + tipo);
			CriaTxt.deletaArquivo(nome[0] + ".cript" );//deleta cript criado, poin n e mais necessario
			if(tipo != ".cript")
				CriaTxt.deletaArquivo(nome[0] + ".txt" );//deleta txt criado, poin n e mais necessario
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void unzip(ZipInputStream zin, String s) throws IOException {

		System.out.println("Extraindo: " + s);

		FileOutputStream out = new FileOutputStream(s);

		byte [] b = new byte[512];

		int len = 0;

		while ( (len=zin.read(b))!= -1 ) {

			out.write(b,0,len);

		}

		out.close();

	}


	private void zip(String arqSaida,String arqEntrada)
			throws IOException{
		int cont;
		byte[] dados = new byte[TAMANHO_BUFFER];

		BufferedInputStream origem = null;
		FileInputStream streamDeEntrada = null;
		FileOutputStream destino = null;
		ZipOutputStream saida = null;
		ZipEntry entry = null;

		try {
			destino = new FileOutputStream(new File(arqSaida));
			saida = new ZipOutputStream(new BufferedOutputStream(destino));
			File file = new File(arqEntrada);
			streamDeEntrada = new FileInputStream(file);
			origem = new BufferedInputStream(streamDeEntrada, TAMANHO_BUFFER);
			entry = new ZipEntry(file.getName());
			saida.putNextEntry(entry);

			while((cont = origem.read(dados, 0, TAMANHO_BUFFER)) != -1) {
				saida.write(dados, 0, cont);
			}
			origem.close();
			saida.close();
		} catch(IOException e) {
			throw new IOException(e.getMessage());
		}
	}

}

