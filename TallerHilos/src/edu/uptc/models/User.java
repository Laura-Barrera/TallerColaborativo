package edu.uptc.models;

public class User {
	private String id;
	private String procedure;
	private String turn;
	private boolean status;
	
	public User(String id, String procedure, String turn) {
		this.id = id;
		this.procedure = procedure;
		this.turn = turn;
		status = false;
	}
	
	public User() {	}
	
	public String getId() {
		return id;
	}

	public String getProcedure() {
		return procedure;
	}

	public String getTurn() {
		return turn;
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}

	public void setTurn(String turn) {
		this.turn = turn;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", procedure=" + procedure + ", turn=" + turn + "]";
	}
}