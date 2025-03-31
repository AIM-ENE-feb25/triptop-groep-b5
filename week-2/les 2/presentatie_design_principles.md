# Design principles

## Naam & definitie principe
DIP - Dependency Inversion Principle

### Definitie
Hoge niveau modules mogen niet afhankelijk zijn van lage niveau modules. Beide moeten afhankelijk zijn van abstracties. Daarnaast moeten abstracties niet afhankelijk zijn van details. Details moeten afhankelijk zijn van abstracties.

Dit betekent dat in plaats van directe afhankelijkheden tussen klassen, de afhankelijkheden worden beheerd door interfaces.

## Consequenties van DIP
- Betere modulariteit - Code is makkelijk te begrijpen en te onderhouden.
- Klassen zijn minden afhankelijk van elkaar.
- Betere testbaarheid - Klassen kunnen makkelijk worden getest door middel van mock objecten.
- Flexibiliteit - Het is makkelijker om code aan te passen en uit te breiden.

## Voorbeeld
```java
//IReviewDao.java
public interface IReviewDao {

    List<Review> getAllReviewsByUsers();

    List<Review> getAllReviewsByGuestbook(Guestbook guestbook);

    List<Review> getReviewsByUser(Review user);

    List<Review> getReviewsPerUserFromGuestbook(Review review, Guestbook guestbook);

    void addReview(Review Review, Guestbook guestbook);

    void addAnonymousReview(Review Review, Guestbook guestbook);

    void deleteReview(Review Review);
}

//ReviewRepository.java
@Repository
public class ReviewRepository implements IReviewDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReviewRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Review> getAllReviewsByUsers() {
        String sql = "SELECT " +
                "Reviews.Review_ID, " +
                "Reviews.Review, " +
                "Reviews.Review_Date, " +
                "Reviews.Review_Rating, " +
                "Users.Username " +
                "FROM Reviews " +
                "LEFT JOIN Users ON Reviews.User_ID = Users.ID";

        return jdbcTemplate.query(sql, new ReviewRowMapper());
    }

    @Override
    public List<Review> getAllReviewsByGuestbook(Guestbook guestbook) {
        String sql = "SELECT " +
                "Reviews.Review_ID, " +
                "Reviews.Review," +
                "Reviews.Review_Date, Reviews.Review_Rating," +
                "Users.Username FROM Reviews " +
                "LEFT JOIN Users ON Reviews.User_ID = Users.ID WHERE Guestbook = ?";
        return jdbcTemplate.query(sql, new ReviewRowMapper(), guestbook.getGuestBook_ID());
    }

    @Override
    public List<Review> getReviewsByUser(Review user) {
        String sql = "SELECT " +
                "Reviews.Review_ID, " +
                "Reviews.Review," +
                "Reviews.Review_Date, Reviews.Review_Rating," +
                "Users.Username FROM Reviews " +
                "LEFT JOIN Users ON Reviews.User_ID = Users.ID WHERE User_ID = ?";
        return jdbcTemplate.query(sql, new ReviewRowMapper(), user.getUser_ID());
    }

    @Override
    public void addReview(Review review, Guestbook guestbook) {
        String sql = "INSERT INTO Reviews (Review, Review_Date, Review_Rating, User_ID, Guestbook) VALUES (?, GETDATE(), ?, ?, ?)";
        jdbcTemplate.update(sql, review.getReview(), review.getRating(), review.getUser_ID(), guestbook.getLocation());

    }

    @Override
    public void addAnonymousReview(Review review, Guestbook guestbook) {
        String sql = "INSERT INTO Reviews (Review, Review_Date, Review_Rating, Guestbook) VALUES (?, GETDATE(), ?, ?)";
        jdbcTemplate.update(sql, review.getReview(), review.getRating(), guestbook.getLocation());
    }

    @Override
    public void deleteReview(Review review) {
        String sql = "DELETE FROM Reviews WHERE Review_ID = ?";
        jdbcTemplate.update(sql, review.getReview_ID());
    }

    @Override
    public List<Review> getReviewsPerUserFromGuestbook(Review review, Guestbook guestbook) {
        String sql = "SELECT " +
                "Reviews.Review_ID, " +
                "Reviews.Review," +
                "Reviews.Review_Date, Reviews.Review_Rating," +
                "Users.Username FROM Reviews " +
                "LEFT JOIN Users ON Reviews.User_ID = Users.ID WHERE User_ID = ? AND Guestbook = ?";
        return jdbcTemplate.query(sql, new ReviewRowMapper(), review.getUser_ID(), guestbook.getGuestBook_ID());
    }
}
```
## Design properties van DIP
DIP is gebaseerd op de volgende design properties:
* Coupling - Componenten worden minder afhankelijk van elkaar.
* Separation of concerns - Het is makkelijker om code te onderhouden en te begrijpen.
* Extensibility - Het is makkelijker om code uit te breiden.