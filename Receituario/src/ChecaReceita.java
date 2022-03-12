import java.util.ArrayList;

public class ChecaReceita {
	public int checaReceita(String alimento, ArrayList<String> possiveisAlimentos) {
		for(int i = 0; i < possiveisAlimentos.size(); i ++) {
			if(possiveisAlimentos.get(i).contains(alimento) == true) {
				return i;//delvolve posicao em que encontrou a palavra
			}
		}
		return -1;
	}
}
