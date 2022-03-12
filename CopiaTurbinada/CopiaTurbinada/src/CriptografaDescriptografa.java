import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CriptografaDescriptografa extends Decorador {

	public CriptografaDescriptografa(AnaliseInput newEntrada) {
		super(newEntrada);
	}
	
	public CriptografaDescriptografa(AnaliseOutput newSaida) {
		super(newSaida);
	}

	public List<String> leituraInput(String arquivo){
		try {
			List<String> arquivoDescriptografado = new ArrayList<>();
			String[]fields = arquivo.split(" ");
			String[]nome = fields[1].split("\\.");
			String chaveEncriptacao = fields[0];
			BufferedReader buffer = new BufferedReader
					(new FileReader(nome[0] +".cript"));//recebe o nome do arquivo
			CriaTxt.geraArquivoTxt(nome[0]);//fornece o nome do arquivo para gerar um txt com seu nome
			String linha = buffer.readLine();
			while(linha != null) {//descriptografando arquivo e adicionando suas linhas a uma lista
				fields = linha.split(" ");
				byte [] textoEncriptado = new byte[fields.length];
				for(int i = 0; i < fields.length; i++) {
					textoEncriptado[i] = Byte.parseByte(fields[i]); 
				}
				arquivoDescriptografado.add(decrypt(textoEncriptado, chaveEncriptacao));//adiciona cada linha descriptografada
				linha = buffer.readLine();
			}
			CriaTxt.escritor(arquivoDescriptografado);//cria arquivo base que sera lido/modificado
			//CriaTxt.deletaArquivo(nome[0]+ ".txt");
			buffer.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
			return tempEntrada.leituraInput(arquivo);
	}

	public void copiaOutput(List<String> conteudo) {
		try {
			List<String> arquivoDescriptografado = new ArrayList<>();
			List<String> textoCriptografado = new ArrayList<>();
			byte[] textoencriptadobyte = null; 
			String[]fields = informacao.split(" ");
			String[]nome = fields[1].split("\\.");
			String chaveEncriptacao = fields[0];
			BufferedReader buffer = new BufferedReader
					(new FileReader(nome[0] +".cript"));//recebe o nome do arquivo
			CriaTxt.geraArquivoTxt(nome[0]);//fornece o nome do arquivo para gerar um txt com seu nome
			String linha = buffer.readLine();
			while(linha != null) {//descriptografando arquivo e adicionando suas linhas a uma lista
				fields = linha.split(" ");
				byte [] textoEncriptado = new byte[fields.length];
				for(int i = 0; i < fields.length; i++) {
					textoEncriptado[i] = Byte.parseByte(fields[i]); 
				}
				arquivoDescriptografado.add(decrypt(textoEncriptado, chaveEncriptacao));//adiciona cada linha descriptografada
				linha = buffer.readLine();
			}
			CriaTxt.escritor(arquivoDescriptografado);//cria arquivo base que sera lido/modificado
			buffer.close();//arquivo foi descriptografado e esta pronto p ser modificado
			
			tempSaida.copiaOutput(conteudo);//chega a copia saida arquivo, onde modifica o arquivo original
			
			CriaTxt.deletaArquivo(nome[0] + ".cript");//deleta o cript sobrando
			CriaTxt.geraArquivoCript(nome[0]);//arquivo modificado criado
			BufferedReader buff = new BufferedReader(new FileReader(nome[0] + ".txt"));//abrindo o arquivo modificado
			linha = buff.readLine();
			while(linha != null) {
				textoencriptadobyte = encrypt(linha, chaveEncriptacao);//encriptografa a linha, devolve uma lista
				linha = "";
				for(byte num:textoencriptadobyte) {
					linha += num + " ";//recebe a linha em bytes
				}
				textoCriptografado.add(linha);//adiciona ao arraylist a linha do texto convertida em bytes
				linha = buff.readLine();
			}
			CriaTxt.escritor(textoCriptografado);//recria o arquivo original, porem agora modificado
			buff.close();
			CriaTxt.deletaArquivo(nome[0] + ".txt" );//deleta o txt sobrando, ja q n sera mais util
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	private byte[] encrypt(String textopuro, String chaveencriptacao) throws Exception {
		Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
		encripta.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
		return encripta.doFinal(textopuro.getBytes("UTF-8"));
	}
	
	private String decrypt(byte[]textoencriptado, String chaveencriptacao) throws Exception{
		Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
		decripta.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
		return new String(decripta.doFinal(textoencriptado),"UTF-8");
	}
}
