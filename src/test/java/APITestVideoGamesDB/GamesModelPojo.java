package APITestVideoGamesDB;

public class GamesModelPojo {
    private Integer id;
    private String name;
    private String releaseDate;
    private Integer reviewScore;
    private String category;
    private String rating;

    public GamesModelPojo() {
    }

    public GamesModelPojo(Integer id, String name, String releaseDate, Integer reviewScore, String category, String rating) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.reviewScore = reviewScore;
        this.category = category;
        this.rating = rating;

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Integer getReviewScore() {
        return reviewScore;
    }

    public String getCategory() {
        return category;
    }

    public String getRating() {
        return rating;
    }
}
