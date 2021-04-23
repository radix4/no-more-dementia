package application.models;

public class User {
    private String name;
    private String email;
    private String password;
    private int[] topScores;
    

    public int[] getTopScores() {
		return topScores;
	}

	public void setTopScores(int[] topScores) {
		this.topScores = topScores;
	}

	/**
     * Constructor
     *
     * @param name of user
     * @param email of user
     * @param password of user
     */
    public User(String name, String email, String password, int[] scores) {
        this.name = name;
        this.email = email;
        this.password = password;
        topScores = scores;
    }

    /** ===== Getters and setters ===== */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

