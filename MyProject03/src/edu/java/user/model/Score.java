package edu.java.user.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Score {

	private int topScpre;
	private LocalDateTime time;
	
	public Score(int topScpre, LocalDateTime time){
		this.topScpre = topScpre;
		this.time =time;
	}

	public int getTopScpre() {
		return topScpre;
	}

	public void setTopScpre(int topScpre) {
		this.topScpre = topScpre;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	
}
