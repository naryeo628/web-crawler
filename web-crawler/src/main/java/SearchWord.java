
public class SearchWord {
	private int rank;
	private String word;
	private int degree;
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}
	public void addDegree(int degree) {
		this.degree += degree;		
	}
	
}
